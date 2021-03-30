package de.othr.vs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginDialog extends JFrame {

    JTextField usernameField;
    JTextField passwordField;
    JButton exitBtn;
    JButton loginBtn;

    public LoginDialog() {
        super("Anmelden");

        this.setLayout(new GridLayout(3,2));

        this.add(new JLabel("Username", JLabel.LEFT));
        this.usernameField = new JTextField();
        this.add(this.usernameField);

        this.add(new JLabel("Password", JLabel.LEFT));
        this.passwordField = new JTextField();
        this.add(this.passwordField);

        this.exitBtn = new JButton("Exit");
        this.exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitBtn);

        this.loginBtn = new JButton("Login");
        this.loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Username: " +
                    usernameField.getText());
                System.out.println("Password: " +
                    passwordField.getText());
            }
        });
        this.add(loginBtn);

        this.setPreferredSize(new Dimension(400, 100));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
