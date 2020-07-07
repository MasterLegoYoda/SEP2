package sharedClasses;

import sharedClasses.Material;

import java.io.Serializable;

public class UsedMaterial implements Serializable
{
  private Material material;
  private int unitUsed;

  public UsedMaterial(Material material, int unitUsed)
  {
    this.material = material;
    this.unitUsed = unitUsed;
  }

  public Material getMaterial()
  {
    return material;
  }

  public int getUnitUsed()
  {
    return unitUsed;
  }

  public void setMaterial(Material material)
  {
    this.material = material;
  }

  public void setUnitUsed(int unitUsed)
  {
    this.unitUsed = unitUsed;
  }
}
