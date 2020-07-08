package networking;

import sharedClasses.Subject;
import sharedClasses.User;

public interface UserClient extends Subject
{
  void transmitUser(User user);
  void transmitCMD(String cmd);
}
