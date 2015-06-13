package DATABASE_EN_ENTITEITEN;


import java.util.ArrayList;

public class TakeAway {
  
    private String naamTakeAway ;
    private String paswoordTakeAway ;
    private int vanafBepaaldBedragKorting;
    private double gegevenKorting;
    private int aantalBestellingenTA;
    private int kortingscode;
    private String email ;
    private Menu menu ;
    private ArrayList<String> categorien ;
    private ArrayList<Vestiging> vestigingen ;
    
    DB db = new DB() ;

    // geen vanafBepaaldBedragKorting,gegevenKorting,aantalBestellingenTA,kortingscode
    public TakeAway(String naamTakeAway, String paswoordTakeAway, Menu menu) {
        this.naamTakeAway = naamTakeAway;
        this.paswoordTakeAway = paswoordTakeAway;
        this.menu = menu;
        aantalBestellingenTA = 0 ;
        categorien = new ArrayList() ;
        vestigingen = new ArrayList() ;
    }
    // geen vanafBepaaldBedragKorting,gegevenKorting,kortingscode

    public TakeAway(String naamTakeAway, String paswoordTakeAway, int aantalBestellingenTA, Menu menu) {
        this.naamTakeAway = naamTakeAway;
        this.paswoordTakeAway = paswoordTakeAway;
        this.aantalBestellingenTA = aantalBestellingenTA;
        this.menu = menu;
        categorien = new ArrayList() ;
        vestigingen = new ArrayList() ;
    }

    public TakeAway(String naamTakeAway, String paswoordTakeAway, int vanafBepaaldBedragKorting, double gegevenKorting,String em, Menu menu) {
        this.naamTakeAway = naamTakeAway;
        this.paswoordTakeAway = paswoordTakeAway;
        this.vanafBepaaldBedragKorting = vanafBepaaldBedragKorting;
        this.gegevenKorting = gegevenKorting;
        this.aantalBestellingenTA = 0 ;
        this.menu = menu;
        email = em ;
        categorien = new ArrayList() ;
        vestigingen = new ArrayList() ;
    }

    public String getNaamTakeAway() {
        return naamTakeAway;
    }

    public void setNaamTakeAway(String naamTakeAway) {
        this.naamTakeAway = naamTakeAway;
    }

    public String getPaswoordTakeAway() {
        return paswoordTakeAway;
    }

    public void setPaswoordTakeAway(String paswoordTakeAway) {
        this.paswoordTakeAway = paswoordTakeAway;
    }

    public int getVanafBepaaldBedragKorting() {
        return vanafBepaaldBedragKorting;
    }

    public void setVanafBepaaldBedragKorting(int vanafBepaaldBedragKorting) {
        this.vanafBepaaldBedragKorting = vanafBepaaldBedragKorting;
    }

    public double getGegevenKorting() {
        return gegevenKorting;
    }

    public void setGegevenKorting(int gegevenKorting) {
        this.gegevenKorting = gegevenKorting;
    }

    public int getAantalBestellingenTA() {
        return aantalBestellingenTA;
    }

    public void setAantalBestellingenTA(int aantalBestellingenTA) {
        this.aantalBestellingenTA = aantalBestellingenTA;
    }

    public int getKortingscode() {
        return kortingscode;
    }

    public void setKortingscode(int kortingscode) {
        this.kortingscode = kortingscode;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ArrayList<String> getCategorien() {
        return categorien;
    }

    public void setCategorien(ArrayList<String> categorien) {
        this.categorien = categorien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void addVestiginInArray(Vestiging v)
    {
        vestigingen.add(v) ;
    }

    public void addTakeAway() 
    {
        db.nieuweTakeAway(naamTakeAway, vanafBepaaldBedragKorting, gegevenKorting, aantalBestellingenTA, paswoordTakeAway, email);
    }
    public void addCategorie(String cat)
    {
       categorien.add(cat) ;
       db.nieuweCategorieBijTakeaway(naamTakeAway, cat);
    }
    public void addVestiging(Vestiging v)
    {
        v.addVestiging(naamTakeAway);
    }
    
    
    
    
}
    
    