package wt.muppety.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wt.muppety.presenter.AbstractDialogPresenter;
import wt.muppety.session.SessionService;
import wt.muppety.view.LayoutName;

import java.io.IOException;

/**
 * Main class responsible for loading GUI layouts.
 */
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
            Pane rootLayout = loader.load();

            AbstractController<T> controller = loader.getController();
            controller.setAppController(this);
            controller.setData(data);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(event -> {
                SessionService.closeSession();
                System.exit(0);
            });
            primaryStage.setMaximized(layoutName.isMaximized());
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
     * @param <T>        Type of data required by layout controller
     * @param data       Data required by layout controller
     * @param layoutName Enum LayoutName associated with .fxml file to display
     * @param title      Title for dialog window
     * @return true if presenter accepts data provided in dialog, false otherwise
     */
    public <T> boolean showDialog(T data, LayoutName layoutName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource(pathToResources + layoutName.getPath()));
            Pane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setTitle(title);
            dialogStage.setMaximized(layoutName.isMaximized());

            AbstractDialogPresenter<T> presenter = loader.getController();
            presenter.setAppController(this);
            presenter.setStage(dialogStage);
            presenter.setData(data);

            dialogStage.showAndWait();
            return presenter.isAccepted();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
