package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "contact")
public class Contact extends AuditBaseEntity {

    @Column(nullable = false, length = 15)
    private String cellphoneNumber;

    @Column(nullable = false, length = 100)
    private String emailAddress;

    @Column(nullable = false, length = 200)
    private String fcmToken;

    @Column(name = "email_address_verified", nullable = false)
    private boolean emailAddressVerified;

    @Column(name = "cellphone_number_verified", nullable = false)
    private boolean cellphoneNumberVerified;

    @ManyToOne()
    private User user;
}