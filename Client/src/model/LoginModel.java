package model;

import sharedClasses.AuthPack;
import sharedClasses.Subject;

public interface LoginModel extends Subject
{
  //void transmitCredentials(int id, String password);
  void transmitCredentials(int id, String password);
}
