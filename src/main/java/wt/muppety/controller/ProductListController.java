package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import wt.muppety.dao.CategoryDao;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.SupplierDao;
import wt.muppety.model.Category;
import wt.muppety.model.Product;
import wt.muppety.model.Supplier;

import java.util.ArrayList;
import java.util.Optional;

import static wt.muppety.view.LayoutName.*;


/**
 * Controller for layout presenting product list.
 * Allows to add, remove and edit product entries and create new categories in system's database.
 */
public class ProductListController extends AbstractController<ObservableList<Product>> {

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
    public TableColumn<Product, String> supplierColumn;
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
    @FXML
    private TextField filterField;
    private ObservableList<Product> data = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        onPrescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("onPrescription"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        FilteredList<Product> filteredData = new FilteredList<>(products, p -> true);
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                String[] splited = lowerCaseFilter.split(" ");
                for(String part : splited) {
                    if (!product.toString().toLowerCase().contains(part))
                        return false;
                }
                return true;
            });
            productTable.setItems(sortedData);
        });

        productTable.setItems(sortedData);


        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        deleteButton.disableProperty().bind(Bindings.isEmpty(productTable.getSelectionModel().getSelectedItems()));
        editButton.disableProperty().bind(Bindings.size(productTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
        productTable.getSortOrder().add(unitPriceColumn);
        sortedData.comparatorProperty().bind(productTable.comparatorProperty());

        productTable.sort();
    }

    public void handleDeleteAction(ActionEvent event) {
        ProductDao productDao = new ProductDao();
        System.out.println(productTable.getSelectionModel().getSelectedItems().size());
        boolean deleted;
        for (Product product : new ArrayList<>(productTable.getSelectionModel().getSelectedItems())) {
            deleted = productDao.deleteById(Product.class, product.getId());
            if (!deleted) System.out.println("Error while deleting " + product);
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
            ProductDao productDao = new ProductDao();
            Optional<Product> product = productDao.create(newProduct);
            data.add(product.orElseThrow());
        }
    }

    public void handleAddCategoryAction(ActionEvent event) {
        Category newCategory = new Category();
        if (appController.showDialog(newCategory, EditCategory, "Add category")) {
            CategoryDao categoryDao = new CategoryDao();
            Optional<Category> category = categoryDao.create(newCategory);
            if (category.isEmpty()) System.out.println("Could not create new category");
        }
    }

    public void handleAddSupplierAction(ActionEvent event) {
        Supplier newSupplier = new Supplier();
        if (appController.showDialog(newSupplier, EditSupplier, "Add supplier")) {
            SupplierDao supplierDao = new SupplierDao();
            Optional<Supplier> supplier = supplierDao.create(newSupplier);
            if (supplier.isEmpty()) System.out.println("Could not create new supplier");
        }
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
