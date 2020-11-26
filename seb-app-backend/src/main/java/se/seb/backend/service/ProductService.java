package se.seb.backend.service;

import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;

public interface ProductService {

    Iterable<Product> getProducts();
    Iterable<Product> getProductsByCriteria(AgeBracket ageBracket, IncomeBracket incomeBracket, Boolean student);
    Product findByProductName(String productName);
    Boolean existsByProductName(String productName);
    Product addNewProduct(Product newProduct);
    Product updateProduct(String productName, Product updatedProduct);
    void removeProduct(String productName);
}
