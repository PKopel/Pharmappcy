package wt.muppety.presenter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.model.Employee;

public class EditEmployeeDialogPresenter extends AbstractDialogPresenter<Employee> {

    @FXML
    public TextField firstNameTextField;

    @FXML
    public TextField lastNameTextField;

    @FXML
    public ComboBox<Employee.Position> positionComboBox;

    @FXML
    public TextField loginTextField;

    @FXML
    public TextField passwordTextField;

    @FXML
    private void initialize() {
        positionComboBox.setItems(FXCollections.observableArrayList(Employee.Position.values()));
    }

    @Override
    protected void updateModel() {
        data.setFirstname(firstNameTextField.getText());
        data.setLastname(lastNameTextField.getText());
        data.setPosition(positionComboBox.getSelectionModel().getSelectedItem());
        data.setLogin(loginTextField.getText());
        data.setPassword(passwordTextField.getText());
    }

    @Override
    protected void updateControls() {
        firstNameTextField.setText(data.getFirstname());
        lastNameTextField.setText(data.getLastname());
        positionComboBox.getSelectionModel().select(data.getPosition());
        loginTextField.setText(data.getLogin());
        passwordTextField.setText(data.getPassword());
    }
}
