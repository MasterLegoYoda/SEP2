package view.viaShopEmployee;

import core.ViewHandler;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class ViaShopEmployeeController
{
  public TextField studentIDTextField;
  public Button confirmButton;
  public Label errorLabel;
  public Button clearDebtButton;
  public Button cancelButton;
  public Label studentIDLabel;
  public Label studentDebtLabel;
  private BooleanProperty isLoaded;
  private ViewHandler viewHandler;
  private ViaShopEmployeeViewModel viaShopEmployeeViewModel;

  public void init(ViewHandler vh, ViaShopEmployeeViewModel shopviewModel)
  {
    viewHandler = vh;
    viaShopEmployeeViewModel = shopviewModel;
    isLoaded.bindBidirectional(viaShopEmployeeViewModel.getIsLoaded());
    studentDebtLabel.textProperty().bindBidirectional(viaShopEmployeeViewModel.getDebt());
    studentIDLabel.textProperty().bindBidirectional(viaShopEmployeeViewModel.getID());
    errorLabel.textProperty().bindBidirectional(viaShopEmployeeViewModel.getError());
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
    studentIDTextField.setTextFormatter(fieldFormat);
  }
  public void confirmLoadStudent(ActionEvent actionEvent)
  {
    if(isLoaded.getValue().booleanValue())
    {
      if(!( studentIDTextField.textProperty() == null &&studentIDTextField.getText().length() == 0))
      {
        int a = Integer.valueOf(studentIDTextField.getText());
        viaShopEmployeeViewModel.reqUser(a);
      }
    }
  }

  public void clearStudentDebt(ActionEvent actionEvent)
  {
    viaShopEmployeeViewModel.clearDebt();
    studentIDTextField.clear();
  }

  public void cancel(ActionEvent actionEvent)
  {
    studentIDTextField.clear();
    viaShopEmployeeViewModel.clear();
  }
}
