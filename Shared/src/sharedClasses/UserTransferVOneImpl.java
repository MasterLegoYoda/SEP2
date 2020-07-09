package sharedClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class UserTransferVOneImpl implements Serializable, User
{
  private byte status;
  private int id;
  private ArrayList<UsedMaterial> usedMaterials;
  private ArrayList<Boolean> licences;

  @Override public int getID()
  {
    return id;
  }

  @Override public byte getStatus()
  {
    return status;
  }

  @Override public boolean equalsByID(int id)
  {
    if(id == this.id)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public UserTransferVOneImpl(int id)
  {
    this.id = id;
  }

  public void setStatus(byte status)
  {
    this.status = status;
  }

  public void setUsedMaterials(ArrayList<UsedMaterial> usedMaterials)
  {
    this.usedMaterials = usedMaterials;
  }

  public void setLicences(ArrayList<Boolean> licences)
  {
    this.licences = licences;
  }

  public ArrayList<UsedMaterial> getUsedMaterials()
  {
    return usedMaterials;
  }

  public ArrayList<Boolean> getLicences()
  {
    return licences;
  }

}
