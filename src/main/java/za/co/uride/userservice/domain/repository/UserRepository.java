package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.uride.userservice.domain.entity.User;
import za.co.uride.userservice.enums.EUserType;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndUserTypeAndStatus(String username, EUserType userType, boolean status);

    @Query(value = "select u from User u join UserRole ur on ur.user=u where u.userType =:userType and ur.role.roleName=:role  and u.verified=:status ")
    List<User> findAll(@Param(value = "userType") EUserType userType,@Param("status") boolean status,@Param(value = "role") String role);

}
