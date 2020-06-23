import java.io.Serializable;
public class Text implements Serializable
{
    private String user;
    private String text;
    public Text(String user, String text)
    {
        this.text=text;
        this.user=user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return user+": "+text;
    }
}
