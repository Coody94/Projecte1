/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import org.milaifontanals.dialogs.EditarEquipDialog;
import org.milaifontanals.dialogs.NouEquipDialog;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.TableModel.EquipTableModel;
import org.milaifontanals.dialogs.GestionarJugadorsDialog;
import org.milaifontanals.dialogs.NovaTemporadaDialog;

/**
 *
 * @author sepec
 */
public class MainFrame extends JFrame{

    private JugadorsFrame jugadorsFrame;
    private List<Temporada> lltemp; 
    private  EquipTableModel eqTaModel;
    private List<Categoria> llcat;
    private List<Equip> equips_filtrat;
    private JComboBox<Temporada> temporadaComboBox;
    
    private JTextField nameField;
    private JComboBox<Categoria> categoriaComboBox;
    private JComboBox<String> tipusComboBox;
    
    private String urlJRS;
    private String userJRS;
    private String passwordJRS;
    
    public MainFrame(GestorDBClubjdbc gBD) {

        setTitle("Gestió Equips");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());
        urlJRS="";
        
        //Recuperar temporadas y equips
        try{
            lltemp=gBD.getTemporades();
            
            
            
        } catch (GestorBDClubException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            llcat = gBD.getAllCategories();
            Categoria c = new Categoria();
            c.setId(-500);
            c.setNom("Totes");
            
            llcat.add(0, c);
           
           
        } catch (GestorBDClubException ex) {
            llcat = new ArrayList<>();
            Categoria c = new Categoria();
            c.setId(-500);
            c.setNom("Totes");
            llcat.add(c);
        }
        
        
        
        

        // Panell superior 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //String[] temporadas = {"24-25", "25-26"};
        temporadaComboBox = new JComboBox<>(lltemp.toArray(new Temporada[0]));
        
        temporadaComboBox.addActionListener(e -> {
            Temporada seleccion = (Temporada) temporadaComboBox.getSelectedItem();
            aplicar_filtres();
            eqTaModel.setEquips(equips_filtrat);
        
        });
        
        
        
        JButton novaButton = new JButton("Nova");

        JButton jugadoresButton = new JButton("Jugadors ➤");

        topPanel.add(new JLabel("Temporada:"));
        topPanel.add(temporadaComboBox);
        topPanel.add(novaButton);
        topPanel.add(jugadoresButton);

        // Panell central 
//        String[] columnNames = {"Categoria", "Nom", "Tipus", "Nº jugadors"};
//        Object[][] data = {
//                {"Cadet", "Cadet A", "Masculí", 3},
//                {"Cadet", "Cadet B", "Femení", 3},
//                {"Cadet", "Cadet C", "Mixt", 6},
//                {"Juvenil", "Juvenil A", "Masculí", 3},
//                {"Juvenil", "Senior A", "Masculí", 3}
//        };
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        
        Temporada seleccion = (Temporada) temporadaComboBox.getSelectedItem();
        eqTaModel = new EquipTableModel(seleccion.getEquips(),gBD);
        JTable table = new JTable(eqTaModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panell de la dreta
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rightPanel.add(new JLabel("Nom:"));
        nameField = new JTextField(10);
        nameField.setPreferredSize(new Dimension(75, 25));
        rightPanel.add(nameField);

        rightPanel.add(new JLabel("Categoria:"));
        categoriaComboBox = new JComboBox<>(llcat.toArray(new Categoria[0]));
        rightPanel.add(categoriaComboBox);

        rightPanel.add(new JLabel("Tipus:"));
        tipusComboBox = new JComboBox<>(new String[]{"Totes", "Masculí", "Femení", "Mixt"});
        rightPanel.add(tipusComboBox);

        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton borrarButton = new JButton("Borrar");
        JButton aplicarButton = new JButton("Aplicar");
        rightButtonPanel.add(borrarButton);
        rightButtonPanel.add(aplicarButton);

        rightPanel.add(Box.createVerticalStrut(10)); // Espaciado
        rightPanel.add(rightButtonPanel);

        // Panell inferior 
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton nuevoButton = new JButton("Nou");
        JButton editarEquipoButton = new JButton("Editar Equip");
        JButton editarJugadoresButton = new JButton("Editar Jugadors");
        
        
        borrarButton.addActionListener(e -> {
                    
            nameField.setText("");
            categoriaComboBox.setSelectedIndex(0);
            tipusComboBox.setSelectedIndex(0);
                    
            aplicar_filtres();
            eqTaModel.setEquips(equips_filtrat);
            
            
        });
        
        aplicarButton.addActionListener(e -> {

                    
            aplicar_filtres();
            eqTaModel.setEquips(equips_filtrat);
            
            
        });
        
        
        
        
        //botons
        
        nuevoButton.addActionListener(e -> {
            Temporada t = (Temporada) temporadaComboBox.getSelectedItem();
            NouEquipDialog dialog = new NouEquipDialog(this,gBD,t);
            Equip newEquip = dialog.showDialog(); // Abre el diálogo y espera un objeto

            if (newEquip != null) {
                t.getEquips().add(newEquip);
                aplicar_filtres();
                eqTaModel.setEquips(equips_filtrat);
            }
        });
        
        editarEquipoButton.addActionListener(e -> {
            int i =table.getSelectedRow();
            Temporada t = (Temporada) temporadaComboBox.getSelectedItem();
            
            if(i!=-1){
                Equip eq = t.getEquips().get(i);
                EditarEquipDialog dialog = new EditarEquipDialog(this,eq,gBD);
                Equip newEquip = dialog.showDialog();

                if (newEquip != null && !newEquip.getNom().equals(eq.getNom())) {
                    t.getEquips().remove(i);
                    t.getEquips().add(i, newEquip);
                    aplicar_filtres();
                    eqTaModel.setEquips(equips_filtrat);
                }else if(newEquip == null){
                    t.getEquips().remove(i);
                    aplicar_filtres();
                    eqTaModel.setEquips(equips_filtrat);
                }  
            }
            
            
            
            
        });
        
        editarJugadoresButton.addActionListener(e -> {
            int i =table.getSelectedRow();
            Temporada t = (Temporada) temporadaComboBox.getSelectedItem();
            
            if(i!=-1){
                Equip eq = t.getEquips().get(i);
                GestionarJugadorsDialog dialog = new GestionarJugadorsDialog(this,gBD,eq,t);
                dialog.setVisible(true);
 
            }
            
            
            
            
        });
        
        novaButton.addActionListener(e -> {
            
            
            NovaTemporadaDialog dialog = new NovaTemporadaDialog(this,gBD);
            Temporada t = dialog.showDialog();
            
            if(t!=null){
                lltemp.add(t);
                temporadaComboBox = new JComboBox<>(lltemp.toArray(new Temporada[0]));
            }
            
        });
        
        
        
        
        //Combo box categories Informe
        
        JComboBox<Categoria> totesComboBox = new JComboBox<>(llcat.toArray(new Categoria[0]));
        JButton informeButton = new JButton("Informe Jasper");
        
        informeButton.addActionListener(e->{
        
            String fitxerConfigJRS = "informesJRS.xml";
            try {
                Properties props = new Properties();
                props.loadFromXML(new FileInputStream(fitxerConfigJRS));
                String[] claus = {"url", "user", "password"};
                String[] valors = new String[3];
                for (int i = 0; i < claus.length; i++) {
                    valors[i] = props.getProperty(claus[i]);
                    if (valors[i] == null || valors[i].isEmpty()) {
                        //txtInfo.setText(txtInfo.getText() + "\nNo es troba clau " + valors[i] + " en fitxer " + fitxerConfigJRS);
                    }
                }
                urlJRS = valors[0];
                userJRS = valors[1];
                passwordJRS = valors[2];
                //txtInfo.setText(txtInfo.getText() + "\nParàmetres per connectar amb JRS recuperats.");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "No es troba fitxer " + fitxerConfigJRS + " - No es podrà executar cap informe");
                //txtInfo.setText(txtInfo.getText() + "\nNo es troba fitxer " + fitxerConfigJRS + " - No es podrà executar cap informe");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Probablement no es podrà executar cap informe");
                //txtInfo.setText(txtInfo.getText() + "\n" + infoError(ex) + " - Probablement no es podrà executar cap informe");
            }
            
            Categoria c =(Categoria) totesComboBox.getSelectedItem();
            
            try{
                if(c.getId()>=0){
                    informeJRS(c);
                }else{
                    informeJRS(null);
                }
                
            }catch(Exception ez){
                JOptionPane.showMessageDialog(this, "Error en executar el informe");
            }
            
        
        });
        
        
        
        

        bottomPanel.add(nuevoButton);
        bottomPanel.add(editarEquipoButton);
        bottomPanel.add(editarJugadoresButton);
        bottomPanel.add(totesComboBox);
        bottomPanel.add(informeButton);

        // Agregar panells al jframe
        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        jugadoresButton.addActionListener(e -> {
            if (jugadorsFrame == null) {
                jugadorsFrame = new JugadorsFrame(this,gBD); // Crea el frame de jugadores si no existe
            }
            this.setVisible(false); // Oculta el MainFrame
            jugadorsFrame.setVisible(true); // Mostra el JugadorsFrame
        });
        
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    
    private void informeJRS(Categoria cat) throws IOException {
        int BUFFER_SIZE = 4096;
        String url;
        if(cat!= null){
            url = urlJRS + "FitxaEquip.pdf"
                + "?codi_categoria=" + cat.getId();  
        }else{
            url = urlJRS + "FitxaEquip.pdf";
        }
             // Emplenem el paràmetre "codi" de l'informe
        // Si hi ha més paràmetres a passar, cal concatenar-los com "&" com:
        // + "&nomParametre=valor&nomParametre=valor..."
        // L'extensió del fitxer (.pdf) indica que es vol en format .pdf
        // Altres formats possibles: .html, .xls, .rtf, .csv, .ods, .odt, .txt
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String autenticacio = Base64.getEncoder().encodeToString((userJRS + ":" + passwordJRS).getBytes());
        con.setRequestProperty("Authorization", "Basic " + autenticacio);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = con.getHeaderField("Content-Disposition");
            String contentType = con.getContentType();
            int contentLength = con.getContentLength();
            
            if (disposition != null) {
                // Obtenir el nom del fitxer a partir de la capçalera (Content-Disposition)
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // Obtenir el nom del fitxer de dins la URL
                int posArguments = url.lastIndexOf("?");
                if (posArguments == -1) { // No hi ha arguments
                    fileName = url.substring(url.lastIndexOf("/") + 1,
                            url.length());
                } else { // Hi ha arguments i cal eliminar-los per obtenir el nom del fitxer
                    fileName = url.substring(url.lastIndexOf("/") + 1, posArguments);
                }
            }

//            System.out.println("Content-Type = " + contentType);
//            System.out.println("Content-Disposition = " + disposition);
//            System.out.println("Content-Length = " + contentLength);
//            System.out.println("fileName = " + fileName);
//            System.out.println("url = " + url);
            // Obrim InputStream des de HTTP connection
            InputStream inputStream = con.getInputStream();
            // Obrim OutputStream per enregistrar el fitxer

            // Si es vol que cada informe generat tingui diferent nom, per exemple,
            // per incorporar el codi de producte i que no sobreescrigui l'anterior
            // descàrrega d'un altre informe, cal personalitzar filename amb el
            // nom que correspongui, indicant extensió adequada al què indica la URL:
            fileName = "FitxaEquip" + ".pdf";
            // Si comentem la línia anterior, el fitxer descarregat sempre tindrà
            // nom "FitxaProducteAmbComandes.pdf"

            FileOutputStream outputStream = new FileOutputStream(fileName);

            // Llegim i escrivim
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            JOptionPane.showMessageDialog(this, "Fitxer " + fileName + " descarregat");
//            txtInfo.setText("Fitxer " + fileName + " descarregat");
            // Intentem obrir-lo en alguna aplicació del SO
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File(fileName));
                } catch (IOException ex) {
//                    txtInfo.setText("No hi ha aplicacions disponibles per obrir el fitxer");
                }
            }
        } else {
//            txtInfo.setText("Mètode 'GET' : " + url);
//            txtInfo.setText(txtInfo.getText() + "\nCodi resposta: " + responseCode);
//            txtInfo.setText(txtInfo.getText() + "\nCap fitxer a descarregar");
        }
        con.disconnect();
    }
    
    private void aplicar_filtres(){
        //tipusComboBox,categoriaComboBox,nameField
        //113 canvio temporada
        
        
        
        Temporada sel = (Temporada) temporadaComboBox.getSelectedItem();
        equips_filtrat = new ArrayList<>();
        String tipus = (String)tipusComboBox.getSelectedItem();
        
        String tip="";
        boolean totes= false;
        if(tipus.equals("Totes")){
            totes= true;
        }else if(tipus.equals("Masculí")){
            tip="H";
        }else if(tipus.equals("Femení")){
           tip="D"; 
        }else if(tipus.equals("Mixt")){
            tip="M";
        }
        Categoria cat = (Categoria) categoriaComboBox.getSelectedItem();
        
        
        for(Equip e :sel.getEquips()){
            boolean si= true;
            
            
            if(e.getTipus().equals(tip)|| totes){
            
            }else{
                si= false;
            }
            if(e.getCategoria().getId()==cat.getId()|| cat.getId()==-500){
                
            }else{
                si= false;
            }
            if(e.getNom().contains(nameField.getText())){
                
            }else{
                si= false;
            }
            
            
            if(si){
                equips_filtrat.add(e);
            }
            
            
            
            
            
        }
        
        
    }
    
}
