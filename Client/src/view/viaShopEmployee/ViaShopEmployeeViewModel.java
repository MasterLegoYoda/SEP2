package view.viaShopEmployee;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.UserModel;
import sharedClasses.MaterialList;
import sharedClasses.UsedMaterial;
import sharedClasses.UserTransferVOneImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ViaShopEmployeeViewModel
{
  private UserModel userModel;
  private MaterialList materialList;
  private UserTransferVOneImpl editingUser;
  private StringProperty id;
  private StringProperty error;
  private StringProperty debt;
  private BooleanProperty isLoaded;

  public ViaShopEmployeeViewModel(UserModel model)
  {
    userModel = model;
    id = new SimpleStringProperty("");
    error = new SimpleStringProperty("");
    debt = new SimpleStringProperty("");
    isLoaded = new SimpleBooleanProperty(false);
    userModel.addListener("updateEditingUserVIAShopEmployee",this::setEditingUser);
    userModel.addListener("userNotFoundVIAShopEmployee",this::userNotFound);
    userModel.addListener("viaShopEmployeeMaterialsList",this::setMaterialsList);
  }
  public void setEditingUser(PropertyChangeEvent propertyChangeEvent)
  {
    editingUser = (UserTransferVOneImpl) propertyChangeEvent.getNewValue();
    int debtValue = 0;
    id.setValue(String.valueOf(editingUser.getID()));
    for(int x = 0; x < editingUser.getUsedMaterials().size();x++)
    {
      for (int i = 0; i < materialList.getMaterials().size();i++)
      {
        if(editingUser.getUsedMaterials().get(x).getMaterial().getName().equalsIgnoreCase(materialList.getMaterials().get(i)))
        {
          int alpha = editingUser.getUsedMaterials().get(x).getUnitUsed()*materialList.getMaterials().get(i).getPricePerUnit();
          debtValue = (alpha + debtValue);
        }
      }
    }
    debt.setValue(String.valueOf(debtValue));
    isLoaded.set(true);
  }
  public void userNotFound(PropertyChangeEvent propertyChangeEvent)
  {
    String str = (String)propertyChangeEvent.getNewValue();
    error.setValue(str);
  }
  public void reqUser(int id)
  {
    if(String.valueOf(id).length() == 6)
    {
      userModel.requestUser(id);
    }
  }
  public void clearDebt()
  {
    if(isLoaded.getValue().booleanValue())
    {
      ArrayList<UsedMaterial> tempArr = new ArrayList<>();
      editingUser.setUsedMaterials(tempArr);
      userModel.transmitUser(editingUser);
      isLoaded.set(false);
      error.setValue("");
      id.setValue("");
      debt.setValue("");
    }
  }
  public void clear()
  {
      isLoaded.set(false);
      editingUser = null;
      userModel.cancelEditing();
      error.setValue("");
      id.setValue("");
      debt.setValue("");
  }
  public void setMaterialsList(PropertyChangeEvent propertyChangeEvent)
  {
    materialList = (MaterialList)propertyChangeEvent.getNewValue();
  }
  public StringProperty getID()
  {
    return id;
  }
  public StringProperty getError()
  {
    return error;
  }
  public StringProperty getDebt()
  {
    return debt;
  }
  public BooleanProperty getIsLoaded()
  {
    return isLoaded;
  }
}
