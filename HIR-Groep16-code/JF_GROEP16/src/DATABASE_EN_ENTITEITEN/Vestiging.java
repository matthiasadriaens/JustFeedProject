package DATABASE_EN_ENTITEITEN;


import java.util.ArrayList;
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
public class Vestiging 
{
    private int VestigingsID ;
    private double leveringskosten ;
    private int aantalBestellingen ;
    // leveringsgebied
    private String straat ;
    private int huisnummer ;
    private int postcode ;
    private String gemeente ;
    private ArrayList<Integer> leveringsgebiedenpostcodes;
    private ArrayList<String> leveringsgebiedengemeentes ;
    
    DB db = new DB() ;

    public Vestiging(String straat, int huisnummer, int postcode, String gemeente, double leveringskosten) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.leveringskosten = leveringskosten ;
        aantalBestellingen = 0 ;
        leveringsgebiedenpostcodes = new ArrayList() ;
        leveringsgebiedengemeentes = new ArrayList() ;
    }

    public int getVestigingsID() {
        return VestigingsID;
    }

    public void setVestigingsID(int VestigingsID) {
        this.VestigingsID = VestigingsID;
    }

    public double getLeveringskosten() {
        return leveringskosten;
    }

    public void setLeveringskosten(double leveringskosten) {
        this.leveringskosten = leveringskosten;
    }

    public int getAantalBestellingen() {
        return aantalBestellingen;
    }

    public void setAantalBestellingen(int aantalBestellingen) {
        this.aantalBestellingen = aantalBestellingen;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public ArrayList<Integer> getLeveringsgebiedenpostcodes() {
        return leveringsgebiedenpostcodes;
    }

    public void setLeveringsgebiedenpostcodes(ArrayList<Integer> leveringsgebiedenpostcodes) {
        this.leveringsgebiedenpostcodes = leveringsgebiedenpostcodes;
    }

    public ArrayList<String> getLeveringsgebiedengemeentes() {
        return leveringsgebiedengemeentes;
    }

    public void setLeveringsgebiedengemeentes(ArrayList<String> leveringsgebiedengemeentes) {
        this.leveringsgebiedengemeentes = leveringsgebiedengemeentes;
    }
    public void addVestiging(String nTA) 
    {
       db.nieuweVestigingBijTakeAway(leveringskosten, aantalBestellingen, nTA,straat,huisnummer,postcode,gemeente,leveringsgebiedenpostcodes, leveringsgebiedengemeentes);
    }
    
    
    
    
}
