package provenda.pos.backend.stock.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dto.StockEntryResponse;
import provenda.pos.backend.stock.dto.StockHistoryFilterRequest;
import provenda.pos.backend.stock.dto.StockHistoryResponse;
import provenda.pos.backend.stock.entity.StockEntry;

import java.util.List;

public interface StockEntryService {
    StockEntry createStockEntry(UserContext userContext, StockEntry stockEntry) throws BusinessException;

    StockEntryResponse getStockEntryDetails(Long stockEntryId) throws ValidationException;

    List<StockHistoryResponse> getStockHistory(StockHistoryFilterRequest request);
}