package wt.muppety.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wt.muppety.model.Category;

public class EditCategoryDialogPresenter extends AbstractDialogPresenter<Category> {

    @FXML
    public TextField nameTextField;

    @Override
    protected void updateModel() {
        data.setName(nameTextField.getText());
    }

    @Override
    protected void updateControls() {
        //IGNORE
    }
}
