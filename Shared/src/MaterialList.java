import java.io.Serializable;
import java.util.ArrayList;

public class MaterialList implements Serializable
{
  private ArrayList<Material> materials;

  public MaterialList(ArrayList<Material> materials)
  {
    this.materials = materials;
  }
  public void addMaterial(Material material)
  {
    materials.add(material);
  }

  public ArrayList<Material> getMaterials()
  {
    return materials;
  }
}
