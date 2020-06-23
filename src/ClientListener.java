import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientListener implements Runnable
{
    //private AdderFrame frame;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private ChatFrame frame;
    //private ConnectFourGame game;

    public ClientListener(ObjectOutputStream os, ObjectInputStream is, ChatFrame frame) {
        this.os = os;
        this.is = is;
        this.frame = frame;
    }
    public void run() {
        try {
            while (true) {
                Command command = (Command) this.is.readObject();
                System.out.println("Receieved: "+command.getCommandData());
                /*if(command.getCommand()==Command.UPDATE)
                    frame.updateValue((Integer)(command.getCommandData()));*/
                System.out.println("in run of client listener");
                if (command.getCommand() == Command.ADD) {
                    System.out.println("Client got text");
                    frame.addText((Text)command.getCommandData());
                }
                if(command.getCommand() == Command.DISCONNECT)
                {
                    frame.updateUsers((ArrayList<User>) command.getCommandData());
                }
                else if(command.getCommand() == Command.NEWUSER)
                {
                    System.out.println("Here");
                    frame.updateUsers((ArrayList<User>) (command.getCommandData()));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in Client's Listener: ");
            e.printStackTrace();
        }

    }
}
