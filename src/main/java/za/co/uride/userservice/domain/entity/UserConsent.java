package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EConsentType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table()
@Entity
@Builder
public class UserConsent extends BaseEntity {
    private EConsentType consentType;
    @ManyToOne()
    private User user;
    private boolean consentGranted;
    private LocalDateTime effFrom;
    private LocalDateTime effTo;
}
