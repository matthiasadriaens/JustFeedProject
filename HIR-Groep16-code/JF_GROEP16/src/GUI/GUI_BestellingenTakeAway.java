package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class GUI_BestellingenTakeAway extends JFrame implements ActionListener,TableModelListener{
    
    private JLabel overzichtBestellingen;
    
    private JButton terugNaarOverzicht;
    
    private JTable bestellingenTable;
    private DefaultTableModel bestellingenTM = new DefaultTableModel();
    private JScrollPane bestellingenSP;
    
    private String naamTakeAway;
    
    
    private DB db =  new DB();
    
  
    public GUI_BestellingenTakeAway(final String naamTakeAway5)
    {
        super("Just-Feed");
        setSize(1100,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.naamTakeAway = naamTakeAway5;
        System.out.println(naamTakeAway);
        ArrayList<Integer> bestellingsIDsTA = db.getBestellingsIDsTakeAway(naamTakeAway);
        ArrayList<Integer> bestellingsIDsPending = db.getBestellingsIDsPending();
        ArrayList<Integer> bestellingsIDSPendingTA = new ArrayList();
        for(Integer X : bestellingsIDsPending)
        {
            if (bestellingsIDsTA.contains(X))
            {
                bestellingsIDSPendingTA.add(X);
            }
        }
        
        //initialiseren van SWING-elementen
        overzichtBestellingen = new JLabel();
        overzichtBestellingen.setText("Overzicht Lopende Bestellingen");
        overzichtBestellingen.setFont(new Font("Lucida Bright", Font.BOLD ,22));
        
        terugNaarOverzicht = new JButton();
        terugNaarOverzicht.setText("Terug naar overzicht");
        terugNaarOverzicht.setBackground(Color.DARK_GRAY);
        terugNaarOverzicht.setForeground(Color.WHITE);
        terugNaarOverzicht.setFocusable(false);
        terugNaarOverzicht.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        terugNaarOverzicht.addActionListener(this);
        
        bestellingenTM.addColumn("ID");
        bestellingenTM.addColumn("Vestiging");
        bestellingenTM.addColumn("Klantlogin");
        bestellingenTM.addColumn("Leveringsadres");
        bestellingenTM.addColumn("Bestelling");
        bestellingenTM.addColumn("Totaalprijs");
        bestellingenTM.addColumn("Status");
        bestellingenTM.addColumn("Afgehandeld");
        
        
        bestellingenTable = new JTable(bestellingenTM) ;

        bestellingenTable.getColumnModel().getColumn(0).setPreferredWidth(2);
        bestellingenTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        bestellingenTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        bestellingenTable.getColumnModel().getColumn(3).setPreferredWidth(155);
        bestellingenTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        bestellingenTable.getColumnModel().getColumn(5).setPreferredWidth(15);
        bestellingenTable.getColumnModel().getColumn(6).setPreferredWidth(15);
        bestellingenTable.getColumnModel().getColumn(7).setPreferredWidth(25);
        bestellingenTable.setRowHeight(20);
        bestellingenTable.setFillsViewportHeight(true); 

        bestellingenSP = new JScrollPane(bestellingenTable);
        bestellingenSP.setPreferredSize(new Dimension(630,320));
        bestellingenSP.setBackground(Color.WHITE);
        bestellingenSP.getViewport().setBackground(Color.WHITE);    
        bestellingenSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        bestellingenTM.addTableModelListener(this);
        TableButton buttons = new TableButton();
        buttons.addHandler(new TableButton.TableButtonPressedHandler() {
			
			@Override
			public void onButtonPress(int row, int column) {
                            db.updateStatusAfgehandeldBestelling((int)bestellingenTable.getValueAt(row, 0));
                            bestellingenTM.setValueAt("afgehandeld", row, 6);
                            bestellingenTM.removeRow(row);
                            bestellingenTM.fireTableDataChanged();
			}
		});
        TableButton buttons2 = new TableButton();
        buttons2.addHandler(new TableButton.TableButtonPressedHandler() {
			
			@Override
			public void onButtonPress(int row, int column) {
                            System.out.println((int)bestellingenTable.getValueAt(row, 0));
                            GUI_BekijkBestelling guiWindow = new GUI_BekijkBestelling((int)bestellingenTable.getValueAt(row, 0), naamTakeAway);
                            guiWindow.setVisible(true);
                            guiWindow.setLocationRelativeTo(null);
                            guiWindow.setResizable(false);
                        }
		});
        bestellingenTable.getColumn("Afgehandeld").setCellRenderer(buttons);
        bestellingenTable.getColumn("Afgehandeld").setCellEditor(buttons);
        
        bestellingenTable.getColumn("Bestelling").setCellRenderer(buttons2);
        bestellingenTable.getColumn("Bestelling").setCellEditor(buttons2);
        
        int menuID = db.getMenuIDTakeAway(naamTakeAway);
                
        for (Integer ID :  bestellingsIDSPendingTA)
        {
            JComboBox bestellingsbox = new JComboBox() ;
            ImageIcon acc = new ImageIcon(getClass().getResource("correct-md.png"));

            String vestigingsadres = "";
            ArrayList<Integer> vestingingsIDsBest = db.getVestigingsIDBijBestellingsID(ID);
            ArrayList<String> vestingingsIDsTA = db.getVestigingsIDSTakeAway(naamTakeAway);
            for(String vest : vestingingsIDsTA)
            {
                if(vestingingsIDsBest.contains(Integer.parseInt(vest)))
                {
                    vestigingsadres = vest;
                }
            }
            
            Object[] s = new Object[]{ID,db.getAdresBijVestigingsID(Integer.parseInt(vestigingsadres)),db.getKlantLoginBijBestellingsID(ID), db.getAdresBijBestelling(ID), "Bekijk Bestelling" ,"€" + db.getBedragParieleBestelling(menuID, ID), "pending",acc};
            bestellingenTM.addRow(s);
        }
//        Object[] s = new Object[]{"","","", "", "" ,"", "",""};
//        bestellingenTM.addRow(s);
        //maken van Container
        Container contenPane = getContentPane();
        
        //maken van outer Panel
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        contenPane.add(outer);
        
        //samenstellen van outer
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0,-150,0,540);
        outer.add(overzichtBestellingen,gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0,20,0,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        outer.add(bestellingenSP,gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(0,900,0,0);
        gbc.fill = GridBagConstraints.NONE;
        outer.add(terugNaarOverzicht,gbc);
        
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() ==  terugNaarOverzicht)
        {
            super.dispose();
            GUI_TakeAwayInlogScherm guiWindow = new GUI_TakeAwayInlogScherm(naamTakeAway);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
    }

    
        @Override
    public void tableChanged(TableModelEvent e) {
        bestellingenTable.repaint();
    }
            
}



