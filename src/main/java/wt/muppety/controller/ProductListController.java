package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wt.muppety.model.Category;
import wt.muppety.model.MockData;
import wt.muppety.model.Product;

import static wt.muppety.view.LayoutName.*;

public class ProductListController implements IController<ObservableList<Product>> {

    @FXML
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product, String> nameColumn;
    @FXML
    public TableColumn<Product, String> priceColumn;
    @FXML
    public TableColumn<Product, String> manufacturerColumn;
    @FXML
    public TableColumn<Product, String> onPrescriptionColumn;
    @FXML
    public TableColumn<Product, String> categoryColumn;
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
    private ObservableList<Product> data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productTable.setItems(data);

        nameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getName()));
        priceColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getUnitPrice() + " zł"));
        categoryColumn.setCellValueFactory(dataValue -> {
            StringBuilder builder = new StringBuilder();
            dataValue.getValue().getCategories().forEach(category -> {
                builder.append(category);
                builder.append(", ");
            });
            return new SimpleStringProperty(builder.toString());
        });
        manufacturerColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getManufacturer()));
        onPrescriptionColumn.setCellValueFactory(dataValue -> {
            String text = dataValue.getValue().isOnPrescription() ? "yes" : "no";
            return new SimpleStringProperty(text);
        });

        deleteButton.disableProperty().bind(Bindings.isEmpty(productTable.getSelectionModel().getSelectedItems()));
        editButton.disableProperty().bind(Bindings.size(productTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
    }

    public void handleDeleteAction(ActionEvent event) {
        data.removeAll(productTable.getSelectionModel().getSelectedItems());
    }

    public void handleEditAction(ActionEvent event) {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            appController.showDialog(product, EditProduct, "Edit product");
        }
        productTable.refresh();
    }

    public void handleAddProductAction(ActionEvent event) {
        Product newProduct = new Product();
        if (appController.showDialog(newProduct, EditProduct, "Add product")) {
            data.add(newProduct);
        }
    }

    public void handleAddCategoryAction(ActionEvent event) {
        Category newCategory = new Category();
        if (appController.showDialog(newCategory, EditCategory, "Add category")) {
            MockData.categories.add(newCategory);
        }
    }

    public void handleBackAction(ActionEvent event) {
        appController.showPane(null, MainView);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(ObservableList<Product> data) {
        this.data = data;
        productTable.setItems(data);
    }
}
