package database;

import java.sql.SQLException;

public interface Auth
{
  public boolean authenticate(int id, int password) throws SQLException;
}
