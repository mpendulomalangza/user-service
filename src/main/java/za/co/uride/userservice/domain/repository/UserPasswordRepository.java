package za.co.uride.userservice.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserPassword;

import java.util.Optional;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    Optional<UserPassword> findByUserAndPasswordAndStatus(User user, String password, boolean status);
}
