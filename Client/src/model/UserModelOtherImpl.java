package model;

import networking.UserClient;
import sharedClasses.Container;
import sharedClasses.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserModelOtherImpl implements UserModel
{
  private User systemUser;
  private User editingUser;
  private int systemUserID;
  private UserClient client;
  private PropertyChangeSupport support;

  public UserModelOtherImpl(UserClient client)
  {
    support = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener("incomingUser",this::receiveUser);
  }
  public void receiveUser(PropertyChangeEvent propertyChangeEvent)
  {
    User tmpUser = (User) ((Container)propertyChangeEvent.getNewValue()).getObject();
    //if()
  }
  @Override public void addListener(String eventName, PropertyChangeListener listener)
{
  support.addPropertyChangeListener(eventName, listener);
}

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override
  public void transmitUser(User user) {

  }

  @Override
  public void requestUser(int id) {

  }

  @Override
  public void cancelEditing() {

  }
}
