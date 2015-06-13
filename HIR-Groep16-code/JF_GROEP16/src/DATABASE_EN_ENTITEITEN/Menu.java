package DATABASE_EN_ENTITEITEN;



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
public class Menu 
{
    private  int menuID ;
    private HashMap<String, Double> gerechten ;// naam prijs
    private HashMap<String, Double> bijgerechten ; // naam prijs
    private HashMap<String, Double> snacks ; // naam prijs
    private HashMap<String, Double> dranken ; // naam prijs
    
    DB db = new DB() ;

    public Menu(int menuID, HashMap<String, Double> gerechten, HashMap<String, Double> bijgerechten, HashMap<String, Double> snacks, HashMap<String, Double> dranken) {
        this.menuID = menuID;
        this.gerechten = gerechten;
        this.bijgerechten = bijgerechten;
        this.snacks = snacks;
        this.dranken = dranken;
    }

    public Menu(HashMap<String, Double> gerechten, HashMap<String, Double> bijgerechten, HashMap<String, Double> snacks, HashMap<String, Double> dranken) {
        this.gerechten = gerechten;
        this.bijgerechten = bijgerechten;
        this.snacks = snacks;
        this.dranken = dranken;
    }
    

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public HashMap<String, Double> getGerechten() {
        return gerechten;
    }

    public void setGerechten(HashMap<String, Double> gerechten) {
        this.gerechten = gerechten;
    }

    public HashMap<String, Double> getBijgerechten() {
        return bijgerechten;
    }

    public void setBijgerechten(HashMap<String, Double> bijgerechten) {
        this.bijgerechten = bijgerechten;
    }

    public HashMap<String, Double> getSnacks() {
        return snacks;
    }

    public void setSnacks(HashMap<String, Double> snacks) {
        this.snacks = snacks;
    }

    public HashMap<String, Double> getDranken() {
        return dranken;
    }

    public void setDranken(HashMap<String, Double> dranken) {
        this.dranken = dranken;
    }
    
    public void fillMenu(String naamTA) 
    {
        menuID = db.getMenuIDTakeAway(naamTA) ;
        for (String s: gerechten.keySet())
        {
             db.nieuwGerechtBijMenu(s, gerechten.get(s), 0, menuID);
        }
        for (String s: bijgerechten.keySet())
        {
             db.nieuwBijgerechtBijMenu(s, bijgerechten.get(s), 0, menuID);
        }
        for (String s: snacks.keySet())
        {
             db.nieuwSnackBijMenu(s, snacks.get(s), 0, menuID);
        }
        for (String s: dranken.keySet())
        {
             db.nieuweDrankBijMenu(s, dranken.get(s), 0, menuID);
        }
       
    }

    
    
    
    
    
    
    
    
}
