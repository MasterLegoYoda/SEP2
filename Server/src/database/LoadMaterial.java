package database;


import sharedClasses.Material;
import sharedClasses.MaterialList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoadMaterial
{
  public MaterialList loadMaterial() throws SQLException;

}
