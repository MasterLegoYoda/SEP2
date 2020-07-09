package sharedClasses;

public interface User
{
  public int getID();
  public byte getStatus();
  public boolean equalsByID(int id);
}
