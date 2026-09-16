package provenda.pos.backend.stock.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.stock.dto.*;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;
import provenda.pos.backend.stock.entity.InventoryEntity;
import provenda.pos.backend.stock.service.InventoryService;
import provenda.pos.backend.security.UserContext;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;


    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;

    }

    @PostMapping
    public ResponseEntity<?> startInventory(@RequestBody @Valid CreateInventoryRequest createInventoryRequest) throws
            BusinessException {

        var inventory = inventoryService.startInventory(
                UserContext.getDefaultContext(),
                CreateInventoryRequest.getInventoryInstance(createInventoryRequest)
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<?> updateInventory(@PathVariable Long inventoryId,
                                             @RequestBody @Valid InventoryEntity updatedInventory,
                                             UserContext userContext) throws ValidationException {
        InventoryEntity inventory = inventoryService.updateInventory(userContext, inventoryId, updatedInventory);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/adjust")
    public ResponseEntity<?> adjustInventory(
            @RequestBody @Valid AdjustInventoryRequest adjustInventoryRequest) throws ValidationException {

        UserContext userContext = UserContext.getDefaultContext();
        InventoryAdjustmentEntity savedAdjustment = inventoryService.adjustInventory(userContext, adjustInventoryRequest);


        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ListInventoryDTO>> listInventories(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) String status,
            Pageable pageable) {
        Page<InventoryEntity> inventories = inventoryService.listInventories(startDate, endDate, warehouseId, status, pageable);
        Page<ListInventoryDTO> response = inventories.map(ListInventoryDTO::toListInventoryDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryDetailsDTO> getInventoryDetails(@PathVariable Long inventoryId) throws ValidationException {
        InventoryDetailsDTO details = inventoryService.getInventoryDetails(inventoryId);
        return ResponseEntity.ok(details);
    }

    @GetMapping
    public List<ListInventoryDTO> filterInventories(@ModelAttribute FilterInventoryRequest filterRequest) throws ValidationException {
        return inventoryService.filterInventories(filterRequest);
    }

    @GetMapping("/{inventoryId}/report/pdf")
    public ResponseEntity<byte[]> generatePdfReport(
            @PathVariable Long inventoryId,
            @RequestParam(required = false, defaultValue = "en") String lang) throws Exception {

        byte[] pdfBytes = inventoryService.generatePdfReport(inventoryId, lang);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "inventory-report-" + inventoryId + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @GetMapping("/{inventoryId}/report/excel")
    public ResponseEntity<byte[]> generateExcelReport(
            @PathVariable Long inventoryId,
            @RequestParam(required = false, defaultValue = "en") String lang) throws Exception {

        byte[] excelBytes = inventoryService.generateExcelReport(inventoryId, lang);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "inventory-report-" + inventoryId + ".xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);


    }

}





