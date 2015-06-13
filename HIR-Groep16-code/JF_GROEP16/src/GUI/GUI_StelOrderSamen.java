package GUI;

import DATABASE_EN_ENTITEITEN.*;
     import java.awt.Color;
import java.awt.Component;
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
     import java.awt.event.ItemEvent;
     import java.awt.event.ItemListener;
     import java.awt.font.TextAttribute;
     import java.io.IOException;
     import java.text.DecimalFormat;
     import java.util.ArrayList;
     import java.util.Map;
     import java.util.Random;
     import javax.imageio.ImageIO;
     import javax.swing.BorderFactory;
     import javax.swing.Box;
     import javax.swing.ImageIcon;
     import javax.swing.JButton;
     import javax.swing.JComboBox;
     import javax.swing.JFrame;
     import javax.swing.JLabel;
     import javax.swing.JPanel;

    public class GUI_StelOrderSamen extends JFrame implements ActionListener,ItemListener{

    //declaratie labels
     private JLabel suggestiesLabel;
     private JLabel welkomLabel;
     private JLabel voornaamLabel;
     private JLabel categorieLabel;
     private JLabel gemeenteLabel;
     private JLabel gerechtLabel;
     private JLabel takeAwayLabel;

    //declaratie buttons
     private JButton winkelkarBtn;
     private JLabel saldoBtn;
     private JButton awardGerechtBtn;
     private JButton gerecht1Btn;
     private JButton gerecht2Btn;
     private JButton gerecht3Btn;
     private JButton gerecht4Btn;
     private JButton naarWinkelMandBtn;
     private JButton naarHoofdMenuBtn;
     private JButton zoekBoven;
     private JButton zoekBeneden;

    //declaratie comboboxen
     private JComboBox categorieCB;
     private JComboBox gemeenteCB;
     private JComboBox gerechtCB;
     private JComboBox takeAwayCB;
     
     private Box zoekenGerecht ;
     private JPanel zoekenGerechtenPanel ;
     // declaratie bestelling
     
     private Bestelling bestelling ;
     
     private String inlogklant ;
     private DecimalFormat f = new DecimalFormat("##.00");
     private Double totaalbedrag ;
     
     // suggesties
     private ArrayList<String> takeawaysZelfdeLeveringsadres;
     private ArrayList<Bestelling> bestellingen ;
     private ArrayList<String> takeawaysOrderhistoriek ;
     private ArrayList<String> gerechtenrandom ; 
     private ArrayList<String> gerechtenOBVorder; 
     private ArrayList<String> alletakeaways ;
     
     private String takeawaynaamSug ;
     private String gerechtSuggestie ; 
     private String gerechtSuggestie2 ;
     private String gerechtSuggestie3 ;
     private String gerechtSuggestie4 ;
     
     private String takeawaynaamSug1;
     private String takeawaynaamSug2;
     private String takeawaynaamSug3;
     private String takeawaynaamSug4;
     
     private String naamawardgerecht;
     private int menuid2 ;
     private String naamawardTakeaway ;
     DB db = new DB();


    public GUI_StelOrderSamen(String ingelogdeKlant, Double tb, Bestelling best) {

    super("Just Feed");
     setSize(800, 600);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     
     inlogklant = ingelogdeKlant ;
     totaalbedrag = tb ;
     bestelling = best ;
     takeawaysZelfdeLeveringsadres = new ArrayList() ;
     
     naamawardgerecht = "";
     menuid2 = 0 ;
     naamawardTakeaway = "";
    //maken container
     Container contentPane = getContentPane();

    //maken outer
     JPanel outer = new JPanel();
     outer.setLayout(new GridBagLayout());
     outer.setBackground(Color.WHITE);
     contentPane.add(outer);

    //initialisatie van de label-Objects
     suggestiesLabel = new JLabel();
     suggestiesLabel.setText("Stel order samen,");
     suggestiesLabel.setFont(new Font("Serif", Font.PLAIN, 25));
     welkomLabel = new JLabel();
     welkomLabel.setText("Welkom");
     voornaamLabel = new JLabel("Underlined Label");
     Font font = voornaamLabel.getFont();
     Map attributes = font.getAttributes();
     attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
     voornaamLabel.setFont(font.deriveFont(attributes));
     voornaamLabel.setText(db.getVoornaamVanKlantlogin(inlogklant));
     voornaamLabel.setBackground(Color.BLACK);
     voornaamLabel.setForeground(Color.BLACK);
     voornaamLabel.setFont(new Font("Serif", Font.PLAIN, 25));
     categorieLabel = new JLabel();
     categorieLabel.setText("Kies een categorie:");
     gemeenteLabel = new JLabel();
     gemeenteLabel.setText("Kies een gemeente:");
     gerechtLabel = new JLabel();
     gerechtLabel.setText("Kies een gerecht:");
     takeAwayLabel = new JLabel();
     takeAwayLabel.setText("Kies een takeAway:");

    //initialisatie button-objecten
     winkelkarBtn = new JButton();
     winkelkarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     winkelkarBtn.setFocusable(false);
     winkelkarBtn.addActionListener(this);
     winkelkarBtn.setPreferredSize(new Dimension(30,30));
     winkelkarBtn.setBackground(Color.white);
     winkelkarBtn.setBorder(null);
    try 
        {
            Image img = ImageIO.read(getClass().getResource("shopping_trolley.png"));
            winkelkarBtn.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
     
     saldoBtn = new JLabel();
     saldoBtn.setText("€" + f.format(totaalbedrag));
     Font font2 = saldoBtn.getFont();
     Map attributes2 = font2.getAttributes();
     attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
     saldoBtn.setFont(font.deriveFont(attributes));

     awardGerechtBtn = new JButton();
     awardGerechtBtn.setBackground(Color.DARK_GRAY);
     awardGerechtBtn.setFont(new Font("Lucida Bright", Font.BOLD ,13));
     awardGerechtBtn.setForeground(Color.WHITE);
     awardGerechtBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     awardGerechtBtn.setFocusable(false);
     awardGerechtBtn.setPreferredSize(new Dimension(70,115));
     
     
     
     naamawardgerecht = db.getAwardGerechtNaamOpMoment();
     menuid2 = Integer.parseInt(db.getAwardGerechtMenuIDOpMoment());
    
     double prijsawardgerecht = db.getPrijsGerecht(naamawardgerecht, menuid2);
     naamawardTakeaway = db.getNaamTakeAwayBijMenuID(menuid2);
     awardGerechtBtn.setText("<html>"+naamawardTakeaway+ "<br /> " + "<br />" +naamawardgerecht + "<br />"+ "€ " +prijsawardgerecht+"</html>" );
    
     awardGerechtBtn.addActionListener(this);
     takeawaysZelfdeLeveringsadres = db.getAlleActieveTakeAwaysMetleveringsgebied(bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente());
     
        
     
     boolean code = false ;
     for(String s : takeawaysZelfdeLeveringsadres)
     {
         if(s.equalsIgnoreCase(naamawardTakeaway))
         {
             code = true;
             break;
         }
         else
         {
             code = false ;
         }
         
     }
     
     if(code)
     {
         
     }
     else if(!code)
     {
         awardGerechtBtn.setEnabled(false);
     }
     else
     {
         
     }
     gerecht1Btn = new JButton();
     gerecht1Btn.setBackground(Color.DARK_GRAY);
     gerecht1Btn.setFont(new Font("Lucida Bright", Font.BOLD ,13));
     gerecht1Btn.setForeground(Color.WHITE);
     gerecht1Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     gerecht1Btn.setFocusable(false);
     gerecht1Btn.addActionListener(this);
     gerecht1Btn.setPreferredSize(new Dimension(70,115));
     //gerecht1Btn.setText(gerechtSuggestie);
     gerecht2Btn = new JButton();
     gerecht2Btn.setBackground(Color.DARK_GRAY);
     gerecht2Btn.setFont(new Font("Lucida Bright", Font.BOLD ,13));
     gerecht2Btn.setForeground(Color.WHITE);
     gerecht2Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     gerecht2Btn.setFocusable(false);
     gerecht2Btn.setPreferredSize(new Dimension(70,115));
     gerecht2Btn.addActionListener(this);
     //gerecht2Btn.setText("Gerecht n€3");
     gerecht3Btn = new JButton();
     gerecht3Btn.setBackground(Color.DARK_GRAY);
     gerecht3Btn.setFont(new Font("Lucida Bright", Font.BOLD ,13));
     gerecht3Btn.setForeground(Color.WHITE);
     gerecht3Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     gerecht3Btn.setFocusable(false);
     gerecht3Btn.setPreferredSize(new Dimension(70,115));
     gerecht3Btn.addActionListener(this);
    // gerecht3Btn.setText("Gerecht €13");
     gerecht4Btn = new JButton();
     gerecht4Btn.setBackground(Color.DARK_GRAY);
     gerecht4Btn.setFont(new Font("Lucida Bright", Font.BOLD ,13));
     gerecht4Btn.setForeground(Color.WHITE);
     gerecht4Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     gerecht4Btn.setFocusable(false);
     gerecht4Btn.setPreferredSize(new Dimension(70,115));
     gerecht4Btn.addActionListener(this);
     //gerecht4Btn.setText("Gerecht €11");
     naarWinkelMandBtn = new JButton();
     naarWinkelMandBtn.setBackground(Color.DARK_GRAY);
     naarWinkelMandBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
     naarWinkelMandBtn.setForeground(Color.WHITE);
     naarWinkelMandBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     naarWinkelMandBtn.setFocusable(false);
     naarWinkelMandBtn.setText("Naar winkelmand");
     naarWinkelMandBtn.addActionListener(this);
     naarHoofdMenuBtn = new JButton();
     naarHoofdMenuBtn.setBackground(Color.DARK_GRAY);
     naarHoofdMenuBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
     naarHoofdMenuBtn.setForeground(Color.WHITE);
     naarHoofdMenuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     naarHoofdMenuBtn.setFocusable(false);
     naarHoofdMenuBtn.setText("Terug naar hoofmenu");
     naarHoofdMenuBtn.addActionListener(this);
     zoekBoven = new JButton();
     zoekBoven.setBackground(Color.DARK_GRAY);
     zoekBoven.setFont(new Font("Lucida Bright", Font.BOLD ,14));
     zoekBoven.setForeground(Color.WHITE);
     zoekBoven.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     zoekBoven.setFocusable(false);
     zoekBoven.setText("Zoek");
     zoekBoven.addActionListener(this);
     zoekBeneden = new JButton();
     zoekBeneden.setBackground(Color.DARK_GRAY);
     zoekBeneden.setFont(new Font("Lucida Bright", Font.BOLD ,14));
     zoekBeneden.setForeground(Color.WHITE);
     zoekBeneden.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     zoekBeneden.setFocusable(false);
     zoekBeneden.setText("Zoek");
     zoekBeneden.addActionListener(this);
     
     
     JPanel suggestions = new JPanel();
     suggestions.setLayout(new GridBagLayout());
     suggestions.setBackground(Color.white);
     //suggestions.setPreferredSize(new Dimension(80,600));
     GridBagConstraints g = new GridBagConstraints();
     g.fill = GridBagConstraints.HORIZONTAL;
     g.weightx = 1;
     g.weighty = 1;
     g.gridx = 0;
     g.gridy = 0;
     suggestions.add(awardGerechtBtn, g);
     g.gridx = 1;
     suggestions.add(gerecht1Btn, g);
     g.gridx = 2;
     suggestions.add(gerecht2Btn, g);
     g.gridx = 3;
     suggestions.add(gerecht3Btn, g);
     g.gridx = 4;
     suggestions.add(gerecht4Btn, g);
     
     //panel voor zoeken gerecht maken
     zoekenGerechtenPanel = new JPanel();
     zoekenGerechtenPanel.setLayout(new GridBagLayout());
     zoekenGerechtenPanel.setBackground(Color.WHITE);
     GridBagConstraints ggc = new GridBagConstraints();
     ggc.anchor = GridBagConstraints.LINE_START;
     ggc.fill = GridBagConstraints.HORIZONTAL;
     ggc.weightx = 1;
     ggc.weighty = 1;
     ggc.gridx = 0;
     ggc.gridy = 0;
     ggc.insets = new Insets(20, 50, 20, 0);
     zoekenGerechtenPanel.add(gerechtLabel,ggc);
     ggc.gridx = 1;
     gerechtCB = new JComboBox();
     gerechtCB.setBackground(Color.WHITE);
     gerechtCB.setFocusable(false);
     gerechtCB.setPreferredSize(new Dimension(50,25));
     ggc.fill = GridBagConstraints.HORIZONTAL;
     ggc.insets = new Insets(0,35, 0, 0);
     ggc.weightx = 2;
     zoekenGerechtenPanel.add(gerechtCB,ggc);
     ggc.gridy = 0;
     ggc.gridx = 2;
     ggc.weightx = 1;
     ggc.fill = GridBagConstraints.HORIZONTAL;
     ggc.insets = new Insets(0,20,0,30);
     zoekenGerechtenPanel.add(zoekBeneden,ggc);

    //samenstellen van panel voor bovenste box
     categorieCB = new JComboBox();
     categorieCB.setBackground(Color.WHITE);
     categorieCB.setFocusable(false);
     categorieCB.addItemListener(this);
     
     gemeenteCB = new JComboBox();
     gemeenteCB.setFocusable(false);
     gemeenteCB.setBackground(Color.WHITE);
     takeAwayCB = new JComboBox();
     takeAwayCB.setFocusable(false);
     takeAwayCB.setBackground(Color.WHITE);
     takeAwayCB.addItemListener(this);
     JPanel keuze = new JPanel();
     keuze.setLayout(new GridBagLayout());
     keuze.setBackground(Color.WHITE);
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.anchor = GridBagConstraints.LINE_START;
     gbc.insets = new Insets(0, 50, 0, 0);
     gbc.weightx = 1;
     gbc.weighty = 1;
     gbc.gridx = 0;
     gbc.gridy = 0;
     keuze.add(categorieLabel, gbc);
     gbc.gridy = 1;
     keuze.add(takeAwayLabel, gbc);
     gbc.gridx = 1;
     gbc.gridy = 0;
     gbc.weightx = 0.5;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.insets = new Insets(0,-60, 0,20);
     keuze.add(categorieCB, gbc);
     gbc.gridy = 1;
     keuze.add(takeAwayCB, gbc);
     gbc.gridy  =0;
     gbc.gridx = 2;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.insets = new Insets(0,0,0,30);
     keuze.add(zoekBoven,gbc);

    //initialisatie van de boxen
     Box suggesties = Box.createHorizontalBox();
     //suggesties.setPreferredSize(new Dimension(80,400));
     suggesties.add(suggestions);
     suggesties.setBorder(BorderFactory.createTitledBorder("Suggesties"));
     Box zoekenBestelling = Box.createHorizontalBox();
     zoekenBestelling.add(keuze);
     zoekenBestelling.setBorder(BorderFactory.createTitledBorder("Zoekfunctie"));
     zoekenGerecht = Box.createHorizontalBox();
     zoekenGerecht.add(zoekenGerechtenPanel);
     zoekenGerecht.setBorder(BorderFactory.createTitledBorder("Zoek op gerecht"));
     zoekenGerecht.setEnabled(false);
     Component[] comp = zoekenGerechtenPanel.getComponents() ; 
        
        for(Component c : comp)
        {
            c.setEnabled(false);
        }   
     
    //outer samenstellen
     GridBagConstraints gc = new GridBagConstraints();
     gc.anchor = GridBagConstraints.LINE_START;
     gc.fill = GridBagConstraints.EAST;
     gc.weightx = 1;
     gc.weighty = 1;
     gc.gridx = 0;
     gc.gridy = 0;
     gc.insets = new Insets(0, 57, 0, 0);
     outer.add(suggestiesLabel, gc);
     gc.gridx = 1;
     //gc.insets = new Insets(10, -480, 0, 0);
     //outer.add(welkomLabel, gc);
     gc.gridx = 2;
     gc.insets = new Insets(0, -520, 0, 0);
     outer.add(voornaamLabel, gc);
     gc.gridx = 3;
     gc.insets = new Insets(0, -90, 0, 0);
     outer.add(winkelkarBtn, gc);
     gc.gridx = 4;
     gc.insets = new Insets(0, -60, 0, 0);
     outer.add(saldoBtn, gc);
     gc.fill = GridBagConstraints.BOTH;
     gc.gridx = 0;
     gc.gridy = 1;
     gc.weighty = 2.3;
     gc.insets = new Insets(10, 50, 0, -20);
     gc.fill = GridBagConstraints.HORIZONTAL;
     outer.add(suggesties, gc);
     gc.weighty = 1;
     gc.fill = GridBagConstraints.BOTH;
     gc.gridx = 0;
     gc.gridy = 2;
     gc.fill = GridBagConstraints.BOTH;
     gc.insets = new Insets(15,50,0,0);
     outer.add(zoekenBestelling, gc);
     gc.fill = GridBagConstraints.HORIZONTAL;
     gc.gridy = 3;
     gc.insets = new Insets(0,50,0,0);
     outer.add(zoekenGerecht,gc);
     gc.fill = GridBagConstraints.NONE;
     gc.gridx = 0;
     gc.gridy = 4;
     gc.insets = new Insets(0, 590, 0, 0);
     outer.add(naarWinkelMandBtn, gc);
     //gc.fill = GridBagConstraints.HORIZONTAL;
     gc.gridx = 0;
     gc.gridy = 4;
     gc.insets = new Insets(0, 375, 0, 0);
     outer.add(naarHoofdMenuBtn, gc);
     
     
     // zoekfunctie /////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     // categorien
     
        ArrayList <String > categorieen = db.getAlleCategorieenVanTakeeAwaysMetleveringsgebied(bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente());
        categorieCB.addItem("");
        for (String s: categorieen)
            {
                categorieCB.addItem(s);
            }
     
     // take away
        
        ArrayList<String> takeaway = db.getAlleActieveTakeAwaysMetleveringsgebied(bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente()) ;
        takeAwayCB.addItem("");
        for (String a: takeaway)
        {
            takeAwayCB.addItem(a);
        }
     
     // gerechtenSUGGESTIES
        Random randomGenerator = new Random();
        int randomInt4 = 0;
        int randomInt3 = 0;
        int randomInt2 = 0;
        int randomInt  = 0;
                                
        
        bestellingen = new ArrayList() ;
        takeawaysOrderhistoriek = new ArrayList() ;
        gerechtenrandom = new ArrayList();
        gerechtenOBVorder = new ArrayList() ;
        alletakeaways = new ArrayList() ;
        
        
            
        
            takeawaysZelfdeLeveringsadres = db.getAlleActieveTakeAwaysMetleveringsgebied(bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente());
            
            bestellingen = db.getBestellingenKlant(inlogklant) ;
            alletakeaways = db.getAlleTakeAways() ;
            for(String s : takeawaysZelfdeLeveringsadres)
            {
                takeawaysOrderhistoriek.add(s);
            }
            for(int i = 0 ; i < bestellingen.size() ; i++)
            {
                for(String s :  bestellingen.get(i).getMenuGerecht().keySet())
               {
                   if (db.getStatusTakeAway(s).equalsIgnoreCase("actief"))
                   {
                    takeawaysOrderhistoriek.add(s); 
                   }
                    
                }
                
            }
            if(takeawaysZelfdeLeveringsadres.isEmpty())
            {
                       
                randomInt = randomGenerator.nextInt(alletakeaways.size()-1);
                takeawaynaamSug1 = alletakeaways.get(randomInt);
                gerechtenrandom =  db.getGerechtenTakeAway(takeawaynaamSug1);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie = gerechtenrandom.get(randomInt2) ;
                int menuid = db.getMenuIDTakeAway(takeawaynaamSug1);
                gerecht1Btn.setText("<html>"+takeawaynaamSug1+ "<br /> " + "<br />" +gerechtSuggestie + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie, menuid)+"</html>" );
                gerecht1Btn.setEnabled(false);
               
                
                randomInt = randomGenerator.nextInt(alletakeaways.size()-1);
                alletakeaways.remove(takeawaynaamSug1);
                takeawaynaamSug2 = alletakeaways.get(randomInt);
                gerechtenrandom = db.getGerechtenTakeAway(takeawaynaamSug2);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie2 = gerechtenrandom.get(randomInt2);
                menuid = db.getMenuIDTakeAway(takeawaynaamSug2);
                gerecht2Btn.setText("<html>"+takeawaynaamSug2+ "<br /> " + "<br />" +gerechtSuggestie2 + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie2, menuid)+"</html>" );
                gerecht2Btn.setEnabled(false);
                
                
                randomInt = randomGenerator.nextInt(alletakeaways.size()-1);
                alletakeaways.remove(takeawaynaamSug2);
                takeawaynaamSug3 = alletakeaways.get(randomInt);
                gerechtenrandom = db.getGerechtenTakeAway(takeawaynaamSug3);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie3 = gerechtenrandom.get(randomInt2);
                menuid = db.getMenuIDTakeAway(takeawaynaamSug3);
                gerecht3Btn.setText("<html>"+takeawaynaamSug3+ "<br /> " + "<br />" +gerechtSuggestie3 + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie3, menuid)+"</html>" );
                gerecht3Btn.setEnabled(false);
                
                
                randomInt = randomGenerator.nextInt(alletakeaways.size()-1);
                alletakeaways.remove(takeawaynaamSug3);
                takeawaynaamSug4 = alletakeaways.get(randomInt);
                gerechtenrandom = db.getGerechtenTakeAway(takeawaynaamSug4);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie4 = gerechtenrandom.get(randomInt2);
                menuid = db.getMenuIDTakeAway(takeawaynaamSug4);
                gerecht4Btn.setText("<html>"+takeawaynaamSug4+ "<br /> " + "<br />" +gerechtSuggestie4 + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie4, menuid)+"</html>" );
                gerecht4Btn.setEnabled(false);    
                
            }
            else if(takeawaysOrderhistoriek.isEmpty()  )
            {
                randomInt = 0 ;
                if(takeawaysZelfdeLeveringsadres.size() >1)
                {
                    randomInt = randomGenerator.nextInt(takeawaysZelfdeLeveringsadres.size()-1);
                }
                takeawaynaamSug1 = takeawaysZelfdeLeveringsadres.get(randomInt);
                gerechtenrandom =  db.getGerechtenTakeAway(takeawaynaamSug1);
                randomInt2 = 0 ;
                
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }        
                gerechtSuggestie = gerechtenrandom.get(randomInt2) ;
                int menuid = db.getMenuIDTakeAway(takeawaynaamSug1);
                gerecht1Btn.setText("<html>"+takeawaynaamSug1+ "<br /> " + "<br />" +gerechtSuggestie + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie, menuid)+"</html>" );
                
                randomInt = 0 ;
                if(gerechtenrandom.size() >1)
                {
                    randomInt = randomGenerator.nextInt(takeawaysZelfdeLeveringsadres.size()-1);
                }
                takeawaysZelfdeLeveringsadres.remove(takeawaynaamSug1);
                takeawaynaamSug2 = takeawaysZelfdeLeveringsadres.get(randomInt);
                gerechtenrandom = db.getGerechtenTakeAway(takeawaynaamSug2);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie2 = gerechtenrandom.get(randomInt2);
                menuid = db.getMenuIDTakeAway(takeawaynaamSug2);
                gerecht2Btn.setText("<html>"+takeawaynaamSug2+ "<br /> " + "<br />" +gerechtSuggestie2 + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie2, menuid)+"</html>" );
                
                randomInt = 0;
                if (gerechtenrandom.size() > 1) 
                {
                    randomInt = randomGenerator.nextInt(takeawaysZelfdeLeveringsadres.size() - 1);
                }
                takeawaysZelfdeLeveringsadres.remove(takeawaynaamSug2);
                takeawaynaamSug3 = takeawaysZelfdeLeveringsadres.get(randomInt);
                gerechtenrandom = db.getGerechtenTakeAway(takeawaynaamSug3);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie3 = gerechtenrandom.get(randomInt2);
                menuid = db.getMenuIDTakeAway(takeawaynaamSug3);
                gerecht3Btn.setText("<html>"+takeawaynaamSug3+ "<br /> " + "<br />" +gerechtSuggestie3 + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie3, menuid)+"</html>" );
                
                randomInt = 0;
                if (gerechtenrandom.size() > 1) 
                {
                    randomInt = randomGenerator.nextInt(takeawaysZelfdeLeveringsadres.size() - 1);
                }
                
                takeawaysZelfdeLeveringsadres.remove(takeawaynaamSug3);
                takeawaynaamSug4 = takeawaysZelfdeLeveringsadres.get(randomInt);
                gerechtenrandom = db.getGerechtenTakeAway(takeawaynaamSug4);
                randomInt2 = 0 ;
                if (gerechtenrandom.size() > 1)
                {
                    randomInt2 = randomGenerator.nextInt(gerechtenrandom.size()-1);  
                }    
                gerechtSuggestie4 = gerechtenrandom.get(randomInt2);
                menuid = db.getMenuIDTakeAway(takeawaynaamSug4);
                gerecht4Btn.setText("<html>"+takeawaynaamSug4+ "<br /> " + "<br />" +gerechtSuggestie4 + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie4, menuid)+"</html>" );

            }

            else if(takeawaysOrderhistoriek.size()> 0 )
            {
                randomInt3 = 0 ;
                if(takeawaysOrderhistoriek.size() > 1)
                {
                    randomInt3 = randomGenerator.nextInt(takeawaysOrderhistoriek.size() - 1);
                }
                takeawaynaamSug1 = takeawaysOrderhistoriek.get(randomInt3);
                gerechtenOBVorder = db.getGerechtenTakeAway(takeawaynaamSug1);
                if(gerechtenOBVorder.size() > 1)
                {   
                    
                    randomInt4 = randomGenerator.nextInt(gerechtenOBVorder.size()-1) ;
                    gerechtSuggestie = gerechtenOBVorder.get(randomInt4) ;
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug1);
                    gerecht1Btn.setText("<html>"+takeawaynaamSug1+ "<br /> " + "<br />" +gerechtSuggestie + "<br />"+ "€ " +db.getPrijsGerecht(gerechtSuggestie, menuid)+"</html>" );


                }
                else
                {
                    randomInt4 = 0 ;
                    gerechtSuggestie = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug1);
                    gerecht1Btn.setText("<html>" + takeawaynaamSug1 + "<br /> " + "<br />" + gerechtSuggestie + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie, menuid) + "</html>");
                }
                //2de keer
                
                randomInt3 = 0;
                if (takeawaysOrderhistoriek.size() > 1) {
                    randomInt3 = randomGenerator.nextInt(takeawaysOrderhistoriek.size() - 1);
                    takeawaynaamSug2 = takeawaysOrderhistoriek.get(randomInt3);
                    gerechtenOBVorder = db.getGerechtenTakeAway(takeawaynaamSug2);
                }
                if (gerechtenOBVorder.size() > 1) {

                    randomInt4 = randomGenerator.nextInt(gerechtenOBVorder.size() - 1);
                    gerechtSuggestie2 = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug2);
                    gerecht2Btn.setText("<html>" + takeawaynaamSug2 + "<br /> " + "<br />" + gerechtSuggestie2 + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie2, menuid) + "</html>");

                } else {
                    randomInt4 = 0;
                    gerechtSuggestie2 = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug2);
                    gerecht2Btn.setText("<html>" + takeawaynaamSug2 + "<br /> " + "<br />" + gerechtSuggestie2 + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie2, menuid) + "</html>");
                }
                
                //3de keer
                
                randomInt3 = 0;
                if (takeawaysOrderhistoriek.size() > 1) {
                    randomInt3 = randomGenerator.nextInt(takeawaysOrderhistoriek.size() - 1);

                    takeawaynaamSug3 = takeawaysOrderhistoriek.get(randomInt3);
                    gerechtenOBVorder = db.getGerechtenTakeAway(takeawaynaamSug3);
                }
                if (gerechtenOBVorder.size() > 1) {

                    randomInt4 = randomGenerator.nextInt(gerechtenOBVorder.size() - 1);
                    gerechtSuggestie3 = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug3);
                    gerecht3Btn.setText("<html>" + takeawaynaamSug3 + "<br /> " + "<br />" + gerechtSuggestie3 + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie3, menuid) + "</html>");

                } else {
                    randomInt4 = 0;
                    gerechtSuggestie3 = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug3);
                    gerecht3Btn.setText("<html>" + takeawaynaamSug3 + "<br /> " + "<br />" + gerechtSuggestie3 + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie3, menuid) + "</html>");
                }
                
                //4de keer
                randomInt3 = 0;
                if (takeawaysOrderhistoriek.size() > 1) {
                    randomInt3 = randomGenerator.nextInt(takeawaysOrderhistoriek.size() - 1);

                    takeawaynaamSug4 = takeawaysOrderhistoriek.get(randomInt3);
                    gerechtenOBVorder = db.getGerechtenTakeAway(takeawaynaamSug4);
                }
                if (gerechtenOBVorder.size() > 1) {

                    randomInt4 = randomGenerator.nextInt(gerechtenOBVorder.size() - 1);
                    gerechtSuggestie4 = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug4);
                    gerecht4Btn.setText("<html>" + takeawaynaamSug4 + "<br /> " + "<br />" + gerechtSuggestie4 + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie4, menuid) + "</html>");

                } else {
                    randomInt4 = 0;
                    gerechtSuggestie4 = gerechtenOBVorder.get(randomInt4);
                    int menuid = db.getMenuIDTakeAway(takeawaynaamSug4);
                    gerecht4Btn.setText("<html>" + takeawaynaamSug4 + "<br /> " + "<br />" + gerechtSuggestie4 + "<br />" + "€ " + db.getPrijsGerecht(gerechtSuggestie4, menuid) + "</html>");
                }
            }
            
            if (takeawaysZelfdeLeveringsadres.contains(takeawaynaamSug1))
            {
               gerecht1Btn.setEnabled(true);
            }
            else
            {
                gerecht1Btn.setEnabled(false);
            }
            
            if (takeawaysZelfdeLeveringsadres.contains(takeawaynaamSug2)) 
            {
            gerecht2Btn.setEnabled(true);
            } 
            else 
            {
            gerecht2Btn.setEnabled(false);
            }
            if (takeawaysZelfdeLeveringsadres.contains(takeawaynaamSug3)) 
            {
            gerecht3Btn.setEnabled(true);
            } 
            else 
            {
            gerecht3Btn.setEnabled(false);
            }
            if (takeawaysZelfdeLeveringsadres.contains(takeawaynaamSug4)) 
            {
            gerecht4Btn.setEnabled(true);
            } else
            {
            gerecht4Btn.setEnabled(false);
            }
        }
        
        @Override
        public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == naarWinkelMandBtn)
        {
            super.dispose();
            GUI_Winkelmandje guiWindow = new GUI_Winkelmandje(inlogklant, bestelling);
            guiWindow.setVorigeTotaalBedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (evt.getSource() == winkelkarBtn)
        {
            super.dispose();
            GUI_Winkelmandje guiWindow = new GUI_Winkelmandje(inlogklant, bestelling);
            guiWindow.setVorigeTotaalBedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false); 
        }
         if (evt.getSource() == naarHoofdMenuBtn)
        {
            super.dispose();
            GUI_KlantInlogScherm guiWindow = new GUI_KlantInlogScherm(inlogklant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false); 
        }
        if (evt.getSource() == zoekBeneden)
        {
            if (gerechtCB.getSelectedItem() != "")
            {
                super.dispose();
                GUI_MaaltijdOverzicht guiWindow = new GUI_MaaltijdOverzicht((String)takeAwayCB.getSelectedItem(), (String)gerechtCB.getSelectedItem(), bestelling);
                guiWindow.setIngelogdeklant(inlogklant);
                guiWindow.setTotaalbedrag(totaalbedrag);
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false);  
            }
        }
        if (evt.getSource() == zoekBoven)
        {
            if (takeAwayCB.getSelectedItem() != "")
            {
                super.dispose();
                GUI_MenuTakeAway guiWindow = new GUI_MenuTakeAway((String)takeAwayCB.getSelectedItem(), bestelling) ;
                guiWindow.setKlant(inlogklant);
                guiWindow.setTotaalbedrag(totaalbedrag);
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false); 
            }
        }
        if(evt.getSource() == gerecht1Btn)
        {
            super.dispose();
            GUI_MaaltijdOverzicht guiWindow = new GUI_MaaltijdOverzicht( takeawaynaamSug1 , gerechtSuggestie,  bestelling);
            
            guiWindow.setIngelogdeklant(inlogklant);
            guiWindow.setTotaalbedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
            
            
        }
        if(evt.getSource() == gerecht2Btn)
        {
            super.dispose();
            GUI_MaaltijdOverzicht guiWindow = new GUI_MaaltijdOverzicht( takeawaynaamSug2 , gerechtSuggestie2,  bestelling);
            
            guiWindow.setIngelogdeklant(inlogklant);
            guiWindow.setTotaalbedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
            
            
        }
        if(evt.getSource() == gerecht3Btn)
        {
            super.dispose();
            GUI_MaaltijdOverzicht guiWindow = new GUI_MaaltijdOverzicht( takeawaynaamSug3 , gerechtSuggestie3,  bestelling);
            
            guiWindow.setIngelogdeklant(inlogklant);
            guiWindow.setTotaalbedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
            
            
        }
        if(evt.getSource() == gerecht4Btn)
        {
            super.dispose();
            GUI_MaaltijdOverzicht guiWindow = new GUI_MaaltijdOverzicht( takeawaynaamSug4 , gerechtSuggestie4,  bestelling);
            
            guiWindow.setIngelogdeklant(inlogklant);
            guiWindow.setTotaalbedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
            
            
        }
        
        if(evt.getSource() == awardGerechtBtn)
        {
            super.dispose();
            GUI_MaaltijdOverzicht guiWindow = new GUI_MaaltijdOverzicht( naamawardTakeaway , naamawardgerecht,  bestelling);
            
            guiWindow.setIngelogdeklant(inlogklant);
            guiWindow.setTotaalbedrag(totaalbedrag);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
            
            
        }
    }
    public void itemStateChanged(ItemEvent event) {
        if (event.getSource() == categorieCB)
        {
            if (event.getStateChange() == ItemEvent.SELECTED) {
            Object categorie = event.getItem();
            takeAwayCB.removeAllItems();
            if (!(categorie == null))
            {
                ArrayList<String> toonTakeaways = db.getActieveTakeAwaysMetLeveringsgebiedBijCategorie(bestelling.getLeveringspostcode(), bestelling.getLeveringsgemeente(), (String)categorie) ;
                for(String t: toonTakeaways )
                {
                    takeAwayCB.addItem(t) ;
                }
            }
          
           }
        } 
        if (event.getSource() == takeAwayCB)
        {
           if (event.getStateChange() == ItemEvent.SELECTED)
           {
               Object ta = event.getItem() ;
               if (!(ta.equals("")))
               {
                  zoekenGerecht.setEnabled(true);
                  Component[] comp = zoekenGerechtenPanel.getComponents() ; 
        
                  for(Component c : comp)
                  {
                    c.setEnabled(true);
                  }    
               }
               gerechtCB.removeAllItems();
               ArrayList<String> gerechten = db.getGerechtenTakeAway((String)ta) ;
               gerechtCB.addItem("");
               for(String g: gerechten)
               {
                   gerechtCB.addItem(g);
               }
               
               
           }
        }
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

