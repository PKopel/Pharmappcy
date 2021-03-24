package wt.muppety.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wt.muppety.model.Supplier;

public class EditSupplierDialogPresenter extends AbstractDialogPresenter<Supplier> {

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField phoneNumberTextField;

    @Override
    protected void updateModel() {
        data.setCompanyName(nameTextField.getText());
        data.setEmail(emailTextField.getText());
        data.setPhoneNumber(phoneNumberTextField.getText());
    }

    @Override
    protected void updateControls() {
        //IGNORE
    }
}
