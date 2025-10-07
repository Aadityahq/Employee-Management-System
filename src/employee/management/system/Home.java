package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {



    Home()
    {

        //Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1100, 630);
        add(img);

        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(300, 155, 500, 40);
        heading.setFont(new Font("Rale way", Font.BOLD, 25));
        img.add(heading);

        JButton addEmployee = new JButton("Add Employee");
        addEmployee.setBounds(335, 270, 150, 40);
        addEmployee.setForeground(Color.WHITE);
        addEmployee.setBackground(Color.black);
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployee();
                setVisible(false);

            }
        });
        img.add(addEmployee);

        JButton viewEmployee = new JButton("View Employee");
        viewEmployee.setBounds(565, 270, 150, 40);
        viewEmployee.setForeground(Color.WHITE);
        viewEmployee.setBackground(Color.black);
        viewEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployee();
                setVisible(false);
            }
        });
        img.add(viewEmployee);

        JButton removeEmployee = new JButton("Remove Employee");
        removeEmployee.setBounds(335, 370, 150, 40);
        removeEmployee.setForeground(Color.WHITE);
        removeEmployee.setBackground(Color.black);
        removeEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveEmployee();
                setVisible(false);

            }
        });
        img.add(removeEmployee);

        JButton quit = new JButton("Quit");
        quit.setBounds(565, 370, 150, 40);
        quit.setForeground(Color.WHITE);
        quit.setBackground(Color.BLACK);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // This will close the application
            }
        });
        img.add(quit);



        setSize(1100, 630);
        setLocation(85, 100);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        try {
            // Ensure button colors work on macOS
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Home();

    }
}
