package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "user_device")
@Entity
public class UserDevice extends BaseEntity {
    @Column(name = "device_key")
    private String deviceKey;
    @Column(name = "device_name")
    private String deviceName;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
