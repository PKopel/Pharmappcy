package wt.muppety.presenter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import wt.muppety.authentication.Authenticator;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;
import wt.muppety.notificator.EmailNotificator;


public class EditRecommendationDialogPresenter extends AbstractDialogPresenter<Product> {

    @FXML
    public TextArea messageTextArea;
    @FXML
    public ComboBox<Employee> employeeComboBox;

    @FXML
    private void initialize() {
        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();
        employeeComboBox.setItems(employees);
    }

    @Override
    protected void updateModel() {
        Employee receiver = employeeComboBox.getValue();
        Employee sender = Authenticator.getInstance().getCurrentUser();
        String subject = "Product recommendation from " + sender;
        String text = messageTextArea.getText();
        if (receiver.getEmail() != null)
            EmailNotificator.getInstance().sendEmail(receiver.getEmail(), subject, text);
    }

    @Override
    protected void updateControls() {
        String text = "Check this out: " + data;
        messageTextArea.setText(text);
    }
}
