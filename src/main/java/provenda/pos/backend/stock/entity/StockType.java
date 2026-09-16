package provenda.pos.backend.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StockType {
ENTRANCE("ENTRANCE"),EXIT("EXIT");
	
private String type;
}
