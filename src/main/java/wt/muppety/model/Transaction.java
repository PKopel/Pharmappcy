package wt.muppety.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = Transaction.TABLE_NAME)
public class Transaction {

    public static final String TABLE_NAME = "Tranzaction";

    public Transaction(Product product, Employee employee, int quantity, double value, LocalDateTime datetime){
        this.product = product;
        this.employee = employee;
        this.quantity = quantity;
        this.value = value;
        this.datetime = datetime;
        
    }

    public Transaction(){}

    public int getId(){
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


    public String getFormattedDatetime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return this.datetime.format(formatter);
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employee employee;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;
    @Column(name="quantity", nullable = false)
    private int quantity;
    @Column(name="value", nullable = false)
    private double value;
    @Column(name="datetime", nullable = false)
    private LocalDateTime datetime;

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
}
