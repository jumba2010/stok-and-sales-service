package provenda.pos.backend.sale.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.user.entity.SucursalEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>client details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="promotion")
public class PromotionEntity extends LifeCycleEntity<Long> {

	@Column(name="description")
	private String description;
	
	@Column(name="percentage", nullable = false)
	private int percentage;
	
	@Column(name="start_date", nullable = false)
	private Timestamp startDate;
	
	@Column(name="end_date")
	private Timestamp endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sucursal_id",insertable = false,updatable=false,nullable = false)
	private SucursalEntity sucursal;
	
	@Column(name="sucursal_id")
	private Long sucursalId;
	
	@OneToMany(mappedBy = "promotion",targetEntity = ProductEntity.class)
	private Set<ProductEntity> products;
}
