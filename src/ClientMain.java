import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientMain
{

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        try
        {
            System.out.print("Enter the ip address of the server: ");
            String ip = keyboard.next();
            System.out.print("Enter your name: ");
            String userName = keyboard.next();
            //create a connection to the server on the given address string provided
            //port
            Socket socket = new Socket(ip, 8001);
            //create input and output Strings
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            //take in the command
            //Command command = (Command)is.readObject();
            ClientListener cl = new ClientListener(os, is, new ChatFrame(os, userName, new ArrayList<User>(), new ArrayList<Text>()));
            new Thread(cl).start();
            //System.out.println("Finished");
        }
        catch (Exception e)
        {
            System.out.println("Error in Client's Main: ");
            e.printStackTrace();
        }
    }
}