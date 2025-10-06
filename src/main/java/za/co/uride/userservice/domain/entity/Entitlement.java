package za.co.uride.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity()
@Table(name = "entitlement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Entitlement extends BaseEntity {
   private String name;
}
