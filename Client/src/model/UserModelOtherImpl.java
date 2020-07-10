package model;

import networking.UserClient;
import sharedClasses.Container;
import sharedClasses.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserModelOtherImpl implements UserModel
{
  private User systemUser;
  private User editingUser;
  private int systemUserID;
  private UserClient client;


  public UserModelOtherImpl(UserClient client)
  {
    this.client = client;
    client.addListener("incomingUser",this::receiveUser);
  }
  public void receiveUser(PropertyChangeEvent propertyChangeEvent)
  {
    User tmpUser = (User) ((Container)propertyChangeEvent.getNewValue()).getObject();
    if()
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
