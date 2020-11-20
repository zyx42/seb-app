package se.seb.backend.service;

import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;

public interface ProductService {

    Iterable<Product> getProducts();
    Iterable<Product> getProductsByCriteria(AgeBracket ageBracket, IncomeBracket incomeBracket, Boolean student);
}
