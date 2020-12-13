package wt.muppety.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;

public class LoginDialogPresenter extends AbstractDialogPresenter<LoginData> {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    private Label errorLabel;

    @Override
    protected void updateModel() {
        data.setLogin(loginTextField.getText());
        data.setPassword(passwordTextField.getText());

    }

    @Override
    @FXML
    protected void handleOkAction(ActionEvent event){
        updateModel();
        if(Authenticator.logIn(data)){
            stage.close();
            accepted = true;
        }
        else{
            errorLabel.setVisible(true);
        }

    }

    @Override
    protected void updateControls() {
        //IGNORE
    }
}
