/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;



import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.sql.ResultSetMetaData;

/**
 *
 * @author sepec
 */
public class GestorDBClubjdbc implements IGestioBDClub{

     private Connection conn;
    
    private PreparedStatement getJugadorById;
    private PreparedStatement getAllJugadors;
    private PreparedStatement getAllJugadorsFAny;
    private PreparedStatement modificarEquip; 
    private PreparedStatement actualizarTitularitat;
    private PreparedStatement afegirTemporada;
    private PreparedStatement afegirEquip;
    private PreparedStatement afegirJugador;
    private PreparedStatement afegirJugadorAEquip;
    private PreparedStatement eliminarEquip;
    private PreparedStatement eliminarJugador;
    private PreparedStatement eliminarJugadorEquip;
    private PreparedStatement getEquip;
    private PreparedStatement getJugadorsEquips;
    private PreparedStatement getJugadorsEquipsTitulars;
    private PreparedStatement getJugadorsEquipsConvidats;
    private PreparedStatement getTemporades;
    private PreparedStatement getUsuari;
    private PreparedStatement modificarJugador;
    private PreparedStatement getCategoria;
    private PreparedStatement getAllCategories;
    
    
    public GestorDBClubjdbc() throws GestorBDClubException {
        String nomFitxer = "clubJDBC.xml";
        try {
            Properties props = new Properties();
            props.loadFromXML(new FileInputStream(nomFitxer));
            String[] claus = {"url", "user", "password"};
            String[] valors = new String[3];
            for (int i = 0; i < claus.length; i++) {
                valors[i] = props.getProperty(claus[i]);
                if (valors[i] == null || valors[i].isEmpty()) {
                    throw new GestorBDClubException("L'arxiu " + nomFitxer + " no troba la clau " + claus[i]);
                }
            }
            conn = DriverManager.getConnection(valors[0], valors[1], valors[2]);
            conn.setAutoCommit(false);
        } catch (IOException ex) {
            throw new GestorBDClubException("Problemes en recuperar l'arxiu de configuració " + nomFitxer, ex);
        } catch (SQLException ex) {
            throw new GestorBDClubException("No es pot establir la connexió.", ex);
        }
    }
    
    
    @Override
    public Jugador getJugadorsById(int id) throws GestorBDClubException {
        if(getJugadorById==null){
            try {
                getJugadorById = conn.prepareStatement("select nom, cognom, sexe, data_naix, id_legal, iban, any_fi_reviso_medica, adreca, foto,codi_postal,poblacio from jugador where id = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getJugadorId", ex);
            }
        }
        
         try {
            getJugadorById.setInt(1, id);
            ResultSet rs = getJugadorById.executeQuery();
            if(rs.next()){
                String nom = rs.getString("nom");
                String cognom = rs.getString("cognom");
                String sexe = rs.getString("sexe");
                java.sql.Date sql_data_naix = rs.getDate("data_naix");
                Date data_naix = new Date(sql_data_naix.getTime());
                String id_legal = rs.getString("id_legal");
                String iban = rs.getString("iban");
                int fi_rm = rs.getInt("any_fi_reviso_medica");
                String adreca = rs.getString("adreca");
                String codi_postal = rs.getString("codi_postal");
                String poblacio = rs.getString("poblacio");
                
                Jugador j = new Jugador(id, nom, cognom, sexe, data_naix, id_legal, iban, fi_rm, adreca,codi_postal,poblacio);        
                rs.close();
                return j;
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de equips", ex);
        }
        
        
        
        return null;
        
        
        
    }
    
    
    @Override
    public boolean afegirTemporad(Temporada t) throws GestorBDClubException {
        int fi=0;
        int key = -500;
        if(afegirTemporada==null){
            try {

                afegirTemporada=conn.prepareStatement("INSERT INTO temporada (any_t) VALUES (?)");

                
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per afegir temporada ", ex);
            }
        }
        
        try {
            
            afegirTemporada.setInt(1, t.getAny_t());
            
            fi = afegirTemporada.executeUpdate();

            
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en executar sentència per afegir equip ", ex);
        }
        
        return fi>0;
    }
    
    

    @Override
    public List<Integer> getJugadorsEquip(Equip e) throws GestorBDClubException {
        List<Integer> lljug =  new ArrayList<>();
        if(getJugadorsEquips==null){
            try {
                getJugadorsEquips = conn.prepareStatement("select id_jugador from membre_equip where id_equip = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getJugadorsEquip", ex);
            }
        }
        
        try {
            getJugadorsEquips.setInt(1, e.getId());
            ResultSet rs = getJugadorsEquips.executeQuery();
            while(rs.next()){
                
                int id_jugador = rs.getInt("id_jugador");
                
                
                
                       
                lljug.add(id_jugador);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de jugadors", ex);
        }
        
        
        
        return lljug;
        
        
    }
    
    @Override
    public List<Jugador> getJugadorsEquipTitulars(Equip e) throws GestorBDClubException {
        List<Jugador> lljug =  new ArrayList<>();
        if(getJugadorsEquipsTitulars==null){
            try {
                getJugadorsEquipsTitulars = conn.prepareStatement("select id_jugador from membre_equip where id_equip = ? and titular_convidat = 'T'");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getJugadorsEquipTitulars", ex);
            }
        }
        
        try {
            getJugadorsEquipsTitulars.setInt(1, e.getId());
            ResultSet rs = getJugadorsEquipsTitulars.executeQuery();
            while(rs.next()){
                
                int id_jugador = rs.getInt("id_jugador");
                Jugador j = this.getJugadorsById(id_jugador);
                
                
                       
                lljug.add(j);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de jugadors titulars", ex);
        }
        
        
        
        return lljug;
        
        
    }
    
    
    @Override
    public List<Jugador> getJugadorsEquipConvidats(Equip e) throws GestorBDClubException {
        List<Jugador> lljug =  new ArrayList<>();
        if(getJugadorsEquipsConvidats==null){
            try {
                getJugadorsEquipsConvidats = conn.prepareStatement("select id_jugador from membre_equip where id_equip = ? and titular_convidat = 'C'");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getJugadorsEquipConvidats", ex);
            }
        }
        
        try {
            getJugadorsEquipsConvidats.setInt(1, e.getId());
            ResultSet rs = getJugadorsEquipsConvidats.executeQuery();
            while(rs.next()){
                
                int id_jugador = rs.getInt("id_jugador");
                Jugador j = this.getJugadorsById(id_jugador);
                
                
                       
                lljug.add(j);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de jugadors convidats", ex);
        }
        
        
        
        return lljug;
        
        
    }
    

    @Override
    public List<Jugador> getAllJugadors() throws GestorBDClubException {
        List<Jugador> lljugadors = new ArrayList<>();
        if(getAllJugadors==null){
            try {
                getAllJugadors = conn.prepareStatement("select id,nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,foto,codi_postal,poblacio from jugador");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getAllJugadors", ex);
            } 
        }
        
         try {
            ResultSet rs = getAllJugadors.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String cognom = rs.getString("cognom");
                String sexe = rs.getString("sexe");
                java.sql.Date sql_data_naix = rs.getDate("data_naix");
                Date data_naix = new Date(sql_data_naix.getTime());
                String id_legal = rs.getString("id_legal");
                String iban = rs.getString("iban");
                int fi_rm = rs.getInt("any_fi_reviso_medica");
                String adreca = rs.getString("adreca");
                String codi_postal = rs.getString("codi_postal");
                String poblacio = rs.getString("poblacio");
                
                
                
                
                Jugador j = new Jugador(id, nom, cognom, sexe, data_naix, id_legal, iban, fi_rm, adreca,codi_postal,poblacio);        
                
                
                lljugadors.add(j);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de jugadors", ex);
        }
        
        
        
        return lljugadors;
    }
    
    @Override
    public List<Jugador> getAllJugadorsFiltrat(int any,String tipus_equip) throws GestorBDClubException {
        List<Jugador> lljugadors = new ArrayList<>();
        if(getAllJugadorsFAny==null){
            try {
                getAllJugadorsFAny = conn.prepareStatement("select id,nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,foto,codi_postal,poblacio from jugador WHERE data_naix >= ?and ('M'=? or sexe=?)");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getAllJugadors", ex);
            } 
        }
        
         try {
            java.sql.Date data = java.sql.Date.valueOf(any+"-01-01");
            getAllJugadorsFAny.setDate(1, data);
            getAllJugadorsFAny.setString(2, tipus_equip);
            getAllJugadorsFAny.setString(3, tipus_equip);
            ResultSet rs = getAllJugadorsFAny.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String cognom = rs.getString("cognom");
                String sexe = rs.getString("sexe");
                java.sql.Date sql_data_naix = rs.getDate("data_naix");
                Date data_naix = new Date(sql_data_naix.getTime());
                String id_legal = rs.getString("id_legal");
                String iban = rs.getString("iban");
                int fi_rm = rs.getInt("any_fi_reviso_medica");
                String adreca = rs.getString("adreca");
                String codi_postal = rs.getString("codi_postal");
                String poblacio = rs.getString("poblacio");
                
                
                
                
                Jugador j = new Jugador(id, nom, cognom, sexe, data_naix, id_legal, iban, fi_rm, adreca,codi_postal,poblacio);        
                
                
                lljugadors.add(j);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de jugadors", ex);
        }
        
        
        
        return lljugadors;
    }

    @Override
    public List<Equip> getEquips(Temporada t) throws GestorBDClubException {
        List<Equip>llequips = new ArrayList<>();
        if(getEquip==null){
           try {
                getEquip = conn.prepareStatement("select id,nom,tipus,categoria from equip where temporada=?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getTemporades", ex);
            } 
        }
        try {
            getEquip.setInt(1, t.getAny_t());
            ResultSet rs = getEquip.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String tipus = rs.getString("tipus");
                int id_cat = rs.getInt("categoria");
                Categoria c = this.getCategoria(id_cat);
                
                Equip e = new Equip(id, nom, tipus, t, c);
                
                llequips.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de equips", ex);
        }
        
        return llequips;
        
        
        
    }

    @Override
    public List<Temporada> getTemporades() throws GestorBDClubException {
        List<Temporada>lltemp = new ArrayList<>();
        if(getTemporades==null){
           try {
                getTemporades = conn.prepareStatement("select any_t from temporada ");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getTemporades", ex);
            } 
        }
        try {
            ResultSet rs = getTemporades.executeQuery();
            while(rs.next()){
                int temp = rs.getInt("any_t");
                Temporada t =new Temporada(temp);
                t.setEquips(this.getEquips(t));
                lltemp.add(t);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de temporadas", ex);
        }
        
        return lltemp;
    }

    @Override
    public Usuari getUsuari(String login) throws GestorBDClubException {
        if(getUsuari==null){
            try {
                getUsuari = conn.prepareStatement("select login, password from usuari where login = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getUsuari", ex);
            }
        }
        try {
            getUsuari.setString(1, login);
            ResultSet rs = getUsuari.executeQuery();
            if(rs.next()){
                String passwd = rs.getString("password");
                rs.close();
                return new Usuari(login, passwd);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio del usuari", ex);
        }
        return null;
        
    }

    @Override
    public boolean actualizarTitularitat(Jugador j, Equip e, boolean titular) throws GestorBDClubException {
        int upd;
        if(actualizarTitularitat==null){
            try {
                actualizarTitularitat = conn.prepareStatement("UPDATE membre_equip SET titular_convidat = ? WHERE id_equip = ? and id_jugador = ?");
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en preparar sentència per acctualizar titularitat ", ex);
            }
        }
        
        try {
            String tc = titular ? "T" : "C";
                actualizarTitularitat.setString(1, tc);
                actualizarTitularitat.setInt(2, e.getId());
                actualizarTitularitat.setInt(3, j.getId());
                
                upd = actualizarTitularitat.executeUpdate();
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en executar sentència per actualizar titularitat ", ex);
            }
        return upd==1;
        
        
        
    }

    @Override
    public boolean afegirJugadorAEquip(Jugador j, Equip e, boolean titular) throws GestorBDClubException {
        int  fi=0;
        if(afegirJugadorAEquip==null){
            try {
                afegirJugadorAEquip=conn.prepareStatement("INSERT INTO membre_equip (id_equip,id_jugador,titular_convidat) VALUES (?,?,?)");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per afegir jugador a equip", ex);
            }
        }
        try {
            afegirJugadorAEquip.setInt(1, e.getId());
            afegirJugadorAEquip.setInt(2, j.getId());
            String tc = titular ? "T" : "C";
            afegirJugadorAEquip.setString(3, tc);
            
            fi = afegirJugadorAEquip.executeUpdate();
            
            
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en executar sentència per afegir equip ", ex);
        }
        
        return fi==1;
    }

    @Override
    public int afegirJugador(Jugador j) throws GestorBDClubException {
        int fi=0;

        if(afegirJugador==null){
            try {
                afegirJugador=conn.prepareStatement("INSERT INTO jugador (nom,cognom,sexe,data_naix,id_legal,iban,any_fi_reviso_medica,adreca,codi_postal,poblacio) VALUES (?,?,?,?,?,?,?,?,?,?)"
                        , new String[] { "id" });
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per afegir jugador ", ex);
            }
        }
        
        try {
            afegirJugador.setString(1, j.getNom());
            afegirJugador.setString(2, j.getCognom());
            afegirJugador.setString(3, j.getSexe());
            afegirJugador.setDate(4, new java.sql.Date(j.getData_naix().getTime()));   
            afegirJugador.setString(5, j.getId_Legal());
            afegirJugador.setString(6, j.getIban());
            afegirJugador.setInt(7, j.getAny_fi_revisio_medica());
            afegirJugador.setString(8, j.getAdreca());
            afegirJugador.setString(9, j.getCodi_postal());
            afegirJugador.setString(10, j.getPoblacio());
            
            fi = afegirJugador.executeUpdate();
            if(fi>0){
                try (ResultSet generatedKeys = afegirJugador.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en executar sentència per afegir equip ", ex);
        }
        
        return -500;
        
        
    }

    @Override
    public boolean modificarJugador(Jugador j) throws GestorBDClubException {
        int upd;
        if(modificarJugador==null){
            try {
                modificarJugador = conn.prepareStatement("UPDATE jugador SET nom= ?, cognom=?, sexe=?, data_naix=?, id_legal=?, iban=?, any_fi_reviso_medica=?, adreca=?, codi_postal=?, poblacio=? WHERE id = ?");
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en preparar sentència per acctualizar jugador ", ex);
            }
        }
        
        try {
            modificarJugador.setString(1, j.getNom());
            modificarJugador.setString(2, j.getCognom());
            modificarJugador.setString(3, j.getSexe());
            modificarJugador.setDate(4, new java.sql.Date(j.getData_naix().getTime()));   
            modificarJugador.setString(5, j.getId_Legal());
            modificarJugador.setString(6, j.getIban());
            modificarJugador.setInt(7, j.getAny_fi_revisio_medica());
            modificarJugador.setString(8, j.getAdreca());
            modificarJugador.setString(9, j.getCodi_postal());
            modificarJugador.setString(10, j.getPoblacio());
            modificarJugador.setInt(11, j.getId());
            
            upd = modificarJugador.executeUpdate();
        } catch (SQLException ex) {
             throw new GestorBDClubException("Error en executar sentència per actualizar jugador ", ex);
        }
        return upd==1;
        
        
    }

    @Override
    public boolean eliminarJugador(Jugador j) throws GestorBDClubException {
        int del =0;
        if(eliminarJugador==null){
            try {
                eliminarJugador = conn.prepareStatement("DELETE FROM jugador WHERE id = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per eliminar jugador ", ex);
            }
        }
        

        try {
                eliminarJugador.setInt(1, j.getId());
                del = eliminarJugador.executeUpdate();
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en executar sentència per eliminar jugador ", ex);
            }
            
            return del==1;
        
        
        
    }
    
    
    @Override
    public boolean eliminarJugadorEquip(Jugador j,Equip e) throws GestorBDClubException {
        int del =0;
        if(eliminarJugadorEquip==null){
            try {
                eliminarJugadorEquip = conn.prepareStatement("DELETE FROM membre_equip WHERE id_equip = ? and id_jugador = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per eliminar jugador del equip", ex);
            }
        }
        

        try {
                eliminarJugadorEquip.setInt(1, e.getId());
                eliminarJugadorEquip.setInt(2, j.getId());
                del = eliminarJugadorEquip.executeUpdate();
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en executar sentència per eliminar jugador del equip ", ex);
            }
            
            return del==1;
        
        
        
    }

    @Override
    public int afegirEquip(Equip e) throws GestorBDClubException {
        int fi=0;
        int key = -500;
        if(afegirEquip==null){
            try {

                afegirEquip=conn.prepareStatement("INSERT INTO equip (nom,tipus,temporada,categoria) VALUES (?,?,?,?)",
                    new String[] { "id" });

                
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per afegir equip ", ex);
            }
        }
        
        try {
            System.out.println(e.getNom());
            System.out.println(e.getTipus());
            System.out.println(e.getTemporada().getAny_t()+"");
            System.out.println(e.getCategoria().getId()+"");
            
            afegirEquip.setString(1, e.getNom());
            afegirEquip.setString(2, e.getTipus());
            afegirEquip.setInt(3, e.getTemporada().getAny_t());
            afegirEquip.setInt(4, e.getCategoria().getId());
            
            
            
            fi = afegirEquip.executeUpdate();
            if(fi>0){
                
                try (ResultSet generatedKeys = afegirEquip.getGeneratedKeys()) {
                    ResultSetMetaData metaData = generatedKeys.getMetaData();

                if (generatedKeys.next()) {
                    key = generatedKeys.getInt(1); // Obtener la clave generada
                } else {
                    throw new SQLException("Error: no se generó ninguna clave.");
                }
                    
                
            }
                
                /*
                
                
                
                ResultSet generatedKeys = afegirEquip.getGeneratedKeys();
                if (generatedKeys.next() && generatedKeys!= null) {
                    key =generatedKeys.getLong(1);
                    int i=0;
                    //return (int)key;
                }
                */
            }
            
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en executar sentència per afegir equip ", ex);
        }
        
        return key;
    }

    @Override
    public boolean modificarEquip(Equip e) throws GestorBDClubException {
        int upd;
        if(modificarEquip==null){
            try {
                modificarEquip = conn.prepareStatement("UPDATE equip SET nom = ? WHERE id = ?");
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en preparar sentència per acctualizar equip ", ex);
            }
        }
        
        try {
                modificarEquip.setString(1, e.getNom());
                modificarEquip.setInt(2, e.getId());
                upd = modificarEquip.executeUpdate();
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en executar sentència per actualizar equip ", ex);
            }
        return upd==1;
            
    }

    @Override
    public boolean eliminarEquip(Equip e) throws GestorBDClubException {
        int del =0;
        if(eliminarEquip==null){
            try {
                eliminarEquip = conn.prepareStatement("DELETE FROM equip WHERE id = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per eliminar equip ", ex);
            }

        }
        try {
                eliminarEquip.setInt(1, e.getId());
                del = eliminarEquip.executeUpdate();
            } catch (SQLException ex) {
                 throw new GestorBDClubException("Error en executar sentència per eliminar equip ", ex);
            }
            
            return del==1;
    }

    @Override
    public void tancarCapa() throws GestorBDClubException {
     if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en fer rollback final.", ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en tancar la connexió.\n", ex);
            }
        }
    }

    @Override
    public void confirmarCanvis() throws GestorBDClubException {
         try {
            conn.commit();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en confirmar canvis", ex);
        }
    }

    @Override
    public void desferCanvis() throws GestorBDClubException {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en desfer canvis", ex);
        }
    }

    @Override
    public Categoria getCategoria(int id) throws GestorBDClubException {
        
        if(getCategoria==null){
            try {
                getCategoria = conn.prepareStatement("select nom, edat_min, edat_max from categoria where id = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getCategoria", ex);
            }
        }
        try {
            getCategoria.setInt(1, id);
            ResultSet rs = getCategoria.executeQuery();
            if(rs.next()){
                String nom = rs.getString("nom");
                int edat_min = rs.getInt("edat_min");
                int edat_max = rs.getInt("edat_max");
                rs.close();
                return new Categoria(id, nom, edat_min, edat_max);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio del categoria", ex);
        }
        return null;
        
        
        
    }

    @Override
    public List<Categoria> getAllCategories() throws GestorBDClubException {
        List<Categoria>llcat= new ArrayList<>();
        if(getAllCategories==null){
            try {
                getAllCategories = conn.prepareStatement("select id, nom, edat_min, edat_max from categoria ");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getCategoria", ex);
            }
        }
        try {
            
            ResultSet rs = getAllCategories.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                int edat_min = rs.getInt("edat_min");
                int edat_max = rs.getInt("edat_max");
                
                Categoria c = new Categoria(id, nom, edat_min, edat_max);
                llcat.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio de totes les categoria", ex);
        }
        return llcat;
    }
    
}
