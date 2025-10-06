package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity {

    @JoinColumn(name = "role_id")
    @ManyToOne()
    private Role role;

    @JoinColumn(name = "user_id")
    @ManyToOne()
    private User user;
}
