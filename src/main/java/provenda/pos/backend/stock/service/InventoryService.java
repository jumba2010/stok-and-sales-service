package provenda.pos.backend.stock.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.stock.dto.AdjustInventoryRequest;
import provenda.pos.backend.stock.dto.FilterInventoryRequest;
import provenda.pos.backend.stock.dto.InventoryDetailsDTO;
import provenda.pos.backend.stock.dto.ListInventoryDTO;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;
import provenda.pos.backend.stock.entity.InventoryEntity;
import provenda.pos.backend.security.UserContext;

import java.util.List;

public interface InventoryService {

    InventoryEntity startInventory(UserContext userContext, InventoryEntity inventoryEntity);

    InventoryEntity updateInventory(UserContext userContext, Long inventoryId, InventoryEntity updatedInventory) throws ValidationException;

    InventoryAdjustmentEntity adjustInventory(UserContext userContext, AdjustInventoryRequest request) throws ValidationException;

    Page<InventoryEntity> listInventories(String startDate, String endDate, Long warehouseId, String status, Pageable pageable);

    InventoryDetailsDTO getInventoryDetails(Long inventoryId) throws ValidationException;

    List<ListInventoryDTO> filterInventories(FilterInventoryRequest filterRequest);

    byte[] generatePdfReport(Long inventoryId, String lang) throws Exception;

    byte[] generateExcelReport(Long inventoryId, String lang) throws Exception;

}
