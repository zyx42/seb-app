package se.seb.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;
import se.seb.backend.security.payload.MessageResponse;
import se.seb.backend.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        Iterable<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductsByCriteria(@RequestParam AgeBracket ageBracket,
                                                      @RequestParam IncomeBracket incomeBracket,
                                                      @RequestParam Boolean student) {

        Iterable<Product> products = productService.getProductsByCriteria(ageBracket, incomeBracket, student);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<?> getProductByProductName(@PathVariable String productName) {
        Product product = productService.findByProductName(productName);

        return ResponseEntity.ok(product);
    }

    @PostMapping("")
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody Product newProduct) {

        if (productService.existsByProductName(newProduct.getProductName())) {

            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Product with such a name already exists."));
        }

        Product product = productService.addNewProduct(newProduct);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productName}")
    public ResponseEntity<?> updateProduct(@PathVariable String productName,
                                           @Valid @RequestBody Product updatedProduct) {

        if (!productName.equalsIgnoreCase(updatedProduct.getProductName()) &&
                productService.existsByProductName(updatedProduct.getProductName())) {

            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Product with such a name already exists."));
        }

        Product product = productService.updateProduct(productName, updatedProduct);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<?> removeProduct(@PathVariable String productName) {
        productService.removeProduct(productName);

        return ResponseEntity.ok(
                new MessageResponse("Product has been successfully removed, product name: " + productName));
    }
}
