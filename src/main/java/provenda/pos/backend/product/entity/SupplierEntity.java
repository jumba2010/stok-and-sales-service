package provenda.pos.backend.product.entity;

import lombok.*;
import provenda.pos.backend.generic.entity.LifeCycleEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents the supplier details.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "supplier")
public class SupplierEntity extends LifeCycleEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "email", nullable = false)
    private String email;
}
