package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.uride.userservice.domain.entity.OTP;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.enums.EVerificationType;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByUserAndExpiryDateIsAfterAndStatus(User user, LocalDateTime date, boolean status);

    Optional<OTP> findByUserAndStatus(User user, boolean status);

    Optional<OTP> findByUserAndExpiryDateIsAfterAndOtpAndStatus(User user, LocalDateTime date, String otp, boolean status);

    @Query(value = "select otp from OTP otp where otp.user =:user and otp.expiryDate>:effective and otp.type=:verificationType and  otp.otp=:optValue  and otp.status=:status")
    Optional<OTP> find(@Param("user") User user, @Param("effective") LocalDateTime date, @Param("optValue") String otp, @Param("verificationType") EVerificationType verificationType, @Param("status") boolean status);
}
