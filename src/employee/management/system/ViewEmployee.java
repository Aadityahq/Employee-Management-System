package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewEmployee extends JFrame  implements ActionListener {

    JTable table;

    Choice choiceEmp;

    JButton searchBtn, print, update, back;

    ViewEmployee()
    {
        getContentPane().setBackground(new Color(255, 131, 122));
        JLabel search = new JLabel("Search by employee id");
        search.setBounds(20, 20, 150, 20);
        add(search);

        choiceEmp = new Choice();
        choiceEmp.setBounds(180, 20, 150, 20);
        add(choiceEmp);

        //Showing employee id in search scroll pane
        try{
           Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            while(resultSet.next())
            {
                choiceEmp.add(resultSet.getString("empID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        //Showing data in a table
        table = new JTable();
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            e.printStackTrace();
        }

        //table scroll pane
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 600);
        add(jp);

        //search button
        searchBtn = new JButton("Search");
        searchBtn.setBounds(20, 70, 80, 20);
        searchBtn.addActionListener(this);
        add(searchBtn);

        //print button
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        //update button
        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        //back button
        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);



        setSize(900, 700);
        setLayout(null);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == searchBtn)
        {
            String query = "select * from employee where empID = '" + choiceEmp.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == print)
        {
            try{
                table.print();
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == update)
        {
            setVisible(false);
            new UpdateEmployee(choiceEmp.getSelectedItem());

        }
        else
        {
            setVisible(false);
            new Home();
        }


    }




public static void main(String[] args) {
        try {
            // Ensure button colors work on macOS
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ViewEmployee();

    }
}

