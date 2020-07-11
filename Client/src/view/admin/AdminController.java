package view.admin;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import model.UserModel;

import java.util.function.UnaryOperator;

public class AdminController
{
    public ComboBox setStatusComboBox;
    public Label currentStatusLabel;
    public Label studentIDLAbel;
    public TextField userIDTextField;
    public Label errorLabel;
    private ViewHandler viewHandler;
    private AdminViewModel adminViewModel;
    private BooleanProperty isLoaded;

    public void init(ViewHandler viewHandler, AdminViewModel adminModel)
    {
        isLoaded = new SimpleBooleanProperty();
    this.viewHandler = viewHandler;
    adminViewModel = adminModel;
    currentStatusLabel.textProperty().bindBidirectional(adminViewModel.getStatus());
    studentIDLAbel.textProperty().bindBidirectional(adminViewModel.getStatus());
    errorLabel.textProperty().bindBidirectional(adminViewModel.getError());
    UnaryOperator<TextFormatter.Change> filter = change ->
    {
        String tmpStr = change.getText();
        if (tmpStr.matches("[0-9]*")&&(tmpStr.length() <= 6))
        {
            return change;
        }
        return null;
    };
    TextFormatter<String> fieldFormat = new TextFormatter<>(filter);
    userIDTextField.setTextFormatter(fieldFormat);
    setStatusComboBox.getItems().add("Student");
    setStatusComboBox.getItems().add("Student employee");
    setStatusComboBox.getItems().add("VIA shop employee");
    }

    public void confirmStatusChange(ActionEvent actionEvent)
    {
        if(isLoaded.getValue().booleanValue())
        {
            String comboBoxValue = (String)setStatusComboBox.getValue();
            if(comboBoxValue.equalsIgnoreCase("Student"))
            {
                adminViewModel.confirmEditAndSend(1);
            }
            if(comboBoxValue.equalsIgnoreCase("Student employee"))
            {
                adminViewModel.confirmEditAndSend(2);
            }
            if(comboBoxValue.equalsIgnoreCase("VIA shop employee"))
            {
                adminViewModel.confirmEditAndSend(3);
            }
        }
    }

    public void cancel(ActionEvent actionEvent)
    {
        if(isLoaded.getValue().booleanValue())
        {
            userIDTextField.clear();
            adminViewModel.cancel();
        }
    }
    public void confirmUserID(ActionEvent actionEvent)
    {
        if(!( userIDTextField.textProperty() == null &&userIDTextField.getText().length() == 0))
        {
            int a = Integer.valueOf(userIDTextField.getText());
            adminViewModel.getUserById(a);
        }
    }
}
