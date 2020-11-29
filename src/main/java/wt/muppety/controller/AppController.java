package wt.muppety.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wt.muppety.presenter.AbstractDialogPresenter;
import wt.muppety.view.LayoutName;

import java.io.IOException;

public class AppController {
    private final String pathToResources = "../../../";

    private final Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Generic function responsible for displaying a window with layout corresponding to
     * argument layoutName. Loads layout defined in .fxml file and initializes its controller,
     * then shows it in application window.
     *
     * @param data       Data required by layout controller
     * @param layoutName Enum LayoutName associated with .fxml file to display
     * @param <T>        Type of data required by layout controller
     */
    public <T> void showPane(T data, LayoutName layoutName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource(pathToResources + layoutName.getPath()));
            BorderPane rootLayout = loader.load();

            IController<T> controller = loader.getController();
            controller.setAppController(this);
            controller.setData(data);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generic function responsible for displaying a dialog with layout corresponding to
     * argument layoutName. Loads layout defined in .fxml file and initializes its controller,
     * then shows it in dialog window.
     *
     * @param data       Data required by layout controller
     * @param layoutName Enum LayoutName associated with .fxml file to display
     * @param title      Title for dialog window
     * @param <T>        Type of data required by layout controller
     */
    public <T> void showDialog(T data, LayoutName layoutName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource(pathToResources + layoutName.getPath()));
            BorderPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setTitle(title);

            AbstractDialogPresenter<T> presenter = loader.getController();
            presenter.setStage(dialogStage);
            presenter.setData(data);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
