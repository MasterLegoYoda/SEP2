package core;

import networking.*;

public class ClientFactory
{
    LoginClient loginClient;

    public ClientFactory(){
    loginClient = new SocketClient();
    }

    public LoginClient getLoginClient() {
        return loginClient;
    }

}
