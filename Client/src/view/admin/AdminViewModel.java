package view.admin;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminViewModel
{
    IntegerProperty id;
    StringProperty status;

    public AdminViewModel(){
    id = new SimpleIntegerProperty();
    status = new SimpleStringProperty();
    }

    public IntegerProperty getId(){return id;}
    public StringProperty getStatus(){return status;}
}
