package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table()
@Entity
@Builder
public class UserAvatar extends AuditBaseEntity {
    @ManyToOne()
    private User user;
    private String avatar;
    private String fileKey;

}
