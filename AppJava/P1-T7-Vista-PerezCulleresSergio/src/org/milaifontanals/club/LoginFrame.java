/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        public LoginFrame(GestorDBClubjdbc gBD) {
        
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
            char[] password2 = passField.getPassword();
            String password = new String(password2);
            // Validacio 
            
            Usuari u=null;
            
            if (!username.isEmpty() && password2.length > 0) {
                try{
                    u = gBD.getUsuari(username);
                    
                }catch(Exception ex){
                    
                }
                if(u!=null){
                    String hash=null;
                    try{
                          hash = encriptarSHA1(password);
                    }catch(Exception ex){

                    }   
                    if(hash.equals(u.getPassword())){
                        new MainFrame(); // Anar al mainFrame
                        dispose(); // Tencar finestra login
                    }else{
                        JOptionPane.showMessageDialog(this, "Contrasenya incorrecte");
                    }

                    
                }else{
                    JOptionPane.showMessageDialog(this, "Usuari incorrecte");
                }
     
            } else {
                JOptionPane.showMessageDialog(this, "Falta usuari o contrasseña");
            }
        });

        add(loginPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
        
        
        
        
     public static String encriptarSHA1(String texto) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        
        byte[] hashBytes = digest.digest(texto.getBytes());
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
}
