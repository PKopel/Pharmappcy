package wt.muppety.presenter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import wt.muppety.authentication.Authenticator;
import wt.muppety.dao.ProductDao;
import wt.muppety.model.Product;
import wt.muppety.model.Transaction;

import java.time.LocalDateTime;

public class EditTransactionDialogPresenter extends AbstractDialogPresenter<Transaction> {

    @FXML
    public ComboBox<Product> productComboBox;
    @FXML
    public TextField quantityTextField;

// Product product, Employee employee, int quantity, double value, LocalDateTime datetime

    @FXML
    private void initialize() {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        productComboBox.setItems(products);
    }


    @Override
    protected void updateModel() {
        data.setQuantity(Integer.parseInt(quantityTextField.getText()));
        Product product = productComboBox.getValue();
        data.setProduct(product);
        data.setDatetime(LocalDateTime.now());
        data.setValue(product.getUnitPrice() * data.getQuantity());
        data.setEmployee(Authenticator.getInstance().getCurrentUser());
    }

    @Override
    protected void updateControls() {
        //IGNORE
    }
}
