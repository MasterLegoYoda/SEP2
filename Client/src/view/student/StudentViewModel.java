package view.student;

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

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class StudentViewModel
{
  private UserModel userModel;
  private StringProperty studentID;
  private StringProperty totalDebt;
  private BooleanProperty updater;
  private ObservableList<String> materialsCB;
  private ObservableList<TableMaterial> tableMaterials;
  private ArrayList<Material> materials;
  private UserTransferVOneImpl user;

  public StudentViewModel(UserModel model)
  {
    userModel = model;
    studentID = new SimpleStringProperty("");
    updater = new SimpleBooleanProperty();
    totalDebt = new SimpleStringProperty("");
    updater.set(false);
    model.addListener("userToStudentViewModel",this::receiveUser);
    model.addListener("studentMaterialsList",this::receiveMaterialsList);
    materialsCB = FXCollections.observableArrayList();
    tableMaterials = FXCollections.observableArrayList();
    tableMaterials.addAll(getUpdatedTableInfo());
  }



  public void receiveUser(PropertyChangeEvent propertyChangeEvent)
  {
    user = (UserTransferVOneImpl)propertyChangeEvent.getNewValue();
    studentID.setValue(String.valueOf(user.getID()));
    updtr();
    totalDebt.setValue(getAmountOfTotalDebtInString());
    tableMaterials.addAll(getUpdatedTableInfo());
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
    for(int x = 0; x < user.getUsedMaterials().size();x++)
    {
      temp.add(new TableMaterial(user.getUsedMaterials().get(x).getMaterial().getName(),
        String.valueOf(user.getUsedMaterials().get(x).getMaterial().getPricePerUnit()),
          String.valueOf(user.getUsedMaterials().get(x).getUnitUsed()),
          String.valueOf(user.getUsedMaterials().get(x).getUnitUsed()*user.getUsedMaterials().get(x).getMaterial().getPricePerUnit())));
    }
    return temp;
  }
  public void addMaterial(String name,int amount)
  {
    ArrayList<UsedMaterial> temp = new ArrayList<>();
    if(user.getUsedMaterials().size() > 0)
    {
      for(int x = 0; x < user.getUsedMaterials().size();x++)
      {
        if (name.contains(user.getUsedMaterials().get(x).getMaterial().getName()))
        {
          for (int y = 0; y < materials.size();y++)
          {
            if(name.contains(materials.get(y).getName()))
            {
              temp.add(new UsedMaterial(materials.get(y),amount));
            }
          }
        }
        else
        {
          temp.add(user.getUsedMaterials().get(x));
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
    user.setUsedMaterials(temp);
    userModel.transmitUser(user);
    totalDebt.setValue(getAmountOfTotalDebtInString());
    tableMaterials.addAll(getUpdatedTableInfo());
    updtr();
  }
  private String getAmountOfTotalDebtInString()
  {
    int temp = 0;
    for(int x = 0; x < user.getUsedMaterials().size();x++)
    {
      temp = (int) (temp + (user.getUsedMaterials().get(x).getMaterial().getPricePerUnit()*user.getUsedMaterials().get(x).getUnitUsed()));
    }
    return String.valueOf(temp);
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

  public String getStudentID()
  {
    return studentID.get();
  }

  public StringProperty studentIDProperty()
  {
    return studentID;
  }

  public String getTotalDebt()
  {
    return totalDebt.get();
  }

  public StringProperty totalDebtProperty()
  {
    return totalDebt;
  }

  public boolean isUpdater()
  {
    return updater.get();
  }

  public BooleanProperty updaterProperty()
  {
    return updater;
  }

  public ObservableList<TableMaterial> getTableMaterials()
  {
    return tableMaterials;
  }
}
