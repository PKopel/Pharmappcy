package wt.muppety.controller;

import javafx.event.ActionEvent;

import static wt.muppety.view.LayoutName.MainView;

public abstract class AbstractController<T> {

    protected AppController appController;

    public abstract void setAppController(AppController appController);

    public abstract void setData(T data);

    public void handleBackAction(ActionEvent event) {
        appController.showPane(null, MainView);
    }
}
