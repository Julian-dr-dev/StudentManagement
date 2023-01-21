package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

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

        addUserToDb(name, phoneNum, address, username, password);

    }
    private User addUserToDb(String name, String phoneNum, String address, String username, String password)
    {
        User user = null;


        return user;
    }


    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
    }
}
