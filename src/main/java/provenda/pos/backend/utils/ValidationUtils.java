package provenda.pos.backend.utils;

import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.product.dao.CategoryRepository;
import provenda.pos.backend.product.dao.InventoryRepository;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.dao.SupplierRepository;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.product.entity.SupplierEntity;

public class ValidationUtils {

    public static boolean validateProductCreation(ProductRepository productRepository, final ProductEntity product, CategoryRepository categoryRepository)
            throws ValidationException {

        Long categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
        if (categoryId == null) {
            throw new ValidationException(BusinessConstants.CATEGORY_IS_REQUIRED, "Category is required");
        }
        if (!categoryRepository.existsById(categoryId)){
            throw new ValidationException(BusinessConstants.CATEGORY_IS_REQUIRED, "No category found by given Id");
        }
        /*productRepository.findByCode(product.getCode()).orElseThrow(
                ()->new IllegalAccessException("Codigo ja existe"));
         */
    return true;
    }

    public static boolean validateProductUpdate(ProductRepository productRepository, CategoryRepository categoryRepository, Long productId, ProductEntity updatedProduct
    ) throws ValidationException {
        // Validar se o productID existe na DB
        ProductEntity existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ValidationException(BusinessConstants.PRODUCT_NOT_FOUND, "Product not found with ID: " + productId));


        if (!existingProduct.getCode().equals(updatedProduct.getCode())) {
            productRepository.findByCode(updatedProduct.getCode()).ifPresent(product -> {
                try {
                    throw new ValidationException(BusinessConstants.PRODUCT_CODE_UNIQUE, "Product code already exists.");
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return true;
    }

    public static boolean validateSupplierCreation(SupplierRepository supplierRepository, final SupplierEntity supplierEntity)
        throws ValidationException{
        supplierRepository.findByName(supplierEntity.getName()).ifPresent(existingSupplier ->{
            try {
                throw new ValidationException(
                        BusinessConstants.SUPPLIER_NAME_UNIQUE, "A supplier with this name already exists"
                );
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }

    public static boolean validateSupplierUpdate(SupplierRepository supplierRepository,  Long supplierId, SupplierEntity updatedSupplier)
        throws ValidationException{

        SupplierEntity existingSupplier = supplierRepository.findById(supplierId).orElseThrow(()->
                new ValidationException(BusinessConstants.SUPPLIER_NOT_FOUND, "Supplier With ID: "+supplierId+" Not Found"));

        return true;
    }

    public static boolean validateInventoryExists(InventoryRepository inventoryRepository, Long inventoryId)
            throws ValidationException {
        inventoryRepository.findById(inventoryId).orElseThrow(() ->
                new ValidationException(BusinessConstants.INVENTORY_NOT_FOUND, "Inventory with ID: " + inventoryId + " not found"));
        return true;


    }




}
