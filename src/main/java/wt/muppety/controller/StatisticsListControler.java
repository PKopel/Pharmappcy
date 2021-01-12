package wt.muppety.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.TransactionDao;
import wt.muppety.model.Product;
import wt.muppety.model.Transaction;

import java.util.HashMap;

public class StatisticsListControler  extends AbstractController<ObservableList<javafx.util.Pair<String,String>>> {
    @FXML
    public TableView<javafx.util.Pair<String,String>> statisticsTable;
    @FXML
    public TableColumn<javafx.util.Pair<String,String>,String> nameColumn;
    @FXML
    public TableColumn<javafx.util.Pair<String,String>,String> valueColumn;

    @FXML
    private void initialize() {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        TransactionDao transactionDao = new TransactionDao();
        ObservableList<Transaction> transactions = transactionDao.listAll();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("second"));
        HashMap<Product,Integer> stats = new HashMap<Product, Integer>();
        ObservableList<javafx.util.Pair<String,String>> data;
        for(Product p : products){
            stats.put(p,0);
        }
        for(Transaction t : transactions){
            if(stats.containsKey(t.getProduct())) stats.put(t.getProduct(),stats.get(t.getProduct())+t.getQuantity());
        }

        data = FXCollections.observableArrayList(stats.keySet().stream().map(x->new Pair<>(x.getName(),stats.get(x).toString())).toArray(Pair[]::new));

        statisticsTable.setItems(data);
    }
}
