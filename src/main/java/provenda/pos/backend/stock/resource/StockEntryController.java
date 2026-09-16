package provenda.pos.backend.stock.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dto.CreateStockEntryRequest;
import provenda.pos.backend.stock.dto.StockEntryResponse;
import provenda.pos.backend.stock.dto.StockHistoryFilterRequest;
import provenda.pos.backend.stock.dto.StockHistoryResponse;
import provenda.pos.backend.stock.service.StockEntryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockEntryController {
    private final StockEntryService stockEntryService;

    @Autowired
    public StockEntryController(StockEntryService stockEntryService) {
        this.stockEntryService = stockEntryService;
    }

    @PostMapping
    public ResponseEntity<?> createStockEntry(@RequestBody @Valid CreateStockEntryRequest request) throws BusinessException {
        var stockEntry = stockEntryService.createStockEntry(
                UserContext.getDefaultContext(),
                CreateStockEntryRequest.getStockEntryInstance(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockEntryResponse> getStockEntryDetails(@PathVariable Long id) throws ValidationException {
        StockEntryResponse response = stockEntryService.getStockEntryDetails(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public List<StockHistoryResponse> getStockHistory(
            @ModelAttribute StockHistoryFilterRequest filterRequest) {
        return stockEntryService.getStockHistory(filterRequest);
    }
}

