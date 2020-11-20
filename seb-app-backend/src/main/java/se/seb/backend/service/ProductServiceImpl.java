package se.seb.backend.service;

import org.springframework.stereotype.Service;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ArrayList<Product> products = new ArrayList<>();

    public ProductServiceImpl() {

        // Populating product list with given values;
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

    @Override
    public Iterable<Product> getProducts() {
        return products;
    }

    @Override
    public Iterable<Product> getProductsByCriteria(AgeBracket ageBracket, IncomeBracket incomeBracket, Boolean student) {
        return products.stream()
                .filter(product -> product.getAgeBrackets().contains(ageBracket))
                .filter(product -> product.getIncomeBrackets().contains(incomeBracket))
                .filter(product -> !product.isStudent() || student)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
