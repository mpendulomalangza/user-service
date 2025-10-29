package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EContactType;

public interface ContactService {

    void saveContact(ContactDto contactDto);

    void deleteContact(long id);

    ContactDto findByCellphone(String cellphone, long contactId);

    ContactDto findByEmailAddress(String emailAddress, long contactId);

    void verifyContact(EContactType eContactType, long id);

    ContactDto findById(long contactId);


    ContactDto findByUser(UserDto userDto);


}
