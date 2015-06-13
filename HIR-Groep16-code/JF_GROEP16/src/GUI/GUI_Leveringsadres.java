package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hberouag
 */
public class GUI_Leveringsadres extends JFrame implements ActionListener
{
    private JTextField straatT ;
    private JTextField nummerT ;
    private JTextField postcodeT ; 
    private JComboBox gemeenteT ;
    private JRadioButton eigen ;
    private JRadioButton ander ;
    private JPanel geg ;
    private JButton oke ;
    private String ingelogdeKlant;
    
    DB db = new DB() ;
  
    public GUI_Leveringsadres(String ingelogdeKlant)
    {
        super("Just Feed") ;
        setSize(600,400) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        this.ingelogdeKlant = ingelogdeKlant;
        
        Container contentPane = getContentPane() ;
        
        JPanel outer = new JPanel() ;
        outer.setLayout(new GridBagLayout());
        contentPane.add(outer,BorderLayout.CENTER) ;
        outer.setBackground(Color.WHITE);
        
        GridBagConstraints gc = new GridBagConstraints() ;
        GridBagConstraints gbc = new GridBagConstraints() ;
        
        // leveringsadres
        
        gc.gridx = 0 ;
        gc.gridy = 0 ;
        gc.insets = new Insets(-160,0,0,0) ;
        JLabel leveringsadres = new JLabel("Wat is uw leveringsadres?") ;
       
        outer.add(leveringsadres, gc) ;
       
        
        // radiobutton 
        
        gc.gridy = 1 ;
        gc.insets = new Insets(-60,-255,0,0) ;
        eigen = new JRadioButton("Eigen adres") ;
        eigen.addActionListener(this);
        eigen.setSelected(true);
        eigen.setBackground(Color.WHITE);
        outer.add(eigen,gc) ;
        gc.gridy = 2 ;
        gc.insets = new Insets(-10,-250,0,0) ;
        ander = new JRadioButton("Ander adres") ;
        ander.setBackground(Color.WHITE);
        outer.add(ander,gc) ;
        
        
        ander.addActionListener(this);
        
        ButtonGroup groep = new ButtonGroup();
        groep.add(eigen);
        groep.add(ander);
        
        // ander invullen
        gc.gridy = 3 ;
        gc.insets = new Insets(0,-50,0,0) ;
        geg = new JPanel() ;
        geg.setPreferredSize(new Dimension(300,100));
        geg.setLayout(new GridBagLayout());
        geg.setBorder(BorderFactory.createTitledBorder("Adres"));
        geg.setBackground(Color.WHITE);
        outer.add(geg,gc) ;
        
        gc.gridy = 4 ;
        gc.insets = new Insets(30,200,0,0);
        oke = new JButton("OK") ;
        oke.setFont(new Font("Lucida Bright", Font.BOLD ,14));
        oke.setBackground(Color.DARK_GRAY);
        oke.setForeground(Color.WHITE);
        oke.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        oke.setFocusable(false);
        oke.addActionListener(this);
        outer.add(oke,gc) ;
        
        // panel invullen
        
        JLabel straat = new JLabel("Adres:") ;
        JLabel postcode = new JLabel("Postcode:") ;
        JLabel gemeente = new JLabel("Gemeente:") ;
         
        straatT = new JTextField(12) ;
        nummerT = new JTextField(2) ;
        postcodeT = new JTextField(12) ;
        gemeenteT = new JComboBox() ;
        gemeenteT.setBackground(Color.WHITE);
        
        nummerT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                onlyNumbersKeyTyped(evt);
            }
        });
       
        gbc.anchor = GridBagConstraints.FIRST_LINE_START ;
        gbc.weightx = 3 ;
        gbc.weighty = 1 ;
        gbc.gridx = 0 ;
        gbc.gridy = 0 ;
        gbc.insets = new Insets(0,10,0,0) ;
        geg.add(straat, gbc) ;
        
        gbc.gridx = 0 ;
        gbc.gridy = 1 ;
        gbc.insets = new Insets(0,10,0,0) ;
        geg.add(postcode, gbc) ;
        
        gbc.gridx = 0 ;
        gbc.gridy = 2 ;
        gbc.insets = new Insets(0,10,0,0) ;
        geg.add(gemeente, gbc) ;
        
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        
        gbc.gridx = 1 ;
        gbc.gridy = 0 ;
        gbc.insets = new Insets(0,0,0,0) ;
        geg.add(straatT, gbc) ;
        
        gbc.weightx = 1 ;
        gbc.gridx = 2 ;
        gbc.gridy = 0 ;
        gbc.insets = new Insets(0,0,0,0) ;
        geg.add(nummerT, gbc) ;
        
        gbc.weightx = 3 ;
        gbc.gridx = 1 ;
        gbc.gridy = 1 ;
        gbc.insets = new Insets(0,0,0,0) ;
        geg.add(postcodeT, gbc) ;
        
      
        gbc.gridx = 1 ;
        gbc.gridy = 2 ;
        gbc.insets = new Insets(0,0,0,0) ;
        geg.add(gemeenteT, gbc) ;
        
        // disable components
        Component[] comp = geg.getComponents() ; 
        
        for(Component c : comp)
        {
            c.setEnabled(false);
        }   
        
        // postcode - gemeente
        
        postcodeT.addKeyListener(new KeyAdapter() {
           public void keyTyped(KeyEvent evt) {
               toonGemeenten(evt) ;
           }
       } ) ;
       
    }
    
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
                robot.keyPress(KeyEvent.VK_TAB);
                }catch (AWTException e) {
                    e.printStackTrace();
                }
           }
           else
               evt.consume() ;
        }
    }
    public void onlyNumbersKeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c))) {
            e.consume();
        }
    }
    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        if(evt.getSource() == eigen)
        {
          // disable components
            Component[] comp = geg.getComponents() ; 
        
            for(Component c : comp)
            {
                c.setEnabled(false);
            }   
        }
        if(evt.getSource() == ander)
        {
            // enable component
             Component[] comp = geg.getComponents() ; 
        
            for(Component c : comp)
            {
                c.setEnabled(true);
            }
        }
        
        if (evt.getSource() == oke)
        {
           
            if(eigen.isSelected())
            {
                  Bestelling bestelling = new Bestelling(ingelogdeKlant) ;
                  bestelling.setLeveringsstraat(db.getStraatVanKlant(ingelogdeKlant));
                  bestelling.setLeveringshuisnummer(db.getHuisnummerVanKlant(ingelogdeKlant));
                  bestelling.setLeveringspostcode(db.getPostcodeVanKlant(ingelogdeKlant));
                  bestelling.setLeveringsgemeente(db.getGemeenteVanKlant(ingelogdeKlant));
                
                  super.dispose();
                  GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(ingelogdeKlant, 0.00, bestelling);
                  guiWindow.setVisible(true);
                  guiWindow.setLocationRelativeTo(null);
                  guiWindow.setResizable(false);
                  
                  

            }
            else
            {
                if(straatT.getText().isEmpty() || nummerT.getText().isEmpty() || postcodeT.getText().isEmpty() || gemeenteT.getSelectedItem() == null )
                {
                   JOptionPane.showMessageDialog(null, "U hebt niet alle gegevens ingevuld!", "Error", JOptionPane.ERROR_MESSAGE); 
                }
                else
                {
                    Bestelling bestelling = new Bestelling(ingelogdeKlant) ;
                    bestelling.setLeveringsstraat(straatT.getText());
                    bestelling.setLeveringshuisnummer(Integer.parseInt(nummerT.getText()));
                    bestelling.setLeveringspostcode(Integer.parseInt(postcodeT.getText()));
                    bestelling.setLeveringsgemeente((String)gemeenteT.getSelectedItem());
                    
                    super.dispose();
                    GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(ingelogdeKlant, 0.00, bestelling);
                    guiWindow.setVisible(true);
                    guiWindow.setLocationRelativeTo(null);
                    guiWindow.setResizable(false);
                    
                }
            }
        }
        
    }
            
}
