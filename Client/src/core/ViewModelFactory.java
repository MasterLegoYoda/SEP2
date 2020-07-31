package core;


import view.admin.AdminViewModel;
import view.login.LoginViewModel;
import view.student.StudentViewModel;
import view.studentEmployee.StudentEmployeeViewModel;
import view.viaShopEmployee.ViaShopEmployeeViewModel;

public class ViewModelFactory
{
    private ModelFactory modelFactory;
    private AdminViewModel adminViewModel;
    private LoginViewModel loginViewModel;
    private StudentViewModel studentVIewModel;
    private StudentEmployeeViewModel studentEmployeeViewModel;
    private ViaShopEmployeeViewModel viaShopEmployeeViewModel;

    public ViewModelFactory(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
        loginViewModel = new LoginViewModel(modelFactory.getModel());
        adminViewModel = new AdminViewModel(modelFactory.getModel());
        studentVIewModel = new StudentViewModel(modelFactory.getModel());
        studentEmployeeViewModel = new StudentEmployeeViewModel(modelFactory.getModel());
        viaShopEmployeeViewModel = new ViaShopEmployeeViewModel(modelFactory.getModel());
    }

    public AdminViewModel getAdminViewModel() { return adminViewModel;}
    public LoginViewModel getLoginViewModel(){return loginViewModel;}
    public StudentViewModel getStudentVIewModel() { return studentVIewModel; }
    public StudentEmployeeViewModel getStudentEmployeeViewModel() {return studentEmployeeViewModel;}
    public ViaShopEmployeeViewModel getViaShopEmployeeViewModel() {return viaShopEmployeeViewModel;}

    //scene change code

    public ModelFactory getModelFactory()
    {
        return modelFactory;
    }
}
