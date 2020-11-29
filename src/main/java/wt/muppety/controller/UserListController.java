package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wt.muppety.model.User;

import java.util.Collection;

import static wt.muppety.view.LayoutName.EditUser;
import static wt.muppety.view.LayoutName.MainView;

public class UserListController implements IController<Collection<User>> {

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
    private AppController appController;

    public void handleDeleteAction(ActionEvent event) {
        //TODO
    }

    public void handleEditAction(ActionEvent event) {
        User user = userTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            appController.showDialog(user, EditUser, "Edit user");
        }
    }

    public void handleAddAction(ActionEvent event) {
        appController.showDialog(new User(), EditUser, "Add user");
    }

    public void handleBackAction(ActionEvent event) {
        appController.showPane(null, MainView);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(Collection<User> data) {

    }
}
