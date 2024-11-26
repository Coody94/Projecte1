/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author sepec
 */
public class LoginFrame extends JFrame{
        public LoginFrame() {
        
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());

        // Panel per al usuari i la contraseña
        JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel userLabel = new JLabel("Usuari:");
        JLabel passLabel = new JLabel("Contrasenya:");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);

        // Botón de login
        JButton loginButton = new JButton("Acceder");
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            char[] password = passField.getPassword();
            // Validacio 
            if (!username.isEmpty() && password.length > 0) {
                new MainFrame(); // Anar al mainFrame
                dispose(); // Tencar finestra login
            } else {
                JOptionPane.showMessageDialog(this, "Falta usuari o contrasseña");
            }
        });

        add(loginPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
