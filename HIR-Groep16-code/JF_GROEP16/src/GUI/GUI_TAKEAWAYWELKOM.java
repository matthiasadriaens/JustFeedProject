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
import java.awt.Image;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class GUI_TAKEAWAYWELKOM extends JFrame implements ActionListener, ListSelectionListener {

    private JPanel outer;
    private JPanel opperPanel, linksboven, rechtsboven, linksonder, rechtsonder, rechtsbovenlinks, rechtsbovenrechts;

    private JPanel boven;
    private JPanel onderPanel;
    private JPanel onderstePanel;

    private JLabel takeawaylabel;
    private JLabel emailLabel;
    private JLabel kortingvanafLabel;
    private JLabel kortingLabel;
    private JLabel wachtwoordLabel;

    private JTextField dbtakeawaylabel;
    private JTextField dbemailLabel;
    private JTextField dbkortingvanafLabel;
    private JTextField dbkortingLabel;
    private JPasswordField dbwachtwoordLabel;

    private String emailtakeaway;
    private int kortingvanaftakeaway;
    private double kortingtakeaway;
    private String wachtwoordtakeaway;

    private JLabel mijnTALabel;
    private JLabel mijnTANaamLabel;

    private JButton naarHoofdmenu;
    private JButton naarMenu;
    private JButton registreer;
    private JList categorieL, vestigingenL, gebiedenL;
    private DefaultListModel categorieLM, vestigingenLM, leveringsgebiedenLM;
    private String ingelogdeTakeAway;

    private JButton statwijz;
    private JButton toevoegen;
    private JButton verwijderen;
    private JButton toevoegen2;
    private JButton verwijderen2;
    private JButton toevoegen3;
    private JButton verwijderen3;
    private JButton toevoegen4;
    private JButton verwijderen4;
    private JButton wijzigen1;
    private JButton toevoegenPane;
    
    private JRadioButton veRB ;
    private JRadioButton taRB ;
    private JRadioButton eenmaligRB ;
    private JRadioButton periodeRB ;
    private JComboBox vestigingenVanTakeaway ;
    private JDatePickerImpl datePickerBegin ;
    private JDatePickerImpl datePickerEinde ;

    private JTextField postcodeT;
    private JTextField postcodeT1;
    private JComboBox gemeenteT;
    private JComboBox gemeenteT1;
    private JTextField postcodeT2;
    private JComboBox gemeenteT2;
    
    private DecimalFormat f = new DecimalFormat("##.00");
    
    private JTable table ;
    private DefaultTableModel tableTM ;

    private ArrayList<JButton> buttons;
    private ArrayList<String> vestigingenIDs;
    private ArrayList<String> vestigingenAdressen;
    private ArrayList<String> categorieen;
    private ArrayList<String> categorieenlijst;

    private DB db = new DB();
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize");

    public GUI_TAKEAWAYWELKOM(String naamTakeAway) {
        super("Just Feed - Takeaway hoofdmenu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categorieLM = new DefaultListModel();
        vestigingenLM = new DefaultListModel();
        leveringsgebiedenLM = new DefaultListModel();
        tableTM = new DefaultTableModel() ;

        ingelogdeTakeAway = naamTakeAway;

        emailtakeaway = db.getEmailVanTakeAway(ingelogdeTakeAway);
        kortingvanaftakeaway = db.getKortingBovenBedragTakeAway(ingelogdeTakeAway);
        kortingtakeaway = db.getGegevenKortingVanTakeAway(ingelogdeTakeAway);
        wachtwoordtakeaway = db.getPaswoordVanTakeAway(ingelogdeTakeAway);

        takeawaylabel = new JLabel();
        takeawaylabel.setText("Naam:");
        kortingvanafLabel = new JLabel();
        kortingvanafLabel.setText("Korting vanaf:");
        kortingLabel = new JLabel();
        kortingLabel.setText("Korting:");
        wachtwoordLabel = new JLabel();
        wachtwoordLabel.setText("Mijn wachtwoord:");
        emailLabel = new JLabel();
        emailLabel.setText("E-mail:");

        Container contentpane = getContentPane();

        outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.white);
        contentpane.add(outer);

        opperPanel = new JPanel();
        opperPanel.setLayout(new GridBagLayout());
        opperPanel.setBackground(Color.white);
        opperPanel.setPreferredSize(new Dimension(750, 50));

        boven = new JPanel();
        boven.setLayout(new GridLayout(1, 2));
        boven.setBackground(Color.white);
        boven.setPreferredSize(new Dimension(750, 200));

        onderPanel = new JPanel();
        onderPanel.setLayout(new GridLayout(1, 2));
        onderPanel.setBackground(Color.white);
        onderPanel.setPreferredSize(new Dimension(750, 200));

        onderstePanel = new JPanel();
        onderstePanel.setLayout(new GridBagLayout());
        onderstePanel.setBackground(Color.white);
        onderstePanel.setPreferredSize(new Dimension(750, 50));

        GridBagConstraints frameconstraints = new GridBagConstraints();

        frameconstraints.weightx = 1;
        frameconstraints.weighty = 1;
        frameconstraints.gridx = 0;
        frameconstraints.gridy = 0;

        outer.add(opperPanel, frameconstraints);
        frameconstraints.gridy = 1;
        outer.add(boven, frameconstraints);
        frameconstraints.gridy = 2;
        outer.add(onderPanel, frameconstraints);
        frameconstraints.gridy = 3;
        outer.add(onderstePanel, frameconstraints);

        //OpperPanel vullen
        GridBagConstraints opperpanelconstraints = new GridBagConstraints();

        mijnTALabel = new JLabel("Overzicht: " + ingelogdeTakeAway);
        mijnTALabel.setFont(new Font("Serif", Font.PLAIN, 25));

        mijnTANaamLabel = new JLabel("McHilal's");
        mijnTANaamLabel.setBackground(Color.black);
        mijnTANaamLabel.setForeground(Color.BLUE);
        Font font = mijnTANaamLabel.getFont();
        Map onderlijnen = font.getAttributes();
        onderlijnen.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        mijnTANaamLabel.setFont(font.deriveFont(onderlijnen));

        opperpanelconstraints.weightx = 1;
        opperpanelconstraints.weighty = 1;
        opperpanelconstraints.gridx = 0;
        opperpanelconstraints.gridy = 0;
        opperpanelconstraints.insets = new Insets(0, -480, 0, 0);
        opperPanel.add(mijnTALabel, opperpanelconstraints);

        opperpanelconstraints.weightx = 1;
        opperpanelconstraints.weighty = 1;
        opperpanelconstraints.gridx = 4;
        opperpanelconstraints.gridy = 0;
        //opperPanel.add(mijnTANaamLabel, opperpanelconstraints);

        rechtsboven = new JPanel();
        rechtsboven.setLayout(new GridLayout(1, 2));
        rechtsboven.setBackground(Color.white);
        rechtsboven.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));

        rechtsbovenlinks = new JPanel();
        rechtsbovenlinks.setLayout(new GridBagLayout());
        rechtsbovenlinks.setBackground(Color.white);
        rechtsbovenlinks.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));

        rechtsbovenrechts = new JPanel();
        rechtsbovenrechts.setLayout(new GridBagLayout());
        rechtsbovenrechts.setBackground(Color.white);
        rechtsbovenrechts.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        rechtsboven.add(rechtsbovenlinks);
        rechtsboven.add(rechtsbovenrechts);

        //ALLES LINKSBOVEN
        linksboven = new JPanel();
        linksboven.setLayout(new GridBagLayout());
        linksboven.setBorder(BorderFactory.createTitledBorder("Mijn gegevens"));
        linksboven.setBackground(Color.white);
        //linksboven.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 20, Color.white));
        boven.add(linksboven);
        boven.add(rechtsboven);

        linksonder = new JPanel();
        linksonder.setLayout(new GridBagLayout());
        linksonder.setBackground(Color.WHITE);
        linksonder.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));

        //ALLES LINKSBOVEN
        rechtsonder = new JPanel();
        rechtsonder.setLayout(new GridBagLayout());
        rechtsonder.setBackground(Color.white);
        onderPanel.add(linksonder);
        onderPanel.add(rechtsonder);

        buttons = new ArrayList<JButton>();

        ArrayList<Image> images = new ArrayList();

        Border emptyBorder;

        // create each by jsut assigning a name based on its index
        for (int i = 0; i < 10; i++) {
            buttons.add(new JButton());
            buttons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons.get(i).setFocusable(false);
            buttons.get(i).setBackground(Color.white);
            emptyBorder = BorderFactory.createEmptyBorder();
            buttons.get(i).setBorder(emptyBorder);
            buttons.get(i).addActionListener(this);
            try {

                images.add(i, ImageIO.read(getClass().getResource("modify1.png")));
                buttons.get(i).setIcon(new ImageIcon(images.get(i)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        dbtakeawaylabel = new JTextField(ingelogdeTakeAway);
        dbemailLabel = new JTextField(emailtakeaway);
        dbkortingvanafLabel = new JTextField(kortingvanaftakeaway + "");
        dbkortingLabel = new JTextField(kortingtakeaway + "");
        dbwachtwoordLabel = new JPasswordField(wachtwoordtakeaway);

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        takeawaylabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(takeawaylabel, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        linksboven.add(dbtakeawaylabel, gc);

        gc.gridy = 1;
        gc.gridx = 0;
        emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(emailLabel, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        linksboven.add(dbemailLabel, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        buttons.get(3).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(buttons.get(0), gc);  // 3 omdat er in de 0 om  mysterieuze reden altijd een spatie zit

        gc.gridx = 0;
        gc.gridy = 2;
        kortingvanafLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(kortingvanafLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        kortingvanafLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(dbkortingvanafLabel, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        linksboven.add(buttons.get(1), gc);

        gc.gridx = 0;
        gc.gridy = 3;
        kortingLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(kortingLabel, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        kortingLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(dbkortingLabel, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        kortingLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(buttons.get(2), gc);

        gc.gridx = 0;
        gc.gridy = 4;
        wachtwoordLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(wachtwoordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        wachtwoordLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        linksboven.add(dbwachtwoordLabel, gc);

        gc.gridx = 2;
        linksboven.add(buttons.get(3), gc);

        registreer = new JButton("Opslaan");

        Border emptyB = BorderFactory.createEmptyBorder();
        registreer.setBorder(emptyB);

        registreer.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        registreer.setBackground(Color.DARK_GRAY);
        registreer.setForeground(Color.white);
        registreer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registreer.setFocusable(false);
        registreer.setEnabled(false);
        registreer.addActionListener(this);

        gc.anchor = GridBagConstraints.PAGE_END;
        gc.gridy = 7;
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 35, 10, 35);
        linksboven.add(registreer, gc);

        dbtakeawaylabel.setBorder(null);
        dbemailLabel.setBorder(null);
        dbkortingvanafLabel.setBorder(null);
        dbkortingLabel.setBorder(null);
        dbwachtwoordLabel.setBorder(null);

        dbtakeawaylabel.setEditable(false);
        dbemailLabel.setEditable(false);
        dbkortingvanafLabel.setEditable(false);
        dbkortingLabel.setEditable(false);
        dbwachtwoordLabel.setEditable(false);

        dbtakeawaylabel.setBackground(Color.WHITE);
        dbemailLabel.setBackground(Color.WHITE);
        dbkortingvanafLabel.setBackground(Color.WHITE);
        dbkortingLabel.setBackground(Color.WHITE);
        dbwachtwoordLabel.setBackground(Color.WHITE);

        // initialisatie tabels
        vestigingenIDs = db.getVestigingsIDSTakeAway(naamTakeAway);
        vestigingenAdressen = new ArrayList();
        for (String s : vestigingenIDs) {
            vestigingenAdressen.add(db.getAdresBijVestigingsID(Integer.parseInt(s)));
        }

        for (String adres : vestigingenAdressen) {
            vestigingenLM.addElement(adres);
        }
        vestigingenL = new JList(vestigingenLM);
        vestigingenL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vestigingenL.getSelectionModel().addListSelectionListener(this);
        vestigingenL.setLayoutOrientation(JList.VERTICAL);
        vestigingenL.setVisibleRowCount(10);
        vestigingenL.setBackground(Color.WHITE);

        gebiedenL = new JList(leveringsgebiedenLM);
        gebiedenL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gebiedenL.setLayoutOrientation(JList.VERTICAL);
        gebiedenL.setVisibleRowCount(10);
        gebiedenL.setBackground(Color.WHITE);

        JScrollPane scrollv = new JScrollPane(vestigingenL);
        scrollv.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollv.setPreferredSize(new Dimension(160, 170));
        scrollv.setBorder(BorderFactory.createTitledBorder("Mijn vestigingen"));
        scrollv.setBackground(Color.white);

        JScrollPane scrollv2 = new JScrollPane(gebiedenL);
        scrollv2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollv2.setPreferredSize(new Dimension(160, 170));
        scrollv2.setBorder(BorderFactory.createTitledBorder("Leveringsgebieden"));
        scrollv2.setBackground(Color.white);

        categorieen = db.getCategorieenTakeAway(naamTakeAway);
        categorieenlijst = new ArrayList();
        for (String cat : categorieen) {
            categorieLM.addElement(cat);
        }

        categorieL = new JList(categorieLM);
        categorieL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categorieL.setLayoutOrientation(JList.VERTICAL);
        categorieL.setVisibleRowCount(10);

        JScrollPane scroll = new JScrollPane(categorieL);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(320, 170));
        scroll.setBorder(BorderFactory.createTitledBorder("Categoriën"));
        scroll.setBackground(Color.white);

        toevoegen = new JButton("Toevoegen");
        toevoegen.setBackground(Color.DARK_GRAY);
        toevoegen.setFont(new Font("Lucida Bright", Font.BOLD, 8));
        toevoegen.setForeground(Color.WHITE); //lettertype
        toevoegen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toevoegen.setFocusable(false);
        toevoegen.addActionListener(this);

        verwijderen = new JButton("Verwijderen");
        verwijderen.setBackground(Color.DARK_GRAY);
        verwijderen.setFont(new Font("Lucida Bright", Font.BOLD, 8));
        verwijderen.setForeground(Color.WHITE); //lettertype
        verwijderen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verwijderen.setFocusable(false);
        verwijderen.addActionListener(this);

        toevoegen2 = new JButton("Toevoegen");
        toevoegen2.setBackground(Color.DARK_GRAY);
        toevoegen2.setFont(new Font("Lucida Bright", Font.BOLD, 8));
        toevoegen2.setForeground(Color.WHITE); //lettertype
        toevoegen2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toevoegen2.setFocusable(false);
        toevoegen2.addActionListener(this);

        verwijderen2 = new JButton("Verwijderen");
        verwijderen2.setBackground(Color.DARK_GRAY);
        verwijderen2.setFont(new Font("Lucida Bright", Font.BOLD, 8));
        verwijderen2.setForeground(Color.WHITE); //lettertype
        verwijderen2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verwijderen2.setFocusable(false);
        verwijderen2.addActionListener(this);

        wijzigen1 = new JButton("Wijzig");
        wijzigen1.setBackground(Color.DARK_GRAY);
        wijzigen1.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        wijzigen1.setForeground(Color.WHITE); //lettertype
        wijzigen1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        wijzigen1.setFocusable(false);

        GridBagConstraints rechtsonderc = new GridBagConstraints();

        rechtsonderc.weightx = 1;
        rechtsonderc.weighty = 1;
        rechtsonderc.gridx = 0;
        rechtsonderc.gridy = 0;
        rechtsonder.add(scroll);

        rechtsonderc.weightx = 1;
        rechtsonderc.weighty = 1;
        rechtsonderc.gridx = 0;
        rechtsonderc.gridy = 1;
        rechtsonderc.insets = new Insets(0, -130, 0, 0);
        rechtsonder.add(toevoegen, rechtsonderc);

        rechtsonderc.weightx = 1;
        rechtsonderc.weighty = 1;
        rechtsonderc.gridx = 0;
        rechtsonderc.gridy = 1;
        rechtsonderc.insets = new Insets(0, 0, 0, -130);
        rechtsonder.add(verwijderen, rechtsonderc);

        GridBagConstraints rechtsbovc = new GridBagConstraints();

        rechtsbovc.weightx = 1;
        rechtsbovc.weighty = 1;
        rechtsbovc.gridx = 0;
        rechtsbovc.gridy = 0;
        rechtsbovc.insets = new Insets(0, 25, 0, 0);
        rechtsbovenlinks.add(scrollv, rechtsbovc);

        rechtsbovc.weightx = 1;
        rechtsbovc.weighty = 1;
        rechtsbovc.gridx = 0;
        rechtsbovc.gridy = 1;
        rechtsbovc.insets = new Insets(0, -50, 0, 0);
        rechtsbovenlinks.add(toevoegen, rechtsbovc);

        rechtsbovc.weightx = 1;
        rechtsbovc.weighty = 1;
        rechtsbovc.gridx = 0;
        rechtsbovc.gridy = 1;
        rechtsbovc.insets = new Insets(0, 90, 0, 0);
        rechtsbovenlinks.add(verwijderen, rechtsbovc);

        GridBagConstraints rechtsbovrc = new GridBagConstraints();

        rechtsbovrc.weightx = 1;
        rechtsbovrc.weighty = 1;
        rechtsbovrc.gridx = 1;
        rechtsbovrc.gridy = 0;
        rechtsbovrc.insets = new Insets(0, 0, 0, 0);
        rechtsbovenrechts.add(scrollv2, rechtsbovrc);

        rechtsbovrc.weightx = 1;
        rechtsbovrc.weighty = 1;
        rechtsbovrc.gridx = 1;
        rechtsbovrc.gridy = 1;
        rechtsbovrc.insets = new Insets(0, -89, 0, 0);
        rechtsbovenrechts.add(toevoegen2, rechtsbovrc);

        rechtsbovrc.weightx = 1;
        rechtsbovrc.weighty = 1;
        rechtsbovrc.gridx = 1;
        rechtsbovrc.gridy = 1;
        rechtsbovrc.insets = new Insets(0, 75, 0, 0);
        rechtsbovenrechts.add(verwijderen2, rechtsbovrc);

        //Alles onderpanel
        naarHoofdmenu = new JButton();
        naarHoofdmenu.setBackground(Color.DARK_GRAY);
        naarHoofdmenu.setFont(new Font("Lucida Bright", Font.BOLD, 13));
        naarHoofdmenu.setForeground(Color.WHITE);
        naarHoofdmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        naarHoofdmenu.setFocusable(false);
        naarHoofdmenu.setText("Terug naar hoofdmenu");
        naarHoofdmenu.addActionListener(this);

        GridBagConstraints onderpanelConstraint = new GridBagConstraints();
        onderpanelConstraint.weightx = 1;
        onderpanelConstraint.weighty = 1;
        onderpanelConstraint.gridx = 0;
        onderpanelConstraint.gridy = 0;
        onderpanelConstraint.insets = new Insets(-30, 0, 0, 10); // 0 0 -415 10
        onderstePanel.add(naarHoofdmenu, onderpanelConstraint);

        naarMenu = new JButton();
        naarMenu.setBackground(Color.DARK_GRAY);
        naarMenu.setFont(new Font("Lucida Bright", Font.BOLD, 13));
        naarMenu.setForeground(Color.WHITE);
        naarMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        naarMenu.setFocusable(false);
        naarMenu.setText("     Menu aanpassen      ");
        naarMenu.addActionListener(this);

        onderpanelConstraint.weightx = 1;
        onderpanelConstraint.weighty = 1;
        onderpanelConstraint.gridx = 0;
        onderpanelConstraint.gridy = 0;
        onderpanelConstraint.insets = new Insets(26, 0, 0, 10); // 0 0 -415 10
        onderstePanel.add(naarMenu, onderpanelConstraint);

        table = new JTable(tableTM);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBackground(Color.white);
        
        tableTM.addColumn("Kortingscode");
        tableTM.addColumn("Korting");
        tableTM.addColumn("periode");
        tableTM.addColumn("Vestiging");
        tableTM.addColumn("Status");
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(77);
        table.getColumnModel().getColumn(1).setPreferredWidth(48);
        table.getColumnModel().getColumn(2).setPreferredWidth(110) ;
        table.getColumnModel().getColumn(3).setPreferredWidth(78) ;
        table.getColumnModel().getColumn(4).setPreferredWidth(45) ;
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        ArrayList<String> codes = db.getLijstUniekActieKortingsCodesBijTakeAwayEnZijnVestigingen(ingelogdeTakeAway);
        for (String code : codes)
        {
            if (db.getGegevenKortingBijKortingsCode(code)>=1)
            {
                String gegevenKorting = "€" + db.getGegevenKortingBijKortingsCode(code);
                tableTM.addRow(new Object[]{code,gegevenKorting,db.getGeldigheidsduurBijUniekeActieKortingscode(code),db.getAdresBijVestigingsID(db.getVestigingBijUniekeActieKortingscode(code)),db.getStatusBijUniekeActieKortingscode(code)});
            }
            if (db.getGegevenKortingBijKortingsCode(code)<1)
            {
                double korting = ((db.getGegevenKortingBijKortingsCode(code))*100);
                String gegevenKorting = korting + " %";
                tableTM.addRow(new Object[]{code,gegevenKorting,db.getGeldigheidsduurBijUniekeActieKortingscode(code),db.getAdresBijVestigingsID(db.getVestigingBijUniekeActieKortingscode(code)),db.getStatusBijUniekeActieKortingscode(code)});
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(365, 170));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Unieke actie kortingscode"));
        scrollPane.setBackground(Color.white);

        toevoegen4 = new JButton("Toevoegen");
        toevoegen4.setBackground(Color.DARK_GRAY);
        toevoegen4.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        toevoegen4.setForeground(Color.WHITE); //lettertype
        toevoegen4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toevoegen4.setFocusable(false);
        toevoegen4.addActionListener(this);

        verwijderen4 = new JButton("Verwijderen");
        verwijderen4.setBackground(Color.DARK_GRAY);
        verwijderen4.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        verwijderen4.setForeground(Color.WHITE); //lettertype
        verwijderen4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verwijderen4.setFocusable(false);
        verwijderen4.addActionListener(this);

        toevoegen3 = new JButton("Toevoegen");
        toevoegen3.setBackground(Color.DARK_GRAY);
        toevoegen3.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        toevoegen3.setForeground(Color.WHITE); //lettertype
        toevoegen3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toevoegen3.setFocusable(false);
        toevoegen3.addActionListener(this);

        statwijz = new JButton("Status wijzigen");
        statwijz.setBackground(Color.DARK_GRAY);
        statwijz.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        statwijz.setForeground(Color.WHITE); //lettertype
        statwijz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        statwijz.setFocusable(false);
        statwijz.addActionListener(this);

        GridBagConstraints linksonderc = new GridBagConstraints();

        linksonderc.weightx = 1;
        linksonderc.weighty = 1;
        linksonderc.gridx = 0;
        linksonderc.gridy = 0;
        //table.setForeground(Color.white);

        table.setFillsViewportHeight(true);
        linksonder.setBackground(Color.white);

        linksonder.add(scrollPane, linksonderc);

        linksonderc.weightx = 1;
        linksonderc.weighty = 1;
        linksonderc.gridx = 0;
        linksonderc.gridy = 1;
        linksonderc.insets = new Insets(0, -160, 0, 0);
        linksonder.add(toevoegen3, linksonderc);

        linksonderc.weightx = 1;
        linksonderc.weighty = 1;
        linksonderc.gridx = 0;
        linksonderc.gridy = 1;
        linksonderc.insets = new Insets(0, 0, 0, -160);
        linksonder.add(statwijz, linksonderc);

        GridBagConstraints rechtsonderco = new GridBagConstraints();
        rechtsonderco.weightx = 1;
        rechtsonderco.weighty = 1;
        rechtsonderco.gridx = 0;
        rechtsonderco.gridy = 1;
        rechtsonderco.insets = new Insets(0, -130, 0, 0);
        rechtsonder.add(toevoegen4, rechtsonderco);

        rechtsonderco.weightx = 1;
        rechtsonderco.weighty = 1;
        rechtsonderco.gridx = 0;
        rechtsonderco.gridy = 1;
        rechtsonderco.insets = new Insets(0, 0, 0, -130);
        rechtsonder.add(verwijderen4, rechtsonderco);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == statwijz)
        {
            if(!(table.getSelectedRow()== -1))
            {
                String status = db.getStatusBijUniekeActieKortingscode(tableTM.getValueAt(table.getSelectedRow(),0)+"");
                String code = tableTM.getValueAt(table.getSelectedRow(),0)+"";

                if(status.equals("actief"))
                {
                    db.updateStatusNaarPassiefKortingscode(code);
                    super.dispose();
                    GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdeTakeAway);
                    guiWindow.setVisible(true);
                    guiWindow.setLocationRelativeTo(null);
                    guiWindow.setResizable(false);
                }
                else if (status.equals("passief"))
                {
                    db.updateStatusNaarActiefKortingscode(code);
                    super.dispose();
                    GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdeTakeAway);
                    guiWindow.setVisible(true);
                    guiWindow.setLocationRelativeTo(null);
                    guiWindow.setResizable(false);
                }
            }
        }
        if (e.getSource() == naarHoofdmenu) {
            super.dispose();
            GUI_TakeAwayInlogScherm guiWindow = new GUI_TakeAwayInlogScherm(ingelogdeTakeAway);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (e.getSource() == buttons.get(0)) {

            dbemailLabel.setEditable(true);
            dbemailLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dbemailLabel.getCaret().setVisible(true);
            dbemailLabel.requestFocusInWindow();

            registreer.setEnabled(true);
        }
        if (e.getSource() == buttons.get(1)) {
            dbkortingvanafLabel.setEditable(true);
            dbkortingvanafLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dbkortingvanafLabel.getCaret().setVisible(true);
            dbkortingvanafLabel.requestFocusInWindow();

            registreer.setEnabled(true);
        }
        if (e.getSource() == buttons.get(2)) {
            dbkortingLabel.setEditable(true);
            dbkortingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dbkortingLabel.getCaret().setVisible(true);
            dbkortingLabel.requestFocusInWindow();

            registreer.setEnabled(true);
        }
        if (e.getSource() == buttons.get(3)) {
            dbwachtwoordLabel.setEditable(true);
            dbwachtwoordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dbwachtwoordLabel.getCaret().setVisible(true);
            dbwachtwoordLabel.requestFocusInWindow();

            registreer.setEnabled(true);
        }

        if (e.getSource() == registreer) {

            if (!(emailtakeaway.equalsIgnoreCase(dbemailLabel.getText()))) {
                if (dbemailLabel.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "E-mail moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!(dbemailLabel.getText().contains("@") && dbemailLabel.getText().contains("."))) {
                    JOptionPane.showMessageDialog(null, "E-mail is niet geldig!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (dbemailLabel.getText().length() > 50) {
                    JOptionPane.showMessageDialog(null, "E-mail kan niet meer dan 50 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!(db.controleerOfEmailTakeAwayBestaat(dbemailLabel.getText()))) {
                    JOptionPane.showMessageDialog(null, "E-mail bestaat al!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    db.updateEmailTakeAway(ingelogdeTakeAway, dbemailLabel.getText());
                    emailtakeaway = dbemailLabel.getText();
                }
            }

            if (!(kortingvanaftakeaway == Integer.parseInt(dbkortingvanafLabel.getText()))) {
                db.updateVanafBepaaldBedragkortingTakeAway(ingelogdeTakeAway, Integer.parseInt(dbkortingvanafLabel.getText()));
                kortingvanaftakeaway = Integer.parseInt(dbkortingvanafLabel.getText());
            }

            if (!(kortingtakeaway == Double.parseDouble(dbkortingLabel.getText()))) {

            }
            db.updateKortingTakeAway(ingelogdeTakeAway, Double.parseDouble(dbkortingLabel.getText()));
            kortingtakeaway = Double.parseDouble(dbkortingLabel.getText());

            if (!(wachtwoordtakeaway.equalsIgnoreCase(dbwachtwoordLabel.getText()))) {
                if (dbwachtwoordLabel.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Paswoord moet minstens 8 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (dbwachtwoordLabel.getText().length() > 20) {
                    JOptionPane.showMessageDialog(null, "Wachtwoord kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (dbwachtwoordLabel.getText().contains(ingelogdeTakeAway)) {
                    JOptionPane.showMessageDialog(null, "Paswoord mag de naam van de takeaway niet bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    db.updatePaswoordTakeAway(ingelogdeTakeAway, dbwachtwoordLabel.getText());
                    wachtwoordtakeaway = dbwachtwoordLabel.getText();
                }
            }

            dbtakeawaylabel.setBorder(null);
            dbemailLabel.setBorder(null);
            dbkortingvanafLabel.setBorder(null);
            dbkortingLabel.setBorder(null);
            dbwachtwoordLabel.setBorder(null);

            dbtakeawaylabel.setEditable(false);
            dbemailLabel.setEditable(false);
            dbkortingvanafLabel.setEditable(false);
            dbkortingLabel.setEditable(false);
            dbwachtwoordLabel.setEditable(false);

            dbtakeawaylabel.getCaret().setVisible(false);
            dbemailLabel.getCaret().setVisible(false);
            dbkortingvanafLabel.getCaret().setVisible(false);
            dbkortingLabel.getCaret().setVisible(false);
            dbwachtwoordLabel.getCaret().setVisible(false);

            registreer.setEnabled(false);
        }

        if (e.getSource() == toevoegen) {
            super.dispose();
            GUI_VestigingenToevoegen guiWindow = new GUI_VestigingenToevoegen(ingelogdeTakeAway);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (e.getSource() == verwijderen) {
            if(vestigingenL.isSelectionEmpty())
            {
                
                        
            }
            else
            {
                int index = vestigingenL.getSelectedIndex();
                vestigingenLM.removeElement(vestigingenL.getSelectedValue());
                db.verwijderVestiging(Integer.parseInt(vestigingenIDs.get(index)));
            }

        }
        if (e.getSource() == toevoegen2) {
            if (vestigingenL.getSelectedValue() != null) {
                boolean bestaatal1 = false;

                JPanel nieuw1 = new JPanel();
                nieuw1.setLayout(new GridBagLayout());

                JLabel postcode1 = new JLabel("Postcode:");
                JLabel gemeente1 = new JLabel("Gemeente:");
                postcodeT = new JTextField(12);
                gemeenteT = new JComboBox();
                gemeenteT.setBackground(Color.WHITE);

                GridBagConstraints gc = new GridBagConstraints();

                gc.anchor = GridBagConstraints.FIRST_LINE_START;
                gc.weightx = 1;
                gc.weighty = 1;
                gc.gridx = 0;
                gc.gridy = 0;
                nieuw1.add(postcode1, gc);
                gc.gridy = 1;
                nieuw1.add(gemeente1, gc);
                gc.gridy = 0;
                gc.gridx = 1;
                gc.fill = GridBagConstraints.HORIZONTAL;
                nieuw1.add(postcodeT, gc);
                gc.gridy = 1;
                nieuw1.add(gemeenteT, gc);

                postcodeT.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent evt) {
                        toonGemeenten(evt);
                    }
                });
                postcodeT.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent evt) {
                        onlyNumbersKeyTyped(evt);
                    }
                });

                UIManager.put("OptionPane.minimumSize", dim);
                int result1 = JOptionPane.showConfirmDialog(null, nieuw1, "Vestiging toevoegen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result1 == JOptionPane.OK_OPTION) {

                    if (postcodeT.getText().isEmpty() || gemeenteT.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Gegevens zijn niet geldig!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (bestaatal1) {
                        JOptionPane.showMessageDialog(null, "Leveringsgebieden mogen niet overlappen!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        db.nieuwLeveringsgebiedBijVestiging(Integer.parseInt(vestigingenIDs.get(vestigingenL.getSelectedIndex())), Integer.parseInt(postcodeT.getText()), (String) gemeenteT.getSelectedItem());
                        leveringsgebiedenLM.addElement(postcodeT.getText() + " " + (String) gemeenteT.getSelectedItem());

                    }

                }
            }

        }
        if (e.getSource() == verwijderen2) {
            if (vestigingenL.getSelectedValue() != null) {
                if (gebiedenL.getSelectedValue() != null) {
                    String postcodegemeente = (String) gebiedenL.getSelectedValue();
                    String postcodeDeel = postcodegemeente.substring(0, 4);
                    String gemeenteDeel = postcodegemeente.substring(5);

                    leveringsgebiedenLM.removeElement(gebiedenL.getSelectedValue());

                    db.verwijderLeveringsgebied(Integer.parseInt(vestigingenIDs.get(vestigingenL.getSelectedIndex())), Integer.parseInt(postcodeDeel), gemeenteDeel);

                }
            }
        }
        if (e.getSource() == toevoegen3)
        {
            // panel opmaken
            JPanel outer = new JPanel() ;
            outer.setLayout(new GridBagLayout());
            JLabel nieuweKort = new JLabel("Nieuwe Kortingscode:") ;
            JLabel gegevenKort = new JLabel("Gegeven korting:");
            JLabel geldigheidsduur = new JLabel("Geldigheidsduur:") ;
            JLabel beginDatum = new JLabel("Begindatum:") ;
            JLabel eindDatum = new JLabel("Einddatum:");
            
            JTextField gegevenKortTF = new JTextField(3) ;
            
            gegevenKortTF.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTypedAndDot(evt);
           }
       } ) ;
            
            SqlDateModel model = new SqlDateModel() ;
            SqlDateModel model2 = new SqlDateModel() ;
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
            JDatePanelImpl datePanel2 = new JDatePanelImpl(model2,p);
            datePickerBegin = new JDatePickerImpl(datePanel,new DateLabelFormatter());
            datePickerEinde = new JDatePickerImpl(datePanel2,new DateLabelFormatter());
            
            datePickerBegin.setEnabled(false);
            datePickerEinde.setEnabled(false);
            
            vestigingenVanTakeaway = new JComboBox() ;
            ArrayList<String> vestta = db.getVestigingsIDSTakeAway(ingelogdeTakeAway) ;
            for (String numm : vestta)
            {
                vestigingenVanTakeaway.addItem(db.getAdresBijVestigingsID(Integer.parseInt(numm)));
            }
            
            vestigingenVanTakeaway.setEnabled(false);
            
            taRB = new JRadioButton("TakeAway") ;
            veRB = new JRadioButton("Vestiging") ;
            JRadioButton eurRB = new JRadioButton("EUR") ;
            JRadioButton percRB = new JRadioButton("%") ;
            eenmaligRB  = new JRadioButton("Eenmalig") ;
            periodeRB = new JRadioButton("Periode") ;
            
            taRB.addActionListener(this);
            veRB.addActionListener(this);
            eenmaligRB.addActionListener(this);
            periodeRB.addActionListener(this);
            
            ButtonGroup taOfVes = new ButtonGroup();
            ButtonGroup eurOfPerc = new ButtonGroup();
            ButtonGroup eenmOfPeriode = new ButtonGroup();
            
            taOfVes.add(taRB);
            taOfVes.add(veRB);
            taRB.setSelected(true);
            
            eurOfPerc.add(eurRB);
            eurOfPerc.add(percRB);
            eurRB.setSelected(true);
            
            eenmOfPeriode.add(eenmaligRB);
            eenmOfPeriode.add(periodeRB);
            eenmaligRB.setSelected(true);
            
            // alles toevoegen aan panel
            GridBagConstraints gg = new GridBagConstraints() ;
            gg.anchor = GridBagConstraints.LINE_START ;
            gg.weightx = 1 ;
            gg.weighty = 1 ;
            gg.gridx = 0 ;
            gg.gridy = 0 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(nieuweKort, gg) ;
            
            gg.gridy = 1 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(taRB, gg) ;
            
            gg.gridy = 1 ;
            gg.insets = new Insets(0,100,0,0) ;
            outer.add(veRB, gg) ;
            
            gg.fill = GridBagConstraints.HORIZONTAL ;
            gg.gridy = 2 ;
            gg.insets = new Insets(0,0,0,40) ;
            outer.add(vestigingenVanTakeaway, gg) ;
            
            gg.fill = GridBagConstraints.NONE ;
            gg.gridy = 3 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(gegevenKort, gg) ;
            
            gg.gridy = 4 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(gegevenKortTF, gg) ;
            
            gg.gridy = 4 ;
            gg.insets = new Insets(0,40,0,0) ;
            outer.add(eurRB, gg) ;
            
            gg.gridy = 4 ;
            gg.insets = new Insets(0,90,0,0) ;
            outer.add(percRB, gg) ;
            
            gg.gridy = 5 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(geldigheidsduur, gg) ;
            
            gg.gridy = 6 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(eenmaligRB, gg) ;
            
            gg.gridy = 6 ;
            gg.insets = new Insets(0,100,0,0) ;
            outer.add(periodeRB, gg) ;
            
            gg.gridy = 7 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(beginDatum, gg) ;
            
            gg.gridy = 7 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(datePickerBegin, gg) ;
            
            gg.gridy = 8 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(eindDatum, gg) ;
            
            gg.gridy = 8 ;
            gg.insets = new Insets(0,0,0,0) ;
            outer.add(datePickerEinde, gg) ;
            
            UIManager.put("OptionPane.minimumSize", dim);
            int result1 = JOptionPane.showConfirmDialog(null, outer, "Kortingscode toevoegen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
             
            
            if (result1 == JOptionPane.OK_OPTION) 
            {
                if(gegevenKortTF.getText().isEmpty())
                {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Geen waarde ingegeven", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(Double.parseDouble(gegevenKortTF.getText()) <=1 )
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U kunt geen korting geven van kleiner of gelijk aan 1!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                else if(percRB.isSelected() && Double.parseDouble(gegevenKortTF.getText()) >100  )
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U kunt geen kortingspercentage geven groter dan 100%", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                
                else if (taRB.isSelected())
                {
                    if (eenmaligRB.isSelected())
                    {
                        double ggvnKorting = Double.parseDouble(gegevenKortTF.getText()) ;
                        if (percRB.isSelected())
                        {
                            ggvnKorting = ggvnKorting/100.0 ;
                        }
                        db.nieuweUniekeActieKortingsCode(codecombinatieGenereren(), ggvnKorting , eenmaligRB.getText(), null, null, ingelogdeTakeAway, 0);
                        super.dispose();
                        GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdeTakeAway);
                        guiWindow.setVisible(true);
                        guiWindow.setLocationRelativeTo(null);
                        guiWindow.setResizable(false);
                    }
                    else
                    {
                        double ggvnKorting = Double.parseDouble(gegevenKortTF.getText()) ;
                        if (percRB.isSelected())
                        {
                            ggvnKorting = ggvnKorting/100.0 ;
                        }
                        if( datumgroter(datePickerBegin.getModel().getValue().toString() , datePickerEinde.getModel().getValue().toString()) )
                        {
                            UIManager.put("OptionPane.minimumSize", dim) ;
                            JOptionPane.showMessageDialog(null, "Ongeldige periode!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            db.nieuweUniekeActieKortingsCode(codecombinatieGenereren(), ggvnKorting , periodeRB.getText(), (java.sql.Date) datePickerBegin.getModel().getValue(), (java.sql.Date) datePickerEinde.getModel().getValue(), ingelogdeTakeAway, 0);
                            super.dispose();
                        GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdeTakeAway);
                        guiWindow.setVisible(true);
                        guiWindow.setLocationRelativeTo(null);
                        guiWindow.setResizable(false);
                        }
                    }
                }
                else 
                {
                    if (eenmaligRB.isSelected())
                    {
                        double ggvnKorting = Double.parseDouble(gegevenKortTF.getText()) ;
                        if (percRB.isSelected())
                        {
                            ggvnKorting = ggvnKorting/100.0 ;
                        }
                        db.nieuweUniekeActieKortingsCode(codecombinatieGenereren(), ggvnKorting , eenmaligRB.getText(), null, null, null, Integer.parseInt(vestta.get(vestigingenVanTakeaway.getSelectedIndex())));
                    }
                    else
                    {
                        double ggvnKorting = Double.parseDouble(gegevenKortTF.getText()) ;
                        if (percRB.isSelected())
                        {
                            ggvnKorting = ggvnKorting/100.0 ;
                        }
                        if( datumgroter(datePickerBegin.getModel().getValue().toString() , datePickerEinde.getModel().getValue().toString()) )
                        {
                            UIManager.put("OptionPane.minimumSize", dim) ;
                            JOptionPane.showMessageDialog(null, "Ongeldige periode!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            db.nieuweUniekeActieKortingsCode(codecombinatieGenereren(), ggvnKorting , periodeRB.getText(), (java.sql.Date) datePickerBegin.getModel().getValue(), (java.sql.Date) datePickerEinde.getModel().getValue(), null,Integer.parseInt(vestta.get(vestigingenVanTakeaway.getSelectedIndex())) );
                        }
                    }
                }
            }
            
        }
        if (e.getSource() == veRB)
        {
           vestigingenVanTakeaway.setEnabled(true);
        }
        if (e.getSource() == taRB)
        {
            vestigingenVanTakeaway.setEnabled(false);
        }
        if (e.getSource() == eenmaligRB)
        {
            datePickerBegin.setEnabled(false);
            datePickerEinde.setEnabled(false);
        }
        if (e.getSource() == periodeRB)
        {
            datePickerBegin.setEnabled(true);
            datePickerEinde.setEnabled(true);
        }
        if (e.getSource() == toevoegen4) {
            boolean comboboxIngevuld = false;
            boolean nieuweCatIngevuld = false;
            boolean allebeiIngevuld = false;
            boolean bestaatal = false;
            // combobox
            ArrayList<String> categorien = db.getAlleUniekeCategorien();
            JComboBox box = new JComboBox();
            box.setBackground(Color.WHITE);
            box.addItem("");
            for (String s : categorien) {
                box.addItem(s);
            }

            // panel opmaken
            JLabel cate = new JLabel("Kies een categorie");
            JLabel andere = new JLabel("Kies andere categorie:");
            JTextField andereT = new JTextField(12);
            JPanel outer = new JPanel();
            outer.setLayout(new GridLayout(1, 2));
            JPanel left = new JPanel();
            left.setLayout(new GridBagLayout());
            outer.add(left);
            JPanel right = new JPanel();
            right.setLayout(new GridBagLayout());

            outer.add(right);

            GridBagConstraints gc = new GridBagConstraints();

            //elementen toevoegen aan linker panel
            gc.anchor = GridBagConstraints.LINE_START;
            gc.weightx = 0;
            gc.weighty = 0;
            gc.gridx = 0;
            gc.gridy = 0;
            gc.insets = new Insets(0, 0, 0, 0);
            left.add(cate, gc);

            gc.gridy = 1;
            gc.insets = new Insets(30, 0, 0, 0);
            left.add(andere, gc);

            //elementen toevoegen aan recher panel
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.weightx = 0;
            gc.weighty = 0;
            gc.gridx = 0;
            gc.gridy = 0;
            gc.insets = new Insets(0, 0, 0, 0);
            right.add(box, gc);

            gc.gridy = 1;
            gc.insets = new Insets(30, 0, 0, 0);
            right.add(andereT, gc);

            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
            UIManager.put("Optionpane.buttonBackground", Color.DARK_GRAY);
            int result = JOptionPane.showConfirmDialog(null, outer, "Categorie toevoegen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (!(((String) box.getSelectedItem()).equals(""))) {
                comboboxIngevuld = true;
            }
            if (!(andereT.getText().equals(""))) {
                nieuweCatIngevuld = true;
            }
            if ((nieuweCatIngevuld) && (comboboxIngevuld)) {
                allebeiIngevuld = true;
            }
            if (categorieLM.contains((String) box.getSelectedItem()) || categorieLM.contains(andereT.getText())) {
                bestaatal = true;
            }

            if (result == JOptionPane.OK_OPTION && allebeiIngevuld) {
                UIManager.put("OptionPane.minimumSize", dim);
                JOptionPane.showMessageDialog(null, "Je mag maar 1 categorie per keer toevoegen!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (result == JOptionPane.OK_OPTION && nieuweCatIngevuld && andereT.getText().length() > 20) {
                JOptionPane.showMessageDialog(null, "Categorie kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (result == JOptionPane.OK_OPTION && comboboxIngevuld && !bestaatal) {
                categorieLM.addElement((String) box.getSelectedItem());
                db.nieuweCategorieBijTakeaway(ingelogdeTakeAway, (String) box.getSelectedItem());

            } else if (result == JOptionPane.OK_OPTION && nieuweCatIngevuld && !bestaatal) {
                categorieLM.addElement(andereT.getText());
                db.nieuweCategorieBijTakeaway(ingelogdeTakeAway, andereT.getText());

            } else {

            }

        }
        if (e.getSource() == verwijderen4) {
            String index = (String) categorieL.getSelectedValue();
            categorieLM.removeElement(categorieL.getSelectedValue());
            db.verwijderCategorie(ingelogdeTakeAway, index);

        }

        if (e.getSource() == naarMenu) {
            super.dispose();
            GUI_MenuAanpassen guiWindow = new GUI_MenuAanpassen(ingelogdeTakeAway);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);

        }

    }

    public void toonGemeenten(KeyEvent evt) {
        gemeenteT.removeAllItems();
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();

            postcodeT.setText(postcodeT.getText() + c);

            if (postcodeT.getText().length() == 4) {
                ArrayList<String> gem = db.getGemeentesBijPostcode(Integer.parseInt(postcodeT.getText()));
                for (String s : gem) {
                    gemeenteT.addItem(s);
                }
                gemeenteT.showPopup();
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_TAB);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {
                evt.consume();
            }
        }
    }

    public void onlyNumbersKeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c))) {
            e.consume();
        }
    }

    public void onlyNumbersKeyTypedAndDot(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_PERIOD)) {
            e.consume();
        }
    }
    public String codecombinatieGenereren()
    {
        Random random = new Random() ;
        boolean firstNoZero = false ;
        boolean codeBestaatal = true ;
        int first = 0 ;
        String codeCombinatie = "" ;
        ArrayList<String> codeCombinaties = db.getLijstCodeCombinaties();
            while (!firstNoZero)
            {
                first = random.nextInt(10) ;
                if (first != 0)
                {
                    firstNoZero = true ;
                }
            }
                    
            while (codeBestaatal)
            {
                codeCombinatie = first + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10) + "";
                if (!(codeCombinaties.contains(codeCombinatie)))
                {
                    codeBestaatal = false ;
                }
            }
        return codeCombinatie ;
    }

    
    public Boolean datumgroter(String datumbegin , String datumeinde)
    {
        
        int dag = Integer.parseInt(datumbegin.substring(8,10));
        int maand = Integer.parseInt(datumbegin.substring(5,7));
        int jaar = Integer.parseInt(datumbegin.substring(0, 4)) ;
        System.out.println(dag + " " + maand +" " +jaar + "");
        int dagE = Integer.parseInt(datumeinde.substring(8,10));
        int maandE = Integer.parseInt(datumeinde.substring(5,7));
        int jaarE = Integer.parseInt(datumeinde.substring(0, 4)) ;
        System.out.println(dagE +" "+ maandE + " "+jaarE + "");
        boolean statement = false ;
        if(jaar == jaarE)
        {
            if(maand == maandE)
            {
                if(dag > dagE)
                {
                    statement = true  ;
                }
            }
            else if (maand > maandE)
            {
                statement = true ;
            }
            else
            {
                statement = false ;
            }
            
        }
        else if(jaar > jaarE)
        {
            statement = true ;
        }
        else
        {
            statement = false ;
        }
        return statement ;
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vestigingenL.getSelectionModel()) {
            if (vestigingenL.getSelectedValue() == null) {
                leveringsgebiedenLM.removeAllElements();
            } else {
                leveringsgebiedenLM.removeAllElements();
                HashMap<String, Integer> lev = db.getLeveringsGebiedenBijVestigingsID(Integer.parseInt(vestigingenIDs.get(vestigingenL.getSelectedIndex())));
                for (String gem : lev.keySet()) {
                    leveringsgebiedenLM.addElement(lev.get(gem) + " " + gem);
                }
            }
        }
    }
   public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}         
}
