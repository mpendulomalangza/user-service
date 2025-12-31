package za.co.uride.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.dto.ContactDetailsDto;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.FindContactDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.ContactDetailService;
import za.co.uride.userservice.service.ContactService;
import za.co.uride.userservice.service.UserAvatarService;

@RequiredArgsConstructor
@Service

public class ContactDetailServiceImpl implements ContactDetailService {
    private final UserAvatarService userAvatarService;
    private final ContactService contactService;

    @PreAuthorize("hasAuthority('find-contact')")
    @Cacheable(cacheNames = "defaultCache")
    @Override
    public ContactDetailsDto find(FindContactDto findContactDto) {
        ContactDto contactDto = contactService.findById(findContactDto.getId());
        UserDto userDto = contactDto.getUser();
        ContactDetailsDto.ContactDetailsDtoBuilder contactDetailsDtoBuilder = ContactDetailsDto.builder().contactName(userDto.getName());
        if (findContactDto.getEContactType().equals(EContactType.FCM_TOKEN)) {
            contactDetailsDtoBuilder.contact(contactDto.getFcmToken());
        } else if (findContactDto.getEContactType().equals(EContactType.CELLPHONE)) {
            contactDetailsDtoBuilder.contact(contactDto.getCellphoneNumber());
        } else if (findContactDto.getEContactType().equals(EContactType.EMAIL)) {
            contactDetailsDtoBuilder.contact(contactDto.getEmailAddress());
        }
        try {
            contactDetailsDtoBuilder.avatar(userAvatarService.findAll(findContactDto.getId()).getUrl());
        } catch (FindException ignored) {

        }
        return contactDetailsDtoBuilder.build();
    }

}
