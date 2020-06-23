import javax.lang.model.element.NestingKind;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerListener implements Runnable
{
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private Text newText;
    private static ArrayList<User> users= new ArrayList<>();;
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    public ServerListener(ObjectOutputStream os, ObjectInputStream is) {
        this.os = os;
        this.is = is;
        outs.add(os);
    }
    public void run() {
        try
        {
            while(true) {
                Command command = (Command) is.readObject();
                System.out.println("here");
                if (command.getCommand()==Command.DISCONNECT)
                {
                    for(int x=0;x<users.size();x++)
                        if(users.get(x).toString()==command.getCommandData())
                            users.remove(x);
                    //users.clear();
                    command.setCommandData(users);
                    outs.remove(os);
                }
                if(command.getCommand()==Command.NEWUSER)
                {
                    //System.out.println("here size: "+users.size());
                    users.add((User) command.getCommandData());
                    command.setCommandData(users);
                    //System.out.println("then: "+users.size());
                }

               // else{
                    for(ObjectOutputStream out: outs) {
                        //if (out != os && command.getCommand() != Command.DISCONNECT) {
                            out.writeObject(command);
                            out.reset();
                        //}


                    }
                    if (command.getCommand() == Command.DISCONNECT)
                    {

                        break;
                    }
                //}

            }
        }
        catch (Exception e)
        {
            System.out.println("Error in Server's Main: ");
            e.printStackTrace();
        }
        finally {
            outs.remove(os);
        }
    }
}
