package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Date;
import java.util.Random;


public class GUI_Startscherm extends JFrame implements ActionListener
{
    // declaratie van de buttons
    private JButton inlogBtn ;
    private JButton registrerenBtn;
    
    // database
    DB db = new DB() ;
    
    public GUI_Startscherm()
    {
        super("Just Feed") ;
        setSize(600,400) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        Container contentPane = getContentPane() ;
       
        // onderste panel die volledig scherm inneemt
        JPanel outer = new JPanel() ;
        outer.setLayout(new BoxLayout(outer, BoxLayout.PAGE_AXIS));
        contentPane.add(outer,BorderLayout.CENTER) ;
        outer.setBackground(Color.WHITE);
       
        // plaats tussen bovenkant en afbeelding
        outer.add(Box.createRigidArea(new Dimension(0,40))) ;
        
        // afbeelding (logo)
        JLabel justFeedImage = new JLabel() ;
        ImageIcon justFeedIcon = new ImageIcon(getClass().getResource("ons zalig logo 300x.jpg")) ;
        justFeedImage.setIcon(justFeedIcon);
        outer.add(justFeedImage) ;
        justFeedImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // plaats tussen afbeelding en buttons
        outer.add(Box.createRigidArea(new Dimension(0,50))) ;

        //onderste panel waarop de 2 buttons komen
        JPanel bottom = new JPanel() ;
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
        outer.add(bottom) ;
        bottom.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottom.setBackground(Color.WHITE);
        
        //  inlogbutton
        inlogBtn = new JButton("  Inloggen  ") ;
        inlogBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14)); // ander lettertype
        inlogBtn.setBackground(Color.DARK_GRAY);                    
        inlogBtn.setForeground(Color.WHITE);
        inlogBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // indien men over de knop gaat verandert de muis naar een hand
        inlogBtn.setFocusable(false);                                       // geen focus bij opstarten van de gui anders is er een lelijke selection rand rond de knop
        inlogBtn.addActionListener(this);
        bottom.add(inlogBtn) ;
        
        // plaats tussen de 2 buttons
        bottom.add(Box.createRigidArea(new Dimension(50,0))) ;
        
        // registreren button
        registrerenBtn = new JButton("Registreren") ;
        registrerenBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        registrerenBtn.setBackground(Color.DARK_GRAY);
        registrerenBtn.setForeground(Color.WHITE);
        registrerenBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registrerenBtn.setFocusable(false);
        registrerenBtn.addActionListener(this);
        bottom.add(registrerenBtn) ;
        
        // uitdelen maandelijkse kortingscodes en awards
        voerMaandelijkseBewerkingenUit();
        
    }
    
    public static void main(String[] args) 
    {
        GUI_Startscherm guiWindow = new GUI_Startscherm() ;
        guiWindow.setVisible(true);
        guiWindow.setLocationRelativeTo(null);
        guiWindow.setResizable(false);
       
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
      if (evt.getSource() == registrerenBtn) 
      {
          super.dispose();
          GUI_RegistrerenTA_Of_Klant guiWindow = new GUI_RegistrerenTA_Of_Klant();
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null); // zorgt ervoor dat het scherm centraal geplaatst wordt
          guiWindow.setResizable(false);
      }
      if (evt.getSource() == inlogBtn)
      {
          super.dispose();
          GUI_Inloggen guiWindow = new GUI_Inloggen();
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null); // zorgt ervoor dat het scherm centraal geplaatst wordt
          guiWindow.setResizable(false);
      }
   }
    
    private void voerMaandelijkseBewerkingenUit()
    {
       // vergelijk datum van vandaag met periode laatste award 
       // periode award voor de volledige maand november krijgt 1-12-2014 bij periode. 
       // dus het is pas nieuwe maand als maand van vandaag > maand laatste award
       Date datumvandaag = new Date() ;
       System.out.println(datumvandaag) ;
       Date datumLaatste = db.getMaxDateAwardGerecht() ;
       int maandVandaag = datumvandaag.getMonth() ;
       int jaarVandaag = datumvandaag.getYear() + 1900 ;
       
       String maandVandaagTot12 ;
       // indien we december zijn (int = 11) willen we kijken of er bestellingen zijn geplaatst in november( String = 11)
           switch (maandVandaag) 
           {
                case 0: maandVandaagTot12 = "12";
                        break;
                case 1: maandVandaagTot12 = "01";
                        break;
                case 2: maandVandaagTot12 = "02";
                        break;
                case 3: maandVandaagTot12 = "03";
                        break;
                case 4: maandVandaagTot12 = "04";
                        break;
                case 5: maandVandaagTot12 = "05";
                        break;
                case 6: maandVandaagTot12 = "06";
                        break;
                case 7: maandVandaagTot12 = "07";
                        break;
                case 8: maandVandaagTot12 = "08";
                        break;
                case 9:maandVandaagTot12 = "09";
                        break;
                case 10:maandVandaagTot12 = "10";
                        break;
                case 11:maandVandaagTot12 = "11";
                        break;
                default:maandVandaagTot12 = "geen geldige maand";
                        break;
           }
       
       if (datumLaatste == null)
       {
           String maxKlant = db.getHoogstGecummuleerdeOmzetKlantBijAlleActieveTakeAwaysInMaand(maandVandaagTot12, jaarVandaag) ; // bestellingen in november
           if (!(maxKlant.equals(""))) // er zijn bestellingen geplaatst vorige maand
           {
               String gerechtAwardGerecht = db.getAwardGerecht(maandVandaagTot12, jaarVandaag) ; // gerecht met meeste bestellingen in november, krijgt award geplaatst met periode op 1 dec
               int gerechtAwardMenuID = db.getMenuIDBijAwardGerecht( maandVandaagTot12 , jaarVandaag) ;
               String userChoice = db.getHoogsteGemiddeldeReviewScoreTakeAway() ;
               ArrayList<String> takeawaybossKortingenLogins = db.getLijstKlantenTakeAwayBossKCInMaand(maandVandaagTot12, jaarVandaag) ;
               String bestSeller = db.getBestsellerAward(maandVandaagTot12, jaarVandaag) ;
               String justFeeder = db.getJFFeederAwardInMaand(maandVandaagTot12, jaarVandaag) ;
               
               // JFBosskortingscode
               String codeC = codecombinatieGenereren() ;
               db.nieuweJFBossKC(codeC);
               db.nieuweKortingBijKlantLogin(maxKlant, codeC);
               
               // TakeAwayBosskortingscode
               for (String log : takeawaybossKortingenLogins)
               {
                   String co = codecombinatieGenereren() ;
                   db.nieuweTakeAwayBossKC(co);
                   db.nieuweKortingBijKlantLogin(log, co);
               }
               
               // Hot-item
               db.nieuweAwardBijGerecht(maandVandaagTot12, jaarVandaag, gerechtAwardGerecht, gerechtAwardMenuID);
               
               //Bestseller
               db.nieuweAwardBijTakeaway("Bestseller", bestSeller, maandVandaagTot12, jaarVandaag);
               // User choice
               db.nieuweAwardBijTakeaway("User Choice", userChoice, maandVandaagTot12, jaarVandaag);
               // just feeder
               db.nieuweAwardBijTakeaway("Just Feeder", justFeeder, maandVandaagTot12, jaarVandaag);
               
           }
       }
       else
       {
          int maandLaatste = datumLaatste.getMonth() ;
          int jaarLaatste = datumLaatste.getYear() + 1900 ;
          
          if (maandVandaag != 0)
          {
             if (maandVandaag> maandLaatste)
             {
                 String maxKlant = db.getHoogstGecummuleerdeOmzetKlantBijAlleActieveTakeAwaysInMaand(maandVandaagTot12, jaarVandaag) ;
                 if (!(maxKlant.equals("")))
                 {
                    String gerechtAwardGerecht = db.getAwardGerecht(maandVandaagTot12, jaarVandaag) ;
                    int gerechtAwardMenuID = db.getMenuIDBijAwardGerecht( maandVandaagTot12 , jaarVandaag) ;
                    String userChoice = db.getHoogsteGemiddeldeReviewScoreTakeAway() ;
                    ArrayList<String> takeawaybossKortingenLogins = db.getLijstKlantenTakeAwayBossKCInMaand(maandVandaagTot12, jaarVandaag) ;
                    String bestSeller = db.getBestsellerAward(maandVandaagTot12, jaarVandaag) ;
                    String justFeeder = db.getJFFeederAwardInMaand(maandVandaagTot12, jaarVandaag) ;
                    
                    // JFBossKortingscode
                    String codeComb = codecombinatieGenereren() ;
                    db.nieuweJFBossKC(codeComb);
                    db.nieuweKortingBijKlantLogin(maxKlant, codeComb);
                     
                    // TakeAwayBosskortingscode
                    for (String log : takeawaybossKortingenLogins)
                    {
                        String co = codecombinatieGenereren() ;
                        db.nieuweTakeAwayBossKC(co);
                        db.nieuweKortingBijKlantLogin(log, co);
                    }
               
                    // Hot-item
                    db.nieuweAwardBijGerecht(maandVandaagTot12, jaarVandaag, gerechtAwardGerecht, gerechtAwardMenuID);
               
                    //Bestseller
                    db.nieuweAwardBijTakeaway("Bestseller", bestSeller, maandVandaagTot12, jaarVandaag);
                    // User choice
                    db.nieuweAwardBijTakeaway("User Choice", userChoice, maandVandaagTot12, jaarVandaag);
                    // just feeder
                    db.nieuweAwardBijTakeaway("Just Feeder", justFeeder, maandVandaagTot12, jaarVandaag);
                    
                 }
                 
             }
          }
          else
          {
              if (maandLaatste == 11)
              {
                  String maxKlant = db.getHoogstGecummuleerdeOmzetKlantBijAlleActieveTakeAwaysInMaand(maandVandaagTot12, jaarVandaag) ;
                 if (!(maxKlant.equals("")))
                 {
                    String gerechtAwardGerecht = db.getAwardGerecht(maandVandaagTot12, jaarVandaag) ;
                    int gerechtAwardMenuID = db.getMenuIDBijAwardGerecht( maandVandaagTot12 , jaarVandaag) ;
                    String userChoice = db.getHoogsteGemiddeldeReviewScoreTakeAway() ;
                    ArrayList<String> takeawaybossKortingenLogins = db.getLijstKlantenTakeAwayBossKCInMaand(maandVandaagTot12, jaarVandaag) ;
                    String bestSeller = db.getBestsellerAward(maandVandaagTot12, jaarVandaag) ;
                    String justFeeder = db.getJFFeederAwardInMaand(maandVandaagTot12, jaarVandaag) ;
                    
                    // JFBossKortingscode
                    String c = codecombinatieGenereren() ;
                    db.nieuweJFBossKC(c);
                    db.nieuweKortingBijKlantLogin(maxKlant, c);
                    
                    // TakeAwayBosskortingscode
                    for (String log : takeawaybossKortingenLogins)
                    {
                        String co = codecombinatieGenereren() ;
                        db.nieuweTakeAwayBossKC(co);
                        db.nieuweKortingBijKlantLogin(log, co);
                    }
               
                    // Hot-item
                    db.nieuweAwardBijGerecht(maandVandaagTot12, jaarVandaag, gerechtAwardGerecht, gerechtAwardMenuID);
               
                    //Bestseller
                    db.nieuweAwardBijTakeaway("Bestseller", bestSeller, maandVandaagTot12, jaarVandaag);
                    // User choice
                    db.nieuweAwardBijTakeaway("User Choice", userChoice, maandVandaagTot12, jaarVandaag);
                    // just feeder
                    db.nieuweAwardBijTakeaway("Just Feeder", justFeeder, maandVandaagTot12, jaarVandaag);
                    
                 }
              }
          }
       }
       
       
       
        
    }
    public String codecombinatieGenereren()
    {
        // hoewel we in de database de codecombinatie opslaan als string hebben we voor veiligheidsredenen er toch voor gekozen dat het eerste cijfer geen nul mag zijn 
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

}
