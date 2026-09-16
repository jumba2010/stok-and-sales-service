package provenda.pos.backend.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.generic.service.AbstractServiceImpl;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.stock.dao.StockEntryRepository;
import provenda.pos.backend.stock.dto.ListInventoryDTO;
import provenda.pos.backend.stock.dto.StockEntryResponse;
import provenda.pos.backend.stock.dto.StockHistoryFilterRequest;
import provenda.pos.backend.stock.dto.StockHistoryResponse;
import provenda.pos.backend.stock.entity.StockEntry;
import provenda.pos.backend.security.UserContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static provenda.pos.backend.stock.service.InventoryServiceImpl.LAST_HOUR;
import static provenda.pos.backend.stock.service.InventoryServiceImpl.LAST_MINUTE;


@Service
public class StockEntryServiceImpl extends AbstractServiceImpl<StockEntry, Long> implements StockEntryService {
    private final StockEntryRepository stockEntryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public StockEntryServiceImpl(StockEntryRepository stockEntryRepository, ProductRepository productRepository) {
        super(stockEntryRepository);
        this.stockEntryRepository = stockEntryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public StockEntry createStockEntry(UserContext userContext, StockEntry stockEntry) throws BusinessException {
        var product = productRepository.findById(stockEntry.getProductId())
                        .orElseThrow(()-> new BusinessException("product.not.found", "Product not found"));
        stockEntry.setProduct(product);

        this.create(userContext, stockEntry);
        return stockEntry;
    }


    @Override
    public StockEntryResponse getStockEntryDetails(Long stockEntryId) throws ValidationException {
        StockEntry stockEntry = stockEntryRepository.findById(stockEntryId)
                .orElseThrow(() -> new ValidationException("stock.entry.not.found", "Stock entry not found"));

        return StockEntryResponse.builder()
                .id(stockEntry.getId())
                .receivedDate(stockEntry.getReceivedDate().toString())
                .reference(stockEntry.getReference())
                .warehouseId(stockEntry.getWarehouseId())
                .supplierId(stockEntry.getSupplierId())
                .productId(stockEntry.getProduct().getId())
                .build();
    }


    @Override
    public List<StockHistoryResponse> getStockHistory(StockHistoryFilterRequest filterRequest) {
        LocalDate startDate = LocalDate.parse(filterRequest.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(filterRequest.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<StockEntry> stockEntries = stockEntryRepository.findStockHistory(
                filterRequest.getWarehouseId(),
                filterRequest.getProductId(),
                filterRequest.getSupplierId(),
                startDate.atStartOfDay(),
                endDate.atTime(LAST_HOUR, LAST_MINUTE)
        );

        return stockEntries.stream()
                .map(stockEntry -> StockHistoryResponse.builder()
                        .productId(stockEntry.getProductId())
                        .productName(stockEntry.getProduct().getName())
                        //.lot(entry.getReference())
                        .receivedQuantity(stockEntry.getReceivedQuantity())
                        //.purchasePrice(entry.getPurchasePrice())
                        .supplierId(stockEntry.getSupplierId())
                        .receivedDate(stockEntry.getReceivedDate().toString())
                        .notes(stockEntry.getNotes())
                        .build())
                .collect(Collectors.toList());
    }


}




