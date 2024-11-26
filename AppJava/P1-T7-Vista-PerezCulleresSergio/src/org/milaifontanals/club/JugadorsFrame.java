/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sepec
 */
public class JugadorsFrame extends JFrame{
    private final MainFrame mainFrame;
    
    
    public JugadorsFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        
        setTitle("Gestió Jugadors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panell superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("← Equips");
        topPanel.add(backButton);

        // Panell central
        String[] columnNames = {"Nom", "Cognom", "Data Naixement", "Sexe"};
        Object[][] data = {
                {"Roman", "Huerta", "10-01-2010", "H"},
                {"Aitana", "Rial", "10-01-2010", "D"},
                {"Sandra", "Pacheco", "10-01-2010", "D"},
                {"Alfredo", "Arnaiz", "10-01-2010", "H"},
                {"Adolfo", "Gonzales", "10-01-2010", "H"},
                {"Gloria", "Holgado", "10-01-2010", "D"}
        };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panell de la dreta
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rightPanel.add(new JLabel("Nom:"));
        JTextField nameField = new JTextField(10);
        rightPanel.add(nameField);

        rightPanel.add(new JLabel("Cognom:"));
        JTextField cognomField = new JTextField(10);
        rightPanel.add(cognomField);

        rightPanel.add(new JLabel("Sexe:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleRadio = new JRadioButton("H");
        JRadioButton femaleRadio = new JRadioButton("D");
        JRadioButton otherRadio = new JRadioButton("O");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        rightPanel.add(genderPanel);

        rightPanel.add(new JLabel("Categoria:"));
        JComboBox<String> categoriaComboBox = new JComboBox<>(new String[]{"Cadet", "Juvenil", "Senior"});
        rightPanel.add(categoriaComboBox);

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
        JButton editarButton = new JButton("Editar");
        JButton exportarButton = new JButton("Exportar Jugadors");

        bottomPanel.add(nuevoButton);
        bottomPanel.add(editarButton);
        bottomPanel.add(exportarButton);

        // Agregar panells al jframe
        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        backButton.addActionListener(e -> {
            this.setVisible(false); // Oculta el JugadorsFrame
            mainFrame.setVisible(true); // Mostra el MainFrame
        });
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
