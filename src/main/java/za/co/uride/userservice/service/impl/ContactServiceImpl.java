package za.co.uride.userservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.domain.entity.Contact;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.repository.ContactRepository;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.exception.FindException;
import za.co.uride.userservice.service.ContactService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ModelMapper modelMapper;

    /**
     * @param contactDto contact DTO
     */
    @CacheEvict(cacheNames = "defaultCache", key = "#contactDto.toString()")
    @Override
    public void saveContact(ContactDto contactDto) {
        Contact contact = modelMapper.map(contactDto, Contact.class);
        if (contactDto.getId() != null && contactDto.getId() > 0) {
            Optional<Contact> optionalContact = repository.findById(contactDto.getId());
            if (optionalContact.isPresent()) {
                optionalContact.get().setEffTo(LocalDateTime.now().minusSeconds(1));
                repository.save(optionalContact.get());
            }
            contact.setId(null);
        }
        repository.save(contact);
    }

    @Override
    public void deleteContact(long id) {
        repository.deleteById(id);
    }

    /**
     * @param emailAddress email address
     * @param contactId    contact id
     * @return returns contact by email address or throw findException
     */
    @Override
    public ContactDto findByEmailAddress(String emailAddress, long contactId) {
        Optional<Contact> optional = repository.findByEmailAddressAndIdIsNot(emailAddress, contactId);
        return mapToDto(optional, "emailAddress", emailAddress);
    }

    /***
     *
     * @param contactType contact type
     * @param id contact id
     */
    @Override
    public void verifyContact(EContactType contactType, long id) {
        repository.findById(id).ifPresent(contact -> {
            if (contactType.equals(EContactType.EMAIL)) {
                contact.setEmailAddressVerified(true);
            } else if (contactType.equals(EContactType.CELLPHONE)) {
                contact.setCellphoneNumberVerified(true);
            }
            repository.save(contact);
        });

    }

    /**
     *
     * @param contactId contact id
     * @return {@link ContactDto}
     */
    @Override
    public ContactDto findById(long contactId) {
        return mapToDto(repository.findById(contactId), "id", String.valueOf(contactId));
    }

    @Override
    public ContactDto findByUser(UserDto userDto) {
        return mapToDto(repository.findByUser(modelMapper.map(userDto, User.class), LocalDateTime.now()), "user", String.valueOf(userDto.getId()));
    }


    /**
     * @param cellphone cellphone
     * @param contactId contact id
     * @return returns contact by cellphone or throw findException
     */
    @Override
    public ContactDto findByCellphone(String cellphone, long contactId) {
        Optional<Contact> optional = repository.findByCellphoneNumberAndIdIsNot(cellphone, contactId);
        return mapToDto(optional, "cellphone", cellphone);
    }

    /**
     * @param optional optional of  {@link Contact}
     * @param name     contact search field name
     * @param value    contact search  value
     * @return {@link ContactDto}
     */
    private ContactDto mapToDto(Optional<Contact> optional, String name, String value) {
        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), ContactDto.class);
        }
        throw new FindException(String.format("%s [%s] not found", name, value));
    }

}
