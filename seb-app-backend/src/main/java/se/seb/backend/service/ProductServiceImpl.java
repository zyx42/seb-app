package se.seb.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;
import se.seb.backend.repository.ProductRepository;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Iterable<Product> getProductsByCriteria(AgeBracket ageBracket, IncomeBracket incomeBracket, Boolean student) {

        return productRepository.getProducts().stream()
                .filter(product -> product.getAgeBrackets().contains(ageBracket))
                .filter(product -> product.getIncomeBrackets().contains(incomeBracket))
                .filter(product -> !product.isStudent() || student)
                .collect(Collectors.toList());
    }

    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product was not found with name: " + productName));
    }

    @Override
    public Product addNewProduct(Product newProduct) {
        Product product = productRepository.addProduct(newProduct);
        logger.info("product {} has been added", product.getProductName());

        return product;
    }

    @Override
    public Product updateProduct(String productName, Product updatedProduct) {
        Product product = productRepository.updateProduct(productName, updatedProduct);
        logger.info("product {} has been updated", productName);

        return product;
    }

    @Override
    public void removeProduct(String productName) {
        productRepository.removeProduct(productName);
        logger.info("product {} has been removed", productName);
    }
}
