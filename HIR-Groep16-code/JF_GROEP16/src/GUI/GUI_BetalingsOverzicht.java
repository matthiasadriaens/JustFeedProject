package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GUI_BetalingsOverzicht extends JFrame implements ActionListener {
    
    private Box betalingbox ;
    private Box keuzebox ;
    private Box naasterbox ;
    private JRadioButton Visabutton ;
    private JRadioButton Bankcontactbutton ;
    private JRadioButton Mastercardbutton ;
    private GridBagConstraints c ;
    private JPanel onderpanel ;
    private JPanel naasterpanel ; 
    private JLabel tekst1 ;
    private Container frame ;
    private JButton betaalbutton ; 
    
    private JTextField naamkaartT  ;
    private JTextField kaartnummerT ;
    private JTextField codeT ;
    private JComboBox  jaar ;
    private JComboBox maand ; 
    
    private CardCheck object ;
    
    private String klant ;
    private Bestelling bestelling ;
    private Double prijs ;
    private DecimalFormat f = new DecimalFormat("##.00");
    
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize") ;
    
    
    public GUI_BetalingsOverzicht(String k, Bestelling b, Double p) 
    {
        super("Just-Feed");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame = getContentPane();
        klant = k ;
        bestelling = b ;
        prijs = p ;

        //Maak outer
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.white);

        c = new GridBagConstraints();
        frame.add(outer);
        
        
        //maak boven panel met labels 
        JPanel bovenpanel = new JPanel() ;
        bovenpanel.setLayout(new GridBagLayout());
        bovenpanel.setBackground(Color.white);
        
        
        
        JLabel tekst2 = new JLabel("Referentie van de bestelling:");
        tekst2.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst2.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-5, 0, 0, 0);
        //bovenpanel.add(tekst2, c);
        
        Random random = new Random() ;
        JLabel tekst3 = new JLabel(random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) ) ;
        tekst3.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst3.setForeground(Color.DARK_GRAY);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0 , 0, 0, 0);
       // bovenpanel.add(tekst3, c);
        
        JLabel tekst4 = new JLabel("Totale kostprijs:");
        tekst4.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst4.setForeground(Color.DARK_GRAY);
        c.gridx = 0 ;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, -87, 0, 0);
        bovenpanel.add(tekst4, c);
        
        JLabel tekst5 = new JLabel("€" + f.format(prijs));
        tekst5.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst5.setForeground(Color.DARK_GRAY);
        c.gridx =1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        bovenpanel.add(tekst5, c);
        
        JLabel tekst6 = new JLabel("Begunstigde:");
        tekst6.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst6.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, -109, 0, 0);
        bovenpanel.add(tekst6, c);
        
        JLabel tekst7 = new JLabel("NV Just-Feed ");
        tekst7.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst7.setForeground(Color.DARK_GRAY);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        bovenpanel.add(tekst7, c);
        
        // maak box 
        betalingbox = Box.createHorizontalBox();
        betalingbox.setBorder(BorderFactory.createTitledBorder("Betaling"));
        betalingbox.setPreferredSize(new Dimension(550, 370));
        
        // voeg panel toe aan box 
        
        betalingbox.add(bovenpanel) ;
        
        // box toevoegen aan outer
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,15,0,15) ;
        outer.add(betalingbox, c);
        // betaalbutton
        
        betaalbutton = new JButton("Betalen");
       // betaalbutton.setPreferredSize(new Dimension(200, 30));
        betaalbutton.setFont(new Font("Lucida Bright", Font.BOLD ,14)) ;
        betaalbutton.setForeground(Color.WHITE);
        betaalbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        betaalbutton.setFocusable(false) ;
        betaalbutton.setBackground(Color.DARK_GRAY);
        betaalbutton.addActionListener(this);
        
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL ;
        c.insets = new Insets(0,400,0,15) ;
        outer.add(betaalbutton,c) ;
        
        // 2de panel RADIO BUTTONS 
        onderpanel = new JPanel() ;
        onderpanel.setLayout(new GridBagLayout());
        onderpanel.setBackground(Color.white);
        
        Visabutton = new JRadioButton(); 
        Bankcontactbutton = new JRadioButton();
        Mastercardbutton = new JRadioButton();
        
        Visabutton.setBackground(Color.white);
        Mastercardbutton.setBackground(Color.white);
        Bankcontactbutton.setBackground(Color.white);
        
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(Visabutton);
        group.add(Bankcontactbutton);
        group.add(Mastercardbutton);
        
        
        // add buttons to panel
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-140, -230, 0, 0);
        c.fill = GridBagConstraints.NONE;
        onderpanel.add(Visabutton,c) ;
        
        JButton BfotoVisa = new JButton("");
        BfotoVisa.setPreferredSize(new Dimension(40, 25));
        BfotoVisa.setBackground(Color.white);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(-140, -160, 0, 0);
        onderpanel.add(BfotoVisa, c);
        
        try {
            Image img = ImageIO.read(getClass().getResource("visa.jpg"));
            BfotoVisa.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();}
        
        
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-10, -230, 0, 0);
        onderpanel.add(Bankcontactbutton,c) ;
        
        JButton BfotoMastercard = new JButton("");
        BfotoMastercard.setPreferredSize(new Dimension(40, 25));
        BfotoMastercard.setBackground(Color.white);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(-10, -160, 0, 0);
        onderpanel.add(BfotoMastercard, c);
        
        try {
            Image img = ImageIO.read(getClass().getResource("bankcontact.jpg"));
            BfotoMastercard.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(120, -230, 0, 0);
        onderpanel.add(Mastercardbutton,c) ;
        
        JButton BfotoBankcontact = new JButton("");
        BfotoBankcontact.setPreferredSize(new Dimension(40, 25));
        BfotoBankcontact.setBackground(Color.white);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(120, -160, 0, 0);
        onderpanel.add(BfotoBankcontact, c);
        
        try {
            Image img = ImageIO.read(getClass().getResource("mastercard.jpg"));
            BfotoBankcontact.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //maak box;
        keuzebox = Box.createHorizontalBox();
        keuzebox.setBorder(BorderFactory.createTitledBorder("Betaalwijze"));
        keuzebox.setPreferredSize(new Dimension(550, 360));
        
        //add panel in box 
        keuzebox.add(onderpanel) ;
        
        //add box in outer ; 
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 15,0 ,15) ;
        c.fill = GridBagConstraints.BOTH ;
        outer.add(keuzebox,c) ;
        c.insets = new Insets(0,-200,0,0) ;
       
        
        Visabutton.addActionListener(this);
        Bankcontactbutton.addActionListener(this);
        Mastercardbutton.addActionListener(this);
        
        
        // new panel naasterpanel + label test
        
        naasterpanel = new JPanel();
        naasterpanel.setLayout(new GridBagLayout());
        naasterpanel.setPreferredSize(new Dimension(150,100));
        naasterpanel.setBackground(Color.white);
        
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH ;
        onderpanel.add(naasterpanel,c) ;
      
        
        Visabutton.setSelected(true);
         // IF VOOR TE SELECTEREN 
            //remove all components in panel.
            naasterpanel.removeAll(); 
            // refresh the panel.
            naasterpanel.updateUI(); 
            
            //1
            JLabel naamkaartL = new JLabel("Naam Kaarthouders:");
            naamkaartL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            naamkaartL.setForeground(Color.DARK_GRAY);
            
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(naamkaartL, c);
            
            
            naamkaartT = new JTextField();
            naamkaartT.setForeground(Color.DARK_GRAY);
            naamkaartT.setPreferredSize(new Dimension(200,20));
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(naamkaartT, c);
            
            //2
            
            JLabel kaartnummerL = new JLabel("Kaartnummer:");
            kaartnummerL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            kaartnummerL.setForeground(Color.DARK_GRAY);
            
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -43, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(kaartnummerL, c);
            
            kaartnummerT = new JTextField();
            kaartnummerT.setForeground(Color.DARK_GRAY);
            kaartnummerT.setPreferredSize(new Dimension(200,20));
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(kaartnummerT, c);
            
            kaartnummerT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
            
            
            //3 Vervaldatum (mm/jjjj)*  :
            JLabel vervaldatumL = new JLabel("Vervaldatum (mm/jjjj):");
            vervaldatumL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            vervaldatumL.setForeground(Color.DARK_GRAY);
            
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 21, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(vervaldatumL, c);
            
             maand = new JComboBox() ;
            maand.setPreferredSize(new Dimension(50,20));
            maand.setBackground(Color.white);
            for(int i = 1 ; i < 13 ; i++)
            {
                maand.addItem(i);
            }
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -150, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(maand, c);
             
            jaar = new JComboBox();
            jaar.setBackground(Color.white);
            jaar.setPreferredSize(new Dimension(145, 20));
            for (int i = 2014; i < 2050; i++) {
                jaar.addItem(i);
            }
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0,50, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(jaar, c);

            //4
            JLabel codeL = new JLabel("Kaart VerificatieCode:");
            codeL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            codeL.setForeground(Color.DARK_GRAY);

            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0,12, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(codeL, c);

            codeT = new JTextField();
            codeT.setForeground(Color.DARK_GRAY);
            codeT.setPreferredSize(new Dimension(50, 20));
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -151, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(codeT, c);
            
            codeT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
            
            
            
            c.gridx = 1;
            c.gridy = 4;
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.NONE;
            c.insets = new Insets(0, 0, 0, 0);
            
            
            frame.revalidate(); 
        
         
        
        
    }

    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        
        if (evt.getSource() == betaalbutton)
        {
            boolean allesIngevuld = false ;
            boolean betaald = false;
            
            if (Visabutton.isSelected() || Mastercardbutton.isSelected())
            {
               if(!(naamkaartT.getText().isEmpty()) && !(kaartnummerT.getText().isEmpty()) && !(codeT.getText().isEmpty()) )
               {
                    object = new CardCheck();
                    betaald = object.checkNummer(kaartnummerT.getText()) ;
                    allesIngevuld = true ;
               } 
            }
            else
            {
               if(!(naamkaartT.getText().isEmpty()) && !(kaartnummerT.getText().isEmpty()) )
               {
                    object = new CardCheck();
                    betaald = object.checkNummer(kaartnummerT.getText()) ; 
                    allesIngevuld = true ;
               }  
            }
            
            if(!allesIngevuld)
            {
                UIManager.put("OptionPane.minimumSize", dim) ;
                JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            else if(!betaald)
            {
                UIManager.put("OptionPane.minimumSize", dim) ;
                JOptionPane.showMessageDialog(null, "Geen geldige kaart nummer!", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            else
            {
                UIManager.put("OptionPane.minimumSize", dim) ;
                JOptionPane.showMessageDialog(null, "Uw bestelling is succesvol ontvangen en doorgegeven!" + "\n" + "Laat ons weten of u tevreden was!", "Welkom", JOptionPane.INFORMATION_MESSAGE);
            
                super.dispose();
                GUI_KlantInlogScherm guiWindow = new GUI_KlantInlogScherm(klant);
                guiWindow.setVisible(true);
                guiWindow.setLocationRelativeTo(null);
                guiWindow.setResizable(false); 
                
                 bestelling.addBestelling(prijs); 
            }
                
            
            
       }
            
            
        
        if(evt.getSource() == Visabutton  || evt.getSource() == Mastercardbutton)
        {     
            //remove all components in panel.
              naasterpanel.removeAll(); 
            // refresh the panel.
            naasterpanel.updateUI(); 
            
            //1
            JLabel naamkaartL = new JLabel("Naam Kaarthouders:");
            naamkaartL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            naamkaartL.setForeground(Color.DARK_GRAY);
            
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(naamkaartL, c);
            
            
            naamkaartT = new JTextField();
            naamkaartT.setForeground(Color.DARK_GRAY);
            naamkaartT.setPreferredSize(new Dimension(200,20));
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(naamkaartT, c);
            
            //2
            
            JLabel kaartnummerL = new JLabel("Kaartnummer:");
            kaartnummerL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            kaartnummerL.setForeground(Color.DARK_GRAY);
            
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -43, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(kaartnummerL, c);
            
            kaartnummerT = new JTextField();
            kaartnummerT.setForeground(Color.DARK_GRAY);
            kaartnummerT.setPreferredSize(new Dimension(200,20));
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(kaartnummerT, c);
            
            kaartnummerT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
            
            //3 Vervaldatum (mm/jjjj)*  :
            JLabel vervaldatumL = new JLabel("Vervaldatum (mm/jjjj):");
            vervaldatumL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            vervaldatumL.setForeground(Color.DARK_GRAY);
            
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 21, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(vervaldatumL, c);
            
            maand = new JComboBox();
            maand.setPreferredSize(new Dimension(50, 20));
            maand.setBackground(Color.white);
            for (int i = 1; i < 13; i++) {
                maand.addItem(i);
            }
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -150, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(maand, c);
            
            jaar = new JComboBox();
            jaar.setPreferredSize(new Dimension(140, 20));
            jaar.setBackground(Color.white);
            for (int i = 2014; i < 2050; i++) {
                jaar.addItem(i);
            }
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 50, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(jaar, c);
            
            //4
            JLabel codeL = new JLabel("Kaart VerificatieCode:");
            codeL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            codeL.setForeground(Color.DARK_GRAY);

            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 12, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(codeL, c);

            codeT = new JTextField();
            codeT.setForeground(Color.DARK_GRAY);
            codeT.setPreferredSize(new Dimension(50, 20));
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -151, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(codeT, c);

            codeT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
           
            c.gridx = 1;
            c.gridy = 4;
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.NONE;
            c.insets = new Insets(0, 100, 0, 0);
            

            frame.revalidate();
        }
        
       
        if(evt.getSource() == Bankcontactbutton)
        {
            naasterpanel.removeAll();
            // refresh the panel.
            naasterpanel.updateUI();

            //1
            JLabel naamkaartL = new JLabel("Naam Kaarthouders:");
            naamkaartL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            naamkaartL.setForeground(Color.DARK_GRAY);

            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(naamkaartL, c);

            naamkaartT = new JTextField();
            naamkaartT.setForeground(Color.DARK_GRAY);
            naamkaartT.setPreferredSize(new Dimension(200, 20));
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(naamkaartT, c);

            //2
            JLabel kaartnummerL = new JLabel("Kaartnummer:");
            kaartnummerL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            kaartnummerL.setForeground(Color.DARK_GRAY);

            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -43, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(kaartnummerL, c);

            kaartnummerT = new JTextField();
            kaartnummerT.setForeground(Color.DARK_GRAY);
            kaartnummerT.setPreferredSize(new Dimension(200, 20));
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 0, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(kaartnummerT, c);
            
            kaartnummerT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });

            //3 Vervaldatum (mm/jjjj)*  :
            JLabel vervaldatumL = new JLabel("Vervaldatum (mm/jjjj):");
            vervaldatumL.setFont(new Font("Lucida Bright", Font.BOLD, 14));
            vervaldatumL.setForeground(Color.DARK_GRAY);

            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 21, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(vervaldatumL, c);

            JComboBox maand = new JComboBox();
            maand.setPreferredSize(new Dimension(50, 20));
            maand.setBackground(Color.white);
            
                    
            for (int i = 1; i < 13; i++) {
                maand.addItem(i);
            }
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, -150, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(maand, c);

            JComboBox jaar = new JComboBox();
            jaar.setPreferredSize(new Dimension(140, 20));
            jaar.setForeground(Color.black);
            jaar.setBackground(Color.WHITE);
            
            for (int i = 2014; i < 2050; i++) {
                jaar.addItem(i);
                
            }
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 50, 0, 0);
            c.fill = GridBagConstraints.NONE;
            naasterpanel.add(jaar, c);
            
            
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.NONE;
            c.insets = new Insets(0, 100, 0, 0);
           

            frame.revalidate();
        }
            
    }
    public void onlyNumbersKeyTyped(KeyEvent e)
    {
        char c = e.getKeyChar() ;
        if(!(Character.isDigit(c)) )
        {
           e.consume(); 
        }
    }
}
