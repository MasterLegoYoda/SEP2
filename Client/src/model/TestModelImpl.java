package model;

import networking.SocketClient;
import sharedClasses.AuthPack;
import sharedClasses.Container;
import sharedClasses.MaterialList;
import sharedClasses.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TestModelImpl implements UserModel,LoginModel
{
  private PropertyChangeSupport support;
  private MaterialList materialList;
  private User systemUser;
  private User editingUser;
  private int id;
  private boolean isLoggedIn;
  private boolean[] views;
  private SocketClient client;
  public TestModelImpl(SocketClient client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    client.addListener("Login results",this::loginResults);
    client.addListener("incomingMaterialsList",this::saveMaterialsList);
    client.addListener("incomingUser",this::receiveUser);
    client.addListener("UserNotFound",this::userRequestResults);
    isLoggedIn = false;
    views = new boolean[] {false, false, false, false};
  }
  @Override public void requestUser(int id)
  {
    Integer tmpInteger = id;
    client.requestUserById(tmpInteger);
  }
  public void userRequestResults(PropertyChangeEvent propertyChangeEvent)
  {
    if(systemUser.getStatus() == 1)
    {
      support.firePropertyChange("userNotFoundStudentEmployee",null,"User not found");
    }
    else if(systemUser.getStatus() == 2)
    {
      support.firePropertyChange("userNotFoundVIAShopEmployee",null,"User not found");
    }
    else if(systemUser.getStatus() == 3)
    {
      support.firePropertyChange("userNotFoundAdmin",null,"User not found");
    }
  }
  public void loginResults(PropertyChangeEvent propertyChangeEvent)
  {
    String str = (String) ((Container)propertyChangeEvent.getNewValue()).getObject();
    if(str.equalsIgnoreCase("Login Successful"))
    {
      if(id == 0)
      {
        support.firePropertyChange("openAdministratorView",null,null);
      }
      isLoggedIn = true;
    }
    else if(str.equalsIgnoreCase("Wrong Credentials"))
    {
      support.firePropertyChange("Wrong-Credentials",null,str);
    }
  }
  @Override public void cancelEditing()
  {
    editingUser = null;
  }
  public void receiveUser(PropertyChangeEvent propertyChangeEvent)
  {
   if(isLoggedIn)
   {
     User tmpUser = (User) ((Container)propertyChangeEvent.getNewValue()).getObject();
     if(tmpUser.getID() == id)
     {
       systemUser = tmpUser;
       if(systemUser.getStatus() == 0)
       {
         support.firePropertyChange("openStudentView",null,null);
         support.firePropertyChange("userToStudentViewModel",null,systemUser);
         //get student view
       }
       else if(systemUser.getStatus() == 1)
       {
         support.firePropertyChange("openStudentEmployeeView",null,null);
         support.firePropertyChange("userToStudentEmployeeViewModel",null,systemUser);
       }
       else if(systemUser.getStatus() ==2)
       {
         support.firePropertyChange("openVIAshopEmployeeView",null,null);
         support.firePropertyChange("userToViaShopEmployeeViewModel",null,systemUser);
       }
     }
     else
     {
       editingUser = tmpUser;
       if(systemUser.getStatus() == 1)
       {
         support.firePropertyChange("updateEditingUserStudentEmployee",null,editingUser);
       }
       else if(systemUser.getStatus() == 2)
       {
         support.firePropertyChange("updateEditingUserVIAShopEmployee",null,editingUser);
       }
       else if( id == 0)
       {
         support.firePropertyChange("updateEditingUserAdmin",null,editingUser);
       }
       //support.firePropertyChange("updateEditingUser",null,editingUser);
     }
   }
  }
  public void saveMaterialsList(PropertyChangeEvent propertyChangeEvent)
  {
    materialList = (MaterialList)((Container)propertyChangeEvent.getNewValue()).getObject();
    if(systemUser.getStatus() == 0)
    {
      support.firePropertyChange("studentMaterialsList",null,materialList);
      //get student view
    }
    else if(systemUser.getStatus() == 1)
    {
      support.firePropertyChange("studentEmployeeMaterialsList",null,materialList);
    }
    else if(systemUser.getStatus() ==2)
    {
      support.firePropertyChange("viaShopEmployeeMaterialsList",null,materialList);
    }
  }
  @Override public void transmitUser(User user)
  {
    if(isLoggedIn)
    {
      if(user.getID() == systemUser.getID())
      {
        systemUser = user;
      }
      else if(user.getID() == editingUser.getID())
      {
        editingUser = user;
      }
      else
      {
        //this is for later, if we decide to implement some other stuff
        editingUser = user;
      }
      client.transmitUser(user);
    }
  }
  /*
  @Override public void transmitCMD(String str)
  {
    client.transmitCMD(str);
  }

   */
  @Override public void transmitCredentials(int id, String password)
  {
    AuthPack authPack = new AuthPack(id,password);
    client.transmitLoginInfo(authPack);
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
