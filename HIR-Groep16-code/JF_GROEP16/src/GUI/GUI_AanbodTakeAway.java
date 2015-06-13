package GUI;


import DATABASE_EN_ENTITEITEN.* ;
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
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;


public class GUI_AanbodTakeAway extends JFrame implements ActionListener {
    
    // declaratie van de boxen
    private Box aanbodbox;
    private Box takeawaybox;
    private Box takeawaybox2 ;
    private Box takeawaybox3 ;
    
    // declaratie kleur
    private Color newcolor ;
    
    // declaratie arraylisten
    private ArrayList<String> takeaways ;
    private ArrayList<JButton> houseButtonlist ;
    
    // declaratie klant
    private String klant ;
    
    // declaratie van de buttons
    private JButton terugbutton;
    private JButton Bfotohouse ;
    private JButton Bnaamtakeaway5 ;
    private JButton naamtakeaway ;
    private JButton naamtakeaway2 ;
    private JButton naamtakeaway3 ;
    private JButton naamtakeaway4 ;
    
    private String naamawardgerecht ;
    private String naamawardTakeaway;
    private int menuid2 ;
    
    // database
    DB db  = new DB();

    public GUI_AanbodTakeAway(String k) {
        
        super("Just-Feed");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container frame = getContentPane();
        
        
        // initialisatie 
        newcolor = new Color(76,117,154) ;
        houseButtonlist = new ArrayList<JButton>() ;
        klant = k ;
        
        //Maak outer
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.white);

        GridBagConstraints c = new GridBagConstraints();
        frame.add(outer);

        //maak panel aan met knoppen
        JPanel aanbod = new JPanel();
        aanbod.setLayout(new GridBagLayout());
        //aanbod.setPreferredSize(new Dimension(550, 370));  dit weg doen om scroll te laten werken tot oneindig
        aanbod.setBackground(Color.white);

        //scroll
        JScrollPane scroll = new JScrollPane(aanbod); //scroll hangt nu aan een panel
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(null);

        //maak aanbodBOX aan
        aanbodbox = Box.createHorizontalBox();
        aanbodbox.setBorder(BorderFactory.createTitledBorder("AANBOD TAKE-AWAY'S"));
        aanbodbox.setPreferredSize(new Dimension(530, 312));
        aanbodbox.add(scroll, c);

        // voeg deze box toe aan outer 
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 2;
        c.insets = new Insets(0,0,10,0);
        outer.add(aanbodbox, c);

        //maak panel van takeway
        
        JPanel takeaway = new JPanel();
        takeaway.setLayout(new GridBagLayout());
        takeaway.setBackground(Color.white);
        
        // bij deze  PANEL FOTO1

        JButton Bfotoster = new JButton("");
        Bfotoster.setPreferredSize(new Dimension(50, 50));
        Bfotoster.setBackground(Color.white);
        
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
        Border border = BorderFactory.createLineBorder(new Color(76, 79, 83));
        UIManager.put("ToolTip.border", border);
        Bfotoster.setToolTipText("De Take-Away die de voorbije maand de hoogste gemiddelde score behaalde op zijn gerechten.");
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, -120, 0, 0);
        takeaway.add(Bfotoster, c);
            
        try 
        {
            Image img = ImageIO.read(getClass().getResource("ster1.jpg"));
            Bfotoster.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        naamtakeaway = new JButton(db.getUserChoiceAwardKort());
        naamtakeaway.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        naamtakeaway.setForeground(newcolor);
        naamtakeaway.setFocusPainted(false);
        naamtakeaway.setMargin(new Insets(0,0,0,0));
        naamtakeaway.setContentAreaFilled(false);
        naamtakeaway.setBorderPainted(false);
        naamtakeaway.setOpaque(false);
        naamtakeaway.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        naamtakeaway.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        takeaway.add(naamtakeaway, c);

        // voeg takeaway toe in BOX
        takeawaybox = Box.createHorizontalBox();
        takeawaybox.setBorder(BorderFactory.createTitledBorder("Users-Choice"));
        //takeawaybox.setPreferredSize(new Dimension(530, 130));
        takeawaybox.add(takeaway);

        // voeg box toe aan panel aanbod
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        aanbod.add(takeawaybox, c);
        
        //panel 2 met FOTO 2
        
        //maak panel van takeway
        JPanel takeaway2 = new JPanel();
        takeaway2.setLayout(new GridBagLayout());
        takeaway2.setBackground(Color.white);

        // bij deze  PANEL FOTO2
        JButton Bfotohotitem = new JButton("");
        Bfotohotitem.setPreferredSize(new Dimension(50, 50));
        Bfotohotitem.setBackground(Color.white);
        
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
        Border border2 = BorderFactory.createLineBorder(new Color(76, 79, 83));
        UIManager.put("ToolTip.border", border2);
        Bfotohotitem.setToolTipText("TakeAway met het meest bestelde gerecht van de maand.");
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, -120, 0, 0);
        takeaway2.add(Bfotohotitem, c);

        try {
            Image img = ImageIO.read(getClass().getResource("hotitem.png"));
            Bfotohotitem.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        naamawardgerecht = db.getAwardGerechtNaamOpMoment();
        menuid2 = Integer.parseInt(db.getAwardGerechtMenuIDOpMoment());
        naamawardTakeaway = db.getNaamTakeAwayBijMenuID(menuid2);
        naamtakeaway2 = new JButton(naamawardTakeaway);
        naamtakeaway2.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        naamtakeaway2.setForeground(newcolor);
        naamtakeaway2.setFocusPainted(false);
        naamtakeaway2.setMargin(new Insets(0,0,0,0));
        naamtakeaway2.setContentAreaFilled(false);
        naamtakeaway2.setBorderPainted(false);
        naamtakeaway2.setOpaque(false);
        naamtakeaway2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        naamtakeaway2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        takeaway2.add(naamtakeaway2, c);

        // voeg takeaway toe in BOX
        takeawaybox2 = Box.createHorizontalBox();
        takeawaybox2.setBorder(BorderFactory.createTitledBorder("Hot-Item"));
        takeawaybox2.add(takeaway2);

        // voeg box toe aan panel aanbod
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        aanbod.add(takeawaybox2, c) ;
        
        
        //panel 3 met foto3
        
        //maak panel van takeway
        JPanel takeaway3 = new JPanel();
        takeaway3.setLayout(new GridBagLayout());
        takeaway3.setBackground(Color.white);

        // bij deze  PANEL FOTO3
        JButton Bfotoeuro = new JButton("");
        Bfotoeuro.setPreferredSize(new Dimension(50, 50));
        Bfotoeuro.setBackground(Color.white);
        Bfotoeuro.setToolTipText("");
        
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
        Border border3 = BorderFactory.createLineBorder(new Color(76, 79, 83));
        UIManager.put("ToolTip.border", border3);
        Bfotoeuro.setToolTipText("De Take-Away die de voorbije maand de hoogste commisie opbracht.");
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, -120, 0, 0);
        takeaway3.add(Bfotoeuro, c);

        try {
            Image img = ImageIO.read(getClass().getResource("euro.jpg"));
            Bfotoeuro.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        naamtakeaway3 = new JButton(db.getJFFeederAwardKort());
        naamtakeaway3.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        naamtakeaway3.setForeground(newcolor);
        naamtakeaway3.setFocusPainted(false);
        naamtakeaway3.setMargin(new Insets(0,0,0,0));
        naamtakeaway3.setContentAreaFilled(false);
        naamtakeaway3.setBorderPainted(false);
        naamtakeaway3.setOpaque(false);
        naamtakeaway3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        naamtakeaway3.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        takeaway3.add(naamtakeaway3, c);

        // voeg takeaway toe in BOX
        takeawaybox3 = Box.createHorizontalBox();
        takeawaybox3.setBorder(BorderFactory.createTitledBorder("Just-Feeder"));
        takeawaybox3.add(takeaway3);

        // voeg box toe aan panel aanbod
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        aanbod.add(takeawaybox3, c);

        //panel 4 met foto4
        //maak panel van takeway
        JPanel takeaway4 = new JPanel();
        takeaway4.setLayout(new GridBagLayout());
        takeaway4.setBackground(Color.white);

        // bij deze  PANEL FOTO4
        JButton Bfotobestseller = new JButton("");
        Bfotobestseller.setPreferredSize(new Dimension(50, 50));
        Bfotobestseller.setBackground(Color.white);
        
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
        Border border4 = BorderFactory.createLineBorder(new Color(76, 79, 83));
        UIManager.put("ToolTip.border", border4);
        Bfotobestseller.setToolTipText("De Take-Away met het meest aantal orders gedurende de voorbije maand.");
        
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, -120, 0, 0);
        takeaway4.add(Bfotobestseller, c);

        try {
            Image img = ImageIO.read(getClass().getResource("bestseller.jpg"));
            Bfotobestseller.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        naamtakeaway4 = new JButton(db.getBestsellerAwardKort());
        naamtakeaway4.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        naamtakeaway4.setForeground(newcolor);
        naamtakeaway4.setFocusPainted(false);
        naamtakeaway4.setMargin(new Insets(0,0,0,0));
        naamtakeaway4.setContentAreaFilled(false);
        naamtakeaway4.setBorderPainted(false);
        naamtakeaway4.setOpaque(false);
        naamtakeaway4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        naamtakeaway4.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        takeaway4.add(naamtakeaway4, c);

        // voeg takeaway toe in BOX
        takeawaybox3 = Box.createHorizontalBox();
        takeawaybox3.setBorder(BorderFactory.createTitledBorder("Best-Seller"));
        takeawaybox3.add(takeaway4);

        // voeg box toe aan panel aanbod
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        aanbod.add(takeawaybox3, c);
        
        //panel 5 en de REST met foto5
        
        takeaways = db.getAlleTakeAways() ;
        GridBagConstraints abc = new GridBagConstraints();
        abc.gridy =4;
        for(int i = 0 ; i < takeaways.size() ; i++)
        {
                //maak panel van takeway
                JPanel takeaway5 = new JPanel();
                takeaway5.setLayout(new GridBagLayout());
                takeaway5.setBackground(Color.white);

                // bij deze  PANEL FOTO5
                Bfotohouse = new JButton("");
                Bfotohouse.setPreferredSize(new Dimension(50, 50));
                Bfotohouse.setBackground(Color.white);
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 1;
                c.weighty = 1;
                c.fill = GridBagConstraints.NONE;
                c.anchor = GridBagConstraints.LINE_START;
                c.insets = new Insets(0, 40, 0, 0);
                takeaway5.add(Bfotohouse, c);

                try {
                    Image img = ImageIO.read(getClass().getResource("House1.png"));
                    Bfotohouse.setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Bnaamtakeaway5 = new JButton(takeaways.get(i));
                Bnaamtakeaway5.setFont(new Font("Lucida Bright", Font.BOLD ,14));
                Bnaamtakeaway5.setForeground(newcolor);
                Bnaamtakeaway5.setFocusPainted(false);
                Bnaamtakeaway5.setMargin(new Insets(0,0,0,0));
                Bnaamtakeaway5.setContentAreaFilled(false);
                Bnaamtakeaway5.setBorderPainted(false);
                Bnaamtakeaway5.setOpaque(false);
                Bnaamtakeaway5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Bnaamtakeaway5.addActionListener(this);
                houseButtonlist.add(Bnaamtakeaway5);
                
                c.gridx = 1;
                c.gridy = 0;
                c.weightx = 1;
                c.weighty = 1;
                takeaway5.add(Bnaamtakeaway5, c);

                // voeg takeaway toe in BOX
                takeawaybox3 = Box.createHorizontalBox();
                takeawaybox3.setBorder(BorderFactory.createTitledBorder("Store"));
                takeawaybox3.add(takeaway5, c);

                // voeg box toe aan panel aanbod
                abc.gridx = 0;
                abc.gridy ++;
                abc.weightx = 1;
                abc.weighty = 1;
                abc.fill = GridBagConstraints.HORIZONTAL;

                aanbod.add(takeawaybox3, abc);
     
        }
        
        
        terugbutton = new JButton("Terug naar inlogscherm");
        terugbutton.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        terugbutton.setBackground(Color.DARK_GRAY);
        terugbutton.setForeground(Color.WHITE);
        terugbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        terugbutton.setFocusable(false);
        terugbutton.addActionListener(this);
        
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(-20,0,0,37) ;
        outer.add(terugbutton,c);
        
       
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terugbutton)
        {
            super.dispose();
            GUI_KlantInlogScherm guiWindow = new GUI_KlantInlogScherm(klant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        for (JButton b : houseButtonlist)
        {
            if (e.getSource() == b)
            {
                super.dispose();
                GUI_MenuOverzicht2 menu = new GUI_MenuOverzicht2(b.getText(),klant);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                menu.setResizable(false);
                
            }
        }
        if(e.getSource() == naamtakeaway2)
        {
            super.dispose();
                GUI_MenuOverzicht2 menu = new GUI_MenuOverzicht2(naamtakeaway2.getText(),klant);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                menu.setResizable(false);
            
        }
        if(e.getSource() == naamtakeaway3)
        {
            super.dispose();
                GUI_MenuOverzicht2 menu = new GUI_MenuOverzicht2(naamtakeaway3.getText(),klant);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                menu.setResizable(false);
            
        }
        if(e.getSource() == naamtakeaway4)
        {
            super.dispose();
                GUI_MenuOverzicht2 menu = new GUI_MenuOverzicht2(naamtakeaway4.getText(),klant);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                menu.setResizable(false);
            
        }
        if(e.getSource() == naamtakeaway)
        {
            super.dispose();
                GUI_MenuOverzicht2 menu = new GUI_MenuOverzicht2(naamtakeaway.getText(),klant);
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                menu.setResizable(false);
            
        }
        
            
        
            
        
            
    }

    
   

    
}
