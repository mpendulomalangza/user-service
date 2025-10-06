package za.co.uride.userservice.service;

import org.springframework.web.multipart.MultipartFile;
public interface AmazonStorageService {

    String upload(MultipartFile file);

    String getFile(String key);
}
