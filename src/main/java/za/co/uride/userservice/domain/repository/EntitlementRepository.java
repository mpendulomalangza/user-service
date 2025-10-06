package za.co.uride.userservice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.Entitlement;

import java.util.Optional;

public interface EntitlementRepository extends JpaRepository<Entitlement, Long> {
    Optional<Entitlement> findByNameAndStatus(String name, boolean status);

    Page<Entitlement> findByNameLikeAndStatus(String name, boolean status, Pageable pageable);
}
