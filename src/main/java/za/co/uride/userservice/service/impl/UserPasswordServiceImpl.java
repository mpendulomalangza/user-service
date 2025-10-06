package za.co.uride.userservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.UserPassword;
import za.co.uride.userservice.domain.repository.UserPasswordRepository;
import za.co.uride.userservice.dto.UserPasswordDto;
import za.co.uride.userservice.exception.PasswordException;
import za.co.uride.userservice.exception.PasswordUsedBeforeException;
import za.co.uride.userservice.exception.UpdateException;
import za.co.uride.userservice.service.UserPasswordService;

import java.util.Optional;

@Service
@Slf4j
public class UserPasswordServiceImpl implements UserPasswordService {
    private final UserPasswordRepository repository;
    private final ModelMapper modelMapper;
    private final String passwordEncoder;

    public UserPasswordServiceImpl(UserPasswordRepository repository, ModelMapper modelMapper, @Value("${password.encoder}") String passwordEncoder) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param userPasswordDto user password object
     */
    @Override
    public void create(UserPasswordDto userPasswordDto) {
        try {

            UserPassword userPassword = modelMapper.map(userPasswordDto, UserPassword.class);
            userPassword.setPassword(encodePassword(userPasswordDto.getPassword()));
            userPassword.setStatus(true);
            repository.save(userPassword);
        } catch (Exception ex) {
            log.debug(ex.getMessage(), ex);
            throw new UpdateException(ex.getMessage());
        }
    }

    /**
     * @param userPasswordDto user password object
     */

    @Override
    public void update(UserPasswordDto userPasswordDto) {
        UserPassword userPassword = modelMapper.map(userPasswordDto, UserPassword.class);
        Optional<UserPassword> optional = repository.findByUserAndPasswordAndStatus(userPassword.getUser(), encodePassword(userPasswordDto.getPassword()), true);
        if (optional.isPresent()) {
            throw new PasswordUsedBeforeException("Please use a password that was not used in the past");
        }
        userPassword.setStatus(true);
        repository.save(userPassword);
    }

    /**
     * @param plainTextPassword plain  text password
     * @return returns encrypted password
     */
    private String encodePassword(String plainTextPassword) {
        SCryptPasswordEncoder encoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        return String.format("{%s}".concat(encoder.encode(plainTextPassword)), passwordEncoder);
    }

    /**
     * @param userPasswordDto user password object
     */
    @Override
    public void verifyPassword(UserPasswordDto userPasswordDto) {
        UserPassword userPassword = modelMapper.map(userPasswordDto, UserPassword.class);
        Optional<UserPassword> optional = repository.findByUserAndPasswordAndStatus(userPassword.getUser(), encodePassword(userPasswordDto.getPassword()), true);
        if (optional.isEmpty()) {
            throw new PasswordException(String.format("User password for username [%s] does not exist", userPassword.getUser().getUsername()));
        }
    }
}
