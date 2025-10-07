package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    Login() {
        setLayout(null);
        setSize(600, 350); // Bigger frame to fit images
        setLocation(350, 200);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
        JLabel pageBackgroundImg = new JLabel(new ImageIcon(i2));
        pageBackgroundImg.setBounds(0, 0, 1000, 600);
        add(pageBackgroundImg);

        // Username label and textfield
        JLabel username = new JLabel("Username:");
        username.setBounds(50, 50, 100, 30);
        username.setForeground(Color.WHITE);
        pageBackgroundImg.add(username); // add to background so it appears on top

        tusername = new JTextField();
        tusername.setBounds(150, 50, 150, 30);
        pageBackgroundImg.add(tusername);

        // Password label and field
        JLabel password = new JLabel("Password:");
        password.setBounds(50, 100, 100, 30);
        password.setForeground(Color.WHITE);
        pageBackgroundImg.add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150, 100, 150, 30);
        pageBackgroundImg.add(tpassword);

        // Login button
        login = new JButton("LOGIN");
        login.setBounds(150, 160, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setOpaque(true);
        login.setBorderPainted(false);
        login.addActionListener(this);
        pageBackgroundImg.add(login);

        // Back button
        back = new JButton("BACK");
        back.setBounds(150, 210, 150, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);
        pageBackgroundImg.add(back);

        // Optional secondary icon image
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image icon2 = icon1.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        JLabel iconImg = new JLabel(new ImageIcon(icon2));
        iconImg.setBounds(400, 50, 400, 200);
        pageBackgroundImg.add(iconImg);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            // Ensure button colors work on macOS
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == login)
        {
            try{
                String username = tusername.getText();
                String password = new String(tpassword.getPassword());


                Conn con = new Conn();

                String query = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet resultSet = con.statement.executeQuery(query);

                if(resultSet.next())
                {
                    setVisible(false);
                    new Home();

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }
            }
            catch (Exception E)
            {
                E.printStackTrace();
            }
        }
        else if(e.getSource() == back)
        {
            System.exit(101);
        }

    }
}
