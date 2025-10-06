package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EVerificationType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "otp")
@Entity
@Builder
public class OTP extends BaseEntity {
    private String otp;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    private EVerificationType type;
}