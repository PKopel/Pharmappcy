package wt.muppety.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import wt.muppety.model.MockData;
import wt.muppety.model.Transaction;
import wt.muppety.model.Product;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import wt.muppety.dao.TransactionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static wt.muppety.view.LayoutName.*;

import static wt.muppety.view.LayoutName.EmployeeList;
import static wt.muppety.view.LayoutName.ProductList;
import javafx.collections.FXCollections;

public class MainViewController implements IController<Void> {

    @FXML
    public Button employeeListButton;
    @FXML
    public Button productListButton;
    @FXML
    public Button addTransaction;
    private AppController appController;
    private ObservableList<Transaction> data = FXCollections.observableArrayList();


    public void handleEmployeeListAction(ActionEvent event) {
        appController.showPane(MockData.employees, EmployeeList);
    }

    public void handleProductListAction(ActionEvent event) {
        appController.showPane(MockData.products, ProductList);
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

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(Void data) {
// operation ignored
    }
}
