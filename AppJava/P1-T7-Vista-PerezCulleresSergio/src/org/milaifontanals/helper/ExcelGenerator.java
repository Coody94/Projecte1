/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.milaifontanals.club.Jugador;

/**
 *
 * @author sepec
 */
public class ExcelGenerator {
    
    
     public static void generarCSVJugadores(List<Jugador> jugadores, String rutaArchivo) {
        // Formato para la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezados
            writer.write("ID,Nombre,Apellido,Sexo,Fecha de Nacimiento,ID Legal,IBAN,Año Fin Revisión Médica,Dirección,Código Postal,Población");
            writer.newLine();

            // Escribir datos de jugadores
            for (Jugador jugador : jugadores) {
                StringBuilder sb = new StringBuilder();
                sb.append(jugador.getId()).append(",");
                sb.append(jugador.getNom()).append(",");
                sb.append(jugador.getCognom()).append(",");
                sb.append(jugador.getSexe()).append(",");
                sb.append(jugador.getData_naix() != null ? dateFormat.format(jugador.getData_naix()) : "").append(",");
                sb.append(jugador.getId_Legal()).append(",");
                sb.append(jugador.getIban()).append(",");
                sb.append(jugador.getAny_fi_revisio_medica()).append(",");
                sb.append(jugador.getAdreca()).append(",");
                sb.append(jugador.getCodi_postal()).append(",");
                sb.append(jugador.getPoblacio());
                writer.write(sb.toString());
                writer.newLine();
            }

            System.out.println("Archivo CSV generado exitosamente en: " + rutaArchivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    
    
}
