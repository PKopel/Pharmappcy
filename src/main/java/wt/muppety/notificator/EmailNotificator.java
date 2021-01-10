package wt.muppety.notificator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javafx.collections.ObservableList;

import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;

public class EmailNotificator {

    private static EmailNotificator _instance;

    private EmailNotificator() throws NoSuchAlgorithmException {

    }

    public static EmailNotificator getInstance() {
        try {
            if (_instance == null) {
                _instance = new EmailNotificator();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return _instance;
    }

    public void sendEmailToAll(){
        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();

        for (Employee employee : employees) {
            if (employee.getIsSubscribed() && employee.getEmail() != null) {
                sendEmail(employee.getEmail());
            }
        }

    }

    public void sendEmail(String recipient) {
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