package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;

import static wt.muppety.view.LayoutName.EditUser;
import static wt.muppety.view.LayoutName.MainView;

public class LoginViewController extends AbstractController<LoginData> {

    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    private Label errorLabel;

    @Override
    public void setData(LoginData data) {
        this.data = data;
    }

    protected void updateModel() {
        data.setLogin(loginTextField.getText());
        data.setPassword(passwordTextField.getText());
    }

    @FXML
    protected void handleLogInAction(ActionEvent event) {
        updateModel();
        if (Authenticator.getInstance().logIn(data)) {
            appController.showPane(null, MainView);
        } else {
            errorLabel.setVisible(true);
        }
    }

    @FXML
    protected void handleRegisterAction(ActionEvent event) {
        Employee newEmployee = new Employee();
        if (appController.showDialog(newEmployee, EditUser, "New user")) {
            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.createClient(newEmployee);
            
        }
    }
}
