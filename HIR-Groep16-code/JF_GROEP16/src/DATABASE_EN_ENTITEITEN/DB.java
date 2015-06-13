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
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class DB {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private int menuID;
    
    public DB()
    {
   
    }
    
    public Connection getConnection()
    {
            try
            {
                String protocol = "jdbc" ;
                String subProtocol = "mysql" ;
                String subName = "//mysqlha2.ugent.be/BINFG16?user=BINFG16&password=DOTC0R4e";


                String URL = protocol +":"+ subProtocol +":"+ subName; 

                Class.forName("com.mysql.jdbc.Driver").newInstance();

                con = DriverManager.getConnection(URL);

                System.out.println("Database connection succes!"); 
            }

            catch(Exception ex)
                {
                    System.out.println("Error : " + ex);
                }
            
            return con;
    }
    
    public void nieuweTakeAway(String TakeAway,int vanafBepaaldBedragKorting, double gegevenKorting,int aantalBestellingenTA,String paswoord, String email)
    {
        nieuweMenu();
        con = null;
        int hoogsteMenu = getMaximumMenuID();
        
        try
        {      
            con = getConnection();
            
            String query = " insert into TakeAway  (naamTakeAway,vanafBepaaldBedragKorting,gegevenKorting,aantalBestellingenTA,paswoord,menuID,emailTakeAway)"
                             + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, TakeAway);
            preparedStmt.setInt(2, vanafBepaaldBedragKorting);
            preparedStmt.setDouble(3, gegevenKorting);
            preparedStmt.setInt(4, aantalBestellingenTA);
            preparedStmt.setString(5, paswoord);
            preparedStmt.setInt(6, hoogsteMenu);
            preparedStmt.setString(7,email);
       
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error takeaway toevoegen: " + ex);
            }
    }
    
    public void nieuweMenu()
    {
        con = null;
        
        try
        {     
            con = getConnection();
            
            String query = "insert into Menu VALUES (NULL)";

            
            PreparedStatement preparedStmt = con.prepareStatement(query);

            
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error menu toevoegen:" + ex);
            }
    }
    
    public void nieuweVestigingBijTakeAway(double leveringskosten,int aantalBestellingenV, String naamTakeAway,String straat, int huisnummer, int postcode, String gemeente, ArrayList<Integer> postcodes,ArrayList<String> gemeentes)
    {
        con = null;
        try
        {
            con = getConnection();
            String query = "insert into Vestiging (leveringskosten,aantalBestellingenV,naamTakeAway,straat,huisnummer,postcode,gemeente)" + "values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDouble(1,leveringskosten);
            preparedStmt.setInt(2, aantalBestellingenV);
            preparedStmt.setString(3, naamTakeAway);
            preparedStmt.setString(4,straat);
            preparedStmt.setInt(5,huisnummer);
            preparedStmt.setInt(6,postcode);
            preparedStmt.setString(7,gemeente);

            preparedStmt.execute();

            con.close();
        }
        catch(Exception ex)
        {
            try {con.close();} catch(Exception ex2) {}
            System.out.println("Error vestiging bij TakeAway Toevoegen:" + ex);
        }

        int leveringsgebied =  getMaximumVID();

        for (int x =0; x<gemeentes.size();x++)
        {
            nieuwLeveringsgebiedBijVestiging(leveringsgebied, postcodes.get(x), gemeentes.get(x));
        }

    }
    
   
    
    public void nieuwGerechtBijMenu(String naamGerecht, double prijs,int aantalBesteld, int menuID)
    {
        con = null;
        
        try
        {       
            con = getConnection();
            String query = "insert into Gerechten (naamGerecht,prijs,aantalBesteld,menuID)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, naamGerecht);
            preparedStmt.setDouble(2,prijs);
            preparedStmt.setInt(3, aantalBesteld);
            preparedStmt.setInt(4, menuID);
            
            preparedStmt.execute();
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error gerecht bij menu toevoegen:" + ex);
            }
    }
    
    public void nieuwBijgerechtBijMenu(String naamBijgerecht, double prijs,int aantalBesteld, int menuID)
    {
        con = null;
        
        try
        {      
            con = getConnection();
            String query = "insert into Bijgerechten (naamBijgerecht,prijs,aantalBesteld,menuID)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, naamBijgerecht);
            preparedStmt.setDouble(2,prijs);
            preparedStmt.setInt(3, aantalBesteld);
            preparedStmt.setInt(4, menuID);
            
            preparedStmt.execute();
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error Bijgerecht bij menu toevoegen:" + ex);
            }
    }
    
    public void nieuweDrankBijMenu(String naamDrank, double prijs,int aantalBesteld, int menuID)
    {
        con = null;
        
        try
        {   
            con = getConnection();
            String query = "insert into Dranken (naamDrank,prijs,aantalBesteld,menuID)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,naamDrank);
            preparedStmt.setDouble(2,prijs);
            preparedStmt.setInt(3, aantalBesteld);
            preparedStmt.setInt(4, menuID);
            
            preparedStmt.execute();
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error drank bij menu toevoegen:" + ex);
            }
    }
    
    public void nieuwSnackBijMenu(String naamSnack, double prijs,int aantalBesteld, int menuID)
    {
        con = null;
        
        try
        {        
            con = getConnection();
            String query = "insert into Snacks (naamSnack,prijs,aantalBesteld,menuID)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, naamSnack);
            preparedStmt.setDouble(2,prijs);
            preparedStmt.setInt(3,aantalBesteld);
            preparedStmt.setInt(4, menuID);
            
            preparedStmt.execute();
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error Snack bij menu toevoegen:" + ex);
            }
    }
    
    public int getAantalBesteldGerecht(String naamGerecht, int menuID)
    {
        int aantalBesteld = 0;
        con = null;
        
        
        try
        {
            con = getConnection();
            
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBesteld FROM Gerechten WHERE naamGerecht = '" + naamGerecht +"' AND menuID =  '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    aantalBesteld = rs.getInt("aantalBesteld");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal bestelde gerechten:" + ex);
            }
        
        return aantalBesteld;
    }
    
    public int getAantalBesteldBijgerecht(String naamBijgerecht, int menuID)
    {
        int aantalBesteld = 0;
        con = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBesteld FROM Bijgerechten WHERE naamBijgerecht = '" + naamBijgerecht +"' AND menuID =  '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            while(rs.next())
                {
                    aantalBesteld = rs.getInt("aantalBesteld");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal bestelde bijgerechten:" + ex);
            }
        
        return aantalBesteld;
    }
    
    public int getAantalBesteldDrank(String naamDrank, int menuID)
    {
        int aantalBesteld = 0;
        con = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBesteld FROM Dranken WHERE naamDrank = '" + naamDrank +"' AND menuID =  '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            while(rs.next())
                {
                    aantalBesteld = rs.getInt("aantalBesteld");
                }
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het ophalen van aantal bestelde drank:" + ex);
            }
        
        return aantalBesteld;
    }
    
    public int getAantalBesteldSnack(String naamSnack, int menuID)
    {
        int aantalBesteld = 0;
        con = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBesteld FROM Snacks WHERE naamSnack = '" + naamSnack +"' AND menuID =  '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            while(rs.next())
                {
                    aantalBesteld = rs.getInt("aantalBesteld");
                }
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error:" + ex);
            }
        
        return aantalBesteld;
    }
    
     public void updateAantalBesteldGerechten(String naamGerecht,int menuID, int aantalBesteld)
    {
        int aantal = 0;
        aantal += getAantalBesteldGerecht(naamGerecht,menuID);
        aantal += aantalBesteld;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Gerechten SET aantalBesteld = '" + aantal + "' WHERE naamGerecht = '" + naamGerecht + "' AND menuID = '" + menuID + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van het anatal bestelde gerechten:" + ex);
            }
        
    
    }
        
     public void updateAantalBesteldBijgerechten(String naamBijgerecht,int menuID, int aantalBesteld)
    {
        int aantal = 0;
        aantal += getAantalBesteldBijgerecht(naamBijgerecht,menuID);
        aantal += aantalBesteld;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Bijgerechten SET aantalBesteld = '" + aantal + "' WHERE naamBijgerecht = '" + naamBijgerecht + "' AND menuID = '" + menuID + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van het anatal bestelde bijgerechten:" + ex);
            }
        
    
    }
     
     public void updateAantalBesteldDrank(String naamDrank,int menuID, int aantalBesteld)
    {
        int aantal = 0;
        aantal += getAantalBesteldDrank(naamDrank,menuID);
        aantal += aantalBesteld;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Dranken SET aantalBesteld = '" + aantal + "' WHERE naamDrank = '" + naamDrank + "' AND menuID = '" + menuID + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                System.out.println("Error bij het updaten van het anatal bestelde dranken:" + ex);
                try {con.close();} catch(Exception ex2) {}
            }
        
    
    }
     
     public void updateAantalBesteldSnack(String naamSnack,int menuID, int aantalBesteld)
    {
        int aantal = 0;
        aantal += getAantalBesteldSnack(naamSnack,menuID);
        aantal += aantalBesteld;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Snacks SET aantalBesteld = '" + aantal + "' WHERE naamSnack = '" + naamSnack + "' AND menuID = '" + menuID + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van het anatal bestelde snacks:" + ex);
            }
        
    
    }
     
     public String getGerechtenMenuID(int menuID)
     {
         String Gerechten = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht FROM Gerechten WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    Gerechten += rs.getString("naamGerecht") + "\n";
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de Gerechten uit menu met " + menuID + " als ID geeft error:" + ex);
            }
         
         
         return Gerechten;
     }
     
     public String getBijgerechtenMenuID(int menuID)
     {
         String Bijgerechten = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamBijgerecht FROM Bijgerechten WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    Bijgerechten += rs.getString("naamBijgerecht") + "\n";
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de Bijgerechten uit menu met " + menuID + " als ID geeft error:" + ex);
            }
         
         
         return Bijgerechten;
     }
     
     public String getDrankenMenuID(int menuID)
     {
         String Dranken = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamDrank FROM Dranken WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    Dranken += rs.getString("naamDrank") + "\n";
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de Dranken uit menu met " + menuID + " als ID geeft error:" + ex);
            }
         
         
         return Dranken;
     }
     
     public String getSnacksMenuID(int menuID)
     {
         String Snacks = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamSnack FROM Snacks WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    Snacks += rs.getString("naamSnack") + "\n";
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                System.out.println("Error bij opvragen van de Snacks uit menu met " + menuID + " als ID geeft error:" + ex);
                try {con.close();} catch(Exception ex2) {}
            }
         
         
         return Snacks;
     }
     
     public String getVestigingenTakeAway(String naamTakeAway)
     {
         String Vestigingen = "";
 
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT leveringsgebied FROM Vestiging WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    Vestigingen += naamTakeAway + " " + rs.getString("leveringsgebied") + "\n";
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de vestigingen van takeaway :  " + naamTakeAway + ":" + ex);
            }
        
         return Vestigingen;
     }
     
    public String getVolledigMenu(int menuID)
    {
        String VolledigMenu = "Gerechten:\n";
        
        VolledigMenu += getGerechtenMenuID(menuID) + "\n";
        VolledigMenu += "Bijgerechten:\n" + getBijgerechtenMenuID(menuID) +"\n";
        VolledigMenu += "Snacks:\n" + getSnacksMenuID(menuID) + "\n";
        VolledigMenu +=  "Dranken:\n" + getDrankenMenuID(menuID);
        
        return VolledigMenu;
    }
    
    public int getAantalOrdersTakeAway(String naamTakeAway)
    {
        int AantalOrders =0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBestellingenTA FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    AantalOrders = rs.getInt("aantalBestellingenTA");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal orders voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        
        return AantalOrders;
    }
    
    public int getKortingBovenBedragTakeAway(String naamTakeAway)
    {
        int kortingBovenBedrag =0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT vanafBepaaldBedragKorting FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    kortingBovenBedrag = rs.getInt("vanafBepaaldBedragKorting");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van korting boven bedrag voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        
        return kortingBovenBedrag;
    }
    
    public int getKortingscodeTakeAway(String naamTakeAway)
    {
        int kortingscode = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT kortingscode FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    kortingscode = rs.getInt("kortingscode");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van menuID voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        
        return kortingscode;
    }
    
    public int getMenuIDTakeAway(String naamTakeAway)
    {
        int menuID = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT menuID FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    menuID = rs.getInt("menuID");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van menuID voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        return menuID;
    }
    
    public String getNaamTakeAwayBijMenuID(int menuID)
    {
        String naamTakeAway = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM TakeAway WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    naamTakeAway = rs.getString("naamTakeAway");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van naam takeaway met menuID:  " + menuID + " , error:" + ex);
            }
        return naamTakeAway;
    }
    
    public  void updateAantalOrdersTakeAway(String naamTakeAway, int aantalBestellingenTA)
    //default 1, maar misschien komt het ruimer definiëren van aantalOrders later van pas
    {
        int updatedOrderAaantal = 0;
        updatedOrderAaantal += getAantalOrdersTakeAway(naamTakeAway);
        updatedOrderAaantal += aantalBestellingenTA;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET aantalBestellingenTA = '" + updatedOrderAaantal + "' WHERE naamTakeAway = '" + naamTakeAway + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten aantal orders van takeaway met naam: " + naamTakeAway + " ,Error:" + ex);
            }
        
    }
    
    public double getPrijsGerecht(String naamGerecht, int menuID)
    //al op double gezet, nog integer in mySQL-db
    {
        double prijsGerecht = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT prijs FROM Gerechten WHERE naamGerecht = '" + naamGerecht + "' AND menuID = '" + menuID + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    prijsGerecht = rs.getDouble("prijs");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van prijs gerecht:  " + naamGerecht + " en menuID: " + menuID + " , error:" + ex);
            }
        return prijsGerecht;
    }
    
    public double getPrijsBijgerecht(String naamBijgerecht, int menuID)
    //al op double gezet, nog integer in mySQL-db
    {
        double prijsBijgerecht = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT prijs FROM Bijgerechten WHERE naamBijgerecht = '" + naamBijgerecht + "' AND menuID = '" + menuID + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    prijsBijgerecht = rs.getDouble("prijs");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van prijs  bijgerecht:  " + naamBijgerecht + " en menuID: " + menuID + " , error:" + ex);
            }
        return prijsBijgerecht;
    }
    
    public double getPrijsDrank(String naamDrank, int menuID)
    //al op double gezet, nog integer in mySQL-db
    {
        double prijsDrank = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT prijs FROM Dranken WHERE naamDrank = '" + naamDrank + "' AND menuID = '" + menuID + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    prijsDrank = rs.getDouble("prijs");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van prijs  drank:  " + naamDrank + " en menuID: " + menuID + " , error:" + ex);
            }
        return prijsDrank;
    }
    
    public double getPrijsSnack(String naamSnack, int menuID)
    //al op double gezet, nog integer in mySQL-db
    {
        double prijsSnack = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT prijs FROM Snacks WHERE naamSnack = '" + naamSnack + "' AND menuID = '" + menuID + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    prijsSnack = rs.getDouble("prijs");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van prijs bij snack:  " + naamSnack + " en menuID: " + menuID + " , error:" + ex);
            }
        return prijsSnack;
    }
    
    public void nieuweKlant(String klantLogin,String voornaam, String achternaam,String paswoord,
                            String email, String straat, int huisnummer,int postcode,String gemeente,
                            String telefoonnummer)
    {
        con = null;
        
        try
        {     
            con = getConnection();
            
            //nieuweGemeente(postcode, gemeente);
            
            String query = "INSERT into Klant (klantLogin,voornaam,achternaam,paswoord,email,straat,huisnummer"
                    + ",postcode,gemeente,telefoonnummer)" + "values (?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, klantLogin);
            preparedStmt.setString(2, voornaam);
            preparedStmt.setString(3, achternaam);
            preparedStmt.setString(4, paswoord);
            preparedStmt.setString(5, email);
            preparedStmt.setString(6,straat);
            preparedStmt.setInt(7, huisnummer);
            preparedStmt.setInt(8, postcode);
            preparedStmt.setString(9, gemeente);
            preparedStmt.setString(10, telefoonnummer);
            
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error klant toevoegen:" + ex);
            }
    }
    
    public void nieuweGemeente(int postcode, String gemeente)
    {
        con = null;
        
        try
        {     
            con = getConnection();
            
            String query = "INSERT into Gemeenten (postcode,gemeente)" + "values (?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, postcode);
            preparedStmt.setString(2, gemeente);
            
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error gemeente toevoegen:" + ex);
            }
    }
    
    public void nieuweReview(double score, String tekst,int likes,String klantLogin,int menuID,String naamGerecht)
    {
        con = null;
        
        try
        {     
            con = getConnection();
            
            String query = "INSERT into Review (score,tekst,likes,klantLogin,menuID,naamGerecht)" + "values (?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDouble(1, score);
            preparedStmt.setString(2, tekst);
            preparedStmt.setInt(3, likes);
            preparedStmt.setString(4,klantLogin);
            preparedStmt.setInt(5,menuID);
            preparedStmt.setString(6,naamGerecht);
            
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error review toevoegen:" + ex);
            }
        
    }
    
    public void updateScoreReview(int reviewID, Double nieuweScore)
    {
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Review SET score = '" + nieuweScore + "' WHERE reviewID = '" + reviewID +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van score van review:" + ex);
            }
    }
    
    public void nieuweBestelling(int bestellingsID, Date datum,double totaalbedrag,
            String leveringsstraat,int leveringsHuisnummer,int postcode,String gemeente,String klantLogin,
            ArrayList<Integer> vestigingen)
    /*Maakt een bestelling aan in de Bestelling-table en maakt ook een tupel aan in de 
     *GeplaatstBij-table zodat een bestelling aan een vestiging wordt gekoppeld.
     *Het totaal aantal bestelde items van een vestiging en takeaway worden ook aangepast
     */
    {
        con = null;
        
        try
        {     
            con = getConnection();
           
            String query = "INSERT into Bestelling (bestellingsID,datum,totaalbedrag,leveringsstraat"
                    + ",leveringsHuisnummer,postcode,gemeente,klantLogin)" + "values (?,?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, bestellingsID);
            preparedStmt.setDate(2, datum);
            preparedStmt.setDouble(3, totaalbedrag);
            preparedStmt.setString(4,leveringsstraat );
            preparedStmt.setInt(5,leveringsHuisnummer);
            preparedStmt.setInt(6,postcode);
            preparedStmt.setString(7,gemeente);
            preparedStmt.setString(8, klantLogin);
            
            preparedStmt.execute();
            System.out.println(totaalbedrag);
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error review toevoegen:" + ex);
            }
        
        //updaten van alle gegevens bij de verschillende vestigingen
        for (Integer x: vestigingen)
        {
            nieuweBestellingBijVestiging(x, bestellingsID);
            updateAantalBesteldVestiging(x);
            updateAantalBesteldTakeAway(getTakeAwayVanVestiging(x));
        }
    }
    
   
    
    public void nieuwGerechtAanBestelling(String naamGerecht,int menuID,int bestellingsID, int aantalGerechten)
    //maakt een nieuwe rij in de 'gerechtBehoortTot'-tabel en update het totaalbedrag van de bestelling 
    //en pas het aant totaal keren besteld van een bepaald gerecht in het systeem
    {
        con = null;
        
        //insert in de "gerechtBehoortTot"-table
        try
        {     
            con = getConnection();
            
            String query = "INSERT into GerechtenBehoortTot (naamGerecht,menuID,bestellingsID"
                    + ",aantalGerechten)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,  naamGerecht);
            preparedStmt.setInt(2, menuID);
            preparedStmt.setInt(3, bestellingsID);
            preparedStmt.setInt(4,aantalGerechten);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error insert in gerechtenBehoortTot:" + ex);
            }
        
       
        
        //update het aantal keren dat het bepaalde gerecht besteld is in het systeem (in de 'gerechten'-table)
        updateAantalBesteldGerechten(naamGerecht, menuID, aantalGerechten);      
        
    }
    
    public void nieuwBijgerechtAanBestelling(String naamBijgerecht,int menuID,int bestellingsID, int aantalBijgerechten)
    //maakt een nieuwe rij in de 'BijgerechtBehoortTot'-tabel en update het totaalbedrag van de bestelling 
    {
        con = null;
        
        //insert in de "BijgerechtBehoortTot"-table
        try
        {     
            con = getConnection();
            
            String query = "INSERT into BijgerechtenBehoortTot (naamBijgerecht,menuID,bestellingsID"
                    + ",aantalBijgerechten)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,  naamBijgerecht);
            preparedStmt.setInt(2, menuID);
            preparedStmt.setInt(3, bestellingsID);
            preparedStmt.setInt(4,aantalBijgerechten);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error insert in bijgerechtenBehoortTot:" + ex);
            }
        
        
        
        //update het aantal keren dat het bepaalde gerecht besteld is in het systeem (in de 'gerechten'-table)
        updateAantalBesteldBijgerechten(naamBijgerecht, menuID, aantalBijgerechten);
    }
    
    public double getTotaalbedragBestelling(double bestellingsID)
    {
        double totaalbedrag = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT totaalbedrag FROM Bestelling WHERE bestellingsID = '" + bestellingsID +  "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    totaalbedrag = rs.getDouble("totaalbedrag");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van totaalbedrag bij bestelling:  " + bestellingsID +  "error:" + ex);
            }
        
        return totaalbedrag;
    }
    
    public void updateTotaalbedragBestelling(int bestellingsID, double toeTeVoegenBedrag)
    {
        double nieuwTotaalbedrag = getTotaalbedragBestelling(bestellingsID) + toeTeVoegenBedrag;
             
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Bestelling SET totaalbedrag = '" + nieuwTotaalbedrag + "' WHERE bestellingsID = '" + bestellingsID + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten totaalbedrag van bestelling met ID: " + bestellingsID + " ,Error:" + ex);
            }
        
    }
    
    public void nieuweDrankAanBestelling(String naamDrank,int menuID,int bestellingsID, int aantalDranken)
    //maakt een nieuwe rij in de 'DrankenbehoortTot'-tabel en update het totaalbedrag van de bestelling 
    //update ook het aantal bestelde dranken in het systeem
    {
        con = null;
        
        //insert in de "DrankentBehoortTot"-table
        try
        {     
            con = getConnection();
            
            String query = "INSERT into DrankenBehoortTot (naamDrank,menuID,bestellingsID"
                    + ",aantalDranken)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,  naamDrank);
            preparedStmt.setInt(2, menuID);
            preparedStmt.setInt(3, bestellingsID);
            preparedStmt.setInt(4,aantalDranken);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error insert in DrankenBehoortTot:" + ex);
            }
        
       
        
        //update het aantal keren dat het bepaalde gerecht besteld is in het systeem (in de 'gerechten'-table)
        updateAantalBesteldDrank(naamDrank, menuID, aantalDranken);
    }
    
    
    public void nieuweSnackAanBestelling(String naamSnack,int menuID,int bestellingsID, int aantalSnacks)
    //maakt een nieuwe rij in de 'SnacksbehoortTot'-tabel en update het totaalbedrag van de bestelling 
    //update ook het aantal bestelde snacks in het systeem
    {
        con = null;
        
        //insert in de "SnacksBehoortTot"-table
        try
        {     
            con = getConnection();
            
            String query = "INSERT into SnacksBehoortTot (naamSnack,menuID,bestellingsID"
                    + ",aantalSnacks)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,  naamSnack);
            preparedStmt.setInt(2, menuID);
            preparedStmt.setInt(3, bestellingsID);
            preparedStmt.setInt(4,aantalSnacks);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error insert in SnacksBehoortTot:" + ex);
            }
        
        
        
        //update het aantal keren dat het bepaalde gerecht besteld is in het systeem (in de 'gerechten'-table)
        updateAantalBesteldSnack(naamSnack, menuID, aantalSnacks);
    }
    
    public void nieuweBestellingBijVestiging(int vestigingsID,int bestellingsID)
    //koppelt een bestelling aan een vestiging via het updaten van de 'GeplaatstBij'-table
    {
        con = null;
        
        try
        {     
            con = getConnection();
            
            String query = "INSERT into GeplaatstBij (vestigingsID,bestellingsID)" + "values (?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,  vestigingsID);
            preparedStmt.setInt(2, bestellingsID);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error insert in GeplaatstBij:" + ex);
            }
    }
    
    public void nieuweCategorieBijTakeaway(String naamTakeaway,String categorie)
    {
        try
        {     
            con = getConnection();
            
            String query = "INSERT into Soort (naamTakeaway,categorie)" + "values (?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,  naamTakeaway);
            preparedStmt.setString(2, categorie);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij insert in Soort (categorie toevoegen bij takeaway):" + ex);
            }
    }     
    
    public void nieuwLeveringsgebiedBijVestiging(int vestigingsID,int postcode, String gemeente)
    {
        try
        {     
            con = getConnection();
            
            String query = "INSERT into Leveringsgebied (vestigingsID,postcode,gemeente)" + "values (?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,  vestigingsID);
            preparedStmt.setInt(2, postcode);
            preparedStmt.setString(3, gemeente);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij insert in Leveringsgebied (leveringsgebied toevoegen bij vestiging):" + ex);
            }
    }
    
    public void nieuweAwardBijTakeaway(String naamAwardTA,String naamTakeAway,String maand, int jaar)
    {
        Date periode = new Date(jaar-1900,Integer.parseInt(maand),01);
        try
        {     
            con = getConnection();
            
            String query = "INSERT into AwardTakeAway (periode,naamAwardTA,naamTakeAway)" + "values (?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDate(1, periode );
            preparedStmt.setString(2, naamAwardTA);
            preparedStmt.setString(3, naamTakeAway);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij insert in AwardTakeAway (award bij takeaway toevoegen):" + ex);
            }
    }
    
    public void nieuweAwardBijGerecht(String maand,int jaar,String naamGerecht,int menuID)
    {
        Date periode = new Date(jaar-1900,Integer.parseInt(maand),01);
        String naamAwardG = "hot item";
        
         try
        {     
            con = getConnection();
            
            String query = "INSERT into AwardGerecht(periode,naamAwardG,naamGerecht,menuID)" + "values (?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDate(1, periode );
            preparedStmt.setString(2, naamAwardG);
            preparedStmt.setString(3, naamGerecht);
            preparedStmt.setInt(4, menuID);
           
            preparedStmt.execute();
            
            con.close();        
        }
        
        catch(Exception ex)
            {
                System.out.println("Error bij insert in AwardGerecht (award bij gerecht toevoegen):" + ex);
            }
    }
    
    public String getAwardGerechtNaamOpMoment()
    {
        String naamGerecht = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht FROM AwardGerecht WHERE periode = '" + getMaxDateAwardGerecht()  + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    naamGerecht = rs.getString("naamGerecht");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van naamGerect bij Award: error:" + ex);
            }
    
        return naamGerecht;
    }
    
    public String getAwardGerechtMenuIDOpMoment()
    {
        String naamGerecht = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT menuID FROM AwardGerecht WHERE periode = '" + getMaxDateAwardGerecht()  + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    naamGerecht = rs.getString("menuID");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van menuid bij award: error:" + ex);
            }
    
        return naamGerecht;
    }
    
    public int getMenuIDBijGerechtAward(String naamGerechtAward)
    {
        int menuID  = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT menuID FROM AwardGerecht WHERE naamAwardG = '" + naamGerechtAward +  "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    menuID = rs.getInt("menuID");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van menuID bij AwardTakeAway met naam award:  " + naamGerechtAward +  " error:" + ex);
            }
        
        return menuID;
    }
    
    
    public Date getPeriodeGerechtAward(String naamGerechtAward)
    {
        Date periode = null;
        
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT periode FROM AwardGerecht WHERE naamAwardG = '" + naamGerechtAward +  "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    periode = rs.getDate("periode");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van periode bij AwardGerecht met naam award:  " + naamGerechtAward +  " error:" + ex);
            }
        
        return periode;
    }
    
    
    public String getNaamTakeAwayBijAward(String naamAwardTakeAway)
    {
        String naamTA = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM AwardTakeAway WHERE naamAwardTA = '" + naamAwardTakeAway +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    naamTA = rs.getString("naamTakeAway");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van naam TakeAway bij AwardTakeaway met naam award:  " + naamAwardTakeAway +  " error:" + ex);
            }
        
        return naamTA;
    }
    
    
    public Date getPeriodeTakeAwayBijAward(String naamAwardTakeAway)
    {
        Date periode = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT periode FROM AwardTakeAway WHERE naamAwardTA = '" + naamAwardTakeAway +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    periode = rs.getDate("periode");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van naam periode bij AwardTakeaway met naam award:  " + naamAwardTakeAway +  " error:" + ex);
            }
        
        return periode;
    }
    
    public String getLeveringsstraatBijBestellingsID(int bestellingsID)
    {
        String leveringsstraat = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT leveringsstraat FROM Bestelling WHERE bestellingsID = '" + bestellingsID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    leveringsstraat = rs.getString("leveringsstraat");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van leveringsstraat bij bestellingsid met ID:  " + bestellingsID +  " error:" + ex);
            }
        
        return leveringsstraat;
    }
    
     public int getLeveringsHuisnummerBijBestellingsID(int bestellingsID)
    {
        int leveringsHuisnummer = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT leveringsHuisnummer FROM Bestelling WHERE bestellingsID = '" + bestellingsID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    leveringsHuisnummer = rs.getInt("leveringsHuisnummer");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van leveringsHuisnummer bij bestellingsid met ID:  " + bestellingsID +  " error:" + ex);
            }
        
        return leveringsHuisnummer;
    }
     
     public int getPostcodeBijBestellingsID(int bestellingsID)
    {
        int postcode = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT postcode FROM Bestelling WHERE bestellingsID = '" + bestellingsID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    postcode = rs.getInt("postcode");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van postcode bij bestellingsid met ID:  " + bestellingsID +  " error:" + ex);
            }
        
        return postcode;
    }
     
     public String getGemeenteBijBestellingsID(int bestellingsID)
    {
        String gemeente = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT gemeente FROM Bestelling WHERE bestellingsID = '" + bestellingsID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    gemeente = rs.getString("gemeente");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van leveringsHuisnummer bij bestellingsid met ID:  " + bestellingsID +  " error:" + ex);
            }
        
        return gemeente;
    }
     
     public String getAdresBijBestelling(int bestellingsID)
     //geeft het volledige adres weer van een bepaalde bestelling(combinatie van 4 methodes)
     {
         String adres = "";
         
         adres += getLeveringsstraatBijBestellingsID(bestellingsID) + ",";
         adres += getLeveringsHuisnummerBijBestellingsID(bestellingsID) + ",";
         adres += getPostcodeBijBestellingsID(bestellingsID) + ",";
         adres += getGemeenteBijBestellingsID(bestellingsID);
         
         return adres;        
     }
     
     public Date getPeriodeBijBestellingsID(int bestellingsID)
    {
        Date periode = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT datum FROM Bestelling WHERE bestellingsID = '" + bestellingsID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    periode = rs.getDate("datum");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van periode bij bestellingsid met ID:  " + bestellingsID +  " error:" + ex);
            }
        
        return periode;
    }
     
     public ArrayList getGerechtenBijMenuID(int menuID)
     {
         ArrayList gerechten = new ArrayList();
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht FROM Gerechten WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    gerechten.add(rs.getString("naamGerecht"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van gerechten bij MenuID met ID:  " + menuID +  " error:" + ex);
            }
         
         return gerechten;
     }
     
     public ArrayList getBijgerechtenBijMenuID(int menuID)
     {
         ArrayList Bijgerechten = new ArrayList();
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamBijgerecht FROM Bijgerechten WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    Bijgerechten.add(rs.getString("naamBijgerecht"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Bijgerechten bij MenuID met ID:  " + menuID +  " error:" + ex);
            }
         
         return Bijgerechten;
     }
     
     public ArrayList getDrankenBijMenuID(int menuID)
     {
         ArrayList Dranken = new ArrayList();
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamDrank FROM Dranken WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    Dranken.add(rs.getString("naamDrank"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Dranken bij MenuID met ID:  " + menuID +  " error:" + ex);
            }
         
         return Dranken;
     }
     
     public ArrayList getSnacksBijMenuID(int menuID)
     {
         ArrayList Snacks = new ArrayList();
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamSnack FROM Snacks WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    Snacks.add(rs.getString("naamSnack"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Snacks bij MenuID met ID:  " + menuID +  " error:" + ex);
            }
         
         return Snacks;
     }
     
     public String getVoornaamVanKlantlogin(String klantLogin)
     {
         String voornaam = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT voornaam FROM Klant WHERE klantLogin = '" + klantLogin +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   voornaam = rs.getString("voornaam");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van voornaam bij klant met klantLogin:  " + klantLogin +  " error:" + ex);
            }
         
         
         return voornaam;
     }
     
     public String getAchternaamVanKlantlogin(String klantLogin)
     {
         String achternaam= "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT achternaam FROM Klant WHERE klantLogin = '" + klantLogin +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   achternaam = rs.getString("achternaam");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van achternaam bij klant met klantLogin:  " + klantLogin +  " error:" + ex);
            }
         
         
         return achternaam;
     }
     
     public String getPaswoordVanKlantlogin(String klantLogin)
     {
         String paswoord = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT paswoord FROM Klant WHERE klantLogin = '" + klantLogin +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   paswoord = rs.getString("paswoord");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van paswoord bij klant met klantLogin:  " + klantLogin +  " error:" + ex);
            }
         
         
         return paswoord;
     }
     
     public String getEmailVanKlantlogin(String klantLogin)
     {
         String email= "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT email FROM Klant WHERE klantLogin = '" + klantLogin +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   email = rs.getString("email");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van email bij klant met klantLogin:  " + klantLogin +  " error:" + ex);
            }
         
         
         return email;
     }
     
     public String getTelefoonnummerVanKlantlogin(String klantLogin)
     {
         String telefoonnummer = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT telefoonnummer FROM Klant WHERE klantLogin = '" + klantLogin +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   telefoonnummer = rs.getString("telefoonnummer");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van telefoonnummer bij klant met klantLogin:  " + klantLogin +  " error:" + ex);
            }
         
         
         return telefoonnummer;
     }
     
     public void updateVoornaamVanKlantlogin(String klantlogin, String nieuweVoornaam)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET voornaam = '" + nieuweVoornaam + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van voornaam klant:" + ex);
            }
     
     }
     
     public void updateAchternaamVanKlantlogin(String klantlogin, String nieuweAchternaam)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET achternaam = '" + nieuweAchternaam + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van achternaam klant:" + ex);
            }
     
     }
    
    public void updatePaswoordVanKlantlogin(String klantlogin, String nieuwPaswoord)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET paswoord = '" + nieuwPaswoord + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van paswoord klant:" + ex);
            }
     
     }
    
    public void updateEmailVanKlantlogin(String klantlogin, String nieuwEmail)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET email = '" + nieuwEmail + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van email klant:" + ex);
            }
    
     }
    
    public void updateStraatVanKlantlogin(String klantlogin, String nieuweStraat)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET straat = '" + nieuweStraat + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van straat klant:" + ex);
            }
    
     }
    
    public void updateHuisnummerVanKlantlogin(String klantlogin, int nieuwHuisnummer)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET huisnummer = '" + nieuwHuisnummer + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van huisnummer klant:" + ex);
            }
    
     }
    
    public void updatePostcodeVanKlantlogin(String klantlogin, int nieuwePostcode)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET postcode = '" + nieuwePostcode + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van postcode klant:" + ex);
            }
    
     }
    public void updateGemeenteVanKlantlogin(String klantlogin, String nieuweGemeente)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET gemeente = '" + nieuweGemeente + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van gemeente klant:" + ex);
            }
    
     }
    
    public void updateTelefoonnummerVanKlantlogin(String klantlogin, String nieuwTelefoonnummer)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Klant SET telefoonnummer = '" + nieuwTelefoonnummer + "' WHERE klantlogin = '" + klantlogin +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van telefoonnummer klant:" + ex);
            }
    
     }
    
    public void updateVanafBepaaldBedragkortingTakeAway(String takeAway, int nieuwVanafBepaaldBedragkorting)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET vanafBepaaldBedragkorting = '" + nieuwVanafBepaaldBedragkorting + "' WHERE naamTakeAway = '" + takeAway +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van VanafBepaaldBedragkorting takeaway:" + ex);
            }
    }
    
    public void updateGegevenKortingTakeAway(String takeAway, double nieuwGegevenKorting)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET gegevenKorting = '" + nieuwGegevenKorting + "' WHERE naamTakeAway = '" + takeAway +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van gegeven korting van takeaway:" + ex);
            }
    }
    
    public void updateKortingscodeTakeAway(String takeAway, String nieuweKortingscode)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET kortingscode = '" + nieuweKortingscode + "' WHERE naamTakeAway = '" + takeAway +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van kortingscode takeaway:" + ex);
            }
    }
    
    public void updatePaswoordTakeAway(String takeAway, String nieuwPaswoord)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET paswoord = '" + nieuwPaswoord + "' WHERE naamTakeAway = '" + takeAway +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van paswoord takeaway:" + ex);
            }
    }
    
    public void updateLeveringskostenVestiging(int vestiginsID, int nieuweLeveringskosten)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Vestiging SET leveringskosten = '" + nieuweLeveringskosten + "' WHERE vestigingsID = '" + vestiginsID+  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van leveringskosten bij vestiging:" + ex);
            }
    }
    
    public void deleteCategorieBijTakeAway(String takeAway, String categorie)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Soort WHERE naamTakeAway = '" + takeAway + "' AND categorie ='" + categorie + "'; ";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van leveringskosten bij vestiging:" + ex);
            }
    }
    
    public double getScoreReview(int reviewID)
    {
        double score = 0;
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT score FROM Review WHERE reviewID = '" + reviewID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   score = rs.getDouble("score");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van score bij review met ID:  " + reviewID +  " error:" + ex);
            }
        return score;
    }
    
    public String getNaamEnTakeAwayBijReview(int reviewID)
    {
        String naamGerecht = "";
        int menuID = 0;
         
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht,menuID FROM Review WHERE reviewID = '" + reviewID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   naamGerecht = rs.getString("naamGerecht");
                   menuID = rs.getInt("menuID");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van score bij review met ID:  " + reviewID +  " error:" + ex);
            }
        
        String tekst = naamGerecht + " (" + getNaamTakeAwayBijMenuID(menuID) + ")";
        return tekst;
    }
    
    
    public int getAantalLikesReview(int reviewID)
    {
        int aantalLikes = 0;
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT likes FROM Review WHERE reviewID = '" + reviewID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   aantalLikes = rs.getInt("likes");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal likes bij review met ID:  " + reviewID +  " error:" + ex);
            }
        return aantalLikes;
    }
    
    public String getTekstReview(int reviewID)
    {
        String reviewTekst = "";
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT tekst FROM Review WHERE reviewID = '" + reviewID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   reviewTekst = rs.getString("tekst");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van tekst bij review met ID:  " + reviewID +  " error:" + ex);
            }
          
          return reviewTekst;
    }
    
    public String getKlantloginReview(int reviewID)
    {
        String klantlogin = "";
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT klantlogin FROM Review WHERE reviewID = '" + reviewID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   klantlogin = rs.getString("klantlogin");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van klantlogin bij review met ID:  " + reviewID +  " error:" + ex);
            }
        return klantlogin;
    }
    
    public ArrayList getLijstScoresReviewVanGerecht(String naamGerecht,int MenuID)
    {
        ArrayList scores = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT score FROM Review WHERE naamGerecht = '" + naamGerecht +  "' AND menuID = '" + MenuID + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   scores.add(rs.getDouble("score"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van scores bij naamGercht : " + naamGerecht + " en menuID : " + MenuID + " error:" + ex);
            }
        
        return scores;
    }
    
    public ArrayList getLijstTekstenReviewGerecht(String naamGerecht,int MenuID)
    {
        ArrayList tekstjes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT tekst FROM Review WHERE naamGerecht = '" + naamGerecht +  "' AND menuID = '" + MenuID + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   tekstjes.add(rs.getString("tekst"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van teksten bij naamGercht : " + naamGerecht + " en menuID : " + MenuID + " error:" + ex);
            }
        
        return tekstjes;
    }

    public ArrayList getLijstAantalLikesReviewGerecht(String naamGerecht,int MenuID)
    {
        ArrayList lijstLikes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT likes FROM Review WHERE naamGerecht = '" + naamGerecht +  "' AND menuID = '" + MenuID + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   lijstLikes.add(rs.getInt("likes"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van teksten bij naamGercht : " + naamGerecht + " en menuID : " + MenuID + " error:" + ex);
            }
        
        return lijstLikes;
    }
    
    public ArrayList getLijstKlantloginReviewGerecht(String naamGerecht,int MenuID)
    {
        ArrayList lijstKlantlogin = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT klantlogin FROM Review WHERE naamGerecht = '" + naamGerecht +  "' AND menuID = '" + MenuID + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   lijstKlantlogin.add(rs.getString("klantlogin"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van klantlogins bij naamGercht : " + naamGerecht + " en menuID : " + MenuID + " error:" + ex);
            }
        
        return lijstKlantlogin;
    }
    
    public int getAantalBesteldVestiging(int VestigingsID)
    {
        int aantalBesteld = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBestellingenV FROM Vestiging WHERE vestigingsID = '" + VestigingsID +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   aantalBesteld = rs.getInt("aantalBestellingenV");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van aantal besteld bij vestiging met ID : " + VestigingsID + " error:" + ex);
            }
        return aantalBesteld;
    }
    
    public int getAantalBesteldTakeAway(String naamTakeAway)
    {
        int aantalBesteld = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT aantalBestellingenTA FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   aantalBesteld = rs.getInt("aantalBestellingenTA");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van aantal besteld bij takeAway met naam : " + naamTakeAway + " error:" + ex);
            }
        return aantalBesteld;
    }
    
    public void updateAantalBesteldVestiging(int vestigingsID)
    //Deze methode verhoogt de aantal bestellingen bij een vestiging met 1
    {
        int oudAantal = getAantalBesteldVestiging(vestigingsID);
        oudAantal ++;
        int nieuwAantal = oudAantal;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Vestiging SET aantalBestellingenV = '" + nieuwAantal + "' WHERE vestigingsID = '" + vestigingsID + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van het anatal bestelde bestellingen bij vestiging :" + ex);
            }
    }
    
    public void updateAantalBesteldTakeAway(String naamTakeAway)
    {
        int oudAantal = getAantalBesteldTakeAway(naamTakeAway);
        oudAantal ++;
        int nieuwAantal = oudAantal;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET aantalBestellingenTA = '" + nieuwAantal + "' WHERE naamTakeAway = '" + naamTakeAway + "' ;";
            st.executeUpdate(query);
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van het anatal bestelde bestellingen bij takeaway :" + ex);
            }
    }
    
    public String getTakeAwayVanVestiging(int vestigingsID)
    {
        String naamTakeAway = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM Vestiging WHERE vestigingsID = '" + vestigingsID +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   naamTakeAway = rs.getString("naamTakeAway");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van aantal besteld bij takeAway met naam : " + naamTakeAway + " error:" + ex);
            }
        
        return naamTakeAway;
    }
    
    public boolean controleerOfKlantloginBestaat(String klantlogin)
    //retourneert false als de klantlogin al bestaat, true als klantlogin nog niet bestaat
    {
        boolean bool = false;
        int aantal = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT klantlogin FROM Klant WHERE klantlogin = '" + klantlogin + "'; ";
            rs = st.executeQuery(query);
            
            if (rs.next())
                {
                    bool = false;
                }
            else
                bool = true;
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij controleren van bestaan klantlogin:  " + klantlogin +  " , error:" + ex);
            }
        
        return bool;
    }
    
    public ArrayList<String> getGemeentesBijPostcode(int postcode)
    {
        ArrayList<String> gemeentes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT gemeente FROM Gemeenten WHERE postcode = '" + postcode + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    gemeentes.add(rs.getString("gemeente"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen gemeentes bij postcode :  " + postcode +" , error:" + ex);
            }
        
        return gemeentes;
    }
        
    public boolean controleerOfEmailBestaat(String email)
    //retourneert false als email al bestaat, true als email nog niet bestaat
    {
        boolean bool = false;
        int aantal = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT email FROM Klant WHERE email = '" + email + "'; ";
            rs = st.executeQuery(query);
            
            if (rs.next())
                {
                    bool = false;
                }
            else
                bool = true;
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij controleren van bestaan email:  " + email +  " , error:" + ex);
            }
        
        return bool;
    }
    public boolean controleerOfEmailTakeAwayBestaat(String email)
        //retourneert false als email al bestaat, true als email nog niet bestaat
    {
        boolean bool = false;
        int aantal = 0;

        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT emailTakeAway FROM TakeAway WHERE emailTakeAway = '" + email + "'; ";
            rs = st.executeQuery(query);

            if (rs.next())
            {
                bool = false;
            }
            else
            {
                bool = true;
            }
            con.close();
        }  

     catch(Exception ex)
     {
     try {con.close();} catch(Exception ex2) {}
     System.out.println("Error bij controleren van bestaan emailtakeAway: " + email + " , error:" + ex);
     }

     return bool;
     }
    
     public boolean controleerOfTakeAwayBestaat(String takeAway)
    //retourneert false als de takeway al bestaat, true als takeaway nog niet bestaat
    {
        boolean bool = false;
        int aantal = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM TakeAway WHERE naamTakeAway = '" + takeAway + "'; ";
            rs = st.executeQuery(query);
            
            if (rs.next())
                {
                    bool = false;
                }
            else
                bool = true;
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij controleren van bestaan takeaway:  " + takeAway +  " , error:" + ex);
            }
        
        return bool;
    }
     
     public String getPaswoordVanTakeAway(String takeAway)
     {
         String paswoord = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT paswoord FROM TakeAway WHERE naamTakeAway = '" + takeAway +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   paswoord = rs.getString("paswoord");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van paswoord bij takeaway met naam:  " + takeAway +  " error:" + ex);
            }
         
         
         return paswoord;
     }
     
     public int getMaximumMenuID()
     {   
         int hoogsteMenuID = 0;
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT MAX(menuID) AS HighestmenuID FROM Menu;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   hoogsteMenuID = rs.getInt("HighestmenuID");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van hoogste menuID , error:" + ex);
            }
         
         
         return hoogsteMenuID;
     }
     
     public int getMaximumVID()
     {   
         int hoogsteVID = 0;
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT MAX(vestigingsID) AS hoogsteVID FROM Vestiging;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   hoogsteVID = rs.getInt("HoogsteVID");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van hoogste vestigingsid , error:" + ex);
            }
         
         
         return hoogsteVID;
     }
     
     public ArrayList<String> getAlleUniekeCategorien()
     {   
        ArrayList<String> categorieen = new ArrayList();
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT categorie FROM Soort;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   if (!categorieen.contains(rs.getString("categorie")))
                       categorieen.add(rs.getString("categorie"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van unieke categorien , error:" + ex);
            }
         
         
         return categorieen;
     }
     
     public ArrayList<String> getAlleTakeAways()
     {   
        ArrayList<String> takeaways = new ArrayList();
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM TakeAway;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    takeaways.add(rs.getString("naamTakeAway"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van takeaways , error:" + ex);
            }
         
         
         return takeaways;
     }
     
     
     public ArrayList<String> getCategorieenTakeAway(String naamTakeAway)
    {
        ArrayList<String> categorieen = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT categorie FROM Soort WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    categorieen.add(rs.getString("categorie"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van categorie voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        
        return categorieen;
    }
     
     public ArrayList<String> getGerechtenTakeAway(String naamTakeAway)
    {
        ArrayList<String> gerechten = new ArrayList();
        int menuID = getMenuIDTakeAway(naamTakeAway);
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht FROM Gerechten WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    gerechten.add(rs.getString("naamGerecht"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van gerechten voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        
        return gerechten;
    }
     
      public String getGemeenteVanKlant(String klant)
     {
         String gemeente = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT gemeente FROM Klant WHERE klantLogin = '" + klant +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   gemeente = rs.getString("gemeente");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van gemeente bij klant met naam:  " + klant +  " error:" + ex);
            }
         
         
         return gemeente;
     }
      
      public String getStraatVanKlant(String klant)
     {
         String straat = "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT straat FROM Klant WHERE klantLogin = '" + klant +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   straat = rs.getString("straat");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van straat bij klant met naam:  " + klant +  " error:" + ex);
            }
         
         
         return straat;
     }
      
      public int getPostcodeVanKlant(String klant)
     {
         int postcode = 0;
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT postcode FROM Klant WHERE klantLogin = '" + klant +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   postcode = rs.getInt("postcode");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van postcode bij klant met naam:  " + klant +  " error:" + ex);
            }
         
         
         return postcode;
     }
      
      public int getHuisnummerVanKlant(String klant)
     {
         int huisnummer = 0;
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT huisnummer FROM Klant WHERE klantLogin = '" + klant +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   huisnummer = rs.getInt("huisnummer");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van huisnummer bij klant met naam:  " + klant +  " error:" + ex);
            }
         
         
         return huisnummer;
     }
      
      public ArrayList<String> getTakeAwayBijCategorie(String categorie)
    {
        ArrayList<String> takeAways = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM Soort WHERE categorie = '" + categorie + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    takeAways.add(rs.getString("naamTakeAway"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van takeaways voor categorie met naam:  " + categorie + " , error:" + ex);
            }
        
        return takeAways;
    }
      
       public HashMap<String,Double> getGerechtenEnPrijzenTakeAway(String naamTakeAway)
    {
        HashMap<String,Double> gerechtenHM = new HashMap();
        int menuID = getMenuIDTakeAway(naamTakeAway);
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht,prijs FROM Gerechten WHERE menuID = '" + menuID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    gerechtenHM.put(rs.getString("naamGerecht"),rs.getDouble("prijs"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van gerechten en prijzen voor TakeAway met naam:  " + naamTakeAway + " , error:" + ex);
            }
        
        return gerechtenHM;
    }
       
       public HashMap<String,Double> getSnacksEnPrijzenTakeAway(String takeaway)
     {
         HashMap <String,Double> snacksHM = new HashMap();
         int menuID = getMenuIDTakeAway(takeaway);
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamSnack,prijs FROM Snacks WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    snacksHM.put(rs.getString("naamSnack"),rs.getDouble("prijs"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Snacks en prijzen bij takeaway met naam :  " + takeaway+  " error:" + ex);
            }
         
         return snacksHM;
     }
     
       public HashMap<String,Double> getBijgerechtenEnPrijzenTakeAway(String takeaway)
     {
         HashMap<String, Double> bijgerechtenHM = new HashMap();
         int menuID = getMenuIDTakeAway(takeaway);
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamBijgerecht,prijs FROM Bijgerechten WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    bijgerechtenHM.put(rs.getString("naamBijgerecht"),rs.getDouble("prijs"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Bijgerechten en prijzen bij takeaway met naam:  " + takeaway +  " error:" + ex);
            }
         
         return bijgerechtenHM;
     }
       
       public HashMap<String, Double> getDrankenEnPrijzenTakeAway(String takeaway)
     {
         HashMap<String,Double> drankenHM = new HashMap();
         int menuID = getMenuIDTakeAway(takeaway);
         
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamDrank,prijs FROM Dranken WHERE menuID = '" + menuID +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    drankenHM.put(rs.getString("naamDrank"),rs.getDouble("prijs"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Dranken en prijzen bij takeaway met naam:  " + takeaway +  " error:" + ex);
            }
         
         return drankenHM;
     }
       
       public String getAantalTakeAways()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM TakeAway;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal takeaways  error:" + ex);
            }
         
         return aantal;
     }
       
       public String getAantalKlanten()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Klant;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal klanten error:" + ex);
            }
         
         return aantal;
     }
       
       public String getAantalBestellingen()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Bestelling;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal bestellingen error:" + ex);
            }
         
         return aantal;
     }
       
       public String getAantalGerechten()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Gerechten;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal gerechten error:" + ex);
            }
         
         return aantal;
     }
       
       
       public String getAantalBijgerechten()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Bijgerechten;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal bijgerechten error:" + ex);
            }
         
         return aantal;
     }
       
       public String getAantalSnacks()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Snacks;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal snacks error:" + ex);
            }
         
         return aantal;
     }
       
       public String getAantalDranken()
     {
         String aantal = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Dranken;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantal = rs.getString(1);                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen aantal dranken error:" + ex);
            }
         
         return aantal;
     }
       
       public int getAantalGerechtenTakeAway(String takeaway)
    {
        int menuID = getMenuIDTakeAway(takeaway);
        int aantalGerechten = 0;
        con = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(menuID) AS aantalGerechtenC FROM Gerechten WHERE menuID=" + menuID + ";  ; ";
            rs = st.executeQuery(query);
            while(rs.next())
                {
                    aantalGerechten= rs.getInt("aantalGerechtenC");
                }
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het ophalen van aantal gerechten bij takeaway:" + ex);
            }
        
        return aantalGerechten;
    }
       
       public int getAantalBijgerechtenTakeAway(String takeaway)
    {
        int menuID = getMenuIDTakeAway(takeaway);
        int aantalBijgerechten = 0;
        con = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(menuID) AS aantalBijgerechtenC FROM Bijgerechten WHERE menuID=" + menuID + ";  ; ";
            rs = st.executeQuery(query);
            while(rs.next())
                {
                    aantalBijgerechten= rs.getInt("aantalBijgerechtenC");
                }
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het ophalen van aantal Bijgerechten bij takeaway:" + ex);
            }
        
        return aantalBijgerechten;
    }
       
       public int getAantalSnacksTakeAway(String takeaway)
    {
        int menuID = getMenuIDTakeAway(takeaway);
        int aantalSnacks = 0;
        con = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(menuID) AS aantalSnacksC FROM Snacks WHERE menuID=" + menuID + ";  ; ";
            rs = st.executeQuery(query);
            while(rs.next())
                {
                    aantalSnacks = rs.getInt("aantalSnacksC");
                }
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het ophalen van aantal Bijgerechten bij takeaway:" + ex);
            }
        
        return aantalSnacks;
    }
       
        public ArrayList<String> getVestigingsIDSTakeAway(String naamTakeAway)
     {
         ArrayList<String> vestigingsIDs = new ArrayList();
 
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT vestigingsID FROM Vestiging WHERE naamTakeAway = '" + naamTakeAway + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    vestigingsIDs.add(rs.getString("vestigingsID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de vestigingsIDS van takeaway :  " + naamTakeAway + ":" + ex);
            }
        
         return vestigingsIDs;
     }
        
        public ArrayList<String> getBestellingsIDBijVestigingsID(int vestigingsID)
     {
         ArrayList<String> vestigingsIDs = new ArrayList();
 
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT bestellingsID FROM GeplaatstBij WHERE vestigingsID = '" + vestigingsID + "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    vestigingsIDs.add(rs.getString("bestellingsID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de bestellingsIDS bij vestigingsids :  " + vestigingsID + ":" + ex);
            }
        
         return vestigingsIDs;
     }
        
        public int getMaximumBestellingsID()
     {   
         int hoogsteBestellingsID = 0;
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT MAX(bestellingsID) AS HighestbestellingsID FROM Bestelling;";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   hoogsteBestellingsID = rs.getInt("HighestbestellingsID");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van hoogste bestellingsID , error:" + ex);
            }
         
         
         return hoogsteBestellingsID;
     }
        
        public Date getDatumBijBestellingsID(int bestellingsID)
        {
             Date datum = null;
            try
           {
               con = getConnection();
               Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

               String query = "SELECT datum FROM Bestelling WHERE bestellingsID = " + bestellingsID + " ;";
               rs = st.executeQuery(query);


               while(rs.next())
                   {
                      datum = rs.getDate("datum");

                   }

               con.close();
           }

           catch(Exception ex)
               {
                   try {con.close();} catch(Exception ex2) {}
                   System.out.println("Error bij opvragen van datum bij bestellingsID " + bestellingsID + "  , error:" + ex);
               }
            return datum;
        }
        
        public String getKlantLoginBijBestellingsID(int bestellingsID)
        {
            String klant = null;
            try
           {
               con = getConnection();
               Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

               String query = "SELECT klantLogin FROM Bestelling WHERE bestellingsID = " + bestellingsID + " ;";
               rs = st.executeQuery(query);


               while(rs.next())
                   {
                     klant= rs.getString("klantLogin");

                   }

               con.close();
           }

           catch(Exception ex)
               {
                   try {con.close();} catch(Exception ex2) {}
                   System.out.println("Error bij opvragen van klantlogin bij bestellingsID " + bestellingsID + "  , error:" + ex);
               }
            return klant;
        }
        
         public ArrayList<Integer> getVestigingsIDBijBestellingsID(int bestellingsID)
     {
         ArrayList<Integer> IDs = new ArrayList();
 
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT vestigingsID FROM GeplaatstBij WHERE bestellingsID = '" + bestellingsID+ "' ; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    IDs.add(rs.getInt("vestigingsID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van de vestigingsID bij vestigingsids :  " + bestellingsID + ":" + ex);
            }
        
         return IDs;
     }
         
         public String getAdresBijVestigingsID(int vestigingsID)
         {
             String adres = "";

              try
                {
                    con = getConnection();
                    Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                    String query = "SELECT straat,huisnummer,gemeente FROM Vestiging WHERE vestigingsID = '" + vestigingsID+ "' ; ";
                    rs = st.executeQuery(query);

                    while(rs.next())
                        {
                            adres += rs.getString("straat") + ",";
                            adres += rs.getString("huisnummer") + ",";
                            adres += rs.getString("gemeente");
                        }

                    con.close();
                }
        
            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error bij opvragen van de adres als string bij vestigingsids :  " + vestigingsID + ":" + ex);
                }
              
             return adres;
             
         }
         
         public int getVestigingsIDInLeveringsgebiedVanTakeAway(String naamTakeAway, int postcode, String gemeente)
         //met veronderstelling dat een takeaway maar 1 vestiging heeft
         {
            int ID = 0;
            ArrayList<String> vestigingsIDs = getVestigingsIDSTakeAway(naamTakeAway);
              
            //als er maar 1 vestiging in systeem zit, directe selecit in vestiging-Table
            if (vestigingsIDs.size() == 1)
              {
                try
                  {
                      con = getConnection();
                      Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                      String query = "SELECT vestigingsID FROM Vestiging WHERE naamTakeAway = '" + naamTakeAway + "'  ; ";
                      rs = st.executeQuery(query);

                      while(rs.next())
                          {
                              ID = rs.getInt("vestigingsID");
                          }

                      con.close();
                  }

              catch(Exception ex)
                  {
                      try {con.close();} catch(Exception ex2) {}
                      System.out.println("Error bij opvragen van de vestigingsid als string bij takeaway :  " + naamTakeAway + ":" + ex);
                  }
            } 
             
            ArrayList<Integer> ids = new ArrayList();
            if (vestigingsIDs.size() != 1)
            {
                for(String x : vestigingsIDs)
                {
                try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT vestigingsID FROM Leveringsgebied WHERE postcode = '" + postcode+ "' AND gemeente = '" + gemeente + "' ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                ids.add(rs.getInt("vestigingsID"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van de vestigingsid als string bij takeaway :  " + naamTakeAway + ":" + ex);
                    }
                
                }
                for(Integer x: ids)
                {
                    int getal = x;
                    if (vestigingsIDs.contains(x + ""))
                    {
                        ID = x;
                    }
                    
                }
            }
                    
                    
                    
             return ID;
         }
         
         public Double getBedragGerechtenBijMenuIDenBestellingsID(int menuID,int bestellingsID)
         {
             HashMap<String,Integer> namenEnPrijzen = new HashMap();
             Double gerechtenBedrag = 0.0;
             
             try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT naamGerecht,aantalGerechten FROM GerechtenBehoortTot WHERE menuID = '" + menuID + "' AND bestellingsID = '" + bestellingsID + "' ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                namenEnPrijzen.put(rs.getString("naamGerecht"),rs.getInt("aantalGerechten"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van naamgerecht en aantal besteld bij menuid en bestellingsid " + ex);
                    }
                    
             
                    for (String gerecht : namenEnPrijzen.keySet())
                    {
                        gerechtenBedrag += (getPrijsGerecht(gerecht, menuID)*namenEnPrijzen.get(gerecht));
                    }
             
             
             return gerechtenBedrag;
         }
         
         public Double getBedragBijgerechtenBijMenuIDenBestellingsID(int menuID,int bestellingsID)
         {
             HashMap<String,Integer> namenEnPrijzen = new HashMap();
             Double gerechtenBedrag = 0.0;
             
             try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT naamBijgerecht,aantalBijgerechten FROM BijgerechtenBehoortTot WHERE menuID = '" + menuID + "' AND bestellingsID = '" + bestellingsID + "' ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                namenEnPrijzen.put(rs.getString("naamBijgerecht"),rs.getInt("aantalBijgerechten"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van naambijgerecht en aantal besteld bij menuid en bestellingsid " + ex);
                    }
                    
                    for (String bijgerecht : namenEnPrijzen.keySet())
                    {
                        gerechtenBedrag += (getPrijsBijgerecht(bijgerecht, menuID)*namenEnPrijzen.get(bijgerecht));
                    }
             
             
             return gerechtenBedrag;
         }
         
         public Double getBedragSnacksBijMenuIDenBestellingsID(int menuID,int bestellingsID)
         {
             HashMap<String,Integer> namenEnPrijzen = new HashMap();
             Double gerechtenBedrag = 0.0;
             
             try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT naamSnack,aantalSnacks FROM SnacksBehoortTot WHERE menuID = '" + menuID + "' AND bestellingsID = '" + bestellingsID + "' ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                namenEnPrijzen.put(rs.getString("naamSnack"),rs.getInt("aantalSnacks"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van naamsnack en aantal besteld bij menuid en bestellingsid " + ex);
                    }
                    
                    for (String snack : namenEnPrijzen.keySet())
                    {
                        gerechtenBedrag += (getPrijsSnack(snack, menuID)*namenEnPrijzen.get(snack));
                    }
             
             
             return gerechtenBedrag;
         }
         
         public Double getBedragDrankenBijMenuIDenBestellingsID(int menuID,int bestellingsID)
         {
             HashMap<String,Integer> namenEnPrijzen = new HashMap();
             Double gerechtenBedrag = 0.0;
             
             try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT naamDrank,aantalDranken FROM DrankenBehoortTot WHERE menuID = '" + menuID + "' AND bestellingsID = '" + bestellingsID + "' ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                namenEnPrijzen.put(rs.getString("naamDrank"),rs.getInt("aantalDranken"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van naamDrank en aantal besteld bij menuid en bestellingsid " + ex);
                    }
                    
                    for (String drank : namenEnPrijzen.keySet())
                    {
                        gerechtenBedrag += (getPrijsDrank(drank, menuID)*namenEnPrijzen.get(drank));
                    }
             
             
             return gerechtenBedrag;
         }
         
         public Double getBedragParieleBestelling(int menuID,int bestellingsID)
         {
             Double totaalBedrag = 0.0;
             totaalBedrag += getBedragGerechtenBijMenuIDenBestellingsID(menuID, bestellingsID);
             totaalBedrag += getBedragBijgerechtenBijMenuIDenBestellingsID(menuID, bestellingsID);
             totaalBedrag += getBedragSnacksBijMenuIDenBestellingsID(menuID, bestellingsID);
             totaalBedrag += getBedragDrankenBijMenuIDenBestellingsID(menuID, bestellingsID);
             return totaalBedrag;
         }
         
         public double getOmzetTakeAway(String naamTakeAway)
         {
             ArrayList<Integer> bestellingsIDs = getBestellingsIDsTakeAway(naamTakeAway);
             int menuID = getMenuIDTakeAway(naamTakeAway);
             double omzet = 0.0;
             
             for (Integer x: bestellingsIDs)
             {
                 omzet += getBedragParieleBestelling(menuID, x);
             }
             
             return omzet;
         }
         
         public double getTotaleOmzetKlant(String klantLogin)
         // retourneert het totale bedrag waarvoor een klant heeft besteld in systeem
         {
             double omzet = 0.0;
             ArrayList<Integer> bestellingsIDsKlant = getBestellingsIDsKlant(klantLogin);
             for(int bestelling : bestellingsIDsKlant)
             {
                 omzet += getTotaalbedragBestelling(bestelling);
             }
             
             return omzet;
             
         }
         
         public ArrayList<Integer> getBestellingsIDSTakeAwayInMaand(String takeaway, String maand, int jaar)
         {
             ArrayList<Integer> bestellingsIDs = getBestellingsIDsTakeAway(takeaway);
             ArrayList<Integer> bestellingsIDsInMaand = new ArrayList();
             
             for(int bestelling : bestellingsIDs)
             {
                 Date datum = getDatumBijBestellingsID(bestelling);
                 if ((datum.getMonth()+1 == Integer.parseInt(maand)) && (datum.getYear() + 1900 == jaar) )
                 {
                     bestellingsIDsInMaand.add(bestelling) ;
                 }
             }
             
             return bestellingsIDsInMaand ;
         
         }
         
         public double getOmzetKlantBijTakeAwayInEenMaand(String klantLogin, String takeaway,String maand,int jaar)
         //geeft het totale bedrag van de bestellingen van een klant bij een beapaalde takeaway
         {
             double omzet = 0.0;
             
             ArrayList<Integer> bestellingIDsTakeawayMetBestellingenInEenMaand = getBestellingsIDSTakeAwayInMaand(takeaway, maand, jaar);
             ArrayList<Integer> bestellingsIDsKlant = getBestellingsIDsKlant(klantLogin);
             
             for (int bestelTakeAway: bestellingIDsTakeawayMetBestellingenInEenMaand)
             {
                 if (bestellingsIDsKlant.contains(bestelTakeAway))
                    {
                        ArrayList<Integer> vestigingsIDs = getVestigingsIDBijBestellingsID(bestelTakeAway);
                        ArrayList<String> vestigingsIDsTakeAway = getVestigingsIDSTakeAway(takeaway);
                        for (int vest : vestigingsIDs)
                        {
                            if(vestigingsIDsTakeAway.contains(vest+""))
                            {
                                omzet += getBedragParieleBestelling(getMenuIDTakeAway(getTakeAwayVanVestiging(vest)), bestelTakeAway);
                            }
                        }
                    }
             }
             return omzet;
         }
         
         public double getOmzetKlantInMaand(String maand,int jaar,String klant)
         {
            double omzet = 0.0;
            HashMap<Integer,Double> idEnBedrag = new HashMap();
            try
            {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT bestellingsID,totaalbedrag FROM Bestelling WHERE datum between '"+ jaar+"/"+ maand +"/1' AND '"+ jaar+"/"+ maand +"/31' AND klantLogin = '" + klant + "';";

            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    idEnBedrag.put(rs.getInt("bestellingsID"),rs.getDouble("totaalbedrag"));
                }
        
            con.close();
            }
            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error  bestellingen en totaalbedragen klant in bepaalde maand ophalen:" + ex);
                }
             
            for(int id : idEnBedrag.keySet())
            {
                omzet += idEnBedrag.get(id);
            }
            
            return omzet;
         }
         
         public ArrayList<String> getKlantLoginsBesteldInMaand(String maand,int jaar)
         {
             ArrayList<String> klanten  = new ArrayList();
             
             try
            {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT klantLogin FROM Bestelling WHERE datum between '"+ jaar+"/"+ maand +"/1' AND '"+ jaar+"/"+ maand +"/31';";

            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    if(!(klanten.contains(rs.getString("klantLogin"))))
                    {
                        klanten.add(rs.getString("klantLogin"));
                    }
                }
        
            con.close();
            }
            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error klanten in bepaalde maand ophalen:" + ex);
                }
             
             return klanten;
         }
         
         public String getHoogstGecummuleerdeOmzetKlantBijAlleActieveTakeAwaysInMaand(String maand, int jaar)
         {
             String klant = "";
             
             ArrayList<String> klanten = getKlantLoginsBesteldInMaand(maand, jaar);
             ArrayList<Double> omzetten = new ArrayList();
             
             for(String klnt: klanten)
             {
                 omzetten.add(getOmzetKlantInMaand(maand,jaar,klnt));
             }
             if(!omzetten.isEmpty())
             {
                double maxOmzet = Collections.max(omzetten);
                int index = omzetten.indexOf(maxOmzet);
                klant = klanten.get(index);
             }
             return klant;
         }
         
         public ArrayList getBestellingsIDsTakeAway(String naamTakeAway)
         {
            ArrayList<String> vestigingsIDs = getVestigingsIDSTakeAway(naamTakeAway);
            ArrayList<Integer> bestellingsIDs = new ArrayList();
            //vullen van bestellingsidsArraList met alle bestellingsIDs vloeiend voort uit de verschillende vestiginsIDs aan elkaar gekoppeld in de GeplaatstBij-Table
            for (String vestiging : vestigingsIDs)
            {
                ArrayList<String> bestellingen = getBestellingsIDBijVestigingsID(Integer.parseInt(vestiging));
                for (int x=0;x<bestellingen.size();x++)
                {
                    bestellingsIDs.add(Integer.parseInt(bestellingen.get(x)));
                }
            }
            return bestellingsIDs;
         }
         
          public ArrayList getBestellingsIDsPendingTakeAway(String naamTakeAway)
         {
            ArrayList<String> vestigingsIDs = getVestigingsIDSTakeAway(naamTakeAway);
            ArrayList<Integer> bestellingsIDs = new ArrayList();
            //vullen van bestellingsidsArraList met alle bestellingsIDs vloeiend voort uit de verschillende vestiginsIDs aan elkaar gekoppeld in de GeplaatstBij-Table
            for (String vestiging : vestigingsIDs)
            {
                ArrayList<String> bestellingen = getBestellingsIDBijVestigingsID(Integer.parseInt(vestiging));
                ArrayList<Integer> bestellingenPending = getBestellingsIDsPending();
                
                for(Integer X : bestellingenPending)
                {    
                    for (int x=0;x<bestellingen.size();x++)
                    if(X == Integer.parseInt(bestellingen.get(x)))
                    {
                        bestellingsIDs.add(Integer.parseInt(bestellingen.get(x)));
                    }
                }
            }
            return bestellingsIDs;
         }
          
          public ArrayList<Integer> getBestellingsIDsPending()
          {
              ArrayList<Integer> bestellingsPening = new ArrayList();
              String status = "pending";
              
              try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT bestellingsID FROM Bestelling WHERE status = '" + status + "'  ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                bestellingsPening.add(rs.getInt("bestellingsID"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van bestellingsIDs " + ex);
                    }
              return bestellingsPening;
          }
         
         
         public ArrayList<Integer> getBestellingsIDsKlant(String naamKlant)
         {
             ArrayList<Integer> bestellingsIDs = new ArrayList();
             
             try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT bestellingsID FROM Bestelling WHERE klantLogin = '" + naamKlant + "'  ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                bestellingsIDs.add(rs.getInt("bestellingsID"));
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van bestellingsIDs " + ex);
                    }
             return bestellingsIDs;
         }
        
         
    public HashMap<String,HashMap<String,Integer>> getMenuGerechtBijBestelling(int bestellingsID)
    //HashMap<naamTA,HM<naamGerecht,hoeveelheid>>
    {
        HashMap<String,HashMap<String,Integer>> menuGerecht = new HashMap();
        
        ArrayList<String> naamGerechten = new ArrayList();
        ArrayList<Integer> menuIDs = new ArrayList();
        ArrayList<Integer> aantalGerechten = new ArrayList();
            
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT naamGerecht,menuID,aantalGerechten FROM GerechtenBehoortTot WHERE bestellingsID = '" + bestellingsID + "'  ; ";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                naamGerechten.add(rs.getString("naamGerecht"));
                aantalGerechten.add(rs.getInt("aantalGerechten"));
                menuIDs.add(rs.getInt("menuID")); 
            }
            con.close();
        }

        catch(Exception ex)
        {
            try {con.close();} catch(Exception ex2) {}
            System.out.println("Error bij opvragen van 'menuGerecht' bij een bestellingsID " + ex);
        }
         
        for (Integer x: menuIDs)
            {
                String naamTakeAway = getNaamTakeAwayBijMenuID(x).toLowerCase() ;
                if (menuGerecht.containsKey(naamTakeAway))
                {
                    HashMap naamEnHoeveelheid = menuGerecht.get(naamTakeAway);
                    naamEnHoeveelheid.put(naamGerechten.get(menuIDs.indexOf(x)+1),aantalGerechten.get(menuIDs.indexOf(x)+1));
                    menuGerecht.put(naamTakeAway,naamEnHoeveelheid);
                }
                else 
                {
                    HashMap<String,Integer> naamEnHoeveelheid = new HashMap();
                    naamEnHoeveelheid.put(naamGerechten.get(menuIDs.indexOf(x)), aantalGerechten.get(menuIDs.indexOf(x)));
                    menuGerecht.put(naamTakeAway, naamEnHoeveelheid);
                }
            } 
        
        return menuGerecht;
    }
    
    public HashMap<String,HashMap<String,Integer>> getMenuBijgerechtBijBestelling(int bestellingsID)
            //HashMap<naamTA,HM<naamGerecht,hoeveelheid>>
    {
        HashMap<String,HashMap<String,Integer>> menuBijgerecht = new HashMap();
        
        ArrayList<String> naamBijgerechten = new ArrayList();
        ArrayList<Integer> menuIDs = new ArrayList();
        ArrayList<Integer> aantalBijgerechten = new ArrayList();
            
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT naamBijgerecht,menuID,aantalBijgerechten FROM BijgerechtenBehoortTot WHERE bestellingsID = '" + bestellingsID + "'  ; ";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                naamBijgerechten.add(rs.getString("naamBijgerecht"));
                aantalBijgerechten.add(rs.getInt("aantalBijgerechten"));
                menuIDs.add(rs.getInt("menuID")); 
            }
            con.close();
        }

        catch(Exception ex)
        {
            try {con.close();} catch(Exception ex2) {}
            System.out.println("Error bij opvragen van 'menuBijgerecht' bij een bestellingsID " + ex);
        }
         
        for (Integer x: menuIDs)
            {
                String naamTakeAway = getNaamTakeAwayBijMenuID(x).toLowerCase() ;
                if (menuBijgerecht.containsKey(naamTakeAway))
                {
                    HashMap naamEnHoeveelheid = menuBijgerecht.get(naamTakeAway);
                    naamEnHoeveelheid.put(naamBijgerechten.get(menuIDs.indexOf(x)+1),aantalBijgerechten.get(menuIDs.indexOf(x)+1));
                    menuBijgerecht.put(naamTakeAway,naamEnHoeveelheid);
                }
                else 
                {
                    HashMap<String,Integer> naamEnHoeveelheid = new HashMap();
                    naamEnHoeveelheid.put(naamBijgerechten.get(menuIDs.indexOf(x)), aantalBijgerechten.get(menuIDs.indexOf(x)));
                    menuBijgerecht.put(naamTakeAway, naamEnHoeveelheid);
                }
            } 
        
        return menuBijgerecht;
    }
    
    public HashMap<String,HashMap<String,Integer>> getMenuSnacksBijBestelling(int bestellingsID)
            //HashMap<naamTA,HM<naamGerecht,hoeveelheid>>
    {
        HashMap<String,HashMap<String,Integer>> menuSnack = new HashMap();
        
        ArrayList<String> naamSnacks = new ArrayList();
        ArrayList<Integer> menuIDs = new ArrayList();
        ArrayList<Integer> aantalSnacks = new ArrayList();
            
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT naamSnack,menuID,aantalSnacks FROM SnacksBehoortTot WHERE bestellingsID = '" + bestellingsID + "'  ; ";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                naamSnacks.add(rs.getString("naamSnack"));
                aantalSnacks.add(rs.getInt("aantalSnacks"));
                menuIDs.add(rs.getInt("menuID")); 
            }
            con.close();
        }

        catch(Exception ex)
        {
            try {con.close();} catch(Exception ex2) {}
            System.out.println("Error bij opvragen van 'menuSnacks' bij een bestellingsID " + ex);
        }
         
        for (Integer x: menuIDs)
            {
                String naamTakeAway = getNaamTakeAwayBijMenuID(x).toLowerCase() ;
                if (menuSnack.containsKey(naamTakeAway))
                {
                    HashMap naamEnHoeveelheid = menuSnack.get(naamTakeAway);
                    naamEnHoeveelheid.put(naamSnacks.get(menuIDs.indexOf(x)+1),aantalSnacks.get(menuIDs.indexOf(x)+1));
                    menuSnack.put(naamTakeAway,naamEnHoeveelheid);
                }
                else 
                {
                    HashMap<String,Integer> naamEnHoeveelheid = new HashMap();
                    naamEnHoeveelheid.put(naamSnacks.get(menuIDs.indexOf(x)), aantalSnacks.get(menuIDs.indexOf(x)));
                    menuSnack.put(naamTakeAway, naamEnHoeveelheid);
                }
            } 
        
        return menuSnack;
    }
    
    public HashMap<String,HashMap<String,Integer>> getMenuDrankenBijBestelling(int bestellingsID)
            //HashMap<naamTA,HM<naamGerecht,hoeveelheid>>
    {
        HashMap<String,HashMap<String,Integer>> menuDrank = new HashMap();
        
        ArrayList<String> naamDranken = new ArrayList();
        ArrayList<Integer> menuIDs = new ArrayList();
        ArrayList<Integer> aantalDranken = new ArrayList();
            
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT naamDrank,menuID,aantalDranken FROM DrankenBehoortTot WHERE bestellingsID = '" + bestellingsID + "'  ; ";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                naamDranken.add(rs.getString("naamDrank"));
                aantalDranken.add(rs.getInt("aantalDranken"));
                menuIDs.add(rs.getInt("menuID")); 
            }
            con.close();
        }

        catch(Exception ex)
        {
            try {con.close();} catch(Exception ex2) {}
            System.out.println("Error bij opvragen van 'menuDranken' bij een bestellingsID " + ex);
        }
         
        for (Integer x: menuIDs)
            {
                String naamTakeAway = getNaamTakeAwayBijMenuID(x).toLowerCase() ;
                if (menuDrank.containsKey(naamTakeAway))
                {
                    HashMap naamEnHoeveelheid = menuDrank.get(naamTakeAway);
                    naamEnHoeveelheid.put(naamDranken.get(menuIDs.indexOf(x)+1),aantalDranken.get(menuIDs.indexOf(x)+1));
                    menuDrank.put(naamTakeAway,naamEnHoeveelheid);
                }
                else 
                {
                    HashMap<String,Integer> naamEnHoeveelheid = new HashMap();
                    naamEnHoeveelheid.put(naamDranken.get(menuIDs.indexOf(x)), aantalDranken.get(menuIDs.indexOf(x)));
                    menuDrank.put(naamTakeAway, naamEnHoeveelheid);
                }
            } 
        
        return menuDrank;
    }
    
    public ArrayList<Bestelling> getBestellingenKlant(String naamKlant)
    {
        ArrayList<Bestelling> bestellingen = new ArrayList();
        
        ArrayList<Integer> bestellingsIDs = getBestellingsIDsKlant(naamKlant);
    
        for (int ID: bestellingsIDs)
        {
            Bestelling bestelling = new Bestelling(naamKlant);
            bestelling.setBestellingsID(ID);
            bestelling.setDatum(getDatumBijBestellingsID(ID));
            bestelling.setMenuGerecht(getMenuGerechtBijBestelling(ID));
            bestelling.setMenuBijGerecht(getMenuBijgerechtBijBestelling(ID));
            bestelling.setMenuDranken(getMenuDrankenBijBestelling(ID));
            bestelling.setMenuSnacks(getMenuSnacksBijBestelling(ID));
            
            bestellingen.add(bestelling);
        }
       
        return bestellingen;
    }
    
 
    public ArrayList getLijstAantalLikesReviewKlant(String klantLogin)
    {
        ArrayList lijstLikes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT likes FROM Review WHERE klantLogin = '" + klantLogin +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   lijstLikes.add(rs.getInt("likes"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van likes bij naamLogin , error:" + ex);
            }
        
        return lijstLikes;
    }
    
    public ArrayList getLijstScoresReviewVanklant(String klantLogin)
    {
        ArrayList scores = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT score FROM Review WHERE klantLogin = '" + klantLogin +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   scores.add(rs.getInt("score"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van scores bij klantLogin : " + klantLogin + ",  error:" + ex);
            }
        
        return scores;
    }


    public ArrayList getLijstTekstenReviewKlant(String klantLogin)
    {
        ArrayList tekstjes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT tekst FROM Review WHERE klantLogin = '" + klantLogin +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   tekstjes.add(rs.getString("tekst"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van teksten bij klantlogin : " + klantLogin + " en menuID : error:" + ex);
            }
        
        return tekstjes;
    }
    
    public ArrayList getLijstTakeAwaysReviewKlant(String klantLogin)
    {
        ArrayList<String> takeAways = new ArrayList();
        ArrayList<Integer> menuIDs = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT menuID FROM Review WHERE klantLogin = '" + klantLogin +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   menuIDs.add(rs.getInt("menuID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van takeawaysbij klantlogin : " + klantLogin + ", error:" + ex);
            }
        
        for (Integer i :  menuIDs)
        {
            takeAways.add(getNaamTakeAwayBijMenuID(i));
        }
        return takeAways;
    }
    
    public ArrayList getLijstGerechtenReviewKlant(String klantLogin)
    {
        ArrayList<String> naamGerechten = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamGerecht FROM Review WHERE klantLogin = '" + klantLogin +  "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   naamGerechten.add(rs.getString("naamGerecht"));
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van  namen gerechten bij klantlogin : " + klantLogin + " , error:" + ex);
            }
        
        return naamGerechten;
    }
    
     public String getStatusTakeAway(String naamTakeAway)
         {
            String status = "";
             
             try
                    {
                        con = getConnection();
                        Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                        String query = "SELECT status FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway + "'  ; ";
                        rs = st.executeQuery(query);

                        while(rs.next())
                            {
                                status = rs.getString("status");
                            }

                        con.close();
                    }

                catch(Exception ex)
                    {
                        try {con.close();} catch(Exception ex2) {}
                        System.out.println("Error bij opvragen van status van TakeAway " + ex);
                    }
             return status;
         }
         


public void updateStatusNaarActief(String naamTakeAway)
     {
         String nieuweStatus = "actief";
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET status = '" + nieuweStatus+ "' WHERE naamTakeAway= '" + naamTakeAway +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status takeaway:" + ex);
            }
    
     }

public void updateStatusNaarPassief(String naamTakeAway)
     {
         String nieuweStatus = "passief";
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET status = '" + nieuweStatus+ "' WHERE naamTakeAway= '" + naamTakeAway +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status takeaway:" + ex);
            }
    
     }

    public void updateEmailTakeAway(String naamTakeAway, String nieuweEmail)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET emailTakeAway = '" + nieuweEmail+ "' WHERE naamTakeAway= '" + naamTakeAway +  "' ;";
            st.executeUpdate(query);

            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status takeaway:" + ex);
            }

     }
    
    public void updateKortingVanafTakeAway(String naamTakeAway, int kortingVanaf)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET vanafBepaaldBedragkorting = '" + kortingVanaf+ "' WHERE naamTakeAway= '" + naamTakeAway +  "' ;";
            st.executeUpdate(query);

            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status takeaway:" + ex);
            }

     }
    
    public void updateKortingTakeAway(String naamTakeAway, double korting)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET gegevenKorting = '" + korting + "' WHERE naamTakeAway= '" + naamTakeAway +  "' ;";
            st.executeUpdate(query);

            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status takeaway:" + ex);
            }

     }
    
    public void updateWachtwoordTakeAway(String naamTakeAway, String nieuweWachtwoord)
     {
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE TakeAway SET paswoord = '" + nieuweWachtwoord + "' WHERE naamTakeAway= '" + naamTakeAway +  "' ;";
            st.executeUpdate(query);

            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status takeaway:" + ex);
            }

     }
    
     public String getEmailVanTakeAway(String naamTakeAway)
     {
         String email= "";
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT emailTakeAway FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   email = rs.getString("emailTakeAway");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van email bij takeaway met naam:  " + naamTakeAway+  " error:" + ex);
            }
         
         
         return email;
     }
     
     public Double getGegevenKortingVanTakeAway(String naamTakeAway)
     {
         Double gegevenKorting = 0.0;
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT gegevenKorting FROM TakeAway WHERE naamTakeAway = '" + naamTakeAway +  "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   gegevenKorting = rs.getDouble("gegevenKorting");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van gegevenKorting bij takeaway met naam:  " + naamTakeAway+  " error:" + ex);
            }
         
         
         return gegevenKorting;
     }
     
     public double getTotaleWaardeBestellingen()
     {
         double totaleWaarde = 0.0;
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT SUM(totaalbedrag) AS totaleWaarde FROM Bestelling; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   totaleWaarde = rs.getDouble("totaleWaarde");
                    
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van totale waarde bestllingen:  " +  " error:" + ex);
            }
         
         return totaleWaarde;
     }
     
     public ArrayList<Integer> getReviewIDs(String klantLogin)
     {
         ArrayList<Integer> reviewIDs = new ArrayList();
         
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT reviewID FROM Review WHERE klantLogin = '" + klantLogin + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   reviewIDs.add(rs.getInt("reviewID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van totale waarde bestllingen:  " +  " error:" + ex);
            }
         return reviewIDs;
     }
     
     public ArrayList<String> getAlleActieveTakeAwaysMetleveringsgebied(int postcode, String gemeente)
     {
         ArrayList<String> takeAways =  new ArrayList();
         ArrayList<Integer> vestigingsIDsInJuisteGebied = getVestigingsIDsBijPostcodeEnLeveringsgebied(postcode,gemeente);
         ArrayList<String> juisteTakeAways = new ArrayList();
         ArrayList<String> vestigingsNamenInJuisteGebied = new ArrayList();
         for(Integer x : vestigingsIDsInJuisteGebied)
         {
            vestigingsNamenInJuisteGebied.add(getTakeAwayVanVestiging(x));
        }
         
        String status =  "actief";
         
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM TakeAway WHERE status = '" + status + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   takeAways.add(rs.getString("naamTakeAway"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van totale waarde bestllingen:  " +  " error:" + ex);
            }
          
          for(String TA : takeAways)
          {
              if (vestigingsNamenInJuisteGebied.contains(TA))
              {
                  juisteTakeAways.add(TA);
              }
          }
          
         return juisteTakeAways;
     }
     
     public ArrayList<String> getAlleCategorieenVanTakeeAwaysMetleveringsgebied(int postcode, String gemeente)
     {
         ArrayList<String> takeAwaysJuisteGebied = getAlleActieveTakeAwaysMetleveringsgebied(postcode, gemeente);
         ArrayList<String> juisteCategorieen = new ArrayList();
         for(String TA: takeAwaysJuisteGebied)
         {
             ArrayList<String> cats = getCategorieenTakeAway(TA);
             for(String cat: cats)
             {
                 if(!juisteCategorieen.contains(cat))
                 juisteCategorieen.add(cat);
             }
         }
         return juisteCategorieen;
     }
     
     public ArrayList<Integer> getVestigingsIDsBijPostcodeEnLeveringsgebied(int postcode,String gemeente)
     {
         ArrayList<Integer> vestigingsIDs =  new ArrayList();
         
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT vestigingsID FROM Vestiging WHERE postcode = '" + postcode + "' AND gemeente = '" + gemeente + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   vestigingsIDs.add(rs.getInt("vestigingsID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van vestigings id bij postcode en gemeente:  " +  " error:" + ex);
            }
         
         return vestigingsIDs;
     }
     
     public void updateAantalLikesReview(int reviewID)
             //verhoogt het aantal likes van een review met 1 in db
     {
         int aantalLikes =  getAantalLikesReview(reviewID);
         aantalLikes++;
         int nieuwAantalLikes = aantalLikes;
         
           try
            {
                con = getConnection();
                Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                String query = "UPDATE Review SET likes = '" + nieuwAantalLikes + "' WHERE reviewID = '" + reviewID + "';";
                st.executeUpdate(query);
            }
            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error bij het updaten van het anatal likes review:" + ex);
                }
                 
     }
     
     public double getLeveringsKostenBijVestigingsID(int vestigingsID)
     {
         double kosten = 0.0;
         
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT leveringskosten FROM Vestiging WHERE vestigingsID = '" + vestigingsID + "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   kosten = rs.getDouble("leveringskosten");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van leveringskosten bij een vestiging  " +  " error:" + ex);
            }
         
         return kosten;
     }
     
    public ArrayList<String> getActieveTakeAwaysMetLeveringsgebiedBijCategorie(int postcode,String gemeente,String categorie)
    //geeft alle takeaways die op actief staan met een vestiging die kan leveren bij de meegegeven postcode en gemeente die 
    //voldoet aan de restrictie van de meegegevencategorie
    {
         ArrayList<String> actieveTakeAwaysMetLeveringsgebied = getAlleActieveTakeAwaysMetleveringsgebied(postcode, gemeente);
         ArrayList<String> alleCategorieenVanTakeAwaysMetLeveringsgebied = getAlleCategorieenVanTakeeAwaysMetleveringsgebied(postcode, gemeente);
         ArrayList<String> juisteTakeAways = new ArrayList();
         String cat = categorie.toLowerCase();
         
         for(String X : actieveTakeAwaysMetLeveringsgebied)
         {
             ArrayList<String> categorieenBijActieveTakeAwayMetLeveringsgebied = getCategorieenTakeAway(X);
             for(String Y : alleCategorieenVanTakeAwaysMetLeveringsgebied)
             {
                 if(categorieenBijActieveTakeAwayMetLeveringsgebied.contains(Y) && Y.toLowerCase().equals(cat))
                 {
                     if(!juisteTakeAways.contains(X))
                     {
                        juisteTakeAways.add(X);
                     }
                 }
             }
         }
         return juisteTakeAways;
    }
    
    public HashMap<String,Integer> getLeveringsGebiedenBijVestigingsID(int vestigingsID)
    {
        HashMap<String,Integer> leveringsGebieden = new HashMap();
;
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT gemeente,postcode FROM Leveringsgebied WHERE vestigingsID = '" + vestigingsID + "' ; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   leveringsGebieden.put(rs.getString("gemeente"),rs.getInt("postcode"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van leveringskosten bij een vestiging  " +  " error:" + ex);
            }
        return leveringsGebieden;
    }
    
    public void updateStatusAfgehandeldBestelling(int bestellingsID)
    {
        String nieuweStatus = "afgehandeld";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Bestelling SET status = '" + nieuweStatus+ "' WHERE bestellingsID = '" + bestellingsID +  "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status bestelling:" + ex);
            }
    }
    
    public void verwijderVestiging(int vestigingsID)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Vestiging WHERE vestigingsID = '" + vestigingsID + "' ;";
            st.executeUpdate(query);
            System.out.println("Vestiging goed verwijderd.");
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een vestiging:" + ex);
            }
    }
    
    public void verwijderLeveringsgebied(int vestigingsID, int postcode, String gemeente)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Leveringsgebied WHERE vestigingsID = '" + vestigingsID+ "' AND gemeente = '" + gemeente +  "' AND postcode = '"  + postcode + "' ;";
            st.executeUpdate(query);
            System.out.println("Vestiging goed verwijderd.");
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een leveringsgebied:" + ex);
            }
    }
    
    public void verwijderCategorie(String naamTakeAway,String categorie)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Soort WHERE naamTakeAway = '" + naamTakeAway + "' AND categorie =  '" + categorie +  "' ;";
            st.executeUpdate(query);
            System.out.println("Categorie goed verwijderd.");
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een categorie:" + ex);
            }
    }
    
    public int getReviewID(int menuID,String naamgerecht,String klantlogin, String reviewTekst)
    {
        int reviewID = 0;
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT reviewID FROM Review WHERE klantLogin = '" + klantlogin + "' AND menuID = '" + menuID + "' AND naamGerecht = '" + naamgerecht + "' AND tekst =  '" + reviewTekst + "'; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   reviewID = rs.getInt("reviewID");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van reviewID bij klant, naagerecht en menuID " +  " error:" + ex);
            }
        return reviewID;
    }
    
    public void nieuweKortingsCode(String codecombinatie,double gegevenKorting,String status,String geldigheidsduur, String gegevenDoorJustFeed, 
            Date begindatum, Date einddatum, String groep,String naamtTA,int vestigingsID)
    {
        con = null;
        
         try
        {     
            con = getConnection();
           
            String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
            + "geldigheidsduur, gegevenDoorJustFeed, begindatum, einddatum, groep, naamTakeAway, vestigingsID)" 
            + "values (?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, codecombinatie);
            preparedStmt.setDouble(2, gegevenKorting);
            preparedStmt.setString(3, status);
            preparedStmt.setString(4,geldigheidsduur);
            preparedStmt.setString(5,gegevenDoorJustFeed);
            preparedStmt.setDate(6,begindatum);
            preparedStmt.setDate(7,einddatum);
            preparedStmt.setString(8, groep);
            preparedStmt.setString(9,naamtTA);
            preparedStmt.setInt(10,vestigingsID);
            
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error kortingscode toevoegen:" + ex);
            }
    }
    
    public void nieuweReviewKortingsCode(String codecombinatie, String naamGerecht, int menuID)
    {
        con = null;
        double gegevenKorting = 0.1;
        String geldigheidsduur = "eenmalig";
        String status = "actief";
        String gegevenDoorJustFeed = "ja";
        String groep = "reviewKC";
        
         try
        {     
            con = getConnection();
           
            String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
            + "geldigheidsduur,gegevenDoorJustFeed,groep,naamGerecht,menuID)" 
            + "values (?,?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, codecombinatie);
            preparedStmt.setDouble(2, gegevenKorting);
            preparedStmt.setString(3, status);
            preparedStmt.setString(4,geldigheidsduur);
            preparedStmt.setString(5,gegevenDoorJustFeed);
            preparedStmt.setString(6, groep);
            preparedStmt.setString(7, naamGerecht);
            preparedStmt.setInt(8,menuID);
            
            
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error reviewKC toevoegen:" + ex);
            }
    }
    
    public void nieuweRegistratieKortingsCode(String codecombinatie)
    {
        con = null;
        double gegevenKorting = 2.0;
        String status = "actief";
        String geldigheidsduur = "eenmalig";
        String gegevenDoorJustFeed = "ja";
        String groep = "registratieKC";
        
         try
        {     
            con = getConnection();
           
            String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
            + "geldigheidsduur, gegevenDoorJustFeed,groep)" 
            + "values (?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, codecombinatie);
            preparedStmt.setDouble(2, gegevenKorting);
            preparedStmt.setString(3, status);
            preparedStmt.setString(4,geldigheidsduur);
            preparedStmt.setString(5,gegevenDoorJustFeed);
            preparedStmt.setString(6, groep);
       
            
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error kortingscode toevoegen:" + ex);
            }
    }
    
    
    public void nieuweKortingBijKlantLogin(String klantlogin, String codecombinatie)
    {
        con = null;
        
        try
        {     
            con = getConnection();
           
            String query = "INSERT into  KortingenKlant (klantLogin,codecombinatie) values (?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, klantlogin);
            preparedStmt.setString(2, codecombinatie);
        
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error klant aan kortingscode koppelen: " + ex);
            }
        
    }
    
     public void nieuweKortingBijBestelling(int bestellingsID, String codecombinatie)
    {
        con = null;
        
        try
        {     
            con = getConnection();
           
            String query = "INSERT into  KortingenBestelling (bestellingsID,codecombinatie) values (?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, bestellingsID);
            preparedStmt.setString(2, codecombinatie);
        
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bestelling aan kortingscode koppelen: " + ex);
            }
        
    }
     
     public ArrayList<String> getLijstCodeCombinaties()
     {
         ArrayList<String> codeCombinaties = new ArrayList();
         
          try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT codecombinatie FROM Kortingscode; ";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   codeCombinaties.add(rs.getString("codecombinatie"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van codecombinaties " +  " error:" + ex);
            }
         
         return codeCombinaties;
     }
    
    public void verwijderGerecht(String naamGerecht, int menuID)
    {
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Gerechten WHERE naamGerecht= '" + naamGerecht+ "' AND menuID = '" + menuID +  "';";
            st.executeUpdate(query);
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een gerecht: " + ex);
            }
    }
    public void verwijderBijgerecht(String naamBijgerecht, int menuID)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Bijgerechtent WHERE naamBijgerecht= '" + naamBijgerecht+ "' AND menuID = '" + menuID +  "';";
            st.executeUpdate(query);
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een Bijgerecht: " + ex);
            }
    }
    
    public void verwijderSnack(String naamSnack, int menuID)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Snacks WHERE naamSnack= '" + naamSnack + "' AND menuID = '" + menuID +  "';";
            st.executeUpdate(query);
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een snack: " + ex);
            }
    }
    
    public void verwijderDrank(String naamDrank, int menuID)
    {
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "DELETE FROM Dranken WHERE naamDrank= '" + naamDrank+ "' AND menuID = '" + menuID +  "';";
            st.executeUpdate(query);
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het deleten van een drank: " + ex);
            }
    }
    
    public String getStatusKortingscode(String codecombinatie)
    {
        String status = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT status FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                   status = rs.getString("status");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van status combinatiecode " +  " error:" + ex);
            }
         return status;
    }
    
    public void updateStatusNaarPassiefKortingscode(String kortingscombinatie)
    {
        String status = "passief";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Kortingscode SET status = '" + status + "' WHERE codecombinatie= '" + kortingscombinatie + "' ;";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status combinatiecode:" + ex);
            }
    }
    
    public void updateStatusNaarActiefKortingscode(String codecombinatie)
    {
        String status = "actief";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            String query = "UPDATE Kortingscode SET status = '" + status + "' WHERE codecombinatie = '" + codecombinatie + "';";
            st.executeUpdate(query);
            
            con.close();
        }
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij het updaten van status combinatiecode:" + ex);
            }
    }
    
    public ArrayList<String> getKortingcodeBijKlantLogin(String klantLogin)
    {
        ArrayList<String> codes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT codecombinatie FROM KortingenKlant WHERE klantLogin = '" + klantLogin + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   codes.add(rs.getString("codecombinatie"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van combinatiecodes bij klantlogin " +  " error:" + ex);
            }
        
        return codes;
    }
    
    public String getGroepBijKortingscode(String codecombinatie)
    {
        String groep = "";
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT groep FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   groep = rs.getString("groep");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van groep bij combinatiecode " +  " error:" + ex);
            }
        return groep;
    }
    
    public double getGegevenKortingBijKortingsCode(String codecombinatie)
    {
        double gegevenKorting = 0;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT gegevenKorting FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   gegevenKorting = rs.getDouble("gegevenKorting");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van gegevenkorting bij combinatiecode " +  " error:" + ex);
            }
        return gegevenKorting;
    }
    
    public String getGeldigheidsduurBijUniekeActieKortingscode(String codecombinatie)
    {
        String geldigheidsduur = "";
        String groep = "uniekeactieKC";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT geldigheidsduur FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "' AND groep = '" + groep + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   geldigheidsduur = rs.getString("geldigheidsduur");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van geldigheidsduur bij combinatiecode " +  " error:" + ex);
            }
        
        return geldigheidsduur;
    }
    public String getPeriodeBijUniekeActieKortingscode(String codecombinatie)
    {
        String periode = "";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT geldigheidsduur FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   periode = rs.getString("geldigheidsduur");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van periode bij combinatiecode " +  " error:" + ex);
            }
        
        return periode;
    }
    public int getVestigingBijUniekeActieKortingscode(String codecombinatie)
    {
        int vestigingsID = 0;
        String groep = "uniekeactieKC";
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT vestigingsID FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "' AND groep = '" + groep+ "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   vestigingsID = rs.getInt("vestigingsID");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van vestigingsID bij combinatiecode " +  " error:" + ex);
            }
        return vestigingsID;
    }
    public String getStatusBijUniekeActieKortingscode(String codecombinatie)
    {
        String status = "";
        String groep = "uniekeactieKC";
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT status FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "' AND groep =  '" + groep + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   status = rs.getString("status");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van status bij combinatiecode " +  " error:" + ex);
            }
        return status;
        
    }
    
    public ArrayList<String> getLijstUniekActieKortingsCodesBijTakeAwayEnZijnVestigingen(String naamTakeAway)
    //methode retourneert een arraylist met alle codecombinaties die in db staan als een uniekeactiekorting
    //die lopen bij een bepaalde takeaway en/of zijn vestigingen        
    {
        String groep = "uniekeactieKC";
        ArrayList<String> vestigingenTakeAway = getVestigingsIDSTakeAway(naamTakeAway);
        ArrayList<String> codes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT codecombinatie FROM Kortingscode WHERE naamTakeAway = '" + naamTakeAway + "' AND groep =  '" + groep + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   codes.add(rs.getString("codecombinatie"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van codecombinaties bij naamtakeaway " +  " error:" + ex);
            }
        
        for (String vest: vestigingenTakeAway)
        {
            try
            {
                con = getConnection();
                Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                String query = "SELECT codecombinatie FROM Kortingscode WHERE vestigingsID = '" + Integer.parseInt(vest) + "' AND groep =  '" + groep + "';";
                rs = st.executeQuery(query);

                while(rs.next())
                    {
                       codes.add(rs.getString("codecombinatie"));
                    }

                con.close();
            }

            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error bij opvragen van codecombinaties bij vestigingen van takeaway " +  " error:" + ex);
                }
        }
        
        return codes;
    }
    
    public void nieuweUniekeActieKortingsCode(String codecombinatie,double gegevenKorting,String geldigheidsduur,Date begindatum,Date einddatum,String naamTakeAway,int vestigingsID)
    {
        con = null;
        String status = "actief";
        String gegevenDoorJustFeed = "nee";
        String groep = "uniekeactieKC";
        
        if (vestigingsID != 0)
        {
           try
           {     
               con = getConnection();

               String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
               + "geldigheidsduur, gegevenDoorJustFeed,beginDatum,einddatum,groep,vestigingsID)" 
               + "values (?,?,?,?,?,?,?,?,?)";

               PreparedStatement preparedStmt = con.prepareStatement(query);
               preparedStmt.setString(1, codecombinatie);
               preparedStmt.setDouble(2, gegevenKorting);
               preparedStmt.setString(3, status);
               preparedStmt.setString(4,geldigheidsduur);
               preparedStmt.setString(5,gegevenDoorJustFeed);
               preparedStmt.setDate(6, begindatum);
               preparedStmt.setDate(7, einddatum);
               preparedStmt.setString(8, groep);
               preparedStmt.setInt(9, vestigingsID);

               preparedStmt.execute();
               con.close();        
           }
        
            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error uniekactieKC toevoegen:" + ex);
                }
        }
        
        if (vestigingsID == 0)
        {
           try
           {     
               con = getConnection();

               String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
               + "geldigheidsduur, gegevenDoorJustFeed,beginDatum,einddatum,groep,naamTakeAway)" 
               + "values (?,?,?,?,?,?,?,?,?)";

               PreparedStatement preparedStmt = con.prepareStatement(query);
               preparedStmt.setString(1, codecombinatie);
               preparedStmt.setDouble(2, gegevenKorting);
               preparedStmt.setString(3, status);
               preparedStmt.setString(4,geldigheidsduur);
               preparedStmt.setString(5,gegevenDoorJustFeed);
               preparedStmt.setDate(6, begindatum);
               preparedStmt.setDate(7, einddatum);
               preparedStmt.setString(8, groep);
               preparedStmt.setString(9, naamTakeAway);

               preparedStmt.execute();
               con.close();        
           }
        
            catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error uniekactieKC toevoegen:" + ex);
                }
           
        }
    }
    
    public int getAantalReviewsOpGerechtenTakeAway(String naamTakeAway)
    {
        int aantalReviews = 0;
        int menuID = getMenuIDTakeAway(naamTakeAway);
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT COUNT(*) FROM Review WHERE menuID = '" + menuID + "';";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    aantalReviews = rs.getInt(1);                    
                }
        
            con.close();
        }
         catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error aantal reviews op gerechten toevoegen:" + ex);
                }
        
        
        return aantalReviews;
    }
    
    public ArrayList<String> getAlleTakeAwaysMetMeerDanDrieReviewsOpGerechtInSysteem()
    {
        ArrayList<String> juisteTakeAways = new ArrayList();
        ArrayList<String> alleTakeAways = getAlleTakeAways();
        
        for(String ta: alleTakeAways)
        {
            if (getAantalReviewsOpGerechtenTakeAway(ta) >= 3)
            {
                juisteTakeAways.add(ta);
            }
        }
        
        return juisteTakeAways;
    }
    
    public ArrayList<Integer> getReviewIDsBijTakeAway(String naamTakeAway)
    {
        ArrayList<Integer> reviewIDs = new ArrayList();
        
        int menuID = getMenuIDTakeAway(naamTakeAway);
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT reviewID FROM Review WHERE menuID = '" + menuID + "';";
            rs = st.executeQuery(query);
            
            
            while(rs.next())
                {
                    reviewIDs.add(rs.getInt("reviewID"));                    
                }
        
            con.close();
        }
         catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error  reviewids bij takeaway:" + ex);
                }
        return reviewIDs;
    }
    
    public double getGemiddeldeScoreReviewsVanTakeAway(String naamTakeAway)
    {
        double reviewScore = 0.0;
        double totaleScore = 0.0;
        ArrayList<Integer> reviewIDs = getReviewIDsBijTakeAway(naamTakeAway);
        
        for(int reviewID : reviewIDs)
        {
            totaleScore += getScoreReview(reviewID);
        }
        
        reviewScore = (totaleScore/reviewIDs.size());
        return reviewScore;
    }
    
    
    public String getHoogsteGemiddeldeReviewScoreTakeAway()
    //op basis van minimum 3 gerechten
    {
        String takeaway = "first";
        
        HashMap<String,Double> scores = new HashMap();
        ArrayList<String> takeaways = getAlleTakeAwaysMetMeerDanDrieReviewsOpGerechtInSysteem();
        scores.put("first", 0.0) ;
        for(String ta : takeaways)
        {
            scores.put(ta,getGemiddeldeScoreReviewsVanTakeAway(ta));
        }
        
        for (String s : scores.keySet())
        {
            if (scores.get(s) > scores.get(takeaway))
            {
                takeaway = s ;
            }
        }
        return takeaway;
    }

    public ArrayList<Integer> getBestellingIDsInMaand(String maand, int jaar)
    {
        ArrayList<Integer> bestellingen = new ArrayList();
        
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT bestellingsID FROM Bestelling WHERE datum between '"+ jaar+"/"+ maand +"/1' AND '"+ jaar+"/"+ maand +"/31';";

            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    bestellingen.add(rs.getInt("bestellingsID"));                    
                }
        
            con.close();
        }
         catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error  bestellingen in bepaalde maand ophalen:" + ex);
                }
        return bestellingen;
    }
    
    public HashMap<String,HashMap<Integer,Integer>> getGerechtenMetMenuIDsEnAantalKerenBesteldInMaandEnJaar(String maand,int jaar)
    //HashMap<gerecht,HashMap<menuID,hoeveelBesteld>>
    {
        ArrayList<Integer> bestellingen = getBestellingIDsInMaand(maand, jaar);
        //HashMap<gerecht,HashMap<menuID,hoeveelBesteld>>
        HashMap<String,HashMap<Integer,Integer>> map = new HashMap();
                
        for(int bestelling: bestellingen)
        {
            //naamTa,naamGerecht,hoveelheid
            HashMap<String,HashMap<String,Integer>> menuGerecht = getMenuGerechtBijBestelling(bestelling);
            //hashmap vullen met relevante gegevens       
            for(String ta: menuGerecht.keySet())
            {
                HashMap<String,Integer> gerechtEnHoveelheid = menuGerecht.get(ta);
                for(String gerecht : gerechtEnHoveelheid.keySet())
                {
                    HashMap<Integer,Integer> menuEnHoveelheid = new HashMap();
                    menuEnHoveelheid.put(getMenuIDTakeAway(ta), menuGerecht.get(ta).get(gerecht));
                    map.put(gerecht, menuEnHoveelheid);
                }
            }
        }
        return map;
    }
    
    
    public String getAwardGerecht(String maand, int jaar)
    {
        
        int keerBesteld = 0;
        String awardGerecht = "";
        
        //<gerecht<menuid,aantalkeerbesteld>>
        HashMap<String,HashMap<Integer,Integer>> map = getGerechtenMetMenuIDsEnAantalKerenBesteldInMaandEnJaar(maand,jaar);
        
        for(String gerecht : map.keySet())
        {
            //<menuid,aantalkeerbesteld>
            HashMap<Integer,Integer> menuEnHoeveel = map.get(gerecht);
            for(int menu : menuEnHoeveel.keySet())
            {
                if(menuEnHoeveel.get(menu) > keerBesteld)
                {
                    keerBesteld = menuEnHoeveel.get(menu);
                    awardGerecht = gerecht;
                    menuID = menu;
                }
            }
        }
        return awardGerecht;
    }

    public int getMenuIDBijAwardGerecht(String maand,int jaar) {
        getAwardGerecht(maand,jaar);
        return menuID;
    }
    
    
    //de takeaway met het meeste aantal orders gedurende de voorbije maand. Krijgt een visuele aanduiding waarmee het kan opvallen t.o.v. andere takeaways.
    public String getBestsellerAward(String maand, int jaar)
    {
        String takeAway = "";
        
        //geeft alle bestellingen die geplaatst zijn in een maand in systeem
        ArrayList<Integer> bestellingen = getBestellingIDsInMaand(maand, jaar);
        
        //hashmap met takeaways en aantal orders
        HashMap<String,Integer> takeawayAantalOrders = new HashMap();
        takeawayAantalOrders.put("lol",1);
        
        for(int bestelling : bestellingen)
        {
            ArrayList<Integer> vestigingen = getVestigingsIDBijBestellingsID(bestelling);
            for(int vest : vestigingen)
            {
                int aantal;
                if ((takeawayAantalOrders.get(getTakeAwayVanVestiging(vest)) == null))
                {
                    aantal =1;
                    takeawayAantalOrders.put(getTakeAwayVanVestiging(vest), aantal);
                }
                else 
                {
                    aantal = takeawayAantalOrders.get(getTakeAwayVanVestiging(vest));
                    aantal++;
                    int nieuwAantal = aantal;
                    takeawayAantalOrders.put(getTakeAwayVanVestiging(vest),nieuwAantal);
                }
            }
        }
        
        int aantal = 0;
        for(String ta: takeawayAantalOrders.keySet())
        {
            if(takeawayAantalOrders.get(ta) > aantal)
            {
                aantal = takeawayAantalOrders.get(ta);
                takeAway = ta;
            }
        }
        return takeAway;
    }
    
    public Date getMaxDateAwardGerecht() 
    {
        Date datum = null;
        
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT MAX(periode) AS datum  FROM AwardGerecht;";

            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    datum = rs.getDate("datum");                    
                }
        
            con.close();
        }
         catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error  maxdatumawardgerecht ophalen:" + ex);
                }
         
         
        return datum;
    }
    
    public boolean controleerOfReviewKCBijGerechtAlBestaat(String klantLogin,String naamTakeAway,String naamGerecht)
    
    {
        boolean waarde = false;
        int menuID = getMenuIDTakeAway(naamTakeAway);
        ArrayList<String> codes = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT codecombinatie FROM Kortingscode WHERE naamGerecht = '" + naamGerecht + "' AND menuID = '" + menuID + "';";

            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    codes.add(rs.getString("codecombinatie"));                    
                }
        
            con.close();
        }
         catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error  codes bij naamgerecht en menuid ophalen:" + ex);
                }
        
        
        for(String code : codes)
        {
            try
            {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT klantLogin FROM KortingenKlant WHERE codecombinatie = '" + code + "'; ";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    String compareKlant = rs.getString("klantLogin") ;
                    if (compareKlant.equalsIgnoreCase(klantLogin))
                    {
                        waarde = true ;
                    }
                    
                    
                }
            
            con.close();
            }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij controleren van korting bij klant , gerecht en menuID error:" + ex);
            }
        }
        
        return waarde;
    }
    
    public void nieuweTakeAwayBossKC(String codecombinatie)
    {
        con = null;
        double gegevenKorting = 0.2;
        String status = "actief";
        String geldigheidsduur = "eenmalig";
        String gegevenDoorJustFeed = "ja";
        String groep = "takeAwayBossKC";
        
         try
        {     
            con = getConnection();
           
            String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
            + "geldigheidsduur, gegevenDoorJustFeed ,groep)" 
            + "values (?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, codecombinatie);
            preparedStmt.setDouble(2, gegevenKorting);
            preparedStmt.setString(3, status);
            preparedStmt.setString(4,geldigheidsduur);
            preparedStmt.setString(5,gegevenDoorJustFeed);
            preparedStmt.setString(6, groep);
            
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error takeawayBossKC toevoegen:" + ex);
            }
    }
    
    public void nieuweJFBossKC(String codecombinatie)
    {
        con = null;
        double gegevenKorting = 0.4;
        String status = "actief";
        String geldigheidsduur = "eenmalig";
        String gegevenDoorJustFeed = "ja";
        String groep = "jfbossKC";
                
         try
        {     
            con = getConnection();
           
            String query = "INSERT into Kortingscode (codecombinatie, gegevenKorting, status, "
            + "geldigheidsduur, gegevenDoorJustFeed, groep)" 
            + "values (?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, codecombinatie);
            preparedStmt.setDouble(2, gegevenKorting);
            preparedStmt.setString(3, status);
            preparedStmt.setString(4,geldigheidsduur);
            preparedStmt.setString(5,gegevenDoorJustFeed);
            preparedStmt.setString(6, groep);
            
            preparedStmt.execute();
            con.close();        
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error jfbossKC toevoegen:" + ex);
            }
    }
    
    public ArrayList<String> getTakeAwaysWaarbijBesteldIsInMaand(String maand,int jaar)
    {
        ArrayList<Integer> bestellingsIDs = getBestellingIDsInMaand(maand, jaar);
        ArrayList<String> takeaways = new ArrayList();
         
        for(int bestelling : bestellingsIDs)
        {
            ArrayList<Integer> vestigingenBijBestellingen = getVestigingsIDBijBestellingsID(bestelling);
            for(Integer vest : vestigingenBijBestellingen)
            {
                if(!takeaways.contains(getTakeAwayVanVestiging(vest)))
                {
                    takeaways.add(getTakeAwayVanVestiging(vest));
                }
            }
        }
        return takeaways;
    }
    
    public ArrayList<String> getAlleKlanten()
    {
        ArrayList<String> klanten =  new ArrayList();
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT klantLogin FROM Klant;";

            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                    klanten.add(rs.getString("klantLogin"));                    
                }
        
            con.close();
        }
         catch(Exception ex)
                {
                    try {con.close();} catch(Exception ex2) {}
                    System.out.println("Error  klanten ophalen:" + ex);
                }
         return klanten;
    }
    
    public String getTakeAwayBossKC(String takeaway,String maand, int jaar)
    {
        String klant = "";
        double omzet = 0;
        
        ArrayList<String> klanten  = getKlantLoginsBesteldInMaand(maand, jaar);
        for(String klnt : klanten)
        {
            double omzetKlant = getOmzetKlantBijTakeAwayInEenMaand(klnt, takeaway, maand, jaar);
            System.out.println(omzetKlant);
            if(omzetKlant > omzet)
            {
                omzet = omzetKlant;
                klant = klnt;
            }
        }
        return klant;
    }
    
    public ArrayList<String> getLijstKlantenTakeAwayBossKCInMaand(String maand,int jaar)
    {
        ArrayList<String> takeaways = getTakeAwaysWaarbijBesteldIsInMaand(maand, jaar);
        ArrayList<String> klanten = new ArrayList();
        
        for(String takeAway : takeaways)
        {
            klanten.add(getTakeAwayBossKC(takeAway, maand,jaar));
        }
        return klanten;
    }

    public String getNaamTakeAwayBijUniekeActieKC(String codecombinatie)
    {
        String naamTakeAway = "";
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "' ;";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   naamTakeAway = rs.getString("naamTakeAway");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van naamTakeAway bij combinatiecode " +  " error:" + ex);
            }
         return naamTakeAway;
    }
    
    public ArrayList<String> getUniekeActieKortingsCodesTakeAway(String takeaway)
    {
        ArrayList<String> codes = new ArrayList();
        String groep = "uniekeactieKC";
        String ja = "ja";
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT codecombinatie FROM Kortingscode WHERE naamTakeAway = '" + takeaway + "' AND groep = '" + groep + "' ;";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   codes.add(rs.getString("codecombinatie"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van codecombinaties van type uniekeactieKC bij takeaway" +  " error:" + ex);
            }
        return codes;
    }
    
    public ArrayList<Integer> getBestellingsIDsBijCodeCombinatie(String codecombinatie)
    {
        ArrayList<Integer> bestellingsIDs = new ArrayList();
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT bestellingsID FROM KortingenBestelling WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   bestellingsIDs.add(rs.getInt("bestellingsID"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van bestellingsids bij codecombinatie" +  " error:" + ex);
            }
        return bestellingsIDs;
    }
    
    public Date getBeginDatumUniekeAK(String codecombinatie)
    {
        Date datum = null;
        
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT beginDatum FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   datum = rs.getDate("beginDatum");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van begindatum bij codecombinatie" +  " error:" + ex);
            }
        
        return datum;
    }
    
    public Date getEindeDatumUniekeAK(String codecombinatie)
    {
        Date datum = null;
        
         try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT eindDatum FROM Kortingscode WHERE codecombinatie = '" + codecombinatie + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   datum = rs.getDate("eindDatum");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van einddatum bij codecombinatie" +  " error:" + ex);
            }
        
        return datum;
    }
    
    public ArrayList<String> getCodeCombinatiesBijKlantLogin(String klantlogin)
    {
        ArrayList<String> codes = new ArrayList(); 
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT codecombinatie FROM KortingenKlant WHERE klantLogin = '" + klantlogin + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   codes.add(rs.getString("codecombinatie"));
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van code combinaties bij klantlogin" +  " error:" + ex);
            }
        return codes;
    }
    
    public double getWaardeUniekActieKCOmzetTakeAwayInMaand(String takeAway,String maand, int jaar)
    {
        double waarde = 0.0;
        int menuID = getMenuIDTakeAway(takeAway);
        
        ArrayList<Integer> bestellingenInMaand = getBestellingIDsInMaand(maand, jaar);
        ArrayList<String> actieCodes = getUniekeActieKortingsCodesTakeAway(takeAway);
        
        for(String code : actieCodes)
        {
            ArrayList<Integer> bestellingsCodesBijCodeCombinatie = getBestellingsIDsBijCodeCombinatie(code);
            for(int bestelling : bestellingsCodesBijCodeCombinatie)
            {
                for(int bestellingMaand : bestellingenInMaand)
                {
                    if(bestelling == bestellingMaand)
                    {
                        double gegevenKorting = getGegevenKortingBijKortingsCode(code);
                        if(gegevenKorting> 1)
                        {
                        waarde += ((getBedragParieleBestelling(menuID, bestellingMaand))*gegevenKorting);
                        }
                        else
                        {
                            waarde += gegevenKorting;
                        }
                    }
                }
            }
        }
        return waarde;
    }
    
    public double getCommissieTakeAwayInMaand(String takeAway,String maand, int jaar)
    {
        double commissie,omzet,geldwaarde,uniekeActieKCOmzet;
        int aantalOrders;
        
        String jfeeder = getJFFeederAwardKort();
        commissie = 0.0;
        omzet = getOmzetTakeAway(takeAway);
        uniekeActieKCOmzet = getWaardeUniekActieKCOmzetTakeAwayInMaand(takeAway,maand,jaar);
        geldwaarde = omzet-uniekeActieKCOmzet;
        aantalOrders = getAantalBesteldTakeAway(takeAway);

        if(takeAway.equals(jfeeder))
        {
            commissie = Math.max((0.07 *(geldwaarde)), (Math.min((0.1 * (geldwaarde)), (((-1/5000) * (aantalOrders) + (11/100)) * (geldwaarde)))));
            commissie = (commissie*0.99);
        }
        else
        {
            commissie = Math.max((0.07 *(geldwaarde)), (Math.min((0.1 * (geldwaarde)), (((-1/5000) * (aantalOrders) + (11/100)) * (geldwaarde)))));
        }
        return commissie;
    }
    
    public double getTotalCommissieJustFeedInMaand(String maand, int jaar)
    {
        ArrayList<String> takeaways = getAlleTakeAways();
        double totaleCommissieJF = 0.0;
        for (String takeaway : takeaways)
        {
            totaleCommissieJF += getCommissieTakeAwayInMaand(takeaway,maand,jaar);
        }
        return totaleCommissieJF;
    }
    
    public String getJFFeederAwardInMaand(String maand, int jaar)
    {
        String takeaway = "";
        
        ArrayList<String> takeaways = getAlleTakeAways();
        double hoogsteCommissie = 0.0;    
        for(String ta : takeaways)
        {
            double commissieTa = getCommissieTakeAwayInMaand(ta,maand,jaar);
            if (commissieTa > hoogsteCommissie)
            {
                takeaway = ta;
                hoogsteCommissie = commissieTa;
            }
        }
        return takeaway;
    }
    
    public Date getMaxDateAwardTakeAway()
    {
        Date datum = null;
        
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT MAX(periode) AS maxdate FROM AwardTakeAway ;";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   datum = rs.getDate("maxdate");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van maxdate bij awardtakeaway" +  " error:" + ex);
            }
        return datum;
    }
    
    public String getBestsellerAwardKort()
    {
        Date maxDatum = getMaxDateAwardTakeAway();
        String takeaway = "";
        String awardType = "Bestseller";
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM AwardTakeAway WHERE periode = '" + maxDatum + "' AND naamAwardTA = '" + awardType + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   takeaway = rs.getString("naamTakeAway");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van besteselleraward" +  " error:" + ex);
            }
        return takeaway;
    }
    
    public String getUserChoiceAwardKort()
    {
        Date maxDatum = getMaxDateAwardTakeAway();
        String takeaway = "";
        String awardType = "User Choice";
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM AwardTakeAway WHERE periode = '" + maxDatum + "' AND naamAwardTA = '" + awardType + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   takeaway = rs.getString("naamTakeAway");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van User Choice" +  " error:" + ex);
            }
        return takeaway;
    }
    
    public String getJFFeederAwardKort()
    {
        Date maxDatum = getMaxDateAwardTakeAway();
        String takeaway = "";
         String awardType = "Just Feeder";
        try
        {
            con = getConnection();
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            String query = "SELECT naamTakeAway FROM AwardTakeAway WHERE periode = '" + maxDatum + "' AND naamAwardTA = '" + awardType + "';";
            rs = st.executeQuery(query);
            
            while(rs.next())
                {
                   takeaway = rs.getString("naamTakeAway");
                }
        
            con.close();
        }
        
        catch(Exception ex)
            {
                try {con.close();} catch(Exception ex2) {}
                System.out.println("Error bij opvragen van Just Feeder" +  " error:" + ex);
            }
        return takeaway;
    }
}
