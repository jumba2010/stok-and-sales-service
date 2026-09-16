package provenda.pos.backend.stock.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Judiao Mbaua
 *
 *Wrapper class to carry all the information need to update the stock
 */
@NoArgsConstructor
@Data
public class ProductStock {
	private Long productId;
	
	private BigDecimal sellPrice;
	
	private BigDecimal purchasePrice;
	
	private int quantity;
	
	private String stockType;
}
