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
class Temporada {
    
    private int any_t;
    private List<Equip> equips;

    public Temporada(int any_t, List<Equip> equips) {
        setAny_t(any_t);
        setEquips(equips);
    }
    public Temporada(int any_t) {
        setAny_t(any_t);
        setEquips(null);
    }

    public Temporada() {
        setEquips(null);
    }
    
    
    
    
    

    public int getAny_t() {
        return any_t;
    }

    public void setAny_t(int any_t) {
        this.any_t = any_t;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        if(equips!=null){
           this.equips = equips; 
        }else{
            this.equips = new ArrayList<>();
        }
       
    }

  
    
    
    
    
}
