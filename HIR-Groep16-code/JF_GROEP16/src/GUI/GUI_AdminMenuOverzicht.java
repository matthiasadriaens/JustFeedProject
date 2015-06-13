package GUI;

import DATABASE_EN_ENTITEITEN.* ;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class GUI_AdminMenuOverzicht extends JFrame   {

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
    private DefaultTableModel gerechtenTM ;
    private DefaultTableModel bijgerechtenTM ;
    private DefaultTableModel snacksTM ;
    private DefaultTableModel drankenTM ;
    private JTable gerechtenTable;
    private JTable bijgerechtenTable;
    private JTable snacksTable;
    private JTable drankenTable;
    private JScrollPane gerechtenSP;
    private JScrollPane bijgerechtenSP;
    private JScrollPane snackSP;
    private JScrollPane drankenSP;

    private Menu menu;
    private int menuID;

    private HashMap<String, Double> gerechtenHM;
    private HashMap<String, Double> bijgerechtenHM;
    private HashMap<String, Double> snacksHM;
    private HashMap<String, Double> drankenHM;

    private DecimalFormat f = new DecimalFormat("##.00");

    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize");

    private String takeAway;
    private String klant;
    private int select;

    private Double bedrag;
    private Double totaalbedrag;

    private int vestigingsID;
    
    DB db = new DB();

    public GUI_AdminMenuOverzicht(String naamTakeAway) {
        
        
        super("Just-Feed");
        setSize(800,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        takeAway = naamTakeAway;
        menuID = db.getMenuIDTakeAway(takeAway);
        bedrag = 0.00;
        vestigingsID = Integer.parseInt(db.getVestigingsIDSTakeAway(takeAway).get(0));

        
        gerechtenHM = db.getGerechtenEnPrijzenTakeAway(takeAway);
        bijgerechtenHM = db.getBijgerechtenEnPrijzenTakeAway(takeAway);
        snacksHM = db.getSnacksEnPrijzenTakeAway(takeAway);
        drankenHM = db.getDrankenEnPrijzenTakeAway(takeAway);

        gerechtenTM = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };

        bijgerechtenTM = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
        }
        };

        snacksTM = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };

        drankenTM = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
        };

        
        Container frame = getContentPane();

        //maak outer
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        frame.add(outer);

       
        
        
        // gerechten bijgerechten snacks drank
        
        //initialisatie van de JTable
        gerechtenTable = new JTable(gerechtenTM);
        gerechtenTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
       
        bijgerechtenTable = new JTable(bijgerechtenTM);
        bijgerechtenTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        snacksTable = new JTable(snacksTM);
        snacksTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       
        drankenTable = new JTable(drankenTM);
        drankenTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        

        //layout van de tables en de tableModels
        gerechtenTM.addColumn("Gerecht");
        gerechtenTM.addColumn("Prijs");
        gerechtenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        gerechtenTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        gerechtenTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        bijgerechtenTM.addColumn("Bijgerecht");
        bijgerechtenTM.addColumn("Prijs");
        bijgerechtenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        bijgerechtenTable.getColumnModel().getColumn(0).setPreferredWidth(112);
        bijgerechtenTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        snacksTM.addColumn("Snack");
        snacksTM.addColumn("Prijs");
        snacksTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        snacksTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        snacksTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        drankenTM.addColumn("Drank");
        drankenTM.addColumn("Prijs");
        drankenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        drankenTable.getColumnModel().getColumn(0).setPreferredWidth(108);
        drankenTable.getColumnModel().getColumn(1).setPreferredWidth(42);
        bijgerechtenTable.setRowSelectionAllowed(false);
        snacksTable.setRowSelectionAllowed(false);       
        drankenTable.setRowSelectionAllowed(false);
        
        //initialisatie van de scrollpanes 
        gerechtenSP = new JScrollPane(gerechtenTable);
        gerechtenSP.setPreferredSize(new Dimension(168, 300));
        gerechtenSP.getViewport().setBackground(Color.WHITE);
        gerechtenSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bijgerechtenSP = new JScrollPane(bijgerechtenTable);
        bijgerechtenSP.setPreferredSize(new Dimension(172, 300));
        bijgerechtenSP.getViewport().setBackground(Color.WHITE);
        bijgerechtenSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        snackSP = new JScrollPane(snacksTable);
        snackSP.setPreferredSize(new Dimension(168, 300));
        snackSP.getViewport().setBackground(Color.WHITE);
        snackSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        drankenSP = new JScrollPane(drankenTable);
        drankenSP.setPreferredSize(new Dimension(168, 300));
        drankenSP.getViewport().setBackground(Color.WHITE);
        drankenSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // opvullen menu
        for (String g : gerechtenHM.keySet()) {
            Object[] s = new Object[]{g, "€" + f.format(gerechtenHM.get(g)), 0};
            gerechtenTM.addRow(s);
        }
        for (String p : bijgerechtenHM.keySet()) {
            Object[] s = new Object[]{p, "€" + f.format(bijgerechtenHM.get(p)), 0};
            bijgerechtenTM.addRow(s);
        }
        for (String d : drankenHM.keySet()) {
            Object[] s = new Object[]{d, "€" + f.format(drankenHM.get(d)), 0};
            drankenTM.addRow(s);
        }
        for (String snacks : snacksHM.keySet()) {
            Object[] s = new Object[]{snacks, "€" + f.format(snacksHM.get(snacks)), 0};
            snacksTM.addRow(s);
        }
        
        
        //NAAM TAke away
        JLabel naamtakeaway = new JLabel();
        naamtakeaway.setText(takeAway);
        naamtakeaway.setFont(new Font("Lucida Bright", Font.BOLD, 25));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-40, 0, 0, 0);
        outer.add(naamtakeaway, c);
        
        JLabel menulabel = new JLabel();
        menulabel.setText(" MENU-OVERZICHT:   ");
        menulabel.setFont(new Font("Lucida Bright", Font.BOLD, 25));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-40, -450, 0, 0);
        outer.add(menulabel, c);
        
        //maak panel-maaltijd met knoppen
        JPanel gerechtPanel = new JPanel();
        gerechtPanel.setLayout(new GridLayout(1,4));
        gerechtPanel.setBackground(Color.white);
        
        JPanel gerechtPanel1 = new JPanel();
        gerechtPanel.setLayout(new GridBagLayout());
        gerechtPanel.setBackground(Color.white);
        
        JPanel gerechtPanel2 = new JPanel();
        gerechtPanel.setLayout(new GridBagLayout());
        gerechtPanel.setBackground(Color.white);
        
        JPanel gerechtPanel3 = new JPanel();
        gerechtPanel.setLayout(new GridBagLayout());
        gerechtPanel.setBackground(Color.white);
        
        JPanel gerechtPanel4 = new JPanel();
        gerechtPanel.setLayout(new GridBagLayout());
        gerechtPanel.setBackground(Color.white);
        
        gerechtPanel.add(gerechtPanel1);
        gerechtPanel.add(gerechtPanel2);
        gerechtPanel.add(gerechtPanel3);
        gerechtPanel.add(gerechtPanel4);
        
        
        
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 20);
        // gerechtenSP.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        gerechtPanel1.add(gerechtenSP, c);
                
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 20);
        gerechtPanel2.add(bijgerechtenSP, c);    
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 20);
        gerechtPanel3.add(snackSP, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 20);
        gerechtPanel4.add(drankenSP, c);
        
        
        //add deze panel in box 
        Box gerechtenbox = Box.createHorizontalBox();
        gerechtenbox.setBorder(BorderFactory.createTitledBorder("Menu"));
        gerechtenbox.add(gerechtPanel);
       
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-80, 30, 0, 30);
        c.fill = GridBagConstraints.HORIZONTAL ;
        outer.add(gerechtenbox, c);
        
        // button
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-130, 500, 0, 0);
        c.fill = GridBagConstraints.NONE;
    }
}
