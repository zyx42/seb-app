package se.seb.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<?> getProducts(@RequestParam AgeBracket ageBracket,
                                         @RequestParam IncomeBracket incomeBracket,
                                         @RequestParam Boolean student) {

        Iterable<Product> products = productService.getProductsByCriteria(ageBracket, incomeBracket, student);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
