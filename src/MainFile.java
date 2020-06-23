import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFile
{
    public static void main(String[] args)
    {
        try {
            ServerSocket serverSocket = new ServerSocket(8001);
            while (true) {
                //create a new Person
                //accept the connection
                Socket socket = serverSocket.accept();
                //create a new person here
                //
                //create input and output Strings
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                //create a thread for the client
                new Thread(new ServerListener(os, is)).start();
                System.out.println("Connected");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in Server's Main: ");
            e.printStackTrace();
        }
    }
}
