package provenda.pos.backend.product.entity;

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
 *<p>This class holds all possible <b>product unities (Ex. Kg, M,box,etc) <b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Table(name="unit")
public class UnitEntity extends LifeCycleEntity<Long> {

	@Column(name="code", nullable = false)
	private String code;

	@Column(name="description",nullable = false)
	private String description;

	@Column(name = "lang", nullable = false)
	private String lang;

}
