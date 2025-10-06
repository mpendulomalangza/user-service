package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.UserContactDto;

public interface UserContactService {
    void updateContact(UserContactDto userContactDto);

    ContactDto getContact(UserContactDto userContactDto);

    boolean isAvailable(UserContactDto userContactDto);
}
