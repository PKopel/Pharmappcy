package wt.muppety.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MockData {

    public static ObservableList<Employee> employees = FXCollections.observableArrayList();
    public static ObservableList<Product> products = FXCollections.observableArrayList();
    public static ObservableList<String> positions = FXCollections.observableArrayList("Manager", "Chair", "Worker");
    public static ObservableList<Category> categories = FXCollections.observableArrayList();
    public static ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
}

