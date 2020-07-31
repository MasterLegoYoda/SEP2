package model;

import networking.LoginClient;
import sharedClasses.AuthPack;
import sharedClasses.Container;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelImpl implements LoginModel
{
  private AuthPack authPack;
  private LoginClient client;
  private PropertyChangeSupport support;
  private int id;
  private int password;

  public LoginModelImpl(LoginClient client)
  {
    this.client = client;
    client.addListener("Login results",this::loginResults);
  }
  public void loginResults(PropertyChangeEvent propertyChangeEvent)
  {
    String str = (String) ((Container)propertyChangeEvent.getNewValue()).getObject();
    if(str.equalsIgnoreCase("Login Successful"))
    {
      if(id == 0)
      {
        support.firePropertyChange("activateAdmin",null,null);
      }
      else
      {
        Integer tmpInt = id;
        support.firePropertyChange("ActiveUserID",null,tmpInt);
      }
    }
    else if(str.equalsIgnoreCase("Wrong Credentials"))
    {
      support.firePropertyChange("Wrong Credentials",null,str);
    }
  }
  @Override public void transmitCredentials(int id, String password)
  {
    AuthPack temp = new AuthPack(id, password);
    client.transmitLoginInfo(temp);
  }
  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
