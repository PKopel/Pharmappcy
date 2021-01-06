package wt.muppety;

import javafx.application.Application;
import javafx.stage.Stage;
import wt.muppety.authentication.LoginData;
import wt.muppety.controller.AppController;

import static wt.muppety.view.LayoutName.LoginView;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Pharmacy app");

        AppController appController = new AppController(primaryStage);
        appController.showPane(new LoginData(), LoginView);
    }
}
