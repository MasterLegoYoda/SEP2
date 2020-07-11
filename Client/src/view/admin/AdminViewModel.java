package view.admin;

import javafx.beans.property.*;
import model.TestModelImpl;
import model.UserModel;
import sharedClasses.UserTransferVOneImpl;

import java.beans.PropertyChangeEvent;

public class AdminViewModel
{
    private StringProperty id;
    private StringProperty status;
    private UserModel userModel;
    private UserTransferVOneImpl editingUser;
    private StringProperty error;
    private BooleanProperty isLoaded;
    public AdminViewModel(UserModel model)
    {
        isLoaded = new SimpleBooleanProperty();
        isLoaded.set(false);
        id = new SimpleStringProperty("");
        status = new SimpleStringProperty("");
        error = new SimpleStringProperty("");
        userModel = model;
        model.addListener("updateEditingUserAdmin",this::setEditingUser);
        model.addListener("userNotFoundAdmin",this::setError);
    }
    public void setEditingUser(PropertyChangeEvent propertyChangeEvent)
    {
        editingUser = (UserTransferVOneImpl) propertyChangeEvent.getNewValue();
        id.setValue(String.valueOf(editingUser.getID()));
        if(editingUser.getStatus() == 0)
        {
            status.setValue("Student");
        }
        else if(editingUser.getStatus() == 1)
        {
            status.setValue("Student employee");
        }
        else if(editingUser.getStatus() == 2)
        {
            status.setValue("VIA shop employee");
        }
        isLoaded.set(true);
    }
    public void setError(PropertyChangeEvent propertyChangeEvent)
    {
        String str = (String)propertyChangeEvent.getNewValue();
        error.setValue(str);
    }
    public void getUserById(int id)
    {
        if(String.valueOf(id).length() == 6)
        {
            userModel.requestUser(id);
        }
    }
    public void cancel()
    {
        isLoaded.set(false);
        editingUser = null;
        userModel.cancelEditing();
        status.setValue("");
        error.setValue("");
        id.setValue("");
    }
    public void confirmEditAndSend(int status)
    {
        byte st = (byte) status;
        editingUser.setStatus(st);
        userModel.transmitUser(editingUser);

    }
    public StringProperty getId(){return id;}
    public StringProperty getStatus(){return status;}
    public BooleanProperty getIsLoaded()
    {
        return isLoaded;
    }
    public StringProperty getError()
    {
        return error;
    }
}
