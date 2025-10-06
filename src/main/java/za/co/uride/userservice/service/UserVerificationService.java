package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.CompleteVerifyUserDto;
import za.co.uride.userservice.dto.VerifyUserDto;

public interface UserVerificationService {

    void startVerification(VerifyUserDto verifyUserDto);

    void complete(CompleteVerifyUserDto completeUserVerificationDto);
}
