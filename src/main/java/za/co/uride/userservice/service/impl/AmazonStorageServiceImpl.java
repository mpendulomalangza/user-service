package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.AmazonStorageService;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AmazonStorageServiceImpl implements AmazonStorageService {
    @Value("${s3.bucket}")
    private String s3Bucket;
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    @Value("${s3.allowed-mime-types}")
    private List<String> allowedTypes;
    @Value("${s3.maximum-file-size}")
    private Long maxFileSize;

    private void bucketExist() {
        try {
            s3Client.headBucket(request -> request.bucket(s3Bucket));
        } catch (NoSuchBucketException exception) {
            throw new FindException("Bucket not found");
        }
    }

    /**
     *
     * @param file file
     * @return {@link String} mime type from content stream
     * @throws Exception throws an exception when it fails
     */
    private String detectFileType(MultipartFile file) throws Exception {
        Tika tika = new Tika();
        return tika.detect(file.getInputStream());
    }


    @CacheEvict(cacheNames = "imageCache", key = "#key")
    @Override
    public void upload(MultipartFile file, String key) {
        try {
            bucketExist();
            String mimeType = detectFileType(file);
            if (!allowedTypes.contains(mimeType) && file.getSize() / 1000 < maxFileSize) {
                throw new FindException("File is not supported");
            }
            Map<String, String> metadata = new HashMap<>();
            try {
                GetObjectResponse getObjectResponse = s3Client.getObject(builder -> builder.key(key).bucket(s3Bucket)).response();
                if (getObjectResponse != null && getObjectResponse.contentLength() > 0) {
                    s3Client.deleteObject(request ->
                            request
                                    .bucket(s3Bucket)
                                    .key(key));
                }
            } catch (NoSuchKeyException ignored) {
            }
            s3Client.putObject(request ->
                            request
                                    .bucket(s3Bucket)
                                    .key(key)
                                    .metadata(metadata)
                                    .ifNoneMatch("*"),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable(cacheNames = "imageCache")
    @Override
    public String getFile(String key) {
        try {
            bucketExist();
            // For a GET (download) request
            GetObjectPresignRequest getRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(GetObjectRequest.builder()
                            .bucket(s3Bucket)
                            .key(key)
                            .build())
                    .signatureDuration(Duration.ofMinutes(30)).build();

            PresignedGetObjectRequest presignedGetObject = s3Presigner.presignGetObject(getRequest);
            return presignedGetObject.url().toString();
        } catch (Exception ex) {
            throw new FindException(ex.getMessage());
        }
    }
}
