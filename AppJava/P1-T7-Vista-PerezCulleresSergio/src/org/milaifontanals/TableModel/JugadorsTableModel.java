/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.TableModel;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.milaifontanals.club.Equip;
import org.milaifontanals.club.GestorDBClubjdbc;
import org.milaifontanals.club.Jugador;

/**
 *
 * @author sepec
 */
public class JugadorsTableModel extends AbstractTableModel{
    
    private final String[] columnas = {"Nom", "Cognom","Data Naixement","Sexe"};
    private List<Jugador> jugadors;
    SimpleDateFormat sdf ;
    
    public JugadorsTableModel(List<Jugador> jugadors) {
        this.jugadors = jugadors;
        sdf = new SimpleDateFormat("MM-dd-yyyy");
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
    
    public void setEquips(List<Jugador> jugadors) {
        this.jugadors = jugadors;
        fireTableDataChanged(); 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jugador jugador = jugadors.get(rowIndex);
        switch (columnIndex) {
            case 0: return jugador.getNom(); 
            case 1: return jugador.getCognom();  
            case 2: return sdf.format(jugador.getData_naix());
            case 3:
                String s="";
                if(jugador.getSexe().equals("H"))
                    s="Home";
                else if(jugador.getSexe().equals("D"))
                    s="Dona";
                else
                    s="";
                
                return s;
            default: return null;
        }
    }
    
}
