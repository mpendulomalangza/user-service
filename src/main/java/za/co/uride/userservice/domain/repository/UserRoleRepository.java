package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.Role;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleAndUserAndStatus(Role role, User user, boolean status);

    List<UserRole> findAllByUserAndStatus(User user, boolean status);
}
