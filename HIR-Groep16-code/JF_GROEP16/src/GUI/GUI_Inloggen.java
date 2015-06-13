package GUI;

import DATABASE_EN_ENTITEITEN.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI_Inloggen extends JFrame implements ActionListener
{
    private JTextField gebruikersnaamveld;
    private JPasswordField wachtwoordveld;
    private JButton klikHierBtn;
    private JButton aanmeld;
    DB db = new DB();
    private int counter = 0 ;
    
  

    //objecten van andere GUIs
    
    public GUI_Inloggen()
    {
        super("Just Feed");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        Container contentPane = getContentPane();
        JRootPane rootPane = getRootPane();

       
        
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0,1,50,50));      
        center.setBackground(Color.WHITE);
        center.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        JPanel tekst =  new JPanel();
        tekst.setLayout(new GridLayout(1,1,0,0));
        tekst.setBackground(Color.WHITE);
        
        JPanel fill =  new JPanel();
        tekst.setLayout(new GridLayout(2,1,0,5));
        fill.setBackground(Color.WHITE);
        
        JPanel left = new JPanel();
        left.setBackground(Color.WHITE);
        
        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        
        JPanel outer = new JPanel();
        outer.setLayout(new GridLayout(1,3));
        contentPane.add(outer);
      
        
        //intitialiseren van elementen
        gebruikersnaamveld = new JTextField(10);
        gebruikersnaamveld.setText("Gebruikersnaam");
       // gebruikersnaamveld.setFocusCycleRoot(false); 
        
        gebruikersnaamveld.setCaretPosition(0);
        gebruikersnaamveld.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    if (counter == 0) 
                    {
                        gebruikersnaamveld.setText(""); 
                    }
                        counter ++ ;
                    }
                    } ) ;
        gebruikersnaamveld.addMouseListener(new MouseAdapter() {
        @Override
                public void mouseClicked(MouseEvent e) {
                    if (counter == 0)
                    {
                        gebruikersnaamveld.setText("");
                    }
                        counter ++ ;
                }
                });
        
        wachtwoordveld = new JPasswordField(10);
        wachtwoordveld.setText("Wachtwoord");
        wachtwoordveld.setEchoChar((char)0) ;
        wachtwoordveld.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                wachtwoordveld.setText("") ;
                wachtwoordveld.setEchoChar('\u2022');
            }
            public void focusLost(FocusEvent e) {
               
            }
        });
        wachtwoordveld.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            wachtwoordveld.setText("");
            wachtwoordveld.setEchoChar('\u2022');
        }
        });
       
        aanmeld = new JButton("Inloggen");
        aanmeld.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        aanmeld.setBackground(Color.DARK_GRAY);
        aanmeld.setForeground(Color.WHITE);
        aanmeld.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aanmeld.setFocusable(false);
        aanmeld.addActionListener(this);
        rootPane.setDefaultButton(aanmeld);


        //klik hier label
        klikHierBtn = new JButton();
        klikHierBtn.setText(" Nog niet geregistreerd?");
        klikHierBtn.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        klikHierBtn.setForeground(Color.BLUE);
        klikHierBtn.setFocusPainted(false);
        klikHierBtn.setMargin(new Insets(0,0,0,0));
        klikHierBtn.setContentAreaFilled(false);
        klikHierBtn.setBorderPainted(false);
        klikHierBtn.setOpaque(false);
        klikHierBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        klikHierBtn.addActionListener(this);

        //elementen toevoegen aan centerpanel
        center.add(fill);
        tekst.add(gebruikersnaamveld);
        tekst.add(wachtwoordveld);
        center.add(tekst);
        center.add(aanmeld);
        center.add(klikHierBtn);

        //voeg panelen toe aan outer
        outer.add(left);
        outer.add(center);
        outer.add(right);

    }  
    
    public boolean controleerAdmin()
    {
        boolean admin = true;
        if (gebruikersnaamveld.getText().equals("admin"))
        {
            admin = false;
        }
        return admin;
    }
    
    public void actionPerformed(ActionEvent evt) {
     
      if (evt.getSource() == klikHierBtn) 
      {
          super.dispose();
          GUI_RegistrerenTA_Of_Klant guiWindow = new GUI_RegistrerenTA_Of_Klant() ;
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null);
          guiWindow.setResizable(false);
      }
      if (evt.getSource() == aanmeld)
      {    
          //retourneert false als de takeAway/klant al bestaat, true als takeAway/klant nog niet bestaat
          boolean klant = db.controleerOfKlantloginBestaat(gebruikersnaamveld.getText());
          boolean takeAway = db.controleerOfTakeAwayBestaat(gebruikersnaamveld.getText());
          boolean admin = controleerAdmin();
          boolean nogNietInsysteem = false;
          
          //controleert of gebruikersnaam al in systeem zit
          if ((klant == true) && (takeAway == true) && (admin == true))
          {
            //SOUND TOEVOEGEN
            //Toolkit.getDefaultToolkit().beep();
              
            JOptionPane.showMessageDialog(null, "Nog niet geregistreerd in systeem, gebruikersnaam juist?", "Error",JOptionPane.ERROR_MESSAGE);
            nogNietInsysteem = true;
          }
          
          //controleert inlogvoorwaarden klant
          if ((klant == false) && (db.getPaswoordVanKlantlogin(gebruikersnaamveld.getText()).equals(wachtwoordveld.getText())))
            {
                super.dispose();
                GUI_KlantInlogScherm guiWindow = new GUI_KlantInlogScherm(gebruikersnaamveld.getText()) ;
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false);
            }
          else if ((klant == false) && (!db.getPaswoordVanKlantlogin(gebruikersnaamveld.getText()).equals(wachtwoordveld.getText())))
          {
              JOptionPane.showMessageDialog(null, "Wachtwoord verkeerd!", "Error",JOptionPane.ERROR_MESSAGE);
          }
          
          //controleert inlogvoorwaarden takeAway
          if ((takeAway == false) && (db.getPaswoordVanTakeAway(gebruikersnaamveld.getText()).equals(wachtwoordveld.getText())))
            {
                super.dispose();
                GUI_TakeAwayInlogScherm guiWindow = new GUI_TakeAwayInlogScherm(gebruikersnaamveld.getText());
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false);
                
            }
          else if ((takeAway == false) && (!db.getPaswoordVanTakeAway(gebruikersnaamveld.getText()).equals(wachtwoordveld.getText())))
          {
              JOptionPane.showMessageDialog(null, "Wachtwoord verkeerd!", "Error",JOptionPane.ERROR_MESSAGE);
              Toolkit.getDefaultToolkit().beep();
          }
          
          //controleert inlogvoorwaarden Admin
          if ((admin == false) && ("admin123".equals(wachtwoordveld.getText())))
            {
                super.dispose(); 
                new SplashScreenMain();
                
//                GUI_AdminPanel guiWindow = new GUI_AdminPanel() ;
//                guiWindow.setVisible(true);
//                guiWindow.setLocationRelativeTo(null);
//                guiWindow.setResizable(false);
            }
          else if ((admin == false) && (!("admin123".equals(wachtwoordveld.getText()))))
          {
              JOptionPane.showMessageDialog(null, "Wachtwoord verkeerd!", "Error",JOptionPane.ERROR_MESSAGE);
          }
          
      }
    }
 
}
