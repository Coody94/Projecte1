/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.TableModel;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.milaifontanals.club.Jugador;
import org.milaifontanals.club.JugadorAuxEquip;
import org.milaifontanals.dialogs.GestionarJugadorsDialog;

/**
 *
 * @author sepec
 */
public class JugadorsDialogTableModel extends AbstractTableModel{
    private final String[] columnas = {"Nom", "Titular"};
    private GestionarJugadorsDialog gestJD;
    private List<JugadorAuxEquip> jugadors;
    
    public JugadorsDialogTableModel(List<JugadorAuxEquip> jugadors,GestionarJugadorsDialog gestJD) {
        this.jugadors = jugadors;
        this.gestJD=gestJD;
    }

    @Override
    public int getRowCount() {
        return jugadors.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length; 
    }
    
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Especifica que la columna booleana es de tipo Boolean
        return (columnIndex == 1) ? Boolean.class : String.class;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Solo la columna booleana es editable
        return columnIndex == 1;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        JugadorAuxEquip jugador = jugadors.get(rowIndex);
        switch (columnIndex) {
            case 0:break;
            case 1:
                //jugador.setTitular((Boolean) aValue);
                // Ejecuta una función cuando cambia el valor del checkbox
                canviarTitularitat(rowIndex, (Boolean) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setJugadors(List<JugadorAuxEquip> jugadors) {
        this.jugadors = jugadors;
        fireTableDataChanged(); 
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JugadorAuxEquip jugador = jugadors.get(rowIndex);
        switch (columnIndex) {
            case 0: return jugador.getJugador().toString(); 
            case 1: return jugador.isTitular();  
            default: return null;
        }
    }
    
     private void canviarTitularitat(int rowIndex, boolean newValue) {

         gestJD.canviarTitularitat(rowIndex, newValue);
         
         
    }
    
    
}
