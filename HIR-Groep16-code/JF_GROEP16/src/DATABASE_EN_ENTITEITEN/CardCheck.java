package DATABASE_EN_ENTITEITEN;

import java.util.ArrayList;

public class CardCheck {

    public ArrayList arrayIt(String s)
    {
        ArrayList al = new ArrayList();
         for (int i = 0; i < s.length()-1; i++) 
         {
            int getal = Integer.parseInt(s.substring(i, i+1));
            al.add(getal);
         }
         return al;
    }
    
    public static int getCheck(String s)
    {
        int check;
        check = Integer.parseInt(s.substring(s.length()-1));
        return check;
    }
    
   
    public ArrayList dubbel2e (ArrayList a)
    {
        int oud;
        int nieuw;
        
        ArrayList<Integer> adubbel = new ArrayList<Integer>(a); //waarden overzetten in andere array// niet enkel de referentie
        
        
        for (int i = 1; i < a.size(); i=i+2) 
        {
            oud = (int) a.get(i);
            nieuw = oud*2;
            adubbel.set(i, nieuw);
        }
        
        return adubbel;
    }
    
    public ArrayList als2edubbeloptellen (ArrayList a)
    {
        
        int nieuw;
        
        ArrayList<Integer> aoptellen = new ArrayList<Integer>(a); //waarden overzetten in andere array// niet enkel de referentie
        
        for (int i = 1; i < a.size(); i=i+2) 
        {
            int waarde = (int) a.get(i);
            nieuw = waarde;
            if( waarde > 9)
            {
                if (waarde == 10)
                    nieuw = 1;
                if (waarde == 12)
                    nieuw = 3;
                if (waarde == 14)
                    nieuw = 5;
                if (waarde == 16)
                    nieuw = 7;
                if (waarde == 18)
                    nieuw = 9;
            }
            aoptellen.set(i, nieuw);
        }
        
        return aoptellen;
    }
    
    
    public int sumIt(ArrayList a)
    {
        int sum = 0;
        int waarde;
        
        for (int i = 0; i < a.size(); i++) 
        {
            waarde = (int) a.get(i);
            sum+= waarde;
            
        }
        
        return sum;
    }
    
   public boolean checkNummer(String s)
   {
       ArrayList<Integer> arrayl; 
       ArrayList aldubbels,als2edubbeloptellenn;
       int sum;
       int xx9;
       int check;
       
        arrayl = this.arrayIt(s);
        check = getCheck(s);
        aldubbels = this.dubbel2e(arrayl);
        als2edubbeloptellenn = this.als2edubbeloptellen(aldubbels);
        sum = this.sumIt(als2edubbeloptellenn);
        
        xx9 = sum *9;
        
        int rest10 = xx9%10;
        
        if (rest10 == check)
            return true;
        else
            return false;
   }
            
    
   
    
    
   
}
    
