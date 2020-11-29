package wt.muppety.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.model.Category;

public class EditProductDialogPresenter {
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

    public void handleCancelAction(ActionEvent event) {
    }

    public void handleOkAction(ActionEvent event) {
    }
}
