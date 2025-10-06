package za.co.uride.userservice.domain.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.Month;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime createdOn;
    @LastModifiedBy
    private String modifiedBy;
    @CreatedBy
    private String createdBy;
    private LocalDateTime modifiedOn;
    private LocalDateTime effFrom;
    private LocalDateTime effTo;

    @PreUpdate
    public void preUpdate() {
        this.setModifiedOn(LocalDateTime.now());
    }

    @PreDestroy
    public void preDestroy() {
        this.setModifiedOn(LocalDateTime.now());
    }

    @PrePersist
    public void prePersist() {
        this.setCreatedOn(LocalDateTime.now());
        this.setEffTo(LocalDateTime.of(9999, Month.DECEMBER, 31, 23, 59));
        this.setEffFrom(LocalDateTime.now());
    }


}