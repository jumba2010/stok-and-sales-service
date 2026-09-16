package provenda.pos.backend.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.generic.service.AbstractServiceImpl;
import provenda.pos.backend.generic.service.ExcelExportService;
import provenda.pos.backend.product.dao.InventoryAdjustmentRepository;
import provenda.pos.backend.product.dao.InventoryRepository;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.dto.ProductAdjustmentDTO;
import provenda.pos.backend.stock.dto.AdjustInventoryRequest;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;
import provenda.pos.backend.stock.entity.InventoryEntity;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dto.FilterInventoryRequest;
import provenda.pos.backend.stock.dto.InventoryDetailsDTO;
import provenda.pos.backend.stock.dto.ListInventoryDTO;
import provenda.pos.backend.user.dto.SucursalDetailsDTO;
import provenda.pos.backend.user.entity.SucursalEntity;
import provenda.pos.backend.utils.BusinessConstants;
import provenda.pos.backend.utils.PdfGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class InventoryServiceImpl extends AbstractServiceImpl<InventoryEntity, Long> implements InventoryService {
    public static final int LAST_MINUTE = 59;
    public static final int LAST_HOUR = 23;
    private final InventoryRepository inventoryRepository;
    private final InventoryAdjustmentRepository inventoryAdjustmentRepository;
    private final ProductRepository productRepository;
    private final PdfGenerator pdfGenerator;
    private final TemplateEngine templateEngine;
    private final ExcelExportService excelExportService;
    private final MessageSource messageSource;


    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryAdjustmentRepository inventoryAdjustmentRepository, ProductRepository productRepository, PdfGenerator pdfGenerator, TemplateEngine templateEngine, ExcelExportService excelExportService, MessageSource messageSource) {
        super(inventoryRepository);
        this.inventoryRepository = inventoryRepository;
        this.inventoryAdjustmentRepository = inventoryAdjustmentRepository;
        this.productRepository = productRepository;
        this.pdfGenerator = pdfGenerator;
        this.templateEngine = templateEngine;
        this.excelExportService = excelExportService;
        this.messageSource = messageSource;
    }

    @Override
    public InventoryEntity startInventory(UserContext userContext, InventoryEntity inventory) {
        this.create(userContext, inventory);
        return inventory;
    }

    @Override
    public InventoryEntity updateInventory(UserContext userContext, Long inventoryId, InventoryEntity updatedInventory) throws ValidationException {
        InventoryEntity existingInventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(ValidationException::new);


        existingInventory.setWarehouseId(updatedInventory.getWarehouseId());
        existingInventory.setStatus(updatedInventory.getStatus());
        existingInventory.setState(updatedInventory.getState());
        existingInventory.setActive(updatedInventory.isActive());
        existingInventory.setUpdatedBy(userContext.getId());
        existingInventory.setUpdatedAt(LocalDateTime.now());
        existingInventory.setSucursalId(updatedInventory.getSucursalId());
        existingInventory.setSucursal(updatedInventory.getSucursal());

        return inventoryRepository.save(existingInventory);
    }

    @Override
    public InventoryAdjustmentEntity adjustInventory(UserContext userContext, AdjustInventoryRequest request) throws ValidationException {

        InventoryEntity inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new ValidationException(BusinessConstants.INVENTORY_NOT_FOUND, "Inventory not found"));


        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ValidationException(BusinessConstants.PRODUCT_NOT_FOUND, "Product not found"));

        int systemQuantity = product.getAvailableQuantity();
        int discrepancy = request.getPhysicalQuantity() - systemQuantity;


        product.setAvailableQuantity(request.getPhysicalQuantity());
        productRepository.save(product);


        InventoryAdjustmentEntity adjustment = InventoryAdjustmentEntity.builder()
                .inventory(inventory)
                .product(product)
                .systemQuantity(systemQuantity)
                .physicalQuantity(request.getPhysicalQuantity())
                .discrepancy(discrepancy)
                .reason(request.getReason())
                .createdBy(userContext.getId())
                .activatedBy(userContext.getId())
                .active(LifeCyCleState.ACTIVE.isActive())
                .state(LifeCyCleState.ACTIVE.getState())
                .sucursalId(userContext.getSucursalId())
                .build();


        return inventoryAdjustmentRepository.save(adjustment);
    }

    @Override
    public Page<InventoryEntity> listInventories(String startDate, String endDate, Long warehouseId, String status, Pageable pageable) {
        LocalDateTime start = startDate != null ? LocalDateTime.parse(startDate) : null;
        LocalDateTime end = endDate != null ? LocalDateTime.parse(endDate) : null;
        Integer state = status != null ? LifeCyCleState.valueOf(status.toUpperCase()).getState() : null;

        return inventoryRepository.findInventories(start, end, warehouseId, state, pageable);
    }

    @Override
    public InventoryDetailsDTO getInventoryDetails(Long inventoryId) throws ValidationException {
        InventoryEntity inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ValidationException(BusinessConstants.INVENTORY_NOT_FOUND, "Inventory not found"));

        List<InventoryAdjustmentEntity> adjustments = inventoryAdjustmentRepository.findByInventoryId(inventoryId);

        List<ProductAdjustmentDTO> products = adjustments.stream()
                .map(ProductAdjustmentDTO::toProductAdjustmentDTO)
                .collect(Collectors.toList());

        return InventoryDetailsDTO.builder()
                .inventoryId(inventory.getId())
                .date(inventory.getCreatedAt().toString())
                .warehouseId(inventory.getWarehouseId())
                .createdById(inventory.getCreatedBy())
                .products(products)
                .build();
    }

    @Override
    public List<ListInventoryDTO> filterInventories(FilterInventoryRequest filterRequest) {
        LocalDate startDate = LocalDate.parse(filterRequest.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(filterRequest.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<InventoryEntity> inventories = inventoryRepository.findByFilter(
                startDate.atStartOfDay(),
                endDate.atTime(LAST_HOUR, LAST_MINUTE),
                filterRequest.getWarehouseId(),
                LifeCyCleState.ACTIVE.getState()


        );

        return inventories.stream()
                .map(inventory -> ListInventoryDTO.builder()
                        .inventoryId(inventory.getId())
                        .date(inventory.getCreatedAt().toString())
                        .warehouse(inventory.getWarehouseId())
                        .totalProducts(inventory.getAdjustments() != null ? inventory.getAdjustments().size() : 0)
                        .status(LifeCyCleState.ACTIVE.getState() == inventory.getState() ? "FINISHED" : "PENDING")
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public byte[] generatePdfReport(Long inventoryId, String lang) throws Exception {
        InventoryEntity inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ValidationException());


        List<InventoryAdjustmentEntity> adjustments = inventoryAdjustmentRepository.findByInventoryId(inventoryId);

        InventoryDetailsDTO details = InventoryDetailsDTO.builder()
                .inventoryId(inventory.getId())
                .date(inventory.getCreatedAt().toString())
                .warehouseId(inventory.getWarehouseId())
                .createdById(inventory.getCreatedBy())
                .products(adjustments.stream()
                        .map(ProductAdjustmentDTO::toProductAdjustmentDTO)
                        .collect(Collectors.toList()))
                .build();

        SucursalEntity sucursalEntity = inventory.getSucursal();
        if (sucursalEntity == null) {
            throw new ValidationException();
        }

        SucursalDetailsDTO sucursalDetails = SucursalDetailsDTO.getSucursalDetails(sucursalEntity);

        Context context = new Context();
        context.setVariable("branchName", sucursalDetails.getName());
        context.setVariable("nuit", sucursalDetails.getNuit());
        context.setVariable("address", sucursalDetails.getAddress());
        context.setVariable("contact", sucursalDetails.getContact());
        context.setVariable("inventoryDetails", details);
        context.setLocale(new Locale(lang));

        String htmlContent = templateEngine.process("inventory-report", context);
        return pdfGenerator.generatePdfFromHtml(htmlContent);
    }

    @Override
    public byte[] generateExcelReport(Long inventoryId, String lang) throws Exception {
        Locale locale = new Locale(lang);
        InventoryDetailsDTO details = getInventoryDetails(inventoryId);

        List<String> headers = List.of(
                messageSource.getMessage("excel.header.name", null, locale),
                messageSource.getMessage("excel.header.systemQuantity", null, locale),
                messageSource.getMessage("excel.header.physicalQuantity", null, locale),
                messageSource.getMessage("excel.header.adjustment", null, locale),
                messageSource.getMessage("excel.header.reason", null, locale)
        );

        // Prepare rows for the Excel file
        List<List<Object>> rows = details.getProducts().stream().map(product -> List.of(
                product.getName(),
                product.getSystemQuantity(),
                product.getPhysicalQuantity(),
                product.getAdjustment(),
                (Object) product.getReason()
        )).collect(Collectors.toList());
        // Use the ExcelExportService to generate the Excel file
        return excelExportService.exportToExcel(headers, rows);
    }




}
