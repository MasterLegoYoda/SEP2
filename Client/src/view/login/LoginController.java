package view.login;

import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class LoginController
{
  public Label errorLabel;
  public TextField passwordTextField;
  public TextField viaIDTextField;
  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;
  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel)
  {
    this.viewHandler = viewHandler;
    this.loginViewModel = loginViewModel;
    errorLabel.textProperty().bindBidirectional(loginViewModel.errorProperty());
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
    viaIDTextField.setTextFormatter(fieldFormat);
  }
  public void attemptLogin(ActionEvent actionEvent)
  {
    if(!(viaIDTextField.getText().length() == 0 || passwordTextField.getText().length() == 0  ))
    {
      int id = Integer.parseInt(viaIDTextField.getText());
      loginViewModel.submitLoginInfo(id, passwordTextField.getText());
    }
    else
    {
      errorLabel.setText("please enter a valid value");
    }
  }
}
