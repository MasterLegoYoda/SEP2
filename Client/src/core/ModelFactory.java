package core;

import model.LoginModel;
import model.LoginModelImpl;
import networking.LoginClient;

public class ModelFactory
{
    private LoginModelImpl loginModelImpl;
    private LoginClient loginClient;

    public LoginModelImpl getLoginModel(){
        if(loginModelImpl==null){
            loginModelImpl = new LoginModelImpl(loginClient);
        }
        return loginModelImpl;
    }
}
