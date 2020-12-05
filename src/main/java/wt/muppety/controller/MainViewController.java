package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import wt.muppety.authentication.Authenticator;
import wt.muppety.model.MockData;

import static wt.muppety.view.LayoutName.EmployeeList;
import static wt.muppety.view.LayoutName.ProductList;

public class MainViewController implements IController<Void> {

    @FXML
    public Button employeeListButton;
    @FXML
    public Button productListButton;
    @FXML
    public Button loginButton;

    private AppController appController;

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

    public void handleLogin(ActionEvent actionEvent) {
        Authenticator.logIn();
    }
}
