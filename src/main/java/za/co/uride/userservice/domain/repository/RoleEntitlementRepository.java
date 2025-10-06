package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.Entitlement;
import za.co.uride.userservice.domain.entity.Role;
import za.co.uride.userservice.domain.entity.RoleEntitlement;

import java.util.Optional;

public interface RoleEntitlementRepository extends JpaRepository<RoleEntitlement,Long> {

    Optional<RoleEntitlement> findByRoleAndEntitlementAndStatus(Role role, Entitlement  entitlement, boolean  status);
}
