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
import javafx.scene.control.cell.PropertyValueFactory;
import wt.muppety.model.Category;
import wt.muppety.model.MockData;
import wt.muppety.model.Product;

import java.util.ArrayList;
import java.util.Optional;
import wt.muppety.model.Supplier;
import wt.muppety.model.Category;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.SupplierDao;
import wt.muppety.dao.CategoryDao;
import wt.muppety.dao.BaseDao;

import static wt.muppety.view.LayoutName.*;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class ProductListController implements IController<ObservableList<Product>> {

    @FXML
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product, String> nameColumn;
    @FXML
    public TableColumn<Product, String> unitPriceColumn;
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
    @FXML
    public Button addSupplierButton;
    private AppController appController;
    private ObservableList<Product> data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products =  productDao.listAll();
        productTable.setItems(products);
        
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn unitPriceColumn = new TableColumn("Price");
        TableColumn categoryColumn = new TableColumn("Category");
        TableColumn manufacturerColumn = new TableColumn("Manufacturer");
        TableColumn onPrescriptionColumn = new TableColumn("On prescription");
        TableColumn supplierColumn = new TableColumn("Supplier");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        onPrescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("onPrescription"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        productTable.setItems(products);
        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productTable.getColumns().addAll(nameColumn);
        productTable.getColumns().addAll(unitPriceColumn);
        productTable.getColumns().addAll(categoryColumn);
        productTable.getColumns().addAll(manufacturerColumn);
        productTable.getColumns().addAll(onPrescriptionColumn);
        productTable.getColumns().addAll(supplierColumn);

        // nameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getName()));
        // priceColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getUnitPrice() + " zł"));
        // categoryColumn.setCellValueFactory(dataValue -> {
        //     StringBuilder builder = new StringBuilder();
        //     dataValue.getValue().getCategories().forEach(category -> {
        //         builder.append(category);
        //         builder.append(", ");
        //     });
        //     return new SimpleStringProperty(builder.toString());
        // });
        // manufacturerColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getManufacturer()));
        // onPrescriptionColumn.setCellValueFactory(dataValue -> {
        //     String text = dataValue.getValue().isOnPrescription() ? "yes" : "no";
        //     return new SimpleStringProperty(text);
        // });

        deleteButton.disableProperty().bind(Bindings.isEmpty(productTable.getSelectionModel().getSelectedItems()));
        editButton.disableProperty().bind(Bindings.size(productTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
    }

    public void handleDeleteAction(ActionEvent event) {
        ProductDao productDao = new ProductDao();
        System.out.println(productTable.getSelectionModel().getSelectedItems().size());
        for (Product product : new ArrayList<>(productTable.getSelectionModel().getSelectedItems())){
            productDao.deleteById(Product.class, product.getId());
        }
        data.removeAll(productTable.getSelectionModel().getSelectedItems());



    }

    public void handleEditAction(ActionEvent event) {
        Product product = productTable.getSelectionModel().getSelectedItem();
        ProductDao productDao = new ProductDao();
        
        if (product != null) {
            appController.showDialog(product, EditProduct, "Edit product");
            productDao.update(product);
        }
        productTable.refresh();
    }

    public void handleAddProductAction(ActionEvent event) {
        Product newProduct = new Product();
        if (appController.showDialog(newProduct, EditProduct, "Add product")) {
            data.add(newProduct);
            ProductDao productDao = new ProductDao();
            Optional<Product> product = productDao.create(newProduct);
        }
    }

    public void handleAddCategoryAction(ActionEvent event) {
        Category newCategory = new Category();
        if (appController.showDialog(newCategory, EditCategory, "Add category")) {
            MockData.categories.add(newCategory);
            CategoryDao categoryDao = new CategoryDao();
            Optional<Category> category = categoryDao.create(newCategory);
        }
    }

    public void handleAddSupplierAction(ActionEvent event) {
        Supplier newSupplier = new Supplier();
        SupplierDao supplierDao = new SupplierDao();
        if (appController.showDialog(newSupplier, EditSupplier, "Add supplier")) {
            MockData.suppliers.add(newSupplier);
            
            Optional<Supplier> supplier = supplierDao.create(newSupplier);
            
        }
        supplierDao.listAll();
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
