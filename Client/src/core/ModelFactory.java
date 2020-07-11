package core;

import model.LoginModel;
import model.LoginModelImpl;
import model.TestModelImpl;

public class ModelFactory
{
    private ClientFactory clientFactory;
    private TestModelImpl model;
    public ModelFactory(ClientFactory clientFactory){
        this.clientFactory = clientFactory;
        model = new TestModelImpl(clientFactory.getClient());
       // loginModel = new LoginModelImpl(clientFactory.getLoginClient());

    }

   // public LoginModel getLoginModel(){return loginModel;}

    public TestModelImpl getModel()
    {
        return model;
    }

    public ClientFactory getClientFactory() {
        return clientFactory;
    }
}
