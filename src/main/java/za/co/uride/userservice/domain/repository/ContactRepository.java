package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.uride.userservice.domain.entity.Contact;
import za.co.uride.userservice.domain.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByCellphoneNumberAndIdIsNot(String cellphone, long id);

    Optional<Contact> findByEmailAddressAndIdIsNot(String emailAddress, long id);

    @Query(value = "select cnt from  Contact cnt where cnt.user =:user and :effectiveDate between cnt.effFrom and cnt.effTo order by cnt.effFrom desc limit 1 ")
    Optional<Contact> findByUser(@Param("user") User user, @Param("effectiveDate") LocalDateTime effectiveDate);
}
