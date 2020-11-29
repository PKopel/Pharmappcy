package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wt.muppety.model.User;

public class UserListController {

    private AppController appController;

    @FXML
    public TableView<User> userTable;

    @FXML
    public TableColumn firstNameColumn;

    @FXML
    public TableColumn lastNameColumn;

    @FXML
    public TableColumn positionColumn;

    @FXML
    public Button addButton;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button backButton;

    public void handleDeleteAction(ActionEvent event) {
        //TODO
    }

    public void handleEditAction(ActionEvent event) {
        User user = userTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            appController.showEditUserDialog(user, "Edit user");
        }
    }

    public void handleAddAction(ActionEvent event) {
        appController.showEditUserDialog(new User(), "Add user");
    }

    public void handleBackAction(ActionEvent event) {
        appController.showMainViewPane();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
