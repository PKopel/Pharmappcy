package wt.muppety;

import javafx.application.Application;
import javafx.stage.Stage;
import wt.muppety.controller.AppController;
import wt.muppety.dao.*;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;
import wt.muppety.model.Supplier;

import java.time.LocalDateTime;

import static wt.muppety.view.LayoutName.MainView;

public class App extends Application {

    private Stage primaryStage;
    private AppController appController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pharmacy app");

        this.appController = new AppController(primaryStage);
        this.appController.showPane(null, MainView);
    }
}
