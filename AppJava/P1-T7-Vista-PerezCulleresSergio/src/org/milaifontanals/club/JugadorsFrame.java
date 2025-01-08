/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.club;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.TableModel.JugadorsTableModel;
import org.milaifontanals.dialogs.CrearJugadorDialog;
import org.milaifontanals.dialogs.EditarJugadorDialog;
import static org.milaifontanals.helper.ExcelGenerator.generarCSVJugadores;

/**
 *
 * @author sepec
 */
public class JugadorsFrame extends JFrame{
    private final MainFrame mainFrame;
    private List<Jugador> lljug;
    private List<Jugador> lljug_filtrats;
    private List<Categoria> llcat;
    
    private JComboBox<Categoria> categoriaComboBox;
    private JTextField nameField;
    private JTextField cognomField;
    private JRadioButton maleRadio ;
    private JRadioButton femaleRadio;
    
    
    public JugadorsFrame(MainFrame mainFrame,GestorDBClubjdbc gBD) {
        this.mainFrame = mainFrame;

        
        setTitle("Gestió Jugadors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());
        
        
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
        JButton backButton = new JButton("← Equips");
        topPanel.add(backButton);


        
        try{
             lljug = gBD.getAllJugadors();
        }catch(Exception ex){
            
        }
        lljug_filtrats=lljug;
        JugadorsTableModel juTaModel = new JugadorsTableModel(lljug_filtrats);
        
        JTable table = new JTable(juTaModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panell de la dreta
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rightPanel.add(new JLabel("Nom:"));
        nameField = new JTextField(10);
        rightPanel.add(nameField);

        rightPanel.add(new JLabel("Cognom:"));
        cognomField = new JTextField(10);
        rightPanel.add(cognomField);

        rightPanel.add(new JLabel("Sexe:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadio = new JRadioButton("H");
        femaleRadio = new JRadioButton("D");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        rightPanel.add(genderPanel);

        rightPanel.add(new JLabel("Categoria:"));
         categoriaComboBox = new JComboBox<>(llcat.toArray(new Categoria[0]));
        rightPanel.add(categoriaComboBox);

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
        JButton editarButton = new JButton("Editar");
        JButton exportarButton = new JButton("Exportar Jugadors");
        
        
        exportarButton.addActionListener(e ->{
            // Usar JFileChooser para seleccionar la carpeta
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona la carpeta donde guardar el archivo CSV");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Obtener la carpeta seleccionada
                String selectedFolder = fileChooser.getSelectedFile().getAbsolutePath();

                // Definir el nombre del archivo dentro de la carpeta seleccionada
                String rutaArchivo = selectedFolder + "/Jugadores.csv";

                // Llamar al método para generar el archivo CSV
                generarCSVJugadores(lljug_filtrats, rutaArchivo);
            } else {
                System.out.println("No se seleccionó ninguna carpeta.");
            }

        });
        
        
        borrarButton.addActionListener(e ->{
            maleRadio.setSelected(false);
            femaleRadio.setSelected(false);
            cognomField.setText("");
            nameField.setText("");
            categoriaComboBox.setSelectedIndex(0);

        });
        
        
        aplicarButton.addActionListener(e ->{
            
            aplicar_filtres();
            juTaModel.setEquips(lljug_filtrats);
        });
        
        
        
        editarButton.addActionListener(e ->{
            //test seleccio
            int i =table.getSelectedRow();
            if(i!=-1){
                Jugador j= lljug.get(i);
                EditarJugadorDialog dialog = new EditarJugadorDialog(this,gBD,j);
                Jugador jug = dialog.showDialog();
                if(jug!=null){
                    lljug.remove(i);
                    lljug.add(i, jug);
                    aplicar_filtres();
                    juTaModel.setEquips(lljug_filtrats);
                }else{
                    lljug.remove(i);
                    aplicar_filtres();
                    juTaModel.setEquips(lljug_filtrats);
                }
            }
            
            
            
        
        });
        
        nuevoButton.addActionListener(e -> {
            
            
            CrearJugadorDialog dialog = new CrearJugadorDialog(this,gBD);
            Jugador j = dialog.showDialog();
            if(j!=null){
                lljug.add(j);
                aplicar_filtres();
                juTaModel.setEquips(lljug_filtrats);
            }
 
        });

        

        bottomPanel.add(nuevoButton);
        bottomPanel.add(editarButton);
        bottomPanel.add(exportarButton);

        // Agregar panells al jframe
        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        backButton.addActionListener(e -> {
            this.setVisible(false); // Oculta el JugadorsFrame
            mainFrame.setVisible(true); // Mostra el MainFrame
        });
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    private void aplicar_filtres(){
        //tipusComboBox,categoriaComboBox,nameField

        lljug_filtrats= new ArrayList<>();
        
        

        
        String sexe="";
        
        if(maleRadio.isSelected()){
            sexe="H";
        }else if(femaleRadio.isSelected()){
            sexe="D";
        }

        Categoria cat = (Categoria) categoriaComboBox.getSelectedItem();
        
        
        for(Jugador j :lljug){
            boolean si= true;
            LocalDate currentDate = LocalDate.now();
            LocalDate birthLocalDate = j.getData_naix().toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate();
            
            
            if(j.getSexe().equals(sexe)||sexe.length()==0){
            
            }else{
                si= false;
            }
            if(cat.getId()==-500){
                
            }else if(cat.getEdat_max()>=Period.between(birthLocalDate, currentDate).getYears()){
                
            }else{
                si= false;
            }
            
            
            
            if(j.getNom().contains(nameField.getText())){
                
            }else{
                si= false;
            }
            
            if(j.getCognom().contains(cognomField.getText())){
                
            }else{
                si= false;
            }
            
            
            if(si){
                lljug_filtrats.add(j);
            }
            
            
            
            
            
        }
        
        
    }
    
    
    
}
