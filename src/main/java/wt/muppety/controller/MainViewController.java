package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static wt.muppety.view.LayoutName.ProductList;
import static wt.muppety.view.LayoutName.UserList;

public class MainViewController implements IController<Void> {

    @FXML
    public Button userListButton;
    @FXML
    public Button productListButton;
    private AppController appController;

    public void handleUserListAction(ActionEvent event) {
        appController.showPane(null, UserList);
    }

    public void handleProductListAction(ActionEvent event) {
        appController.showPane(null, ProductList);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(Void data) {
// operation ignored
    }
}
