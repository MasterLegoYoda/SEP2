package networking;

import sharedClasses.AuthPack;
import sharedClasses.Subject;

public interface LoginClient extends Subject
{
  void transmitLoginInfo(AuthPack authPack);
}
