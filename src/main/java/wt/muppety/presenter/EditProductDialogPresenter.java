package wt.muppety.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.model.Category;
import wt.muppety.model.Supplier;
import wt.muppety.model.Product;

import wt.muppety.dao.CategoryDao;
import wt.muppety.dao.SupplierDao;

import javafx.collections.ObservableList;
import java.util.HashSet;
import java.util.Set;

public class EditProductDialogPresenter extends AbstractDialogPresenter<Product> {
    @FXML
    public TextField nameTextField;

    @FXML
    public TextField priceTextField;

    @FXML
    public ComboBox<Category> categoryComboBox;

    @FXML
    public ComboBox<Supplier> supplierComboBox;

    @FXML
    public TextField manufacturerTextField;

    @FXML
    public CheckBox onPrescriptionCheckBox;

    @FXML
    private void initialize() {
        CategoryDao categoryDao = new CategoryDao();
        ObservableList<Category> categories = categoryDao.listAll();
        categoryComboBox.setItems(categories);
        SupplierDao supplierDao = new SupplierDao();
        ObservableList<Supplier> suppliers = supplierDao.listAll();
        supplierComboBox.setItems(suppliers);
    }

    @Override
    protected void updateModel() {
        data.setName(nameTextField.getText());
        data.setUnitPrice(Double.parseDouble(priceTextField.getText()));
        data.setManufacturer(manufacturerTextField.getText());
        data.setOnPrescription(onPrescriptionCheckBox.isSelected());
        Set<Category> newCategories = data.getCategories();
        if (newCategories == null)
            newCategories = new HashSet<>();
        newCategories.add(categoryComboBox.getValue());
        data.setCategories(newCategories);
        data.setSupplier(supplierComboBox.getValue());
        
    }

    @Override
    protected void updateControls() {
        nameTextField.setText(data.getName());
        priceTextField.setText(((Double) data.getUnitPrice()).toString());
        manufacturerTextField.setText(data.getManufacturer());
        onPrescriptionCheckBox.setSelected(data.getOnPrescription());
    }
}
