package networking;

import database.AuthImpl;
import database.InsertUserImpl;
import database.LoadMaterialImpl;
import database.LoadUserImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  private static final int SERVER_PORT = 9090;

  public void start()
  {
    try
    {
      ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
      ConnectionPool connectionPool = new ConnectionPool();
      AuthImpl authenticator = new AuthImpl();
      InsertUserImpl userInserter = new InsertUserImpl();
      LoadMaterialImpl materialLoader = new LoadMaterialImpl();
      LoadUserImpl userLoader = new LoadUserImpl();
      while(true)
      {
        System.out.println("[SERVER] Waiting for client connection");
        Socket socket = serverSocket.accept();
        ServerSocketHandler socketHandler = new ServerSocketHandler(socket,
            connectionPool,authenticator,userInserter,materialLoader,userLoader);
        new Thread(socketHandler).start();
        System.out.println("[SERVER] Connected to client");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
