package wt.muppety.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;

public class LoginDialogPresenter extends AbstractDialogPresenter<LoginData> {

    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    private Label errorLabel;

    @Override
    public void setStage(Stage stage) {
        super.setStage(stage);
        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    @Override
    protected void updateModel() {
        data.setLogin(loginTextField.getText());
        data.setPassword(passwordTextField.getText());
    }


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

    public void handleRegisterAction(ActionEvent actionEvent) {
    }
}
