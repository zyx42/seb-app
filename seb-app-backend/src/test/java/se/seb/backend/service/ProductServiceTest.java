package se.seb.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;
import se.seb.backend.repository.ProductRepository;

import java.util.ArrayList;
import java.util.EnumSet;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @TestConfiguration
    static class ProductServiceTestContextConfiguration {

        @Bean
        public ProductService productService(ProductRepository productRepository) {
            return new ProductServiceImpl(productRepository);
        }
    }

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        ArrayList<Product> products = new ArrayList<>();

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

        Mockito.when(productRepository.getProducts()).thenReturn(products);
    }

    @Test
    public void whenGetProductsByCriteria_thenReturnProducts() {

        ArrayList<Product> products1 = (ArrayList<Product>) productService.getProductsByCriteria(
                AgeBracket.JUNIOR,
                IncomeBracket.NO_INCOME,
                false);

        ArrayList<Product> products2 = (ArrayList<Product>) productService.getProductsByCriteria(
                AgeBracket.ADULT,
                IncomeBracket.MEDIUM_INCOME,
                true);

        assertThat(products1.size()).isEqualTo(1);
        assertThat(products2.size()).isEqualTo(3);
    }
}
