package wt.muppety.controller;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import wt.muppety.Util.StringPair;
import wt.muppety.dao.ProductDao;
import wt.muppety.dao.TransactionDao;
import wt.muppety.model.Product;
import wt.muppety.model.Transaction;

import java.util.HashMap;

public class StatisticsListControler  extends AbstractController<ObservableList<StringPair>> {
    @FXML
    public TableView<StringPair> statisticsTable;
    @FXML
    public TableColumn<StringPair,String> nameColumn;
    @FXML
    public TableColumn<StringPair,String> valueColumn;

    private ObservableList<StringPair> data;

    @FXML
    private void initialize() {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        TransactionDao transactionDao = new TransactionDao();
        ObservableList<Transaction> transactions = transactionDao.listAll();

        HashMap<Product,Integer> stats = new HashMap<>();
        for(Product p : products){
            stats.put(p,0);
        }
        for(Transaction t : transactions){
            if(stats.containsKey(t.getProduct())) stats.put(t.getProduct(),stats.get(t.getProduct())+t.getQuantity());
        }

        data = FXCollections.observableArrayList(stats.keySet().stream().map(x->new StringPair(x.getName(),stats.get(x).toString())).toArray(StringPair[]::new));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("second"));

        statisticsTable.setItems(data);
    }
}