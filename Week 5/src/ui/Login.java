package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField userField;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton loginButton;


    public Login() {
        setContentPane(panel1);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = "1234321";
                String userName = "fabianva";

                if (userField.getText().equals(userName) && passwordField1.getText().equals(password)) {
                    JOptionPane.showMessageDialog(Login.this, "Sesión iniciada como " + userName);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectos ");
                }
            }
        });
    }
}
