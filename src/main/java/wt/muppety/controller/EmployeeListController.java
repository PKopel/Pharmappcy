package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import wt.muppety.authentication.Authenticator;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;

import java.util.ArrayList;
import java.util.Optional;

import static wt.muppety.authentication.Permission.canModerateDB;
import static wt.muppety.view.LayoutName.EditUser;

/**
 * Controller for layout presenting employee list.
 * Allows to add, remove and edit employee entries in system's database.
 */
public class EmployeeListController extends AbstractController<ObservableList<Employee>> {

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
    @FXML
    private TextField filterField;
    private ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();
        employeeTable.setItems(employees);


        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        FilteredList<Employee> filteredData = new FilteredList<>(employees, e -> true);
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                String[] split = lowerCaseFilter.split(" ");
                for (String part : split) {
                    if (!employee.toString().toLowerCase().contains(part))
                        return false;
                }
                return true;
            });
            employeeTable.setItems(sortedData);
        });

        employeeTable.setItems(sortedData);
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());

        Authenticator.guardControl(addButton, canModerateDB);
        Authenticator.guardControl(editButton, canModerateDB);
        Authenticator.guardControl(deleteButton, canModerateDB);

        deleteButton.disableProperty().bind(Bindings.isEmpty(employeeTable.getSelectionModel().getSelectedItems()));
        editButton.disableProperty().bind(Bindings.size(employeeTable.getSelectionModel().getSelectedItems()).isNotEqualTo(1));
    }

    public void handleDeleteAction(ActionEvent event) {
        EmployeeDao employeeDao = new EmployeeDao();
        boolean deleted;
        for (Employee employee : new ArrayList<>(employeeTable.getSelectionModel().getSelectedItems())) {
            deleted = employeeDao.deleteById(Employee.class, employee.getId());
            if (!deleted) System.out.println("Error while deleting " + employee);
        }
        data.removeAll(employeeTable.getSelectionModel().getSelectedItems());
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
            EmployeeDao employeeDao = new EmployeeDao();
            Optional<Employee> employee = employeeDao.create(newEmployee);
            data.add(employee.orElseThrow());
        }
    }

    @Override
    public void setData(ObservableList<Employee> data) {
        this.data = data;
        employeeTable.setItems(data);
    }
}
