package database;

import sharedClasses.User;

import java.sql.SQLException;

public interface InsertUser
{
  public void insertUser(User user) throws SQLException;
}
