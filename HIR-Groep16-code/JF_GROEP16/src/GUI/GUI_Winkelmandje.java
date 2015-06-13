package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
 import javax.swing.Box;
import javax.swing.DefaultListModel;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GUI_Winkelmandje extends JFrame implements ActionListener
{
    //declaratie van Labels
    private JLabel winkelmandjeLabel;
    private JLabel welkomLabel;
    private JLabel voornaamLabel;
    private JLabel prijsLabel;
    private JLabel leveringskostLabel;
    private JLabel kortingLabel;
    private JLabel totaalprijsLabel;
    private JLabel prijsEuroLabel;
    private JLabel leveringskostenEuroLabel;
    private JLabel kortingEuroLabel;
    private JLabel totaleprijsEuroLabel;

   //declaratie van de Buttons
    private JButton verwijderBtn;
    private JButton plusBtn;
    private JButton minBtn;
    private JButton extraGerechtBtn;
    private JButton kortingBtn;
    private JButton nogHongerBtn;
    private JButton betaalBtn;

   //declaratie van de boxen
    private Box mandjeBox;
    private Box pasHoeveelheidAanBox;
    private Box bestellingBox;
    private Box subprijsBox;
    private Box totaalprijsBox;
    
    //declaratie van de Jtable
    private DefaultTableModel gerechtenTM = new DefaultTableModel();
    private JTable gerechtenTable;
    private JScrollPane gerechtenTableSP;
    
    private DecimalFormat f = new DecimalFormat("##.00");
    
    private String klant ;
    private Bestelling bestelling ;
    private Double vorigeTotaalBedrag ;
    private Double totaalbedrag ;
    
    private Double leveringskosten ;
    
    DB db = new DB() ;
     


 public GUI_Winkelmandje(String ingelogdeklant, Bestelling b)
 {
    super("Just Feed - Winkelmandje");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    klant = ingelogdeklant ;
    bestelling = b ;
    totaalbedrag = 0.00 ;
    leveringskosten = 0.00 ;
    
    //tableModel aanpassen
     gerechtenTM.addColumn("TakeAway");
     gerechtenTM.addColumn("Gerecht");
     gerechtenTM.addColumn("Hoeveelheid");
     gerechtenTM.addColumn("Prijs/Stuk");
     

    //initialisatie van de Labels
    winkelmandjeLabel = new JLabel();
    winkelmandjeLabel.setText("Winkelmandje");
    winkelmandjeLabel.setFont(new Font("Lucida Bright", Font.BOLD ,20));
    welkomLabel = new JLabel();
    welkomLabel.setText("Welkom");
    welkomLabel.setFont(new Font("Lucida Bright", Font.BOLD ,15));
    voornaamLabel = new JLabel("Underlined Label");
    voornaamLabel.setFont(new Font("Lucida Bright", Font.BOLD ,15));
    Font font = voornaamLabel.getFont();
    Map attributes = font.getAttributes();
    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    voornaamLabel.setFont(font.deriveFont(attributes));
    voornaamLabel.setText(db.getVoornaamVanKlantlogin(klant));
    voornaamLabel.setBackground(Color.BLUE);
    voornaamLabel.setForeground(Color.BLUE);
    prijsLabel = new JLabel();
    prijsLabel.setText("Prijs");
    leveringskostLabel = new JLabel();
    leveringskostLabel.setText("Leveringskosten");
    kortingLabel = new JLabel();
    kortingLabel.setText("Korting");
    totaalprijsLabel = new JLabel();
    totaalprijsLabel.setText("Te betalen prijs");
    prijsEuroLabel = new JLabel();
    prijsEuroLabel.setText("€" + f.format(totaalbedrag));
    leveringskostenEuroLabel = new JLabel();
    leveringskostenEuroLabel.setText("€" + f.format(leveringskosten));
    kortingEuroLabel = new JLabel();
    kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
    totaleprijsEuroLabel = new JLabel();
    totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));

    //initialisatie van de Buttons
    verwijderBtn = new JButton();
    verwijderBtn.setText("Verwijder");
    verwijderBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
    verwijderBtn.setForeground(Color.WHITE);
    verwijderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    verwijderBtn.setFocusable(false);
    verwijderBtn.setBackground(Color.DARK_GRAY);
    verwijderBtn.addActionListener(this);
    plusBtn = new JButton();
    plusBtn.setText("+");
    plusBtn.setFont(new Font("Lucida Bright", Font.BOLD ,20));
    plusBtn.setForeground(Color.WHITE);
    plusBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    plusBtn.setFocusable(false);
    plusBtn.setBackground(Color.DARK_GRAY);
    plusBtn.addActionListener(this);
    minBtn = new JButton();
    minBtn.setText("-");
    minBtn.setFont(new Font("Lucida Bright", Font.BOLD ,20));
    minBtn.setForeground(Color.WHITE);
    minBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    minBtn.setFocusable(false);
    minBtn.setBackground(Color.DARK_GRAY);
    minBtn.addActionListener(this);
    extraGerechtBtn = new JButton();
    extraGerechtBtn.setText("Extra Gerecht");
    extraGerechtBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
    extraGerechtBtn.setForeground(Color.WHITE);
    extraGerechtBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    extraGerechtBtn.setFocusable(false);
    extraGerechtBtn.setBackground(Color.DARK_GRAY);
    extraGerechtBtn.addActionListener(this);
    kortingBtn = new JButton();
    kortingBtn.setText("Gebruik korting");
    kortingBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
    kortingBtn.setForeground(Color.WHITE);
    kortingBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    kortingBtn.setFocusable(false);
    kortingBtn.setBackground(Color.DARK_GRAY);
    kortingBtn.addActionListener(this);
    nogHongerBtn = new JButton();
    nogHongerBtn.setText("Nog honger?");
    nogHongerBtn.setFont(new Font("Lucida Bright", Font.BOLD ,16));
    nogHongerBtn.setForeground(Color.WHITE);
    nogHongerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    nogHongerBtn.setFocusable(false);
    nogHongerBtn.setBackground(Color.DARK_GRAY);
    nogHongerBtn.addActionListener(this);
    betaalBtn = new JButton();
    betaalBtn.setText("Reken Af");
    betaalBtn.setFont(new Font("Lucida Bright", Font.BOLD ,16));
    betaalBtn.setForeground(Color.WHITE);
    betaalBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    betaalBtn.setFocusable(false);
    betaalBtn.setBackground(Color.DARK_GRAY);
    betaalBtn.addActionListener(this);
    
    //initialisatie van de JList
    gerechtenTable = new JTable(gerechtenTM);
    gerechtenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    gerechtenTable.setBackground(Color.WHITE);
    gerechtenTable.setOpaque(true);
    gerechtenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    gerechtenTable.getColumnModel().getColumn(0).setPreferredWidth(152);
    gerechtenTable.getColumnModel().getColumn(1).setPreferredWidth(153);
    gerechtenTable.getColumnModel().getColumn(2).setPreferredWidth(80);
    gerechtenTable.getColumnModel().getColumn(3).setPreferredWidth(80);

    
    //maken container
    Container contentPane = getContentPane();
        
    //maken outer
    JPanel outer = new JPanel();
    outer.setLayout(new GridBagLayout());
    outer.setBackground(Color.WHITE);
    contentPane.add(outer);
    
    
    //maken van Panel voor Box "pas hoeveelheid aan"
    JPanel pasHoeveelheidAanPanel = new JPanel();
    pasHoeveelheidAanPanel.setLayout(new GridBagLayout());
    pasHoeveelheidAanPanel.setBackground(Color.WHITE);
    GridBagConstraints g = new GridBagConstraints();
    g.weightx = 1;
    g.weighty = 1;
    g.gridx = 0;
    g.gridy = 0;
    g.insets = new Insets(0,5,5,0);
    pasHoeveelheidAanPanel.add(plusBtn,g);
    g.gridx = 1;
    pasHoeveelheidAanPanel.add(minBtn,g);
    pasHoeveelheidAanBox = Box.createHorizontalBox();
    pasHoeveelheidAanBox.setBorder(BorderFactory.createTitledBorder("Pas hoeveelheid aan"));
    pasHoeveelheidAanBox.add(pasHoeveelheidAanPanel);
    
    
    //maken van Panel voor Box "winkelmandje"
    gerechtenTableSP = new JScrollPane(gerechtenTable);
    gerechtenTableSP.setPreferredSize(new Dimension(310,160));
    gerechtenTableSP.setBackground(Color.WHITE);
    gerechtenTableSP.getViewport().setBackground(Color.WHITE);
    gerechtenTableSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JPanel winkelmandjePanel = new JPanel();
    winkelmandjePanel.setLayout(new GridBagLayout());
    winkelmandjePanel.setBackground(Color.WHITE);
    GridBagConstraints gc = new GridBagConstraints();
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.weightx = 0.8;
    gc.weighty = 1;
    gc.gridx = 0;
    gc.gridy = 0;
    gc.insets = new Insets(20,20,10,0);
    winkelmandjePanel.add(gerechtenTableSP,gc);
    gc.gridx = 1;
    gc.weightx = 0.2;
    gc.weighty = 0.3;
    gc.insets = new Insets(-120,20,0,20);
    winkelmandjePanel.add(verwijderBtn,gc);
    gc.weighty = 0.3;
    gc.insets = new Insets(50,50,50,50);
    winkelmandjePanel.add(pasHoeveelheidAanBox,gc);
    gc.insets = new Insets(140,20,0,20);
    winkelmandjePanel.add(extraGerechtBtn,gc);
    mandjeBox = Box.createHorizontalBox();
    mandjeBox.setBorder(BorderFactory.createTitledBorder("Overzicht bestelling"));
    mandjeBox.add(winkelmandjePanel);
    
    
    //maken van Panel "Subprijs" + aan Box "SubprijsBox" toevoegen
    JPanel subprijsPanel = new JPanel();
    subprijsPanel.setLayout(new GridBagLayout());
    subprijsPanel.setBackground(Color.WHITE);
    GridBagConstraints grid = new GridBagConstraints();
    grid.fill = GridBagConstraints.HORIZONTAL;
    grid.weightx = 1;
    grid.weighty = 1;
    grid.gridx = 0;
    grid.gridy = 0;
    grid.insets = new Insets(0,20,0,0);
    subprijsPanel.add(prijsLabel,grid);
    grid.gridy = 1;
    subprijsPanel.add(leveringskostLabel,grid);
    grid.gridy = 2;
    subprijsPanel.add(kortingLabel,grid);
    grid.gridx = 1;
    grid.gridy = 0;
    subprijsPanel.add(prijsEuroLabel,grid);
    grid.gridy = 1;
    subprijsPanel.add(leveringskostenEuroLabel,grid);
    grid.gridy = 2;
    subprijsPanel.add(kortingEuroLabel,grid);
    subprijsBox = Box.createHorizontalBox();
    subprijsBox.setBorder(BorderFactory.createTitledBorder("Subprijs"));
    subprijsBox.add(subprijsPanel);
    
    //maken van Panel "Totaalprijs" + aan Box "TotaalprijsBox" toevoegen
    JPanel totaalprijsPanel = new JPanel();
    totaalprijsPanel.setLayout(new GridBagLayout());
    totaalprijsPanel.setBackground(Color.WHITE);
    GridBagConstraints tgrid = new GridBagConstraints();
    tgrid.fill = GridBagConstraints.HORIZONTAL;
    tgrid.weightx = 1;
    tgrid.weighty = 1;
    tgrid.gridx = 0;
    tgrid.gridy = 0;
    tgrid.insets = new Insets(0,20,0,0);
    totaalprijsPanel.add(totaalprijsLabel,tgrid);
    tgrid.gridx = 1;
    tgrid.insets = new Insets(0,55,0,0);
    totaalprijsPanel.add(totaleprijsEuroLabel,tgrid);
    totaalprijsBox = Box.createHorizontalBox();
    totaalprijsBox.setBorder(BorderFactory.createTitledBorder("Totaalprijs"));
    totaalprijsBox.add(totaalprijsPanel);
    
    //maken van Panel met Bestelling Overview +aan Box "Bestelling Overview" toeveogen
    JPanel bestellingOverviewPanel = new JPanel();
    bestellingOverviewPanel.setLayout(new GridBagLayout());
    bestellingOverviewPanel.setBackground(Color.WHITE);
    GridBagConstraints bgrid = new GridBagConstraints();
    bgrid.fill = GridBagConstraints.HORIZONTAL;
    bgrid.weightx = 0.8;
    bgrid.weighty = 1;
    bgrid.gridx = 0;
    bgrid.gridy = 0;
    bgrid.insets = new Insets(15,15,0,0);
    bestellingOverviewPanel.add(subprijsBox,bgrid);
    bgrid.gridy = 1;
    bgrid.insets = new Insets(15,15,15,0);
    bestellingOverviewPanel.add(totaalprijsBox,bgrid);
    bgrid.weightx = 0.2;
    bgrid.gridx = 1;
    bgrid.gridy = 0;
    bgrid.fill = GridBagConstraints.HORIZONTAL;
    bgrid.insets = new Insets(10,20,20,20);
    bestellingOverviewPanel.add(kortingBtn,bgrid);
    bestellingBox = Box.createHorizontalBox();
    bestellingBox.setBorder(BorderFactory.createTitledBorder("Overzicht prijsbepaling"));
    bestellingBox.add(bestellingOverviewPanel);
    
    //samenstellen van outer
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(30,25,0,20);
    outer.add(winkelmandjeLabel,gbc);
    gbc.insets = new Insets(30,620,0,20);
    outer.add(welkomLabel,gbc);
    gbc.insets = new Insets(30,685,0,20);
    outer.add(voornaamLabel,gbc);
    gbc.gridy = 1;
    gbc.insets = new Insets(0,20,0,20);
    outer.add(mandjeBox,gbc);
    gbc.gridy = 2;
    outer.add(bestellingBox,gbc);
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets = new Insets(0,340,0,0);
    outer.add(nogHongerBtn,gbc);
    gbc.insets = new Insets(0,639,0,0);
    outer.add(betaalBtn,gbc);
    
    // invullen winkelmandje
    for (String ta : bestelling.getMenuGerecht().keySet() ) 
    {
        for (String ger : bestelling.getMenuGerecht().get(ta).keySet())
        {
            Object[] s = new Object[]{ta, ger , bestelling.getMenuGerecht().get(ta).get(ger) ,bestelling.getGerechtenPrijslijst().get(ta).get(ger) };
            gerechtenTM.addRow(s);
        }
        if (bestelling.getMenuBijGerecht().get(ta) != null)
        {
            for (String bijg : bestelling.getMenuBijGerecht().get(ta).keySet())
            {
                Object[] s = new Object[]{ta, bijg , bestelling.getMenuBijGerecht().get(ta).get(bijg) ,bestelling.getBijgerechtenPrijslijst().get(ta).get(bijg) };
                gerechtenTM.addRow(s);
            }
        }
        if (bestelling.getMenuSnacks().get(ta) != null)
        {
            for (String sn : bestelling.getMenuSnacks().get(ta).keySet())
            {
                Object[] s = new Object[]{ta, sn , bestelling.getMenuSnacks().get(ta).get(sn) ,bestelling.getSnacksPrijslijst().get(ta).get(sn) };
                gerechtenTM.addRow(s); 
            }
        }
        if (bestelling.getMenuDranken().get(ta) != null)
        {
            for (String dr : bestelling.getMenuDranken().get(ta).keySet())
            {
                Object[] s = new Object[]{ta, dr , bestelling.getMenuDranken().get(ta).get(dr) ,bestelling.getDrankenPrijslijst().get(ta).get(dr) };
                gerechtenTM.addRow(s);
            }
        }
        
    }
    // totaalprijs
    
     prijsEuroLabel.setText("€" + f.format(prijsberekenen()));
     leveringskostenEuroLabel.setText("€" + f.format(Leveringskostenberekenen()));
     kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
     totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
     
    
    
    }
 
 
    public void actionPerformed(ActionEvent evt) 
    {
        if (evt.getSource() == nogHongerBtn)
        {
            super.dispose(); 
            GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(klant, prijsberekenen(), bestelling);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (evt.getSource() == extraGerechtBtn)
        {
            super.dispose();
            GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(klant, prijsberekenen(), bestelling);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (evt.getSource() == verwijderBtn)
        {
           if (gerechtenTable.getSelectedRow() != -1)
           {
                if (bestelling.getMenuGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                   bestelling.getGerechtenPrijslijst().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                }
                else if (bestelling.getMenuBijGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuBijGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                   bestelling.getBijgerechtenPrijslijst().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                }
                else if (bestelling.getMenuSnacks().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuSnacks().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                   bestelling.getSnacksPrijslijst().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                }
                else if (bestelling.getMenuDranken().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuDranken().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                   bestelling.getDrankenPrijslijst().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).remove((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)) ;
                }
                else 
                {
                    System.out.println("Error");
                }
               int counter = 0 ;
               for (int i = 0 ; i< gerechtenTM.getRowCount() ; i++)
               {
                   if (gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0).equals(gerechtenTM.getValueAt(i, 0)))
                   {
                       counter++ ;
                   }
               }
               if (counter == 1)
               {
                  bestelling.getVestigingen().remove((Integer)db.getVestigingsIDInLeveringsgebiedVanTakeAway((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0), bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente())) ;
               }
            gerechtenTM.removeRow(gerechtenTable.getSelectedRow());
            prijsEuroLabel.setText("€" + f.format(prijsberekenen()));
            leveringskostenEuroLabel.setText("€" + f.format(Leveringskostenberekenen()));
            kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
            totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
           }
           
            
        }
        if (evt.getSource() == plusBtn)
        {
            if (gerechtenTable.getSelectedRow() != -1)
           {
                int hoev = (int)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 2) ;
                hoev++ ;
                gerechtenTM.setValueAt(hoev, gerechtenTable.getSelectedRow(), 2);
                
                if (bestelling.getMenuGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else if (bestelling.getMenuBijGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuBijGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else if (bestelling.getMenuSnacks().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuSnacks().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else if (bestelling.getMenuDranken().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuDranken().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else 
                {
                    System.out.println("error bij veranderen hoeveelheid");
                }
               prijsEuroLabel.setText("€" + f.format(prijsberekenen())); 
               leveringskostenEuroLabel.setText("€" + f.format(Leveringskostenberekenen()));
               kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
               totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
           }
        }
        if (evt.getSource() == minBtn)
        {
            if (gerechtenTable.getSelectedRow() != -1)
           {
                int hoev = (int)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 2) ;
                if (hoev > 0)
                {
                hoev-- ;
                gerechtenTM.setValueAt(hoev, gerechtenTable.getSelectedRow(), 2);
                
                if (bestelling.getMenuGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else if (bestelling.getMenuBijGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuBijGerecht().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else if (bestelling.getMenuSnacks().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuSnacks().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else if (bestelling.getMenuDranken().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).containsKey((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1)))
                {
                   bestelling.getMenuDranken().get((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 0)).put((String)gerechtenTM.getValueAt(gerechtenTable.getSelectedRow(), 1), hoev) ;
                }
                else 
                {
                    System.out.println("error bij veranderen hoeveelheid");
                }
               prijsEuroLabel.setText("€" + f.format(prijsberekenen())); 
               leveringskostenEuroLabel.setText("€" + f.format(Leveringskostenberekenen()));
               kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(), kortingVanafBepaaldbedrag())));
               totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                }
           }
        }
        if (evt.getSource() == betaalBtn)
        {
            if (prijsberekenen() == 0.0)
            {
                JOptionPane.showMessageDialog(null, "Uw winkelmandje is leeg!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                super.dispose();
                GUI_BetalingsOverzicht guiWindow = new GUI_BetalingsOverzicht(klant, bestelling, totaalbedragberekenen());
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false);
            }
        }
        if (evt.getSource() == kortingBtn)
        {
            String korting = JOptionPane.showInputDialog(null, "Geef uw kortingscode:", "Kortingscode toevoegen", JOptionPane.PLAIN_MESSAGE) ;
            ArrayList<String> bestaandeCodes = db.getLijstCodeCombinaties() ;
            ArrayList<String> behoortbijKlant = db.getKortingcodeBijKlantLogin(klant) ;
            if (!(bestaandeCodes.contains(korting)))
            {
                JOptionPane.showMessageDialog(null, "Geen geldige kortingscode!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (db.getStatusKortingscode(korting).equalsIgnoreCase("passief"))
            {
                JOptionPane.showMessageDialog(null, "Kortingscode is niet meer geldig!", "Error", JOptionPane.ERROR_MESSAGE); 
            }
            else if (db.getGroepBijKortingscode(korting).equalsIgnoreCase("registratieKC"))
            {
                if (bestelling.getRegistratieKorting() == 2.0)
                {
                    JOptionPane.showMessageDialog(null, "Kortingscode is al gebruikt!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else if (!(behoortbijKlant.contains(korting)))
                {
                   JOptionPane.showMessageDialog(null, "Geen geldige kortingscode!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else
                {
                    bestelling.setRegistratieKorting(2.0);
                    bestelling.setRegistatieKortingsCode(korting);
                    bestelling.getNaarpassiefKortingen().add(korting) ;
                    kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
                    totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                    JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (db.getGroepBijKortingscode(korting).equalsIgnoreCase("uniekeactieKC"))
            {
                // eenmalig
                if (db.getPeriodeBijUniekeActieKortingscode(korting).equalsIgnoreCase("eenmalig"))
                {
                    int vestigid = db.getVestigingBijUniekeActieKortingscode(korting) ;
                    if (vestigid != 0)
                    {
                        // korting door vestiging
                        if(!(bestelling.getVestigingen().contains(vestigid)))
                        {
                            JOptionPane.showMessageDialog(null, "U hebt niets besteld bij de vestiging die deze kortingscode uitgeeft!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else if (db.getCodeCombinatiesBijKlantLogin(klant).contains(korting))
                        {
                            JOptionPane.showMessageDialog(null, "U hebt de kortingscode al gebruikt!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else
                        {
                            if (bestelling.getUniekeActieKorting() != 0.0)
                            {
                                JOptionPane.showMessageDialog(null, "U hebt reeds een unieke actieKorting ingevoerd!", "Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else
                            {
                                // geven van de korting
                                bestelling.setUniekeActieKorting(db.getGegevenKortingBijKortingsCode(korting));
                                bestelling.setUniekeActieKortingsCode(korting);
                                bestelling.setUniekeActieKortingTakeAway(db.getTakeAwayVanVestiging(vestigid));
                                kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
                                totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                                JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    else
                    {
                        // korting door takeaway
                        String kortingBijTakeAway = db.getNaamTakeAwayBijUniekeActieKC(korting);
                        ArrayList<String> vestigingsids = db.getVestigingsIDSTakeAway(kortingBijTakeAway) ;
                        boolean goed = false ;
                        for (String id : vestigingsids)
                        {
                            if (bestelling.getVestigingen().contains(Integer.parseInt(id))) 
                            {
                                goed = true ;
                                break ;
                            }
                        }
                        if (!goed)
                        {
                            JOptionPane.showMessageDialog(null, "U hebt niets besteld bij de TakeAway die deze kortingscode uitgeeft!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else if (db.getCodeCombinatiesBijKlantLogin(klant).contains(korting))
                        {
                            JOptionPane.showMessageDialog(null, "U hebt de kortingscode al gebruikt!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else
                        {
                            if (bestelling.getUniekeActieKorting() != 0.0)
                            {
                                JOptionPane.showMessageDialog(null, "U hebt reeds een unieke actieKorting ingevoerd!", "Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else
                            {
                                // geven van de korting
                                bestelling.setUniekeActieKorting(db.getGegevenKortingBijKortingsCode(korting));
                                bestelling.setUniekeActieKortingsCode(korting);
                                bestelling.setUniekeActieKortingTakeAway(db.getNaamTakeAwayBijUniekeActieKC(korting));
                                kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
                                totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                                JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        
                    }
                }
                else
                {
                    // periode
                    int vestigid = db.getVestigingBijUniekeActieKortingscode(korting) ;
                    Date da = new Date() ;
                    if (vestigid != 0)
                    {
                        // korting door vestiging
                        if(!(bestelling.getVestigingen().contains(vestigid)))
                        {
                            JOptionPane.showMessageDialog(null, "U hebt niets besteld bij de vestiging die deze kortingscode uitgeeft!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else if (db.getBeginDatumUniekeAK(korting).after(da))
                        {
                            JOptionPane.showMessageDialog(null, "De korting is nog niet geldig!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else if (db.getEindeDatumUniekeAK(korting).before(da))
                        {
                            JOptionPane.showMessageDialog(null, "De korting is al verlopen!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else
                        {
                            if (bestelling.getUniekeActieKorting() != 0.0)
                            {
                                JOptionPane.showMessageDialog(null, "U hebt reeds een unieke actieKorting ingevoerd!", "Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else
                            {
                                // geven van de korting
                                bestelling.setUniekeActieKorting(db.getGegevenKortingBijKortingsCode(korting));
                                bestelling.setUniekeActieKortingsCode(korting);
                                bestelling.setUniekeActieKortingTakeAway(db.getTakeAwayVanVestiging(vestigid));
                                kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
                                totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                                JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    else
                    {
                        // korting door takeaway
                        String kortingBijTakeAway = db.getNaamTakeAwayBijUniekeActieKC(korting);
                        ArrayList<String> vestigingsids = db.getVestigingsIDSTakeAway(kortingBijTakeAway) ;
                        boolean goed = false ;
                        for (String id : vestigingsids)
                        {
                            if (bestelling.getVestigingen().contains(Integer.parseInt(id))) 
                            {
                                goed = true ;
                                break ;
                            }
                        }
                        if (!goed)
                        {
                            JOptionPane.showMessageDialog(null, "U hebt niets besteld bij de TakeAway die deze kortingscode uitgeeft!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else if (db.getBeginDatumUniekeAK(korting).after(da))
                        {
                            JOptionPane.showMessageDialog(null, "De korting is nog niet geldig!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else if (db.getEindeDatumUniekeAK(korting).before(da))
                        {
                            JOptionPane.showMessageDialog(null, "De korting is al verlopen!", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                        else
                        {
                            if (bestelling.getUniekeActieKorting() != 0.0)
                            {
                                JOptionPane.showMessageDialog(null, "U hebt reeds een unieke actieKorting ingevoerd!", "Error", JOptionPane.ERROR_MESSAGE); 
                            }
                            else
                            {
                                // geven van de korting
                                bestelling.setUniekeActieKorting(db.getGegevenKortingBijKortingsCode(korting));
                                bestelling.setUniekeActieKortingsCode(korting);
                                bestelling.setUniekeActieKortingTakeAway(db.getNaamTakeAwayBijUniekeActieKC(korting));
                                kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
                                totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                                JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        
                    }
                }
                
                
            }
            else if (db.getGroepBijKortingscode(korting).equalsIgnoreCase("jfbossKC"))
            {
               if (bestelling.getJfBossKorting() != 0.00)
                {
                    JOptionPane.showMessageDialog(null, "Kortingscode is al gebruikt!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else if (!(behoortbijKlant.contains(korting)))
                {
                   JOptionPane.showMessageDialog(null, "Geen geldige kortingscode!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else
                {
                    bestelling.setJfBossKorting(0.4);
                    bestelling.setJfBossKortingscode(korting);
                    bestelling.getNaarpassiefKortingen().add(korting) ;
                    kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(), kortingVanafBepaaldbedrag())));
                    totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                    JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                } 
            }
            else if (db.getGroepBijKortingscode(korting).equalsIgnoreCase("takeAwayBossKC"))
            {
               if (bestelling.getTakeAwayBossKorting()!= 0.00)
                {
                    JOptionPane.showMessageDialog(null, "Kortingscode is al gebruikt!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else if (!(behoortbijKlant.contains(korting)))
                {
                   JOptionPane.showMessageDialog(null, "Geen geldige kortingscode!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else
                {
                    bestelling.setTakeAwayBossKorting(0.2);
                    bestelling.setTakeAwayBossKortingscode(korting);
                    bestelling.getNaarpassiefKortingen().add(korting) ;
                    kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(),kortingVanafBepaaldbedrag())));
                    totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                    JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                }  
            }
            else if (db.getGroepBijKortingscode(korting).equalsIgnoreCase("reviewKC"))
            {
                if (bestelling.getReviewKorting() != 0.00)
                {
                    JOptionPane.showMessageDialog(null, "Kortingscode is al gebruikt!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else if (!(behoortbijKlant.contains(korting)))
                {
                   JOptionPane.showMessageDialog(null, "Geen geldige kortingscode!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else
                {
                    bestelling.setReviewKorting(0.1);
                    bestelling.setRegistatieKortingsCode(korting);
                    bestelling.getNaarpassiefKortingen().add(korting) ;
                    kortingEuroLabel.setText("€" + f.format(bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(),prijsberekenen(), kortingVanafBepaaldbedrag())));
                    totaleprijsEuroLabel.setText("€" + f.format(totaalbedragberekenen()));
                    JOptionPane.showMessageDialog(null, "Kortingscode is correct!", "Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                }  
            }
            
            
        }
    }
    private Double prijsberekenen()
    {
        Double bedrag = 0.00 ;
        for (int i = 0 ; i< gerechtenTM.getRowCount() ; i++)
        {
            String metEuroteken = (String)gerechtenTM.getValueAt(i, 3);
            String zonderEuroteken = metEuroteken.substring(1) ;
            String p = zonderEuroteken.replace(',', '.') ;
            int h = (int)gerechtenTM.getValueAt(i, 2) ;
            bedrag += Double.parseDouble(p)*h ;
        } 
        return bedrag ;
    }
    private Double Leveringskostenberekenen()
    {
       Double bedrag = 0.00 ;
       ArrayList<String> tas = new ArrayList() ; 
       for (int i=0 ; i< gerechtenTM.getRowCount() ; i++)
       {
           if ((int)gerechtenTM.getValueAt(i, 2) != 0)
           {
               if (!(tas.contains((String)gerechtenTM.getValueAt(i, 0))))
               {
                   bedrag += db.getLeveringsKostenBijVestigingsID(db.getVestigingsIDInLeveringsgebiedVanTakeAway((String)gerechtenTM.getValueAt(i, 0), bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente())) ;
                   tas.add((String)gerechtenTM.getValueAt(i, 0)) ;
                       
               }
           }
       }
       return bedrag ;
    }
    private Double bedragWaaropUniekeActieGeldtBerekenen()
    {
        double bedrag = 0.00 ;
        if (bestelling.getUniekeActieKortingTakeAway() != null)
        {
            if(gerechtenTM.getRowCount() !=0)
            {
               for (int i =0 ; i< gerechtenTM.getRowCount() ; i++)
               {
                   if (gerechtenTM.getValueAt(i, 0).equals(bestelling.getUniekeActieKortingTakeAway()))
                   {
                       String metEuroteken = (String)gerechtenTM.getValueAt(i, 3);
                       String zonderEuroteken = metEuroteken.substring(1) ;
                       String p = zonderEuroteken.replace(',', '.') ;
                       int h = (int)gerechtenTM.getValueAt(i, 2) ;
                       bedrag += Double.parseDouble(p)*h ;
                   }
               }
            }
        }
        return bedrag ;
    }
    private Double besteldPerTakeAway(String ta)
    {
        double bedrag = 0.00 ;
        if(gerechtenTM.getRowCount() !=0)
        {
               for (int i =0 ; i< gerechtenTM.getRowCount() ; i++)
               {
                   if (gerechtenTM.getValueAt(i, 0).equals(ta))
                   {
                       String metEuroteken = (String)gerechtenTM.getValueAt(i, 3);
                       String zonderEuroteken = metEuroteken.substring(1) ;
                       String p = zonderEuroteken.replace(',', '.') ;
                       int h = (int)gerechtenTM.getValueAt(i, 2) ;
                       bedrag += Double.parseDouble(p)*h ;
                   }
               }
        }
        return bedrag ;
    }
    private Double kortingVanafBepaaldbedrag()
    {
        Double kortingbedrag = 0.00 ;
        if (!(bestelling.getVestigingen().isEmpty()))
        {
            for (Integer id : bestelling.getVestigingen())
            {
                String takeaway = db.getTakeAwayVanVestiging(id) ;
                int bedragvanafkorting = db.getKortingBovenBedragTakeAway(takeaway) ;
                if (besteldPerTakeAway(takeaway) >= bedragvanafkorting)
                {
                    double kort = db.getGegevenKortingVanTakeAway(takeaway) ;
                    if (kort > 1)
                    {
                        kortingbedrag += kort ;
                    }
                    else 
                    {
                        kortingbedrag += besteldPerTakeAway(takeaway)*kort ;
                    }
                }
            }
        }
        return kortingbedrag ;
    }
    private Double totaalbedragberekenen()
    {
        double korting = bestelling.getTotaalKortingen(bedragWaaropUniekeActieGeldtBerekenen(), prijsberekenen(), kortingVanafBepaaldbedrag()) ;
        double prijsb = prijsberekenen() ;
        double leveringsb = Leveringskostenberekenen() ;
        double pEnL = prijsb + leveringsb ;
        
        if (pEnL / 2.0 < korting)
        {
            korting = pEnL / 2.0 ;
        }
        double ttl = prijsb + leveringsb - korting;
        if (ttl >= 0)
        {
            return ttl ;
        }
        else 
        {
            return 0.0 ;
        }
    }

    public Double getVorigeTotaalBedrag() {
        return vorigeTotaalBedrag;
    }

    public void setVorigeTotaalBedrag(Double vorigeTotaalBedrag) {
        this.vorigeTotaalBedrag = vorigeTotaalBedrag;
    }
    
    

    }

    

