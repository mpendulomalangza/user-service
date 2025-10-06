package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.UserPasswordDto;

import java.security.NoSuchAlgorithmException;

public interface UserPasswordService {
    void create(UserPasswordDto passwordDto) throws NoSuchAlgorithmException;

    void update(UserPasswordDto userPasswordDto) throws NoSuchAlgorithmException;

    void verifyPassword(UserPasswordDto userPasswordDto) throws NoSuchAlgorithmException;
}
