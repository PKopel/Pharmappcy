package wt.muppety.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.model.Category;
import wt.muppety.model.Product;

public class EditProductDialogPresenter extends AbstractDialogPresenter<Product> {
    @FXML
    public TextField nameTextField;

    @FXML
    public TextField priceTextField;

    @FXML
    public ComboBox<Category> categoryComboBox;

    @FXML
    public TextField manufacturerTextField;

    @FXML
    public CheckBox onPrescriptionTextField;

    @Override
    protected void updateModel() {
        //TODO
    }

    @Override
    protected void updateControls() {
        //TODO
    }
}
