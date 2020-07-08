package networking;

import sharedClasses.Subject;

public interface AccessClient extends Subject
{
  void transmitCMD(String cmd);
}
