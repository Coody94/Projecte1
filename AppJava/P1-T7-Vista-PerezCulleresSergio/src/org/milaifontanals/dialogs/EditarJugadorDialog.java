/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.dialogs;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.milaifontanals.club.GestorDBClubjdbc;
import org.milaifontanals.club.Jugador;

/**
 *
 * @author sepec
 */
public class EditarJugadorDialog extends JDialog{
    private JLabel lblImagen;
    private JRadioButton rbH, rbD;
    private JTextField txtNom, txtCognoms, txtIdLegal, txtIBAN, txtAnyRevisio, txtAdreca,txtCP,txtPoblacio;
    private JCalendar calendar;
    private JYearChooser chosserAnyRevisio;
    private JButton btnCancelar, btnGuardar,btnBorrar;
    private GestorDBClubjdbc gBD;
    private Jugador jugador;

     public EditarJugadorDialog(Frame owner,GestorDBClubjdbc gBD,Jugador j) {
        super(owner, "Nou Jugador", true);
        setSize(700, 600);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(owner);
        
        jugador=j;
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Márgenes grandes para mayor separación
        gbc.fill = GridBagConstraints.NONE; // Configuración predeterminada

        // Imagen
        lblImagen = new JLabel();
        lblImagen.setPreferredSize(new Dimension(150, 150)); // Tamaño más grande para la imagen
        lblImagen.setOpaque(true);
        lblImagen.setBackground(Color.LIGHT_GRAY);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setText("Imagen");
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarImagen();
            }
        });

        // Botones de radio para H y D
        rbH = new JRadioButton("H");
        rbD = new JRadioButton("D");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbH);
        genderGroup.add(rbD);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        radioPanel.add(rbH);
        radioPanel.add(rbD);

        // Campos de texto
        txtNom = new JTextField(20);
        txtCognoms = new JTextField(20);
        txtIdLegal = new JTextField(20);
        txtIBAN = new JTextField(20);
        txtAnyRevisio = new JTextField(10);
        txtAdreca = new JTextField(20);
        
        txtCP = new JTextField(20);
        txtPoblacio = new JTextField(20);

        
        chosserAnyRevisio = new JYearChooser();
        
        // JCalendar
        calendar = new JCalendar();
        calendar.setPreferredSize(new Dimension(250, 200));

        // Botones
        btnCancelar = new JButton("Cancelar");
        btnGuardar = new JButton("Guardar");
        btnBorrar = new JButton("Eliminar");
        
        
        Insets labelInsets = new Insets(2, 15, 1, 15); // Márgenes más pequeños para etiquetas
        Insets fieldInsets = new Insets(1, 15, 5, 15); // Márgenes más pequeños debajo de los campos

        // Layout de componentes
        // Columna izquierda (imagen, radio buttons y calendario)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblImagen, gbc);

        gbc.gridy = 1;
        add(radioPanel, gbc);

        gbc.gridy = 2;
        add(calendar, gbc);
        
        // CP
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("CP:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtCP.setPreferredSize(new Dimension(100, 25)); // Tamaño fijo para campos de texto
        add(txtCP, gbc);
        
        
        
        // Poblacio
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("Poblacio:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtPoblacio.setPreferredSize(new Dimension(100, 25)); // Tamaño fijo para campos de texto
        add(txtPoblacio, gbc);
        
        
        
        

        // Columna derecha (campos de texto y botones)
        // Ajuste de Layout de componentes (Columna derecha con etiquetas y campos de texto)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // Sin expansión vertical innecesaria

 

        // Nom
        gbc.insets = labelInsets;
        add(new JLabel("Nom:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtNom.setPreferredSize(new Dimension(200, 25)); // Tamaño fijo para campos de texto
        add(txtNom, gbc);

        // Cognoms
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("Cognoms:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtCognoms.setPreferredSize(new Dimension(200, 25)); // Tamaño fijo para campos de texto
        add(txtCognoms, gbc);

        // Id Legal
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("Id Legal:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtIdLegal.setPreferredSize(new Dimension(200, 25));
        add(txtIdLegal, gbc);

        // IBAN
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("IBAN:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtIBAN.setPreferredSize(new Dimension(200, 25));
        add(txtIBAN, gbc);

        // Any fi revisió mèdica
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("Any fi revisió mèdica:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        //txtAnyRevisio.setPreferredSize(new Dimension(200, 25));
        chosserAnyRevisio.setPreferredSize(new Dimension(100,25));
        add(chosserAnyRevisio, gbc);

        // Adreça
        gbc.gridy++;
        gbc.insets = labelInsets;
        add(new JLabel("Adreça:"), gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        txtAdreca.setPreferredSize(new Dimension(200, 25));
        add(txtAdreca, gbc);

        // Botones en la parte inferior
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0; // Volvemos a la configuración estándar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(btnBorrar);
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);
        add(buttonPanel, gbc);

        // Accions
        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardarJugador());
        btnBorrar.addActionListener(e -> eliminarJugador());
        
        
        txtNom.setText(jugador.getNom());
        txtCognoms.setText(jugador.getCognom());
        txtIdLegal.setText(jugador.getId_Legal());
        txtIBAN.setText(jugador.getIban());
        txtCP.setText(jugador.getCodi_postal());
        txtAdreca.setText(jugador.getAdreca());
        txtPoblacio.setText(jugador.getPoblacio());
        chosserAnyRevisio.setYear(jugador.getAny_fi_revisio_medica());
        
        if(jugador.getSexe().equals("H")){
            rbH.setSelected(true);
            
        }else if(jugador.getSexe().equals("D")){
            rbD.setSelected(true);
        }
        
        calendar.setDate(jugador.getData_naix());
        
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText(null); // Elimina el texto predeterminado
        }
    }

    private void guardarJugador() {
        String nom = txtNom.getText();
        String cognoms = txtCognoms.getText();
        String idLegal = txtIdLegal.getText();
        String iban = txtIBAN.getText();
        int yearRevisio = chosserAnyRevisio.getYear();
        String cp = txtCP.getText();
        String poblacio = txtPoblacio.getText();
        
        String sexe="";
        
        String adreca = txtAdreca.getText();
        java.util.Date dataNaixement = calendar.getDate();
        
        String ibanWithoutSpaces = iban.replaceAll("\\s+", "");
        boolean sexeSelected = false;
        if(rbH.isSelected()){
            sexe="H";
            sexeSelected=true;
        }else if(rbD.isSelected()){
            sexe="D";
            sexeSelected=true;
        }
        
        Date currentDate = new Date(); 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(currentDate); 

        int year = calendar.get(Calendar.YEAR); 
        
        
        //chosserAnyRevisio.isValid()
        if(normalizeDate(dataNaixement).compareTo(normalizeDate(new Date()))<0){
            // Validación básica
            if(yearRevisio>=year){
               if (nom.isEmpty() || cognoms.isEmpty() || idLegal.length()==9 || ibanWithoutSpaces.length()==24 || adreca.isEmpty() || !sexeSelected || cp.isEmpty()  || poblacio.isEmpty() ) {
                   
                   jugador = new Jugador(nom, cognoms, sexe, dataNaixement, idLegal, iban, yearRevisio, adreca, cp, poblacio);
                   try{
                       int id = gBD.afegirJugador(jugador);
                       if(id>0){
                           jugador.setId(id);
                           gBD.confirmarCanvis();
                       }
                   }catch(Exception ex){
                        JOptionPane.showMessageDialog(this, "Error en inserir jugador");
                        return ;
                   }
                } 
            }else{
                JOptionPane.showMessageDialog(this, "El any de fi de revisiao medica no es correcte");
                return;
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "La data de naixement es incorrecte");
            return;
        }

        

        JOptionPane.showMessageDialog(this, "Jugador creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    
    
    public void eliminarJugador(){
        boolean del= false;
        try{

                del=gBD.eliminarJugador(jugador);
                gBD.confirmarCanvis();

                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error en eliminar el jugador");
            }
            if(!del ){
                JOptionPane.showMessageDialog(this, "No es pot eliminar un jugador que pertañi a algun equip");
                
            }else{
                jugador = null; 
                dispose();
            }
    }
    
    
    public static Date normalizeDate(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);   // Establecer hora a 0
            calendar.set(Calendar.MINUTE, 0);         // Establecer minutos a 0
            calendar.set(Calendar.SECOND, 0);         // Establecer segundos a 0
            calendar.set(Calendar.MILLISECOND, 0);    // Establecer milisegundos a 0
            return calendar.getTime(); // Devolver la fecha normalizada
        }
    
    public Jugador showDialog() {
        setVisible(true);
        return jugador; 
    }

}
