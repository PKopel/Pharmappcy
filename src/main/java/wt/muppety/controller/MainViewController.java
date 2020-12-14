package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import wt.muppety.authentication.Authenticator;
import wt.muppety.authentication.LoginData;
import wt.muppety.model.MockData;
import wt.muppety.model.Transaction;
import wt.muppety.model.Product;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.TransactionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.collections.FXCollections;

import static wt.muppety.authentication.Permission.canBrowseDB;
import static wt.muppety.authentication.Permission.canModerateDB;
import static wt.muppety.view.LayoutName.*;

public class MainViewController implements IController<Void> {

    @FXML
    public Button employeeListButton;
    @FXML
    public Button productListButton;
    @FXML
    public Button loginButton;

    @FXML
    public Button addTransaction;
    private AppController appController;
    private ObservableList<Transaction> data = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        Authenticator.guardButton(employeeListButton,canBrowseDB);
        Authenticator.guardButton(productListButton,canBrowseDB);
        Authenticator.guardButton(addTransaction,canModerateDB);
        loginButton.managedProperty().bind(Bindings.createBooleanBinding(Authenticator::isLoggedIn));
        loginButton.visibleProperty().bind(Bindings.createBooleanBinding(Authenticator::isLoggedIn));
    }

    public void handleEmployeeListAction(ActionEvent event) {
        appController.showPane(MockData.employees, EmployeeList);
    }

    public void handleProductListAction(ActionEvent event) {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        appController.showPane(products, ProductList);
        // appController.showPane(MockData.products, ProductList);
    }

    public void handleAddTransactionAction(ActionEvent event) {
        System.out.println("handle add transaction action aCtiVaTEd");
        Transaction newTransaction = new Transaction();
        if (appController.showDialog(newTransaction, EditTransaction, "Add transaction")) {
            data.add(newTransaction);
            TransactionDao transactionDao = new TransactionDao();
            Optional<Transaction> transaction = transactionDao.create(newTransaction);
        }
    }

    public void handleLogin(ActionEvent event) {
        LoginData data = new LoginData();
        if(this.appController.showDialog(data,Login,"Sign in")){
            Authenticator.logIn(data);
            this.initialize();
        }

    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(Void data) {
// operation ignored
    }
}
