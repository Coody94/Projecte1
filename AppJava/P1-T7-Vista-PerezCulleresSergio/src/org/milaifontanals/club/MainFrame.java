/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

/**
 *
 * @author sepec
 */
public class MainFrame extends JFrame{

    private JugadorsFrame jugadorsFrame;
    
    public MainFrame() {

        setTitle("Gestió Equips");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panell superior 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] temporadas = {"24-25", "25-26"};
        JComboBox<String> temporadaComboBox = new JComboBox<>(temporadas);
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
        String[] columnNames = {"Categoria", "Nom", "Tipus", "Nº jugadors"};
        Object[][] data = {
                {"Cadet", "Cadet A", "Masculí", 3},
                {"Cadet", "Cadet B", "Femení", 3},
                {"Cadet", "Cadet C", "Mixt", 6},
                {"Juvenil", "Juvenil A", "Masculí", 3},
                {"Juvenil", "Senior A", "Masculí", 3}
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

        rightPanel.add(new JLabel("Categoria:"));
        JComboBox<String> categoriaComboBox = new JComboBox<>(new String[]{"Cadet", "Juvenil", "Senior"});
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
        JComboBox<String> totesComboBox = new JComboBox<>(new String[]{"Totes"});
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
                jugadorsFrame = new JugadorsFrame(this); // Crea el frame de jugadores si no existe
            }
            this.setVisible(false); // Oculta el MainFrame
            jugadorsFrame.setVisible(true); // Mostra el JugadorsFrame
        });
        
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
