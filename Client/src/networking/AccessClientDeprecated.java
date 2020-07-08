package networking;

import sharedClasses.Subject;

public interface AccessClientDeprecated extends Subject
{
  void transmitCMD(String cmd);
}
