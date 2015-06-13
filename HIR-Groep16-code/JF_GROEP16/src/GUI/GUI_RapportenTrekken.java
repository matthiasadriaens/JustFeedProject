package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI_RapportenTrekken  extends JFrame implements ActionListener{
    
    //initialiseren labels
    private JLabel rapportenTrekken;
    
    //initialiseren buttons
    private JButton kortingscodesBtn,lopendeOrdersTABtn,menuBtn,verkopenTABtn,verkopenVBtn,lopendeOrdersVBtn,overzichtBtn;
    
    //initialiseren van boxen
    private Box rapportenTABox;
    private Box rapportenVBox;
    private String ingelogedeKlant;
    
    //object van databaseklasse
    DB db = new DB();
    
    public GUI_RapportenTrekken(String ingelogdeKlant)
    {
        super("Just-Feed");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.ingelogedeKlant = ingelogdeKlant;
          
        //initialisatie van label
        rapportenTrekken = new JLabel();
        rapportenTrekken.setText("Rapporten Trekken");
        rapportenTrekken.setFont(new Font("Serif", Font.PLAIN, 25));
        
        //initialisatie van de buttons
        kortingscodesBtn = new JButton();
        kortingscodesBtn.setText("Unieke actie codes");
        kortingscodesBtn.setBackground(Color.DARK_GRAY);
        kortingscodesBtn.setForeground(Color.WHITE);
        kortingscodesBtn.setFocusable(false);
        kortingscodesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        kortingscodesBtn.addActionListener(this);
        lopendeOrdersTABtn = new JButton();
        lopendeOrdersTABtn.setText("Lopende orders");
        lopendeOrdersTABtn.setBackground(Color.DARK_GRAY);
        lopendeOrdersTABtn.setForeground(Color.WHITE);
        lopendeOrdersTABtn.setFocusable(false);
        lopendeOrdersTABtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lopendeOrdersTABtn.addActionListener(this);
        menuBtn = new JButton();
        menuBtn.setText("Menu");
        menuBtn.setBackground(Color.DARK_GRAY);
        menuBtn.setForeground(Color.WHITE);
        menuBtn.setFocusable(false);
        menuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuBtn.addActionListener(this);
        verkopenTABtn = new JButton();
        verkopenTABtn.setText("Verkopen");
        verkopenTABtn.setBackground(Color.DARK_GRAY);
        verkopenTABtn.setForeground(Color.WHITE);
        verkopenTABtn.setFocusable(false);
        verkopenTABtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verkopenTABtn.addActionListener(this);
        verkopenVBtn = new JButton();
        verkopenVBtn.setText("     Verkopen     ");
        verkopenVBtn.setBackground(Color.DARK_GRAY);
        verkopenVBtn.setForeground(Color.WHITE);
        verkopenVBtn.setFocusable(false);
        verkopenVBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verkopenVBtn.addActionListener(this);
        lopendeOrdersVBtn = new JButton();
        lopendeOrdersVBtn.setText("Lopende orders");
        lopendeOrdersVBtn.setBackground(Color.DARK_GRAY);
        lopendeOrdersVBtn.setForeground(Color.WHITE);
        lopendeOrdersVBtn.setFocusable(false);
        lopendeOrdersVBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lopendeOrdersVBtn.addActionListener(this);
        overzichtBtn = new JButton();
        overzichtBtn.setText("Terug naar overzicht");
        overzichtBtn.setBackground(Color.DARK_GRAY);
        overzichtBtn.setForeground(Color.WHITE);
        overzichtBtn.setFocusable(false);
        overzichtBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        overzichtBtn.addActionListener(this);
        //maken container
        Container contentPane = getContentPane();

        //maken outer
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        contentPane.add(outer);
        
        //maken van rapportenTA-Panel
        JPanel rapportenTA = new JPanel();
        rapportenTA.setLayout(new GridBagLayout());
        rapportenTA.setBackground(Color.WHITE);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10,20,10,20);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        rapportenTA.add(kortingscodesBtn,gc);
        gc.gridy = 1;
        rapportenTA.add(menuBtn,gc);
        gc.gridy = 0;
        gc.gridx = 1;
        rapportenTA.add(lopendeOrdersTABtn,gc);
        gc.gridy = 1;
        rapportenTA.add(verkopenTABtn,gc);
        rapportenTABox = Box.createHorizontalBox();
        rapportenTABox.add(rapportenTA);
        rapportenTABox.setBorder(BorderFactory.createTitledBorder("Rapporten Takeaway"));
        
        //maken van rapportenV-Panel
        JPanel rapportenV = new JPanel();
        rapportenV.setLayout(new GridBagLayout());
        rapportenV.setBackground(Color.WHITE);
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10,20,10,17);
        rapportenV.add(verkopenVBtn,gc);
        gc.gridx = 1;
        gc.insets = new Insets(10,17,10,20);
        rapportenV.add(lopendeOrdersVBtn,gc);
        rapportenVBox = Box.createHorizontalBox();
        rapportenVBox.add(rapportenV);
        rapportenVBox.setBorder(BorderFactory.createTitledBorder("Rapporten per vestiging"));
        
    
        
        //samenstellen van outer
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0,25,0,0);
        outer.add(rapportenTrekken,gc);
        gc.insets = new Insets(0,20,0,20);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridy = 1;
        outer.add(rapportenTABox,gc);
        gc.gridy = 2;
        outer.add(rapportenVBox,gc);
        gc.gridy = 3;
        gc.fill = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,400,0,0);
        outer.add(overzichtBtn,gc);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == overzichtBtn)
        {
            super.dispose();
            //////////////////////////////////////////constructor ingelogde takeaway nog te doen!!!!!!!!!!!!
            GUI_TakeAwayInlogScherm guiWindow = new GUI_TakeAwayInlogScherm(ingelogedeKlant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        
        if (evt.getSource() == menuBtn)
        {
            maakPDF rapport = new maakPDF();
            rapport.menuPDF(ingelogedeKlant);
            JOptionPane.showMessageDialog(null, "U vindt het bestand terug in: /NetbeansProjects/ROBINDEHONDT_BELEID/Rapporten ", "Bestandslocatie", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (evt.getSource() == lopendeOrdersTABtn)
        {
            maakPDF rapport = new maakPDF();
            rapport.lopendeOrdersPDF(ingelogedeKlant);
            JOptionPane.showMessageDialog(null, "U vindt het bestand terug in: /NetbeansProjects/ROBINDEHONDT_BELEID/Rapporten ", "Bestandslocatie", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (evt.getSource() == verkopenTABtn)
        {
            maakPDF rapport = new maakPDF();
            rapport.verkopenPDF(ingelogedeKlant);
            JOptionPane.showMessageDialog(null, "U vindt het bestand terug in: /NetbeansProjects/ROBINDEHONDT_BELEID/Rapporten ", "Bestandslocatie", JOptionPane.INFORMATION_MESSAGE);
        }
        if (evt.getSource() == verkopenVBtn)
        {
            ArrayList<String> vestigingen = db.getVestigingsIDSTakeAway(ingelogedeKlant);
            ArrayList<String> adressen = new ArrayList();
            for(String vest: vestigingen)
            {
                adressen.add(vest + ":" + db.getAdresBijVestigingsID(Integer.parseInt(vest)));
            }
            JComboBox jcb = new JComboBox();
            for(String adres: adressen)
            {
                jcb.addItem(adres);
            }
            
            JOptionPane.showMessageDialog( null, jcb, "Kies een vestiging", JOptionPane.PLAIN_MESSAGE);
            String vestiging = (String)jcb.getSelectedItem();
            int ind = vestiging.indexOf(':');
            int ID = Integer.parseInt(vestiging.substring(0, ind));
            maakPDF rapport = new maakPDF();
            rapport.verkopenVestigingPDF(ingelogedeKlant, ID);
            JOptionPane.showMessageDialog(null, "U vindt het bestand terug in: /NetbeansProjects/ROBINDEHONDT_BELEID/Rapporten ", "Bestandslocatie", JOptionPane.INFORMATION_MESSAGE);
        }
        if (evt.getSource() == lopendeOrdersVBtn)
        {
            ArrayList<String> vestigingen = db.getVestigingsIDSTakeAway(ingelogedeKlant);
            ArrayList<String> adressen = new ArrayList();
            for(String vest: vestigingen)
            {
                adressen.add(vest + ":" + db.getAdresBijVestigingsID(Integer.parseInt(vest)));
            }
            JComboBox jcb = new JComboBox();
            for(String adres: adressen)
            {
                jcb.addItem(adres);
            }
            JOptionPane.showMessageDialog( null, jcb, "Kies een vestiging", JOptionPane.PLAIN_MESSAGE);
            String vestiging = (String)jcb.getSelectedItem();
            int ind = vestiging.indexOf(':');
            int ID = Integer.parseInt(vestiging.substring(0, ind));
            maakPDF rapport = new maakPDF();
            rapport.lopendeOrdersVestigingPDF(ingelogedeKlant, ID);
            JOptionPane.showMessageDialog(null, "U vindt het bestand terug in: /NetbeansProjects/ROBINDEHONDT_BELEID/Rapporten ", "Bestandslocatie", JOptionPane.INFORMATION_MESSAGE);
        }
        if (evt.getSource() == kortingscodesBtn)
        {
            maakPDF rapport = new maakPDF();
            rapport.lopendeUniekeActieKortingPDF(ingelogedeKlant);
            JOptionPane.showMessageDialog(null, "U vindt het bestand terug in: /NetbeansProjects/ROBINDEHONDT_BELEID/Rapporten ", "Bestandslocatie", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        
    }
    
}
