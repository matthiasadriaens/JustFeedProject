package GUI;


import DATABASE_EN_ENTITEITEN.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

public class GUI_SchrijfReview extends JFrame implements ActionListener {

    private Box maaltijdbox;
    private Box buitenreviewbox;
    private Box binnenreviewbox;

    private String ingelogdeklant;
    private int menuID;
    private String gerecht;
    private String takeAway;
    private Double prijs;

    private Box area1;
    private JPanel buitenpanelreview;
    private JScrollPane scroll;
    private JButton toevoegenwinkelmandje;
    private JButton Bschrijfreview ;
    private JButton cancel;
    
    private ArrayList<JButton> likebuttons ;
    private ArrayList<JLabel> likesbijreviews ;
    private ArrayList<JLabel> scoresbijreviews ;
    private ArrayList<String> schrijvers ;
    private ArrayList<String> texts ;

    private Bestelling bestelling;
    private Double totaalbedrag;
    private DecimalFormat f = new DecimalFormat("##.00");
    private ArrayList<Review> reviews ;
    private int vestigingsID;
    private String textsave ;
    
    private Dimension dim = UIManager.getDimension("OptionPane.minimumSize") ;
    
    private Container frame ;
    DB db = new DB();

    public GUI_SchrijfReview(String takeaway, String gerechtnaam, String inlognaam) {

        super("Just-Feed");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame = getContentPane();
        
        takeAway = takeaway;
        menuID = db.getMenuIDTakeAway(takeAway);
        gerecht = gerechtnaam;
        prijs = db.getPrijsGerecht(gerecht, menuID);
        vestigingsID = Integer.parseInt(db.getVestigingsIDSTakeAway(takeAway).get(0));
        ingelogdeklant = inlognaam ;
        likebuttons = new ArrayList() ;
        likesbijreviews = new ArrayList() ;
        scoresbijreviews = new ArrayList() ;
        //maak outer
        JPanel outer = new JPanel();
        outer.setLayout(new GridBagLayout());
        outer.setBackground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        frame.add(outer);

        //maak panel-maaltijd met knoppen
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.white);

        JLabel tekst2 = new JLabel("Prijs:");
        tekst2.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst2.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(tekst2, c);

        JLabel tekst3 = new JLabel("€" + f.format(prijs));
        tekst3.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst3.setForeground(Color.DARK_GRAY);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(tekst3, c);

        JLabel tekst6 = new JLabel("TakeAway:");
        tekst6.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst6.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;;
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(tekst6, c);

        JLabel tekst7 = new JLabel(takeAway);
        tekst7.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        tekst7.setForeground(Color.DARK_GRAY);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(tekst7, c);

        GridBagConstraints cb = new GridBagConstraints();
        Bschrijfreview = new JButton("Schrijf Review");
        Bschrijfreview.setPreferredSize(new Dimension(210, 30));
        Bschrijfreview.setBackground(Color.DARK_GRAY);
        Bschrijfreview.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        Bschrijfreview.setForeground(Color.WHITE);
        Bschrijfreview.setFocusable(false);
        Bschrijfreview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Bschrijfreview.addActionListener(this);

        cancel = new JButton("Annuleren");
        cancel.setPreferredSize(new Dimension(120, 30));
        cancel.setBackground(Color.DARK_GRAY);
        cancel.setFont(new Font("Lucida Bright", Font.BOLD, 14));
        cancel.setForeground(Color.WHITE);
        cancel.setFocusable(false);
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.addActionListener(this);

        cb.gridx = 0;
        cb.gridy = 3;
        cb.weightx = 1;
        cb.weighty = 1;
        cb.insets = new Insets(10, -135, 0, 0);
        panel.add(Bschrijfreview, cb);
        cb.gridx = 1;
        cb.insets = new Insets(10, -190, 0, 0);
        panel.add(cancel, cb);

        //panel in een box voegen
        maaltijdbox = Box.createHorizontalBox();
        maaltijdbox.setBorder(BorderFactory.createTitledBorder("Gerecht"));
        maaltijdbox.add(panel);

        // deze box (0,1) voegen in de outer + De naam van het gerecht 
        JLabel tekst1 = new JLabel(gerecht);
        tekst1.setFont(new Font("Lucida Bright", Font.BOLD, 18));
        tekst1.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(-60, 10, 20, 0);
        outer.add(tekst1, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        c.insets = new Insets(-160, 10, 0, 10);
        outer.add(maaltijdbox, c);

        // maak review panel met een SCROLL FUNCTIE 
        buitenpanelreview = new JPanel();
        buitenpanelreview.setLayout(new GridBagLayout());
        buitenpanelreview.setBackground(Color.white);

        // scroll
        // maak box aan voor reviewpanel
        buitenreviewbox = Box.createHorizontalBox();
        buitenreviewbox.setBorder(BorderFactory.createTitledBorder("Reviews"));
        // scroll
        buitenreviewbox.setPreferredSize(new Dimension(500, 90));
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(-60, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        outer.add(buitenreviewbox, c);

        // recensiespanel (forlus )
        GridBagConstraints abc = new GridBagConstraints();
        abc.gridy = 0;
        ArrayList<Integer> aantalLikes = db.getLijstAantalLikesReviewGerecht(gerecht, menuID);
        schrijvers = db.getLijstKlantloginReviewGerecht(gerecht, menuID);
        ArrayList<Double> scores = db.getLijstScoresReviewVanGerecht(gerecht, menuID);
        texts = db.getLijstTekstenReviewGerecht(gerecht, menuID);
        reviews = new ArrayList();
        for (int i = 0; i < schrijvers.size(); i++) {
            reviews.add(new Review(schrijvers.get(i), texts.get(i), scores.get(i), aantalLikes.get(i)));
        }
        Collections.sort(reviews);
        for (int i = 0; i < reviews.size(); i++) {
            // binnenreviewpanel + knoppen
            JPanel binnenpanelreview = new JPanel();
            binnenpanelreview.setLayout(new GridBagLayout());
            binnenpanelreview.setBackground(Color.WHITE);
            JButton likes = new JButton();
            likes.setForeground(Color.WHITE);
            likes.setBackground(Color.WHITE);
            likes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            likes.setFocusable(false);
            likes.addActionListener(this);
            try {
                Image img = ImageIO.read(getClass().getResource("duim.png"));
                likes.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 20, 0, 0);
            binnenpanelreview.add(likes, c);

            JLabel L = new JLabel(reviews.get(i).getLikes() + "");
            L.setFont(new Font("Serif", Font.BOLD, 14));
            c.insets = new Insets(40, 20, 0, 0);
            binnenpanelreview.add(L, c);

            JLabel score = new JLabel("Score : " + reviews.get(i).getScore() + "/10");
            score.setFont(new Font("Serif", Font.BOLD, 11));
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.insets = new Insets(0, 30, 0, 0);
            binnenpanelreview.add(score, c);

            //JLabel tekst = new JLabel("Dit maaltijd heeft me echt gesmaakt! Een aanrader voor iedereen!!  \n  azmflk,azmlfk,zalmkf,alzk,fzalk,flzak,flzak,f");
            JTextArea tekst = new JTextArea(reviews.get(i).getText());
            tekst.setLineWrap(true);
            tekst.setWrapStyleWord(true);
            tekst.setEditable(false);

            c.anchor = GridBagConstraints.FIRST_LINE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 6;
            c.weighty = 1;
            c.insets = new Insets(20, 30, 0, 0);
            binnenpanelreview.add(tekst, c);

            //maak scroll aan
            //maak box aan
            binnenreviewbox = Box.createHorizontalBox();
            TitledBorder title = BorderFactory.createTitledBorder(reviews.get(i).getSchrijver());
            binnenreviewbox.setBorder(title);
            title.setTitleJustification(TitledBorder.CENTER);
            binnenreviewbox.setPreferredSize(new Dimension(530, 90));
            binnenreviewbox.add(binnenpanelreview);

            // steek box in buitenpanel
            abc.gridx = 0;
            abc.gridy++;
            abc.weightx = 1;
            abc.weighty = 1;
            abc.insets = new Insets(0, 0, 0, 0);
            buitenpanelreview.add(binnenreviewbox, abc);
            
            likebuttons.add(likes) ;
            likesbijreviews.add(L) ;
            scoresbijreviews.add(score) ;
            
            if (i == 0) {
                area1 = binnenreviewbox;
            }

        }
        scroll = new JScrollPane(buitenpanelreview); //scroll hangt nu aan een panel
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(null);
        buitenreviewbox.add(scroll);

        scrollToTop();

    }

    public String getIngelogdeklant() {
        return ingelogdeklant;
    }

    public void setIngelogdeklant(String ingelogdeklant) {
        this.ingelogdeklant = ingelogdeklant;
    }

    private void scrollToTop() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scroll.getVerticalScrollBar().setValue(0);
                
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource() == cancel)
        {
            super.dispose();
            GUI_MenuOverzicht2 guiWindow = new GUI_MenuOverzicht2(takeAway ,ingelogdeklant);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if (e.getSource() == toevoegenwinkelmandje) {
            if (bestelling.getMenuGerecht().containsKey(takeAway)) {
                if (bestelling.getMenuGerecht().get(takeAway).containsKey(gerecht)) {
                    bestelling.getMenuGerecht().get(takeAway).put(gerecht, bestelling.getMenuGerecht().get(takeAway).get(gerecht) + 1);
                    bestelling.getVestigingen().add(vestigingsID);
                } else {
                    bestelling.getMenuGerecht().get(takeAway).put(gerecht, 1);
                    bestelling.getGerechtenPrijslijst().get(takeAway).put(gerecht, "€" + f.format(prijs));
                    bestelling.getVestigingen().add(vestigingsID);
                }
            } else {
                HashMap<String, Integer> hm1 = new HashMap();
                HashMap<String, String> hm2 = new HashMap();
                hm1.put(gerecht, 1);
                hm2.put(gerecht, "€" + f.format(prijs));
                bestelling.getMenuGerecht().put(takeAway, hm1);
                bestelling.getGerechtenPrijslijst().put(takeAway, hm2);
                bestelling.getVestigingen().add(vestigingsID);
            }
            

            super.dispose();
            GUI_StelOrderSamen guiWindow = new GUI_StelOrderSamen(ingelogdeklant, totaalbedrag + prijs, bestelling);
            guiWindow.setVisible(true);
            guiWindow.setLocationRelativeTo(null);
            guiWindow.setResizable(false);
        }
        if(e.getSource() == Bschrijfreview)
        {
            GridBagConstraints gc = new GridBagConstraints() ;
        
             JTextArea tekst = new JTextArea() ;
             tekst.setBorder(BorderFactory.createLineBorder(Color.BLACK));
             tekst.setPreferredSize(new Dimension(100,50));
             if (textsave != null)
             {
                 tekst.setText(textsave);
             }
            // panel opmaken
            JLabel cate = new JLabel("Schrijf uw review:") ;
            JLabel optien = new JLabel("/10");
            JTextField cijfer = new JTextField() ;
            cijfer.setPreferredSize(new Dimension(20,20));
            JPanel outer = new JPanel() ;
            outer.setLayout(new GridBagLayout());
            
            JPanel left = new JPanel() ;
            left.setLayout(new GridBagLayout());
            
            gc.gridx = 0 ;
            gc.gridy = 0 ;
            gc.weightx = 1 ;
            gc.weighty = 1 ;
            gc.insets = new Insets(0,0,0,0);;
            gc.fill = GridBagConstraints.HORIZONTAL;
            outer.add(left,gc) ;
            
            gc.gridx = 0 ;
            gc.gridy = 0 ;
            gc.weightx = 2 ;
            gc.weighty = 1 ;
            gc.insets = new Insets(0,-200,0,0);
            gc.fill = GridBagConstraints.HORIZONTAL;
            left.add(cate, gc) ;
            
            gc.gridx = 0 ;
            gc.gridy = 0 ;
            gc.weightx = 1 ;
            gc.weighty = 1 ;
            gc.insets = new Insets(0,200,0,0);
            gc.fill = GridBagConstraints.NONE;
            left.add(optien, gc) ;
            
            gc.gridx = 0 ;
            gc.gridy = 0 ;
            gc.weightx = 1 ;
            gc.weighty = 1 ;
            gc.insets = new Insets(0,160,0,0);
            gc.fill = GridBagConstraints.NONE;
            left.add(cijfer, gc) ;

            gc.gridx = 0 ;
            gc.gridy = 1 ;
            gc.weightx = 1 ;
            gc.weighty = 1 ;
            gc.insets = new Insets(15,0,0,0);
            gc.fill = GridBagConstraints.BOTH;
            left.add(tekst, gc) ;
            
            UIManager.put("OptionPane.minimumSize", new Dimension(300, 110));      
            UIManager.put("Optionpane.buttonBackground",Color.WHITE ) ;
            int result = JOptionPane.showConfirmDialog(null, outer, "Review",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
            UIDefaults uiDefaults = UIManager.getDefaults();
            
            if (result == JOptionPane.OK_OPTION)
             {
                 if(tekst.getText().isEmpty())
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Geen review ingevuld!", "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 else if (texts.contains(tekst.getText()))
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "U mag niet 2 keer dezelfde review schrijven!", "Error", JOptionPane.ERROR_MESSAGE);
                     textsave = tekst.getText() ;
                 }
                 else if (tekst.getText().length() > 200)
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Te veel tekst", "Error", JOptionPane.ERROR_MESSAGE);
                     textsave = tekst.getText() ;
                 }
                 else if(cijfer.getText().isEmpty())
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Geef een score","Error", JOptionPane.ERROR_MESSAGE);
                     textsave = tekst.getText() ; 
                 } 
                 else if(Integer.parseInt(cijfer.getText()) >10) 
                 {
                     UIManager.put("OptionPane.minimumSize", dim) ;
                     JOptionPane.showMessageDialog(null, "Score mag niet hoger dan 10!","Error", JOptionPane.ERROR_MESSAGE);
                     textsave = tekst.getText() ; 
                 } 
                 
                 else 
                 {
                    db.nieuweReview(Integer.parseInt(cijfer.getText()), tekst.getText() , 0, ingelogdeklant, menuID, gerecht);
                    if (!db.controleerOfReviewKCBijGerechtAlBestaat(ingelogdeklant, takeAway, gerecht))
                    {
                        String codeC = codecombinatieGenereren() ;
                        db.nieuweReviewKortingsCode(codeC, gerecht, menuID);
                        db.nieuweKortingBijKlantLogin( ingelogdeklant, codeC);
                        JOptionPane.showMessageDialog(null, "              Proficiat!                " + "\n" + "\n" +"U hebt een kortingscode van 10% ontvangen:" + codeC + "\n" + "Deze korting krijg je enkel bij je eerste review of like op een gerecht!" ,"Review Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                     super.dispose();
                     GUI_SchrijfReview review = new GUI_SchrijfReview(takeAway, gerecht , ingelogdeklant);
                     review.setVisible(true);
                     review.setLocationRelativeTo(null);
                     review.setResizable(false);
                 }
                 }
             }
             for (int i = 0 ; i< likebuttons.size() ; i++)
             {
               if (e.getSource() == likebuttons.get(i))
               {
                   JPanel outer = new JPanel() ;
                   JLabel vraag = new JLabel("Geef uw score aan het gerecht:") ;
                   JTextField score = new JTextField(2) ;
                   outer.add(vraag) ;
                   outer.add(score) ;
                   int result = JOptionPane.showConfirmDialog(null, outer, "Score",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) ;
                   
                   if (result == JOptionPane.OK_OPTION)
                   {
                       if(Integer.parseInt(score.getText()) >10) 
                       {
                            UIManager.put("OptionPane.minimumSize", dim) ;
                            JOptionPane.showMessageDialog(null, "Score mag niet hoger dan 10!","Error", JOptionPane.ERROR_MESSAGE);
                       }
                       else
                       {
                           double oudeScore = reviews.get(i).getScore() ;
                           double nieuweScore = 0.0 ;
                          
                               int oudeLikes = reviews.get(i).getLikes() ;
                               oudeLikes ++ ;
                               nieuweScore = (((oudeScore*oudeLikes)+Integer.parseInt(score.getText()))/((double)(oudeLikes+1))) ;
                              
                           db.updateAantalLikesReview(db.getReviewID(menuID, gerecht, reviews.get(i).getSchrijver(), reviews.get(i).getText()));
                           likesbijreviews.get(i).setText((Integer.parseInt(likesbijreviews.get(i).getText()) + 1) + "");
                           db.updateScoreReview(db.getReviewID(menuID, gerecht, reviews.get(i).getSchrijver(), reviews.get(i).getText()), nieuweScore);
                           scoresbijreviews.get(i).setText("Score : " + db.getScoreReview(db.getReviewID(menuID, gerecht, reviews.get(i).getSchrijver(), reviews.get(i).getText())) + "/10");
                           if (!db.controleerOfReviewKCBijGerechtAlBestaat(ingelogdeklant, takeAway, gerecht))
                           {
                                String codeC = codecombinatieGenereren() ;
                                db.nieuweReviewKortingsCode(codeC, gerecht, menuID);
                                db.nieuweKortingBijKlantLogin( ingelogdeklant, codeC);
                                JOptionPane.showMessageDialog(null, "              Proficiat!                " + "\n" + "\n" +"U hebt een kortingscode van 10% ontvangen:" + codeC + "\n" + "Deze korting krijg je enkel bij je eerste review of like op een gerecht!" ,"Review Kortingscode", JOptionPane.INFORMATION_MESSAGE);
                           }
                       }
                    }
               }
            
            }
    
    }
    public String codecombinatieGenereren()
    {
        Random random = new Random() ;
        boolean firstNoZero = false ;
        boolean codeBestaatal = true ;
        int first = 0 ;
        String codeCombinatie = "" ;
        ArrayList<String> codeCombinaties = db.getLijstCodeCombinaties();
            while (!firstNoZero)
            {
                first = random.nextInt(10) ;
                if (first != 0)
                {
                    firstNoZero = true ;
                }
            }
                    
            while (codeBestaatal)
            {
                codeCombinatie = first + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10)+ "" + random.nextInt(10) + "";
                if (!(codeCombinaties.contains(codeCombinatie)))
                {
                    codeBestaatal = false ;
                }
            }
        return codeCombinatie ;
    }
    
    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Double getTotaalbedrag() {
        return totaalbedrag;
    }

    public void setTotaalbedrag(Double totaalbedrag) {
        this.totaalbedrag = totaalbedrag;
    }

}