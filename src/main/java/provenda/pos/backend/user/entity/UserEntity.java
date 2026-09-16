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
@Table(name="user")
public class UserEntity extends LifeCycleEntity<Long> {

	@Column(name="picture")
	private String picture;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="contact", nullable = false)
	private String contact;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profile_id",insertable = false,updatable=false,nullable = false)
	private ProfileEntity profile;
	
	@Column(name="profile_id")
	private Long profileId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sucursal_id",insertable = false,updatable=false,nullable = false)
	private SucursalEntity sucursal;
	
	@Column(name="sucursal_id")
	private Long sucursalId;
	
}
