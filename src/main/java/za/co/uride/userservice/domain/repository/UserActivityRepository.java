package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserActivity;
import za.co.uride.userservice.enums.EActivityType;

import java.time.LocalDateTime;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    long countByActivityTypeAndUserAndLastModifiedOnIsBetween(EActivityType EActivityType, User user, LocalDateTime start, LocalDateTime end);
}
