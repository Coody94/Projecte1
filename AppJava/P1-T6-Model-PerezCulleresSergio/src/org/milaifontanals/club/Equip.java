/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sepec
 */
public class Equip {
    
    private int id;
    private String nom;
    private String tipus;
    private Temporada temporada;
    private Categoria categoria;
    private List<JugadorAuxEquip> jugadors;

    public Equip() {
        setJugadors(null);

    }

    public Equip(int id, String nom, String tipus, Temporada temporada, Categoria categoria) {
        setId(id);
        setNom(nom);
        setTipus(tipus);
        setTemporada(temporada);
        setCategoria(categoria);
        setJugadors(null);
    }
    public Equip(String nom, String tipus, Temporada temporada, Categoria categoria) {
        setNom(nom);
        setTipus(tipus);
        setTemporada(temporada);
        setCategoria(categoria);
        setJugadors(null);
    }
    
    
    
    public void promoveToTitular(JugadorAuxEquip j) throws ClubException{
        
        if(jugadors.contains(j)){
            j.setTitular(true);
        }

    }
    
    public void removeTitular(JugadorAuxEquip j) throws ClubException{
        if(jugadors.contains(j)){
            j.setTitular(false);
        }

    }
    
    public void addJugadors(Jugador j,boolean titular) throws ClubException{
        JugadorAuxEquip jug= new JugadorAuxEquip(j, titular);
        if(jugadors.contains(jug)){
            throw new ClubException("El jugador ya esta en el equip");
        }else{
            jugadors.add(jug);
        }
        
        
        
        
    }
 
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

 

    public List<JugadorAuxEquip> getJugadors() {
        return jugadors;
    }

    public void setJugadors(List<JugadorAuxEquip> jugadors) {
        if(jugadors!=null){
            this.jugadors = jugadors;
        }else{
            this.jugadors = new ArrayList<>();
        }
    }

    
    
    
    
    
}
