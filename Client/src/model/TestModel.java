package model;

import sharedClasses.AuthPack;
import sharedClasses.Subject;
import sharedClasses.User;

public interface TestModel extends Subject
{
  void transmitUser(User user);
  void transmitCredentials(AuthPack authPack);
  //void transmitCMD(String cmd);
}
