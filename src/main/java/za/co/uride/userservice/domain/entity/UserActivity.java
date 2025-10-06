package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.EActivityType;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "user_activity")
@Entity
public class UserActivity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private EActivityType activityType;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
