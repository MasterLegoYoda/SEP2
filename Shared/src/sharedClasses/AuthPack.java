package sharedClasses;

import java.io.Serializable;

public class AuthPack implements Serializable
{
  private int id;
  private String password;

  public AuthPack(int id, String password)
  {
    this.id = id;
    this.password = password;
  }

  public int getId()
  {
    return id;
  }

  public String getPassword()
  {
    return password;
  }
}
