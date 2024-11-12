/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.milaifontanals.club;

import java.util.List;

/**
 *
 * @author sepec
 */
public interface IGestioBDClub {
    
    /**
     * Recupera el jugador de la BD
     *
     * @param id id del jugador a recuperar
     * @return Jugador recuperat
     * @throws GestorBDClubException
     */
    Jugador getJugadorsById(int id) throws GestorBDClubException;
    
    /**
     * Recupera els jugadors de un equip de la BD
     *
     * @param e equip dels jugadors a recuperar
     * @return Llista de les ids dels jugadors
     * @throws GestorBDClubException
     */
    List<Integer> getJugadorsEquip(Equip e) throws GestorBDClubException;
    
    /**
     * Recupera els jugadors de la BD
     *
     * @return Llista de equips recuperats
     * @throws GestorBDClubException
     */
    List<Jugador> getAllJugadors() throws GestorBDClubException;
    
    /**
     * Recupera els equips de una temporada de la BD
     *
     * @param t temporada de la que es volen recuperar els equips
     * @return Llista de equips recuperats
     * @throws GestorBDClubException
     */
    List<Equip> getEquips(Temporada t) throws GestorBDClubException;
    
    /**
     * Recupera les temporadas de la BD
     *
     * @return Llista de les temporadas recuperadas
     * @throws GestorBDClubException
     */
    List<Temporada> getTemporades() throws GestorBDClubException;
    
    /**
     * Recupera usuari de la BD
     *
     * @param login login del usuari
     * @return Usuari recuperat null si no existeix
     * @throws GestorBDClubException
     */
    Usuari getUsuari(String login) throws GestorBDClubException;
    
     /**
     * Cambia la titularitat del jugador a la BD
     *
     * @param j Jugador del equip
     * @param e Equip
     * @param titular titularitat del jugador
     * @throws GestorBDClubException
     * @return true si el ha modificat false si no 
     */
    boolean actualizarTitularitat(Jugador j,Equip e,boolean titular) throws GestorBDClubException;
    
    /**
     * Insereix jugador en equip a la BD
     *
     * @param j Jugador a inserir
     * @param e Equip a inserir
     * @param titular titularitat del jugador
     * @throws GestorBDClubException
     * @return true si el ha modificat false si no 
     */
    boolean afegirJugadorAEquip(Jugador j,Equip e,boolean titular) throws GestorBDClubException;
    
    /**
     * Insereix jugador a la BD
     *
     * @param j Jugador a inserir
     * @throws GestorBDClubException
     * @return retorna el id del jugador o -500 si hi ha un error
     */
    int afegirJugador(Jugador j) throws GestorBDClubException;
    
    /**
     * Modifica el jugador a la BD
     *
     * @param j Jugador a modificar
     * @throws GestorBDClubException
     * @return true si el ha modificat false si no 
     */
    boolean modificarJugador(Jugador j) throws GestorBDClubException;
    
    /**
     * Elimina jugador a la BD
     *
     * @param j Jugador a eliminar
     * @return true si el ha eliminat false si no 
     * @throws GestorBDClubException
     */
    boolean eliminarJugador(Jugador j) throws GestorBDClubException;
    
    /**
     * Insereix Equip a la BD
     *
     * @param e Equip a inserir
     * @throws GestorBDClubException
     * @return retorna el id del equip o -500 si hi ha un error
     */
    int afegirEquip(Equip e) throws GestorBDClubException;
    
    /**
     * Modificar Equip a la BD
     *
     * @param e Equip a modificar
     * @throws GestorBDClubException
     * @return true si el ha modificat false si no 
     */
    boolean ModificarEquip(Equip e) throws GestorBDClubException;
    
    /**
     * Eliminar Equip a la BD
     *
     * @param e Equip a eliminar
     * @return true si el ha eliminat false si no 
     * @throws GestorBDClubException
     */
    boolean eliminarEquip(Equip e) throws GestorBDClubException;
    
    
    /**
     * Retorna la categoria del id
     *
     * @param id Id de la categoria a buscar
     * @return La categoria trobada o null
     * @throws GestorBDClubException
     */
    Categoria getCategoria(int id) throws GestorBDClubException;
    
    /**
     * Retorna la una llista de categories
     *
     * @return La llista de categories trobades
     * @throws GestorBDClubException
     */
    List<Categoria> getAllCategories() throws GestorBDClubException;
    
    
    /**
     * Tanca la connexió
     *
     * @throws GestorBDClubException
     */
    void tancarCapa() throws GestorBDClubException;
    
    /**
     * Confirma els canvis efectuats a la BD
     *
     * @throws GestorBDClubException
     */
    void confirmarCanvis() throws GestorBDClubException;

   /**
     * Eliminar les transaccions no confirmades
     *
     * @throws GestorBDClubException
     */
    void desferCanvis() throws GestorBDClubException;
    
}
