package wt.muppety.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.model.Position;
import wt.muppety.model.User;

public class EditUserDialogPresenter extends AbstractDialogPresenter<User> {

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

    @Override
    protected void updateModel() {
        //TODO
    }

    @Override
    protected void updateControls() {
        //TODO
    }
}
