package wt.muppety.view;

public enum LayoutName {
    LoginView("view/panes/LoginViewPane.fxml"),
    MainView("view/panes/MainViewPane.fxml"),
    EmployeeList("view/panes/EmployeeListPane.fxml"),
    ProductList("view/panes/ProductListPane.fxml"),
    EditUser("view/dialogs/EditEmployeeDialog.fxml"),
    EditProduct("view/dialogs/EditProductDialog.fxml"),
    EditCategory("view/dialogs/EditCategoryDialog.fxml"),
    EditSupplier("view/dialogs/EditSupplierDialog.fxml"),
    EditTransaction("view/dialogs/EditTransactionDialog.fxml"),
    EditRecommendation("view/dialogs/EditRecommendationDialog.fxml"),
    StatisticsList("view/StatisticsViewPane.fxml");

    private final String path;

    LayoutName(String layoutPath) {
        this.path = layoutPath;
    }

    public String getPath() {
        return path;
    }
}
