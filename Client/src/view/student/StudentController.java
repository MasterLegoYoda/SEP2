package view.student;

import core.ViewHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.function.UnaryOperator;

public class StudentController{
    public Label studentIDLabel;
    public TableView materialsTable;
    public TableColumn materialTableColumn;
    public TableColumn pricePerUnitTableColumn;
    public TableColumn totalPriceColumn;
    public TableColumn amountColumn;
    public TextField addMaterialAmountTextField;
    public ComboBox addMaterialComboBox;
    public Label totalDebtLabel;
    private ViewHandler viewHandler;
    private StudentViewModel studentViewModel;
    private BooleanProperty update;
    
    public void init(ViewHandler viewHandler, StudentViewModel studentViewModel)
    {
        this.viewHandler = viewHandler;
        this.studentViewModel = studentViewModel;
        studentIDLabel.textProperty().bindBidirectional(studentViewModel.studentIDProperty());
        totalDebtLabel.textProperty().bindBidirectional(studentViewModel.totalDebtProperty());
        addMaterialComboBox.setItems(studentViewModel.getMaterialsCB());
        update = new SimpleBooleanProperty();
        update.bindBidirectional(studentViewModel.updaterProperty());
        UnaryOperator<TextFormatter.Change> filter = change ->
        {
            String tmpStr = change.getText();
            if (tmpStr.matches("[0-9]*")&&(tmpStr.length() <= 6))
            {
                return change;
            }
            return null;
        };
        TextFormatter<String> fieldFormat = new TextFormatter<>(filter);
        addMaterialAmountTextField.setTextFormatter(fieldFormat);
        materialTableColumn = new TableColumn<TableMaterial,String>("MATERIAL");
        materialTableColumn.setCellValueFactory(new PropertyValueFactory<TableMaterial,String>("name"));
        pricePerUnitTableColumn = new TableColumn<TableMaterial,String>("PRICE PER UNIT");
        pricePerUnitTableColumn.setCellValueFactory(new PropertyValueFactory<TableMaterial,String>("pricePerUnit"));
        totalPriceColumn = new TableColumn<TableMaterial,String>("TOTAL PRICE");
        totalPriceColumn.setCellValueFactory(new  PropertyValueFactory<TableMaterial,String>("totalPrice"));
        amountColumn = new TableColumn<TableMaterial,String>("AMOUNT");
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableMaterial,String>("usedAmount"));
        materialsTable.getColumns().addAll(materialsTable,pricePerUnitTableColumn,amountColumn,totalPriceColumn);
        materialsTable.setItems(studentViewModel.getTableMaterials());
        update.bindBidirectional(studentViewModel.updaterProperty());
        //if th table update doesnt work uncomment
        /*
        update.addListener(new ChangeListener<Boolean>()
        {
            @Override public void changed(
                ObservableValue<? extends Boolean> observableValue,
                Boolean aBoolean, Boolean t1)
            {
                updateTable();
            }
        });
         */

    }

    public void addMaterial(ActionEvent actionEvent)
    {
        if(!(addMaterialAmountTextField.textProperty() == null||addMaterialAmountTextField.getText().equalsIgnoreCase("")||addMaterialComboBox.getSelectionModel().isEmpty()))
        {
            String str = (String)addMaterialComboBox.getValue();
            int x = Integer.valueOf(addMaterialAmountTextField.getText());
            if(x>0)
            {
                studentViewModel.addMaterial(str,x);
            }
        }
    }

    public void cancelAdditionOfMaterial(ActionEvent actionEvent)
    {
        addMaterialComboBox.getSelectionModel().clearSelection();
        addMaterialAmountTextField.clear();
    }
    private void updateTable()
    {
        materialsTable.getItems().addAll(studentViewModel.getUpdatedTableInfo());
    }
}
