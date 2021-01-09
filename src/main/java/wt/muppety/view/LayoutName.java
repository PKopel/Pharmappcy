package wt.muppety.view;

public enum LayoutName {
    LoginView("view/LoginViewPane.fxml"),
    MainView("view/MainViewPane.fxml"),
    EmployeeList("view/EmployeeListPane.fxml"),
    ProductList("view/ProductListPane.fxml", true),
    EditUser("view/EditEmployeeDialog.fxml"),
    EditProduct("view/EditProductDialog.fxml"),
    EditCategory("view/EditCategoryDialog.fxml"),
    EditSupplier("view/EditSupplierDialog.fxml"),
    EditTransaction("view/EditTransactionDialog.fxml");

    private final String path;

    private final boolean maximized;

    LayoutName(String layoutPath, boolean maximized) {
        this.maximized = maximized;
        this.path = layoutPath;
    }

    LayoutName(String layoutPath) {
        this.maximized = false;
        this.path = layoutPath;
    }

    public String getPath() {
        return path;
    }

    public boolean isMaximized() {
        return maximized;
    }
}
