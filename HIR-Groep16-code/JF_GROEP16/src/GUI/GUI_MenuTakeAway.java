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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GUI_MenuTakeAway extends JFrame implements ActionListener, ListSelectionListener
{
      
    //declareren van JLabels
    private JLabel menuSamenstellenLabel;
    private JLabel gerechtenLabel;
    private JLabel bijgerechtenLabel;
    private JLabel snacksLabel;
    private JLabel drankenLabel;
    private JLabel takeAwayLabel;
    private JLabel euroWinkelmand;
    
    //declareren van JButtons
    private JButton aanbodTAs;
    private JButton plusBtn;
    private JButton minBtn;
    private JButton winkelMandBenedenBtn;
  
    
    //declareren van JLists
    private DefaultTableModel gerechtenTM = new DefaultTableModel() ;
    private DefaultTableModel bijgerechtenTM = new DefaultTableModel();
    private DefaultTableModel snacksTM = new DefaultTableModel();
    private DefaultTableModel drankenTM = new DefaultTableModel();
    private JTable gerechtenTable;
    private JTable bijgerechtenTable;
    private JTable snacksTable;
    private JTable drankenTable;
    private JScrollPane gerechtenSP;
    private JScrollPane bijgerechtenSP;
    private JScrollPane snackSP;
    private JScrollPane drankenSP;
    
    private Menu menu ;
    private int menuID ;
    
    private HashMap<String,Double> gerechtenHM  ;
    private HashMap<String,Double> bijgerechtenHM  ;
    private HashMap<String,Double> snacksHM  ;
    private HashMap<String,Double> drankenHM  ;
    
    private DecimalFormat f = new DecimalFormat("##.00");
    
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize") ;
    
    private String takeAway ;
    private String klant ;
    private int select ;
    private Bestelling bestelling ;
    
    private Double bedrag ;
    private Double totaalbedrag ;
    
    private int vestigingsID ;
    
    DB db = new DB() ;
    
    public GUI_MenuTakeAway(String naamTakeAway, Bestelling b)
    {
        super("Just Feed");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        takeAway = naamTakeAway ;
        menuID = db.getMenuIDTakeAway(takeAway) ;
        bestelling = b ;
        bedrag = 0.00 ;
        vestigingsID = db.getVestigingsIDInLeveringsgebiedVanTakeAway(takeAway, bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente()) ;
        
        
        gerechtenHM = db.getGerechtenEnPrijzenTakeAway(takeAway) ;
        bijgerechtenHM = db.getBijgerechtenEnPrijzenTakeAway(takeAway) ;
        snacksHM = db.getSnacksEnPrijzenTakeAway(takeAway) ;
        drankenHM = db.getDrankenEnPrijzenTakeAway(takeAway) ;
       
        //initialisatie JLabels
        menuSamenstellenLabel = new JLabel();
        menuSamenstellenLabel.setText("Menu ");
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
        takeAwayLabel = new JLabel();
        takeAwayLabel.setText(takeAway);
        takeAwayLabel.setFont(new Font("Lucida Bright", Font.BOLD ,25));
        euroWinkelmand = new JLabel();
        
        //initialisatie van de JButtons      
        plusBtn = new JButton();
        plusBtn.setText("+");
        plusBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        plusBtn.setForeground(Color.WHITE);
        plusBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        plusBtn.setFocusable(false);
        plusBtn.setBackground(Color.DARK_GRAY);
        plusBtn.addActionListener(this);
        
        minBtn = new JButton();
        minBtn.setText("-");
        minBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        minBtn.setForeground(Color.WHITE);
        minBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        minBtn.setFocusable(false);
        minBtn.setBackground(Color.DARK_GRAY);
        minBtn.addActionListener(this);
                
        
        aanbodTAs = new JButton();
        aanbodTAs.setText("Annuleren");
        aanbodTAs.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        aanbodTAs.setForeground(Color.WHITE);
        aanbodTAs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aanbodTAs.setFocusable(false);
        aanbodTAs.setBackground(Color.DARK_GRAY);
        aanbodTAs.addActionListener(this);
        
        winkelMandBenedenBtn = new JButton();
        winkelMandBenedenBtn.setText("Voeg keuze toe aan winkelmandje");
        winkelMandBenedenBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        winkelMandBenedenBtn.setForeground(Color.WHITE);
        winkelMandBenedenBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        winkelMandBenedenBtn.setFocusable(false);
        winkelMandBenedenBtn.setBackground(Color.DARK_GRAY);
        winkelMandBenedenBtn.addActionListener(this);
              
        
        //initialisatie van de JTable
        gerechtenTable = new JTable(gerechtenTM);
        gerechtenTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gerechtenTable.getSelectionModel().addListSelectionListener(this);
        bijgerechtenTable = new JTable(bijgerechtenTM);
        bijgerechtenTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        bijgerechtenTable.getSelectionModel().addListSelectionListener(this);
        snacksTable = new JTable(snacksTM);
        snacksTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        snacksTable.getSelectionModel().addListSelectionListener(this);
        drankenTable = new JTable(drankenTM);
        drankenTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        drankenTable.getSelectionModel().addListSelectionListener(this);
        
        //layout van de tables en de tableModels
     
        gerechtenTM.addColumn("Gerecht");
        gerechtenTM.addColumn("Prijs");
        gerechtenTM.addColumn("#");
        gerechtenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        gerechtenTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        gerechtenTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        gerechtenTable.getColumnModel().getColumn(2).setPreferredWidth(20) ;
        bijgerechtenTM.addColumn("Bijgerecht");
        bijgerechtenTM.addColumn("Prijs");
        bijgerechtenTM.addColumn("#");
        bijgerechtenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        bijgerechtenTable.getColumnModel().getColumn(0).setPreferredWidth(112);
        bijgerechtenTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        bijgerechtenTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        snacksTM.addColumn("Snack");
        snacksTM.addColumn("Prijs");
        snacksTM.addColumn("#");
        snacksTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        snacksTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        snacksTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        snacksTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        drankenTM.addColumn("Drank");
        drankenTM.addColumn("Prijs");
        drankenTM.addColumn("#");
        drankenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        drankenTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        drankenTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        drankenTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        gerechtenTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        bijgerechtenTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        snacksTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        drankenTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
       
        
        //initialisatie van de scrollpanes 
        gerechtenSP = new JScrollPane(gerechtenTable);
        gerechtenSP.setPreferredSize(new Dimension(107,200));
        gerechtenSP.getViewport().setBackground(Color.WHITE);
        gerechtenSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bijgerechtenSP = new JScrollPane(bijgerechtenTable);
        bijgerechtenSP.setPreferredSize(new Dimension(107,200));
        bijgerechtenSP.getViewport().setBackground(Color.WHITE);
        bijgerechtenSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        snackSP = new JScrollPane(snacksTable);
        snackSP.setPreferredSize(new Dimension(107,200));
        snackSP.getViewport().setBackground(Color.WHITE);
        snackSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        drankenSP = new JScrollPane(drankenTable);
        drankenSP.setPreferredSize(new Dimension(107,200));
        drankenSP.getViewport().setBackground(Color.WHITE);
        drankenSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        
        //maken container
        Container contentPane = getContentPane();
        
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
        //gc.anchor = GridBagConstraints.LINE_START;
        keuzeMenu.add(gerechtenLabel,gc);
        gc.gridx = 1;
        keuzeMenu.add(bijgerechtenLabel,gc);
        gc.gridx = 2;
        keuzeMenu.add(snacksLabel,gc);
        gc.gridx = 3;
        keuzeMenu.add(drankenLabel,gc);
        
        gc.gridy = 1;
        gc.gridx =0;
        gc.insets = new Insets(0,15,15,5);
        gc.fill = GridBagConstraints.BOTH;
        keuzeMenu.add(gerechtenSP,gc);
        gc.gridx = 1;
        gc.insets = new Insets(0,0,15,5);
        keuzeMenu.add(bijgerechtenSP,gc);
        gc.gridx = 2;
        gc.insets = new Insets(0,0,15,5);
        keuzeMenu.add(snackSP,gc);
        gc.gridx = 3;
        gc.insets = new Insets(0,0,15,15);
        keuzeMenu.add(drankenSP,gc);
        
        
        //Panel Selectie bewerken
        JPanel selectieBewerkenPanel = new JPanel();
        selectieBewerkenPanel.setLayout(new GridBagLayout());
        selectieBewerkenPanel.setBackground(Color.WHITE);
        GridBagConstraints sbgc = new GridBagConstraints();
        sbgc.weightx = 1;
        sbgc.weighty = 1;
        sbgc.gridx = 0;
        sbgc.gridy = 0;
        sbgc.insets = new Insets(10,10,10,10);
        selectieBewerkenPanel.add(plusBtn,sbgc);
        sbgc.gridx = 1;
        sbgc.insets = new Insets(10,0,10,10);
        selectieBewerkenPanel.add(minBtn,sbgc);
        
        //initialisatie van de boxen
        Box keuzeMaken = Box.createHorizontalBox();
        keuzeMaken.setBorder(BorderFactory.createTitledBorder("Maak uw keuze"));
        keuzeMaken.add(keuzeMenu);
        Box selectieBewerken = Box.createHorizontalBox();
        selectieBewerken.setBorder(BorderFactory.createTitledBorder("Slectie bewerken"));
        selectieBewerken.add(selectieBewerkenPanel);
        
        //box keuzeMenu toevoegen aan outer
        GridBagConstraints gbc = new GridBagConstraints();
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.weightx = 1;
        gbc1.weighty = 0.3;
        
        //gbc.insets = new Insets(0,-650,0,0);
       // outer.add(menuSamenstellenLabel,gbc);
        //gbc.insets = new Insets(0,-400,0,0);
        gbc1.anchor = GridBagConstraints.FIRST_LINE_START;
        takeAwayLabel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0,0));
        outer.add(takeAwayLabel,gbc1);
        //gbc.insets = new Insets(0,700,0,0);
        
        
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        gbc1.weightx = 1;
        gbc1.weighty = 0.3;
        //euroWinkelmand.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        gbc1.insets = new Insets(20,-60,0,0);
        outer.add(euroWinkelmand,gbc1);
        
        //gbc.insets = new Insets(0,635,0,0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,20,0,20);
        outer.add(keuzeMaken,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(-20,-580,0,0); //oud-625
        outer.add(selectieBewerken,gbc);
        gbc.insets = new Insets(0,30,0,0);
        outer.add(aanbodTAs,gbc);
        gbc.insets = new Insets(0,470,0,0);
        outer.add(winkelMandBenedenBtn,gbc);
        
        // opvullen menu
        for (String g : gerechtenHM.keySet()) 
        {
            if (bestelling.getMenuGerecht().containsKey(takeAway) )
            {
                int hoeveelheid = 0 ;
                if (bestelling.getMenuGerecht().get(takeAway).containsKey(g))
                {
                    hoeveelheid = bestelling.getMenuGerecht().get(takeAway).get(g) ;
                }
                Object[] s = new Object[]{g, "€" + f.format(gerechtenHM.get(g)), hoeveelheid};
                bedrag += gerechtenHM.get(g)*hoeveelheid ;
                gerechtenTM.addRow(s);
            }
            else
            {
                Object[] s = new Object[]{g, "€" + f.format(gerechtenHM.get(g)), 0};
                gerechtenTM.addRow(s);
            }
        }
        for (String p : bijgerechtenHM.keySet()) 
        {
            if (bestelling.getMenuBijGerecht().containsKey(takeAway))
            {
                int hoeveelheid = 0 ;
                if (bestelling.getMenuBijGerecht().get(takeAway).containsKey(p))
                {
                    hoeveelheid = bestelling.getMenuBijGerecht().get(takeAway).get(p) ;
                }
                Object[] s = new Object[]{p, "€" + f.format(bijgerechtenHM.get(p)), hoeveelheid};
                bedrag += bijgerechtenHM.get(p)*hoeveelheid ;
                bijgerechtenTM.addRow(s);
            }
            else
            {
                Object[] s = new Object[]{p, "€" + f.format(bijgerechtenHM.get(p)), 0};
                bijgerechtenTM.addRow(s);
            }
        }
        for (String d : drankenHM.keySet()) 
        {
            if(bestelling.getMenuDranken().containsKey(takeAway))
            {
                int hoeveelheid = 0 ;
                if (bestelling.getMenuDranken().get(takeAway).containsKey(d))
                {
                    hoeveelheid = bestelling.getMenuDranken().get(takeAway).get(d) ;
                }
                Object[] s = new Object[]{d,"€" + f.format(drankenHM.get(d)), hoeveelheid};
                bedrag += drankenHM.get(d)*hoeveelheid ;
                drankenTM.addRow(s);
            }
            else
            {
                Object[] s = new Object[]{d,"€" + f.format(drankenHM.get(d)), 0};
                drankenTM.addRow(s);
            }
        }
        for (String snacks : snacksHM.keySet()) 
        {
            if(bestelling.getMenuSnacks().containsKey(takeAway))
            {
                int hoeveelheid = 0 ;
                if (bestelling.getMenuSnacks().get(takeAway).containsKey(snacks))
                {
                    hoeveelheid = bestelling.getMenuSnacks().get(takeAway).get(snacks) ;
                }
                Object[] s = new Object[]{snacks, "€" + f.format(snacksHM.get(snacks)), hoeveelheid};
                bedrag += snacksHM.get(snacks)*hoeveelheid ;
                snacksTM.addRow(s);
            }
            else
            {
                Object[] s = new Object[]{snacks, "€" + f.format(snacksHM.get(snacks)), 0};
                snacksTM.addRow(s);
            }
        }
       
       euroWinkelmand.setText("€" + f.format(bedrag));

        
    }
    
    public void actionPerformed(ActionEvent evt) 
    {
        if (evt.getSource() == plusBtn)
        {
           JTable[] tables = new JTable[] {gerechtenTable, bijgerechtenTable, snacksTable, drankenTable} ;
           DefaultTableModel[] models = new DefaultTableModel[] {gerechtenTM, bijgerechtenTM, snacksTM, drankenTM} ;
           if (tables[select].getSelectedRow() != -1)
           {
                int hoev = (int)models[select].getValueAt(tables[select].getSelectedRow(), 2) ;
                hoev++ ;
                models[select].setValueAt(hoev, tables[select].getSelectedRow(), 2);
                String metEuroteken = (String)models[select].getValueAt(tables[select].getSelectedRow(), 1);
                String zonderEuroteken = metEuroteken.substring(1) ;
                String p = zonderEuroteken.replace(',', '.') ;
                bedrag += Double.parseDouble(p) ;
                euroWinkelmand.setText("€" + f.format(bedrag));
           }
        }
        if (evt.getSource() == minBtn)
        {
           JTable[] tables = new JTable[] {gerechtenTable, bijgerechtenTable, snacksTable, drankenTable} ;
           DefaultTableModel[] models = new DefaultTableModel[] {gerechtenTM, bijgerechtenTM, snacksTM, drankenTM} ;
           if(tables[select].getSelectedRow() != -1)
           {
                int hoev = (int)models[select].getValueAt(tables[select].getSelectedRow(), 2) ; 
                if (hoev > 0)
                {
                    hoev -- ;
                    models[select].setValueAt(hoev, tables[select].getSelectedRow(), 2);
                    String metEuroteken = (String)models[select].getValueAt(tables[select].getSelectedRow(), 1);
                    String zonderEuroteken = metEuroteken.substring(1) ;
                    String p = zonderEuroteken.replace(',', '.') ;
                    bedrag -= Double.parseDouble(p) ;
                    euroWinkelmand.setText("€" + f.format(bedrag));
                }
           }
           
        }
        if (evt.getSource() == aanbodTAs)
        {
            super.dispose();
            GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(klant, totaalbedrag, bestelling);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (evt.getSource() == winkelMandBenedenBtn)
        {
            Object[][] gerechten = getTableData(gerechtenTable) ;
            Object[][] bijgerechten = getTableData(bijgerechtenTable) ;
            Object[][] snacks = getTableData(snacksTable) ;
            Object[][] dranken = getTableData(drankenTable) ;
            
            HashMap<String, String> gerechtenprijs = new HashMap() ;
            HashMap<String, String> bijgerechtenprijs = new HashMap() ;
            HashMap<String, String> snacksprijs = new HashMap() ;
            HashMap<String, String> drankenprijs = new HashMap() ;
            
            HashMap<String, Integer> gerechtenhoev = new HashMap() ;
            HashMap<String, Integer> bijgerechtenhoev = new HashMap() ;
            HashMap<String, Integer> snackshoev = new HashMap() ;
            HashMap<String, Integer> drankenhoev = new HashMap() ;
            
            for(int i = 0 ; i< gerechtenTable.getRowCount() ; i++)
            {
                if((int)gerechten[i][2] > 0)
                {
                   gerechtenhoev.put((String)gerechten[i][0], (int)gerechten[i][2]) ;
                   gerechtenprijs.put((String)gerechten[i][0], (String)gerechten[i][1]) ;
                }
            }
            for (int i = 0 ; i<bijgerechtenTable.getRowCount() ; i++)
            {
                if ((int)bijgerechten[i][2] > 0)
                {
                    bijgerechtenhoev.put((String)bijgerechten[i][0], (int)bijgerechten[i][2]) ;
                    bijgerechtenprijs.put((String)bijgerechten[i][0], (String)bijgerechten[i][1]) ;
                }
            }
            for (int i = 0 ; i< snacksTable.getRowCount() ; i++)
            {
                if((int)snacks[i][2] > 0)
                {
                   snackshoev.put((String)snacks[i][0], (int)snacks[i][2]) ;
                   snacksprijs.put((String)snacks[i][0], (String)snacks[i][1]) ;
                }
            }
            for (int i = 0 ; i< drankenTable.getRowCount() ; i++)
            {
                if((int)dranken[i][2] > 0)
                {
                   drankenhoev.put((String)dranken[i][0], (int)dranken[i][2]) ;
                   drankenprijs.put((String)dranken[i][0], (String)dranken[i][1]) ;
                }
            }
            
            bestelling.getMenuGerecht().put(takeAway, gerechtenhoev) ;
            bestelling.getMenuBijGerecht().put(takeAway, bijgerechtenhoev) ;
            bestelling.getMenuSnacks().put(takeAway, snackshoev) ;
            bestelling.getMenuDranken().put(takeAway, drankenhoev) ;
            
            bestelling.getGerechtenPrijslijst().put(takeAway, gerechtenprijs) ;
            bestelling.getBijgerechtenPrijslijst().put(takeAway, bijgerechtenprijs) ;
            bestelling.getSnacksPrijslijst().put(takeAway, snacksprijs) ;
            bestelling.getDrankenPrijslijst().put(takeAway, drankenprijs) ;
            
            bestelling.getVestigingen().add(vestigingsID) ;
            
           
            super.dispose();
            GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(klant, totaalbedrag + bedrag, bestelling);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
            
        }
    
    }
   
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if (e.getSource() == gerechtenTable.getSelectionModel())
        {
            if (!e.getValueIsAdjusting())
            {
                if(select == 1)
                {
                    bijgerechtenTable.getSelectionModel().clearSelection();
                }
                if(select == 2)
                {
                    snacksTable.getSelectionModel().clearSelection();
                }
                if(select == 3)
                {
                    drankenTable.getSelectionModel().clearSelection();
                }
                
                select = 0 ;
            }
        }
        if (e.getSource() == bijgerechtenTable.getSelectionModel())
        {
            if (!e.getValueIsAdjusting())
            {
                if(select == 3)
                {
                    drankenTable.getSelectionModel().clearSelection();
                }
                if(select == 2)
                {
                    snacksTable.getSelectionModel().clearSelection();
                }
                if(select == 0)
                {
                    gerechtenTable.getSelectionModel().clearSelection();
                }
                select = 1 ;
            }
        }
        if (e.getSource() == snacksTable.getSelectionModel())
        {
            if (!e.getValueIsAdjusting())
            {
                if(select == 1)
                {
                    bijgerechtenTable.getSelectionModel().clearSelection();
                }
                if(select == 0)
                {
                    gerechtenTable.getSelectionModel().clearSelection();
                }
                if(select == 3)
                {
                    drankenTable.getSelectionModel().clearSelection();
                }
                select = 2 ;
            }
        }
        if (e.getSource() == drankenTable.getSelectionModel())
        {
            if (!e.getValueIsAdjusting())
            {
                if(select == 1)
                {
                    bijgerechtenTable.getSelectionModel().clearSelection();
                }
                if(select == 0)
                {
                    gerechtenTable.getSelectionModel().clearSelection();
                }
                if(select == 2)
                {
                    snacksTable.getSelectionModel().clearSelection();
                }
                select = 3 ;
            }
        }
    }
    public Object[][] getTableData (JTable table) 
    {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = dtm.getValueAt(i,j);
    return tableData;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getKlant() {
        return klant;
    }

    public void setKlant(String klant) {
        this.klant = klant;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Double getTotaalbedrag() {
        return totaalbedrag;
    }

    public void setTotaalbedrag(Double totaalbedrag) {
        this.totaalbedrag = totaalbedrag;
    }
    
    
  
   
}


