package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Component;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GUI_AdminPanel extends JFrame implements ActionListener, TableModelListener{
    
    //declaratie van alle labels
    private JLabel adminPanelLabel,aantalTakeAwaysLabel,aantalKlantenLabel,aantalBestellingenLabel,aantalGerechtenLabel,
    aantalBijgerechtenLabel,aantalSnacksLabel,aantalDrankenLabel,totaalWaardeBestellingenLabel,totaalGenomenCommissieLabel,
    aantalTakeAwaysRLabel,aantalKlantenRLabel,aantalBestellingenRLabel,aantalGerechtenRLabel,aantalBijgerechtenRLabel,aantalSnacksRLabel,
    aantalDrankenRLabel,totaalWaardeBestellingenRLabel,totaalGenomenCommissieRLabel;
    
    //declaratie van alle boxen
    private Box algemeneStatistieken,financieleStatistieken;
    
    
    //declaratie van de button
    private JButton logoutBtn;
    
    //declaratie van de JTables
    private DefaultTableModel takeawaysTM ;
    public JTable takeawaysTable;
    private JScrollPane takeawaysTableSP;
    private DecimalFormat f = new DecimalFormat("##.00");
    
    //database
    DB db = new DB();
    
    public GUI_AdminPanel()
    {
        super("Just Feed");
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        
        //tablemodels enzo samenstellen
        takeawaysTM = new DefaultTableModel() ;
        
        takeawaysTM.addColumn("TakeAway");
        takeawaysTM.addColumn("# Orders");
        takeawaysTM.addColumn("Omzet");
        takeawaysTM.addColumn("Menu");
        takeawaysTM.addColumn("Status");
        takeawaysTM.addColumn("Accepteer");
        takeawaysTM.addColumn("Weiger");
        
        takeawaysTable = new JTable(takeawaysTM);
        takeawaysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        takeawaysTable.setBackground(Color.WHITE);
        takeawaysTable.setOpaque(true);
        takeawaysTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        takeawaysTable.getColumnModel().getColumn(0).setPreferredWidth(156);
        takeawaysTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        takeawaysTable.getColumnModel().getColumn(2).setPreferredWidth(65);
        takeawaysTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        takeawaysTable.getColumnModel().getColumn(4).setPreferredWidth(62);
        takeawaysTable.setFillsViewportHeight(true);
        takeawaysTM.addTableModelListener(this);
        
        TableButton buttons = new TableButton();
        buttons.addHandler(new TableButton.TableButtonPressedHandler() {
			
			@Override
			public void onButtonPress(int row, int column) {
				
                                if(takeawaysTM.getValueAt(row, 4).equals("pending"))
                                {
                                    db.updateStatusNaarActief((String)takeawaysTable.getValueAt(row, 0));
                                    takeawaysTM.setValueAt("actief", row, 4);
                                    takeawaysTM.fireTableDataChanged();
                                    SendMail stuurMail = new SendMail();
                                    stuurMail.stuurAanvaardingTakeAwayMail(db.getEmailVanTakeAway(takeawaysTM.getValueAt(row, 0)+""), "" + takeawaysTM.getValueAt(row, 0));
                                }
                                else 
                                {
                                    db.updateStatusNaarActief((String)takeawaysTable.getValueAt(row, 0));
                                    takeawaysTM.setValueAt("actief", row, 4);
                                    takeawaysTM.fireTableDataChanged();
                                }
                                
                                
			}
		});
        TableButton buttons2 = new TableButton();
        buttons2.addHandler(new TableButton.TableButtonPressedHandler() {
			
			@Override
			public void onButtonPress(int row, int column) {
				db.updateStatusNaarPassief((String)takeawaysTable.getValueAt(row, 0));
                                takeawaysTM.setValueAt("passief", row, 4);
                                takeawaysTM.fireTableDataChanged();
			}
		});
        TableButton buttons3 = new TableButton();
        buttons3.addHandler(new TableButton.TableButtonPressedHandler() {
			
			@Override
			public void onButtonPress(int row, int column) {
                            GUI_AdminMenuOverzicht guiWindow = new GUI_AdminMenuOverzicht((String)takeawaysTable.getValueAt(row, 0));
                            guiWindow.setVisible(true);
                            guiWindow.setLocationRelativeTo(null);
                            guiWindow.setResizable(false);
				
			}
		});
        
        takeawaysTable.getColumn("Accepteer").setCellRenderer(buttons);
        takeawaysTable.getColumn("Accepteer").setCellEditor(buttons);
        
        takeawaysTable.getColumn("Weiger").setCellRenderer(buttons2);
        takeawaysTable.getColumn("Weiger").setCellEditor(buttons2);
        
        takeawaysTable.getColumn("Menu").setCellRenderer(buttons3);
        takeawaysTable.getColumn("Menu").setCellEditor(buttons3);
        
        takeawaysTableSP = new JScrollPane(takeawaysTable);
        takeawaysTableSP.setPreferredSize(new Dimension(630,320));
        takeawaysTableSP.setBackground(Color.WHITE);
        takeawaysTableSP.getViewport().setBackground(Color.WHITE);
        takeawaysTableSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //initialiseren van labels
        adminPanelLabel = new JLabel();
        adminPanelLabel.setText("Admin Panel");
        adminPanelLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        aantalTakeAwaysLabel = new JLabel();
        aantalTakeAwaysLabel.setText("Aantal TakeAways:");
        aantalKlantenLabel= new JLabel();
        aantalKlantenLabel.setText("Aantal Klanten:");
        aantalBestellingenLabel= new JLabel();
        aantalBestellingenLabel.setText("Aantal bestellingen:");
        aantalGerechtenLabel= new JLabel();
        aantalGerechtenLabel.setText("Aantal Gerechten:");
        aantalBijgerechtenLabel= new JLabel();
        aantalBijgerechtenLabel.setText("Aantal Bijgerechten:");
        aantalSnacksLabel= new JLabel();
        aantalSnacksLabel.setText("Aantal Snacks:");
        aantalDrankenLabel= new JLabel();
        aantalDrankenLabel.setText("Aantal Dranken:");
        totaalWaardeBestellingenLabel= new JLabel();
        totaalWaardeBestellingenLabel.setText("Totale geldwaarde Bestellingen:");
        totaalGenomenCommissieLabel= new JLabel();
        totaalGenomenCommissieLabel.setText("Commissie tot nu toe in maand:");
        aantalTakeAwaysRLabel= new JLabel();
        aantalTakeAwaysRLabel.setText(db.getAantalTakeAways());
        aantalKlantenRLabel= new JLabel();
        aantalKlantenRLabel.setText(db.getAantalKlanten());
        aantalBestellingenRLabel= new JLabel();
        aantalBestellingenRLabel.setText(db.getAantalBestellingen());
        aantalGerechtenRLabel= new JLabel();
        aantalGerechtenRLabel.setText(db.getAantalGerechten());
        aantalBijgerechtenRLabel= new JLabel();
        aantalBijgerechtenRLabel.setText(db.getAantalBijgerechten());
        aantalSnacksRLabel= new JLabel();
        aantalSnacksRLabel.setText(db.getAantalSnacks());
        aantalDrankenRLabel= new JLabel();
        aantalDrankenRLabel.setText(db.getAantalDranken());
        totaalWaardeBestellingenRLabel= new JLabel();
        totaalWaardeBestellingenRLabel.setText("€" + f.format(db.getTotaleWaardeBestellingen()) + "");
        totaalGenomenCommissieRLabel= new JLabel();
        Date datum = new Date();
        totaalGenomenCommissieRLabel.setText("€" + f.format(db.getTotalCommissieJustFeedInMaand(""+datum.getMonth(),datum.getYear())));
        
        //initialiseren van de button
        logoutBtn = new JButton();
        logoutBtn.setPreferredSize(new Dimension(50, 40));
        logoutBtn.setBackground(Color.WHITE);
        logoutBtn.setFocusable(false);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(this);
        try {
            Image img = ImageIO.read(getClass().getResource("uitloggen.jpg"));
            logoutBtn.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        //maken container
        Container contentPane = getContentPane() ;
       
        //maken outerPanel
        JPanel outer = new JPanel() ;
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.WHITE);
        contentPane.add(outer);

        //maken van generalestatistiekePanel
        JPanel generalStatistiekenPanel = new JPanel();
        generalStatistiekenPanel.setLayout(new GridBagLayout( ));
        generalStatistiekenPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15,50,0,0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        generalStatistiekenPanel.add(aantalTakeAwaysLabel,gbc);
        gbc.insets = new Insets(0,50,0,0);
        gbc.gridy = 1;
        generalStatistiekenPanel.add(aantalKlantenLabel,gbc);
        gbc.gridy = 2;
        generalStatistiekenPanel.add(aantalBestellingenLabel,gbc);
        gbc.gridy = 3;
        gbc.gridy = 4;
        generalStatistiekenPanel.add(aantalGerechtenLabel,gbc);
        gbc.gridy = 5;
        generalStatistiekenPanel.add(aantalBijgerechtenLabel,gbc);
        gbc.gridy = 6;
        generalStatistiekenPanel.add(aantalSnacksLabel,gbc);
        gbc.gridy = 7;
        generalStatistiekenPanel.add(aantalDrankenLabel,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(15,50,0,0);
        generalStatistiekenPanel.add(aantalTakeAwaysRLabel,gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0,50,0,0);
        generalStatistiekenPanel.add(aantalKlantenRLabel,gbc);
        gbc.gridy = 2;
        generalStatistiekenPanel.add(aantalBestellingenRLabel,gbc);
        gbc.gridy = 3;
        gbc.gridy = 4;
        generalStatistiekenPanel.add(aantalGerechtenRLabel,gbc);
        gbc.gridy = 5;
        generalStatistiekenPanel.add(aantalBijgerechtenRLabel,gbc);
        gbc.gridy = 6;
        generalStatistiekenPanel.add(aantalSnacksRLabel,gbc);
        gbc.gridy = 7;
        generalStatistiekenPanel.add(aantalDrankenRLabel,gbc);
        gbc.fill = GridBagConstraints.NONE;
        algemeneStatistieken = Box.createHorizontalBox();
        algemeneStatistieken.add(generalStatistiekenPanel);
        algemeneStatistieken.setBorder(BorderFactory.createTitledBorder("Algemene Statistieken"));
        
        //maken van financiële statistieken panel
        JPanel financieleStatistiekenPanel = new JPanel();
        financieleStatistiekenPanel.setLayout(new GridBagLayout());
        financieleStatistiekenPanel.setBackground(Color.WHITE);
        GridBagConstraints fgc = new GridBagConstraints();
        fgc.weightx = 1;
        fgc.weighty = 1;
        fgc.gridx = 0;
        fgc.gridy =0;
        fgc.fill = GridBagConstraints.HORIZONTAL;
        fgc.insets = new Insets(15,50,0,0);
        financieleStatistiekenPanel.add(totaalWaardeBestellingenLabel,fgc);
        fgc.gridy = 1;
        fgc.insets = new Insets(0,50,0,0);
        financieleStatistiekenPanel.add(totaalGenomenCommissieLabel,fgc);
        fgc.gridx = 1;
        fgc.gridy = 0;
        fgc.insets = new Insets(15,50,0,0);
        financieleStatistiekenPanel.add(totaalWaardeBestellingenRLabel,fgc);
        fgc.gridy = 1;
        fgc.insets = new Insets(0,50,0,0);
        financieleStatistiekenPanel.add(totaalGenomenCommissieRLabel,fgc);
        financieleStatistieken = Box.createHorizontalBox();
        financieleStatistieken.add(financieleStatistiekenPanel);
        financieleStatistieken.setBorder(BorderFactory.createTitledBorder("Financiële statistieken"));
        
        
        
        //maken van tablePanel
        JPanel tabelPanel = new JPanel();
        tabelPanel.setBackground(Color.WHITE);
        tabelPanel.setLayout(new GridBagLayout( ));
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 0;
        tabelPanel.add(takeawaysTableSP,gc);
        
        //statistiekenpanel
        JPanel statistiekenPanel = new JPanel();
        statistiekenPanel.setLayout(new GridBagLayout());
        statistiekenPanel.setBackground(Color.WHITE);
        GridBagConstraints sgc = new GridBagConstraints();
        sgc.fill = GridBagConstraints.HORIZONTAL;
        sgc.insets = new Insets(0,20,0,20);
        sgc.weightx = 1;
        sgc.weighty = 1;
        sgc.gridx = 0;
        sgc.gridy = 0;
        statistiekenPanel.add(algemeneStatistieken,sgc);
        sgc.gridy = 1;
        statistiekenPanel.add(financieleStatistieken,sgc);
        
        //maken tabbedPanels
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFocusable(false);
        tabs.setBackground(Color.WHITE);
        tabs.addTab("Aanvragen TakeAways",tabelPanel);
        tabs.addTab("Statistieken", statistiekenPanel);
        tabs.setPreferredSize(new Dimension(650,400));
        
        //samenstellen outer
        gbc.gridy = 0;
        gbc.insets = new Insets(0,-500,0,0);
        outer.add(adminPanelLabel,gbc);
        gbc.insets = new Insets(0,600,0,0);
        outer.add(logoutBtn,gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy = 1;
        outer.add(tabs,gbc);
        
        ArrayList<String> namenTakeAways = db.getAlleTakeAways();
        for (String naam:  namenTakeAways)
        {
            ImageIcon acc = new ImageIcon(getClass().getResource("correct-md.png"));
            ImageIcon dec = new ImageIcon(getClass().getResource("error_button.png"));
            
         
            Object[] s = new Object[]{naam, db.getAantalBesteldTakeAway(naam) ,"€" + f.format(db.getOmzetTakeAway(naam)) ,"Bekijk Menu",db.getStatusTakeAway(naam),acc,dec};
            takeawaysTM.addRow(s);
            
        }
        
        takeawaysTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);
                if (column == 3)
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                if (((String)table.getValueAt(row, 4)).equals("actief"))
                {
                    if (column == 0)
                    {
                    c.setBackground(new Color(50,205,50)) ;
                    //c.setBackground(new Color(55,232,85)) ;
                    }
                }
                else if (((String)table.getValueAt(row, 4)).equals("passief"))
                {
                    if (column == 0)
                    {
                    //c.setBackground(new Color(253,31,31)) ;
                      c.setBackground(new Color(229,34,34)) ;
                        
                    }
                }
                else if (((String)table.getValueAt(row, 4)).equals("pending"))
                {
                    if (column == 0)
                    {
                    c.setBackground(new Color(255,255,115)) ;
                    }
                }
                    
                return c;
            }
        });
   
    }
    
    
    public void actionPerformed(ActionEvent evt) 
    {
        if (evt.getSource() == logoutBtn)
        {
            super.dispose();
            GUI_Inloggen guiWindow = new GUI_Inloggen();
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        takeawaysTable.repaint();
    }
    
}




