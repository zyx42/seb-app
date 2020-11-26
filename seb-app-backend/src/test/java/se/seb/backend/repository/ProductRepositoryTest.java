package se.seb.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import se.seb.backend.domain.AgeBracket;
import se.seb.backend.domain.IncomeBracket;
import se.seb.backend.domain.Product;

import java.util.EnumSet;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"se.seb.backend.repository"})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByProductName_thenReturnProduct() {
        Product test_product = new Product(
                "test_product",
                EnumSet.of(AgeBracket.ADULT),
                EnumSet.of(IncomeBracket.MEDIUM_INCOME),
                false);

        productRepository.addProduct(test_product);

        try {
            Product returnedProduct = productRepository.findByProductName(test_product.getProductName()).get();
            assertThat(returnedProduct.getProductName()).isEqualTo(test_product.getProductName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenExistsByProductName_thenReturnBoolean() {
        Product test_product = new Product(
                "test_product",
                EnumSet.of(AgeBracket.ADULT),
                EnumSet.of(IncomeBracket.MEDIUM_INCOME),
                false);

        productRepository.addProduct(test_product);

        try {
            assertThat(productRepository.existsByProductName(test_product.getProductName())).isTrue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddProduct_thenReturnProduct() {
        Product test_product = new Product(
                "test_product",
                EnumSet.of(AgeBracket.ADULT),
                EnumSet.of(IncomeBracket.MEDIUM_INCOME),
                false);

        try {
            Product returnedProduct = productRepository.addProduct(test_product);
            assertThat(returnedProduct.getProductName()).isEqualTo(test_product.getProductName());
            assertThat(returnedProduct.getAgeBrackets()).isEqualTo(test_product.getAgeBrackets());
            assertThat(returnedProduct.getIncomeBrackets()).isEqualTo(test_product.getIncomeBrackets());
            assertThat(returnedProduct.isStudent()).isEqualTo(test_product.isStudent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUpdateProduct_thenReturnUpdatedProduct() {
        Product test_product = new Product(
                "test_product",
                EnumSet.of(AgeBracket.ADULT),
                EnumSet.of(IncomeBracket.MEDIUM_INCOME),
                false);

        productRepository.addProduct(test_product);

        Product updated_product = new Product(
                "updated_test_product",
                EnumSet.of(AgeBracket.JUNIOR),
                EnumSet.of(IncomeBracket.HIGH_INCOME),
                true);

        try {
            Product updated_test_product = productRepository
                    .updateProduct(test_product.getProductName(), updated_product);
            assertThat(updated_test_product.getProductName()).isEqualTo(updated_product.getProductName());
            assertThat(updated_test_product.getAgeBrackets()).isEqualTo(updated_product.getAgeBrackets());
            assertThat(updated_test_product.getIncomeBrackets()).isEqualTo(updated_product.getIncomeBrackets());
            assertThat(updated_test_product.isStudent()).isEqualTo(updated_product.isStudent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenRemoveProduct_thenProductRemoved() {
        Product test_product = new Product(
                "test_product",
                EnumSet.of(AgeBracket.ADULT),
                EnumSet.of(IncomeBracket.MEDIUM_INCOME),
                false);

        productRepository.addProduct(test_product);

        try {
            productRepository.removeProduct(test_product.getProductName());
            Optional<Product> productOptional = productRepository.findByProductName(test_product.getProductName());
            assertThat(productOptional.get()).isNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
