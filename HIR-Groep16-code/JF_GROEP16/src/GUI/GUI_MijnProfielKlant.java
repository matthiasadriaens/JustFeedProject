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
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultCaret;

public class GUI_MijnProfielKlant extends JFrame implements ActionListener
{
     private JLabel mijnProfielLabel;
     private JLabel welkomLabel;
     private JLabel voornaamWelkomLabel;
     private JLabel voornaamLabel;
     private JLabel achterNaamLabel;
     private JLabel loginNaamLabel;
     private JLabel wachtwoordLabel;
     private JLabel emailLabel;
     private JLabel telefoonNrlabel;
     private JLabel straatLabel;
     private JLabel postcodeLabel;
     private JLabel gemeenteLabel;
     private JLabel huisnummerLabel;
     
     
      //de gegevens uit de DB
     private JLabel gmijnProfielLabel;
     private JLabel gwelkomLabel;
     private JLabel gvoornaamWelkomLabel;
     private JTextField gvoornaamLabel;
     private JTextField gachterNaamLabel;
     private JTextField gloginNaamLabel;
     private JPasswordField gwachtwoordLabel;
     private JTextField gemailLabel;
     private JTextField gtelefoonNrlabel;
     private JTextField gstraatLabel;
     private JTextField gpostcodeLabel;
     private JTextField ggemeenteLabel;
     private JTextField ghuisnummerLabel;
     
     private  ArrayList<JButton> buttons ;
     
     
     private JButton naarHoofdmenu;
     private JButton registreer;
     private Box orderhistoriekOuterBox ;
     private Box reviewbox;
     private Box reviewOuterBox;
     
     private String loginklant ;
     private String voornaamKlant ;
     private String achternaamKlant ;
     private String wachtwoordKlant ;
     private String emailKlant ;
     private String telefoonnummerKlant ;
     private String straatKlant ;
     private int huisnummerKlant ;
     private int postcodeKlant ;
     private String gemeenteKlant ;
     
     private DecimalFormat f = new DecimalFormat("##.00");
    
     private JScrollPane scroll1;
     
     DB db = new DB() ;
     
     
     
     public GUI_MijnProfielKlant(String klant)
     {
        super("Just Feed - Mijn profiel");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        loginklant = klant ;
        voornaamKlant = db.getVoornaamVanKlantlogin(loginklant) ;
        achternaamKlant = db.getAchternaamVanKlantlogin(loginklant) ;
        wachtwoordKlant = db.getPaswoordVanKlantlogin(loginklant) ;
        emailKlant = db.getEmailVanKlantlogin(loginklant) ;
        telefoonnummerKlant = db.getTelefoonnummerVanKlantlogin(loginklant) ;
        straatKlant = db.getStraatVanKlant(loginklant) ;
        huisnummerKlant = db.getHuisnummerVanKlant(loginklant) ;
        postcodeKlant = db.getPostcodeVanKlant(loginklant) ;
        gemeenteKlant = db.getGemeenteVanKlant(loginklant) ;
         
         voornaamLabel = new JLabel();
         voornaamLabel.setText("Voornaam:");
         achterNaamLabel = new JLabel();
         achterNaamLabel.setText("Achternaam:");
         loginNaamLabel = new JLabel();
         loginNaamLabel.setText("Loginnaam:");
         wachtwoordLabel = new JLabel();
         wachtwoordLabel.setText("Mijn wachtwoord:");
         emailLabel = new JLabel();
         emailLabel.setText("E-mail:");
         telefoonNrlabel = new JLabel();
         telefoonNrlabel.setText("Telefoonnummer:");
         straatLabel = new JLabel();
         straatLabel.setText("Straat:");
         huisnummerLabel = new JLabel();
         huisnummerLabel.setText("Straat:");
         postcodeLabel = new JLabel();
         postcodeLabel.setText("Postcode:");
         gemeenteLabel = new JLabel();
         gemeenteLabel.setText("Gemeente:");



         naarHoofdmenu = new JButton();
         naarHoofdmenu.setBackground(Color.DARK_GRAY);
         naarHoofdmenu.setFont(new Font("Lucida Bright", Font.BOLD ,14));
         naarHoofdmenu.setForeground(Color.WHITE); //lettertype
         naarHoofdmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         naarHoofdmenu.setFocusable(false);
         naarHoofdmenu.setText("Terug naar hoofdmenu");
         
         
        
         
         buttons = new ArrayList<JButton>();
         ArrayList<Image> images =  new ArrayList<Image>();

         Border emptyBorder;
         
            // create each by jsut assigning a name based on its index
          for ( int i = 0; i < 12; i++ )
          {
            buttons.add( new JButton() );
            buttons.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons.get(i).setFocusable(false);
            buttons.get(i).setBackground(Color.white);
            buttons.get(i).addActionListener(this);
            emptyBorder = BorderFactory.createEmptyBorder();
            buttons.get(i).setBorder(emptyBorder);
            //wijzig.addActionListener(this);
             try {
                
                images.add(i, ImageIO.read(getClass().getResource("modify1.png")));
                buttons.get(i).setIcon(new ImageIcon(images.get(i)));
            }
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
          }
         
          
         Container contentPane = getContentPane();
        
         JPanel outer = new JPanel();
         outer.setLayout(new GridBagLayout());
         outer.setBackground(Color.WHITE);
         contentPane.add(outer);
         
        //ALLES BOVENPANEL
         JPanel bovenpanel = new JPanel();
         bovenpanel.setLayout(new GridBagLayout());
         bovenpanel.setBackground(Color.white);
         bovenpanel.setPreferredSize(new Dimension(750,35));
         
         mijnProfielLabel = new JLabel();
         mijnProfielLabel.setText("Mijn profiel");
         mijnProfielLabel.setFont(new Font("Serif", Font.PLAIN, 25));
         
         voornaamWelkomLabel = new JLabel(voornaamKlant);
         //naam onderlijnen
         Font font = voornaamWelkomLabel.getFont();
         Map attributes = font.getAttributes();
         attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
         voornaamWelkomLabel.setFont(font.deriveFont(attributes)); 
         //voornaamLabel.setText(db.getVoornaamVanKlantlogin(ingelogdeKlant));
         voornaamWelkomLabel.setBackground(Color.black);
         voornaamWelkomLabel.setForeground(Color.BLUE);
         welkomLabel = new JLabel();
         welkomLabel.setText("Overzicht");
         
         
         
         GridBagConstraints bovenpanelConstraint = new GridBagConstraints();
         
         bovenpanelConstraint.weightx = 6;
         bovenpanelConstraint.weighty = 1;
         bovenpanelConstraint.gridx = 0;
         bovenpanelConstraint.gridy = 0;
         bovenpanelConstraint.insets = new Insets(0, -425, 0,0);
         bovenpanel.add(mijnProfielLabel, bovenpanelConstraint);
         
         bovenpanelConstraint.weightx = 1;
         bovenpanelConstraint.weighty = 1;
         bovenpanelConstraint.gridx = 1;
         bovenpanelConstraint.gridy = 0;
         bovenpanelConstraint.insets = new Insets(0, 0, 0, -100);
         bovenpanel.add(welkomLabel, bovenpanelConstraint);
         
         bovenpanelConstraint.weightx = 1;
         bovenpanelConstraint.weighty = 1;
         bovenpanelConstraint.gridx = 2;
         bovenpanelConstraint.gridy = 0;
         bovenpanelConstraint.insets = new Insets(0,0, 0, -50);
         bovenpanel.add(voornaamWelkomLabel, bovenpanelConstraint);
         
         
         //Alles mijn gegevens panel
         JPanel mijnGegevensPanel = new JPanel();
         mijnGegevensPanel.setLayout(new GridLayout(1,2));
         mijnGegevensPanel.setBorder(BorderFactory.createTitledBorder("Mijn gegevens"));
         mijnGegevensPanel.setPreferredSize(new Dimension(750,200));
         mijnGegevensPanel.setBackground(Color.white);
         
         JPanel left = new JPanel() ;
         left.setLayout(new GridBagLayout());
         left.setBackground(Color.WHITE);
         mijnGegevensPanel.add(left);
         
         JPanel right = new JPanel() ;
         right.setLayout(new GridBagLayout());
         right.setBackground(Color.WHITE);
         mijnGegevensPanel.add(right);
         
         
         //elemententen toevoegen aan left
         
         GridBagConstraints gc = new GridBagConstraints() ;
       
           gc.anchor = GridBagConstraints.LINE_START ;
           gc.weightx = 1 ;
           gc.weighty = 1 ;
           gc.gridx = 0 ;
           gc.gridy = 0 ;
           voornaamLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(voornaamLabel, gc) ;
           
           gc.anchor = GridBagConstraints.LINE_START ;
           gc.weightx = 1 ;
           gc.weighty = 1 ;
           gc.gridx = 2;
           gc.gridy = 0 ;
           left.add(buttons.get(11), gc) ; // 11 omdat er in de 0 om  mysterieuze reden altijd een spatie zit
           
           gc.anchor = GridBagConstraints.LINE_START ;
           gc.weightx = 1 ;
           gc.weighty = 1 ;
           gc.gridx = 0 ;
           gc.gridy = 1 ;
           achterNaamLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(achterNaamLabel, gc) ;
           
           gc.anchor = GridBagConstraints.LINE_START ;
           gc.weightx = 1 ;
           gc.weighty = 1 ;
           gc.gridx = 2 ;
           gc.gridy = 1 ;
           left.add(buttons.get(2), gc) ;

           gc.gridy = 2 ;
           gc.gridx = 0;
           emailLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(emailLabel, gc) ;
           gc.gridx = 2 ;
           buttons.get(0).setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(buttons.get(3), gc) ;
           
           gc.gridx = 2;
           left.add(buttons.get(4), gc) ;

           gc.gridx = 0;
           gc.gridy = 3 ;
           loginNaamLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(loginNaamLabel, gc) ;
           
           gc.gridx = 2;
           left.add(buttons.get(5), gc) ;

           gc.gridx = 0;
           gc.gridy = 4 ;
           wachtwoordLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(wachtwoordLabel, gc) ;
           gc.gridx = 2;
           left.add(buttons.get(6), gc) ;

           gc.gridx = 0;
           gc.gridy = 5 ;
           telefoonNrlabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
           left.add(telefoonNrlabel, gc) ;
           
           gc.gridx = 2;
           left.add(buttons.get(7), gc) ;

           //GEGEVENS UIT DB HALEN
           gvoornaamLabel = new JTextField(voornaamKlant);
           gachterNaamLabel = new JTextField(achternaamKlant);
           gloginNaamLabel = new JTextField(loginklant);
           gwachtwoordLabel = new JPasswordField(wachtwoordKlant);
           gemailLabel = new JTextField(emailKlant);
           gtelefoonNrlabel = new JTextField(telefoonnummerKlant);
           gstraatLabel = new JTextField(straatKlant);
           gpostcodeLabel = new JTextField(postcodeKlant + "");
           ggemeenteLabel = new JTextField(gemeenteKlant);
           ghuisnummerLabel = new JTextField(huisnummerKlant + "");
           
           gvoornaamLabel.setBorder(null);
           gachterNaamLabel.setBorder(null);
           gloginNaamLabel.setBorder(null);
           gwachtwoordLabel.setBorder(null);
           gemailLabel.setBorder(null);
           gtelefoonNrlabel.setBorder(null);
           gstraatLabel.setBorder(null);
           gpostcodeLabel.setBorder(null);
           ggemeenteLabel.setBorder(null);
           ghuisnummerLabel.setBorder(null);
           
           gvoornaamLabel.setEditable(false);
           gachterNaamLabel.setEditable(false);
           gloginNaamLabel.setEditable(false);
           gwachtwoordLabel.setEditable(false);
           gemailLabel.setEditable(false);
           gtelefoonNrlabel.setEditable(false);
           gstraatLabel.setEditable(false);
           gpostcodeLabel.setEditable(false);
           ggemeenteLabel.setEditable(false);
           ghuisnummerLabel.setEditable(false);
           
           gvoornaamLabel.setBackground(Color.WHITE);
           gachterNaamLabel.setBackground(Color.WHITE);
           gloginNaamLabel.setBackground(Color.WHITE);
           gwachtwoordLabel.setBackground(Color.WHITE);
           gemailLabel.setBackground(Color.WHITE);
           gtelefoonNrlabel.setBackground(Color.WHITE);
           gstraatLabel.setBackground(Color.WHITE);
           gpostcodeLabel.setBackground(Color.WHITE);
           ggemeenteLabel.setBackground(Color.WHITE);
           ghuisnummerLabel.setBackground(Color.WHITE);
           
           
           
           
           
           gc.anchor = GridBagConstraints.LINE_START ;
           gc.fill = GridBagConstraints.HORIZONTAL ;
           gc.weightx = 4 ;
           gc.gridx = 1 ;
           gc.gridy = 0 ;
           left.add(gvoornaamLabel, gc) ;

           gc.gridy = 1;
           gc.gridx=1;
           left.add(gachterNaamLabel, gc) ;

           gc.gridy = 2 ;
           gc.gridx=1;
           left.add(gemailLabel, gc) ;

           gc.gridy = 3 ;
           gc.gridx=1;
           left.add(gloginNaamLabel, gc) ;

           gc.gridy = 4 ;
           gc.gridx=1;
           left.add(gwachtwoordLabel, gc) ;

           gc.gridy = 5 ;
           gc.gridx=1;
           left.add(gtelefoonNrlabel, gc) ;

           gc.fill = GridBagConstraints.NONE ;
           gc.weighty = 5 ;
           gc.gridy = 7 ;
           JLabel empty = new JLabel() ;
           left.add(empty, gc) ;
         
           
           // elementen toevoegen aan rechterpaneel
       
           gc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING ;
           gc.weightx = 1 ;
           gc.weighty = 1 ;
           gc.gridx = 0 ;
           gc.gridy = 0 ;
           right.add(straatLabel,gc) ;
           
           gc.gridx = 3;
           right.add(buttons.get(8), gc) ;

           gc.gridx = 0;
           gc.gridy = 1 ;
           right.add(postcodeLabel,gc) ;
           
           gc.gridx = 3;
           right.add(buttons.get(9), gc) ;

           gc.gridx = 0;
           gc.gridy = 2 ;
           right.add(gemeenteLabel,gc) ;
           
           gc.gridx = 3;
           right.add(buttons.get(10), gc) ;

           gc.fill = GridBagConstraints.HORIZONTAL ;
           gc.weightx = 4 ;
           gc.gridx = 1 ;
           gc.gridy = 0 ;
           right.add(gstraatLabel,gc) ;

           gc.anchor = GridBagConstraints.LINE_END ;
           gc.gridx = 2 ;

           right.add(ghuisnummerLabel, gc) ;

           gc.anchor = GridBagConstraints.LINE_START ;
           gc.gridx = 1 ;

           gc.gridy = 1 ;
           right.add(gpostcodeLabel, gc) ;

           gc.gridy = 2 ;
           right.add(ggemeenteLabel, gc );
           
           
           //RAAR maar gewoon vullen met een empty button, gebruiker kan het niet merken, eventueel zichtbaar en actionlistener toevoegen om te saven
           
           gc.fill = GridBagConstraints.NONE ;
           registreer = new JButton("Opslaan") ;
           
           //Border emptyB = BorderFactory.createEmptyBorder();
          // registreer.setBorder(emptyB);
           
          registreer.setFont(new Font("Lucida Bright", Font.BOLD ,14));
          registreer.setBackground(Color.DARK_GRAY);
          registreer.setForeground(Color.WHITE);
          registreer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          registreer.setFocusable(false);
          registreer.setEnabled(false);
          registreer.addActionListener(this);
          
           gc.anchor = GridBagConstraints.PAGE_END ;
           gc.gridy = 3 ;
           gc.gridx = 1 ;
           gc.weightx = 1.5;
           gc.weighty = 9 ; 
           gc.insets = new Insets(0,0,10,0) ;
           right.add(registreer, gc) ;
                  
                   
         
           
           
         //Alles orderhistoriekpanel
         orderhistoriekOuterBox = Box.createHorizontalBox();
         orderhistoriekOuterBox.setBorder(BorderFactory.createTitledBorder("Orderhistoriek"));
         orderhistoriekOuterBox.setBackground(Color.white);
         orderhistoriekOuterBox.setPreferredSize(new Dimension(370,175)); //verleden 755ipv355
         
             reviewOuterBox = Box.createHorizontalBox();
             reviewOuterBox.setBorder(BorderFactory.createTitledBorder("Mijn reviews"));
             reviewOuterBox.setBackground(Color.white);
             reviewOuterBox.setPreferredSize(new Dimension(370,175)); //verleden 755ipv355
         
         
         
         
                 //Onderpanel die ik verdeel links voor orderhistoriek en rechts voor reviews
                   JPanel verdeelpanel = new JPanel();
                   verdeelpanel.setLayout (new GridLayout(1,2));

                   JPanel linksonder = new JPanel();
                   linksonder.setBackground(Color.white);
                   
                   JPanel rechtsonder = new JPanel();
                   rechtsonder.setBackground(Color.white);

                   verdeelpanel.add(linksonder);
                   verdeelpanel.add(rechtsonder);
                   
                   linksonder.add(orderhistoriekOuterBox);
                   rechtsonder.add(reviewOuterBox);
         
         JPanel scrollpanel= new JPanel();
         //scrollpanel.setLayout(new GridBagLayout());
         scrollpanel.setLayout(new BoxLayout(scrollpanel, BoxLayout.PAGE_AXIS));
         scrollpanel.setBackground(Color.white);
         
             JPanel scrollpanel1= new JPanel();
             scrollpanel1.setLayout(new GridBagLayout());
             scrollpanel1.setBackground(Color.white);
         
         
         JScrollPane scroll = new JScrollPane(scrollpanel); //scroll hangt nu aan een panel
         scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         scroll.setBorder(null);
         orderhistoriekOuterBox.add(scroll);
         
             scroll1 = new JScrollPane(scrollpanel1); //scroll hangt nu aan een panel
             scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
             scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
             scroll1.setBorder(null);
             reviewOuterBox.add(scroll1);

         //Alles onderpanel
         JPanel onderpanel = new JPanel();
         onderpanel.setBackground(Color.white);
         onderpanel.setLayout(new GridBagLayout());
         naarHoofdmenu = new JButton();
         naarHoofdmenu.setBackground(Color.DARK_GRAY);
         naarHoofdmenu.setFont(new Font("Lucida Bright", Font.BOLD ,14));
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
         onderpanelConstraint.insets = new Insets(0, 0, 0, 10); // 0 0 -415 10
         onderpanel.add(naarHoofdmenu, onderpanelConstraint);
         
         
         
         
         GridBagConstraints eenbinnenboxconstr = new GridBagConstraints();
         GridBagConstraints buitenboxconstr = new GridBagConstraints();

         Map onderlijnen = font.getAttributes();
         onderlijnen.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
         
        
        Box binnenbox;
        
        ArrayList<Bestelling> best = db.getBestellingenKlant(loginklant) ;
        for (Bestelling b : best ) 
        {
            int h = -1;
            int hoeveelheid = 0 ;
            // binnenreviewpanel + knoppen
            JPanel eenorderpanel = new JPanel();
            eenorderpanel.setLayout(new GridBagLayout());
            eenorderpanel.setBackground(Color.white);
            
            for (String ta : b.getMenuGerecht().keySet())
            {
               h++ ;
               hoeveelheid ++ ;
                
            JLabel detakeaway = new JLabel(ta) ;
            detakeaway.setFont(new Font("Serif", Font.BOLD, 11));
            detakeaway.setFont(font.deriveFont(attributes));
                
            JLabel naam = new JLabel("Naam");
            JLabel prijs= new JLabel("Prijs");
            JLabel aantal = new JLabel("Aantal");
            JLabel totaal = new JLabel("Totaal");
            naam.setFont(font.deriveFont(onderlijnen));
            prijs.setFont(font.deriveFont(onderlijnen));
            aantal.setFont(font.deriveFont(onderlijnen));
            totaal.setFont(font.deriveFont(onderlijnen));
         
         
            eenbinnenboxconstr.anchor = GridBagConstraints.LINE_START ;
            eenbinnenboxconstr.weightx = 1;
            eenbinnenboxconstr.weighty =1;
            eenbinnenboxconstr.gridx = 0;
            eenbinnenboxconstr.gridy=h;
            eenbinnenboxconstr.insets = new Insets(0, 10, 0, 0);
            eenorderpanel.add(detakeaway,eenbinnenboxconstr);
            
            h++ ;
            
            eenbinnenboxconstr.anchor = GridBagConstraints.NORTH ;
            eenbinnenboxconstr.weightx = 1;
            eenbinnenboxconstr.weighty =1;
            eenbinnenboxconstr.gridx = 0;
            eenbinnenboxconstr.gridy= h;
            eenbinnenboxconstr.insets = new Insets(0, 0, 0, 0);
            eenorderpanel.add(naam,eenbinnenboxconstr);

            eenbinnenboxconstr.weightx = 1;
            eenbinnenboxconstr.weighty =1;
            eenbinnenboxconstr.gridx = 1;
            eenbinnenboxconstr.gridy=h;
            eenbinnenboxconstr.insets = new Insets(0, 0, 0, 0);
            eenorderpanel.add(prijs,eenbinnenboxconstr);

            eenbinnenboxconstr.weightx = 1;
            eenbinnenboxconstr.weighty =1;
            eenbinnenboxconstr.gridx = 2;
            eenbinnenboxconstr.gridy=h;
            eenorderpanel.add(aantal,eenbinnenboxconstr);

            eenbinnenboxconstr.weightx = 1;
            eenbinnenboxconstr.weighty =1;
            eenbinnenboxconstr.gridx = 3;
            eenbinnenboxconstr.gridy=h;
            eenorderpanel.add(totaal,eenbinnenboxconstr);
            
            if (b.getMenuGerecht().get(ta) != null)
            {
                    for (String ger: b.getMenuGerecht().get(ta).keySet())
                    {
                        h++ ;
                        hoeveelheid ++ ;
                        
                    JLabel gerecht = new JLabel(ger);
                    gerecht.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 0;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenbinnenboxconstr.insets = new Insets(-20, 0, 0, 0);
                    eenorderpanel.add(gerecht, eenbinnenboxconstr);
                    
                    double pr = db.getPrijsGerecht(ger, db.getMenuIDTakeAway(ta)) ;
                    JLabel prijsperstuk = new JLabel("€" + f.format(pr));
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 1;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(prijsperstuk, eenbinnenboxconstr);

                    JLabel aantalstuks = new JLabel(b.getMenuGerecht().get(ta).get(ger) + "");
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 2;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(aantalstuks, eenbinnenboxconstr);

                    JLabel totaalprijs = new JLabel("€" + f.format(pr*b.getMenuGerecht().get(ta).get(ger)));
                    totaalprijs.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 3;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(totaalprijs, eenbinnenboxconstr);
                    }
            }
            if (b.getMenuBijGerecht().get(ta) != null)
            {
                    for (String bijger: b.getMenuBijGerecht().get(ta).keySet())
                    {
                        h++ ;
                        hoeveelheid ++ ;
                        
                    JLabel gerecht = new JLabel(bijger);
                    gerecht.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 0;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenbinnenboxconstr.insets = new Insets(-20, 0, 0, 0);
                    eenorderpanel.add(gerecht, eenbinnenboxconstr);
                    
                    double pr = db.getPrijsBijgerecht(bijger, db.getMenuIDTakeAway(ta)) ;
                    JLabel prijsperstuk = new JLabel("€" + f.format(pr));
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 1;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(prijsperstuk, eenbinnenboxconstr);

                    JLabel aantalstuks = new JLabel(b.getMenuBijGerecht().get(ta).get(bijger) + "");
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 2;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(aantalstuks, eenbinnenboxconstr);

                    JLabel totaalprijs = new JLabel("€" + f.format(pr*b.getMenuBijGerecht().get(ta).get(bijger)));
                    totaalprijs.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 3;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(totaalprijs, eenbinnenboxconstr);
                    }
            }
            if (b.getMenuSnacks().get(ta) != null)
            {
                    for (String sn: b.getMenuSnacks().get(ta).keySet())
                    {
                        h++ ;
                        hoeveelheid ++ ;
                        
                    JLabel gerecht = new JLabel(sn);
                    gerecht.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 0;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenbinnenboxconstr.insets = new Insets(-20, 0, 0, 0);
                    eenorderpanel.add(gerecht, eenbinnenboxconstr);
                    
                    double pr = db.getPrijsSnack(sn, db.getMenuIDTakeAway(ta)) ;
                    JLabel prijsperstuk = new JLabel("€" + f.format(pr));
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 1;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(prijsperstuk, eenbinnenboxconstr);

                    JLabel aantalstuks = new JLabel(b.getMenuSnacks().get(ta).get(sn) + "");
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 2;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(aantalstuks, eenbinnenboxconstr);

                    JLabel totaalprijs = new JLabel("€" + f.format(pr*b.getMenuSnacks().get(ta).get(sn)));
                    totaalprijs.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 3;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(totaalprijs, eenbinnenboxconstr);
                    }
            }
            if (b.getMenuDranken().get(ta) != null)
            {
                    for (String dr: b.getMenuDranken().get(ta).keySet())
                    {
                        h++ ;
                        hoeveelheid ++ ;
                        
                    JLabel gerecht = new JLabel(dr);
                    gerecht.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 0;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenbinnenboxconstr.insets = new Insets(-20, 0, 0, 0);
                    eenorderpanel.add(gerecht, eenbinnenboxconstr);
                    
                    double pr = db.getPrijsDrank(dr, db.getMenuIDTakeAway(ta)) ;
                    JLabel prijsperstuk = new JLabel("€" + f.format(pr));
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 1;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(prijsperstuk, eenbinnenboxconstr);

                    JLabel aantalstuks = new JLabel(b.getMenuDranken().get(ta).get(dr) + "");
                    prijsperstuk.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 2;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(aantalstuks, eenbinnenboxconstr);

                    JLabel totaalprijs = new JLabel("€" + f.format(pr*b.getMenuDranken().get(ta).get(dr)));
                    totaalprijs.setFont(new Font("Serif", Font.BOLD, 11));
                    eenbinnenboxconstr.gridx = 3;
                    eenbinnenboxconstr.gridy = h;
                    eenbinnenboxconstr.weightx = 1;
                    eenbinnenboxconstr.weighty = 1;
                    eenorderpanel.add(totaalprijs, eenbinnenboxconstr);
                    }
            }
            }
            //maak box aan
            binnenbox = Box.createHorizontalBox();
            binnenbox.setBorder(BorderFactory.createTitledBorder(b.getDatum().toString())); // set date
            if (hoeveelheid <= 3)
                binnenbox.setPreferredSize(new Dimension(330,150));  
            else if (hoeveelheid <= 7)
                binnenbox.setPreferredSize(new Dimension(330,300));
            else
                binnenbox.setPreferredSize(new Dimension(330,500));
            
            
            //binnenbox.setPreferredSize(new Dimension(330,300));
            binnenbox.add(eenorderpanel);

            // steek box in buitenbox
            buitenboxconstr.weightx = 1;
            buitenboxconstr.weighty = 1;
            buitenboxconstr.gridx = 0;
            buitenboxconstr.gridy ++;
          //  scrollpanel.add(binnenbox, buitenboxconstr);
            scrollpanel.add(binnenbox) ;
        }
        
                 GridBagConstraints c = new GridBagConstraints();
                 GridBagConstraints buitenboxconstr1 = new GridBagConstraints();

                 Map onderlijnen1 = font.getAttributes();
                 onderlijnen1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

                buitenboxconstr1.gridy = 0;
                Box binnenbox1;
                ////////////////////////////////////////////////////////////////
                ArrayList<Integer> reviewIDs = db.getReviewIDs(klant);
                for (Integer ID: reviewIDs) {
                    // binnenreviewpanel + knoppen
                    JPanel reviewpanel = new JPanel();
                    reviewpanel.setLayout(new GridBagLayout());
                    reviewpanel.setBackground(Color.white);
                    
                    JButton likes = new JButton();
                    likes.setForeground(Color.WHITE);
                    likes.setBackground(Color.WHITE);
                    likes.setFocusable(false);
                    likes.setBorder(null);
                    try {
                    Image img = ImageIO.read(getClass().getResource("duim.png"));
                    likes.setIcon(new ImageIcon(img));
                    }
                    catch (IOException ex) 
                    {
                        ex.printStackTrace();
                    }
                    
                    JLabel score = new JLabel("Score: " + db.getScoreReview(ID)+ "/10");
                    score.setFont(new Font("Serif", Font.BOLD, 11));
                    c.anchor = GridBagConstraints.FIRST_LINE_START ;
                    c.gridx = 0;
                    c.gridy = 0;
                    c.weightx = 1;
                    c.weighty = 1;
                    c.insets = new Insets(0, 30, 0, 0);
                    reviewpanel.add(score, c);
                    
                    c.weightx = 1;
                    c.weighty =1;
                    c.gridx = 2;
                    c.gridy=0;
                    c.insets = new Insets(0, -20, 0, 0);
                    reviewpanel.add(likes,c);
                    
                    c.weightx = 1;
                    c.weighty =1;
                    c.gridx = 3;
                    c.gridy=0;
                    c.insets = new Insets(0, 0, 0, 0);
                    
                    JLabel L = new JLabel(": " + db.getAantalLikesReview(ID)) ;
                    L.setFont(new Font("Serif", Font.BOLD, 11));
                    c.insets = new Insets(0,0,0,10) ;
                    reviewpanel.add(L,c) ;
                    
                    
                    
                    JTextArea tekst = new JTextArea(db.getTekstReview(ID));
                    tekst.setLineWrap(true);
                    tekst.setWrapStyleWord(true);
                    tekst.setEditable(false);
                    
                    c.anchor = GridBagConstraints.FIRST_LINE_START;
                    c.fill = GridBagConstraints.HORIZONTAL ;
                    c.gridx = 0;
                    c.gridy = 2;
                    c.weightx = 6;
                    c.weighty = 1;
                    c.insets = new Insets(20, 30, 0, 0);
                    reviewpanel.add(tekst, c);
                    
                   

                   

                    //maak box aan
                    binnenbox1 = Box.createHorizontalBox();
                    binnenbox1.setBorder(BorderFactory.createTitledBorder(db.getNaamEnTakeAwayBijReview(ID)));
                    binnenbox1.setPreferredSize(new Dimension(330,150));
                    binnenbox1.add(reviewpanel);

                    // steek box in buitenbox
                    buitenboxconstr1.weightx = 1;
                    buitenboxconstr1.weighty = 1;
                    buitenboxconstr1.gridx = 0;
                    buitenboxconstr1.gridy ++;
                    scrollpanel1.add(binnenbox1, buitenboxconstr1);
                }
        
        //ALLES TOEVOEGEN AAN OUTER
        
        GridBagConstraints frameConstraint = new GridBagConstraints();
         
         frameConstraint.weightx = 1;
         frameConstraint.weighty = 0.1;
         frameConstraint.gridx = 0;
         frameConstraint.gridy = 0;
         frameConstraint.insets = new Insets(0, 0, 0, 0);
         outer.add(bovenpanel, frameConstraint);
         
         
         frameConstraint.weightx = 1;
         frameConstraint.weighty = 1;
         frameConstraint.gridx = 0;
         frameConstraint.gridy = 1;
         frameConstraint.insets = new Insets(0, 0, 0, 0);
         
         outer.add(mijnGegevensPanel, frameConstraint);
         
         
         frameConstraint.weightx = 1;
         frameConstraint.weighty = 1;
         frameConstraint.gridx = 0;
         frameConstraint.gridy = 2;
         frameConstraint.insets = new Insets(0, 0, 0, 0);
         
         outer.add(verdeelpanel, frameConstraint);
         
         
         frameConstraint.weightx = 1;
         frameConstraint.weighty = 0.1;
         frameConstraint.gridx = 0;
         frameConstraint.gridy = 3;
         frameConstraint.insets = new Insets(0, 0, 0, 0);
         
         outer.add(onderpanel, frameConstraint);
         
         scrollToTop();
     }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() ==  buttons.get(11))
        {
            gvoornaamLabel.setEditable(true);
            gvoornaamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gvoornaamLabel.getCaret().setVisible(true);
            gvoornaamLabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true);
            
        }
        if (e.getSource() == buttons.get(2))
        {
            gachterNaamLabel.setEditable(true);
            gachterNaamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gachterNaamLabel.getCaret().setVisible(true);
            gachterNaamLabel.requestFocusInWindow() ;
           
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(3))
        {
            gemailLabel.setEditable(true);
            gemailLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gemailLabel.getCaret().setVisible(true);
            gemailLabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(5))
        {
            gloginNaamLabel.setEditable(true);
            gloginNaamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gloginNaamLabel.getCaret().setVisible(true);
            gloginNaamLabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(6))
        {
            gwachtwoordLabel.setEditable(true);
            gwachtwoordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gwachtwoordLabel.getCaret().setVisible(true);
            gwachtwoordLabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(7))
        {
            gtelefoonNrlabel.setEditable(true);
            gtelefoonNrlabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gtelefoonNrlabel.getCaret().setVisible(true);
            gtelefoonNrlabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(8))
        {
            gstraatLabel.setEditable(true);
            gstraatLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gstraatLabel.getCaret().setVisible(true);
            gstraatLabel.requestFocusInWindow() ;
            
            ghuisnummerLabel.setEditable(true);
            ghuisnummerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(9))
        {
            gpostcodeLabel.setEditable(true);
            gpostcodeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gpostcodeLabel.getCaret().setVisible(true);
            gpostcodeLabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == buttons.get(10))
        {
            ggemeenteLabel.setEditable(true);
            ggemeenteLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            ggemeenteLabel.getCaret().setVisible(true);
            ggemeenteLabel.requestFocusInWindow() ;
            
            registreer.setEnabled(true); 
        }
        if (e.getSource() == registreer)
        {
            if (!(voornaamKlant.equalsIgnoreCase(gvoornaamLabel.getText())))
            {
              if (gvoornaamLabel.getText().isEmpty())
              {
                  JOptionPane.showMessageDialog(null, "Voornaam moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
              }
              else if (gvoornaamLabel.getText().length() > 30)
              {
                  JOptionPane.showMessageDialog(null, "Voornaam kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
              }
              else
              {
                  db.updateVoornaamVanKlantlogin(loginklant, gvoornaamLabel.getText());
                  voornaamKlant = gvoornaamLabel.getText() ;
              }
            }
            if (!(achternaamKlant.equalsIgnoreCase(gachterNaamLabel.getText())))
            {
              if (gachterNaamLabel.getText().isEmpty())
              {
                  JOptionPane.showMessageDialog(null, "Achternaam moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
              }
              else if (gachterNaamLabel.getText().length() > 30)
              {
                  JOptionPane.showMessageDialog(null, "Achternaam kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
              }
              else
              {
                  db.updateAchternaamVanKlantlogin(loginklant, gachterNaamLabel.getText());
                  achternaamKlant = gachterNaamLabel.getText() ;
              }
            }
            if (!(wachtwoordKlant.equalsIgnoreCase(gwachtwoordLabel.getText())))
            {
              if (gwachtwoordLabel.getText().length() < 8)
              {
                  JOptionPane.showMessageDialog(null, "Paswoord moet minstens 8 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
              } 
              else if (gwachtwoordLabel.getText().length() > 20)
              {
                  JOptionPane.showMessageDialog(null, "Wachtwoord kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
              }
              else if (gwachtwoordLabel.getText().contains(loginklant))
              {
                 JOptionPane.showMessageDialog(null, "Paswoord mag de login niet bevatten!", "Error", JOptionPane.ERROR_MESSAGE);  
              }
              else
              {
                db.updatePaswoordVanKlantlogin(loginklant, gwachtwoordLabel.getText());
                wachtwoordKlant = gwachtwoordLabel.getText() ;  
              }
            } 
            if (!(emailKlant.equalsIgnoreCase(gemailLabel.getText())))
            {
                if (gemailLabel.getText().isEmpty())
                {
                   JOptionPane.showMessageDialog(null, "E-mail moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(gemailLabel.getText().contains("@") && gemailLabel.getText().contains(".")))
                {
                    JOptionPane.showMessageDialog(null, "E-mail is niet geldig!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (gemailLabel.getText().length() > 50)
                {
                    JOptionPane.showMessageDialog(null, "E-mail kan niet meer dan 50 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(db.controleerOfEmailBestaat(gemailLabel.getText())))
                {
                    JOptionPane.showMessageDialog(null, "E-mail bestaat al!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    db.updateEmailVanKlantlogin(loginklant, gemailLabel.getText());
                    emailKlant = gemailLabel.getText() ;
                }
            }
            if (!(telefoonnummerKlant.equalsIgnoreCase(gtelefoonNrlabel.getText())))
            {
                if (gtelefoonNrlabel.getText().length() != 13)
                {
                    JOptionPane.showMessageDialog(null, "Telefoonnummer is verkeerd!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    db.updateTelefoonnummerVanKlantlogin(loginklant, gtelefoonNrlabel.getText());
                    telefoonnummerKlant = gtelefoonNrlabel.getText() ;
                }
            }
            if (!(straatKlant.equalsIgnoreCase(gstraatLabel.getText())))
            {
                if (gstraatLabel.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Straat moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (gstraatLabel.getText().length() > 30)
                {
                    JOptionPane.showMessageDialog(null, "Straat kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    db.updateStraatVanKlantlogin(loginklant, gstraatLabel.getText());
                    straatKlant = gstraatLabel.getText() ;
                }
            }
            if (!(huisnummerKlant == Integer.parseInt(ghuisnummerLabel.getText())))
            {
                if (ghuisnummerLabel.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Huisnummer moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (ghuisnummerLabel.getText().length() > 11)
                {
                    JOptionPane.showMessageDialog(null, "Huistnummer kan niet meer dan 11 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    db.updateHuisnummerVanKlantlogin(loginklant, Integer.parseInt(ghuisnummerLabel.getText()));
                    huisnummerKlant = Integer.parseInt(ghuisnummerLabel.getText()) ;
                }
            }
            if (!(gemeenteKlant.equalsIgnoreCase(ggemeenteLabel.getText())))
            {
                if (ggemeenteLabel.getText().isEmpty())
                {
                   JOptionPane.showMessageDialog(null, "Gemeente moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else if (ggemeenteLabel.getText().length() > 30)
                {
                   JOptionPane.showMessageDialog(null, "Gemeente kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(db.getGemeentesBijPostcode(Integer.parseInt(gpostcodeLabel.getText())).contains(ggemeenteLabel.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Gemeente komt niet overeen met de postcode!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    db.updateGemeenteVanKlantlogin(loginklant, ggemeenteLabel.getText());
                    gemeenteKlant = ggemeenteLabel.getText() ;
                }
            }
            if (!(postcodeKlant == Integer.parseInt(gpostcodeLabel.getText())))
            {
                if (gpostcodeLabel.getText().length() != 4)
                {
                    JOptionPane.showMessageDialog(null, "Postcode bestaat uit 4 cijfers!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!(db.getGemeentesBijPostcode(Integer.parseInt(gpostcodeLabel.getText())).contains(ggemeenteLabel.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Gemeente komt niet overeen met de postcode!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                     db.updatePostcodeVanKlantlogin(loginklant, Integer.parseInt(gpostcodeLabel.getText()));
                     postcodeKlant = Integer.parseInt(gpostcodeLabel.getText())  ;
                }
            }
            
           gvoornaamLabel.setBorder(null);
           gachterNaamLabel.setBorder(null);
           gloginNaamLabel.setBorder(null);
           gwachtwoordLabel.setBorder(null);
           gemailLabel.setBorder(null);
           gtelefoonNrlabel.setBorder(null);
           gstraatLabel.setBorder(null);
           gpostcodeLabel.setBorder(null);
           ggemeenteLabel.setBorder(null);
           ghuisnummerLabel.setBorder(null);
           
           gvoornaamLabel.setEditable(false);
           gachterNaamLabel.setEditable(false);
           gloginNaamLabel.setEditable(false);
           gwachtwoordLabel.setEditable(false);
           gemailLabel.setEditable(false);
           gtelefoonNrlabel.setEditable(false);
           gstraatLabel.setEditable(false);
           gpostcodeLabel.setEditable(false);
           ggemeenteLabel.setEditable(false);
           ghuisnummerLabel.setEditable(false);
           
           gvoornaamLabel.getCaret().setVisible(false);
           gachterNaamLabel.getCaret().setVisible(false);
           gloginNaamLabel.getCaret().setVisible(false);
           gwachtwoordLabel.getCaret().setVisible(false);
           gemailLabel.getCaret().setVisible(false);
           gtelefoonNrlabel.getCaret().setVisible(false);
           gstraatLabel.getCaret().setVisible(false);
           gpostcodeLabel.getCaret().setVisible(false);
           ggemeenteLabel.getCaret().setVisible(false);
           ghuisnummerLabel.getCaret().setVisible(false);
           
           
           registreer.setEnabled(false);
            
        } 
        if (e.getSource() == naarHoofdmenu)
        {
            super.dispose();
            GUI_KlantInlogScherm guiWindow = new GUI_KlantInlogScherm(loginklant) ;
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
       
        
    }
    private void scrollToTop() 
    {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
           scroll1.getVerticalScrollBar().setValue(0);
       }
    });
    
    }
}
