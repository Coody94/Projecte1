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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.club.Equip;

/**
 *
 * @author sepec
 */
public class GestionarJugadorsDialog extends JDialog{
    private DefaultListModel<String> disponiblesModel;
    private DefaultTableModel seleccionadosModel;
    private JList<String> disponiblesList;
    private JTable seleccionadosTable;

    private JButton btnDerecha, btnIzquierda;
    private JButton btnGuardar, btnCancelar;

    private ArrayList<String> seleccionados;
    
    //public GestionarJugadorsDialog(Frame owner, ArrayList<String> disponibles, ArrayList<String> seleccionadosExistentes)
    public GestionarJugadorsDialog(Frame owner, Equip eq) {
        super(owner, "Gestionar Jugadors Equip", true);
        setSize(600, 400);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(owner);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // taula disponibles
        disponiblesModel = new DefaultListModel<>();
        disponibles.forEach(disponiblesModel::addElement);
        disponiblesList = new JList<>(disponiblesModel);

        // Taula jugadors
        seleccionadosModel = new DefaultTableModel(new String[]{"Nom", "Titular"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 1 ? Boolean.class : String.class;
            }
        };
        for (String jugador : seleccionadosExistentes) {
            seleccionadosModel.addRow(new Object[]{jugador, false});
        }
        seleccionadosTable = new JTable(seleccionadosModel);

        // Botons per pasar
        btnDerecha = new JButton("→");
        btnIzquierda = new JButton("←");

        // Botons Inferiors
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        // Layout de
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        add(new JScrollPane(disponiblesList), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1;
        add(btnDerecha, gbc);
        gbc.gridy = 1;
        add(btnIzquierda, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        add(new JScrollPane(seleccionadosTable), gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.EAST;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);
        add(buttonPanel, gbc);

        // Acciones de las flechas
        btnDerecha.addActionListener(e -> afegirJugador());
        btnIzquierda.addActionListener(e -> retirarJugador());

        // Acciones de botones inferiores
        btnGuardar.addActionListener(e -> guardarSeleccion());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void afegirJugador() {
        int selectedIndex = disponiblesList.getSelectedIndex();
        if (selectedIndex != -1) {
            String jugador = disponiblesModel.getElementAt(selectedIndex);
            disponiblesModel.removeElementAt(selectedIndex);
            seleccionadosModel.addRow(new Object[]{jugador, false});
        }
    }

    private void retirarJugador() {
        int selectedRow = seleccionadosTable.getSelectedRow();
        if (selectedRow != -1) {
            String jugador = (String) seleccionadosModel.getValueAt(selectedRow, 0);
            seleccionadosModel.removeRow(selectedRow);
            disponiblesModel.addElement(jugador);
        }
    }

    private void guardarSeleccion() {
        seleccionados = new ArrayList<>();
        for (int i = 0; i < seleccionadosModel.getRowCount(); i++) {
            String jugador = (String) seleccionadosModel.getValueAt(i, 0);
            seleccionados.add(jugador);
        }
        dispose(); 
    }

    public ArrayList<String> getSeleccionados() {
        return seleccionados;
    }
}
