package database;

import sharedClasses.User;

import java.sql.SQLException;

public interface LoadUser
{
  public User loadUser(int id) throws SQLException;
  boolean checkUserExistence(int id) throws SQLException;
}
