package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI_TakeAwayRegistreren extends JFrame implements ActionListener, ListSelectionListener 
{
    //declaratie van de labels
    private JLabel naamTakeAwayL;
    private JLabel emailTakeAwayL ;
    private JLabel paswoordL;
    private JLabel bevestigPaswoordL;
    private JLabel vestigingenT;
    private JLabel menuT;
    private JLabel categorienT;
    private JLabel kortingVanafBepaaldBedrag ;
    private JLabel bepaaldBedrag ;
    private JLabel korting ;
    private JLabel leveringsGebiedLabel;
           
    // declaratie van de textfield
    private JTextField gebruikersnaamveldTF;
    private JTextField emailTF ;
    private JTextField kortingTF ;
    private JTextField bedragTF ;
    private JTextField postcodeT ;
    
    // declaratie van de passwordfields
    private JPasswordField wachtwoordVeldPF;
    private JPasswordField bevestigWachtwoordVeldPF;

    
    // declaratie van de registratie button
    private JButton registreerBtn;
    private JButton vestigingToeveogenBtn;
    private JButton menuToevoegenBtn;
    private JButton categorieToevoegenBtn;
    private JButton back ;
    private JButton leveringsGebiedVoegToe;
    private JButton leveringsGebiedVerwijder;
    
    // declaratie verwijder buttons
    private JButton vestigingVerwijderBtn ;
    private JButton categorieVerwijderBtn ;
    
    // declaratie radiobuttons + buttongroup
    private JRadioButton eur ;
    private JRadioButton perc ;
    private ButtonGroup groep ;
    
    // declaratie van de lists + models
    private DefaultListModel vestigingenLM = new DefaultListModel();
    private DefaultListModel categorienLM = new DefaultListModel(); 
    private DefaultListModel leveringsGebiedenLM = new DefaultListModel();
    private JList vestigingenL ;
    private JList categorienL ;
    private JList leveringsGebiedenL;
    
    // declaratie van de scrollpanes
    private JScrollPane vestigingenTA ;
    private JScrollPane categorienTA ;
    private JScrollPane leveringsGebiedenTA;
    
    // declaratie van de combobox
    private JComboBox gemeenteT ;
    
    // declaratie van Menu
    private Menu menu ;
    
    // declaratie van arrays
    private ArrayList<Vestiging> vestigingen = new ArrayList()  ;
    
    // declaratie van GUI onderedelen
    private GUI_MenuSamenstellen gui ;
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize") ;
    
    //database
    DB db = new DB();
    
  
    public GUI_TakeAwayRegistreren()
    {
        super("Just Feed");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        Container contentPane = getContentPane() ;
        
        //initialisatie van de labels
        naamTakeAwayL = new JLabel();
        naamTakeAwayL.setText("Naam TakeAway:");
        emailTakeAwayL = new JLabel() ;
        emailTakeAwayL.setText("E-mail:");
        paswoordL = new JLabel();
        paswoordL.setText("Paswoord:");
        bevestigPaswoordL = new JLabel();
        bevestigPaswoordL.setText("Bevestig paswoord:");
        vestigingenT = new JLabel();
        vestigingenT.setText("Vestigingen: ");
        menuT = new JLabel();
        menuT.setText("Menu: ");
        kortingVanafBepaaldBedrag = new JLabel() ;
        kortingVanafBepaaldBedrag.setText("Klant krijgt") ;
        bepaaldBedrag = new JLabel() ;
        bepaaldBedrag.setText("Vanaf €") ;
        korting = new JLabel() ;
        korting.setText("korting");
        categorienT = new JLabel();
        categorienT.setText("Categoriën: ");
        leveringsGebiedLabel = new JLabel();
        leveringsGebiedLabel.setText("Leveringsgebieden:");

        //initialisatie van de textfield
        gebruikersnaamveldTF = new JTextField(12);
        emailTF = new JTextField(12) ;
        kortingTF = new JTextField(3) ;
        bedragTF = new JTextField(3) ;
        
        //initialisatie van paswordfields
        wachtwoordVeldPF = new JPasswordField(12);
        bevestigWachtwoordVeldPF = new JPasswordField(12);
        
        //initialisatie van de buttons
        registreerBtn = new JButton();
        registreerBtn.setText("Registratie aanvragen");
        registreerBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        registreerBtn.setBackground(Color.DARK_GRAY);
        registreerBtn.setForeground(Color.WHITE);
        registreerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registreerBtn.setFocusable(false);
        registreerBtn.addActionListener(this);
        vestigingToeveogenBtn = new JButton();
        vestigingToeveogenBtn.setText(" Voeg toe ");
        vestigingToeveogenBtn.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        vestigingToeveogenBtn.setBackground(Color.DARK_GRAY);
        vestigingToeveogenBtn.setForeground(Color.WHITE);
        vestigingToeveogenBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        vestigingToeveogenBtn.setFocusable(false);
        vestigingToeveogenBtn.addActionListener(this);
        vestigingVerwijderBtn = new JButton();
        vestigingVerwijderBtn.setText("Verwijder");
        vestigingVerwijderBtn.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        vestigingVerwijderBtn.setBackground(Color.DARK_GRAY);
        vestigingVerwijderBtn.setForeground(Color.WHITE);
        vestigingVerwijderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        vestigingVerwijderBtn.setFocusable(false);
        vestigingVerwijderBtn.addActionListener(this);
        menuToevoegenBtn = new JButton();
        menuToevoegenBtn.setText("Aanmaken");
        menuToevoegenBtn.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        menuToevoegenBtn.setBackground(Color.DARK_GRAY);
        menuToevoegenBtn.setForeground(Color.WHITE);
        menuToevoegenBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuToevoegenBtn.setFocusable(false);
        menuToevoegenBtn.addActionListener(this);
        menuToevoegenBtn.setEnabled(false);
        categorieToevoegenBtn = new JButton();
        categorieToevoegenBtn.setText(" Voeg toe ");
        categorieToevoegenBtn.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        categorieToevoegenBtn.setBackground(Color.DARK_GRAY);
        categorieToevoegenBtn.setForeground(Color.WHITE);
        categorieToevoegenBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        categorieToevoegenBtn.setFocusable(false);
        categorieToevoegenBtn.addActionListener(this);
        categorieVerwijderBtn = new JButton();
        categorieVerwijderBtn.setText("Verwijder");
        categorieVerwijderBtn.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        categorieVerwijderBtn.setBackground(Color.DARK_GRAY);
        categorieVerwijderBtn.setForeground(Color.WHITE);
        categorieVerwijderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        categorieVerwijderBtn.setFocusable(false);
        categorieVerwijderBtn.addActionListener(this);
        back = new JButton();
        back.setText("Terug naar startscherm.");
        back.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        back.setForeground(Color.BLUE);
        back.setFocusPainted(false);
        back.setMargin(new Insets(0,0,0,0));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setOpaque(false);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        leveringsGebiedVoegToe = new JButton();
        leveringsGebiedVoegToe.setText("Voeg toe");
        leveringsGebiedVoegToe.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        leveringsGebiedVoegToe.setBackground(Color.DARK_GRAY);
        leveringsGebiedVoegToe.setForeground(Color.WHITE);
        leveringsGebiedVoegToe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        leveringsGebiedVoegToe.setFocusable(false);
        leveringsGebiedVoegToe.addActionListener(this);
        leveringsGebiedVerwijder = new JButton();
        leveringsGebiedVerwijder.setText("Verwijder");
        leveringsGebiedVerwijder.setFont(new Font("Lucida Bright", Font.BOLD ,10));
        leveringsGebiedVerwijder.setBackground(Color.DARK_GRAY);
        leveringsGebiedVerwijder.setForeground(Color.WHITE);
        leveringsGebiedVerwijder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        leveringsGebiedVerwijder.setFocusable(false);
        leveringsGebiedVerwijder.addActionListener(this);
        
        // initialisatie van de radiobuttons + grouperen
        eur = new JRadioButton("EUR") ;
        eur.setBackground(Color.WHITE);
        perc = new JRadioButton("%") ;
        perc.setBackground(Color.WHITE);
        
        groep = new ButtonGroup() ;
        groep.add(eur);
        groep.add(perc);
        
        //initialisatie van de lists
        vestigingenL = new JList(vestigingenLM) ;
        vestigingenL.getSelectionModel().addListSelectionListener(this); // zie regel 1050
        categorienL = new JList(categorienLM) ;
        leveringsGebiedenL = new JList(leveringsGebiedenLM);
        
        vestigingenL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vestigingenL.setLayoutOrientation(JList.VERTICAL);
        vestigingenL.setVisibleRowCount(-1) ;
        
        categorienL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categorienL.setLayoutOrientation(JList.VERTICAL);
        categorienL.setVisibleRowCount(-1) ;
        
        leveringsGebiedenL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leveringsGebiedenL.setLayoutOrientation(JList.VERTICAL);
        leveringsGebiedenL.setVisibleRowCount(-1) ;
        
        vestigingenTA = new JScrollPane(vestigingenL ) ;
        vestigingenTA.setPreferredSize(new Dimension(137,100));
        categorienTA = new JScrollPane(categorienL);
        categorienTA.setPreferredSize(new Dimension(135,150));
        leveringsGebiedenTA = new JScrollPane(leveringsGebiedenL);
        leveringsGebiedenTA.setPreferredSize(new Dimension(137,90));
        
        //aanmaken van outer-Panel
        JPanel outer = new JPanel() ;
        outer.setLayout(new GridLayout(1,2));
        contentPane.add(outer);
        
        // links panel toevoegen aan outer-Panel
        JPanel left = new JPanel() ;
        left.setLayout(new GridBagLayout());
        left.setBackground(Color.WHITE);
        outer.add(left) ;
        
        // rechts panel toevoegen aan outer-Panel
        JPanel right = new JPanel() ;
        right.setLayout(new GridBagLayout());
        right.setBackground(Color.WHITE);
        outer.add(right); 
        
        // elementen toevoegen aan linker panel
        GridBagConstraints gc = new GridBagConstraints() ;
       
        gc.anchor = GridBagConstraints.LINE_START ;
        gc.weightx = 1 ;
        gc.weighty = 1 ;
        gc.gridx = 0 ;
        gc.gridy = 0 ;
        gc.insets = new Insets(15,10,0,0);
        left.add(naamTakeAwayL, gc) ;
        
        gc.gridy = 1 ;
        gc.insets = new Insets(0,10,0,0) ;
        left.add(emailTakeAwayL, gc) ;

        gc.gridy = 2 ;
        gc.insets = new Insets(0,10,0,0);
        left.add(paswoordL, gc) ;

        gc.gridy = 3 ;
        gc.insets = new Insets(0,10,0,0);
        left.add(bevestigPaswoordL, gc) ;
        
        gc.gridy = 4;
        gc.insets = new Insets(-85,10,0,0);
        left.add(vestigingenT,gc);
        
        gc.gridy = 5;
        gc.insets = new Insets(-150,10,0,0);
        left.add(vestigingToeveogenBtn,gc);
        
        gc.gridy = 6 ;
        gc.insets = new Insets(-120,10,0,0) ;
        left.add(vestigingVerwijderBtn,gc) ;
        
        gc.gridy = 7;
        gc.insets = new Insets(-85,10,0,0);
        left.add(leveringsGebiedLabel,gc);
        
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-15,10,0,45);
        left.add(leveringsGebiedVoegToe,gc);
        
        gc.insets = new Insets(40,10,0,45);
        left.add(leveringsGebiedVerwijder,gc);
        gc.fill = GridBagConstraints.NONE;
        
        gc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING ;
        gc.weightx = 3 ;
        gc.weighty = 1 ;
        gc.gridx = 1 ;
        gc.gridy = 0 ;
        gc.insets = new Insets(15,0,0,0);        
        left.add(gebruikersnaamveldTF,gc) ;
        
        gc.gridy = 1 ;
        gc.insets = new Insets(0,0,0,0) ;
        left.add(emailTF, gc) ;
        
        gc.gridy = 2 ;
        gc.insets = new Insets(0,0,0,0);
        left.add(wachtwoordVeldPF,gc) ;

        gc.gridy = 3 ;
        gc.insets = new Insets(0,0,0,0);
        left.add(bevestigWachtwoordVeldPF,gc) ;
        
        gc.gridy = 4;
        gc.insets = new Insets(0,0,0,0);
        left.add(vestigingenTA,gc );
        
        gc.gridy = 7;
        gc.insets = new Insets(-25,0,0,0);
        left.add(leveringsGebiedenTA,gc );
        
        // elementen toevoegen aan rechter panel
        gc.anchor = GridBagConstraints.LINE_START ;
        gc.weightx = 1 ;
        gc.weighty = 1 ;
        gc.gridx = 0 ;
        gc.gridy = 0 ;
        gc.insets = new Insets(-115,0,0,0);
        right.add(categorienT,gc);
        
        gc.gridy = 1;
        gc.insets = new Insets(-250,0,0,0);
        right.add(categorieToevoegenBtn,gc);
        
        gc.gridy = 2 ;
        gc.insets = new Insets(-270,0,0,0) ;
        right.add(categorieVerwijderBtn,gc) ;
        
        gc.gridy = 3;
        gc.insets = new Insets(-160,35,0,0);
        right.add(menuT,gc);
        
        gc.gridy = 4;
        gc.insets = new Insets(-140,10,0,0) ;
        right.add(kortingVanafBepaaldBedrag, gc) ;
        
        gc.gridy = 5 ;
        gc.insets = new Insets(-160,40,0,0) ;
        right.add(bepaaldBedrag, gc) ;
        
        gc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING ;
        gc.weightx = 3 ;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(12,0,0,0);
        right.add(categorienTA,gc);
        
        gc.gridy = 1;
        gc.insets = new Insets(0,0,0,0);
        right.add(menuToevoegenBtn,gc);
        
        gc.gridy = 2 ;
        gc.insets = new Insets(10,0,0,0) ;
        right.add(kortingTF, gc) ;
        
        gc.insets = new Insets(10,40,0,0) ;
        right.add(eur, gc) ;
        
        gc.insets = new Insets(10,90,0,0) ;
        right.add(perc, gc) ;
        
        gc.insets = new Insets(10,130,0,0) ;
        right.add(korting, gc) ;
        
        gc.gridy = 3 ;
        gc.insets = new Insets(-10,0,0,0) ;
        right.add(bedragTF, gc) ;
        
        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0,0,0,0);
        right.add(registreerBtn,gc);
        
        gc.gridy = 5 ;
        gc.insets = new Insets(0,0,0,0) ;
        right.add(back,gc) ;
        
        // Zie GUI_klantregistreren
        kortingTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTypedAndDot(evt);
            }
        });
        bedragTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        if (evt.getSource() == categorieToevoegenBtn)
        {
           boolean comboboxIngevuld = false;
           boolean nieuweCatIngevuld = false;
           boolean allebeiIngevuld = false;
           boolean bestaatal = false ;
           
            // Combobox die gevuld word met alle categorieën die reeds bestaan
           ArrayList<String> categorien = db.getAlleUniekeCategorien() ;
           JComboBox box = new JComboBox() ;
           box.setBackground(Color.WHITE);
           box.addItem("");
                for (String s: categorien)
                {
                    box.addItem(s); 
                }
            
           // panel opmaken
           JLabel cate = new JLabel("Kies een categorie") ;
           JLabel andere = new JLabel("Kies andere categorie:") ;
           JTextField andereT = new JTextField(12) ;
           JPanel outer = new JPanel() ;
           outer.setLayout(new GridLayout(1,2));
           JPanel left = new JPanel() ;
           left.setLayout(new GridBagLayout());  
           outer.add(left) ;
           JPanel right = new JPanel() ;
           right.setLayout(new GridBagLayout());
           outer.add(right); 
           
           //elementen toevoegen aan linker panel
           GridBagConstraints gc = new GridBagConstraints() ;
       
           gc.anchor = GridBagConstraints.LINE_START ;
           gc.weightx = 0 ;
           gc.weighty = 0 ;
           gc.gridx = 0 ;
           gc.gridy = 0 ;
           gc.insets = new Insets(0,0,0,0);
           left.add(cate, gc) ;
           
           gc.gridy = 1;
           gc.insets = new Insets(30,0,0,0);
           left.add(andere,gc);
           
           //elementen toevoegen aan recher panel
           gc.fill = GridBagConstraints.HORIZONTAL;
           gc.weightx = 0 ;
           gc.weighty = 0 ;
           gc.gridx = 0 ;
           gc.gridy = 0 ;
           gc.insets = new Insets(0,0,0,0);
           right.add(box, gc);
           
           gc.gridy = 1;
           gc.insets = new Insets(30,0,0,0);
           right.add(andereT,gc);
           
           UIManager.put("OptionPane.minimumSize", new Dimension(400, 200)); // vaste size voor de optionpane     
           int result = JOptionPane.showConfirmDialog(null, outer, "Categorie toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
           
           if (!(((String)box.getSelectedItem()).equals(""))) {
                comboboxIngevuld = true ;
           }
           if (!(andereT.getText().equals(""))) {
                nieuweCatIngevuld = true;
           }
           if ( (nieuweCatIngevuld) && (comboboxIngevuld) ) {
                allebeiIngevuld = true;
           }
           
           if (categorienLM.contains((String)box.getSelectedItem()) || categorienLM.contains(andereT.getText()))
           {
               bestaatal = true ;
           }
           
           if(result == JOptionPane.OK_OPTION &&  allebeiIngevuld ) 
           {
               UIManager.put("OptionPane.minimumSize", dim) ; // zet de optionpane weer in zijn oorspronkelijke staat
               JOptionPane.showMessageDialog(null, "Je mag maar 1 categorie per keer toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);
           }
           else if (result == JOptionPane.OK_OPTION && nieuweCatIngevuld && andereT.getText().length() > 20  )
           {
               JOptionPane.showMessageDialog(null, "Categorie kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
           }
           else if (result == JOptionPane.OK_OPTION && comboboxIngevuld && !bestaatal) 
           {
               categorienLM.addElement((String)box.getSelectedItem()); 
           } 
           else if (result == JOptionPane.OK_OPTION && nieuweCatIngevuld && !bestaatal)
           {
               categorienLM.addElement(andereT.getText()) ;
           }
           else {
               System.out.println("Error");
           }
           
        }
        if (evt.getSource() == categorieVerwijderBtn)
        {
           categorienLM.removeElement(categorienL.getSelectedValue()) ;
        }
        if (evt.getSource() == vestigingToeveogenBtn)
        {
            boolean bestaatal = false ;
            
            // panel opmaken
            JPanel nieuw = new JPanel() ;
            nieuw.setLayout(new GridBagLayout());
            
            JLabel straat = new JLabel("Adres:") ;
            JLabel postcode = new JLabel("Postcode:") ;
            JLabel gemeente = new JLabel("Gemeente:") ;
            JLabel leveringskosten = new JLabel("Leveringskosten:") ;
            JLabel euroteken = new JLabel("€") ;
         
            JTextField straatT = new JTextField(12) ;
            JTextField nummerT = new JTextField(2) ;
            postcodeT = new JTextField(12) ;
            gemeenteT = new JComboBox() ;
            gemeenteT.setBackground(Color.WHITE);
            JTextField leveringT = new JTextField(3) ;
            
            GridBagConstraints gbc = new GridBagConstraints() ;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START ;
            gbc.weightx = 3 ;
            gbc.weighty = 1 ;
            gbc.gridx = 0 ;
            gbc.gridy = 0 ;
            gbc.insets = new Insets(0,10,0,0) ;
            nieuw.add(straat, gbc) ;
        
            gbc.gridx = 0 ;
            gbc.gridy = 1 ;
            gbc.insets = new Insets(0,10,0,0) ;
            nieuw.add(postcode, gbc) ;
        
            gbc.gridx = 0 ;
            gbc.gridy = 2 ;
            gbc.insets = new Insets(0,10,0,0) ;
            nieuw.add(gemeente, gbc) ;
            
            gbc.gridy = 3 ;
            gbc.insets = new Insets(0,0,0,0) ;
            nieuw.add(leveringskosten, gbc) ;
        
            gbc.fill = GridBagConstraints.HORIZONTAL ;
        
            gbc.gridx = 1 ;
            gbc.gridy = 0 ;
            gbc.insets = new Insets(0,0,0,0) ;
            nieuw.add(straatT, gbc) ;
        
            gbc.weightx = 1 ;
            gbc.gridx = 2 ;
            gbc.gridy = 0 ;
            gbc.insets = new Insets(0,0,0,0) ;
            nieuw.add(nummerT, gbc) ;
        
            gbc.weightx = 3 ;
            gbc.gridx = 1 ;
            gbc.gridy = 1 ;
            gbc.insets = new Insets(0,0,0,0) ;
            nieuw.add(postcodeT, gbc) ;
        
            gbc.gridx = 1 ;
            gbc.gridy = 2 ;
            gbc.insets = new Insets(0,0,0,0) ;
            nieuw.add(gemeenteT, gbc) ;
            
            gbc.gridy = 3 ;
            gbc.insets = new Insets(0,0,0,0) ;
            nieuw.add(euroteken, gbc) ;
            
            gbc.fill = GridBagConstraints.NONE ;
            gbc.gridy = 3 ;
            gbc.insets = new Insets(0,15,0,0) ;
            nieuw.add(leveringT, gbc) ;
            
            // zie GUI_klantregistreren
            postcodeT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               toonGemeenten(evt) ;
           }
       } ) ;
            postcodeT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
            nummerT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
           }
       } ) ;
            leveringT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTypedAndDot(evt);
           }
       } ) ;
            
           UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));      
           int result = JOptionPane.showConfirmDialog(null, nieuw, "Vestiging toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
           
           if(vestigingenLM.contains(straatT.getText() + " " + nummerT.getText() + " " + postcodeT.getText() + " " + (String)gemeenteT.getSelectedItem()))
           {
               bestaatal = true ;
           }
           if(result == JOptionPane.OK_OPTION)
           {
               int teller = 0 ;
                 if (leveringT.getText().contains("."))
                 {
                     
                     for (int i = 0 ; i < leveringT.getText().length() ; i++)
                     {
                         String sub = leveringT.getText().substring(i, i+1) ;
                         if (sub.contains("."))
                         {
                             teller ++ ;
                         }
                         
                     }
                 }
               if(straatT.getText().isEmpty() || nummerT.getText().isEmpty() || leveringT.getText().isEmpty() || postcodeT.getText().isEmpty() || gemeenteT.getSelectedItem() == null )
               {
                   UIManager.put("OptionPane.minimumSize", dim) ;
                   JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else if (straatT.getText().length() > 50)
                {
                    JOptionPane.showMessageDialog(null, "Straat kan niet meer dan 50 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
               else if (nummerT.getText().length() > 11)
                {
                    JOptionPane.showMessageDialog(null, "Huistnummer kan niet meer dan 11 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
               else if (leveringT.getText().length() > 7)
               {
                   JOptionPane.showMessageDialog(null, "Leverkingskosten kan niet meer dan 7 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else if (teller > 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat slechts 1 punt", "Error", JOptionPane.ERROR_MESSAGE); 
                     
                 }
               else if (leveringT.getText().length() == 1 && leveringT.getText().contains("."))
               {
                   JOptionPane.showMessageDialog(null, "Leverkingskosten bevat minstens 1 cijfer!", "Error", JOptionPane.ERROR_MESSAGE);
               }
               else if (!bestaatal)
               {
                    vestigingenLM.addElement(straatT.getText() + " " + nummerT.getText() + " " + postcodeT.getText() + " " + (String)gemeenteT.getSelectedItem()) ;
                    vestigingen.add(new Vestiging(straatT.getText(),Integer.parseInt(nummerT.getText()),Integer.parseInt(postcodeT.getText()),(String)gemeenteT.getSelectedItem(), Double.parseDouble(leveringT.getText()))) ;
                    menuToevoegenBtn.setEnabled(true) ;
               }
           }
           else{
               System.out.println("Error");  
           }
        }
        if (evt.getSource() == vestigingVerwijderBtn)
        {
           if(!(vestigingenL.getSelectedValue() == null))
           {
                int ind = vestigingenL.getSelectedIndex() ;
                vestigingenLM.removeElement(vestigingenL.getSelectedValue()) ;
                vestigingen.remove(ind) ;
                if (vestigingen.isEmpty())
                {
                    menuToevoegenBtn.setEnabled(false);
                }
           }
           
        }
        if (evt.getSource() == leveringsGebiedVoegToe)
        {
           if (vestigingenL.getSelectedValue() != null)
           {
               boolean bestaatal = false ;
               
               //panel opmaken
               JPanel nieuw = new JPanel() ;
               nieuw.setLayout(new GridBagLayout());
               
               JLabel postcode = new JLabel("Postcode:") ;
               JLabel gemeente = new JLabel("Gemeente:") ;
               postcodeT = new JTextField(12) ;
               gemeenteT = new JComboBox() ;
               gemeenteT.setBackground(Color.WHITE);
               
               GridBagConstraints gc = new GridBagConstraints() ;
               
               gc.anchor = GridBagConstraints.FIRST_LINE_START ;
               gc.weightx = 1 ;
               gc.weighty = 1 ;
               gc.gridx = 0 ;
               gc.gridy = 0 ;
               nieuw.add(postcode, gc) ;
               gc.gridy = 1 ;
               nieuw.add(gemeente, gc) ;
               gc.gridy = 0 ;
               gc.gridx = 1 ;
               gc.fill = GridBagConstraints.HORIZONTAL ;
               nieuw.add(postcodeT, gc) ;
               gc.gridy = 1 ;
               nieuw.add(gemeenteT, gc) ;
               
               // Zie GUI_Klantregistreren
               postcodeT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               toonGemeenten(evt) ;
           }
       } ) ;
            postcodeT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
               
               UIManager.put("OptionPane.minimumSize", dim) ;
               int result = JOptionPane.showConfirmDialog(null, nieuw, "Vestiging toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
               
               if(result == JOptionPane.OK_OPTION)
               {
                   // controleren of de vestigingen elkaar niet overlappen
                   for (Vestiging v: vestigingen)
                   {
                       for (int i = 0 ; i < v.getLeveringsgebiedenpostcodes().size() ; i++)
                       {
                          if (v.getLeveringsgebiedenpostcodes().get(i) == Integer.parseInt(postcodeT.getText()) && v.getLeveringsgebiedengemeentes().get(i).equalsIgnoreCase((String)gemeenteT.getSelectedItem())) 
                          {
                              bestaatal = true ;
                              break ;
                          }
                       }
                   }
                    if (postcodeT.getText().isEmpty() || gemeenteT.getSelectedItem() == null )
                    {
                        JOptionPane.showMessageDialog(null, "Gegevens zijn niet geldig!", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                    if (bestaatal)
                    {
                        JOptionPane.showMessageDialog(null, "Leveringsgebieden mogen niet overlappen!", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                    else 
                    {
                        vestigingen.get(vestigingenL.getSelectedIndex()).getLeveringsgebiedenpostcodes().add(Integer.parseInt(postcodeT.getText())) ;
                        vestigingen.get(vestigingenL.getSelectedIndex()).getLeveringsgebiedengemeentes().add((String)gemeenteT.getSelectedItem()) ;
                        leveringsGebiedenLM.addElement(postcodeT.getText() + " " + (String)gemeenteT.getSelectedItem());
                    }
                }
               
           }
        }
        if (evt.getSource() == leveringsGebiedVerwijder)
        {
            if (vestigingenL.getSelectedValue() != null)
            {
                if (leveringsGebiedenL.getSelectedValue() != null)
                {
                    int ind = leveringsGebiedenL.getSelectedIndex() ;
                    leveringsGebiedenLM.removeElement(leveringsGebiedenL.getSelectedValue()) ;
                    vestigingen.get(vestigingenL.getSelectedIndex() ).getLeveringsgebiedenpostcodes().remove(ind) ;
                    vestigingen.get(vestigingenL.getSelectedIndex() ).getLeveringsgebiedengemeentes().remove(ind) ;
                }
            }
        }
        if (evt.getSource() == back)
        {
          super.dispose();
          GUI_Startscherm guiWindow = new GUI_Startscherm();
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null);
          guiWindow.setResizable(false);
        }
        if (evt.getSource() == menuToevoegenBtn)
        {
            super.dispose();
            // Als men de eerste keer een menu opent krijgt men een lege gui om het menu samen te stellen.
            // Als men al een menu heeft samengesteld krijgt men de menu die ze al hebben samengesteld.
            // Zo moet men niet het hele menu opnieuw schrijven als men iets vergeten is of wil aanpassen.
            if (gui == null)
            {
               gui = new GUI_MenuSamenstellen(this) ;
               gui.setVisible(true);
               gui.setLocationRelativeTo(null);
               gui.setResizable(false);
            }
            else
            {
              GUI_MenuSamenstellen guiWindow = gui  ;
              guiWindow.setGui(this);
              guiWindow.setVisible(true);
              guiWindow.setLocationRelativeTo(null);
              guiWindow.setResizable(false);
              
            }
            
        }
        if (evt.getSource() == registreerBtn)
        {
            boolean heeftpostcodeLeveringsgebied = true ;
            boolean heeftgemeentesleveringsgebied = true ;
            
            // zet size optionpane terug naar de oorspronkelijke size
            UIManager.put("OptionPane.minimumSize", dim) ;
            
            // controleren of er wel een leveringsgebied is ingevuld
            for (Vestiging v : vestigingen)
            {
              if (v.getLeveringsgebiedenpostcodes().isEmpty())
              {
                  heeftpostcodeLeveringsgebied = false ;
                  break ;
              }
              if (v.getLeveringsgebiedengemeentes().isEmpty())
              {
                  heeftgemeentesleveringsgebied = false ;
                  break ;
              }
            }
                // controles uitvoeren
                if(gebruikersnaamveldTF.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Naam TakeAway" + " moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(wachtwoordVeldPF.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Wachtwoord" + " moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(db.controleerOfTakeAwayBestaat(gebruikersnaamveldTF.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Deze TakeAway bestaat al!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(wachtwoordVeldPF.getText().length() < 8)
                {
                    JOptionPane.showMessageDialog(null, "Paswoord moet minstens 8 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(wachtwoordVeldPF.getText().contains(gebruikersnaamveldTF.getText()))
                {
                   JOptionPane.showMessageDialog(null, "Paswoord mag de naam van de TakeAway niet bevatten!", "Error", JOptionPane.ERROR_MESSAGE);  
                }
                else if(!(wachtwoordVeldPF.getText().equals(bevestigWachtwoordVeldPF.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Paswoorden komen niet overeen!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (vestigingenLM.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Je moet minstens 1 vestiging toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(categorienLM.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Je moet minstens 1 categorie toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(menu == null)
                {
                     JOptionPane.showMessageDialog(null, "Je moet minstens 1 gerecht aan het menu toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(menu.getGerechten().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Je moet minstens 1 gerecht aan het menu toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(heeftpostcodeLeveringsgebied) || !(heeftgemeentesleveringsgebied))
                {
                    JOptionPane.showMessageDialog(null, "Iedere vestiging moet minstens 1 leveringsgebied hebben", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (gebruikersnaamveldTF.getText().length() > 40)
                {
                    JOptionPane.showMessageDialog(null, "Naam TakeAway kan niet meer dan 40 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (wachtwoordVeldPF.getText().length() > 20)
                {
                    JOptionPane.showMessageDialog(null, "Paswoord kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (emailTF.getText().length() > 50)
                {
                    JOptionPane.showMessageDialog(null, "E-mailadres kan niet meer dan 50 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(emailTF.getText().contains("@") && emailTF.getText().contains(".")))
                {
                  JOptionPane.showMessageDialog(null, "E-mailadres is niet geldig !", "Error", JOptionPane.ERROR_MESSAGE);  
                }
                else if (!(db.controleerOfEmailTakeAwayBestaat(emailTF.getText())))
                {
                    JOptionPane.showMessageDialog(null, "E-mail bestaat al!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (perc.isSelected() && Double.parseDouble(kortingTF.getText()) > 100)
                {
                    JOptionPane.showMessageDialog(null, "Percentage is een getal tussen 1 en 100", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (perc.isSelected() && Double.parseDouble(kortingTF.getText()) < 1)
                {
                    JOptionPane.showMessageDialog(null, "Percentage is een getal tussen 1 en 100", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(kortingTF.getText().isEmpty()) && bedragTF.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Het kortingsbedrag moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(bedragTF.getText().isEmpty()) && kortingTF.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "De gegeven korting moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (kortingTF.getText().length() > 7)
                {
                    JOptionPane.showMessageDialog(null, "De korting kan niet meer dan 7 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (bedragTF.getText().length() > 11)
                {
                    JOptionPane.showMessageDialog(null, "Het kortingsbedrag kan niet meer dan 11 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(kortingTF.getText().isEmpty()) && !(perc.isSelected() || eur.isSelected()))
                {
                    JOptionPane.showMessageDialog(null, "U moet euro of percentage aanduiden!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    // Indien de korting in euro is is deze >1 en wordt het zo opgeslaan.
                    // Indien de korting in % is dan wordt deze opgeslaan als komma getal <1
                    double korting ;
                    if (eur.isSelected())
                    {
                        korting = Double.parseDouble(kortingTF.getText()) ;
                    }
                    else
                    {
                        korting = (Double.parseDouble(kortingTF.getText()))/100 ;
                    }
                    // we maken een object aan van het type takeaway die via zijn methode addtakeaway, addcategorie, addvestiging alles correct in de database plaatst
                    // via de methode fillmenu plaatsen we alles van het menu correct in de database
                    TakeAway ta = new TakeAway(gebruikersnaamveldTF.getText(), wachtwoordVeldPF.getText(),Integer.parseInt(bedragTF.getText()),korting,emailTF.getText(),menu) ;
                    ta.addTakeAway();
                    menu.fillMenu(gebruikersnaamveldTF.getText());
                    Object[] c = categorienLM.toArray() ;
                        for (Object o : c)
                        {
                            ta.addCategorie(o.toString());  
                        }
                        for (Vestiging v : vestigingen)
                        {
                            v.addVestiging(gebruikersnaamveldTF.getText());
                
                        }
                    JOptionPane.showMessageDialog(null, "Welkom bij Just Feed!" + "\n" + "U kunt zich nu aanmelden.", "Welkom", JOptionPane.INFORMATION_MESSAGE);
            
                    super.dispose();
                    new SplashScreenMain3(emailTF.getText(),gebruikersnaamveldTF.getText());
                }
            
            
            }
            
        
        
    }
    // zie GUI_Klantregistreren
    public void toonGemeenten(KeyEvent evt) {
        gemeenteT.removeAllItems();
        char c = evt.getKeyChar() ;
        if(Character.isDigit(c) )
        {
           evt.consume(); 
        
           postcodeT.setText(postcodeT.getText() + c);
       
           if (postcodeT.getText().length() == 4)
           {
                ArrayList<String> gem = db.getGemeentesBijPostcode(Integer.parseInt(postcodeT.getText())) ;
                for(String s: gem)
                {
                   gemeenteT.addItem(s);
                }
                gemeenteT.showPopup();
                try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_TAB);
                }catch (AWTException e) {
                    e.printStackTrace();
                }
           }
           else
               evt.consume() ;
        }
    }
    public void onlyNumbersKeyTyped(KeyEvent e)
    {
        char c = e.getKeyChar() ;
        if(!(Character.isDigit(c)) )
        {
           e.consume(); 
        }
    }
    // periode (.) is ook toegelaten bij prijzen
    public void onlyNumbersKeyTypedAndDot(KeyEvent e)
    {
        char c = e.getKeyChar() ;
        if(!(Character.isDigit(c) || c == KeyEvent.VK_PERIOD) )
        {
           e.consume(); 
        }
    }
   
    // We willen telkens wnr we op de vestiging klikken, enkel de leveringsgebieden van die vestiging zien.
    // Hiervoor laten we telkens wanneer er een andere selectie is in de lijst met vestigingen, deze lijst een actie sturen.
    // Deze lijst controleert welke vestiging geselecteerd is en plaats in de leveringsgebiedenlijst de juist leveringsgebieden.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vestigingenL.getSelectionModel())
        {
            if (vestigingenL.getSelectedValue() == null)
            {
                leveringsGebiedenLM.removeAllElements();
            }
            else
            {
                leveringsGebiedenLM.removeAllElements();
                for ( int i = 0 ; i < vestigingen.get(vestigingenL.getSelectedIndex()).getLeveringsgebiedenpostcodes().size() ; i ++ )
                {
                    leveringsGebiedenLM.addElement(vestigingen.get(vestigingenL.getSelectedIndex()).getLeveringsgebiedenpostcodes().get(i) + " " + vestigingen.get(vestigingenL.getSelectedIndex()).getLeveringsgebiedengemeentes().get(i));
                }
            }
        }
    }
     

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public GUI_MenuSamenstellen getGui() {
        return gui;
    }

    public void setGuiWindow(GUI_MenuSamenstellen gui) {
        this.gui = gui;
    }
    
    

    
}
        