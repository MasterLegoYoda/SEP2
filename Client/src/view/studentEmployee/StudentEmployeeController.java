package view.studentEmployee;

import core.ViewHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.student.StudentViewModel;
import view.student.TableMaterial;

import java.util.function.UnaryOperator;

public class StudentEmployeeController
{
  public TextField studentIDTextField;
  public Label studentIDLabel;
  public TableView materialsTableView;
  public TableColumn materialColumn;
  public TableColumn amountColumn;
  public TextField addMaterialTextField;
  public ComboBox addMaterialComboBox;
  public CheckBox laserCutterLicenceCheckBox;
  public CheckBox threeDimensionalPrinterCheckBox;
  public Label errorLabel;
  private ViewHandler viewHandler;
  private StudentEmployeeViewModel studentEmployeeViewModel;
  private BooleanProperty update;
  public void init(ViewHandler viewHandler, StudentEmployeeViewModel studentEmployeeViewModel)
  {
        this.viewHandler = viewHandler;
        this.studentEmployeeViewModel = studentEmployeeViewModel;
        studentIDLabel.textProperty().bindBidirectional(studentEmployeeViewModel.editingStudentIDProperty());
        errorLabel.textProperty().bindBidirectional(studentEmployeeViewModel.errorMSGProperty());
        addMaterialComboBox.setItems(studentEmployeeViewModel.getMaterialsCB());
        update = new SimpleBooleanProperty();
        update.bindBidirectional(studentEmployeeViewModel.updaterProperty());
        laserCutterLicenceCheckBox.selectedProperty().bindBidirectional(studentEmployeeViewModel.laserLicenceProperty());
        threeDimensionalPrinterCheckBox.selectedProperty().bindBidirectional(studentEmployeeViewModel.printerLicenceProperty());
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
        addMaterialTextField.setTextFormatter(fieldFormat);
        studentIDTextField.setTextFormatter(fieldFormat);
        materialColumn = new TableColumn<TableMaterial,String>("MATERIAL");
        amountColumn = new TableColumn<TableMaterial,String>("AMOUNT");
        materialColumn.setCellValueFactory(new PropertyValueFactory<TableMaterial,String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableMaterial,String>("usedAmount"));
        materialsTableView.getColumns().addAll(materialColumn,amountColumn);
        materialsTableView.setItems(studentEmployeeViewModel.getTableMaterials());
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

  public void changeLicence(ActionEvent actionEvent)
  {
    if(studentEmployeeViewModel.isIsLoaded())
    {
      studentEmployeeViewModel.updateEditingUserLicence(laserCutterLicenceCheckBox.isSelected(),threeDimensionalPrinterCheckBox.isSelected());
    }
  }

  public void addMaterial(ActionEvent actionEvent)
  {
    if(!(addMaterialTextField.textProperty() == null||addMaterialTextField.getText().equalsIgnoreCase("")||addMaterialComboBox.getSelectionModel().isEmpty()))
    {
      String str = (String)addMaterialComboBox.getValue();
      int x = Integer.valueOf(addMaterialTextField.getText());
      if(x>0)
      {
        studentEmployeeViewModel.addMaterialToEditingUser(str,x);
      }
    }
  }

  public void confirmStudentID(ActionEvent actionEvent)
  {
    if(!(studentIDTextField.textProperty() == null||studentIDTextField.getText().equals("")))
    {
      int x = Integer.parseInt(studentIDTextField.getText());
      studentEmployeeViewModel.requestUser(x);
    }
  }

  public void cancel(ActionEvent actionEvent)
  {
    studentIDTextField.clear();
    addMaterialTextField.clear();
    addMaterialComboBox.getSelectionModel().clearSelection();
    studentEmployeeViewModel.cancel();
    //updateTable();
  }
  private void updateTable()
  {
    materialsTableView.getItems().addAll(studentEmployeeViewModel.getUpdatedTableInfo());
  }
}
