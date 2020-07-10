package networking;

import javafx.application.Platform;
import sharedClasses.AuthPack;
import sharedClasses.ClassName;
import sharedClasses.Container;
import sharedClasses.User;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private SocketClient socketClient;
  private Socket socket;
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;

  public ClientSocketHandler(Socket socket, SocketClient socketClient)
      throws IOException
  {
    this.socket = socket;
    this.socketClient = socketClient;
    outToServer = new ObjectOutputStream(socket.getOutputStream());
    inFromServer = new ObjectInputStream(socket.getInputStream());
  }
  @Override public void run()
  {
    try
    {
      while (true)
      {
        Container incomingContainer = (Container) inFromServer.readObject();
        switch (incomingContainer.getClassName())
        {
          case User:
          {
            Platform.runLater(() -> {
              socketClient.updateUser(incomingContainer);
            });
          }
          case MaterialList:
          {
            Platform.runLater(() -> {
              socketClient.updateMaterialsList(incomingContainer);
            });
          }
          case LoginResult:
          {
            Platform.runLater(() -> {
              socketClient.loginResults(incomingContainer);
            });
          }
        }
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
  public void transmitUser(User user)
  {
    try
    {
      Container outgoingContainer = new Container(user, ClassName.User);
      outToServer.writeObject(outgoingContainer);
    }
    catch (IOException e)
    {
      System.out.println("user sending");
    }
  }
  public void transmitLoginInformation(AuthPack authPack)
  {
    try
    {
      Container outgoingContainer = new Container(authPack, ClassName.AuthPackage);
      outToServer.writeObject(outgoingContainer);
    }
    catch (IOException e)
    {
      System.out.println("login info sending");
    }
  }
  public void transmitCMD(String str)
  {
    try
    {
      Container outgoingContainer = new Container(str, ClassName.SQLC);
      outToServer.writeObject(outgoingContainer);
    }
    catch (IOException e)
    {
      System.out.println("command sending");
    }
  }
}
