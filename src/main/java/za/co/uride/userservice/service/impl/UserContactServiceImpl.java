package za.co.uride.userservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.UserContactDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.dto.VerifyUserDto;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.enums.EUserType;
import za.co.uride.userservice.enums.EVerificationType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.ContactService;
import za.co.uride.userservice.service.UserContactService;
import za.co.uride.userservice.service.UserService;
import za.co.uride.userservice.service.UserVerificationService;

@Service
@RequiredArgsConstructor
public class UserContactServiceImpl implements UserContactService {
    private final ContactService contactService;
    private final UserService userService;
    private final UserVerificationService userVerificationService;

    @Transactional
    @Override
    @PreAuthorize(value = "hasAuthority('edit-contact')")
    public void updateContact(UserContactDto userContactDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findByUsername(authentication.getPrincipal().toString(), EUserType.PROFILE);
        ContactDto contactDto = contactService.findByUser(userDto);
        EVerificationType eVerificationType = null;
        if (userContactDto.getContactType().equals(EContactType.EMAIL) && !contactDto.getEmailAddress().equalsIgnoreCase(userContactDto.getContact())) {
            contactDto.setEmailAddress(userContactDto.getContact());
            contactDto.setEmailAddressVerified(false);
            eVerificationType = EVerificationType.EMAIL;
        } else if (userContactDto.getContactType().equals(EContactType.CELLPHONE) && !contactDto.getCellphoneNumber().equalsIgnoreCase(userContactDto.getContact())) {
            contactDto.setCellphoneNumber(userContactDto.getContact());
            contactDto.setCellphoneNumberVerified(false);
            eVerificationType = EVerificationType.CELL;
        } else if (userContactDto.getContactType().equals(EContactType.FCM_TOKEN) && !contactDto.getFcmToken().equalsIgnoreCase(userContactDto.getContact())) {
            contactDto.setFcmToken(userContactDto.getContact());
        } else {
            //return if contact details have not changed
            return;
        }
        contactService.saveContact(contactDto);
        if (eVerificationType != null) {
            userVerificationService.startVerification(VerifyUserDto.builder().verificationType(eVerificationType).contact(userContactDto.getContact()).build());
        }
    }


    @Override
    public ContactDto getContact(UserContactDto userContactDto) {
        if (EContactType.CELLPHONE.equals(userContactDto.getContactType())) {
            return contactService.findByCellphone(userContactDto.getContact(), 0);
        } else if (EContactType.EMAIL.equals(userContactDto.getContactType())) {
            return contactService.findByEmailAddress(userContactDto.getContact(), 0);
        } else {
            throw new FindException("Unknown contact type");
        }
    }

    @Override
    public boolean isAvailable(UserContactDto userContactDto) {
        try {
            getContact(userContactDto);
            return false;
        } catch (FindException ignored) {
            return true;
        }
    }
}
