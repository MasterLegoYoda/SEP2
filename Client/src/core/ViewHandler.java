package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.admin.AdminController;
import view.login.LoginController;
import view.student.StudentController;
import view.studentEmployee.StudentEmployeeController;
import view.viaShopEmployee.ViaShopEmployeeController;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class ViewHandler
{
    private Stage mainStage = null;
    private Scene adminScene;
    private Scene loginScene;
    private Scene studentScene;
    private Scene studentEmployeeScene;
    private Scene viaShopEmployeeScene;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
        mainStage = new Stage();
        viewModelFactory.getModelFactory().getModel().addListener("openStudentView",this::openStudentView);
        viewModelFactory.getModelFactory().getModel().addListener("openStudentEmployeeView",this::openStudentEmployeeView);
        viewModelFactory.getModelFactory().getModel().addListener("openVIAshopEmployeeView",this::openViaShopEmployeeView);
        viewModelFactory.getModelFactory().getModel().addListener("openAdministratorView",this::openAdminView);
    }

    public void start(){ openLoginView(); }

    public void openAdminView(PropertyChangeEvent propertyChangeEvent)
    {
        try {
            if(adminScene==null)
            {
                adminScene=getSceneAdmin("../view/admin/Admin.fxml");
            }
                changeScene("Admin",adminScene);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Scene getSceneAdmin(String path) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource(path));
            root = loader.load();

            AdminController view = loader.getController();
            view.init(this, viewModelFactory.getAdminViewModel());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root);
    }

    public void openLoginView()
    {
        try {
            if(loginScene==null)
            {
                loginScene=getSceneLogin("../view/login/Login.fxml");
            }
            changeScene("Login",loginScene);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Scene getSceneLogin(String path) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource(path));
            root = loader.load();

            LoginController view = loader.getController();
            view.init(this, viewModelFactory.getLoginViewModel());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root);
    }

    public void openStudentView(PropertyChangeEvent propertyChangeEvent)
    {
        try {
            if(studentScene==null)
            {
                studentScene=getSceneStudent("../view/student/Student.fxml");
            }
            changeScene("Student",studentScene);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Scene getSceneStudent(String path) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource(path));
            root = loader.load();

            StudentController view = loader.getController();
            view.init(this, viewModelFactory.getStudentVIewModel());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root);
    }

    public void openStudentEmployeeView(PropertyChangeEvent propertyChangeEvent)
    {
        try {
            if(studentEmployeeScene==null)
            {
                studentEmployeeScene=getSceneStudentEmployee("../view/studentEmployee/StudentEmployee.fxml");
            }
            changeScene("StudentEmployee",studentEmployeeScene);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Scene getSceneStudentEmployee(String path) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource(path));
            root = loader.load();

            StudentEmployeeController view = loader.getController();
            view.init(this, viewModelFactory.getStudentEmployeeViewModel());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root);
    }

    public void openViaShopEmployeeView(PropertyChangeEvent propertyChangeEvent)
    {
        try {
            if(viaShopEmployeeScene==null)
            {
                viaShopEmployeeScene=getViaShopEmployeeScene("../view/viaShopEmployee/ViaShopEmployee.fxml");
            }
            changeScene("ViaShopEmployee",viaShopEmployeeScene);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Scene getViaShopEmployeeScene(String path) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource(path));
            root = loader.load();

            ViaShopEmployeeController view = loader.getController();
            view.init(this, viewModelFactory.getViaShopEmployeeViewModel());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root);
    }

    private void changeScene(String s, Scene scene)
    {
        mainStage.setTitle(s);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
