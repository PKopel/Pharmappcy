package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wt.muppety.model.Category;
import wt.muppety.model.Product;

import java.util.Collection;

import static wt.muppety.view.LayoutName.*;

public class ProductListController implements IController<Collection<Product>> {

    @FXML
    public TableView<Product> productTable;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn priceColumn;
    @FXML
    public TableColumn categoryColumn;
    @FXML
    public TableColumn onPrescriptionColumn;
    @FXML
    public Button addProductButton;
    @FXML
    public Button addCategoryButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button editButton;
    @FXML
    public Button backButton;
    private AppController appController;

    public void handleDeleteAction(ActionEvent event) {
        //TODO
    }

    public void handleEditAction(ActionEvent event) {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            appController.showDialog(product, EditUser, "Edit user");
        }
    }

    public void handleAddProductAction(ActionEvent event) {
        appController.showDialog(new Product(), EditProduct, "Add product");
    }

    public void handleAddCategoryAction(ActionEvent event) {
        appController.showDialog(new Category(), EditCategory, "Add category");
    }

    public void handleBackAction(ActionEvent event) {
        appController.showPane(null, MainView);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(Collection<Product> data) {

    }
}
