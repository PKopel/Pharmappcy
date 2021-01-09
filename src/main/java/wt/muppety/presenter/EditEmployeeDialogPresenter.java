package wt.muppety.presenter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.Permission;
import wt.muppety.model.Employee;

import java.util.Objects;

public class EditEmployeeDialogPresenter extends AbstractDialogPresenter<Employee> {

    @FXML
    public TextField firstNameTextField;

    @FXML
    public TextField lastNameTextField;

    @FXML
    public Label positionLabel;

    @FXML
    public ComboBox<Employee.Position> positionComboBox;

    @FXML
    public TextField loginTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    private void initialize() {
        Authenticator.guardControl(positionComboBox, Permission.canModerateDB);
        Authenticator.guardControl(positionLabel, Permission.canModerateDB);
        positionComboBox.setItems(FXCollections.observableArrayList(Employee.Position.values()));
    }

    @Override
    protected void updateModel() {
        data.setFirstname(firstNameTextField.getText());
        data.setLastname(lastNameTextField.getText());
        Employee.Position position = positionComboBox.getSelectionModel().getSelectedItem();
        data.setPosition(Objects.requireNonNullElse(position, Employee.Position.Chair));
        if (position == Employee.Position.Client){
            data.setClientPermissions();
        }
        data.setLogin(loginTextField.getText());
        data.setPassword(passwordTextField.getText());
    }

    @Override
    protected void updateControls() {
        firstNameTextField.setText(data.getFirstname());
        lastNameTextField.setText(data.getLastname());
        positionComboBox.getSelectionModel().select(data.getPosition());
        loginTextField.setText(data.getLogin());
    }
}
