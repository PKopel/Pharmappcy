package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;
import wt.muppety.model.MockData;


import static wt.muppety.view.LayoutName.EmployeeList;
import static wt.muppety.view.LayoutName.ProductList;
import static wt.muppety.view.LayoutName.Login;

import static wt.muppety.authentication.Permission.canBrowseDB;

public class MainViewController implements IController<Void> {

    @FXML
    public Button employeeListButton;
    @FXML
    public Button productListButton;
    @FXML
    public Button loginButton;

    private AppController appController;

    @FXML
    private void initialize() {
        Authenticator.guardButton(employeeListButton,canBrowseDB);
        Authenticator.guardButton(productListButton,canBrowseDB);
        loginButton.managedProperty().bind(Bindings.createBooleanBinding(Authenticator::isLoggedIn));
        loginButton.visibleProperty().bind(Bindings.createBooleanBinding(Authenticator::isLoggedIn));
    }

    public void handleEmployeeListAction(ActionEvent event) {
        appController.showPane(MockData.employees, EmployeeList);
    }

    public void handleProductListAction(ActionEvent event) {
        appController.showPane(MockData.products, ProductList);
    }


    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(Void data) {
// operation ignored
    }

    public void handleLogin(ActionEvent event) {
        LoginData data = new LoginData();
        if(this.appController.showDialog(data,Login,"Sign in")){
            Authenticator.logIn(data);
            this.initialize();
        }

    }
}
