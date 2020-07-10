package model;

import sharedClasses.Subject;
import sharedClasses.User;

public interface UserModel extends Subject
{
  void transmitUser(User user);
  //void transmitCMD(String cmd);
}
