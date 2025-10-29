package za.co.uride.userservice.service;

import org.springframework.web.multipart.MultipartFile;
public interface AmazonStorageService {

    void upload(MultipartFile file, String key);

    String getFile(String key);
}
