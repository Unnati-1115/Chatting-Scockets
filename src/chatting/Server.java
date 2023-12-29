
package chatting;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener{
    JTextField text; 
    JPanel a1;
    static JFrame f= new JFrame();
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    Server(){

               
        //Layout is used to set components over the frame.
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,68));
        
        //Using SetBounds to set the co-ordinates to see in order where
        //to put the above p1.
        p1.setBounds(0,0, 450,70);
        //Setting layout null so that setBounds can be used further in the application.
        p1.setLayout(null);
        f.add(p1);
        
        //Including icons.
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        //Scaling the image to fit in panel.
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        //Creating i3 because scaled image cannot be given to JLabel directly.
        ImageIcon i3=new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        
        //Letting know what button the functionality has to work.
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        //Including Profile Picture.
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/model1.png"));
        //Scaling the image to fit in panel.
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        //Creating i6 because scaled image cannot be given to JLabel directly.
        ImageIcon i6=new ImageIcon(i5);
        JLabel pp = new JLabel(i6);
        pp.setBounds(40,10,50,50);
        p1.add(pp);
        
        
        //Including Telephone Icon.
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        //Scaling the image to fit in panel.
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        //Creating i6 because scaled image cannot be given to JLabel directly.
        ImageIcon i9=new ImageIcon(i8);
        JLabel tele = new JLabel(i9);
        tele.setBounds(300,20,30,30);
        p1.add(tele);
        
        //Including VideoCam Icon.
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        //Scaling the image to fit in panel.
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        //Creating i6 because scaled image cannot be given to JLabel directly.
        ImageIcon i12=new ImageIcon(i11);
        JLabel vid = new JLabel(i12);
        vid.setBounds(350,20,30,30);
        p1.add(vid);
        
        //Including MoreAbout Icon.
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        //Scaling the image to fit in panel.
        Image i14 = i13.getImage().getScaledInstance(18, 25, Image.SCALE_DEFAULT);
        //Creating i6 because scaled image cannot be given to JLabel directly.
        ImageIcon i15=new ImageIcon(i14);
        JLabel mor = new JLabel(i15);
        mor.setBounds(405,20,10,25);
        p1.add(mor);
        
        
        //Setting the name of user
        JLabel name = new JLabel("Colt");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF" , Font.BOLD, 18));
        p1.add(name);
        
        
        //Setting the status of user
        JLabel status = new JLabel("Online");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF" , Font.BOLD, 14));
        p1.add(status);
        
        
        //New Panel for text and message area.
        a1=new JPanel();
        a1.setBounds(5,75,440,500);
        f.add(a1);
        
        text=new JTextField();
        text.setBounds(5,575,310,40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);
        
        JButton send = new JButton("Send");
        send.setBounds(320,575,123,40);
        send.setBackground(new Color(7,94,68));
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setForeground(Color.WHITE); 
        f.add(send);
        send.addActionListener(this);
        
        
        //Sends message when enter is clicked.
        f.getRootPane().setDefaultButton(send);
        //Chatting Frame widhth and height.
        f.setSize(450,650);

        //Setting location where the frame will open, 200 from left and 50 from top.
        f.setLocation(200, 50);
        //Removing default java header.
        f.setUndecorated(true);
        //Setting background color
        f.getContentPane().setBackground(Color.WHITE);
        //By default frame is hidden, so we will now set it visible.
        f.setVisible(true);
    }
    //Creating the function to implement going back functionality to the arrow.
    public void actionPerformed(ActionEvent ae){
        try{
        String out=text.getText();
        
        //Since add does not take string as first param.
       
        JPanel p2=formatLabel(out);
       
        
        //Using Border layout so that messages sent are seen at the right handside.
        a1.setLayout(new BorderLayout());
        JPanel right=new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        
        //If the messages are more then it will go to new line.
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical, BorderLayout.PAGE_START);
        
        //Sending the message.
        dout.writeUTF(out);
        
        //Putting text field empty after send is clicked.
        text.setText("");
        
        //Using these functions so that we see the message in real time after clicking send.
        f.repaint();
        f.validate();
        f.invalidate();
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    //Function to format the messages sent.
    public static JPanel formatLabel(String out){
    JPanel panel=new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JLabel output = new JLabel("<html><p style=\"width: 150px\">"+out+"</p></html>");
    output.setFont(new Font("Tahoma", Font.PLAIN, 16));
    output.setBackground(new Color(37,211,102));
    output.setOpaque(true);
    //Providing padding to the text.
    output.setBorder(new EmptyBorder(15,15,15,40));
    panel.add(output);
    
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    
    return panel;
}
    public static void main(String args[]){
        Server s = new Server();
        try{
         ServerSocket skt = new ServerSocket(6001);
         while(true){
            Socket sa = skt.accept();
            //To receive message
            DataInputStream din= new DataInputStream(sa.getInputStream());
            dout= new DataOutputStream(sa.getOutputStream());
            //In order to receive infinite messages and send infinite we'll use a protocol.
            while(true){
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);
                
                //Received message to be added on the left.
                JPanel left= new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                f.validate();
                
            }
         }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
