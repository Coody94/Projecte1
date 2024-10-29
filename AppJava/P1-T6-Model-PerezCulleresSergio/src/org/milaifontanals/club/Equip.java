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
    private List<Jugador> convidats;
    private List<Jugador> titulars;

    public Equip() {
        setConvidats(null);
        setTitulars(null);
    }

    public Equip(int id, String nom, String tipus, Temporada temporada, Categoria categoria) {
        setId(id);
        setNom(nom);
        setTipus(tipus);
        setTemporada(temporada);
        setCategoria(categoria);
        setConvidats(null);
        setTitulars(null);
    }
    
    
    
    
    public void promoveToTitular(Jugador j){
        
    }
    
    public void removeTitular(Jugador j){
        
    }
    
    public void addJugadors(Jugador j,boolean titular){
        
        if(titular){
            if(titulars.contains(j)){
                //TODO
                //error
            }else{
                titulars.add(j);
            }
        }else{
            if(convidats.contains(j)){
                //TODO
                //error
            }else{
                convidats.add(j);
            }
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

    public List<Jugador> getTitulars() {
        return titulars;
    }

    public void setTitulars(List<Jugador> titulars) {
        if(titulars!=null){
            this.titulars = titulars;
        }else{
            this.titulars = new ArrayList<>();
        }
    }

    public List<Jugador> getConvidats() {
        return convidats;
    }

    public void setConvidats(List<Jugador> convidats) {
        if(convidats!=null){
            this.convidats = convidats;
        }else{
            this.convidats = new ArrayList<>();
        }
        
    }
    
    
    
    
    
    
}
