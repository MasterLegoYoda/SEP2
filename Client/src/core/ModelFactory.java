package core;

import model.LoginModel;
import model.LoginModelImpl;

public class ModelFactory
{
    private ClientFactory clientFactory;
    private LoginModel loginModel;

    public ModelFactory(ClientFactory clientFactory){
        this.clientFactory = clientFactory;
        loginModel = new LoginModelImpl(clientFactory.getLoginClient());
    }

    public LoginModel getLoginModel(){return loginModel;}

    public ClientFactory getClientFactory() {
        return clientFactory;
    }
}
