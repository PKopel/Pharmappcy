package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewController {

    private AppController appController;

    @FXML
    public Button userListButton;
    @FXML
    public Button productListButton;

    public void handleUserListAction(ActionEvent event) {
        appController.showUserListPane();
    }

    public void handleProductListAction(ActionEvent event) {
        appController.showProductListPane();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
