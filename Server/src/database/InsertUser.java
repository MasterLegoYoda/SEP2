package database;

import sharedClasses.User;
import sharedClasses.UserTransferVOneImpl;

import java.sql.SQLException;

public interface InsertUser
{
  public void insertUser(UserTransferVOneImpl user) throws SQLException;
}
