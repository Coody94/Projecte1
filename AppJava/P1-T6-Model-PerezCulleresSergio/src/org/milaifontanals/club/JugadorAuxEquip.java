/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

/**
 *
 * @author sepec
 */
public class JugadorAuxEquip {
    private Jugador jugador;
    private boolean titular;

    public JugadorAuxEquip(Jugador jugador, boolean titular) {
        this.jugador = jugador;
        this.titular = titular;
    }
    
    
    

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }
    
    
    
    
}
