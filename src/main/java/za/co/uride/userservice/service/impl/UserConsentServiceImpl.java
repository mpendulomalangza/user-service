package za.co.uride.userservice.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.UserConsent;
import za.co.uride.userservice.domain.repository.UserConsentRepository;
import za.co.uride.userservice.dto.UserConsentDto;
import za.co.uride.userservice.service.UserConsentService;

import java.time.LocalDateTime;
import java.time.Month;

@RequiredArgsConstructor
@Service
public class UserConsentServiceImpl implements UserConsentService {
    private final ModelMapper modelMapper;
    private final UserConsentRepository repository;

    @Override
    public void save(@Valid UserConsentDto userConsentDto) {
        UserConsent userConsent = modelMapper.map(userConsentDto, UserConsent.class);
        userConsent.setEffFrom(LocalDateTime.now());
        userConsent.setEffTo(LocalDateTime.of(9999, Month.DECEMBER, 31, 23, 59));
        repository.save(userConsent);
    }
}
