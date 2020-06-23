import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.ObjectOutputStream;

public class ChatFrame extends JFrame implements MouseListener, KeyListener
{
    private ObjectOutputStream os;
    private JLabel lbl_Users, lbl_Chat;
    //private JTextField txt_changeValue;
    public static final int WIDTH=700;
    public static final int HEIGHT=700;
    private JButton send, disconnect;
    private JList<User> userJList;
    private JList<Text> textJList;
    private JTextField txt_message;
    private boolean close;
    private ArrayList<Text> texts;
    private static ArrayList<User> users;
    private String userName;
    //do a double buffer
    BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    Graphics bg = buffer.getGraphics();
    public ChatFrame(ObjectOutputStream os, String userName, ArrayList<User> users, ArrayList<Text> texts)
    {
        super("Chat");
        System.out.println("Creating Frame.");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        this.userName=userName;
        this.os=os;
        this.users=users;
        this.texts=texts;
        addMouseListener(this);
        addKeyListener(this);
        addNotify();
        close=false;

        User user = new User(userName);
        try {
            os.writeObject(new Command(Command.NEWUSER, user));
            os.flush();
            //os.writeObject(new Command(Command.ADD, new Text(userName, "")));
        }
        catch (Exception e)
        {
            System.out.println("Error from new User in Chat Frame: ");
            e.printStackTrace();
        }

        lbl_Users = new JLabel("Users");
        lbl_Users.setBounds(500, 0, 180, 40);
        lbl_Users.setFont(new Font("Serif", Font.BOLD, 24));
        add(lbl_Users);

        lbl_Chat = new JLabel("Chat");
        lbl_Chat.setBounds(0, 0, 180, 40);
        lbl_Chat.setFont(new Font("Serif", Font.BOLD, 24));
        add(lbl_Chat);

        txt_message =new JTextField();
        txt_message.setBounds(0, 460, 500, 160);
        add(txt_message);

        userJList = new JList (toUser());
        JScrollPane pane = new JScrollPane (userJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(500, 40, 180, 400);
        add(pane);

        textJList = new JList (toText());
        JScrollPane pane1 = new JScrollPane(textJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane1.setBounds(0, 40, 500, 400);
        add(pane1);

        send =new JButton("Send");
        send.setBounds(500, 460, 200, 80);
        add(send);
        send.addActionListener(e ->{
            try
            {
                if(!txt_message.getText().equals("")) {
                    System.out.println("Sending add: "+txt_message.getText()+" By: "+userName);
                    Text text = new Text(userName, txt_message.getText());
                    os.writeObject(new Command(Command.ADD, text));
                    os.flush();
                    txt_message.setText("");
                }
            }
            catch (Exception ee)
            {
                System.out.println("Error Sending from Frames Send Button: ");
                ee.printStackTrace();
            }
        });
        disconnect =new JButton("Disconnect");
        disconnect.setBounds(500, 540, 200, 80);
        add(disconnect);
        disconnect.addActionListener(e ->{
            try
            {
                    os.writeObject(new Command(Command.DISCONNECT, userName));
                    os.flush();
                    disconnect();
            }
            catch (Exception ee)
            {
                System.out.println("Error Sending from Frames Disconnect Button: ");
                ee.printStackTrace();
            }
        });

        setVisible(true);

    }
    public void disconnect()
    {
        System.exit(0);
    }
    public void updateGame()
    {
        /*if(game.getStatus()!=ConnectFourGame.PLAYING)
            return;
        try {
            Command command = new Command(Command.UPDATE, game);
            os.writeObject(command);
            os.reset();
            repaint();
        }
        catch (Exception e)
        {
            System.out.println("Error in update board: ");
            e.printStackTrace();
        }*/
    }
    public void update(/*ConnectFourGame newGame*/)
    {

    }
    public User[] toUser()
    {
        User[] userArray= new User[users.size()];
        for(int x = 0;x < users.size();x++) {
            userArray[x] = ((User)(users.get(x)));
        }
        return userArray;
    }
    public Text[] toText()
    {
        Text[] textArray= new Text[texts.size()];
        for(int x = 0;x < texts.size();x++) {
            textArray[x] = ((Text)(texts.get(x)));
        }
        return textArray;
    }
    public void addText(Text t)
    {
        texts.add(t);
        textJList.setListData(toText());
    }
    public void updateUsers(ArrayList<User> newUsers)
    {
        users=newUsers;
        userJList.setListData(toUser());
        //System.out.println("Here and size is "+users.size());
    }
    /*public void paint(Graphics g)
    {
        list = new JList (toUser());
        JScrollPane pane = new JScrollPane (list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBounds(500, 0, 180, 660);
        add(pane);
        g.setColor(new Color(204, 204, 204));
        g.fillRect(0, 0, 700, 700);
        g.setFont(new Font("Times New Roman", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString("Chat", 10, 50);
        setVisible(true);
        //g.drawImage(buffer, 0, 0, null);
    }*/
    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
}
