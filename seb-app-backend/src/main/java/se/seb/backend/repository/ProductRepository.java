package se.seb.backend.repository;

import org.springframework.stereotype.Repository;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Optional;

@Repository
public class ProductRepository {

    private ArrayList<Product> products = new ArrayList<>();

    public ProductRepository() {

        // Populating product list with hardcoded default values;
        Product current_account = new Product("Current Account",
                EnumSet.of(AgeBracket.ADULT, AgeBracket.SENIOR),
                EnumSet.of(IncomeBracket.LOW_INCOME, IncomeBracket.MEDIUM_INCOME, IncomeBracket.HIGH_INCOME),
                false);
        Product current_account_plus = new Product("Current Account Plus",
                EnumSet.of(AgeBracket.ADULT, AgeBracket.SENIOR),
                EnumSet.of(IncomeBracket.HIGH_INCOME),
                false);
        Product junior_saver_account = new Product("Junior Saver Account",
                EnumSet.of(AgeBracket.JUNIOR),
                EnumSet.allOf(IncomeBracket.class),
                false);
        Product student_account = new Product("Student Account",
                EnumSet.of(AgeBracket.ADULT, AgeBracket.SENIOR),
                EnumSet.allOf(IncomeBracket.class),
                true);
        Product senior_account = new Product("Senior Account",
                EnumSet.of(AgeBracket.SENIOR),
                EnumSet.allOf(IncomeBracket.class),
                false);
        Product debit_card = new Product("Debit Card",
                EnumSet.of(AgeBracket.ADULT, AgeBracket.SENIOR),
                EnumSet.of(IncomeBracket.NO_INCOME, IncomeBracket.LOW_INCOME),
                false);
        Product credit_card = new Product("Credit Card",
                EnumSet.of(AgeBracket.ADULT, AgeBracket.SENIOR),
                EnumSet.of(IncomeBracket.MEDIUM_INCOME, IncomeBracket.HIGH_INCOME),
                false);
        Product gold_credit_card = new Product("Gold Credit Card",
                EnumSet.of(AgeBracket.ADULT, AgeBracket.SENIOR),
                EnumSet.of(IncomeBracket.HIGH_INCOME),
                false);

        products.add(current_account);
        products.add(current_account_plus);
        products.add(junior_saver_account);
        products.add(student_account);
        products.add(senior_account);
        products.add(debit_card);
        products.add(credit_card);
        products.add(gold_credit_card);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Optional<Product> findByProductName(String productName) {
        return products.stream().filter(p -> p.getProductName().equalsIgnoreCase(productName)).findFirst();
    }

    public Boolean existsByProductName(String productName) {
        Optional<Product> productOptional = products.stream().filter(p -> p.getProductName().equalsIgnoreCase(productName)).findFirst();
        if (productOptional.isPresent()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Product addProduct(Product newProduct) {
        products.add(newProduct);

        return newProduct;
    }

    public Product updateProduct(String productName, Product updatedProduct) {
        Product product = products.stream()
                .filter(p -> p.getProductName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No product was found with product name: " + productName));
        products.remove(product);
        products.add(updatedProduct);

        return updatedProduct;
    }

    public void removeProduct(String productName) {
        products.removeIf(product -> product.getProductName().equalsIgnoreCase(productName));
    }
}
