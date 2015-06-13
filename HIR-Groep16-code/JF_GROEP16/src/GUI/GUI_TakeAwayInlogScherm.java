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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GUI_TakeAwayInlogScherm extends JFrame implements ActionListener {
    
    private JButton button1 ;
    private JButton button2 ;
    private JButton button3 ;
    private JButton button4 ;
    private String ingelogdeKlant;

    public GUI_TakeAwayInlogScherm(String ingelogdeKlant) {
        super("Just-Feed");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.ingelogdeKlant = ingelogdeKlant;

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
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1 ;
        c.weighty = 2 ;
        panel.add(button1, c);

        button2 = new JButton("");
        button2.setPreferredSize(new Dimension(50, 40));
        button2.setBackground(Color.WHITE);
        button2.setFocusable(false);
        button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button2.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        panel.add(button2, c);

        JLabel tekst2 = new JLabel("");
        c.gridx = 1;
        c.gridy = 1;
        panel.add(tekst2, c);

        button3 = new JButton("            Bestellingen");
        button3.setPreferredSize(new Dimension(250, 60));
        button3.setBackground(Color.DARK_GRAY);
        button3.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        button3.setForeground(Color.WHITE);
        button3.setFocusable(false);
        button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button3.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 12;
        c.weighty = 8;
        panel.add(button3, c);

        button4 = new JButton("      Rapporten trekken");
        button4.setPreferredSize(new Dimension(250, 60));
        button4.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        button4.setBackground(Color.DARK_GRAY);
        button4.setForeground(Color.WHITE);
        button4.setFocusable(false);
        button4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button4.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(button4, c);

        JLabel tekst1 = new JLabel("");
        c.gridx = 1;
        c.gridy = 4;
        panel.add(tekst1, c);

        JLabel tekst3 = new JLabel("");
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 13;
        c.weighty = 3;
        panel.add(tekst3, c);
        
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
            Image img = ImageIO.read(getClass().getResource("orderResize.jpg"));
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
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1)
        {
            super.dispose();
            GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdeKlant);
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
            GUI_BestellingenTakeAway guiWindow = new GUI_BestellingenTakeAway(ingelogdeKlant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        
        if(e.getSource() == button4)
        {
            super.dispose();
            GUI_RapportenTrekken guiWindow = new GUI_RapportenTrekken(ingelogdeKlant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
    }
    

}
