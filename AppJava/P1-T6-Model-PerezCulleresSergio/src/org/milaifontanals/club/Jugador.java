/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author sepec
 */
public class Jugador {
    
    private int id;
    private String nom;
    private String cognom;
    private String sexe;
    private Date data_naix;
    private String id_Legal;
    private String iban;
    private int any_fi_revisio_medica;
    private String adreca;
    private InputStream foto;
    
    
    
    
    

    public Jugador() {
    }

    public Jugador(int id, String nom, String cognom, String sexe, Date data_naix, String id_Legal, String iban, int any_fi_revisio_medica, String adreca) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.sexe = sexe;
        this.data_naix = data_naix;
        this.id_Legal = id_Legal;
        this.iban = iban;
        this.any_fi_revisio_medica = any_fi_revisio_medica;
        this.adreca = adreca;
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

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getData_naix() {
        return data_naix;
    }

    public void setData_naix(Date data_naix) {
        this.data_naix = data_naix;
    }

    public String getId_Legal() {
        return id_Legal;
    }

    public void setId_Legal(String id_Legal) {
        this.id_Legal = id_Legal;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getAny_fi_revisio_medica() {
        return any_fi_revisio_medica;
    }

    public void setAny_fi_revisio_medica(int any_fi_revisio_medica) {
        this.any_fi_revisio_medica = any_fi_revisio_medica;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        return this.id == other.id;
    }
    
    
    
    
    
    
    
    
}
