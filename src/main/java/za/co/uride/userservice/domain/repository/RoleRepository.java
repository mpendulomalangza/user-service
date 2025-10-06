package za.co.uride.userservice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.Role;
import za.co.uride.userservice.enums.ESystem;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleNameAndSystemAndStatus(String roleName, ESystem system, boolean status);

    Page<Role> findAllByRoleNameContainsAndSystemAndStatus(String roleName, ESystem system, boolean status, Pageable pageable);
}
