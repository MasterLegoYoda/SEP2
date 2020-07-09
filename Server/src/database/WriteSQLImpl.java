package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
public class WriteSQLImpl implements WriteSQL
{
  private Connection connection;
  @Override public void writeCMD(String cmd)
  {
    try{
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
          "password");
      Statement st = connection.createStatement();
      String query = cmd;
      st.executeUpdate(query);
      st.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
