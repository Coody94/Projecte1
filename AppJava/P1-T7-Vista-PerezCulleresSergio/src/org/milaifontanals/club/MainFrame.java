/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import org.milaifontanals.dialogs.EditarEquipDialog;
import org.milaifontanals.dialogs.NouEquipDialog;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.TableModel.EquipTableModel;

/**
 *
 * @author sepec
 */
public class MainFrame extends JFrame{

    private JugadorsFrame jugadorsFrame;
    private List<Temporada> lltemp; 
    private  EquipTableModel eqTaModel;
    private List<Categoria> llcat;
    
    public MainFrame(GestorDBClubjdbc gBD) {

        setTitle("Gestió Equips");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        
        //Recuperar temporadas y equips
        try{
            lltemp=gBD.getTemporades();
            
            
            
        } catch (GestorBDClubException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            llcat = gBD.getAllCategories();
            Categoria c = new Categoria();
            c.setId(-500);
            c.setNom("Totes");
            
            llcat.add(0, c);
           
           
        } catch (GestorBDClubException ex) {
            llcat = new ArrayList<>();
            Categoria c = new Categoria();
            c.setId(-500);
            c.setNom("Totes");
            llcat.add(c);
        }
        
        
        
        

        // Panell superior 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //String[] temporadas = {"24-25", "25-26"};
        JComboBox<Temporada> temporadaComboBox = new JComboBox<>(lltemp.toArray(new Temporada[0]));
        
        temporadaComboBox.addActionListener(e -> {
            Temporada seleccion = (Temporada) temporadaComboBox.getSelectedItem();
            
            eqTaModel.setEquips(seleccion.getEquips());
        
        });
        
        
        
        JButton novaButton = new JButton("Nova");
        JTextField anyField = new JTextField("2025", 5);
        JButton calendarioButton = new JButton("📅");
        JButton jugadoresButton = new JButton("Jugadors ➤");

        topPanel.add(new JLabel("Temporada:"));
        topPanel.add(temporadaComboBox);
        topPanel.add(novaButton);
        topPanel.add(anyField);
        topPanel.add(calendarioButton);
        topPanel.add(jugadoresButton);

        // Panell central 
//        String[] columnNames = {"Categoria", "Nom", "Tipus", "Nº jugadors"};
//        Object[][] data = {
//                {"Cadet", "Cadet A", "Masculí", 3},
//                {"Cadet", "Cadet B", "Femení", 3},
//                {"Cadet", "Cadet C", "Mixt", 6},
//                {"Juvenil", "Juvenil A", "Masculí", 3},
//                {"Juvenil", "Senior A", "Masculí", 3}
//        };
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        
        Temporada seleccion = (Temporada) temporadaComboBox.getSelectedItem();
        eqTaModel = new EquipTableModel(seleccion.getEquips(),gBD);
        JTable table = new JTable(eqTaModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panell de la dreta
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rightPanel.add(new JLabel("Nom:"));
        JTextField nameField = new JTextField(10);
        rightPanel.add(nameField);

        rightPanel.add(new JLabel("Categoria:"));
        JComboBox<Categoria> categoriaComboBox = new JComboBox<>(llcat.toArray(new Categoria[0]));
        rightPanel.add(categoriaComboBox);

        rightPanel.add(new JLabel("Tipus:"));
        JComboBox<String> tipusComboBox = new JComboBox<>(new String[]{"Totes", "Masculí", "Femení", "Mixt"});
        rightPanel.add(tipusComboBox);

        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton borrarButton = new JButton("Borrar");
        JButton aplicarButton = new JButton("Aplicar");
        rightButtonPanel.add(borrarButton);
        rightButtonPanel.add(aplicarButton);

        rightPanel.add(Box.createVerticalStrut(10)); // Espaciado
        rightPanel.add(rightButtonPanel);

        // Panell inferior 
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton nuevoButton = new JButton("Nou");
        JButton editarEquipoButton = new JButton("Editar Equip");
        JButton editarJugadoresButton = new JButton("Editar Jugadors");
        
        
        
        //botons
        
        nuevoButton.addActionListener(e -> {
            Temporada t = (Temporada) temporadaComboBox.getSelectedItem();
            NouEquipDialog dialog = new NouEquipDialog(this,gBD,t);
            Equip newEquip = dialog.showDialog(); // Abre el diálogo y espera un objeto

            if (newEquip != null) {
                t.getEquips().add(newEquip);
                eqTaModel.setEquips(seleccion.getEquips());
            }
        });
        
        editarEquipoButton.addActionListener(e -> {
            int i =table.getSelectedRow();
            Temporada t = (Temporada) temporadaComboBox.getSelectedItem();
            
            if(i!=-1){
                Equip eq = t.getEquips().get(i);
                EditarEquipDialog dialog = new EditarEquipDialog(this,eq,gBD);
                Equip newEquip = dialog.showDialog();

                if (newEquip != null && !newEquip.getNom().equals(eq.getNom())) {
                    t.getEquips().remove(i);
                    t.getEquips().add(i, newEquip);
                    eqTaModel.setEquips(seleccion.getEquips());
                }else if(newEquip == null){
                    t.getEquips().remove(i);
                    eqTaModel.setEquips(seleccion.getEquips());
                }  
            }
            
            
            
            
        });
        
        
        
        
        
        
        //Combo box categories Informe
        
        JComboBox<Categoria> totesComboBox = new JComboBox<>(llcat.toArray(new Categoria[0]));
        JButton informeButton = new JButton("Informe Jasper");

        bottomPanel.add(nuevoButton);
        bottomPanel.add(editarEquipoButton);
        bottomPanel.add(editarJugadoresButton);
        bottomPanel.add(totesComboBox);
        bottomPanel.add(informeButton);

        // Agregar panells al jframe
        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        jugadoresButton.addActionListener(e -> {
            if (jugadorsFrame == null) {
                jugadorsFrame = new JugadorsFrame(this,gBD); // Crea el frame de jugadores si no existe
            }
            this.setVisible(false); // Oculta el MainFrame
            jugadorsFrame.setVisible(true); // Mostra el JugadorsFrame
        });
        
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
