package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static wt.muppety.view.LayoutName.MainView;

public abstract class AbstractController<T> {

    protected AppController appController;

    protected T data;

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setData(T data) {
        this.data = data;
    }

    @FXML
    public void handleBackAction(ActionEvent event) {
        appController.showPane(null, MainView);
    }
}
