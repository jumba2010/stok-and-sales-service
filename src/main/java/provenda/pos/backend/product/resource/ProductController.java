package provenda.pos.backend.product.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.dto.CreateProductRequest;
import provenda.pos.backend.product.dto.ProductResponseDTO;
import provenda.pos.backend.product.dto.SearchProductRequest;
import provenda.pos.backend.product.service.ProductQueryService;
import provenda.pos.backend.product.service.ProductService;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.product.entity.ProductEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    /*  @GetMapping(value = "/")
      public String getPage(){
          return "Este endpoint funciona";
      }
  */
    private final ProductService productService;
    private final ProductQueryService productQueryService;

    public ProductController(ProductService productService, ProductQueryService productQueryService) {
        this.productService = productService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest)
            throws BusinessException {
        var product = productService.createProduct(
                UserContext.getDefaultContext(),
                CreateProductRequest.getProductInstance(createProductRequest));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @RequestBody @Valid CreateProductRequest createProductRequest) throws BusinessException {
        ProductEntity updatedProduct = CreateProductRequest.getProductInstance(createProductRequest);
        productService.updateProduct(UserContext.getDefaultContext(), productId, updatedProduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws BusinessException {
        productService.deleteProduct(UserContext.getDefaultContext(), productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<ProductResponseDTO> searchProductsBySupplierId(
            @ModelAttribute SearchProductRequest searchProductRequest, @PageableDefault(size = 6) final Pageable pageable) {
        return productQueryService.searchProducts(searchProductRequest, pageable)
                .map(ProductResponseDTO::fromEntity);
    }

}
