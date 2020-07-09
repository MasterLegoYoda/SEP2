package database;

import java.sql.SQLException;

public interface WriteSQL
{
  void writeCMD(String cmd) throws SQLException;
}
