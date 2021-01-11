package wt.muppety.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.TransactionDao;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;
import wt.muppety.model.Transaction;
import java.util.Optional;
import java.util.Random;

import static wt.muppety.authentication.Permission.canBrowseDB;
import static wt.muppety.authentication.Permission.canModerateDB;
import static wt.muppety.view.LayoutName.*;

public class MainViewController extends AbstractController<Void> {

    private final String[] quotes = {
      "\"One could say medications are useless. Now he can't because he died of pneumonia.\"",
      "\"In case of a headache do as the bottle says - take a pill and keep away from children.\"",
      "\"If you see hallucinations listed twice as a side effect, you might want to contact your doctor.\"",
      "\"Some of our medicine actually tastes like raspberries. Although it's labeled as cherry-flavoured.\"",
      "\"Did you google that? Go see a doctor...\"",
    };

    private final Random random = new Random();

    private final ObservableList<Transaction> data = FXCollections.observableArrayList();
    @FXML
    public Button employeeListButton;
    @FXML
    public Button productListButton;
    @FXML
    public Button addTransactionButton;
    @FXML
    public Button changeSubscriptionButton;
    @FXML
    public Button logOutButton;
    @FXML
    public Label loginLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label positionLabel;

    @FXML
    public Pane subController;

    @FXML
    private void initialize() {
        Authenticator.guardControl(employeeListButton, canModerateDB);
        Authenticator.guardControl(productListButton, canBrowseDB);
        Authenticator.guardControl(addTransactionButton, canModerateDB);
        Employee currentEmployee = Authenticator.getInstance().getCurrentUser();
        if (currentEmployee != null) {
            loginLabel.setText(currentEmployee.getLogin());
            nameLabel.setText(currentEmployee.getFirstname() + " " + currentEmployee.getLastname());
            positionLabel.setText(currentEmployee.getPosition().name());
        }
        if (currentEmployee.getIsSubscribed() == true){
            changeSubscriptionButton.setText("Unsubscribe");
        }
        else {
            changeSubscriptionButton.setText("Subscribe");
        }

        randomQuote();
    }

    private void set_clicked(Button b){
        if (!b.equals(employeeListButton)) employeeListButton.getStyleClass().remove("clicked");
        if (!b.equals(addTransaction)) addTransaction.getStyleClass().remove("clicked");
        if (!b.equals(productListButton)) productListButton.getStyleClass().remove("clicked");
        b.getStyleClass().add("clicked");
    }

    private void randomQuote(){
        this.subController.getChildren().clear();
        Label l = new Label(quotes[random.nextInt(quotes.length)]);
        l.getStyleClass().add("quote");
        subController.getChildren().add(l);
    }

    public void handleEmployeeListAction(ActionEvent event) {
        set_clicked(employeeListButton);
        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();
        appController.showPane(employees, EmployeeList, subController);
    }

    public void handleProductListAction(ActionEvent event) {
        set_clicked(productListButton);
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        appController.showPane(products, ProductList, subController);
    }

    public void handleAddTransactionAction(ActionEvent event) {
        set_clicked(addTransaction);
        Transaction newTransaction = new Transaction();
        if (appController.showDialog(newTransaction, EditTransaction, "Add transaction")) {
            data.add(newTransaction);
            TransactionDao transactionDao = new TransactionDao();
            transactionDao.create(newTransaction);
        }
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        appController.showPane(products, ProductList, subController);
    }

    public void handleLogoutAction(ActionEvent event) {
        Authenticator.getInstance().logOut();
        appController.showPane(new LoginData(), LoginView);
    }

    public void handleChangeSubscriptionAction(ActionEvent event) {
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = Authenticator.getInstance().getCurrentUser();
        if (changeSubscriptionButton.getText().equals("Subscribe")){
            changeSubscriptionButton.setText("Unsubscribe");
            employeeDao.changeSubscription(employee);
        }
        else {
            changeSubscriptionButton.setText("Subscribe");
            employeeDao.changeSubscription(employee);
        }


    public void handleReturnAction(ActionEvent actionEvent) {
        this.subController.getChildren().clear();
    }

    @Override
    public void setData(Void data) {
        //IGNORED
    }

    public void handleReturnAction(ActionEvent actionEvent) {
        set_clicked(new Button());
        randomQuote();
    }
}
