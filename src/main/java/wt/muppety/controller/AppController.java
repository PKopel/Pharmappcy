package wt.muppety.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wt.muppety.model.User;
import wt.muppety.presenter.EditUserDialogPresenter;

import java.io.IOException;

public class AppController {
    private final Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showMainViewPane() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../../../view/MainViewPane.fxml"));
            BorderPane rootLayout = loader.load();

            MainViewController controller = loader.getController();
            controller.setAppController(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showUserListPane() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../../../view/UserListPane.fxml"));
            BorderPane userListLayout = loader.load();

            Scene scene = new Scene(userListLayout);
            primaryStage.setScene(scene);

            UserListController controller = loader.getController();
            controller.setAppController(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProductListPane() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../../../view/ProductListPane.fxml"));
            BorderPane productListLayout = loader.load();

            Scene scene = new Scene(productListLayout);
            primaryStage.setScene(scene);

            ProductListController controller = loader.getController();
            controller.setAppController(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditUserDialog(User user, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../../../view/EditUserDialog.fxml"));
            BorderPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setTitle(title);

            EditUserDialogPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setData(user);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
