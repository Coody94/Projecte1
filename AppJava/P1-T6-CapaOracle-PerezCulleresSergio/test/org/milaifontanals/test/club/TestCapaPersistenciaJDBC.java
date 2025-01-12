/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.milaifontanals.test.club;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.milaifontanals.club.Categoria;
import org.milaifontanals.club.Equip;
import org.milaifontanals.club.GestorBDClubException;
import org.milaifontanals.club.GestorDBClubjdbc;
import org.milaifontanals.club.Jugador;
import org.milaifontanals.club.Temporada;
import org.milaifontanals.club.Usuari;

/**
 *
 * @author sepec
 */
public class TestCapaPersistenciaJDBC {

    /**
     * @param args the command line arguments
     */
    
    private static GestorDBClubjdbc gBD;
    
    /*
    getJugadorById;             ---Tested
    getAllJugadors;             ---Tested
    modificarEquip;             ---Tested
    actualizarTitularitat;      ---Tested
    afegirEquip;                ---Tested
    afegirJugador;              ---Tested
    afegirJugadorAEquip;        ---Tested
    eliminarEquip;              ---Tested
    getEquips;                  ---Tested
    getJugadorsEquips;          ---Tested
    getTemporades;              ---Tested
    getUsuari;                  ---Tested
    modificarJugador;           ---Tested
    getCategoria;               ---Tested
    getAllCategories;           ---Tested
    
    
    
    */
    
    
    
    
    
    public static void main(String[] args) {
       
         // 1. Crear gBD invocant el constructor.
        try {
            System.out.println("Intent de creació de la capa...");
            gBD = new GestorDBClubjdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");
        } catch (GestorBDClubException ex) {
            ex.printStackTrace();
            System.out.println("Problema en crear capa de persistència:");
            System.out.println(ex.getMessage());
            System.out.println("Avortem programa");
            return;
        }
        //getUsuari(gBD,"usuari");
        
        //allCategories(gBD);
        
        //List<Temporada> lltemp = temporades(gBD);
        
        
        /*
        Categoria c = categoriaById(gBD,2);
        Equip e= new Equip();
        e.setNom("Test");
        e.setCategoria(c);
        e.setTemporada(new Temporada(2024));
        e.setTipus("H");
        int id = afegirEquip(gBD,e);
        e.setId(id);
        eliminarEquip(gBD,e);
        */
        
        
        //List<Jugador> lljug = getAllJugadors(gBD);
        
        /*
        Jugador j = getJugadorsById(gBD, 12);
        modificarJugador(gBD, j);
        Jugador j2 = getJugadorsById(gBD, 12);
        */
        
        /*
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.NOVEMBER, 19, 0, 0, 0);
        Date date = calendar.getTime(); 
        Jugador jadd= new Jugador( "Test", "Test", "H",date , "11391888G", "ES39 0233 0402 9856 7350 3641", 0, "carret test", "08700", "igualada");
        int idj = afegirJugador(gBD,jadd);
        jadd.setId(idj);
        
        eliminarJugador(gBD, jadd);
        */
        
        /*
        Equip eq = new Equip();
        eq.setId(1);
        List<Integer> lljugequip = getJugadorsEquips(gBD, eq);
        getJugadorsById(gBD, lljugequip.get(1));
        */

        
        
        /**/
        Equip eqadd = new Equip();
        eqadd.setId(5);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2011, Calendar.NOVEMBER, 19, 0, 0, 0);
        Date date = calendar.getTime(); 
        Jugador jadd2= new Jugador( "Test", "Test", "H",date , "11392388G", "ES39 0233 0402 9856 4450 3641", 0, "carret test", "08700", "igualada");
        int idj = afegirJugador(gBD,jadd2);
        jadd2.setId(idj);
        
        addJugadorEquip(gBD,eqadd,jadd2,false);
        actualizarTitularitat(gBD,eqadd,jadd2,true);
        
        
        /*
        Equip eqmod = new Equip();
        eqmod.setId(5);
        eqmod.setNom("Senior 44");
        modificarEquip(gBD, eqmod);
        */
        
        
        try {
            
            //gBD.desferCanvis();
            //gBD.confirmarCanvis();
            gBD.tancarCapa();
             System.out.println("capa Tancada");
        } catch (GestorBDClubException ex) {
            System.out.println("Tancar capa");
        }
    }
    
    private static void getUsuari(GestorDBClubjdbc gBD,String login){
        System.out.println("Usuari");
        
        try {
            Usuari u = gBD.getUsuari(login);
            System.out.println(u.getLogin()+"-"+u.getPassword());
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en recuperar usuari:");
            System.out.println(ex.getMessage());
        }
    }
    
    private static void allCategories(GestorDBClubjdbc gBD){
        List<Categoria> llcat= new ArrayList<>();
        System.out.println("Categories");
        try {
            llcat = gBD.getAllCategories();
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en recuperar categories:");
            System.out.println(ex.getMessage());
        }
        
        for(Categoria c : llcat){
            System.out.println(c.getId()+", "+c.getNom()+"   "+c.getEdat_min()+"-"+c.getEdat_max());
        }
    }
    
    private static Categoria categoriaById(GestorDBClubjdbc gBD,int id){
        System.out.println("Categoria id="+id);
        Categoria c=null;
        try {
            c = gBD.getCategoria(id);
            System.out.println(c.getId()+", "+c.getNom()+"   "+c.getEdat_min()+"-"+c.getEdat_max());
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en recuperar categoria:");
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    
    
    private static List<Temporada> temporades(GestorDBClubjdbc gBD){
        List<Temporada> lltemp = new ArrayList<>();
        System.out.println("Temporades");
        
        try {
            lltemp=gBD.getTemporades();
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en recuperar temporades:");
            System.out.println(ex.getMessage());
        }
        for(Temporada t : lltemp){
            System.out.println(t.getAny_t()+"  Equips: "+t.getEquips().size());
        }
        
        
        return lltemp;
    }
    
    private static int afegirEquip(GestorDBClubjdbc gBD,Equip e){
        System.out.println("Afegir Equip");
        int i=0;
        try {
             i = gBD.afegirEquip(e);
            String tc  = i>0?"Afegit":"Error";
            System.out.println(tc+" id:"+i);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en afegir equip:");
            System.out.println(ex.getMessage());
        }
        return i;
    }

    private static List<Jugador> getAllJugadors(GestorDBClubjdbc gBD) {
       List<Jugador> lljug = new ArrayList<>();
       System.out.println("Jugadors");
        
        try {
            lljug = gBD.getAllJugadors();
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en recuperar jugadors:");
            System.out.println(ex.getMessage());
        }
        for(Jugador j : lljug){
            System.out.println(j.getNom()+" "+j.getCognom());
        }
        
       
       return lljug;
    }
    private static Jugador getJugadorsById(GestorDBClubjdbc gBD,int id) {
       Jugador j = null;
       System.out.println("Jugadors by id : "+id);
        
        try {
            j = gBD.getJugadorsById(id);
            System.out.println(j.getNom()+" "+j.getCognom());
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en recuperar jugadors by id:");
            System.out.println(ex.getMessage());
        }
        
        
       
       return j;
    }
    
    private static int afegirJugador(GestorDBClubjdbc gBD,Jugador j){
        System.out.println("Afegir Jugador");
        int id=0;
        try {
            id = gBD.afegirJugador(j);
            j.setId(id);
            String tc  = id>0?"Afegit":"Error";
            System.out.println(tc+" id:"+j.getId());
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en afegir jugador:");
            System.out.println(ex.getMessage());
        }
        return id;
    }
    
    private static void modificarJugador(GestorDBClubjdbc gBD,Jugador j){
        System.out.println("Modificar Jugador");
        
        j.setNom("TestMod");
        try {
            boolean i = gBD.modificarJugador(j);

            String tc  = i?"Modificar":"Error";
            System.out.println(tc);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en modificar jugador:");
            System.out.println(ex.getMessage());
        }
    }
    
    private static List<Integer> getJugadorsEquips(GestorDBClubjdbc gBD,Equip e){
        List<Integer> llIDSjug = new ArrayList<>();
        
        
        try {
            llIDSjug = gBD.getJugadorsEquip(e);

            for(int i : llIDSjug){
                System.out.println(""+i);
            }
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en modificar jugador:");
            System.out.println(ex.getMessage());
        }
        
        
        return llIDSjug;
    }

    private static void eliminarEquip(GestorDBClubjdbc gBD,Equip e) {
        System.out.println("Eliminar Equip");
        try {
            boolean i = gBD.eliminarEquip(e);
            String tc  = i?"Eliminat":"Error";
            System.out.println(tc);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en eliminar equip:");
            System.out.println(ex.getMessage());
        }
        
    
    }
    
    private static void eliminarJugador(GestorDBClubjdbc gBD,Jugador j) {
        System.out.println("Eliminar Jugador");
        try {
            boolean i = gBD.eliminarJugador(j);
            String tc  = i?"Eliminat":"Error";
            System.out.println(tc);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en eliminar equip:");
            System.out.println(ex.getMessage());
        }
        
    
    }

    private static void addJugadorEquip(GestorDBClubjdbc gBD, Equip e, Jugador j, boolean titular) { 
        System.out.println("Add jugador a equip");
        try {
            boolean i = gBD.afegirJugadorAEquip(j, e, titular);
            String tc  = i?"Afegit a equip":"Error";
            System.out.println(tc);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en afegir jugador a equip:");
            System.out.println(ex.getMessage());
        }
    
    }

    private static void modificarEquip(GestorDBClubjdbc gBD, Equip eqmod) {
        System.out.println("Modificar Equip");
        try {
            boolean i = gBD.modificarEquip(eqmod);
            String tc  = i?"Equip modificat":"Error";
            System.out.println(tc);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en modificar equip:");
            System.out.println(ex.getMessage());
        }
        
    
    
    }

    private static void actualizarTitularitat(GestorDBClubjdbc gBD, Equip eqadd, Jugador jaddeq, boolean b) {
        System.out.println("actualizar titularitat");
        try {
            
            boolean i = gBD.actualizarTitularitat(jaddeq, eqadd, b);
            String tc  = i?"Titularitat actualizada":"Error";
            System.out.println(tc);
        } catch (GestorBDClubException ex) {
            System.out.println("Problema en actualizar titularitat:");
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
}
