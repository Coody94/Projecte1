/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.milaifontanals.club.Equip;
import org.milaifontanals.club.GestorDBClubjdbc;

/**
 *
 * @author sepec
 */
public class EquipTableModel extends AbstractTableModel{
    
    private final String[] columnas = {"Categoria", "Nom","Tipus","NºJugadors"};
    private List<Equip> equips;
    private GestorDBClubjdbc gBD;
    
    public EquipTableModel(List<Equip> equips,GestorDBClubjdbc gBD) {
        this.equips = equips;
        this.gBD= gBD;
    }

    @Override
    public int getRowCount() {
        return equips.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length; 
    }
    
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    public void setEquips(List<Equip> equips) {
        this.equips = equips;
        fireTableDataChanged(); 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Equip equip = equips.get(rowIndex);
        switch (columnIndex) {
            case 0: return equip.getCategoria().getNom(); 
            case 1: return equip.getNom();   
            case 2:
                String s="";
                if(equip.getTipus().equals("H"))
                    s="Masculi";
                else if(equip.getTipus().equals("D"))
                    s="Femeni";
                else
                    s="Mixte";
                
                return s;
            case 3:
                Integer num = 0;
                try{
                    List<Integer> llids = gBD.getJugadorsEquip(equip);
                    num = llids.size();
                }catch(Exception ex){}
                return num;
            default: return null;
        }
    }
    
}
