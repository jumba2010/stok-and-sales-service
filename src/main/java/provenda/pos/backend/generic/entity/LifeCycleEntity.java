package provenda.pos.backend.generic.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class LifeCycleEntity<T>  implements ILifeCycleEntity<T> {
	
	@Column(name="id",nullable=false,precision = 38,scale=0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private T id;

	@Column(name="created_by",nullable=false)
	private Long createdBy;
	
	@Column(name="updated_by")
	private Long updatedBy;
	
	@Column(name="activated_by",nullable=false)
	private Long activatedBy;
	
	@Column(name="state",nullable=false)
	private int state;
	
	@Column(name="active",nullable=false)
	private boolean active;
	
	@Column(name="created_at",nullable=false)
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name="activated_at",nullable=false)
	private LocalDateTime activatedAt;
	
	@Column(name="sucursal_id")
	private Long sucursalId;

	@PrePersist
	void createdAt() {
		this.createdAt=LocalDateTime.now();
		this.activatedAt=LocalDateTime.now();
	}
}
