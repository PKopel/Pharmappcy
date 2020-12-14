package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wt.muppety.authentication.Authenticator;
import wt.muppety.model.Employee;
import wt.muppety.dao.EmployeeDao;
import java.util.Optional;
import javafx.scene.control.cell.PropertyValueFactory;

import static wt.muppety.authentication.Permission.canModerateDB;
import static wt.muppety.view.LayoutName.EditUser;
import static wt.muppety.view.LayoutName.MainView;

public class EmployeeListController implements IController<ObservableList<Employee>> {

    @FXML
    public TableView<Employee> employeeTable;
    @FXML
    public TableColumn<Employee, String> firstNameColumn;
    @FXML
    public TableColumn<Employee, String> lastNameColumn;
    @FXML
    public TableColumn<Employee, String> positionColumn;
    @FXML
    public Button addButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button backButton;
    private AppController appController;
    private ObservableList<Employee> data;

    @FXML
    private void initialize() {

        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees =  employeeDao.listAll();
        employeeTable.setItems(employees);
        
        TableColumn firstNameColumn = new TableColumn("First name");
        TableColumn lastNameColumn = new TableColumn("Last name");
        TableColumn positionColumn = new TableColumn("Position");

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        employeeTable.setItems(employees);
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        employeeTable.getColumns().addAll(firstNameColumn);
        employeeTable.getColumns().addAll(lastNameColumn);
        employeeTable.getColumns().addAll(positionColumn);


        // employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // firstNameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getFirstname()));
        // lastNameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getLastname()));
        // positionColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getPosition()));

        Authenticator.guardButton(addButton, canModerateDB);
        Authenticator.guardButton(editButton, canModerateDB);
        Authenticator.guardButton(deleteButton, canModerateDB);

        deleteButton.disableProperty().bind(Bindings.isEmpty(employeeTable.getSelectionModel().getSelectedItems()));
        editButton.disableProperty().bind(Bindings.size(employeeTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
    }

    public void handleDeleteAction(ActionEvent event) {
        data.removeAll(employeeTable.getSelectionModel().getSelectedItems());
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.deleteById(Employee.class, employee.getId());
    
    }

    public void handleEditAction(ActionEvent event) {
        Employee user = employeeTable.getSelectionModel().getSelectedItem();
        EmployeeDao employeeDao = new EmployeeDao();
        if (user != null) {
            appController.showDialog(user, EditUser, "Edit user");
            employeeDao.update(user);
        }
        employeeTable.refresh();
    }

    public void handleAddAction(ActionEvent event) {
        Employee newEmployee = new Employee();
        if (appController.showDialog(newEmployee, EditUser, "Add user")) {
            data.add(newEmployee);
            EmployeeDao employeeDao = new EmployeeDao();
            Optional<Employee> employee = employeeDao.create(newEmployee);
        }

    }

    public void handleBackAction(ActionEvent event) {
        appController.showPane(null, MainView);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(ObservableList<Employee> data) {
        this.data = data;
        employeeTable.setItems(data);
    }
}
