/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Magnus
 */
public class SendEmail{

    private static String farmer = null;
    private static String buddy = null;
    private static String[] args;
    private static Sau sheep;
    private final static String username = "alarmsheeptracker";
    private final static String password = "gruppe12";
    
    
    public SendEmail(Sau sheep, String farmer, String buddy){
        this.sheep = sheep;
        this.farmer = farmer;
        this.buddy = buddy;
    }

    
    public static void main(String[] args) {
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
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
            message.setFrom(new InternetAddress("ALARMsheeptracker@gmail.com"));
            if(buddy != null){
                    message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("" + farmer + "," + buddy));
            }
            else{
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(farmer));
            }
            
            message.setSubject("ALARM - Noe er galt med sauene dine");
            message.setText("Hei, du har mottatt denne eposten fordi"
                    + "\nen av sauene dine har blitt angrepet!"
                    + "\n\n Navn: " + sheep.getName() 
                    + "\n\n Siste posisjon: " + "https://maps.google.com/maps?q="
                    + sheep.getLatitude() + "," + sheep.getLongitude());

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setFarmerAndBuddyEmailAndSendEmail(String farmer, String buddy){
        SendEmail.farmer = farmer;
        if(buddy != ""){
            SendEmail.buddy = buddy;
        }
        else{
            SendEmail.buddy = null;
        }
        
        main(args);
        
    }   
}