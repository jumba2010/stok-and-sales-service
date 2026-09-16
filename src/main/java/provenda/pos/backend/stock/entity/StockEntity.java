package provenda.pos.backend.stock.entity;

import java.math.BigDecimal;

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
import provenda.pos.backend.user.entity.SucursalEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>stock details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="stock")
public class StockEntity extends LifeCycleEntity<Long> {

	@Column(name="available_quantity")
	private int availableQuanity;
	
	@Column(name="quanity", nullable = false)
	private int quantity;
	
	@Column(name="stock_type", nullable = false)
	private String stockType;
	
	@Column(name="purchase_price")
	private BigDecimal purchasePrice;
	
	@Column(name="sell_price")
	private BigDecimal sellPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sucursal_id",insertable = false,updatable=false,nullable = false)
	private SucursalEntity sucursal;
	
	@Column(name="sucursal_id")
	private Long sucursalId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id",insertable = false,updatable=false,nullable = false)
	private ProductEntity product;
	
	@Column(name="product_id")
	private Long productId;
}
