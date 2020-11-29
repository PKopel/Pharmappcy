package wt.muppety.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wt.muppety.model.Position;
import wt.muppety.model.User;

import java.text.ParseException;

public class EditUserDialogPresenter {

    @FXML
    public TextField firstNameTextField;

    @FXML
    public TextField lastNameTextField;

    @FXML
    public ComboBox<Position> positionComboBox;

    @FXML
    public TextField loginTextField;

    @FXML
    public TextField passwordTextField;

    private Stage dialogStage;

    private User user;

    private boolean approved;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(User user) {
        this.user = user;
        updateControls();
    }

    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        updateModel();
        approved = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        dialogStage.close();
    }

    private void updateModel() {
        //TODO
    }

    private void updateControls() {
        //TODO
    }
}
