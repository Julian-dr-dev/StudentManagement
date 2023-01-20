package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog {
    private JTextField txtName;
    private JTextField numberTxt;
    private JTextField txtAddress;
    private JTextField txtUsername;
    private JTextField txtPW;
    private JTextField txtConfirmPw;
    private JButton registerBtn;
    private JButton cancelBtn;
    private JPanel registerPanel;

    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Create a enw account");
        setContentPane(registerPanel);
        setMaximumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void registerUser() {
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
    }
}
