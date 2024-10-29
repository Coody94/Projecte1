/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.util.List;

/**
 *
 * @author sepec
 */
public class Categoria {
    private static List<Categoria> categories;
    private int id;
    private String nom;
    private int edat_min;
    private int edat_max;

    public Categoria() {
    }

    public Categoria(int id, String nom, int edat_min, int edat_max) {
        setId(id);
        setNom(nom);
        setEdat_min(edat_min);
        setEdat_max(edat_max);
    }

    
    
    
    
    public static List<Categoria> getCategories() {
        return categories;
    }

    public static void setCategories(List<Categoria> categories) {
        Categoria.categories = categories;
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

    public int getEdat_min() {
        return edat_min;
    }

    public void setEdat_min(int edat_min) {
        this.edat_min = edat_min;
    }

    public int getEdat_max() {
        return edat_max;
    }

    public void setEdat_max(int edat_max) {
        this.edat_max = edat_max;
    }
    
            
    
    
    
}
