package wt.muppety.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.TransactionDao;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;
import wt.muppety.model.Transaction;

import java.util.Optional;

import static wt.muppety.authentication.Permission.canBrowseDB;
import static wt.muppety.authentication.Permission.canModerateDB;
import static wt.muppety.view.LayoutName.*;

public class MainViewController extends AbstractController<Void> {

    private final ObservableList<Transaction> data = FXCollections.observableArrayList();
    @FXML
    public Button employeeListButton;
    @FXML
    public Button productListButton;
    @FXML
    public Button addTransaction;
    @FXML
    public Label loginLabel;
    @FXML
    public Label nameLabel;
    @FXML
    public Label positionLabel;

    @FXML
    private void initialize() {
        Authenticator.guardControl(employeeListButton, canBrowseDB);
        Authenticator.guardControl(productListButton, canBrowseDB);
        Authenticator.guardControl(addTransaction, canModerateDB);
        Employee currentEmployee = Authenticator.getInstance().getCurrentUser();
        if (currentEmployee != null) {
            loginLabel.setText(currentEmployee.getLogin());
            nameLabel.setText(currentEmployee.getFirstname() + " " + currentEmployee.getLastname());
            positionLabel.setText(currentEmployee.getPosition().name());
        }
    }

    public void handleEmployeeListAction(ActionEvent event) {
        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();
        appController.showPane(employees, EmployeeList);
    }

    public void handleProductListAction(ActionEvent event) {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        appController.showPane(products, ProductList);
    }

    public void handleAddTransactionAction(ActionEvent event) {
        Transaction newTransaction = new Transaction();
        if (appController.showDialog(newTransaction, EditTransaction, "Add transaction")) {
            data.add(newTransaction);
            TransactionDao transactionDao = new TransactionDao();
            Optional<Transaction> transaction = transactionDao.create(newTransaction);
        }
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        appController.showPane(products, ProductList);
    }

    public void handleLogoutAction(ActionEvent event) {
        Authenticator.getInstance().logOut();
        appController.showPane(new LoginData(), LoginView);
    }

    @Override
    public void setData(Void data) {
        //IGNORED
    }
}
