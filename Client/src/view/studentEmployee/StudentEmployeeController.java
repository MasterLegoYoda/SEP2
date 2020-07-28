package view.studentEmployee;

import core.ViewHandler;
import view.student.StudentViewModel;

public class StudentEmployeeController
{
    private ViewHandler viewHandler;
    private StudentEmployeeViewModel studentEmployeeViewModel;
    public void init(ViewHandler viewHandler, StudentEmployeeViewModel studentEmployeeViewModel){
        this.viewHandler = viewHandler;
        this.studentEmployeeViewModel = studentEmployeeViewModel;
    }
}
