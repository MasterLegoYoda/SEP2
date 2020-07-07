package core;


import view.admin.AdminViewModel;
import view.login.LoginViewModel;
import view.student.StudentVIewModel;
import view.viaShopEmployee.ViaShopEmployeeViewModel;

public class ViewModelFactory
{
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private AdminViewModel adminViewModel;
    private StudentVIewModel studentVIewModel;
    private ViaShopEmployeeViewModel viaShopEmployeeViewModel;

    public ViewModelFactory(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
        loginViewModel = new LoginViewModel();
        adminViewModel = new AdminViewModel();
        studentVIewModel = new StudentVIewModel();
        viaShopEmployeeViewModel = new ViaShopEmployeeViewModel();
    }
}
