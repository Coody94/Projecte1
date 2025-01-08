/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.dialogs;

import com.toedter.calendar.JYearChooser;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.milaifontanals.club.Equip;
import org.milaifontanals.club.GestorDBClubjdbc;
import org.milaifontanals.club.Temporada;

/**
 *
 * @author sepec
 */
public class NovaTemporadaDialog extends JDialog{
    
    private JTextField nomField;
    private Equip editedEquip; 
    private boolean isDeleted = false; 
    private JYearChooser chosserAny;
    private Temporada temp;

    public NovaTemporadaDialog(Frame owner,GestorDBClubjdbc gBD) {
        super(owner, "Nova Temporada", true);
        setSize(400, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        chosserAny = new JYearChooser();
        
        JLabel nomLabel = new JLabel("Any:");


        JButton cancelarButton = new JButton("Cancelar");
        JButton guardarButton = new JButton("Guardar");

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(nomLabel, gbc);
        gbc.gridx = 1;
        add(chosserAny, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(cancelarButton);
        buttonPanel.add(guardarButton);
        add(buttonPanel, gbc);

        // Accions dels botons

        cancelarButton.addActionListener(e -> {
            dispose();
        });

        guardarButton.addActionListener(e -> {
            boolean mod = false;
            boolean trobat = false;
            
            try{
                List<Temporada>ll = gBD.getTemporades();
                
                Temporada t = new Temporada();
                t.setAny_t(chosserAny.getYear());
                for(Temporada tmp : ll){
                    if(t.getAny_t()==temp.getAny_t()){
                        trobat=true;
                    }
                }
                if(!trobat){
                    mod =gBD.afegirTemporad(t);
                    temp=t;
                    gBD.confirmarCanvis();
                }else{
                    JOptionPane.showMessageDialog(this, "Temporada ya existent");
                }
                
                
            }catch(Exception ex){
                
            }
            if(mod){
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Error en afegir temporada");
            }
            
        });

        setLocationRelativeTo(owner);
    }

    public Temporada showDialog() {
        setVisible(true);
        return temp; 
    }

   
    
}
