package networking;

import database.Auth;
import database.InsertUser;
import database.LoadMaterial;
import database.LoadUser;
import sharedClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
//I know, I know, if a student updates their info in a higher access user is view it, it wont be updated
public class ServerSocketHandler implements Runnable
{
  private ConnectionPool connectionPool;
  private Socket socket;
  private ObjectOutputStream outToClient;
  private ObjectInputStream inFromClient;
  private Auth authenticator;
  private InsertUser insertUser;
  private LoadMaterial loadMaterial;
  private LoadUser loadUser;
  private User user;

  public ServerSocketHandler(Socket socket, ConnectionPool pool,
      Auth authenticator, InsertUser insertUser, LoadMaterial loadMaterial,
      LoadUser loadUser) throws IOException
  {
    this.connectionPool = pool;
    this.socket = socket;
    this.authenticator = authenticator;
    this.insertUser = insertUser;
    this.loadMaterial = loadMaterial;
    this.loadUser = loadUser;
    inFromClient = new ObjectInputStream(socket.getInputStream());
    outToClient = new ObjectOutputStream(socket.getOutputStream());
  }


  @Override public void run()
  {
    try
    {
      Container incomingContainer = (Container) inFromClient.readObject();


      switch (incomingContainer.getClassName())
      {
        case AuthPackage:
        {
          boolean infoCorrectness = false;
          AuthPack tmpAuth = (AuthPack)incomingContainer.getObject();
          int id = tmpAuth.getId();
          String password = tmpAuth.getPassword();
          try
          {
            infoCorrectness = authenticator.authenticate(id,password);
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
          try
          {
            if(infoCorrectness)
            {
              user = loadUser.loadUser(id);
              connectionPool.userJoin(user);
              connectionPool.addHandler(this);
              String tmpStr = "Login Successful";
              Container outContainer0 = new Container(tmpStr,ClassName.LoginResult);
              sendBackData(outContainer0);
              Container outContainer1 = new Container(user, ClassName.User);
              sendBackData(outContainer1);
              Container outContainer2 = new Container(loadMaterial.loadMaterial(),ClassName.MaterialList);
              sendBackData(outContainer2);
            }
            else if(!infoCorrectness)
            {
              String msg = "Wrong Credentials";
              Container wrongCreds = new Container(msg,ClassName.LoginResult);
            }
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
        }
        case User:
        {
          try
          {
            if(user.getStatus() == 1)
            {
                if(loadUser.loadUser(((User) incomingContainer.getObject()).getID()).getStatus() == ((User) incomingContainer.getObject()).getStatus())
                {
                  //I know I could have used one if statement, but i wanted to have them separate for possible bugs
                  if(((UserTransferVOneImpl)loadUser.loadUser(user.getID())).getLicences().equals(((UserTransferVOneImpl)incomingContainer.getObject()).getLicences()))
                  {
                    user = (User)incomingContainer.getObject();
                    insertUser.insertUser(user);
                    Container outContainer = new Container(user, ClassName.User);
                    sendBackData(outContainer);
                  }
                }
            }
            else if(user.getStatus() == 1)
            {
              if(loadUser.loadUser(((User) incomingContainer.getObject()).getID()).getStatus() == ((User) incomingContainer.getObject()).getStatus())
              {
                insertUser.insertUser((User)incomingContainer.getObject());
                connectionPool.updateOnlineUserInfo((User)incomingContainer.getObject());
              }
            }
            else if(user.getStatus() == 2)
            {
              if((loadUser.loadUser(((User) incomingContainer.getObject()).getID()).getStatus() == ((User) incomingContainer.getObject()).getStatus())&&(((UserTransferVOneImpl)loadUser.loadUser(user.getID())).getLicences().equals(((UserTransferVOneImpl)incomingContainer.getObject()).getLicences())))
              {
                insertUser.insertUser((User)incomingContainer.getObject());
                connectionPool.updateOnlineUserInfo((User)incomingContainer.getObject());
              }
            }
            else if(user.getStatus() == 3)
            {
              insertUser.insertUser((User)incomingContainer.getObject());
              connectionPool.updateOnlineUserInfo((User)incomingContainer.getObject());
            }
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
        }
        case UserRequest:
        {
          try
          {
            Integer tempID = (Integer) incomingContainer.getObject();
            if(loadUser.checkUserExistence(tempID.intValue()))
            {
              if(user.getStatus() == 1 || user.getStatus() == 2 || user.getStatus() == 3)
              {
                loadUser.loadUser(tempID.intValue());
                Container outContainer = new Container(loadUser.loadUser(tempID.intValue()), ClassName.UserRequest);
                sendBackData(outContainer);
              }
            }
            else
            {
              String str = "User not found";
              Container outContainer = new Container(str,ClassName.UserNotFound);
            }
          }
          catch (SQLException e)
          {
            e.printStackTrace();
          }
        }

//        case SQLC:
//        {
//          String cmd = "";
//          try
//          {
//            if(user.getStatus() == 3)
//            {
//              String cmd = (String) inDataPack.getObject();
//              writeSQL.writeCMD(cmd);
//            }
//          }
//          catch (SQLException e)
//          {
//            System.out.println(cmd);
//            e.printStackTrace();
//          }
//        }
      }
    }

    /*
    | SQLException e
     */
    catch (ClassNotFoundException | IOException e )
    {
      try
      {
        connectionPool.removeHandler(this);
        socket.close();
      }
      catch (IOException ex)
      {
        ex.printStackTrace();
      }
    }
  }
  public User getUser()
  {
    return user;
  }
  public void sendBackData(Object object)
  {
    try
    {
      outToClient.writeObject(object);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
