import java.io.Serializable;

public class Command implements Serializable
{
    public static final int LOG_OFF=0;
    public static final int UPDATE = 3;
    public static final int RESET=10;
    public static final int ADD=1;
    public static final int NEWUSER=2;
    public static final int DISCONNECT=4;

    private int command;
    private Object commandData;
    public Command(int command)
    {
        this.command=command;
    }
    public Command(int command, Object commandData)
    {
        this.command=command;
        this.commandData=commandData;
    }
    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public Object getCommandData() {
        return commandData;
    }

    public void setCommandData(Object commandData) {
        this.commandData = commandData;
    }
}

