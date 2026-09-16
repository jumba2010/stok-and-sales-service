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
@Table(name="login_info")
public class LoginInfoEntity extends LifeCycleEntity<Long> {

	@Column(name="duration")
	private int duration;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="device", nullable = false)
	private String device;
	
	@Column(name="device_tyme", nullable = false)
	private String deviceType;
	
	@Column(name="location")
	private String location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",insertable = false,updatable=false,nullable = false)
	private UserEntity user;
	
	@Column(name="user_id")
	private Long userId;
	
}
