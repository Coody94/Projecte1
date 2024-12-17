/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.milaifontanals.club;


import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author sepec
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static GestorDBClubjdbc gBD;
    
    public static void main(String[] args) {
        //String nomClassePersistencia="org.milaifontanals.club.GestorDBClubjdbc";
        String nomClassePersistencia="";
        
        try{
            
        
        String nomFitxer = "clubJDBC.xml";
        Properties props = new Properties();
        props.loadFromXML(new FileInputStream(nomFitxer));
        
        nomClassePersistencia = props.getProperty("clase");
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        try {
            gBD = (GestorDBClubjdbc) Class.forName(nomClassePersistencia).newInstance();
            //gBD = new GestorDBClubjdbc();
            SwingUtilities.invokeLater(() -> new LoginFrame(gBD));
        } catch (Exception ex) {
            System.out.println(ex);
            
        }
        
         
    }
    

    private String infoError(Throwable ex) {
        String aux;
        String info = ex.getMessage();
        if (info != null) {
            info += "\n";
        }
        while (ex.getCause() != null) {
            aux = ex.getCause().getMessage();
            if (aux != null) {
                aux += "\n";
            }
            info = info + aux;
            ex = ex.getCause();
        }
        return info;
    }
    
    
}
