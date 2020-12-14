package wt.muppety.view;

public enum LayoutName {
    MainView("view/MainViewPane.fxml"),
    EmployeeList("view/EmployeeListPane.fxml"),
    ProductList("view/ProductListPane.fxml"),
    EditUser("view/EditEmployeeDialog.fxml"),
    EditProduct("view/EditProductDialog.fxml"),
    EditCategory("view/EditCategoryDialog.fxml"),
    EditSupplier("view/EditSupplierDialog.fxml"),
    EditTransaction("view/EditTransactionDialog.fxml"),
    Login("view/LoginDialog.fxml");

    private final String path;

    LayoutName(String layoutPath) {
        this.path = layoutPath;
    }

    public String getPath() {
        return path;
    }
}
