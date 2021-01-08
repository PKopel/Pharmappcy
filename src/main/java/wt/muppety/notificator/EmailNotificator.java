package wt.muppety.notificator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;
import java.util.logging.Level;

public class EmailNotificator {

    public static void sendEmail(String recipient) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");    

        String myAccountEmail = "pharmappcy.muppety@gmail.com";
        String password = "projektTOwtorek1615";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);
        try {
            Transport.send(message);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }


    public static Message prepareMessage(Session session, String myAccountEmail, String recipient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("test pharmappcy notifications");
            message.setText("Hey");
            return message;
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;

    }



}