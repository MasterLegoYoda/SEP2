package model;

import sharedClasses.Subject;
import sharedClasses.User;

public interface UserModel extends Subject
{
  void transmitUser(User user);
  void requestUser(int id);
  //void transmitCMD(String cmd);
}
