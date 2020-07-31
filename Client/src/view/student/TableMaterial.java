package view.student;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableMaterial
{
  private String name;
  private String pricePerUnit;
  private String usedAmount;
  private String totalPrice;
  public TableMaterial(String name,String ppu, String amount,String totalPrice)
  {
  this.name= name;
  pricePerUnit = ppu;
  usedAmount = amount;
  this.totalPrice = totalPrice;
  }

  public String getName()
  {
    return name;
  }

  public String getPricePerUnit()
  {
    return pricePerUnit;
  }

  public String getUsedAmount()
  {
    return usedAmount;
  }

  public String getTotalPrice()
  {
    return totalPrice;
  }
}
