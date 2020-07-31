package view.studentEmployee;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.UserModel;
import sharedClasses.Material;
import sharedClasses.MaterialList;
import sharedClasses.UsedMaterial;
import sharedClasses.UserTransferVOneImpl;
import view.student.TableMaterial;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class StudentEmployeeViewModel
{
  private UserModel model;
  private UserTransferVOneImpl editingUser;
  private ArrayList<Material> materials;
  private ObservableList<String> materialsCB;
  private StringProperty editingStudentID;
  private StringProperty errorMSG;
  private ObservableList<TableMaterial> tableMaterials;
  private BooleanProperty laserLicence;
  private BooleanProperty printerLicence;
  private BooleanProperty updater;
  private BooleanProperty isLoaded;

  public StudentEmployeeViewModel(UserModel userModel)
  {
    model = userModel;
    editingStudentID = new SimpleStringProperty("");
    errorMSG = new SimpleStringProperty("");
    model.addListener("updateEditingUserStudentEmployee",this::setEditingUserUser);
    model.addListener("studentEmployeeMaterialsList",this::receiveMaterialsList);
    model.addListener("userNotFoundStudentEmployee",this::receiveError);
    tableMaterials = FXCollections.observableArrayList();
    laserLicence = new SimpleBooleanProperty(false);
    printerLicence = new SimpleBooleanProperty(false);
    updater = new SimpleBooleanProperty(false);
    isLoaded = new SimpleBooleanProperty(false);
  }
  public void receiveError(PropertyChangeEvent propertyChangeEvent)
  {
    errorMSG.setValue((String)propertyChangeEvent.getNewValue());
  }
  public void receiveMaterialsList(PropertyChangeEvent propertyChangeEvent)
  {
    String str = "";
    materials = ((MaterialList) propertyChangeEvent.getNewValue()).getMaterials();
    for(int x = 0; x < materials.size();x++)
    {
      str = materials.get(x).getName() + "|" + materials.get(x).getPricePerUnit() +"DKK per unit";
      materialsCB.add(str);
      str = null;
    }
  }
  public ArrayList<TableMaterial> getUpdatedTableInfo()
  {
    ArrayList<TableMaterial> temp = new ArrayList<>();
    for(int x = 0; x < editingUser.getUsedMaterials().size();x++)
    {
      temp.add(new TableMaterial(editingUser.getUsedMaterials().get(x).getMaterial().getName(),
          String.valueOf(editingUser.getUsedMaterials().get(x).getMaterial().getPricePerUnit()),
          String.valueOf(editingUser.getUsedMaterials().get(x).getUnitUsed()),
          String.valueOf(editingUser.getUsedMaterials().get(x).getUnitUsed()*editingUser.getUsedMaterials().get(x).getMaterial().getPricePerUnit())));
    }
    return temp;
  }
  public void setEditingUserUser(PropertyChangeEvent propertyChangeEvent)
  {
    editingUser = (UserTransferVOneImpl)propertyChangeEvent.getNewValue();
    isLoaded.set(true);
    editingStudentID.setValue(String.valueOf(editingUser.getID()));
    tableMaterials.addAll(getUpdatedTableInfo());
    laserLicence.set(editingUser.getLicences().get(1));
    printerLicence.set(editingUser.getLicences().get(0));
    updtr();
  }
  public void requestUser(int id)
  {
    if(String.valueOf(id).length() == 6)
    {
      model.requestUser(id);
    }
  }
  public void cancel()
  {
    editingUser = null;
    isLoaded.set(false);
    model.cancelEditing();
    editingStudentID.setValue("");
    errorMSG.setValue("");
    tableMaterials = FXCollections.observableArrayList();
    laserLicence.set(false);
    printerLicence.set(false);
  }
  public void updateEditingUserLicence(boolean laser,boolean printer)
  {
    ArrayList<Boolean> temp = new ArrayList<>();
    Boolean l1 = printer;
    Boolean l2 = laser;
    temp.add(0,l1);
    temp.add(1,l2);
    editingUser.setLicences(temp);
    model.transmitUser(editingUser);
  }
  public void addMaterialToEditingUser(String name,int amount)
  {
    ArrayList<UsedMaterial> temp = new ArrayList<>();
    if(editingUser.getUsedMaterials().size() > 0)
    {
      for(int x = 0; x < editingUser.getUsedMaterials().size();x++)
      {
        if (name.contains(editingUser.getUsedMaterials().get(x).getMaterial().getName()))
        {
          for (int y = 0; y < materials.size();y++)
          {
            if(name.contains(materials.get(y).getName()))
            {
              int t = amount + editingUser.getUsedMaterials().get(x).getUnitUsed();
              temp.add(new UsedMaterial(materials.get(y),t));
            }
          }
        }
        else
        {
          temp.add(editingUser.getUsedMaterials().get(x));
        }
      }
    }
    else
    {
      for (int x = 0;x < materials.size(); x++)
      {
        if(name.contains(materials.get(x).getName()))
        {
          temp.add(new UsedMaterial(materials.get(x),amount));
        }
      }
    }
    editingUser.setUsedMaterials(temp);
    model.transmitUser(editingUser);
    tableMaterials.addAll(getUpdatedTableInfo());
    updtr();
  }
  private void updtr()
  {
    if(updater.getValue())
    {
      updater.set(false);
    }
    else
    {
      updater.set(true);
    }
  }

  public ObservableList<String> getMaterialsCB()
  {
    return materialsCB;
  }

  public String getEditingStudentID()
  {
    return editingStudentID.get();
  }

  public StringProperty editingStudentIDProperty()
  {
    return editingStudentID;
  }

  public String getErrorMSG()
  {
    return errorMSG.get();
  }

  public StringProperty errorMSGProperty()
  {
    return errorMSG;
  }

  public ObservableList<TableMaterial> getTableMaterials()
  {
    return tableMaterials;
  }

  public boolean isLaserLicence()
  {
    return laserLicence.get();
  }

  public BooleanProperty laserLicenceProperty()
  {
    return laserLicence;
  }

  public boolean isPrinterLicence()
  {
    return printerLicence.get();
  }

  public BooleanProperty printerLicenceProperty()
  {
    return printerLicence;
  }

  public boolean isUpdater()
  {
    return updater.get();
  }

  public BooleanProperty updaterProperty()
  {
    return updater;
  }

  public boolean isIsLoaded()
  {
    return isLoaded.get();
  }

  public BooleanProperty isLoadedProperty()
  {
    return isLoaded;
  }
}
