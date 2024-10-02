package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JLabel jName;
    private JPanel MainPanel;
    private JButton button1;
    private JTextField textField1;

    public Home() {
        setContentPane(MainPanel);
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new JDialog(Home.this).setSize(300, 300);
                JOptionPane.showConfirmDialog(Home.this, "Sesión iniciada como " + "Fabian");
            }
        });
    }
}
