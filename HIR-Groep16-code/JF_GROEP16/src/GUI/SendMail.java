package GUI;

import DATABASE_EN_ENTITEITEN.*;
 import java.io.UnsupportedEncodingException;
 import java.util.Properties;
 import javax.mail.Message;
 import javax.mail.MessagingException;
 import javax.mail.PasswordAuthentication;
 import javax.mail.Session;
 import javax.mail.Transport;
 import javax.mail.internet.AddressException;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeMessage;

public class SendMail {

public SendMail(){}

public void stuurKlantRegistratieMail(String ontvanger, String voornaamKlant,String codecombinatie) {

    final String username = "justfeed.info@gmail.com";
    final String password = "beleids1234";

    Properties props = new Properties();
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
     new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication(username, password);
    }
    });

    try {

    Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("justfeed.info@gmail.com"));
     message.setRecipients(Message.RecipientType.TO,
     InternetAddress.parse(ontvanger));
     message.setSubject("Registratie JUST-FEED");
     message.setText("Beste " + voornaamKlant 
     + "\n\nBedankt voor de registratie bij Just-feed.\n\n"
     + "We hopen u van dienst te kunnen zijn met ons systeem!\n\n" 
     + "Om u welkom te heten geven we u graag een registratiekorting ter waarde van €2.\n\n" 
     + "Uw persoonlijke kortingscode: " + codecombinatie + "\n" 
     + "Voeg deze bij uw bestelling en geniet van de korting!" 
     + "\n\nSmakelijke groeten \nHet JUST-FEED team");

    Transport.send(message);

    System.out.println("Email naar " + ontvanger + " verzonden!");

    } 
    catch (MessagingException e) {
 throw new RuntimeException(e);
 }
 }

public void stuurTakeAwayRegistratieMail(String ontvanger, String naamTakeAway) {

    final String username = "justfeed.info@gmail.com";
    final String password = "beleids1234";

    Properties props = new Properties();
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
     new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication(username, password);
    }
    });

    try {

    Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("justfeed.info@gmail.com"));
     message.setRecipients(Message.RecipientType.TO,
     InternetAddress.parse(ontvanger));
     message.setSubject("JUST-FEED: Aanvraag registratie TakeAway");
     message.setText("Beste " + naamTakeAway 
     + "\n\nBedankt voor uw registratieaanvraag voor een takeaway bij Just-feed.\n\n"
     + "We valideren nu uw aanvraag en sturen een confirmatiemail van zodra uw aanvraag geaccepteerd is.\n\n Hierna kan u inloggen in het systeem.\n\n" +
     "Met smakelijke groeten \n Het JUST-FEED team");

    Transport.send(message);

    System.out.println("Email naar " + ontvanger + " verzonden!");

    } 
    catch (MessagingException e) {
 throw new RuntimeException(e);
 }
 }

public void stuurAanvaardingTakeAwayMail(String ontvanger, String naamTakeAway) {

    final String username = "justfeed.info@gmail.com";
    final String password = "beleids1234";

    Properties props = new Properties();
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
     new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication(username, password);
    }
    });

    try {

    Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("justfeed.info@gmail.com"));
     message.setRecipients(Message.RecipientType.TO,
     InternetAddress.parse(ontvanger));
     message.setSubject("JUST-FEED: Aanvaarding TakeAway");
     message.setText("Beste " + naamTakeAway + ","
     + "\n\nUw aanvraag tot registratie is goedgekeurd.\n\n"
     + "U kan vanaf nu inloggen in ons systeem.\nBij vragen helpen we u graag en kan u ons steeds bereiken op dit email-adres.\n\n" +
     "Met Vriendelijke groetjes \n Het JUST-FEED team\n\nVerzonden vanuit Java");

    Transport.send(message);

    System.out.println("Email naar " + ontvanger + " verzonden!");

    } 
    catch (MessagingException e) {
 throw new RuntimeException(e);
 }
 }

public void stuurWachtwoordVergetenMail(String ontvanger, String klant, String gebruikersnaam,String wachtwoord) {

    final String username = "justfeed.info@gmail.com";
    final String password = "beleids1234";

    Properties props = new Properties();
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
     new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication(username, password);
    }
    });

    try {

    Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("justfeed.info@gmail.com"));
     message.setRecipients(Message.RecipientType.TO,
     InternetAddress.parse(ontvanger));
     message.setSubject("JUST-FEED: Wachtwoord vergeten");
     message.setText("Beste " + klant + ","
     + "\n\nHierbij sturen we u graag uw wachtwoord op.\n\nWachtwoord: " + wachtwoord + "\nGebruikersnaam: " + gebruikersnaam + "\n\n"
     + "Indien u deze aanvraag niet heeft ingedien, gelieve deze mail te negeren" +
     "Met Vriendelijke groetjes \n Het JUST-FEED team\n\nVerzonden vanuit Java");

    Transport.send(message);

    System.out.println("Email naar " + ontvanger + " verzonden!");

    } 
    catch (MessagingException e) {
 throw new RuntimeException(e);
 }
 }
 }
