package networking;

import sharedClasses.AuthPack;
import sharedClasses.Container;
import sharedClasses.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class SocketClient implements LoginClient, UserClient,AccessClient
{
  private static final String SERVER_IP = "127.0.0.1";
  private static final int SERVER_PORT = 9090;
  private ClientSocketHandler socketHandler;
  private Socket socket;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public void initialize() throws IOException
  {
    socket = new Socket(SERVER_IP, SERVER_PORT);
    socketHandler = new ClientSocketHandler(socket, this);
    Thread thread = new Thread(socketHandler);
    thread.setDaemon(true);
    thread.start();
  }
  @Override public void transmitCMD(String cmd)
  {
      socketHandler.transmitCMD(cmd);
  }
  @Override public void transmitUser(User user)
  {
    socketHandler.transmitUser(user);
  }
  @Override public void transmitLoginInfo(AuthPack authPack)
  {
    socketHandler.transmitLoginInformation(authPack);
  }
  public void updateUser(Container inPack)
  {
    support.firePropertyChange("incomingUser",null,inPack.getObject());
  }
  public void updateMaterialsList(Container inPack)
  {
    support.firePropertyChange("incomingUser",null,inPack.getObject());
  }
  public void wrongCredentials()
  {
    support.firePropertyChange("incomingUser",null,null);
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
