/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.dialogs;

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

/**
 *
 * @author sepec
 */
public class EditarEquipDialog extends JDialog{
    
    private JTextField nomField;
    private Equip editedEquip; 
    private boolean isDeleted = false; 

    public EditarEquipDialog(Frame owner, Equip equip,GestorDBClubjdbc gBD) {
        super(owner, "Editar Equip", true);
        setSize(400, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        editedEquip = equip;
        
        JLabel nomLabel = new JLabel("Nom:");
        nomField = new JTextField(15);
        nomField.setText(equip.getNom()); 

        JButton eliminarButton = new JButton("Eliminar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton guardarButton = new JButton("Guardar");

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(nomLabel, gbc);
        gbc.gridx = 1;
        add(nomField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(eliminarButton);
        buttonPanel.add(cancelarButton);
        buttonPanel.add(guardarButton);
        add(buttonPanel, gbc);

        // Accions dels botons
        eliminarButton.addActionListener(e -> {
            
            boolean del = false;
            int num=0;
            try{
                List<Integer> llids = gBD.getJugadorsEquip(editedEquip);
                
                num = llids.size();
                if(num>0){
                    JOptionPane.showMessageDialog(this, "ne es pot eliminar un equip amb jugadors");
                }else{
                    del=gBD.eliminarEquip(editedEquip);
                    gBD.confirmarCanvis();
                }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error en carregar les dades");
            }
            if(!del && !(num>0)){
                JOptionPane.showMessageDialog(this, "Error en eliminar");
                
            }else{
                editedEquip = null; 
                dispose();
            }
           
        });

        cancelarButton.addActionListener(e -> {
            dispose();
        });

        guardarButton.addActionListener(e -> {
            String nom = nomField.getText();
            editedEquip.setNom(nom);
            boolean mod = false;
            try{
                mod = gBD.modificarEquip(editedEquip);
                gBD.confirmarCanvis();
                
            }catch(Exception ex){
                
            }
            if(mod){
                
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Error en modificar");
            }
            
        });

        setLocationRelativeTo(owner);
    }

    public Equip showDialog() {
        setVisible(true);
        return editedEquip; 
    }

   
    
}
