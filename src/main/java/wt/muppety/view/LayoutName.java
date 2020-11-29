package wt.muppety.view;

public enum LayoutName {
    MainView("view/MainViewPane.fxml"),
    UserList("view/UserListPane.fxml"),
    ProductList("view/ProductListPane.fxml"),
    EditUser("view/EditUserDialog.fxml"),
    EditProduct("view/EditProductDialog.fxml"),
    EditCategory("view/EditCategoryDialog.fxml");

    private final String path;

    LayoutName(String layoutPath) {
        this.path = layoutPath;
    }

    public String getPath() {
        return path;
    }
}
