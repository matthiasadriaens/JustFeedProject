package DATABASE_EN_ENTITEITEN;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hberouag
 */
public class Klant 
{
    private String klantVoornaam ;
    private String klantAchternaam ;
    private String klantEmail ;
    private String klantLogin ;
    private String klantPaswoord ;
    private String klantTelefoonnummer ;
    private String klantStraat ;
    private int klantHuisnummer ;
    private int klantPostcode ;
    private String klantGemeente ;
    
    DB db = new DB() ;

   
    // geen telefoonnummer
    public Klant(String klantVoornaam, String klantAchternaam, String klantEmail, String klantLogin, String klantPaswoord, String klantStraat, int klantHuisnummer, int klantPostcode, String klantGemeente) {
        this.klantVoornaam = klantVoornaam;
        this.klantAchternaam = klantAchternaam;
        this.klantEmail = klantEmail;
        this.klantLogin = klantLogin;
        this.klantPaswoord = klantPaswoord;
        this.klantStraat = klantStraat;
        this.klantHuisnummer = klantHuisnummer;
        this.klantPostcode = klantPostcode;
        this.klantGemeente = klantGemeente;
    }
    // alles
    public Klant(String klantVoornaam, String klantAchternaam, String klantEmail, String klantLogin, String klantPaswoord, String klantTelefoonnummer, String klantStraat, int klantHuisnummer, int klantPostcode, String klantGemeente) {
        this.klantVoornaam = klantVoornaam;
        this.klantAchternaam = klantAchternaam;
        this.klantEmail = klantEmail;
        this.klantLogin = klantLogin;
        this.klantPaswoord = klantPaswoord;
        this.klantTelefoonnummer = klantTelefoonnummer;
        this.klantStraat = klantStraat;
        this.klantHuisnummer = klantHuisnummer;
        this.klantPostcode = klantPostcode;
        this.klantGemeente = klantGemeente;
    }
    public Klant(String klantLogin, String klantPaswoord)
    {
        this.klantLogin = klantLogin;
        this.klantPaswoord = klantPaswoord;
    }

    public String getKlantVoornaam() {
        return klantVoornaam;
    }

    public void setKlantVoornaam(String klantVoornaam) {
        this.klantVoornaam = klantVoornaam;
    }

    public String getKlantAchternaam() {
        return klantAchternaam;
    }

    public void setKlantAchternaam(String klantAchternaam) {
        this.klantAchternaam = klantAchternaam;
    }

    public String getKlantEmail() {
        return klantEmail;
    }

    public void setKlantEmail(String klantEmail) {
        this.klantEmail = klantEmail;
    }

    public String getKlantLogin() {
        return klantLogin;
    }

    public void setKlantLogin(String klantLogin) {
        this.klantLogin = klantLogin;
    }

    public String getKlantPaswoord() {
        return klantPaswoord;
    }

    public void setKlantPaswoord(String klantPaswoord) {
        this.klantPaswoord = klantPaswoord;
    }

    public String getKlantTelefoonnummer() {
        return klantTelefoonnummer;
    }

    public void setKlantTelefoonnummer(String klantTelefoonnummer) {
        this.klantTelefoonnummer = klantTelefoonnummer;
    }

    public String getKlantStraat() {
        return klantStraat;
    }

    public void setKlantStraat(String klantStraat) {
        this.klantStraat = klantStraat;
    }

    public int getKlantHuisnummer() {
        return klantHuisnummer;
    }

    public void setKlantHuisnummer(int klantHuisnummer) {
        this.klantHuisnummer = klantHuisnummer;
    }

    public int getKlantPostcode() {
        return klantPostcode;
    }

    public void setKlantPostcode(int klantPostcode) {
        this.klantPostcode = klantPostcode;
    }

    public String getKlantGemeente() {
        return klantGemeente;
    }

    public void setKlantGemeente(String klantGemeente) {
        this.klantGemeente = klantGemeente;
    }
    
    public void addKlant()
    {
        db.nieuweKlant(klantLogin, klantVoornaam, klantAchternaam, klantPaswoord, klantEmail, klantStraat, klantHuisnummer, klantPostcode, klantGemeente, klantTelefoonnummer);
    }
    
    
}
