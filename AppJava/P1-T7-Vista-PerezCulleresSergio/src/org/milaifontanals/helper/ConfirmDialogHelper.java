/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.helper;

import javax.swing.JOptionPane;

/**
 *
 * @author sepec
 */
public class ConfirmDialogHelper {
    public static boolean showConfirmDialog(java.awt.Component parent, String question) {
        int response = JOptionPane.showConfirmDialog(
                parent,
                question,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return response == JOptionPane.YES_OPTION;
    }

}
