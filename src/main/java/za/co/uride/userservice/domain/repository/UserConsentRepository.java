package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.UserConsent;

public interface UserConsentRepository extends JpaRepository<UserConsent, Long> {
}
