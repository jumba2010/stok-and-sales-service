package provenda.pos.backend.product.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.user.entity.SucursalEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>user details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="tax")
public class TaxEntity extends LifeCycleEntity<Long> {

	@Column(name="type")
	private String type;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="start_date", nullable = false)
	private Timestamp startDate;
	
	@Column(name="end_date")
	private Timestamp endDate;
	
	@Column(name="value", nullable = false)
	private double value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sucursal_id",insertable = false,updatable=false,nullable = false)
	private SucursalEntity sucursal;
	
	@Column(name="sucursal_id")
	private Long sucursalId;
	
}
