package provenda.pos.backend;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.function.Predicate;


@SpringBootApplication
public class StockAndSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAndSaleApplication.class, args);
	}

}
