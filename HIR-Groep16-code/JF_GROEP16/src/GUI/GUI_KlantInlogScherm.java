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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GUI_KlantInlogScherm extends JFrame implements ActionListener {
    
    private JButton button1 ;
    private JButton button2 ;
    private JButton button3 ;
    private JButton button4 ;
    private JButton button5 ;
    private String ingelogdeKlant;
    
    private JComboBox box ;
    private ArrayList<String> array ;
    
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize");
    
     DB db = new DB() ;

    public GUI_KlantInlogScherm(String ingelogdeKlant) {
        super("Just-Feed");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.ingelogdeKlant = ingelogdeKlant;

        array = new ArrayList() ;
        
        Container frame = getContentPane();

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());

        frame.add(panel);
        GridBagConstraints c = new GridBagConstraints();

        button1 = new JButton("");
        button1.setPreferredSize(new Dimension(50, 40));
        button1.setBackground(Color.WHITE);
        button1.setFocusable(false);
        button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1 ;
        c.weighty = 1 ;
        c.insets = new Insets(-80,300,0,0);
        panel.add(button1, c);

        button2 = new JButton("");
        button2.setPreferredSize(new Dimension(50, 40));
        button2.setBackground(Color.WHITE);
        button2.setFocusable(false);
        button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button2.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1 ;
        c.weighty = 1 ;
        c.insets = new Insets(-80,520,0,0);
        panel.add(button2, c);
        
        button5 = new JButton("");
        button5.setPreferredSize(new Dimension(50, 40));
        button5.setBackground(Color.WHITE);
        button5.setFocusable(false);
        button5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button5.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1 ;
        c.weighty = 1 ;
        c.insets = new Insets(-80, 410, 0 , 0);
        panel.add(button5, c);
        

        button3 = new JButton("      Order samenstellen");
        button3.setPreferredSize(new Dimension(250, 60));
        button3.setBackground(Color.DARK_GRAY);
        button3.setFont(new Font("Lucida Bright", Font.BOLD, 12));
        button3.setForeground(Color.WHITE);
        button3.setFocusable(false);
        button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button3.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-90,0,0,0);
        panel.add(button3, c);

        button4 = new JButton("  Aanbod Takeway's bekijken");
        button4.setPreferredSize(new Dimension(250, 60));
        button4.setFont(new Font("Lucida Bright", Font.BOLD, 11));
        button4.setBackground(Color.DARK_GRAY);
        button4.setForeground(Color.WHITE);
        button4.setFocusable(false);
        button4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button4.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
         c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-140, 0,0,0);
        panel.add(button4, c);

       
        
        try {
            Image img = ImageIO.read(getClass().getResource("uitloggen.jpg"));
            button2.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        try {
            Image img = ImageIO.read(getClass().getResource("personKlein.png"));
            button1.setIcon(new ImageIcon(img));
            
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        try {
            Image img = ImageIO.read(getClass().getResource("ordersamenstellen.png"));
            button3.setIcon(new ImageIcon(img));
            
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        try {
            Image img = ImageIO.read(getClass().getResource("iBooks2b.png"));
            button4.setIcon(new ImageIcon(img));
            
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        try {
           Image img = ImageIO.read(getClass().getResource("korting.png"));
           button5.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
       }
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1)
        {
            super.dispose();
            GUI_MijnProfielKlant guiWindow = new GUI_MijnProfielKlant(ingelogdeKlant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if(e.getSource() == button2)
        {
             super.dispose();
             GUI_Inloggen guiWindow = new GUI_Inloggen();
             guiWindow.setVisible(true);
             guiWindow.setLocationRelativeTo(null);
             guiWindow.setResizable(false);
            
        }
        
        if(e.getSource() == button3 )
        {
            super.dispose();
            GUI_Leveringsadres guiWindow = new GUI_Leveringsadres(ingelogdeKlant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        
        if(e.getSource() == button4)
        {
            super.dispose();
            GUI_AanbodTakeAway guiWindow = new GUI_AanbodTakeAway(ingelogdeKlant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if(e.getSource() == button5)
        {
             boolean bestaatal1 = false ;
               
               JPanel nieuw1 = new JPanel() ;
               nieuw1.setLayout(new GridBagLayout());
               
               JLabel kortingscode = new JLabel("Uw kortingscodes") ;
               box = new JComboBox();
               box.setPreferredSize(new Dimension(200, 20));
               box.setBackground(Color.white);
               box.addItem("");
               
               
               
               array = db.getKortingcodeBijKlantLogin(ingelogdeKlant);
               
               for (int i = 0; i < array.size(); i++) 
               {
                    box.addItem(array.get(i));
               }
               
               GridBagConstraints gc = new GridBagConstraints() ;
               
               
               gc.anchor = GridBagConstraints.FIRST_LINE_START ;
               gc.weightx = 1 ;
               gc.weighty = 1 ;
               gc.gridx = 0 ;
               gc.gridy = 0 ;
               nieuw1.add(kortingscode, gc) ;
               gc.gridy = 1 ;
               nieuw1.add(box, gc) ;
               
               
               
               UIManager.put("OptionPane.minimumSize", dim) ;
               int result1 = JOptionPane.showConfirmDialog(null, nieuw1, "Uw kortingscodes",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
               
        }
    }
    

}
