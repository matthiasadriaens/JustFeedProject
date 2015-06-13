package DATABASE_EN_ENTITEITEN;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hberouag
 */
public class Bestelling 
{
    private int bestellingsID ;
    private Date datum ;
    private double totaalbedrag ;
    private String klantLogin ;
    
    private Double registratieKorting ;
    private String registatieKortingsCode ;
    
    private Double jfBossKorting ;
    private String jfBossKortingscode ;
    
    private Double takeAwayBossKorting ;
    private String takeAwayBossKortingscode ;
    
    private Double reviewKorting ;
    private String reviewKortingscode ;
    
    private Double uniekeActieKorting ;
    private String uniekeActieKortingsCode ;
    private String uniekeActieKortingTakeAway ;
    
    private Double vanafBepaaldgedragKorting ;
    
    private ArrayList<String> naarpassiefKortingen ;
    
    
    private String leveringsstraat ;
    private int leveringshuisnummer ;
    private int leveringspostcode ;
    private String leveringsgemeente ;
   
    private HashMap<String, HashMap<String,Integer> > menuGerecht ; // takeAway, naamgerecht, hoeveelheid
    private HashMap<String, HashMap<String,Integer>> menuBijGerecht ; // takeaway, naambijgerecht, hoeveelheid
    private HashMap<String, HashMap<String,Integer>> menuSnacks ; // takeaway, naamsnack, hoeveelheid
    private HashMap<String, HashMap<String,Integer>> menuDranken ; // takeaway, naamdrank, hoeveelheid
    
    private HashMap<String, HashMap<String,String> > gerechtenPrijslijst ; // takeAway, naamgerecht, prijs
    private HashMap<String, HashMap<String,String>> bijgerechtenPrijslijst ; // takeaway, naambijgerecht, prijs
    private HashMap<String, HashMap<String,String>> snacksPrijslijst ; // takeaway, naamsnack, prijs
    private HashMap<String, HashMap<String,String>> drankenPrijslijst ; // takeaway, naamdrank, prijs
    
    private ArrayList<Integer> vestigingen ;
  
    DB db = new DB() ;

    public Bestelling(String klantLogin) 
    {
        this.klantLogin = klantLogin;
        datum = new Date() ;
        menuGerecht = new HashMap() ;
        menuBijGerecht = new HashMap() ;
        menuSnacks = new HashMap();
        menuDranken = new HashMap();
        gerechtenPrijslijst = new HashMap();
        bijgerechtenPrijslijst = new HashMap();
        snacksPrijslijst = new HashMap();
        drankenPrijslijst = new HashMap();
        vestigingen = new ArrayList() ;
        registratieKorting = 0.00 ;
        uniekeActieKorting = 0.00 ;
        jfBossKorting = 0.00 ;
        takeAwayBossKorting = 0.00 ;
        reviewKorting = 0.00 ;
        vanafBepaaldgedragKorting = 0.00 ;
        naarpassiefKortingen = new ArrayList() ; 
        
    }
    
    

    public int getBestellingsID() {
        return bestellingsID;
    }

    public void setBestellingsID(int bestellingsID) {
        this.bestellingsID = bestellingsID;
    }

    public double getTotaalbedrag() {
        return totaalbedrag;
    }

    public void setTotaalbedrag(double totaalbedrag) {
        this.totaalbedrag = totaalbedrag;
    }

    public String getKlantLogin() {
        return klantLogin;
    }

    public void setKlantLogin(String klantLogin) {
        this.klantLogin = klantLogin;
    }

    public String getLeveringsstraat() {
        return leveringsstraat;
    }

    public void setLeveringsstraat(String leveringsstraat) {
        this.leveringsstraat = leveringsstraat;
    }

    public int getLeveringshuisnummer() {
        return leveringshuisnummer;
    }

    public void setLeveringshuisnummer(int leveringshuisnummer) {
        this.leveringshuisnummer = leveringshuisnummer;
    }

    public int getLeveringspostcode() {
        return leveringspostcode;
    }

    public void setLeveringspostcode(int leveringspostcode) {
        this.leveringspostcode = leveringspostcode;
    }

    public String getLeveringsgemeente() {
        return leveringsgemeente;
    }

    public void setLeveringsgemeente(String leveringsgemeente) {
        this.leveringsgemeente = leveringsgemeente;
    }
    
    public HashMap<String, HashMap<String, Integer>> getMenuGerecht() {
        return menuGerecht;
    }

    public void setMenuGerecht(HashMap<String, HashMap<String, Integer>> menuGerecht) {
        this.menuGerecht = menuGerecht;
    }

    public HashMap<String, HashMap<String, Integer>> getMenuBijGerecht() {
        return menuBijGerecht;
    }

    public void setMenuBijGerecht(HashMap<String, HashMap<String, Integer>> menuBijGerecht) {
        this.menuBijGerecht = menuBijGerecht;
    }

    public HashMap<String, HashMap<String, Integer>> getMenuSnacks() {
        return menuSnacks;
    }

    public void setMenuSnacks(HashMap<String, HashMap<String, Integer>> menuSnacks) {
        this.menuSnacks = menuSnacks;
    }

    public HashMap<String, HashMap<String, Integer>> getMenuDranken() {
        return menuDranken;
    }

    public void setMenuDranken(HashMap<String, HashMap<String, Integer>> menuDranken) {
        this.menuDranken = menuDranken;
    }

    public HashMap<String, HashMap<String, String>> getGerechtenPrijslijst() {
        return gerechtenPrijslijst;
    }

    public void setGerechtenPrijslijst(HashMap<String, HashMap<String, String>> gerechtenPrijslijst) {
        this.gerechtenPrijslijst = gerechtenPrijslijst;
    }

    public HashMap<String, HashMap<String, String>> getBijgerechtenPrijslijst() {
        return bijgerechtenPrijslijst;
    }

    public void setBijgerechtenPrijslijst(HashMap<String, HashMap<String, String>> bijgerechtenPrijslijst) {
        this.bijgerechtenPrijslijst = bijgerechtenPrijslijst;
    }

    public HashMap<String, HashMap<String, String>> getSnacksPrijslijst() {
        return snacksPrijslijst;
    }

    public void setSnacksPrijslijst(HashMap<String, HashMap<String, String>> snacksPrijslijst) {
        this.snacksPrijslijst = snacksPrijslijst;
    }

    public HashMap<String, HashMap<String, String>> getDrankenPrijslijst() {
        return drankenPrijslijst;
    }

    public void setDrankenPrijslijst(HashMap<String, HashMap<String, String>> drankenPrijslijst) {
        this.drankenPrijslijst = drankenPrijslijst;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public ArrayList<Integer> getVestigingen() {
        return vestigingen;
    }

    public void setVestigingen(ArrayList<Integer> vestigingen) {
        this.vestigingen = vestigingen;
    }

    public Double getRegistratieKorting() {
        return registratieKorting;
    }

    public void setRegistratieKorting(Double registratieKorting) {
        this.registratieKorting = registratieKorting;
    }
    

    public String getRegistatieKortingsCode() {
        return registatieKortingsCode;
    }

    public void setRegistatieKortingsCode(String registatieKortingsCode) {
        this.registatieKortingsCode = registatieKortingsCode;
    }
    

    public ArrayList<String> getNaarpassiefKortingen() {
        return naarpassiefKortingen;
    }

    public void setNaarpassiefKortingen(ArrayList<String> naarpassiefKortingen) {
        this.naarpassiefKortingen = naarpassiefKortingen;
    }

    public Double getUniekeActieKorting() {
        return uniekeActieKorting;
    }

    public void setUniekeActieKorting(Double uniekeActieKorting) {
        this.uniekeActieKorting = uniekeActieKorting;
    }

    public String getUniekeActieKortingsCode() {
        return uniekeActieKortingsCode;
    }

    public void setUniekeActieKortingsCode(String uniekeActieKortingsCode) {
        this.uniekeActieKortingsCode = uniekeActieKortingsCode;
    }

    public String getUniekeActieKortingTakeAway() {
        return uniekeActieKortingTakeAway;
    }

    public void setUniekeActieKortingTakeAway(String uniekeActieKortingTakeAway) {
        this.uniekeActieKortingTakeAway = uniekeActieKortingTakeAway;
    }

    public Double getJfBossKorting() {
        return jfBossKorting;
    }

    public void setJfBossKorting(Double jfBossKorting) {
        this.jfBossKorting = jfBossKorting;
    }

    public String getJfBossKortingscode() {
        return jfBossKortingscode;
    }

    public void setJfBossKortingscode(String jfBossKortingscode) {
        this.jfBossKortingscode = jfBossKortingscode;
    }

    public Double getTakeAwayBossKorting() {
        return takeAwayBossKorting;
    }

    public void setTakeAwayBossKorting(Double takeAwayBossKorting) {
        this.takeAwayBossKorting = takeAwayBossKorting;
    }

    public String getTakeAwayBossKortingscode() {
        return takeAwayBossKortingscode;
    }

    public void setTakeAwayBossKortingscode(String takeAwayBossKortingscode) {
        this.takeAwayBossKortingscode = takeAwayBossKortingscode;
    }

    public Double getReviewKorting() {
        return reviewKorting;
    }

    public void setReviewKorting(Double reviewKorting) {
        this.reviewKorting = reviewKorting;
    }

    public String getReviewKortingscode() {
        return reviewKortingscode;
    }

    public void setReviewKortingscode(String reviewKortingscode) {
        this.reviewKortingscode = reviewKortingscode;
    }
    
    
    
    public Double getTotaalKortingen(double bedragWaaropBerekend, double prijsVandeBestelling, double kortingBovenbedrag)
    {
        Double kortingUniekeActie = 0.00 ;
        //unieke actie
        if (bedragWaaropBerekend != 0)
        {
           if (uniekeActieKorting != 0)
           {
               if (uniekeActieKorting > 1)
               {
                   //euro
                   if (bedragWaaropBerekend > uniekeActieKorting)
                   {
                       kortingUniekeActie = uniekeActieKorting ;
                   }
                   else
                   {
                       kortingUniekeActie = bedragWaaropBerekend ;
                   }
               }
               else
               {
                   // perc
                   kortingUniekeActie = bedragWaaropBerekend*uniekeActieKorting ;
               }
           }
        }
        return registratieKorting + kortingUniekeActie + (jfBossKorting*prijsVandeBestelling) + (takeAwayBossKorting*prijsVandeBestelling) + (reviewKorting*prijsVandeBestelling) + kortingBovenbedrag ;
    }
    
    public void addBestelling(Double bedrag)
    {
       int max = db.getMaximumBestellingsID() + 10 ;
       db.nieuweBestelling(max, new java.sql.Date(datum.getTime()) , bedrag, leveringsstraat, leveringshuisnummer, leveringspostcode, leveringsgemeente, klantLogin, vestigingen);
       for (String ta : menuGerecht.keySet())
       {
          int menu = db.getMenuIDTakeAway(ta) ;
          for (String ger : menuGerecht.get(ta).keySet()) 
          {
             db.nieuwGerechtAanBestelling(ger, menu, max, menuGerecht.get(ta).get(ger)); 
          }
          if (getMenuBijGerecht().get(ta) != null)
          {
            for (String bijg : menuBijGerecht.get(ta).keySet())
            {
                  db.nieuwBijgerechtAanBestelling(bijg, menu, max, menuBijGerecht.get(ta).get(bijg));
            }
          }
          if (getMenuSnacks().get(ta) != null)
          {
            for (String snacks : menuSnacks.get(ta).keySet())
            {
                  db.nieuweSnackAanBestelling(snacks, menu, max, menuSnacks.get(ta).get(snacks));
            }
          }
          if (getMenuDranken().get(ta) != null)
          {
            for (String dr : menuDranken.get(ta).keySet())
            {
                  db.nieuweDrankAanBestelling(dr, menu, max, menuDranken.get(ta).get(dr));
            }
          }
       }
       // kortingen aan bestelling toevoegen
       // registratiekorting
       if (registratieKorting != 0.00)
       {
           db.nieuweKortingBijBestelling(max, registatieKortingsCode);
       }
       // unieke actiekorting
       if (uniekeActieKorting != 0.00)
       {
           db.nieuweKortingBijBestelling(max, uniekeActieKortingsCode);
           if (db.getPeriodeBijUniekeActieKortingscode(uniekeActieKortingsCode).equalsIgnoreCase("eenmalig"))
           {
               db.nieuweKortingBijKlantLogin(klantLogin, uniekeActieKortingsCode);
           }
       }
       // jfbosskortingscode
       if (jfBossKorting != 0.00)
       {
           db.nieuweKortingBijBestelling(max, jfBossKortingscode);
       }
       // takeawaybosskortingscode
       if (takeAwayBossKorting != 0.00)
       {
           db.nieuweKortingBijBestelling(max, takeAwayBossKortingscode);
       }
       // reviewkorting
       if (reviewKorting != 0.00)
       {
           db.nieuweKortingBijBestelling(max, reviewKortingscode);
       }
       // kortingscodes naar passief
       if (!(naarpassiefKortingen.isEmpty()))
       {
           for (String kort : naarpassiefKortingen)
           {
               db.updateStatusNaarPassiefKortingscode(kort);
           }
       }
       
    }
    
    
    
    
    

    
    
    
    
    
    
   
}
