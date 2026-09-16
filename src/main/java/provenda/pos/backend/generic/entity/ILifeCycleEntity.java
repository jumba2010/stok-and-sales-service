package provenda.pos.backend.generic.entity;

import java.time.LocalDateTime;

public interface ILifeCycleEntity<T> {
	
	T getId() ;

	void setId(T id) ;

	Long getCreatedBy();

	void setCreatedBy(Long createdBy) ;

	Long getUpdatedBy() ;

	void setUpdatedBy(Long updatedBy);

	Long getActivatedBy() ;
	
	void setActivatedBy(Long activatedBy) ;
	
	int getState() ;

	void setState(int state);

	boolean isActive() ;

	void setActive(boolean active) ;

	LocalDateTime getCreatedAt() ;

	void setCreatedAt(LocalDateTime createdAt) ;

	LocalDateTime getUpdatedAt();

	void setUpdatedAt(LocalDateTime updatedAt) ;

	LocalDateTime getActivatedAt() ;

	void setActivatedAt(LocalDateTime activatedAt) ;

}
