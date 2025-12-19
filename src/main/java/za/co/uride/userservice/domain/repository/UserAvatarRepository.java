package za.co.uride.userservice.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import za.co.uride.userservice.domain.entity.UserAvatar;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserAvatarRepository extends CrudRepository<UserAvatar, Long> {
    @Query(value = "select ua from UserAvatar ua where ua.user.id =:userId and :effectiveDate between ua.effFrom and ua.effTo order by  ua.id desc")
    Optional<UserAvatar> findByUserId(@Param(value = "userId") long userId, @Param(value = "effectiveDate") LocalDateTime effectiveDate);

    @Query(value = "select ua from UserAvatar ua where ua.user.id in (:users) and :effectiveDate between ua.effFrom and ua.effTo order by  ua.id desc")
    List<UserAvatar> findAll(@Param(value = "users") List<Long> users, @Param(value = "effectiveDate") LocalDateTime effectiveDate);
}
