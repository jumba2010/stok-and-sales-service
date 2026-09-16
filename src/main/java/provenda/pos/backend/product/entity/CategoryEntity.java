package provenda.pos.backend.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.SuperBuilder;
import provenda.pos.backend.generic.entity.LifeCycleEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>product category and sub categories<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Table(name = "category")
@AllArgsConstructor
public class CategoryEntity extends LifeCycleEntity<Long> {

	@Column(name = "image_url")
	private String imageURL;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "lang", nullable = false)
	private String lang;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = true)
	private CategoryEntity parent;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private Long parentId;
}
