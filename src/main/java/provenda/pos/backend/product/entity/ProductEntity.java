package provenda.pos.backend.product.entity;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.*;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.sale.entity.PromotionEntity;
import provenda.pos.backend.user.entity.SucursalEntity;

/**
 * @author Judiao Mbaua
 *
 *<p>This class holds the <b>product details<b/></p>
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "product")
@Builder
@AllArgsConstructor
public class ProductEntity extends LifeCycleEntity<Long> {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "can_be_sold", nullable = false)
	private boolean canBeSold;

	@Column(name = "alert_quantity")
	private int alertQuantity;

	@Column(name = "available_quantity", nullable = false)
	private int availableQuantity;

	@Column(name = "package_count", nullable = false)
	private int packageCount;

	@Column(name = "purchase_price", nullable = false)
	private BigDecimal purchasePrice;

	@Column(name = "sale_price", nullable = false)
	private BigDecimal salePrice;

	@Column(name = "current_price", nullable = false)
	private BigDecimal currentPrice;

	@Column(name = "promotional_price")
	private double promotionalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sucursal_id", insertable = false, updatable = false, nullable = false)
	private SucursalEntity sucursal;

	@Column(name = "sucursal_id")
	private Long sucursalId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, updatable = false, nullable = false)
	private UnitEntity unit;

	@Column(name = "unit_id")
	private Long unitId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = false, updatable = false, nullable = false)
	private CategoryEntity category;

	@Column(name = "category_id")
	private Long categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", insertable = false, updatable = false)
	private SupplierEntity supplier;

	@Column(name = "supplier_id")
	private Long supplierId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion_id", insertable = false, updatable = false)
	private PromotionEntity promotion;

	@Column(name = "promotion_id")
	private Long promotionId;
}
