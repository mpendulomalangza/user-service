package za.co.uride.userservice.service;

import za.co.uride.userservice.dto.ContactDetailsDto;
import za.co.uride.userservice.dto.FindContactDto;

public interface ContactDetailService {

    ContactDetailsDto find(FindContactDto contactDto);

}