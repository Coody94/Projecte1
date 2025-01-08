/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.dialogs;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.milaifontanals.club.Categoria;
import org.milaifontanals.club.Equip;
import org.milaifontanals.club.GestorBDClubException;
import org.milaifontanals.club.GestorDBClubjdbc;
import org.milaifontanals.club.Temporada;

/**
 *
 * @author sepec
 */
public class NouEquipDialog extends JDialog {
    
    private JTextField nomField;
    private JComboBox<String> tipusComboBox;
    private JComboBox<Categoria> categoriaComboBox;
    private Equip createdEquip; // Objeto que se devolverá al cerrar la ventana
    
    private List<Categoria> llcat;

    public NouEquipDialog(Frame owner,GestorDBClubjdbc gBD,Temporada t) {
        super(owner, "Nou Equip", true); // Modal para bloquear la ventana principal
        setSize(400, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        try{
            llcat = gBD.getAllCategories();

           
           
        } catch (GestorBDClubException ex) {
            JOptionPane.showMessageDialog(this, "Error en carregar les dades");
        }
        
        

        // Componentes
        JLabel nomLabel = new JLabel("Nom:");
        nomField = new JTextField(15);

        JLabel tipusLabel = new JLabel("Tipus:");
        String[] tipusOptions = {"Masculí", "Femení", "Mixt"};
        tipusComboBox = new JComboBox<>(tipusOptions);

        JLabel categoriaLabel = new JLabel("Categoria:");
        categoriaComboBox = new JComboBox<>(llcat.toArray(new Categoria[0]));

        JButton cancelarButton = new JButton("Cancelar");
        JButton crearButton = new JButton("Crear");

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(nomLabel, gbc);
        gbc.gridx = 1;
        add(nomField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(tipusLabel, gbc);
        gbc.gridx = 1;
        add(tipusComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(categoriaLabel, gbc);
        gbc.gridx = 1;
        add(categoriaComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelarButton);
        buttonPanel.add(crearButton);
        add(buttonPanel, gbc);

        // Acciones de botones
        cancelarButton.addActionListener(e -> {
            createdEquip = null; // No se crea el objeto
            dispose(); // Cierra la ventana
        });

        crearButton.addActionListener(e -> {
            
            if(nomField.getText().length()>1){
                String nom = nomField.getText();
                String tipus = "error";
                switch ((String) tipusComboBox.getSelectedItem()) {
                    case "Masculí":
                        tipus="H";
                        break;
                    case"Femení":
                        tipus="D";
                        break;
                    case "Mixt":
                        tipus="M";
                        break;         
                }
                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();


                createdEquip = new Equip(nom, tipus, t,categoria); 

                try{
                    createdEquip.setId( gBD.afegirEquip(createdEquip));
                    gBD.confirmarCanvis();
                }catch(Exception ex){
                }

                dispose(); // Cierra la ventana 
            }else{
                JOptionPane.showMessageDialog(this, "El nom es obligatori");
            }
            
            
        });

        setLocationRelativeTo(owner);
    }

    public Equip showDialog() {
        setVisible(true);
        return createdEquip; // Devuelve el objeto creado o null
    }
    
}
