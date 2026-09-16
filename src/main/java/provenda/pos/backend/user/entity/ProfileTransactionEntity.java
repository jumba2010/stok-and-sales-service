package provenda.pos.backend.user.entity;

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

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>user details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="profile_transaction")
public class ProfileTransactionEntity extends LifeCycleEntity<Long> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profile_id",insertable = false,updatable=false,nullable = false)
	private ProfileEntity profile;
	
	@Column(name="profile_id")
	private Long profileId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="transaction_id",insertable = false,updatable=false,nullable = false)
	private TransactionEntity transaction;
	
	@Column(name="transaction_id")
	private Long transactionId;
	
}
