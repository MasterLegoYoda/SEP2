package core;

import networking.*;

import java.io.IOException;

public class ClientFactory
{
    /*
    LoginClient loginClient;
    UserClient userClient;
     */
    //LATER REPLACE WITH AN INTERFACE
    private SocketClient client;
    public ClientFactory() throws IOException
    {
        client = new SocketClient();
        /*
    loginClient = newSocketClient;
    userClient = newSocketClient;

         */
    }
/*
    public LoginClient getLoginClient() {
        return loginClient;
    }

    public UserClient getUserClient()
    {
        return userClient;
    }

 */

    public SocketClient getClient()
    {
        return client;
    }
}
