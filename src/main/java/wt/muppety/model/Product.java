package wt.muppety.model;

import java.util.Set;

public class Product {
    private int id;
    private Set<Category> categories;
    private Supplier supplier;
    private String name;
    private double unitPrice;
    private boolean onPrescription;
    private String manufacturer;
    private Permissions permissions;

    public Product(Set<Category> categories, Supplier supplier, String name, double unitPrice, boolean onPrescription, String manufacturer) {
        this.categories = categories;
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

    public boolean isOnPrescription() {
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

    public boolean canBuy() {
        return permissions.canBuy;
    }

    public boolean canSell() {
        return permissions.canSell;
    }

    public boolean canBrowseDb() {
        return permissions.canBrowseDB;
    }

    public boolean canModerateDB() {
        return permissions.canModerateDB;
    }

    public void setPermissions(boolean canBuy, boolean canSell, boolean canBrowseDB, boolean canModerateDB) {
        permissions.canBuy = canBuy;
        permissions.canSell = canSell;
        permissions.canBrowseDB = canBrowseDB;
        permissions.canModerateDB = canModerateDB;
    }

    private class Permissions {
        protected boolean canBuy;
        protected boolean canSell;
        protected boolean canBrowseDB;
        protected boolean canModerateDB;

        public Permissions() {
            canBuy = false;
            canSell = false;
            canBrowseDB = false;
            canModerateDB = false;
        }
    }
}
