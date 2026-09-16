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
 *<p>This class holds all possible <b>profiles <b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Table(name="transaction")
public class TransactionEntity extends LifeCycleEntity<Long> {

	@Column(name="code", nullable = false)
	private String code;
	
	@Column(name="description",nullable = false)
	private String description;
	
}
