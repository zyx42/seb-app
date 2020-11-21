package se.seb.backend.service;

import org.springframework.stereotype.Service;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;
import se.seb.backend.repository.ProductRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

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
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
