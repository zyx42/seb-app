package se.seb.backend.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.EnumSet;
import java.util.Objects;

public class Product {

    @NotBlank
    @Size(min = 3, max = 20)
    private String productName;
    @NotEmpty
    private EnumSet<AgeBracket> ageBrackets;
    @NotEmpty
    private EnumSet<IncomeBracket> incomeBrackets;
    @NotNull
    private Boolean student;

    public Product() {}

    public Product(String productName,
                   EnumSet<AgeBracket> ageBrackets,
                   EnumSet<IncomeBracket> incomeBrackets,
                   Boolean student) {
        this.productName = productName;
        this.ageBrackets = ageBrackets;
        this.incomeBrackets = incomeBrackets;
        this.student = student;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public EnumSet<AgeBracket> getAgeBrackets() {
        return ageBrackets;
    }

    public void setAgeBrackets(EnumSet<AgeBracket> ageBrackets) {
        this.ageBrackets = ageBrackets;
    }

    public EnumSet<IncomeBracket> getIncomeBrackets() {
        return incomeBrackets;
    }

    public void setIncomeBrackets(EnumSet<IncomeBracket> incomeBrackets) {
        this.incomeBrackets = incomeBrackets;
    }

    public Boolean isStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", ageBrackets=" + ageBrackets +
                ", incomeBrackets=" + incomeBrackets +
                ", student=" + student +
                '}';
    }
}
