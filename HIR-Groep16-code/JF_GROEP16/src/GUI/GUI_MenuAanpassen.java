package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class GUI_MenuAanpassen extends JFrame implements ActionListener{
    
    //declareren van JLabels
    private JLabel menuSamenstellenLabel;
    private JLabel gerechtenLabel;
    private JLabel bijgerechtenLabel;
    private JLabel snacksLabel;
    private JLabel drankenLabel;
    
    //declareren van JButtons
    private JButton gerechtenAddBtn;
    private JButton gerechtenDeleteBtn;
    private JButton bijgerechtenAddBtn;
    private JButton bijgerechtenDeleteBtn;
    private JButton snacksAddBtn;
    private JButton snacksDeleteBtn;
    private JButton drankenAddBtn;
    private JButton drankenDeleteBtn;
    private JButton terugBtn;
    
    //declareren van JLists
    private DefaultListModel gerechtenLM = new DefaultListModel();
    private DefaultListModel bijgerechtenLM = new DefaultListModel();
    private DefaultListModel snacksLM = new DefaultListModel();
    private DefaultListModel drankenLM = new DefaultListModel();
    private JList gerechtenList;
    private JList bijgerechtenList;
    private JList snacksList;
    private JList drankenList;
    private JScrollPane gerechtenSP;
    private JScrollPane bijgerechtenSP;
    private JScrollPane snackSP;
    private JScrollPane drankenSP;
    
    private ArrayList<String> gerechtenTabel ;
    private ArrayList<Double> prijzenTabel ;
    private ArrayList<String> bijgerechtenTabel ;
    private ArrayList<Double> prijzenBijgerechtenTabel ;
    private ArrayList<String> snacksTabel ;
    private ArrayList<Double> prijzenSnacksTabel ;
    private ArrayList<String> drankenTabel ;
    private ArrayList<Double> prijzenDrankenTabel ;
            
    // declaratie van het Menu
    private Menu menu ;
    private HashMap<String,Double> gerechtenHM = new HashMap() ;
    private HashMap<String,Double> bijgerechtenHM = new HashMap() ;
    private HashMap<String,Double> snacksHM = new HashMap() ;
    private HashMap<String,Double> drankenHM = new HashMap() ;
    
    private JTextField box ;
    
    // declaratie van GUI onderdelen
    private GUI_TakeAwayRegistreren gui ;
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize") ;
    private String ingelogdeTakeAway;
    
    private DB db = new DB();
    
    
    public GUI_MenuAanpassen(String takeaway)
    {
        super("Just Feed");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;

        Container contentPane = getContentPane();
        
        // initialisatie van gui 
        ingelogdeTakeAway = takeaway ;
        
        //initialisatie JLabels
        menuSamenstellenLabel = new JLabel();
        menuSamenstellenLabel.setText("Menu samenstellen");
        menuSamenstellenLabel.setFont(new Font("Lucida Bright", Font.BOLD ,25));
        gerechtenLabel = new JLabel();
        gerechtenLabel.setText("Gerechten");
        gerechtenLabel.setFont(new Font("Lucida Bright", Font.BOLD ,17));
        bijgerechtenLabel = new JLabel();
        bijgerechtenLabel.setText("Bijgerechten");
        bijgerechtenLabel.setFont(new Font("Lucida Bright", Font.BOLD ,17));
        snacksLabel = new JLabel();
        snacksLabel.setText("Snacks");
        snacksLabel.setFont(new Font("Lucida Bright", Font.BOLD ,17));
        drankenLabel = new JLabel();
        drankenLabel.setText("Dranken");
        drankenLabel.setFont(new Font("Lucida Bright", Font.BOLD ,17));
        
        //initialisatie van de JButtons
        gerechtenAddBtn = new JButton();
        gerechtenAddBtn.setText("VOEG TOE");
        gerechtenAddBtn.setBackground(Color.DARK_GRAY);
        gerechtenAddBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        gerechtenAddBtn.setForeground(Color.WHITE);
        gerechtenAddBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gerechtenAddBtn.setFocusable(false);
        gerechtenAddBtn.addActionListener(this);
        
        gerechtenDeleteBtn = new JButton();
        gerechtenDeleteBtn.setText("VERWIJDER");
        gerechtenDeleteBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        gerechtenDeleteBtn.setForeground(Color.WHITE);
        gerechtenDeleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gerechtenDeleteBtn.setFocusable(false);
        gerechtenDeleteBtn.setBackground(Color.DARK_GRAY);
        gerechtenDeleteBtn.addActionListener(this);
        
        bijgerechtenAddBtn = new JButton();
        bijgerechtenAddBtn.setText("VOEG TOE");
        bijgerechtenAddBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        bijgerechtenAddBtn.setForeground(Color.WHITE);
        bijgerechtenAddBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bijgerechtenAddBtn.setFocusable(false);
        bijgerechtenAddBtn.setBackground(Color.DARK_GRAY);
        bijgerechtenAddBtn.addActionListener(this);
        
        bijgerechtenDeleteBtn = new JButton();
        bijgerechtenDeleteBtn.setText("VERWIJDER");
        bijgerechtenDeleteBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        bijgerechtenDeleteBtn.setForeground(Color.WHITE);
        bijgerechtenDeleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bijgerechtenDeleteBtn.setFocusable(false);
        bijgerechtenDeleteBtn.setBackground(Color.DARK_GRAY);
        bijgerechtenDeleteBtn.addActionListener(this);
        
        snacksAddBtn = new JButton();
        snacksAddBtn.setText("VOEG TOE");
        snacksAddBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        snacksAddBtn.setForeground(Color.WHITE);
        snacksAddBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        snacksAddBtn.setFocusable(false);
        snacksAddBtn.setBackground(Color.DARK_GRAY);
        snacksAddBtn.addActionListener(this);
        
        snacksDeleteBtn = new JButton();
        snacksDeleteBtn.setText("VERWIJDER");
        snacksDeleteBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        snacksDeleteBtn.setForeground(Color.WHITE);
        snacksDeleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        snacksDeleteBtn.setFocusable(false);
        snacksDeleteBtn.setBackground(Color.DARK_GRAY);
        snacksDeleteBtn.addActionListener(this);
        
        drankenAddBtn = new JButton();
        drankenAddBtn.setText("VOEG TOE");
        drankenAddBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        drankenAddBtn.setForeground(Color.WHITE);
        drankenAddBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        drankenAddBtn.setFocusable(false);
        drankenAddBtn.setBackground(Color.DARK_GRAY);
        drankenAddBtn.addActionListener(this);
        
        drankenDeleteBtn = new JButton();
        drankenDeleteBtn.setText("VERWIJDER");
        drankenDeleteBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        drankenDeleteBtn.setForeground(Color.WHITE);
        drankenDeleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        drankenDeleteBtn.setFocusable(false);
        drankenDeleteBtn.setBackground(Color.DARK_GRAY);
        drankenDeleteBtn.addActionListener(this);
        
        terugBtn = new JButton();
        terugBtn.setText("Terug naar profiel");
        terugBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        terugBtn.setForeground(Color.WHITE);
        terugBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        terugBtn.setFocusable(false);
        terugBtn.setBackground(Color.DARK_GRAY);
        terugBtn.addActionListener(this);
        
        //initialisatie van de JLists
        
          
        
        //Tabel 1 
        gerechtenTabel = db.getGerechtenTakeAway(ingelogdeTakeAway);
        
        prijzenTabel = new ArrayList() ;
        for( String gerecht : gerechtenTabel)
        {
            prijzenTabel.add(db.getPrijsGerecht(gerecht,db.getMenuIDTakeAway(ingelogdeTakeAway)));
            
        }
        
        int index = 0 ;
        
        for (String gerecht : gerechtenTabel)
        {
            gerechtenLM.addElement(gerecht +"  €" + prijzenTabel.get(index));
            index ++ ;
        }
        
        //tabel 2 
        bijgerechtenTabel = db.getBijgerechtenBijMenuID(db.getMenuIDTakeAway(ingelogdeTakeAway));
        
        prijzenBijgerechtenTabel = new ArrayList() ;
        for( String bijgerecht : bijgerechtenTabel)
        {
            prijzenBijgerechtenTabel.add(db.getPrijsBijgerecht(bijgerecht,db.getMenuIDTakeAway(ingelogdeTakeAway)));
            
        }
        
        int index2 = 0 ;
        for(String bijgerecht : bijgerechtenTabel)
        {
            bijgerechtenLM.addElement(bijgerecht + "  €" + prijzenBijgerechtenTabel.get(index2) );
            index2 ++ ;
        }
        
        // tabel 3 
        
        snacksTabel = db.getSnacksBijMenuID(db.getMenuIDTakeAway(ingelogdeTakeAway));
        
        prijzenSnacksTabel = new ArrayList() ;
        for( String snacks : snacksTabel)
        {
            prijzenSnacksTabel.add(db.getPrijsSnack(snacks,db.getMenuIDTakeAway(ingelogdeTakeAway)));
            
        }
        
        int index3 = 0 ;
        for(String bijgerecht : snacksTabel)
        {
            snacksLM.addElement(bijgerecht + "  €" + prijzenSnacksTabel.get(index3) );
            index3 ++ ;
        }
        
        // tabel 4 
        
        drankenTabel = db.getDrankenBijMenuID(db.getMenuIDTakeAway(ingelogdeTakeAway));
        
        prijzenDrankenTabel = new ArrayList() ;
        for( String drank : drankenTabel)
        {
            prijzenDrankenTabel.add(db.getPrijsDrank(drank,db.getMenuIDTakeAway(ingelogdeTakeAway)));
            
        }
        
        int index4 = 0 ;
        for(String bijgerecht : drankenTabel)
        {
            drankenLM.addElement(bijgerecht + "  €" + prijzenDrankenTabel.get(index4) );
            index4 ++ ;
        }
        
        
        
        
        gerechtenList = new JList(gerechtenLM);
        gerechtenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gerechtenList.setLayoutOrientation(JList.VERTICAL);
        gerechtenList.setVisibleRowCount(-1) ;
        bijgerechtenList = new JList(bijgerechtenLM);
        bijgerechtenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bijgerechtenList.setLayoutOrientation(JList.VERTICAL);
        bijgerechtenList.setVisibleRowCount(-1) ;
        snacksList = new JList(snacksLM);
        snacksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        snacksList.setLayoutOrientation(JList.VERTICAL);
        snacksList.setVisibleRowCount(-1) ;
        drankenList = new JList(drankenLM);
        drankenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        drankenList.setLayoutOrientation(JList.VERTICAL);
        drankenList.setVisibleRowCount(-1) ;
        
        gerechtenSP = new JScrollPane(gerechtenList);
        gerechtenSP.setPreferredSize(new Dimension(100,200));
        bijgerechtenSP = new JScrollPane(bijgerechtenList);
        bijgerechtenSP.setPreferredSize(new Dimension(100,200));
        snackSP = new JScrollPane(snacksList);
        snackSP.setPreferredSize(new Dimension(100,200));
        drankenSP = new JScrollPane(drankenList);
        drankenSP.setPreferredSize(new Dimension(100,200));
        
        //maken outer
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        contentPane.add(outer);
        
        //maken van keuzePanel
        JPanel keuzeMenu = new JPanel();
        keuzeMenu.setLayout(new GridBagLayout());
        keuzeMenu.setBackground(Color.WHITE);
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty =1;
        keuzeMenu.add(gerechtenLabel,gc);
        
        gc.gridx = 1;
        keuzeMenu.add(bijgerechtenLabel,gc);
        
        gc.gridx = 2;
        keuzeMenu.add(snacksLabel,gc);
        
        gc.gridx = 3;
        keuzeMenu.add(drankenLabel,gc);
        
        gc.gridy = 1;
        gc.gridx =0;
        gc.fill = GridBagConstraints.BOTH;
        keuzeMenu.add(gerechtenSP,gc);
        
        gc.gridx = 1;
        keuzeMenu.add(bijgerechtenSP,gc);
        
        gc.gridx = 2;
        keuzeMenu.add(snackSP,gc);
        
        gc.gridx = 3;
        keuzeMenu.add(drankenSP,gc);
        
        gc.gridy = 2;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-7,0,0,0);
        keuzeMenu.add(gerechtenAddBtn,gc);
        
        gc.gridy = 3;
        gc.gridx = 0;
        gc.insets = new Insets(-20,0,0,0);
        keuzeMenu.add(gerechtenDeleteBtn,gc);
        
        gc.gridy = 2;
        gc.gridx = 1;
        gc.insets = new Insets(-7,0,0,0);
        keuzeMenu.add(bijgerechtenAddBtn,gc);
        
        gc.gridy = 3;
        gc.gridx = 1;
        gc.insets = new Insets(-20,0,0,0);
        keuzeMenu.add(bijgerechtenDeleteBtn,gc);
        
        gc.gridy = 2;
        gc.gridx = 2;
        gc.insets = new Insets(-7,0,0,0);
        keuzeMenu.add(snacksAddBtn,gc);
        
        gc.gridy = 3;
        gc.gridx = 2;
        gc.insets = new Insets(-20,0,0,0);
        keuzeMenu.add(snacksDeleteBtn,gc);
        
        gc.gridx = 3;
        gc.gridy = 2;
        gc.insets = new Insets(-7,0,0,0);
        keuzeMenu.add(drankenAddBtn,gc);
        
        gc.gridy = 3;
        gc.gridx = 3;
        gc.insets = new Insets(-20,0,0,0);
        keuzeMenu.add(drankenDeleteBtn,gc);
        
        //initialisatie van de box
        Box keuzeMaken = Box.createHorizontalBox();
        keuzeMaken.setBorder(BorderFactory.createTitledBorder("Maak uw keuze"));
        keuzeMaken.add(keuzeMenu);
        
        //box keuzeMenu toevoegen aan outer
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0,-500,0,0);
        outer.add(menuSamenstellenLabel,gbc);
        gbc.insets = new Insets(0,635,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,20,0,20);
        outer.add(keuzeMaken,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,630,0,0);
        gbc.fill = GridBagConstraints.NONE;
        outer.add(terugBtn,gbc);
        
        // initialisatie tabels
        
    }
    
    public void actionPerformed(ActionEvent evt) 
    {
        
        if (evt.getSource() == gerechtenAddBtn)
        {
            boolean bestaatal = false ;
            boolean bestaatal2 = false ; 
            
            JTextField box = new JTextField() ;
            box.setPreferredSize(new Dimension(200,20));
             // panel opmaken
            JLabel cate = new JLabel("Naam gerecht:") ;
            JLabel andere = new JLabel("Prijs gerecht:") ;
            JTextField andereT = new JTextField(12) ;
            JPanel outer = new JPanel() ;
            outer.setLayout(new GridLayout(1,2));
            JPanel left = new JPanel() ;
            left.setLayout(new GridBagLayout());
            outer.add(left) ;
            JPanel right = new JPanel() ;
            right.setLayout(new GridBagLayout());
            outer.add(right); 
            
            andereT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               onlyNumbersKeyTyped(evt);
           }
       } ) ;
            
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
            gc.insets = new Insets(0,-50,0,0);
            right.add(box, gc);


            gc.gridy = 1;
            gc.insets = new Insets(30,-50,0,0);
            right.add(andereT,gc);


            
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));      
            UIManager.put("Optionpane.buttonBackground",Color.DARK_GRAY ) ;
            int result = JOptionPane.showConfirmDialog(null, outer, "Gerecht toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
            UIDefaults uiDefaults = UIManager.getDefaults();

            
            
            if(gerechtenLM.contains(box.getText()  + "  €" + andereT.getText()))
            {
                bestaatal = true ;
                
            }
            for (String gerecht : gerechtenTabel)
            {
                if (gerecht.equalsIgnoreCase(box.getText()))
                {
                    bestaatal2 = true ;
                    break ;
                }
            }
            
             if (result == JOptionPane.OK_OPTION)
             {
                 int teller = 0 ;
                 if (andereT.getText().contains("."))
                 {
                     
                     for (int i = 0 ; i < andereT.getText().length() ; i++)
                     {
                         String sub = andereT.getText().substring(i, i+1) ;
                         if (sub.contains("."))
                         {
                             teller ++ ;
                         }
                         
                     }
                 }
                
                  if(box.getText().isEmpty() || andereT.getText().isEmpty())
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (box.getText().length() > 40)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een gerecht kan niet meer dan 40 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (bestaatal2)
                 {
                      UIManager.put("OptionPane.minimumSize", dim) ;
                      JOptionPane.showMessageDialog(null, "Dit gerecht bestaat reeds al!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (andereT.getText().length() > 7)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs kan niet meer dan 7 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (teller > 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat slechts 1 punt", "Error", JOptionPane.ERROR_MESSAGE); 
                     
                 }
                 else if (andereT.getText().contains(".") && andereT.getText().length() == 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat minstens 1 cijfer", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (bestaatal)
                 {
                   UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U kunt niet 2 keer hetzelfd gerecht toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);  
                 }
                 else if (!bestaatal)
                 {
                   gerechtenLM.addElement(box.getText() + " €" + andereT.getText());
                   gerechtenHM.put(box.getText(),Double.parseDouble(andereT.getText())) ;  
                   db.nieuwGerechtBijMenu(box.getText(),Double.parseDouble(andereT.getText()) , 0, db.getMenuIDTakeAway(ingelogdeTakeAway));
                 }
                   
             }
             else{
               System.out.println("error");
             }
        }
        if (evt.getSource() == gerechtenDeleteBtn)
        {
            int index = gerechtenList.getSelectedIndex() ;
             if(!(gerechtenList.getSelectedValue() == null))
             {
                gerechtenLM.removeElement(gerechtenList.getSelectedValue()) ;
                db.verwijderGerecht((String)gerechtenTabel.get(index), db.getMenuIDTakeAway(ingelogdeTakeAway));
             }
             
        }
            
        //bijgerechten add en remove
        if (evt.getSource() == bijgerechtenAddBtn)
        {
            boolean bestaatal = false ;
            boolean bestaatal2 = false ;
            
            JTextField box = new JTextField() ;
             // panel opmaken
            JLabel cate = new JLabel("Naam bijgerecht:") ;
            JLabel andere = new JLabel("Prijs bijgerecht:") ;
            JTextField andereT = new JTextField(12) ;
            JPanel outer = new JPanel() ;
            outer.setLayout(new GridLayout(1,2));
            JPanel left = new JPanel() ;
            left.setLayout(new GridBagLayout());
            outer.add(left) ;
            JPanel right = new JPanel() ;
            right.setLayout(new GridBagLayout());
            outer.add(right); 
            
            andereT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               onlyNumbersKeyTyped(evt);
           }
       } ) ;

            GridBagConstraints gc = new GridBagConstraints() ;

            //elementen toevoegen aan linker panel
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
            
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));      
            UIManager.put("Optionpane.buttonBackground",Color.DARK_GRAY ) ;
            int result = JOptionPane.showConfirmDialog(null, outer, "Bijgerecht toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
            
            if(bijgerechtenLM.contains(box.getText() + " €" + andereT.getText()))
            {
                bestaatal = true ;
            }
            
            for (String bijgerecht : bijgerechtenTabel)
            {
                if (bijgerecht.equalsIgnoreCase(box.getText()))
                {
                    bestaatal2 = true ;
                    break ;
                }
            }
             if (result == JOptionPane.OK_OPTION)
             {
                 int teller = 0 ;
                 if (andereT.getText().contains("."))
                 {
                     
                     for (int i = 0 ; i < andereT.getText().length() ; i++)
                     {
                         String sub = andereT.getText().substring(i, i+1) ;
                         if (sub.contains("."))
                         {
                             teller ++ ;
                         }
                     }
                 }
                 if(box.getText().isEmpty() || andereT.getText().isEmpty())
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (box.getText().length() > 40)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een bijgerecht kan niet meer dan 40 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (andereT.getText().length() > 7)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs kan niet meer dan 7 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (bestaatal2)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Dit bijgerecht bestaat reeds al!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (teller > 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat slechts 1 punt", "Error", JOptionPane.ERROR_MESSAGE); 
                     
                 }
                 else if (andereT.getText().contains(".") && andereT.getText().length() == 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat minstens 1 cijfer", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if(!bestaatal)
                 {
                   bijgerechtenLM.addElement(box.getText() + " €" + andereT.getText());
                   bijgerechtenHM.put(box.getText(), Double.parseDouble(andereT.getText())) ;
                   db.nieuwBijgerechtBijMenu(box.getText(), Double.parseDouble(andereT.getText()), 0, db.getMenuIDTakeAway(ingelogdeTakeAway));
                 }
             }
             else
             {
                 
             }
        }
        if (evt.getSource() == bijgerechtenDeleteBtn)
        {
            if(!(bijgerechtenList.getSelectedValue() == null))
             {
                String item = (String)bijgerechtenList.getSelectedValue() ;
                int index = item.indexOf('€') ;
                String vol = item.substring(0, index-1) ;
                bijgerechtenHM.remove(vol) ;
             }
            int index = bijgerechtenList.getSelectedIndex() ;
            bijgerechtenLM.removeElement(bijgerechtenList.getSelectedValue()) ;
            db.verwijderBijgerecht(bijgerechtenTabel.get(index), db.getMenuIDTakeAway(ingelogdeTakeAway));
        }
        
            //snacks add en remove
        if (evt.getSource() == snacksAddBtn)
        {
            boolean bestaatal = false ;
            boolean bestaatal2 = false ;
            JTextField box = new JTextField() ;
             // panel opmaken
            JLabel cate = new JLabel("Naam snack:") ;
            JLabel andere = new JLabel("Prijs snack:") ;
            JTextField andereT = new JTextField(12) ;
            JPanel outer = new JPanel() ;
            outer.setLayout(new GridLayout(1,2));
            JPanel left = new JPanel() ;
            left.setLayout(new GridBagLayout());
            outer.add(left) ;
            JPanel right = new JPanel() ;
            right.setLayout(new GridBagLayout());
            outer.add(right); 
            
            andereT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               onlyNumbersKeyTyped(evt);
           }
       } ) ;

            GridBagConstraints gc = new GridBagConstraints() ;

            //elementen toevoegen aan linker panel
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
            
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));      
            UIManager.put("Optionpane.buttonBackground",Color.DARK_GRAY ) ;
            int result = JOptionPane.showConfirmDialog(null, outer, "Snack toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
            
            if(snacksLM.contains(box.getText() + " €" + andereT.getText()))
            {
                bestaatal = true ;
            }
            
            for (String snacks   : snacksTabel)
            {
                if (snacks.equalsIgnoreCase(box.getText()))
                {
                    bestaatal2 = true ;
                    break ;
                }
            }
             if (result == JOptionPane.OK_OPTION)
             {
                 int teller = 0 ;
                 if (andereT.getText().contains("."))
                 {
                     
                     for (int i = 0 ; i < andereT.getText().length() ; i++)
                     {
                         String sub = andereT.getText().substring(i, i+1) ;
                         if (sub.contains("."))
                         {
                             teller ++ ;
                         }
                     }
                 }
                   if(box.getText().isEmpty() || andereT.getText().isEmpty())
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                   else if (box.getText().length() > 40)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een snack kan niet meer dan 40 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (andereT.getText().length() > 7)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs kan niet meer dan 7 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (bestaatal2)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Deze snack bestaat reeds al!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 
                 else if (teller > 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat slechts 1 punt", "Error", JOptionPane.ERROR_MESSAGE); 
                     
                 }
                 else if (andereT.getText().contains(".") && andereT.getText().length() == 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat minstens 1 cijfer", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                   else if(!bestaatal)
                 {
                   snacksLM.addElement(box.getText() + " €" + andereT.getText());
                   snacksHM.put(box.getText(), Double.parseDouble(andereT.getText())) ;
                   db.nieuwSnackBijMenu(box.getText(), Double.parseDouble(andereT.getText()), 0 , db.getMenuIDTakeAway(ingelogdeTakeAway));
                  
                 }
                   
             }
             else
             {
                 
             }
            }
            if (evt.getSource() == snacksDeleteBtn)
            {
                if(!(snacksList.getSelectedValue() == null))
                {
                    String item = (String)snacksList.getSelectedValue() ;
                    int index = item.indexOf('€') ;
                    String vol = item.substring(0, index-1) ;
                    snacksHM.remove(vol) ;
                }
                int index = snacksList.getSelectedIndex() ; 
                snacksLM.removeElement(snacksList.getSelectedValue()) ;
                db.verwijderSnack(snacksTabel.get(index), db.getMenuIDTakeAway(ingelogdeTakeAway));
            }
        
            //drank add en remove
            if (evt.getSource() == drankenAddBtn)
            {
                boolean bestaatal = false ;
                boolean bestaatal2 = false ;
            JTextField box = new JTextField() ;
             // panel opmaken
            JLabel cate = new JLabel("Naam drank:") ;
            JLabel andere = new JLabel("Prijs drank:") ;
            JTextField andereT = new JTextField(12) ;
            JPanel outer = new JPanel() ;
            outer.setLayout(new GridLayout(1,2));
            JPanel left = new JPanel() ;
            left.setLayout(new GridBagLayout());
            outer.add(left) ;
            JPanel right = new JPanel() ;
            right.setLayout(new GridBagLayout());
            outer.add(right); 
            
            andereT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               onlyNumbersKeyTyped(evt);
           }
       } ) ;

            GridBagConstraints gc = new GridBagConstraints() ;

            //elementen toevoegen aan linker panel
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
            
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));      
            UIManager.put("Optionpane.buttonBackground",Color.DARK_GRAY ) ;
            int result = JOptionPane.showConfirmDialog(null, outer, "Drank toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
            
            if(drankenLM.contains(box.getText() + " €" + andereT.getText()))
            {
                bestaatal = true ;
            }
            
            for (String dranken : drankenTabel)
            {
                if (dranken.equalsIgnoreCase(box.getText()))
                {
                    bestaatal2 = true ;
                    break ;
                }
            }
             if (result == JOptionPane.OK_OPTION)
             {
                 int teller = 0 ;
                 if (andereT.getText().contains("."))
                 {
                     
                     for (int i = 0 ; i < andereT.getText().length() ; i++)
                     {
                         String sub = andereT.getText().substring(i, i+1) ;
                         if (sub.contains("."))
                         {
                             teller ++ ;
                         }
                     }
                 }
                 if(box.getText().isEmpty() || andereT.getText().isEmpty())
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (box.getText().length() > 40)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een drank kan niet meer dan 40 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (andereT.getText().length() > 7)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs kan niet meer dan 7 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (teller > 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat slechts 1 punt", "Error", JOptionPane.ERROR_MESSAGE); 
                     
                 }
                 
                 else if (bestaatal2)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Deze drank bestaat reeds al!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (andereT.getText().contains(".") && andereT.getText().length() == 1)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Een prijs bevat minstens 1 cijfer", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if(!bestaatal)
                 {
                   drankenLM.addElement(box.getText() + " €" + andereT.getText());
                   drankenHM.put(box.getText(),Double.parseDouble(andereT.getText())) ;
                   db.nieuweDrankBijMenu(box.getText(), Double.parseDouble(andereT.getText()), 0, db.getMenuIDTakeAway(ingelogdeTakeAway));
                 }
             }
             else
             {
                 
             }
        }
        if (evt.getSource() == drankenDeleteBtn)
        {
            if(!(drankenList.getSelectedValue() == null))
             {
                String item = (String)drankenList.getSelectedValue() ;
                int index = item.indexOf('€') ;
                String vol = item.substring(0, index-1) ;
                drankenHM.remove(vol) ;
             }
            int index = drankenList.getSelectedIndex();
            drankenLM.removeElement(drankenList.getSelectedValue()) ;
            db.verwijderDrank(drankenTabel.get(index), db.getMenuIDTakeAway(ingelogdeTakeAway));
        }
        if (evt.getSource() == terugBtn)
        {
            super.dispose();
           GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdeTakeAway);
           guiWindow.setVisible(true);
           guiWindow.setLocationRelativeTo(null);
           guiWindow.setResizable(false);
             
        }
    }
    public void onlyNumbersKeyTyped(KeyEvent e)
    {
        char c = e.getKeyChar() ;
        if(c == KeyEvent.VK_PERIOD)
        {
            
        }
        else if(c == KeyEvent.VK_COMMA)
        {
           e.setKeyChar((char)KeyEvent.VK_PERIOD);
        }
        else if(!(Character.isDigit(c)) )
        {
           e.consume(); 
        }
        else
        {
            
        }
        
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public GUI_TakeAwayRegistreren getGui() {
        return gui;
    }

    public void setGui(GUI_TakeAwayRegistreren gui) {
        this.gui = gui;
    }
}

