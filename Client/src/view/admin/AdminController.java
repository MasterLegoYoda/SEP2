package view.admin;

import core.ViewHandler;
import core.ViewModelFactory;

public class AdminController
{
    private ViewHandler viewHandler;
    private AdminViewModel adminViewModel;

    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler){
    this.viewHandler = viewHandler;
    adminViewModel = viewModelFactory.getAdminViewModel();
    }
}
