package wt.muppety.notificator;

import javafx.collections.ObservableList;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotificator {

    private static final String APP_EMAIL = "pharmappcy.muppety@gmail.com";
    private static final String APP_EMAIL_PASSWORD = "projektTOwtorek1615";

    private static EmailNotificator _instance;

    private Session session;

    private EmailNotificator() {
    }

    public static EmailNotificator getInstance() {
        if (_instance == null) {
            _instance = new EmailNotificator();
        }
        return _instance;
    }

    public void sendEmailToAll(Product product) {
        EmployeeDao employeeDao = new EmployeeDao();
        ObservableList<Employee> employees = employeeDao.listAll();

        String topic = "We've added a new product";
        String text = "Hey, we have a new product in our stock: " + product;

        for (Employee employee : employees) {
            if (employee.getIsSubscribed() && employee.getEmail() != null) {
                sendEmail(employee.getEmail(), topic, text);
            }
        }

    }

    public void sendEmail(String recipient, String subject, String text) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_EMAIL_PASSWORD);
            }
        });

        Message message = prepareMessage(recipient, subject, text);
        try {
            if (message != null)
                Transport.send(message);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Message prepareMessage(String recipient, String subject, String text) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(APP_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

}