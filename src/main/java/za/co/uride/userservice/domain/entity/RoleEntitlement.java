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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "role_entitlement")
public class RoleEntitlement extends BaseEntity {

    @JoinColumn(name = "role_id")
    @ManyToOne()
    private Role role;

    @JoinColumn(name = "entitlement_id")
    @ManyToOne()
    private Entitlement entitlement;
}
