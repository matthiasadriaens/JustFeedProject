package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
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
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField ;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class GUI_KlantRegistreren extends JFrame implements ActionListener
{
    // declaratie van de buttons
    private JButton registreer ; 
    private JButton aanmelden ;
    
    // declaratie van de textfields
    private JTextField voornaamT  ;
    private JTextField achternaamT ;
    private JTextField emailT ;
    private JTextField loginT ;
    private JTextField telefoonnummerT ;
    private JTextField straatT ;
    private JTextField huisnummerT ;
    private JTextField postcodeT ;
    
    // declaratie van de passwordfields
    private JPasswordField paswoordT ;
    private JPasswordField paswoordCT ;
    
    // declaratie van de comboboxen(+model)
    private JComboBox gemeenteT ;
    private DefaultComboBoxModel model ;
    
    // declaratie van een boolean
    private boolean check ;
    
    // database
    DB db = new DB() ;
    
    
    public GUI_KlantRegistreren()
    {
        super("Just Feed") ;
        setSize(600,400) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        Container contentPane = getContentPane() ;
        
        // declaratie + initialisatie van de labels
        JLabel voornaam = new JLabel("Voornaam:") ;
        JLabel achternaam = new JLabel("Achternaam:") ;
        JLabel email = new JLabel("E-mail:") ;
        JLabel login = new JLabel("Login:") ;
        JLabel paswoord = new JLabel("Paswoord:") ;
        JLabel paswoordC = new JLabel("Bevestig paswoord:") ;
        JLabel telefoonnummer = new JLabel("Telefoonnummer:") ;
        JLabel straat = new JLabel("Straat:") ;
        JLabel huisnummer = new JLabel("Huisnummer:") ;
        JLabel postcode = new JLabel("Postcode:") ;
        JLabel gemeente = new JLabel("Gemeente:") ;
        
        // initialisatie van de textfields (+passwordfield)
        voornaamT = new JTextField(20) ;
        achternaamT = new JTextField(20) ;
        emailT = new JTextField(20) ;
        loginT = new JTextField(20) ;
        paswoordT = new JPasswordField(20) ;
        paswoordCT = new JPasswordField(20) ;
        telefoonnummerT = new JTextField(20) ;
        straatT = new JTextField(20) ;
        huisnummerT = new JTextField(20) ;
        postcodeT = new JTextField(20) ;
        gemeenteT = new JComboBox() ;
        
        // extra uitvoeringen
        telefoonnummerT.setText("0032");   
        gemeenteT.setBackground(Color.WHITE); 
        
        // panneel in 2 verdelen
        JPanel outer = new JPanel() ;
        outer.setLayout(new GridLayout(1,2));
        contentPane.add(outer) ;
        
        // linker panneel toevoegen aan outer
        JPanel left = new JPanel() ;
        left.setLayout(new GridBagLayout());
        left.setBackground(Color.WHITE);
        outer.add(left) ;
        
        // rechter panneel toevoegen aan outer
        JPanel right = new JPanel() ;
        right.setLayout(new GridBagLayout());
        right.setBackground(Color.WHITE);
        outer.add(right) ;
        
        // elementen toevoegen aan linker panneel
        GridBagConstraints gc = new GridBagConstraints() ;

        gc.anchor = GridBagConstraints.LINE_START ;
        gc.weightx = 1 ;
        gc.weighty = 1 ;
        gc.gridx = 0 ;
        gc.gridy = 0 ;
        voornaam.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(voornaam, gc) ;

        gc.gridy = 1 ;
        achternaam.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(achternaam, gc) ;

        gc.gridy = 2 ;
        email.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(email, gc) ;

        gc.gridy = 3 ;
        login.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(login, gc) ;

        gc.gridy = 4 ;
        paswoord.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(paswoord, gc) ;

        gc.gridy = 5 ;
        paswoordC.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(paswoordC, gc) ;

        gc.gridy = 6 ;
        telefoonnummer.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
        left.add(telefoonnummer, gc) ;

        gc.anchor = GridBagConstraints.LINE_START ;
        gc.fill = GridBagConstraints.HORIZONTAL ;
        gc.weightx = 4 ;
        gc.gridx = 1 ;
        gc.gridy = 0 ;
        left.add(voornaamT, gc) ;

        gc.gridy = 1 ;
        left.add(achternaamT, gc) ;

        gc.gridy = 2 ;
        left.add(emailT, gc) ;

        gc.gridy = 3 ;
        left.add(loginT, gc) ;
        
        gc.gridy = 4 ;
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200)); 
        Border border = BorderFactory.createLineBorder(new Color(76,79,83)); 
        UIManager.put("ToolTip.border", border);
        paswoordT.setToolTipText("Het paswoord moet minstens 8 tekens zijn en mag de login niet bevatten."); // tooltip in zwart en geel en een langere tijd blijft staan toevoegen aan het paswoordtextfield
        left.add(paswoordT, gc) ;

        gc.gridy = 5 ;
        left.add(paswoordCT, gc) ;

        gc.gridy = 6 ;
        left.add(telefoonnummerT, gc) ;

        gc.fill = GridBagConstraints.NONE ;
        gc.weighty = 5 ;
        gc.gridy = 7 ;
        JLabel empty = new JLabel() ;
        left.add(empty, gc) ; // leeg label toevoegen met extra gewicht die alles wat naar boven duwt zodat de gui mooier uitkomt
       
        // elementen toevoegen aan rechterpaneel
        gc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING ;
        gc.weightx = 1 ;
        gc.weighty = 1 ;
        gc.gridx = 0 ;
        gc.gridy = 0 ;
        right.add(straat,gc) ;

        gc.gridy = 1 ;
        right.add(postcode,gc) ;

        gc.gridy = 2 ;
        right.add(gemeente,gc) ;

        gc.fill = GridBagConstraints.HORIZONTAL ;
        gc.weightx = 4 ;
        gc.gridx = 1 ;
        gc.gridy = 0 ;
        right.add(straatT,gc) ;

        gc.anchor = GridBagConstraints.LINE_END ;
        gc.gridx = 2 ;
        right.add(huisnummerT, gc) ;

        gc.anchor = GridBagConstraints.LINE_START ;
        gc.gridx = 1 ;
        gc.gridy = 1 ;
        right.add(postcodeT, gc) ;

        gc.gridy = 2 ;
        right.add(gemeenteT, gc );

        gc.fill = GridBagConstraints.NONE ;
        registreer = new JButton("Account Aanmaken") ;
        registreer.addActionListener(this);
        registreer.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        registreer.setBackground(Color.DARK_GRAY);
        registreer.setForeground(Color.WHITE);
        registreer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registreer.setFocusable(false);
        gc.anchor = GridBagConstraints.PAGE_END ;
        gc.gridy = 3 ;
        gc.gridx = 1 ;
        gc.weighty = 9 ; 
        right.add(registreer, gc) ;

        gc.fill = GridBagConstraints.NONE ;
        gc.weighty = 3 ;
        gc.gridy = 4 ;
        aanmelden = new JButton("Terug naar startscherm.") ; // button aanmaken waarvan je de boord niet kan zien zodat het "clickable text" wordt
        aanmelden.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        aanmelden.setForeground(Color.BLUE);
        aanmelden.setFocusPainted(false);
        aanmelden.setMargin(new Insets(0,0,0,0));
        aanmelden.setContentAreaFilled(false);
        aanmelden.setBorderPainted(false);
        aanmelden.setOpaque(false);
        aanmelden.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aanmelden.addActionListener(this);
        right.add(aanmelden, gc) ;
       
       // We willen naast de controles die achteraf uitgevoerd worden ook zorgen dat de gebruiker geen foute gegevens ingeeft tijdens het invullen van de textfields.
       // Zo kan je onder andere bij je naam enkel letters ingeven en bij postcodes enkel cijfers ingeven.
       // Naast de restricties willen we telkens wanneer de gebruiker een postcode ingeeft zullen we de combobox vullen met de namen van de gemeenten die deze postcode hebben.
       // Dit kunnen 1 of meerdere gemeenten zijn, hiervoor moeten we enkele dingen uitvoeren!
        
       // iedere keer wanneer iets wordt getypt in het postcode textfield zal het de functie toongemeenten uitvoeren 
       postcodeT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               toonGemeenten(evt) ;
           }
       } ) ;
       
       // restricties voor tekstvelden, iedere keer wanneer iets wordt getypt in het textfield zal het de functie onlyLettersKeyTyped uitvoeren 
       voornaamT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyLettersKeyTyped(evt);
            }
        });
       
       achternaamT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyLettersKeyTyped(evt);
            }
        });
       
       straatT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyLettersKeyTyped(evt);
            }
        });
       
       // restricties voor tekstvelden, iedere keer wanneer iets wordt getypt in het textfield zal het de functie onlyNumbersKeyTyped uitvoeren
       telefoonnummerT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
       
       huisnummerT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
       
       postcodeT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
       
    }
    
    // De functie bekijkt welke character is getypt en indien het geen letter, spatie of min teken is zal het de letter "consumen" (Hierdoor word de actie niet uitgevoerd).
    public void onlyLettersKeyTyped(KeyEvent e)
    {
        char c = e.getKeyChar() ;
        if(!(Character.isLetter(c) || c == KeyEvent.VK_SPACE || c== KeyEvent.VK_MINUS) )
        {
           e.consume(); 
        }
    }
    
    // De functie bekijkt welke character is getypt en indien het geen cijfer is zal het de letter "consumen" (Hierdoor word de actie niet uitgevoerd).
    public void onlyNumbersKeyTyped(KeyEvent e)
    {
        char c = e.getKeyChar() ;
        if(!(Character.isDigit(c)) )
        {
           e.consume(); 
        }
    }
    
    // De functie zorgt ervoor dat het telkens de lijst in het begin leegt (indien men eerst een juiste postcode gaf en daarna een letter verwijdert is de lijst weer leeg).
    // Het eerste deel zorgt ervoor dat de tekst word aangevuld voordat het event gedaan is, dit is noodzakelijk omdat anders de lengte van het textfield verkeerd berekend word.
    // Daarna controleert het of het textfield uit 4 cijfers bestaat.
    // Indien dit zo is, zal het alle gemeentes uit de database halen met deze postcode en ze in de combobox steken die openspringt!
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
                robot.keyPress(KeyEvent.VK_TAB);    // Bij het testen merkten we dat mensen indien ze de juiste gemeente zagen ze op enter drukten ipv de muis te gebruiken.
                }catch (AWTException e) {           // Om dit tegen te gaan laten we java naar de combobox gaan door java tab te laten indrukken, hierdoor als ze op enter drukken zitten ze juist!
                    e.printStackTrace();
                }

           }
           else
               evt.consume() ;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == registreer) 
        {
            check = true ;
            // telefoonnummer controle is anders en wordt daarom verder uitgevoerd
            JTextField[] fields = new JTextField[]{voornaamT,achternaamT,emailT,loginT,paswoordT,paswoordCT,straatT,huisnummerT,postcodeT} ;
            String[] namen = new String[]{"Voornaam","Achternaam","E-mail","Login","Paswoord","PaswoordC","Straat","Huisnummer","Postcode"} ;
            
            while (check)
            {
                for(int i=0 ;i<9 ;i++)
                {
                    if (fields[i].getText().length() == 0)
                    {
                        JOptionPane.showMessageDialog(null, namen[i] + " moet ingevuld zijn!", "Error", JOptionPane.ERROR_MESSAGE);
                        check = false ;
                        break ;
                        
                    }
                }
                if (!check)
                {
                    break ;
                }
                // controleren of login al bestaat
                else if(!(db.controleerOfKlantloginBestaat(loginT.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Login bestaat al!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                      
                }
                // controleren of paswoord geldig is
                else if (paswoordT.getText().length() < 8)
                {
                    JOptionPane.showMessageDialog(null, "Paswoord moet minstens 8 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE); 
                    check = false ;
                    
                }
                else if (paswoordT.getText().contains(loginT.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Paswoord mag de login niet bevatten!", "Error", JOptionPane.ERROR_MESSAGE); 
                    check = false ;
                    
                }
                // controleren of paswoorden overeenkomen
                else if(!(paswoordT.getText().equals(paswoordCT.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Paswoorden komen niet overeen!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                    
                }
                 // controleren of email geldig is
                else if (!(emailT.getText().contains("@")))
                {
                    JOptionPane.showMessageDialog(null, "E-mail moet @ bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                    
                }
                else if (!(emailT.getText().contains(".")))
                {
                    JOptionPane.showMessageDialog(null, "E-mail moet .com, .be, ... bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                    
                }
                else if (!(db.controleerOfEmailBestaat(emailT.getText())))
                {
                    JOptionPane.showMessageDialog(null, "E-mail bestaat al!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                // database lengten controleren
                else if (voornaamT.getText().length() > 30) // database lengten controleren
                {
                    JOptionPane.showMessageDialog(null, "Voornaam kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else if (achternaamT.getText().length() > 30)
                {
                    JOptionPane.showMessageDialog(null, "Achternaam kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else if (emailT.getText().length() > 50) 
                {
                    JOptionPane.showMessageDialog(null, "E-mail kan niet meer dan 50 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false;
                }
                else if (paswoordT.getText().length() > 20)
                {
                    JOptionPane.showMessageDialog(null, "Wachtwoord kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else if (loginT.getText().length() > 20)
                {
                    JOptionPane.showMessageDialog(null, "Login kan niet meer dan 20 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else if (straatT.getText().length() > 30)
                {
                    JOptionPane.showMessageDialog(null, "Straat kan niet meer dan 30 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else if (huisnummerT.getText().length() > 11)
                {
                    JOptionPane.showMessageDialog(null, "Huistnummer kan niet meer dan 11 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else if (telefoonnummerT.getText().length() != 13 && telefoonnummerT.getText().length() != 4)
                {
                    JOptionPane.showMessageDialog(null, "Telefoonnummer moet 13 tekens bevatten!", "Error", JOptionPane.ERROR_MESSAGE);
                    check = false ;
                }
                else 
                {
                    // Indien alles goedgekeurd is maakt men een object aan van het type klant die alle gegevens bevat.
                    // Daarna voeren we de methode addklant uit die de klant in de database plaatst.
                    // De klant krijgt een mailtje toegestuurd en wordt doorverwezen naar het inlogscherm.
                    boolean firstNoZero = false ;
                    boolean codeBestaatal = true ;
                    Random random = new Random() ;
                    int first = 0 ;
                    String codeCombinatie = "" ;
                    ArrayList<String> codeCombinaties = db.getLijstCodeCombinaties();
                    Klant klant = new Klant(voornaamT.getText(),achternaamT.getText(),emailT.getText(),loginT.getText(),paswoordT.getText(),telefoonnummerT.getText(),straatT.getText(),Integer.parseInt(huisnummerT.getText()),Integer.parseInt(postcodeT.getText()),(String)gemeenteT.getSelectedItem()) ; 
                    klant.addKlant();
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
                    
                    db.nieuweRegistratieKortingsCode(codeCombinatie);
                    db.nieuweKortingBijKlantLogin(loginT.getText(), codeCombinatie);
                    JOptionPane.showMessageDialog(null, "Welkom bij Just Feed!" + "\n" + "U kunt zich nu aanmelden.", "Welkom", JOptionPane.INFORMATION_MESSAGE);
                    check = false ;
                    
                    super.dispose();
                    new SplashScreenMain2(emailT.getText(),voornaamT.getText(),codeCombinatie);
                }
                
                  
            }
            
        
        }
        if (evt.getSource() == aanmelden)
        {
          super.dispose();
          GUI_Startscherm guiWindow = new GUI_Startscherm();
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null);
          guiWindow.setResizable(false);
        }
    }
    
}
