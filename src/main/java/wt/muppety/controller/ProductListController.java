package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wt.muppety.model.Product;

public class ProductListController {

    private AppController appController;

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

    public void handleDeleteAction(ActionEvent event) {
        //TODO
    }

    public void handleEditAction(ActionEvent event) {
        //TODO
    }

    public void handleAddProductAction(ActionEvent event) {
        //TODO
    }

    public void handleAddCategoryAction(ActionEvent event) {
        //TODO
    }

    public void handleBackAction(ActionEvent event) {
        appController.initRootLayout();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
