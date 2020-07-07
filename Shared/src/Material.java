import java.io.Serializable;

public class Material implements Serializable
{
  private String name;
  private float pricePerUnit;

  public Material(String name, float pricePerUnit)
  {
    this.name=name;
    this.pricePerUnit=pricePerUnit;
  }

  public String getName()
  {
    return name;
  }

  public float getPricePerUnit()
  {
    return pricePerUnit;
  }
}
