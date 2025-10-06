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
import za.co.uride.userservice.enums.EUserType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "user")
@Entity
@Builder
public class User extends BaseEntity {
    private String name;
    private String username;
    @Column(name = "verified_flag")
    private boolean verified;
    private boolean acceptTermsAndConditions;
    private boolean locked;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoleList;
    private EUserType userType;
}
