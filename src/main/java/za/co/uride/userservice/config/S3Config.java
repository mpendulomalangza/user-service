package za.co.uride.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

    @Value("${s3.access-key}")
    private String accessKey;
    @Value("${s3.secret}")
    private String secret;

    @Bean
    public S3Client s3Client() {
        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secret);
        return S3Client
                .builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(() -> credentials)
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {

        return S3Presigner.builder()
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secret)).region(Region.US_EAST_1)
                .build();
    }
}


