package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice choiceEMPID;
    JButton delete, back;

    RemoveEmployee()
    {
        //Employee id label
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        //Employeeid choice
        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200, 50, 150, 30);
        add(choiceEMPID);

        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");

            //get employee id data from the database table
            while(resultSet.next())
            {
                choiceEMPID.add(resultSet.getString("empID"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Name label
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        //label name and id
        JLabel textName = new JLabel();
        textName.setBounds(200, 100, 100, 30);
        add(textName);

        //phone number visible label
        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhone);

        //phone label from the table
        JLabel textPhone = new JLabel();
        textPhone.setBounds(200, 150, 100, 30);
        add(textPhone);


        //Email label
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50, 200, 100, 30);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelEmail);

        //email from the database table
        JLabel textEmail = new JLabel();
        textEmail.setBounds(200, 200, 150, 30);
        add(textEmail);


        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee where empID = '"+choiceEMPID.getSelectedItem()+"'");
            while (resultSet.next())
            {
                textName.setText(resultSet.getString("name"));
                textPhone.setText(resultSet.getString("phone"));
                textEmail.setText(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    Conn c = new Conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from employee where empID = '"+choiceEMPID.getSelectedItem()+"'");
                    while (resultSet.next())
                    {
                        textName.setText(resultSet.getString("name"));
                        textPhone.setText(resultSet.getString("phone"));
                        textEmail.setText(resultSet.getString("email"));
                    }
                }catch (Exception E)
                {
                    E.printStackTrace();
                }
            }
        });

        //delete button
        delete = new JButton("DELETE");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);


        //back button
        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        //Icon image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(500, 80, 200, 200);
        add(img);

        //Background Image
        ImageIcon backImg = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image backImg2 = backImg.getImage().getScaledInstance(800, 630, Image.SCALE_DEFAULT);
        ImageIcon backImg3 = new ImageIcon(backImg2);
        JLabel backgroundImg = new JLabel(backImg3);
        backgroundImg.setBounds(0, 0, 800, 630);
        add(backgroundImg);


        //frame settings
        setSize(800, 400);
        setLocation(300, 150);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == delete)
        {
            try{
                Conn c = new Conn();
                String query = "delete from employee where empID = '"+choiceEMPID.getSelectedItem()+"'";
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Employee Details deleted Successfully");
                setVisible(false);
                new Home();
            }catch (Exception E){
                E.printStackTrace();
            }
        }
        else
        {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        try {
            // Ensure button colors work on macOS
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        new RemoveEmployee();

    }
}
