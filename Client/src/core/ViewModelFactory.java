package core;


import view.admin.AdminViewModel;
import view.login.LoginViewModel;
import view.student.StudentVIewModel;
import view.studentEmployee.StudentEmployeeViewModel;
import view.viaShopEmployee.ViaShopEmployeeViewModel;

public class ViewModelFactory
{
    private AdminViewModel adminViewModel;
    private LoginViewModel loginViewModel;
    private StudentVIewModel studentVIewModel;
    private StudentEmployeeViewModel studentEmployeeViewModel;
    private ViaShopEmployeeViewModel viaShopEmployeeViewModel;

    public ViewModelFactory(ModelFactory modelFactory){
        loginViewModel = new LoginViewModel();
        adminViewModel = new AdminViewModel();
        studentVIewModel = new StudentVIewModel();
        studentEmployeeViewModel = new StudentEmployeeViewModel();
        viaShopEmployeeViewModel = new ViaShopEmployeeViewModel();
    }

    public AdminViewModel getAdminViewModel() { return adminViewModel;}
    public LoginViewModel getLoginViewModel(){return loginViewModel;}
    public StudentVIewModel getStudentVIewModel() { return studentVIewModel; }
    public StudentEmployeeViewModel getStudentEmployeeViewModel() {return studentEmployeeViewModel;}
    public ViaShopEmployeeViewModel getViaShopEmployeeViewModel() {return viaShopEmployeeViewModel;}
}
