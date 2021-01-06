package wt.muppety.presenter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.dao.CategoryDao;
import wt.muppety.dao.SupplierDao;
import wt.muppety.model.Category;
import wt.muppety.model.Product;
import wt.muppety.model.Supplier;
import wt.muppety.view.DynamicComboBoxPane;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class EditProductDialogPresenter extends AbstractDialogPresenter<Product> {
    @FXML
    public TextField nameTextField;

    @FXML
    public TextField priceTextField;

    @FXML
    public DynamicComboBoxPane<Category> categoryComboBox;

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
        categoryComboBox.insertRow();
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
        Set<Category> newCategories = new HashSet<>(categoryComboBox.getChosenItems());
        data.setCategories(newCategories);
        data.setSupplier(supplierComboBox.getValue());
    }

    @Override
    protected void updateControls() {
        nameTextField.setText(data.getName());
        priceTextField.setText(((Double) data.getUnitPrice()).toString());
        Set<Category> categories = data.getCategories();
        if (categories != null)
            categoryComboBox.setChosenItems(new LinkedList<>(categories));
        manufacturerTextField.setText(data.getManufacturer());
        onPrescriptionCheckBox.setSelected(data.getOnPrescription());
    }


}
