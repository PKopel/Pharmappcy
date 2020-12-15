package wt.muppety.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = Product.TABLE_NAME)
public class Product {

    public static final String TABLE_NAME = "Product";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Category> categories;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Supplier supplier;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "unitPrice", nullable = false)
    private double unitPrice;
    @Column(name = "onPrescription", nullable = false)
    private boolean onPrescription;
    @Column(name = "manfacturer", nullable = false, length = 50)
    private String manufacturer;

    public Product(Supplier supplier, String name, double unitPrice, boolean onPrescription, String manufacturer) {
        this.categories = new HashSet<>();
        this.supplier = supplier;
        this.name = name;
        this.unitPrice = unitPrice;
        this.onPrescription = onPrescription;
        this.manufacturer = manufacturer;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean getOnPrescription() {
        return onPrescription;
    }

    public void setOnPrescription(boolean onPrescription) {
        this.onPrescription = onPrescription;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return name;
    }

}
