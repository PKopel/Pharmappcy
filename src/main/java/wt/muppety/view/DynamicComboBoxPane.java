package wt.muppety.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DynamicComboBoxPane<T> extends GridPane {

    private final List<T> chosenItems = new LinkedList<>();
    private ObservableList<T> items;
    private int index;

    /**
     * Function generating new row containing a ComboBox and a Button.
     */
    public void insertRow() {
        //change indexes of children in "lower" rows
        this.getChildren().forEach(node -> {
            int row = getRowIndex(node);
            if (row >= index) {
                GridPane.setRowIndex(node, row + 1);
            }
        });

        //prepare Button
        Button add = new Button("+");
        add.setDisable(true);
        add.setOnAction(evt -> {
            insertRow();
            add.textProperty().setValue("--");
            add.setOnAction(ev -> {
                index -= 1;
                this.getChildren().removeIf(node -> getRowIndex(node).equals(getRowIndex(add)));
                this.getChildren().forEach(node -> {
                    int row = getRowIndex(node);
                    if (row >= getRowIndex(add)) {
                        GridPane.setRowIndex(node, row - 1);
                    }
                });
                chosenItems.remove((int) getRowIndex(add));
            });
        });

        //prepare ComboBox
        ComboBox<T> categoriesComboBox = new ComboBox<>(items);
        categoriesComboBox.setPrefWidth(500.0);
        categoriesComboBox.valueProperty().addListener((a, oldValue, newValue) -> {
            add.setDisable(categoriesComboBox.getValue() == null);
            chosenItems.set(getRowIndex(add), newValue);
        });

        //create new row
        this.addRow(index, categoriesComboBox, add);
        GridPane.setMargin(categoriesComboBox, new Insets(5, 5, 5, 0));

        chosenItems.add(index, null);
        index += 1;

    }

    public void setItems(ObservableList<T> items) {
        this.items = items;
    }

    public List<T> getChosenItems() {
        return chosenItems.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Setter for chosenItems updating displayed rows
     *
     * @param chosenItems items to be displayed
     */
    @SuppressWarnings("unchecked")
    public void setChosenItems(List<T> chosenItems) {
        chosenItems.forEach(item -> {
            List<Node> children = this.getChildren();
            Node box = children.get((index - 1) * 2);
            if (box instanceof ComboBox<?>)
                ((ComboBox<T>) box).getSelectionModel().select(item);
            Node button = children.get((index - 1) * 2 + 1);
            if (button instanceof Button) ((Button) button).fire();
        });
    }
}
