package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.OTP;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.repository.OTPRepository;
import za.co.uride.userservice.dto.OTPDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EVerificationType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.OTPService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {
    private final Random random = new Random();
    private final ModelMapper modelMapper;
    private final OTPRepository repository;


    @Override
    public OTPDto findByUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Optional<OTP> optional = repository.findByUserAndExpiryDateIsAfterAndStatus(user, LocalDateTime.now(), true);
        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), OTPDto.class);
        }
        throw new FindException(String.format("OTP for user with id[%s] does not exist", userDto.getId()));
    }

    @Override
    public OTPDto createOTP(UserDto userDto, EVerificationType verificationType) {
        User user = modelMapper.map(userDto, User.class);
        String otpValue = String.format("%04d", random.nextInt(10000));
        OTP otp = OTP.builder().expiryDate(LocalDateTime.now().plusMinutes(30)).user(user).otp(otpValue).type(verificationType).build();
        //Check for user existing OTP
        Optional<OTP> optionalOTP = repository.findByUserAndStatus(user, true);
        if (optionalOTP.isPresent()) {
            otp.setId(optionalOTP.get().getId());
        }
        otp.setStatus(true);
        otp = repository.save(otp);
        return modelMapper.map(otp, OTPDto.class);
    }

    @Override
    public void validateOTP(OTPDto optDto) {
        User user = modelMapper.map(optDto.getUser(), User.class);
        Optional<OTP> optional = repository.find(user, LocalDateTime.now(), optDto.getOtp(), optDto.getType(), true);
        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new FindException(String.format("OTP [%s] for user with id[%s] does not exist", optDto.getOtp(), optDto.getUser().getId()));
        }
    }

    @Override
    public void delete(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Optional<OTP> optional = repository.findByUserAndStatus(user, true);
        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new FindException(String.format("OTP for user with id[%s] does not exist", userDto.getId()));
        }
    }

}
