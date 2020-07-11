package view.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModel;
import model.TestModelImpl;

import java.beans.PropertyChangeEvent;

public class LoginViewModel
{
  private StringProperty error;
  private LoginModel loginModel;
  public LoginViewModel(LoginModel model)
  {
    loginModel = model;
    loginModel.addListener("Wrong-Credentials",this::displayNegativeLoginResult);
    error = new SimpleStringProperty("");
  }
  public void displayNegativeLoginResult(PropertyChangeEvent propertyChangeEvent)
  {
    String tmp = propertyChangeEvent.getNewValue().toString();
    error.setValue(tmp);
  }
  public void submitLoginInfo(int id, String password)
  {
    if(String.valueOf(id).length() == 6)
    {
      error.setValue("");
      loginModel.transmitCredentials(id,password);
    }
    else
    {
      error.setValue("Please enter a correct ID value");
    }
  }
  public String getError()
  {
    return error.get();
  }

  public StringProperty errorProperty()
  {
    return error;
  }
}
