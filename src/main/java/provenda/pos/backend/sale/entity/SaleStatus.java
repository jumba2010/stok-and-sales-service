package provenda.pos.backend.sale.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SaleStatus {
MADE("MADE"),REFUNDED("REFUNDED"),CANCELED("CANCELED");
	
private String  status;
	
}
