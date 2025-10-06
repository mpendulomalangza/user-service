package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.uride.userservice.enums.ESystem;

import java.util.List;

@Entity()
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Role extends BaseEntity {
    @Column(name = "system_name")
    private ESystem system;
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<RoleEntitlement> entitlements;

    @Builder
    public Role(final ESystem system, final String roleName, final List<RoleEntitlement> entitlements, final Long id, final boolean status) {
        super.setId(id);
        super.setStatus(status);
        this.entitlements = entitlements;
        this.system = system;
        this.roleName = roleName;
    }

}
