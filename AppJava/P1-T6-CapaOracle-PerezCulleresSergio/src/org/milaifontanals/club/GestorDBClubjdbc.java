/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author sepec
 */
public class GestorDBClubjdbc implements IGestioBDClub{

     private Connection conn;
    
    private PreparedStatement getJugadorById;
    private PreparedStatement getAllJugadors;
    private PreparedStatement modificarEquip; 
    private PreparedStatement actualizarTitularitat;
    private PreparedStatement afegirEquip;
    private PreparedStatement afegirJugador;
    private PreparedStatement afegirJugadorAEquip;
    private PreparedStatement eliminarEquip;
    private PreparedStatement eliminarJugador;
    private PreparedStatement getEquip;
    private PreparedStatement getJugadorsEquips;
    private PreparedStatement getTemporades;
    private PreparedStatement getUsuari;
    private PreparedStatement modificarJugador;
    
    
    
    public GestorDBClubjdbc() throws GestorBDClubException {
        String nomFitxer = "empresaJDBC.xml";
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
                getJugadorById = conn.prepareStatement("select nom, cognom, sexe, data_naix, id_legal, iban, any_fi_reviso_medica, adreca, foto"
                        + "from jugador where id = ?");
            } catch (SQLException ex) {
                throw new GestorBDClubException("Error en preparar sentència per getJugadorId", ex);
            }
        }
        
        
        
        
        
    }

    @Override
    public List<Integer> getJugadorsEquip() throws GestorBDClubException {
        if(getJugadorsEquips==null){
            
        }
    }

    @Override
    public List<Jugador> getAllJugadors() throws GestorBDClubException {
        if(getAllJugadors==null){
            
        }
    }

    @Override
    public List<Equip> getEquips(Temporada t) throws GestorBDClubException {
        if(getEquip==null){
            
        }
    }

    @Override
    public List<Temporada> getTemporades() throws GestorBDClubException {
        if(getTemporades==null){
            
        }
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
                return new Usuari(login, passwd);
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            throw new GestorBDClubException("Error en la recuperacio del usuari", ex);
        }
        
        
    }

    @Override
    public void actualizarTitularitat(Jugador j, Equip e, boolean titular) throws GestorBDClubException {
        if(actualizarTitularitat==null){
            
        }
    }

    @Override
    public void afegirJugadorAEquip(Jugador j, Equip e, boolean titular) throws GestorBDClubException {
        if(afegirJugadorAEquip==null){
            
        }
    }

    @Override
    public void afegirJugador(Jugador j) throws GestorBDClubException {
        if(afegirJugador==null){
            
        }
    }

    @Override
    public void modificarJugador(Jugador j) throws GestorBDClubException {
        if(modificarJugador==null){
            
        }
    }

    @Override
    public boolean eliminarJugador(Jugador j) throws GestorBDClubException {
        if(eliminarJugador==null){
            
        }
    }

    @Override
    public void afegirEquip(Equip e) throws GestorBDClubException {
        if(afegirEquip==null){
            
        }
    }

    @Override
    public void ModificarEquip(Equip e) throws GestorBDClubException {
        if(modificarEquip==null){
            
        }
    }

    @Override
    public boolean eliminarEquip(Equip e) throws GestorBDClubException {
        if(eliminarEquip==null){
            
        }
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
    
}
