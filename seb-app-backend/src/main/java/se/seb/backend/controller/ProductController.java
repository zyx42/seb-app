package se.seb.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;
import se.seb.backend.service.ProductService;

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
    public ResponseEntity<?> addNewProduct(@RequestBody Product newProduct) {
        Product product = productService.addNewProduct(newProduct);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productName}")
    public ResponseEntity<?> updateProduct(@PathVariable String productName,
                                           @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(productName, updatedProduct);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<?> removeProduct(@PathVariable String productName) {
        productService.removeProduct(productName);

        return ResponseEntity.ok("Product has been successfully removed, product name: " + productName);
    }
}
