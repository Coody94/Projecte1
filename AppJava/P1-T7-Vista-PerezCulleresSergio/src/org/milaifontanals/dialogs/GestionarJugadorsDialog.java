/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.TableModel.JugadorsDialogTableModel;
import org.milaifontanals.club.Categoria;
import org.milaifontanals.club.Equip;
import org.milaifontanals.club.GestorBDClubException;
import org.milaifontanals.club.GestorDBClubjdbc;
import org.milaifontanals.club.Jugador;
import org.milaifontanals.club.JugadorAuxEquip;
import org.milaifontanals.club.Temporada;
import org.milaifontanals.helper.ConfirmDialogHelper;

/**
 *
 * @author sepec
 */
public class GestionarJugadorsDialog extends JDialog{
    private DefaultListModel<Jugador> disponiblesModel;
    //private DefaultTableModel seleccionadosModel;
    private JugadorsDialogTableModel seleccionadosModel;
    private JList<Jugador> disponiblesList;
    private JTable seleccionadosTable;

    private JButton btnDerecha, btnIzquierda;
    private JButton btnGuardar, btnCancelar;

    private ArrayList<String> seleccionados;
    
    private List<Jugador> disponibles;
    private Equip eq;
    private GestorDBClubjdbc gBD;
    
    
    //public GestionarJugadorsDialog(Frame owner, ArrayList<String> disponibles, ArrayList<String> seleccionadosExistentes)
    public GestionarJugadorsDialog(Frame owner,GestorDBClubjdbc gBD,  Equip eq, Temporada t) {
        super(owner, "Gestionar Jugadors Equip", true);
        this.eq=eq;
        this.gBD=gBD;
        setSize(600, 400);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(owner);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        
        disponibles = new ArrayList<>();
        
        try{
            List<Jugador>llaux1 = gBD.getJugadorsEquipTitulars(eq);
            for(Jugador j : llaux1){
                eq.getJugadors().add(new JugadorAuxEquip(j,true));
            }
            
            List<Jugador> llaux2 = gBD.getJugadorsEquipConvidats(eq);
            for(Jugador j : llaux2){
                eq.getJugadors().add(new JugadorAuxEquip(j,false));
            }
            
            Categoria c = gBD.getCategoria(eq.getCategoria().getId());
            int any=t.getAny_t()- c.getEdat_max();
            
            List<Jugador>llaux = gBD.getAllJugadorsFiltrat(any,eq.getTipus());
            for(Jugador j : llaux){
                if(llaux1.contains(j)||llaux2.contains(j)){
                    
                }else{
                    disponibles.add(j);
                }
            }
            
           
        } catch (GestorBDClubException ex) {
            JOptionPane.showMessageDialog(this, "Error en carregar les dades");
        }
        
        
        
        

        // taula disponibles
        disponiblesModel = new DefaultListModel<>();
        disponibles.forEach(disponiblesModel::addElement);
        disponiblesList = new JList<>(disponiblesModel);

        // Taula jugadors
        //seleccionadosModel = new DefaultTableModel(new String[]{"Nom", "Titular"}, 0) {
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                return columnIndex == 1 ? Boolean.class : String.class;
//            }
//        };
        seleccionadosModel = new JugadorsDialogTableModel(eq.getJugadors(),this);
//        for (JugadorAuxEquip jugador : eq.getJugadors()) {
//            seleccionadosModel.addRow(new JugadorAuxEquip[0]);
//        }
        seleccionadosTable = new JTable(seleccionadosModel);

        
        JScrollPane disponiblesScroll = new JScrollPane(disponiblesList);
        disponiblesScroll.setPreferredSize(new Dimension(150, 200));

        JScrollPane seleccionadosScroll = new JScrollPane(seleccionadosTable);
        seleccionadosScroll.setPreferredSize(new Dimension(300, 200));
        
        // Botons per pasar
        btnDerecha = new JButton("→");
        btnIzquierda = new JButton("←");

        // Botons Inferiors
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        // Layout de
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        //add(new JScrollPane(disponiblesList), gbc);
        add(disponiblesScroll, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1;
        add(btnDerecha, gbc);
        gbc.gridy = 1;
        add(btnIzquierda, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        //add(new JScrollPane(seleccionadosTable), gbc);
        add(seleccionadosScroll,gbc);
        
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
        btnCancelar.addActionListener(e -> CancelarSeleccion());
        

        
    }

 private void afegirJugador() {
    int selectedIndex = disponiblesList.getSelectedIndex();
    if (selectedIndex != -1) {
        Jugador jugador = disponiblesModel.getElementAt(selectedIndex);
        
        
        try{
            gBD.afegirJugadorAEquip(jugador, eq, false);
            disponiblesModel.remove(selectedIndex);
            disponibles.remove(jugador);
            JugadorAuxEquip jaux = new JugadorAuxEquip(jugador, false);
            eq.getJugadors().add(jaux);
            seleccionadosModel.setJugadors(eq.getJugadors());
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error en afegir el jugador");
        }
        
        
        
        
    }
}

private void retirarJugador() {
    int selectedRow = seleccionadosTable.getSelectedRow();
    if (selectedRow != -1) {
        
        JugadorAuxEquip jaux =eq.getJugadors().get(selectedRow);
        try{
            gBD.eliminarJugadorEquip(jaux.getJugador(), eq);
            eq.getJugadors().remove(selectedRow);
            seleccionadosModel.setJugadors(eq.getJugadors());
            
            disponibles.add(jaux.getJugador());
            disponiblesModel.addElement(jaux.getJugador());
            
   
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error en afegir el jugador");
        }
        

    }
}

    private void guardarSeleccion() {
        try{
            gBD.confirmarCanvis();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error en guardar els canvis");
        }
        
        dispose(); 
    }
    private void CancelarSeleccion() {
        try{
            gBD.desferCanvis();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error en desfer els canvis");
        }
        
        dispose(); 
    }

    public ArrayList<String> getSeleccionados() {
        return seleccionados;
    }
    
    
    public void canviarTitularitat(int rowIndex, boolean newValue) {

        Boolean fet=false;
        try{
            fet= gBD.actualizarTitularitat(eq.getJugadors().get(rowIndex).getJugador(), eq, newValue);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error en actualizar titularitat");
        }
        if(!fet && newValue){
            JOptionPane.showMessageDialog(this, "No es pot fer titular ja es titular de un altre equip ");
        }else if(!fet && !newValue){
           JOptionPane.showMessageDialog(this, "Error al cambiar a convidat "); 
        }else{
            eq.getJugadors().get(rowIndex).setTitular(newValue);
            seleccionadosModel.setJugadors(eq.getJugadors());
         
        }
            
      
         
    }
    

}
