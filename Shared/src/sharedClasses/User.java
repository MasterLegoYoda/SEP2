package sharedClasses;

import java.util.ArrayList;

public interface User
{
  public int getID();
  public byte getStatus();
  ArrayList<UsedMaterial> getUsedMaterials();
  ArrayList<Boolean> getLicences();
  public boolean equalsByID(int id);
}
