package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI_RegistrerenTA_Of_Klant extends JFrame implements ActionListener
{
    // declaratie van de labels
    private JLabel label ;
    private JLabel labelImage ;
    
    // declaratie van de buttons
    private JButton klant;
    private JButton takeaway;
    
    // declaratie van de afbeeldingen
    private ImageIcon image;
    
    
    public GUI_RegistrerenTA_Of_Klant ()
    {
        super("Just Feed");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        Container contentPane = getContentPane();
        
        // onderste panel die volledig scherm inneemt
        JPanel outer = new JPanel();
        outer.setLayout(new BoxLayout (outer, BoxLayout.PAGE_AXIS));
        outer.setBackground(Color.WHITE);
        contentPane.add(outer);
        
        // registreren als klant button
        klant = new JButton("    Registreren als klant        ");
        klant.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        klant.setBackground(Color.DARK_GRAY);
        klant.setForeground(Color.WHITE);
        klant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        klant.setFocusable(false);
        klant.setAlignmentX(Component.CENTER_ALIGNMENT);
        klant.addActionListener(this);
        // afbeelding in de button
        try {
            Image img = ImageIO.read(getClass().getResource("personKlein.png"));
            klant.setIcon(new ImageIcon(img));
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        outer.add(Box.createRigidArea(new Dimension(0,100)));
        outer.add(klant);
        outer.add(Box.createRigidArea(new Dimension(0,75)));
        
        // registreren als takeaway button
        takeaway = new JButton("    Registreren als takeaway");
        takeaway.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        takeaway.setBackground(Color.DARK_GRAY);
        takeaway.setForeground(Color.WHITE);
        takeaway.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        takeaway.setFocusable(false);
        takeaway.setAlignmentX(Component.CENTER_ALIGNMENT);
        takeaway.addActionListener(this);
        // afbeelding in de button
        try {
            Image img = ImageIO.read(getClass().getResource("chef cook klein.jpg"));
            takeaway.setIcon(new ImageIcon(img));
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }

        outer.add(takeaway);
        
    }

    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        if (evt.getSource() == takeaway) 
        {
          super.dispose();
          GUI_TakeAwayRegistreren guiWindow = new GUI_TakeAwayRegistreren();
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null);
          guiWindow.setResizable(false);
        }
        
        if (evt.getSource() == klant) 
        {
          super.dispose();
          GUI_KlantRegistreren guiWindow = new GUI_KlantRegistreren();
          guiWindow.setVisible(true);
          guiWindow.setLocationRelativeTo(null);
          guiWindow.setResizable(false);
        }
    }
    
}
    
