package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "user_password")
@Entity
@Builder
public class UserPassword extends BaseEntity {
    private String password;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
