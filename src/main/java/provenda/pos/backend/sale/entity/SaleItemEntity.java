package provenda.pos.backend.sale.entity;

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
import provenda.pos.backend.product.entity.ProductEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>client details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="sale_item")
public class SaleItemEntity extends LifeCycleEntity<Long> {

	@Column(name="sale_id",nullable = false)
	private Long saleId;
	
	@Column(name="quantity",nullable = false)
	private int quantity;
	
	@Column(name="price",nullable = false)
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sale_id",insertable = false,updatable=false,nullable = false)
	private SaleEntity sale;
	
	@Column(name="product_id")
	private Long productId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id",insertable = false,updatable=false,nullable = false)
	private ProductEntity product;

}
