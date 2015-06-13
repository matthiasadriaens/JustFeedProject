package GUI;

import DATABASE_EN_ENTITEITEN.*;
import java.io.FileOutputStream;
 import java.util.Date;
 import com.itextpdf.text.Anchor;
 import com.itextpdf.text.BadElementException;
 import com.itextpdf.text.BaseColor;
 import com.itextpdf.text.Chapter;
 import com.itextpdf.text.Chunk;
 import com.itextpdf.text.Document;
 import com.itextpdf.text.DocumentException;
 import com.itextpdf.text.Element;
 import com.itextpdf.text.Font;
 import com.itextpdf.text.Image;
 import com.itextpdf.text.List;
 import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.Phrase;
 import com.itextpdf.text.Section;
 import com.itextpdf.text.pdf.PdfPCell;
 import com.itextpdf.text.pdf.PdfPTable;
 import com.itextpdf.text.pdf.PdfWriter;
 import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
 import java.awt.Desktop;
 import java.awt.Toolkit;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import static java.lang.System.in;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class maakPDF {
 private static String FILE = "H:\\\\NetBeansProjects\\\\ROBINDEHONDT_BELEID\\\\Rapporten\\\\TakeAway\\\\menu.pdf";
 private static String FILE1 = "H:\\\\NetBeansProjects\\\\ROBINDEHONDT_BELEID\\\\Rapporten\\\\TakeAway\\\\verkopenTakeAway.pdf";
 private static String FILE2 = "H:\\\\NetBeansProjects\\\\ROBINDEHONDT_BELEID\\\\Rapporten\\\\TakeAway\\\\lopendeOrdersTakeAway.pdf";
 private static String FILE3 = "H:\\\\NetBeansProjects\\\\ROBINDEHONDT_BELEID\\\\Rapporten\\\\Vestiging\\\\verkopenVestiging.pdf";
 private static String FILE4 = "H:\\\\NetBeansProjects\\\\ROBINDEHONDT_BELEID\\\\Rapporten\\\\Vestiging\\\\lopendeOrdersVestiging.pdf";
 private static String FILE5 = "H:\\\\NetBeansProjects\\\\ROBINDEHONDT_BELEID\\\\Rapporten\\\\TakeAway\\\\uniekeActieKortingen.pdf";
 
 private DecimalFormat f = new DecimalFormat("##.00") ;
 
 private static Font catFont = new Font(Font.FontFamily.HELVETICA, 18,
 Font.BOLD);
 private static Font ratFont = new Font(Font.FontFamily.HELVETICA, 12,
 Font.BOLD);
 private static Font whiteFont = new Font(Font.FontFamily.HELVETICA, 12,
 Font.NORMAL, BaseColor.WHITE);
 private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
 Font.BOLD);
 private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
 Font.BOLD);
 private DB db = new DB();

 public maakPDF(){}
 
public void menuPDF(String takeAway) {
 try {
    Document document = new Document(PageSize.A4, 0, 0, 0, 0);
    PdfWriter.getInstance(document, new FileOutputStream(FILE));
    document.open();
    Image img3 = Image.getInstance("ons zalig logo.jpg");
    img3.scalePercent(80);
    img3.setAlignment(Image.ALIGN_MIDDLE);
    img3.setSpacingBefore(50f);
    String text = "Menu-Overzicht: " + takeAway;
    Phrase phrase1 = new Phrase("");
    phrase1.setFont(catFont);
    Paragraph paragraph1 = new Paragraph(phrase1);
    Phrase phrase = new Phrase(text,catFont);
    phrase.setFont(catFont);
    Paragraph paragraph = new Paragraph(phrase);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    paragraph.setSpacingBefore(100f);
    for (int i=0;i<10;i++)
        {
            document.add(new Paragraph(Chunk.NEWLINE));
        }
    document.add(img3);
    document.add(paragraph1);
    document.add(paragraph);
    document.newPage();
    //gerechtenTabel
    PdfPTable gerechtenTable = new PdfPTable(2);
    PdfPCell cell = new PdfPCell(new Paragraph("Gerechten",whiteFont));
    cell.setColspan(2);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBackgroundColor(BaseColor.DARK_GRAY);
    gerechtenTable.addCell(cell);
    for (String ger: db.getGerechtenEnPrijzenTakeAway(takeAway).keySet())
        {
            gerechtenTable.addCell(ger);
            gerechtenTable.addCell("€ " + f.format(db.getGerechtenEnPrijzenTakeAway(takeAway).get(ger)) + "");
        }
    gerechtenTable.setSpacingBefore(10f);
    gerechtenTable.setSpacingAfter(10f);
    //bijgerechtenTabel
    PdfPTable bijgerechtenTable = new PdfPTable(2);
    PdfPCell bijgerechtenCell = new PdfPCell(new Paragraph("Bijgerechten",whiteFont));
    bijgerechtenCell.setColspan(2);
    bijgerechtenCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    bijgerechtenCell.setVerticalAlignment(Element.ALIGN_CENTER);
    bijgerechtenCell.setBackgroundColor(BaseColor.DARK_GRAY);
    bijgerechtenTable.addCell(bijgerechtenCell);
    for (String ger: db.getBijgerechtenEnPrijzenTakeAway(takeAway).keySet())
        {
            bijgerechtenTable.addCell(ger);
            bijgerechtenTable.addCell("€" + db.getBijgerechtenEnPrijzenTakeAway(takeAway).get(ger) + "");
        }
    bijgerechtenTable.setSpacingBefore(10f);
    bijgerechtenTable.setSpacingAfter(10f);
    
    //snacksTabel
    PdfPTable snacksTable = new PdfPTable(2);
    PdfPCell snacksCell = new PdfPCell(new Paragraph("Snacks",whiteFont));
    snacksCell.setColspan(2);
    snacksCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    snacksCell.setVerticalAlignment(Element.ALIGN_CENTER);
    snacksCell.setBackgroundColor(BaseColor.DARK_GRAY);
    snacksTable.addCell(snacksCell);
    for (String ger: db.getSnacksEnPrijzenTakeAway(takeAway).keySet())
        {
            snacksTable.addCell(ger);
            snacksTable.addCell("€" + f.format(db.getSnacksEnPrijzenTakeAway(takeAway).get(ger)) + "");
        }
    snacksTable.setSpacingBefore(10f);
    snacksTable.setSpacingAfter(10f);
    
     //drankenTabel
    PdfPTable drankenTable = new PdfPTable(2);
    PdfPCell drankenCell = new PdfPCell(new Paragraph("Dranken",whiteFont));
    drankenCell.setColspan(2);
    drankenCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    drankenCell.setBackgroundColor(BaseColor.DARK_GRAY);
    drankenTable.addCell(drankenCell);
    for (String ger: db.getDrankenEnPrijzenTakeAway(takeAway).keySet())
        {
            drankenTable.addCell(ger);
            drankenTable.addCell("€" + f.format(db.getDrankenEnPrijzenTakeAway(takeAway).get(ger)) + "");
        }
    drankenTable.setSpacingBefore(10f);
    drankenTable.setSpacingAfter(10f);
    
    //tables toevoegen aan document
    document.add(new Paragraph(Chunk.NEWLINE));
    document.add(gerechtenTable);
    document.add(bijgerechtenTable);
    document.add(snacksTable);
    document.add(drankenTable);
    document.close();
    
    } catch (Exception e) {
    e.printStackTrace();
   }

   }
 
public void verkopenPDF(String takeAway)
{
    try {
        //maken van document
        int menuID = db.getMenuIDTakeAway(takeAway);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE1));
        document.open();
        Image img3 = Image.getInstance("ons zalig logo.jpg");
        img3.scalePercent(80);
        img3.setAlignment(Image.ALIGN_MIDDLE);
        img3.setSpacingBefore(50f);
        String text = "Verkopen-Overzicht: " + takeAway;
        Phrase phrase1 = new Phrase("");
        phrase1.setFont(catFont);
        Paragraph paragraph1 = new Paragraph(phrase1);
        Phrase phrase = new Phrase(text,catFont);
        phrase.setFont(catFont);
        Paragraph paragraph = new Paragraph(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(100f);
        for (int i=0;i<10;i++)
        {
            document.add(new Paragraph(Chunk.NEWLINE));
        }
        document.add(img3);
        document.add(paragraph1);
        document.add(paragraph);
        //nieuwe pagina document
        document.newPage();
        //gerechtenTable
        PdfPTable gerechtenTable = new PdfPTable(4);
        PdfPCell cell = new PdfPCell(new Paragraph("Omzet Gerechten",whiteFont));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        gerechtenTable.addCell(cell);
        Phrase gerechtenP = new Phrase("Gerecht",ratFont);
        Phrase prijsP = new Phrase("Prijs",ratFont);
        Phrase aantalP = new Phrase("# keer besteld",ratFont);
        Phrase omzetGerechtP = new Phrase("Omzet Gerecht",ratFont);
        gerechtenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        gerechtenTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        gerechtenTable.setTotalWidth(510);
        gerechtenTable.setLockedWidth(true);
        gerechtenTable.addCell(gerechtenP);
        gerechtenTable.addCell(prijsP);
        gerechtenTable.addCell(aantalP);
        gerechtenTable.addCell(omzetGerechtP);
        gerechtenTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        HashMap<String,Double> gerechtenenprijzen = db.getGerechtenEnPrijzenTakeAway(takeAway);
        for (String ger: gerechtenenprijzen.keySet())
            {
                gerechtenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                gerechtenTable.addCell(ger);
                gerechtenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                gerechtenTable.addCell("€" + f.format(gerechtenenprijzen.get(ger)) + "");
                gerechtenTable.addCell(db.getAantalBesteldGerecht(ger, menuID) + "");
                gerechtenTable.addCell("€" + f.format(db.getAantalBesteldGerecht(ger, menuID)*gerechtenenprijzen.get(ger)) + "");
            }
        gerechtenTable.setSpacingBefore(10f);
        gerechtenTable.setSpacingAfter(10f);
        
        //bijgerechtenTable
        PdfPTable bijgerechtenTable = new PdfPTable(4);
        PdfPCell bijgerechtenCell = new PdfPCell(new Paragraph("Omzet Bijgerechten",whiteFont));
        bijgerechtenCell.setColspan(4);
        bijgerechtenCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        bijgerechtenCell.setBackgroundColor(BaseColor.DARK_GRAY);
        bijgerechtenTable.setTotalWidth(510);
        bijgerechtenTable.setLockedWidth(true);
        bijgerechtenTable.addCell(bijgerechtenCell);
        Phrase gerechtenB = new Phrase("Bijgerecht",ratFont);
        Phrase prijsB = new Phrase("Prijs",ratFont);
        Phrase aantalB = new Phrase("# keer besteld",ratFont);
        Phrase omzetGerechtB = new Phrase("Omzet bijgerecht",ratFont);
        bijgerechtenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        bijgerechtenTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        bijgerechtenTable.addCell(gerechtenB);
        bijgerechtenTable.addCell(prijsB);
        bijgerechtenTable.addCell(aantalB);
        bijgerechtenTable.addCell(omzetGerechtB);
        bijgerechtenTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        HashMap<String,Double> bijgerechtenenprijzen = db.getBijgerechtenEnPrijzenTakeAway(takeAway);
        for (String bijger: bijgerechtenenprijzen.keySet())
            {
                bijgerechtenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                bijgerechtenTable.addCell(bijger);
                bijgerechtenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                bijgerechtenTable.addCell("€ " + f.format(bijgerechtenenprijzen.get(bijger)) + "");
                bijgerechtenTable.addCell(db.getAantalBesteldBijgerecht(bijger, menuID) + "");
                bijgerechtenTable.addCell("€ " + f.format(db.getAantalBesteldBijgerecht(bijger, menuID)*bijgerechtenenprijzen.get(bijger)) + "");
            }
        bijgerechtenTable.setSpacingBefore(10f);
        bijgerechtenTable.setSpacingAfter(10f);
        
        //snacksTable////////////////////////////////////////////////////////////////////////////////////
        PdfPTable snacksTable = new PdfPTable(4);
        PdfPCell snacksCell = new PdfPCell(new Paragraph("Omzet Snacks",whiteFont));
        snacksCell.setColspan(4);
        snacksCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        snacksCell.setBackgroundColor(BaseColor.DARK_GRAY);
        snacksTable.addCell(snacksCell);
        Phrase gerechtenS = new Phrase("Snacks",ratFont);
        Phrase prijsS = new Phrase("Prijs",ratFont);
        Phrase aantalS = new Phrase("# keer besteld",ratFont);
        Phrase omzetGerechtS = new Phrase("Omzet snack",ratFont);
        snacksTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        snacksTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        snacksTable.setTotalWidth(510);
        snacksTable.setLockedWidth(true);
        snacksTable.addCell(gerechtenS);
        snacksTable.addCell(prijsS);
        snacksTable.addCell(aantalS);
        snacksTable.addCell(omzetGerechtS);
        snacksTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        HashMap<String,Double> snacksenprijzen = db.getSnacksEnPrijzenTakeAway(takeAway);
        for (String sna: snacksenprijzen.keySet())
            {
                snacksTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                snacksTable.addCell(sna);
                snacksTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                snacksTable.addCell("€" + f.format(snacksenprijzen.get(sna)) + "");
                snacksTable.addCell(db.getAantalBesteldSnack(sna, menuID) + "");
                snacksTable.addCell("€" + f.format(db.getAantalBesteldSnack(sna, menuID)*snacksenprijzen.get(sna)) + "");
            }
        snacksTable.setSpacingBefore(10f);
        snacksTable.setSpacingAfter(10f);
        //draneknTable//////////////////////////////////////////////////////////////////////////////
        PdfPTable drankenTable = new PdfPTable(4);
        PdfPCell drankenCell = new PdfPCell(new Paragraph("Omzet Dranken",whiteFont));
        drankenCell.setColspan(4);
        drankenCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        drankenCell.setBackgroundColor(BaseColor.DARK_GRAY);
        drankenTable.setTotalWidth(510);
        drankenTable.setLockedWidth(true);
        drankenTable.addCell(drankenCell);
        Phrase gerechtenD = new Phrase("Dranken",ratFont);
        Phrase prijsD = new Phrase("Prijs",ratFont);
        Phrase aantalD = new Phrase("# keer besteld",ratFont);
        Phrase omzetGerechtD = new Phrase("Omzet drank",ratFont);
        drankenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        drankenTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        drankenTable.addCell(gerechtenD);
        drankenTable.addCell(prijsD);
        drankenTable.addCell(aantalD);
        drankenTable.addCell(omzetGerechtD);
        drankenTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        HashMap<String,Double> drakenEnPrijzen = db.getDrankenEnPrijzenTakeAway(takeAway);
        for (String sna: drakenEnPrijzen.keySet())
            {
                drankenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                drankenTable.addCell(sna);
                drankenTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                drankenTable.addCell("€" + f.format(drakenEnPrijzen.get(sna)) + "");
                drankenTable.addCell(db.getAantalBesteldDrank(sna, menuID) + "");
                drankenTable.addCell("€" + f.format(db.getAantalBesteldDrank(sna, menuID)*drakenEnPrijzen.get(sna)) + "");
            }
        drankenTable.setSpacingBefore(10f);
        drankenTable.setSpacingAfter(10f);
        
        //orderhistoriekTable//////////////////////////////////////////////////////////////////////////////
        PdfPTable orderTable = new PdfPTable(4);
        PdfPCell orderCell = new PdfPCell(new Paragraph("Orderhistoriek",whiteFont));
        orderCell.setColspan(4);
        orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        orderCell.setBackgroundColor(BaseColor.DARK_GRAY);
        orderTable.addCell(orderCell);
        Phrase gerechtenO = new Phrase("Datum",ratFont);
        Phrase prijsO = new Phrase("Vestiging",ratFont);
        Phrase aantalO = new Phrase("klant",ratFont);
        Phrase omzetGerechtO = new Phrase("bedrag",ratFont);
        orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        orderTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        orderTable.setWidths(new int[]{60,200,100,50});
        orderTable.setTotalWidth(510);
        orderTable.setLockedWidth(true);
        orderTable.addCell(gerechtenO);
        orderTable.addCell(prijsO);
        orderTable.addCell(aantalO);
        orderTable.addCell(omzetGerechtO);
        orderTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        //opbouwen vullen tabel
        ArrayList<String> vestigingsIDs = db.getVestigingsIDSTakeAway(takeAway);
        ArrayList<Integer> bestellingsIDs = new ArrayList();
        //vullen van bestellingsidsArraList met alle bestellingsIDs vloeiend voort uit de verschillende vestiginsIDs aan elkaar gekoppeld in de GeplaatstBij-Table
        for (String vestiging : vestigingsIDs)
        {
            ArrayList<String> bestellingen = db.getBestellingsIDBijVestigingsID(Integer.parseInt(vestiging));
            for (int x=0;x<bestellingen.size();x++)
            {
                bestellingsIDs.add(Integer.parseInt(bestellingen.get(x)));
            }
        }
        Collections.sort(bestellingsIDs);
        for (Integer ID: bestellingsIDs)
            {
                
                ArrayList<Integer> vestigingenBestellingen = db.getVestigingsIDBijBestellingsID(ID);
                for (String vest : vestigingsIDs)
                {
                    if (vestigingenBestellingen.contains(Integer.parseInt(vest)))
                    {
                       orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                       orderTable.addCell(db.getDatumBijBestellingsID(ID) + "");
                       orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                       orderTable.addCell(db.getAdresBijVestigingsID(Integer.parseInt(vest)));
                       orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                       orderTable.addCell(db.getKlantLoginBijBestellingsID(ID)+ "");
                       orderTable.addCell("€" + f.format(db.getBedragParieleBestelling(db.getMenuIDTakeAway(takeAway),ID)) + "" ); 
                    }
                }
            }
        
        drankenTable.setSpacingBefore(10f);
        drankenTable.setSpacingAfter(10f);
        
        document.add(new Paragraph(new Phrase("  1.Orderhistoriek",catFont)));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(orderTable);
        document.newPage();
        document.add(new Paragraph(new Phrase("  2.Gegenereerde Omzet",catFont)));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(gerechtenTable);
        document.add(bijgerechtenTable);
        document.add(snacksTable);
        document.add(drankenTable);
        document.close();
        } catch (Exception e) {
            e.printStackTrace();
       }
       
    }

public void lopendeOrdersPDF(String takeAway) {
 try {
    Document document = new Document(PageSize.A4, 0, 0, 0, 0);
    PdfWriter.getInstance(document, new FileOutputStream(FILE2));
    document.open();
    Image img3 = Image.getInstance("ons zalig logo.jpg");
    img3.scalePercent(80);
    img3.setAlignment(Image.ALIGN_MIDDLE);
    img3.setSpacingBefore(50f);
    String text = "Lopende Orders: " + takeAway;
    Phrase phrase1 = new Phrase("");
    phrase1.setFont(catFont);
    Paragraph paragraph1 = new Paragraph(phrase1);
    Phrase phrase = new Phrase(text,catFont);
    phrase.setFont(catFont);
    Paragraph paragraph = new Paragraph(phrase);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    paragraph.setSpacingBefore(100f);
    for (int i=0;i<10;i++)
        {
            document.add(new Paragraph(Chunk.NEWLINE));
        }
    document.add(img3);
    document.add(paragraph1);
    document.add(paragraph);
    document.newPage();
    

    //bestellingstabel
    ArrayList<Integer> bestellingsIDs = db.getBestellingsIDsPendingTakeAway(takeAway);
    int bestellingsCount = 1;
    document.add(new Paragraph(new Phrase("     1. Lopende Orders:",catFont)));
    for (int i=0;i<1;i++)
    {
        document.add(new Paragraph(Chunk.NEWLINE));
    }
    
    for (Integer ID: bestellingsIDs)
    {
        ArrayList<Integer> vestigingsIDsBES = db.getVestigingsIDBijBestellingsID(ID);
        ArrayList<String> vestigingsIDsTA = db.getVestigingsIDSTakeAway(takeAway);
            
        for(Integer vestBES : vestigingsIDsBES)
        {
            for(String vestTA: vestigingsIDsTA )
            {
                if(vestBES == Integer.parseInt(vestTA))
                {
                    HashMap<String,HashMap<String,Integer>> gerechten = db.getMenuGerechtBijBestelling(ID);
                    HashMap<String,HashMap<String,Integer>> bijgerechten = db.getMenuBijgerechtBijBestelling(ID);
                    HashMap<String,HashMap<String,Integer>> snacks = db.getMenuSnacksBijBestelling(ID);
                    HashMap<String,HashMap<String,Integer>> dranken = db.getMenuDrankenBijBestelling(ID);

                    HashMap<String,Integer> bestellingsItems = new HashMap(); //items en hoeveelheden

                    for(String x: gerechten.keySet())
                    {
                        for (String y : gerechten.get(x).keySet())
                        {
                            bestellingsItems.put(y, gerechten.get(x).get(y));
                        }
                    }

                    for(String x: bijgerechten.keySet())
                    {
                        for (String y : bijgerechten.get(x).keySet())
                        {
                            bestellingsItems.put(y, bijgerechten.get(x).get(y));
                        }
                    }

                    for(String x: snacks.keySet())
                    {
                        for (String y : snacks.get(x).keySet())
                        {
                            bestellingsItems.put(y, snacks.get(x).get(y));
                        }
                    }

                    for(String x: dranken.keySet())
                    {
                        for (String y : dranken.get(x).keySet())
                        {
                            bestellingsItems.put(y, dranken.get(x).get(y));
                        }
                    }

                    PdfPTable bestellingsTable = new PdfPTable(4);
                    PdfPCell cell = new PdfPCell(new Paragraph("Bestelling " + bestellingsCount + " (" + db.getAdresBijVestigingsID(Integer.parseInt(vestTA)) + ")",whiteFont));
                    bestellingsCount++;
                    cell.setColspan(4);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.DARK_GRAY);
                    bestellingsTable.addCell(cell);
                    bestellingsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    bestellingsTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
                    bestellingsTable.setTotalWidth(550);
                    bestellingsTable.setWidths(new int[]{90,120,50,120});
                    bestellingsTable.setLockedWidth(true);
                     Phrase klantlogin = new Phrase("KlantLogin",ratFont);
                    Phrase adres = new Phrase("Leveringsadres",ratFont);
                    Phrase totaalprijs = new Phrase("Totaalprijs",ratFont);
                    Phrase bestelling = new Phrase("Bestelling",ratFont);
                    bestellingsTable.addCell(klantlogin);
                    bestellingsTable.addCell(adres);
                    bestellingsTable.addCell(totaalprijs);
                    bestellingsTable.addCell(bestelling);
                    bestellingsTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                    bestellingsTable.addCell(db.getKlantLoginBijBestellingsID(ID));
                    bestellingsTable.addCell(db.getAdresBijBestelling(ID));
                    bestellingsTable.addCell("€" + db.getTotaalbedragBestelling(ID) + "");

                    for (String item: bestellingsItems.keySet())
                    {
                        bestellingsTable.addCell(item + " (x" + bestellingsItems.get(item) + ")");
                        bestellingsTable.addCell("");
                        bestellingsTable.addCell("");
                        bestellingsTable.addCell("");
                    }
                    document.add(bestellingsTable);
                    for (int i=0;i<1;i++)
                    {
                        document.add(new Paragraph(Chunk.NEWLINE));
                    }
                }
            }
        }
    }
    
    document.close();
    } catch (Exception e) {
    e.printStackTrace();
   }

   }

public void verkopenVestigingPDF(String takeAway, int vestigingsID)
{
    try {
        //maken van document
        int menuID = db.getMenuIDTakeAway(takeAway);
        String vestigingsAdres = db.getAdresBijVestigingsID(vestigingsID);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE3));
        document.open();
        Image img3 = Image.getInstance("ons zalig logo.jpg");
        img3.scalePercent(80);
        img3.setAlignment(Image.ALIGN_MIDDLE);
        img3.setSpacingBefore(50f);
        String text = "Verkopen-Overzicht: " + takeAway + " \n\n " + vestigingsAdres;
        Phrase phrase1 = new Phrase("");
        phrase1.setFont(catFont);
        Paragraph paragraph1 = new Paragraph(phrase1);
        Phrase phrase = new Phrase(text,catFont);
        phrase.setFont(catFont);
        Paragraph paragraph = new Paragraph(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(100f);
        for (int i=0;i<10;i++)
        {
            document.add(new Paragraph(Chunk.NEWLINE));
        }
        document.add(img3);
        document.add(paragraph1);
        document.add(paragraph);
        document.newPage();
        //orderhistoriekTable//////////////////////////////////////////////////////////////////////////////
        PdfPTable orderTable = new PdfPTable(3);
        orderTable.setTotalWidth(510);
        orderTable.setWidths(new int[]{60,200,100});
        orderTable.setLockedWidth(true);
        PdfPCell orderCell = new PdfPCell(new Paragraph("Orderhistoriek",whiteFont));
        orderCell.setColspan(3);
        orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        orderCell.setBackgroundColor(BaseColor.DARK_GRAY);
        orderTable.addCell(orderCell);
        Phrase datum = new Phrase("Datum",ratFont);
        Phrase klantlogin = new Phrase("Klantlogin",ratFont);
        Phrase bedrag = new Phrase("Bedrag",ratFont);
        orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        orderTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        orderTable.addCell(datum);
        orderTable.addCell(klantlogin);
        orderTable.addCell(bedrag);
        orderTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        
        //opbouwen vullen tabel
        ArrayList<String> vestigingsIDs = db.getVestigingsIDSTakeAway(takeAway);
        ArrayList<Integer> bestellingsIDs = new ArrayList();
        //vullen van bestellingsidsArraList met alle bestellingsIDs vloeiend voort uit de verschillende vestiginsIDs aan elkaar gekoppeld in de GeplaatstBij-Table
        ArrayList<String> bestellingen = db.getBestellingsIDBijVestigingsID(vestigingsID);
        for (int x=0;x<bestellingen.size();x++)
        {
            bestellingsIDs.add(Integer.parseInt(bestellingen.get(x)));
        }
        
        Collections.sort(bestellingsIDs);
        for (Integer ID: bestellingsIDs)
            {
                
                ArrayList<Integer> vestigingenBestellingen = db.getVestigingsIDBijBestellingsID(ID);
                for (String vest : vestigingsIDs)
                {
                    if (vestigingenBestellingen.contains(Integer.parseInt(vest)))
                    {
                       orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                       orderTable.addCell(db.getDatumBijBestellingsID(ID) + "");
                       orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                       orderTable.addCell(db.getKlantLoginBijBestellingsID(ID)+ "");
                       orderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                       orderTable.addCell("€" + f.format(db.getBedragParieleBestelling(db.getMenuIDTakeAway(takeAway),ID)) + "" ); 
                    }
                }
            }
        
        document.add(new Paragraph(new Phrase("  1.Orderhistoriek: " + vestigingsAdres,catFont)));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(orderTable);
        document.add(new Paragraph(Chunk.NEWLINE));
        document.close();
        } catch (Exception e) {
            e.printStackTrace();
       }
       
    }

public void lopendeOrdersVestigingPDF(String takeAway, int vestigingsID) {
 try {
    Document document = new Document(PageSize.A4, 0, 0, 0, 0);
    PdfWriter.getInstance(document, new FileOutputStream(FILE4));
    document.open();
    Image img3 = Image.getInstance("ons zalig logo.jpg");
    img3.scalePercent(80);
    img3.setAlignment(Image.ALIGN_MIDDLE);
    img3.setSpacingBefore(50f);
    String vestigingsadres = db.getAdresBijVestigingsID(vestigingsID);
     System.out.println(vestigingsadres);
    String text = "Lopende Orders: " + takeAway + "\n\n" + vestigingsadres;
    Phrase phrase1 = new Phrase("");
    phrase1.setFont(catFont);
    Paragraph paragraph1 = new Paragraph(phrase1);
    Phrase phrase = new Phrase(text,catFont);
    phrase.setFont(catFont);
    Paragraph paragraph = new Paragraph(phrase);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    paragraph.setSpacingBefore(100f);
    for (int i=0;i<10;i++)
        {
            document.add(new Paragraph(Chunk.NEWLINE));
        }
    document.add(img3);
    document.add(paragraph1);
    document.add(paragraph);
    document.newPage();
    

    //bestellingstabel
    ArrayList<Integer> bestellingsIDs = db.getBestellingsIDsPending();
    int bestellingsCount = 1;
    document.add(new Paragraph(new Phrase("     1. Lopende Orders:",catFont)));
    for (int i=0;i<1;i++)
    {
        document.add(new Paragraph(Chunk.NEWLINE));
    }
    
    for (Integer ID: bestellingsIDs)
    {
        ArrayList<Integer> vestigingsIDsBES = db.getVestigingsIDBijBestellingsID(ID);
        for(Integer vestBES : vestigingsIDsBES)
        {
            if(vestBES == vestigingsID)
            {
                HashMap<String,HashMap<String,Integer>> gerechten = db.getMenuGerechtBijBestelling(ID);
                HashMap<String,HashMap<String,Integer>> bijgerechten = db.getMenuBijgerechtBijBestelling(ID);
                HashMap<String,HashMap<String,Integer>> snacks = db.getMenuSnacksBijBestelling(ID);
                HashMap<String,HashMap<String,Integer>> dranken = db.getMenuDrankenBijBestelling(ID);

                HashMap<String,Integer> bestellingsItems = new HashMap(); //items en hoeveelheden

                for(String x: gerechten.keySet())
                {
                    for (String y : gerechten.get(x).keySet())
                    {
                        bestellingsItems.put(y, gerechten.get(x).get(y));
                    }
                }

                for(String x: bijgerechten.keySet())
                {
                    for (String y : bijgerechten.get(x).keySet())
                    {
                        bestellingsItems.put(y, bijgerechten.get(x).get(y));
                    }
                }

                for(String x: snacks.keySet())
                {
                    for (String y : snacks.get(x).keySet())
                    {
                        bestellingsItems.put(y, snacks.get(x).get(y));
                    }
                }

                for(String x: dranken.keySet())
                {
                    for (String y : dranken.get(x).keySet())
                    {
                        bestellingsItems.put(y, dranken.get(x).get(y));
                    }
                }

                PdfPTable bestellingsTable = new PdfPTable(4);
                PdfPCell cell = new PdfPCell(new Paragraph("Bestelling " + bestellingsCount ,whiteFont));
                bestellingsCount++;
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                bestellingsTable.addCell(cell);
                bestellingsTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                bestellingsTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
                bestellingsTable.setTotalWidth(550);
                bestellingsTable.setWidths(new int[]{90,120,50,120});
                bestellingsTable.setLockedWidth(true);
                 Phrase klantlogin = new Phrase("KlantLogin",ratFont);
                Phrase adres = new Phrase("Leveringsadres",ratFont);
                Phrase totaalprijs = new Phrase("Totaalprijs",ratFont);
                Phrase bestelling = new Phrase("Bestelling",ratFont);
                bestellingsTable.addCell(klantlogin);
                bestellingsTable.addCell(adres);
                bestellingsTable.addCell(totaalprijs);
                bestellingsTable.addCell(bestelling);
                bestellingsTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                bestellingsTable.addCell(db.getKlantLoginBijBestellingsID(ID));
                bestellingsTable.addCell(db.getAdresBijBestelling(ID));
                bestellingsTable.addCell("€" + db.getTotaalbedragBestelling(ID) + "");

                for (String item: bestellingsItems.keySet())
                {
                    bestellingsTable.addCell(item + " (x" + bestellingsItems.get(item) + ")");
                    bestellingsTable.addCell("");
                    bestellingsTable.addCell("");
                    bestellingsTable.addCell("");
                }
                document.add(bestellingsTable);
                for (int i=0;i<1;i++)
                {
                    document.add(new Paragraph(Chunk.NEWLINE));
                }
            }
            
        }
    }
    
    document.close();
    } catch (Exception e) {
    e.printStackTrace();
   }

   }

public void lopendeUniekeActieKortingPDF(String takeAway)
{
    try 
    {
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        PdfWriter.getInstance(document, new FileOutputStream(FILE5));
        document.open();
        Image img3 = Image.getInstance("ons zalig logo.jpg");
        img3.scalePercent(80);
        img3.setAlignment(Image.ALIGN_MIDDLE);
        img3.setSpacingBefore(50f);
        String text = "Lopende unieke actie kortingscodes: " + takeAway ;
        Phrase phrase1 = new Phrase("");
        phrase1.setFont(catFont);
        Paragraph paragraph1 = new Paragraph(phrase1);
        Phrase phrase = new Phrase(text,catFont);
        phrase.setFont(catFont);
        Paragraph paragraph = new Paragraph(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingBefore(100f);
        for (int i=0;i<10;i++)
            {
                document.add(new Paragraph(Chunk.NEWLINE));
            }
        document.add(img3);
        document.add(paragraph1);
        document.add(paragraph);
        document.newPage();

        //maken van de lopende uniekeactiecodes-table
        PdfPTable uniekeActieKCTable = new PdfPTable(4);
        PdfPCell uniekeActieKCCell = new PdfPCell(new Paragraph("Lopende Unieke ActieCodes",whiteFont));
        uniekeActieKCCell.setColspan(4);
        uniekeActieKCCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniekeActieKCCell.setBackgroundColor(BaseColor.DARK_GRAY);
        uniekeActieKCTable.setTotalWidth(510);
        uniekeActieKCTable.setWidths(new int[]{100,70,170,170});
        uniekeActieKCTable.setLockedWidth(true);
        uniekeActieKCTable.addCell(uniekeActieKCCell);
        Phrase gerechtenB = new Phrase("Codecombinatie",ratFont);
        Phrase prijsB = new Phrase("Kortingsbedrag",ratFont);
        Phrase aantalB = new Phrase("periode",ratFont);
        Phrase omzetGerechtB = new Phrase("Vestiging",ratFont);
        uniekeActieKCTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        uniekeActieKCTable.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        uniekeActieKCTable.addCell(gerechtenB);
        uniekeActieKCTable.addCell(prijsB);
        uniekeActieKCTable.addCell(aantalB);
        uniekeActieKCTable.addCell(omzetGerechtB);
        uniekeActieKCTable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        ArrayList<String> codes = db.getLijstUniekActieKortingsCodesBijTakeAwayEnZijnVestigingen(takeAway);
        for (String code: codes)
            {
                uniekeActieKCTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                uniekeActieKCTable.addCell(code);
                uniekeActieKCTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                
                if (db.getGegevenKortingBijKortingsCode(code)>=1)
                {
                    String gegevenKorting = "€" + db.getGegevenKortingBijKortingsCode(code);
                    uniekeActieKCTable.addCell(gegevenKorting + "");
                }
                if (db.getGegevenKortingBijKortingsCode(code)<1)
                {
                    double korting = ((db.getGegevenKortingBijKortingsCode(code))*100);
                    String gegevenKorting = korting + " %";
                    uniekeActieKCTable.addCell(gegevenKorting + "");
                }
                
                uniekeActieKCTable.addCell(db.getPeriodeBijUniekeActieKortingscode(code));
                uniekeActieKCTable.addCell(db.getAdresBijVestigingsID(db.getVestigingBijUniekeActieKortingscode(code)));
            }
        uniekeActieKCTable.setSpacingBefore(10f);
        uniekeActieKCTable.setSpacingAfter(10f);

       for (int i=0;i<1;i++)
                {
                    document.add(new Paragraph(Chunk.NEWLINE));
                }
       document.add(new Paragraph(new Phrase("     1. Lopende unieke actie kortingscode:",catFont)));
        for (int i=0;i<1;i++)
                {
                    document.add(new Paragraph(Chunk.NEWLINE));
                }
        document.add(uniekeActieKCTable);
        document.close();
    }
    catch (Exception e) 
    {
        e.printStackTrace();
    }
} 

}


