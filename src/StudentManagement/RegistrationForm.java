package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static java.sql.DriverManager.*;

public class RegistrationForm extends JDialog {
    private JTextField txtName;
    private JTextField numberTxt;
    private JTextField txtAddress;
    private JTextField txtUsername;
    private JButton registerBtn;
    private JButton cancelBtn;
    private JPanel registerPanel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Create a enw account");
        setContentPane(registerPanel);
        setMaximumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);

    }

    private void registerUser() {
        String password = String.valueOf(passwordField1.getPassword());
        String confirmPw = String.valueOf(passwordField2.getPassword());
        String name = txtName.getText();
        String phoneNum = numberTxt.getText();
        String address = txtAddress.getText();
        String username = txtUsername.getText();

        if (name.isEmpty()|| address.isEmpty() || phoneNum.isEmpty() || username.isEmpty() || password.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Please try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPw))
        {
            JOptionPane.showMessageDialog(this, "Password does not match", "Please try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDb(name, phoneNum, address, username, password);
        if (user != null)
        {
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Failed to register new user", "Please try again", JOptionPane.ERROR_MESSAGE);

        }

    }

    public User user;

    private User addUserToDb(String name, String phoneNum, String address, String username, String password)
    {

        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statmt = con.createStatement();
            String sql = "INSERT INTO users (name, phoneNum, address, password) " + "VALUES (?, ?, ?, ?)";
            PreparedStatement prepStatmt = con.prepareStatement(sql);
            prepStatmt.setString(1, name);
            prepStatmt.setString(2, phoneNum);
            prepStatmt.setString(3, address);
            prepStatmt.setString(4, password);

            //insertion
            int rowsAdded = prepStatmt.executeUpdate();
            if (rowsAdded > 0)
            {
                user = new User();
                user.name = name;
                user.phoneNum = phoneNum;
                user.address = address;
                user.password = password;
            }

            statmt.close();
            con.close();

        } catch (Exception e){
            e.printStackTrace();
        }


        return user;
    }


    public static void main(String[] args) {

        RegistrationForm myForm = new RegistrationForm(null);

        User user = myForm.user;

        if (user != null)
        {
            System.out.println("Successful registration of: " + user.name);

        }
        else
        {
            System.out.println("Registration cancelled");
        }

    }
}
