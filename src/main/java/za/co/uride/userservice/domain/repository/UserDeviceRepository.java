package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.domain.entity.UserDevice;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
    Optional<UserDevice> findByUserAndStatus(User user, boolean status);
}
