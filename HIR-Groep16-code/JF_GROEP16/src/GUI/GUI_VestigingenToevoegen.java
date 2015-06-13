package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.AWTException;
import java.awt.Color;
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hberouag
 */
public class GUI_VestigingenToevoegen extends JFrame implements ActionListener{
    
    private JTextField postcodeT ;
    private JTextField straatT ;
    private JTextField nummerT;
    private JTextField leveringT;
    private JComboBox gemeenteT;
    
    private JComboBox gemeenteT2;
    private JTextField postcodeT2;
            
    private String ingelogdetakeaway ;
    
    private JButton toevoegenPane ;
    private JButton verwijderenPane ;
    private JButton bevestig ;
    private JButton terug;
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize");
    
    private JList   leveringenLPane;
    private DefaultListModel   leveringenLMPane ;
    
    private  ArrayList<Integer> postcodes ;
    private  ArrayList<String> gemeenten ;
    
    DB db = new DB() ;
            
    public GUI_VestigingenToevoegen (String takeaway)
    {
        super("Just-Feed");
        setSize(400, 370);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Container contentpane = getContentPane();
        ingelogdetakeaway = takeaway;
        
        postcodes = new  ArrayList();
        gemeenten = new  ArrayList();
        
            boolean bestaatal = false;
            // panel opmaken
            JPanel nieuw = new JPanel();
            nieuw.setLayout(new GridBagLayout());
            contentpane.add(nieuw) ;

            toevoegenPane = new JButton("Toevoegen   ");
            toevoegenPane.setFont(new Font("Lucida Bright", Font.BOLD ,10)) ;
            toevoegenPane.setForeground(Color.WHITE);
            toevoegenPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            toevoegenPane.setFocusable(false) ;
            toevoegenPane.setBackground(Color.DARK_GRAY);
            toevoegenPane.addActionListener(this);
            
            verwijderenPane = new JButton("Verwijderen");
            verwijderenPane.setFont(new Font("Lucida Bright", Font.BOLD ,10)) ;
            verwijderenPane.setForeground(Color.WHITE);
            verwijderenPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            verwijderenPane.setFocusable(false) ;
            verwijderenPane.setBackground(Color.DARK_GRAY);
            verwijderenPane.addActionListener(this);
            
            bevestig = new JButton("Bevestigen");
            bevestig.setFont(new Font("Lucida Bright", Font.BOLD ,10)) ;
            bevestig.setForeground(Color.WHITE);
            bevestig.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            bevestig.setFocusable(false) ;
            bevestig.setBackground(Color.DARK_GRAY);
            bevestig.addActionListener(this);
            
            terug = new JButton("Terug       ");
            terug.setFont(new Font("Lucida Bright", Font.BOLD ,10)) ;
            terug.setForeground(Color.WHITE);
            terug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            terug.setFocusable(false) ;
            terug.setBackground(Color.DARK_GRAY);
            terug.addActionListener(this);
            
            JLabel straat = new JLabel("Adres:");
            JLabel postcode = new JLabel("Postcode:");
            JLabel gemeente = new JLabel("Gemeente:");
            JLabel leveringskosten = new JLabel("Leveringskosten:");
            JLabel euroteken = new JLabel("€");
            
            
            JLabel leveringenPane = new JLabel("Leveringsgebieden");
            leveringenLMPane = new DefaultListModel();
            
            leveringenLPane = new JList(leveringenLMPane) ;
            leveringenLPane.setPreferredSize(new Dimension(181,100));
            leveringenLPane.setBackground(Color.WHITE);
            
            straatT = new JTextField(12);
            leveringenLPane.setBorder(straatT.getBorder());
            
            nummerT = new JTextField(2);
            postcodeT2 = new JTextField(12);
            gemeenteT2 = new JComboBox();
            gemeenteT2.setBackground(Color.WHITE);
            leveringT = new JTextField(3);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.weightx = 3;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 10, 0, 0);
            nieuw.add(straat, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 10, 0, 0);
            nieuw.add(postcode, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 10, 0, 0);
            nieuw.add(gemeente, gbc);

            gbc.gridy = 3;
            gbc.insets = new Insets(0, 10, 0, 0);
            nieuw.add(leveringskosten, gbc);
            
            gbc.gridy = 4;
            gbc.insets = new Insets(35, 10, 0, 0);
            nieuw.add(toevoegenPane, gbc);
            
            gbc.gridy = 4;
            gbc.insets = new Insets(60, 10, 0, 0);
            nieuw.add(verwijderenPane, gbc);
            
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 0, 0);
            nieuw.add(straatT, gbc);

            gbc.weightx = 1;
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 0, 0);
            nieuw.add(nummerT, gbc);

            gbc.weightx = 3;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            nieuw.add(postcodeT2, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 0, 0, 0);
            nieuw.add(gemeenteT2, gbc);

            gbc.gridy = 3;
            gbc.insets = new Insets(0, 0, 0, 0);
            nieuw.add(euroteken, gbc);

            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 3;
            gbc.insets = new Insets(0, 15, 0, 0);
            nieuw.add(leveringT, gbc);
            
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 4;
            gbc.insets = new Insets(0, -135, 0, 0);
            nieuw.add(leveringenPane, gbc);
            
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 4;
            gbc.gridx = 2;
            gbc.insets = new Insets(0, -175, 0, 0);
            nieuw.add(leveringenLPane, gbc);
            
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 5;
            gbc.gridx = 2;
            gbc.insets = new Insets(0, -175, 0, 0);
            nieuw.add(bevestig, gbc);
            
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 5;
            gbc.gridx = 2;
            gbc.insets = new Insets(0, -80, 0, 0);
            nieuw.add(terug, gbc);

            postcodeT2.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    toonGemeenten2(evt);
                }
            });
            postcodeT2.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    onlyNumbersKeyTyped(evt);
                }
            });
            nummerT.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    onlyNumbersKeyTyped(evt);
                }
            });
            leveringT.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    onlyNumbersKeyTypedAndDot(evt);
                }
            }); 
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
       if (e.getSource() == toevoegenPane)
       {
               boolean bestaatal1 = false ;
               
               JPanel nieuw1 = new JPanel() ;
               nieuw1.setLayout(new GridBagLayout());
               
               JLabel postcode1 = new JLabel("Postcode:") ;
               JLabel gemeente1 = new JLabel("Gemeente:") ;
               postcodeT = new JTextField(12) ;
               gemeenteT = new JComboBox() ;
               gemeenteT.setBackground(Color.WHITE);
               
               GridBagConstraints gc = new GridBagConstraints() ;
               
               gc.anchor = GridBagConstraints.FIRST_LINE_START ;
               gc.weightx = 1 ;
               gc.weighty = 1 ;
               gc.gridx = 0 ;
               gc.gridy = 0 ;
               nieuw1.add(postcode1, gc) ;
               gc.gridy = 1 ;
               nieuw1.add(gemeente1, gc) ;
               gc.gridy = 0 ;
               gc.gridx = 1 ;
               gc.fill = GridBagConstraints.HORIZONTAL ;
               nieuw1.add(postcodeT, gc) ;
               gc.gridy = 1 ;
               nieuw1.add(gemeenteT, gc) ;
               
               postcodeT.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    toonGemeenten(evt);
                }
            });
            postcodeT.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    onlyNumbersKeyTyped(evt);
                }
            });
               
               UIManager.put("OptionPane.minimumSize", dim) ;
               int result1 = JOptionPane.showConfirmDialog(null, nieuw1, "Leveringsgebied toevoegen",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
               
               if(result1 == JOptionPane.OK_OPTION)
               {
                   
                    if (postcodeT.getText() == null || gemeenteT.getSelectedItem() == null )
                    {
                        JOptionPane.showMessageDialog(null, "Gegevens zijn niet geldig!", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                    else 
                    {
                        leveringenLMPane.addElement(postcodeT.getText() + " " + (String)gemeenteT.getSelectedItem());
                        gemeenten.add((String) gemeenteT.getSelectedItem());
                        postcodes.add(Integer.parseInt(postcodeT.getText()));
                    }
                    }

       }
       if(e.getSource() == verwijderenPane)
       {
                if (leveringenLPane.getSelectedValue() != null)
                {
                    int ind = leveringenLPane.getSelectedIndex() ;
                    postcodes.remove(ind) ;
                    gemeenten.remove(ind);
                    leveringenLMPane.removeElement(leveringenLPane.getSelectedValue()) ;
                    System.out.println(postcodes);
                    System.out.println(gemeenten);
                    
                    //database update om leveringsgebied te verwijderen
                }
       }
        
           
       
               
       if(e.getSource() == bevestig)
       {
           
               if (straatT.getText().isEmpty() || postcodeT.getText().isEmpty()  || leveringT.getText().isEmpty() || (String) gemeenteT.getSelectedItem() ==null || nummerT.getText().isEmpty() ) 
                {
                    JOptionPane.showMessageDialog(null, "Gegevens niet ingevuld! ", "Error", JOptionPane.ERROR_MESSAGE); 
                    
                    
                }
                else 
               {
                 super.dispose();
                 db.nieuweVestigingBijTakeAway(WIDTH, SOMEBITS, ingelogdetakeaway, ingelogdetakeaway, WIDTH, WIDTH, ingelogdetakeaway, postcodes, gemeenten);
                         
                 db.nieuweVestigingBijTakeAway(Double.parseDouble(leveringT.getText()), 0,ingelogdetakeaway, straatT.getText(), Integer.parseInt(nummerT.getText()) ,Integer.parseInt(postcodeT2.getText()), (String)gemeenteT2.getSelectedItem(), postcodes , gemeenten);
                 GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdetakeaway);
                 guiWindow.setVisible(true);
                 guiWindow.setLocationRelativeTo(null);
                 guiWindow.setResizable(false);
               }
       }
       if(e.getSource() == terug )
       {
           super.dispose();
           GUI_TAKEAWAYWELKOM guiWindow = new GUI_TAKEAWAYWELKOM(ingelogdetakeaway);
           guiWindow.setVisible(true);
           guiWindow.setLocationRelativeTo(null);
           guiWindow.setResizable(false);
           
       }
            
                
         
    }
    public void toonGemeenten(KeyEvent evt) {
        gemeenteT.removeAllItems();
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();

            postcodeT.setText(postcodeT.getText() + c);

            if (postcodeT.getText().length() == 4) {
                ArrayList<String> gem = db.getGemeentesBijPostcode(Integer.parseInt(postcodeT.getText()));
                for (String s : gem) {
                    gemeenteT.addItem(s);
                }
                gemeenteT.showPopup();
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_TAB);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {
                evt.consume();
            }
        }
    }
    
    public void toonGemeenten2(KeyEvent evt) {
        gemeenteT2.removeAllItems();
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();

            postcodeT2.setText(postcodeT2.getText() + c);

            if (postcodeT2.getText().length() == 4) {
                ArrayList<String> gem = db.getGemeentesBijPostcode(Integer.parseInt(postcodeT2.getText()));
                for (String s : gem) {
                    gemeenteT2.addItem(s);
                }
                gemeenteT2.showPopup();
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_TAB);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {
                evt.consume();
            }
        }
    }

    public void onlyNumbersKeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c))) {
            e.consume();
        }
    }

    public void onlyNumbersKeyTypedAndDot(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_PERIOD)) {
            e.consume();
        }
    }

}
