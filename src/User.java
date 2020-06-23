import java.io.Serializable;

public class User implements Serializable
{
    private String name;
    public User(String name)
    {
        this.name=name;
    }
    public String toString()
    {
        return name;
    }
    /*
    - JList of users
      JList will hold an array of users
      which will be produced by a method
    - JScrollPane will hold the list
      add the pane

    - JList of texts
      JList will hold an array of text
      which will be produced by a method
    - JScrollPane will hold the list
      add the pane
     */
}
