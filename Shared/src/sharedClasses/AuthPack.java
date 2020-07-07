package sharedClasses;

import java.io.Serializable;

public class AuthPack implements Serializable
{
  private int id;
  private int password;

  public AuthPack(int id, int password)
  {
    this.id = id;
    this.password = password;
  }

  public int getId()
  {
    return id;
  }

  public int getPassword()
  {
    return password;
  }
}
