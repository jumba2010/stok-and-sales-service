package provenda.pos.backend.sale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import provenda.pos.backend.generic.entity.LifeCycleEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>client details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="client")
public class ClientEntity extends LifeCycleEntity<Long> {

	@Column(name="picture")
	private String picture;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="contact", nullable = false)
	private String contact;
	
	@Column(name="email")
	private String email;
}
