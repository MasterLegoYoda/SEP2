package view.student;

import core.ViewHandler;

public class StudentController{
    private ViewHandler viewHandler;
    private StudentViewModel studentViewModel;
    public void init(ViewHandler viewHandler, StudentViewModel studentViewModel){
        this.viewHandler = viewHandler;
        this.studentViewModel = studentViewModel;
    }
}
