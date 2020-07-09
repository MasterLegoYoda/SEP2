package networking;

import sharedClasses.ClassName;
import sharedClasses.Container;
import sharedClasses.User;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private List<ServerSocketHandler> connections = new ArrayList<>();
  private List<User> users = new ArrayList<>();
  public synchronized void addHandler(ServerSocketHandler handler)
  {
    connections.add(handler);
  }
  public void userJoin(User user)
  {
    users.add(user);
  }
  private void userLeft(User user)
  {
    users.remove(user);
  }
  public void removeHandler(ServerSocketHandler handler)
  {
    connections.remove(handler);
    userLeft(handler.getUser());
  }
  public void updateOnlineUserInfo(User user)
  {
    for (ServerSocketHandler handler : connections)
    {
      if(user.getID() == handler.getUser().getID())
      {
        Container outContainer = new Container(user, ClassName.User);
        handler.sendBackData(outContainer);
      }
    }
  }


}
