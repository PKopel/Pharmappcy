package wt.muppety.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wt.muppety.authentication.Authenticator;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;

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
    private ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();
        employeeTable.setItems(employees);

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        employeeTable.setItems(employees);
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        boolean deleted = employeeDao.deleteById(Employee.class, employee.getId());
        if (!deleted) System.out.println("Error while deleting " + employee);
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
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void setData(ObservableList<Employee> data) {
        this.data = data;
        employeeTable.setItems(data);
    }
}
