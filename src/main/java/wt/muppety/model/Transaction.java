package wt.muppety.model;


import java.time.LocalDateTime;

public class Transaction {

    private int id;
    private Employee employee;
    private Product product;
    private int quantity;
    private double value;
    private LocalDateTime datetime;

    public Transaction(Product product, Employee employee, int quantity, double value, LocalDateTime datetime) {
        this.product = product;
        this.employee = employee;
        this.quantity = quantity;
        this.value = value;
        this.datetime = datetime;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
