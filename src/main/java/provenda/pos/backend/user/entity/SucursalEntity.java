package provenda.pos.backend.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import provenda.pos.backend.generic.entity.LifeCycleEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>sucursal details <b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Table(name="sucursal")
public class SucursalEntity extends LifeCycleEntity<Long> {

	@Column(name="code", nullable = false)
	private String code;
	
	@Column(name="name",nullable = false)
	private String name;

	@Column(name = "taxId")
	private String taxId;

	@Column(name = "address")
	private String address;

	@Column(name = "contact")
	private String contact;
}
