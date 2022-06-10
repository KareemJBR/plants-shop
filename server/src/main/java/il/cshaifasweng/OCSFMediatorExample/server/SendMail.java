package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    private final String target_mail;
    private final String message_header;
    private final String message_body;

    private static final String from_email = "";            // TODO: set the right email address for the software email
    private static final String from_email_password = "";   // TODO: set the password for the software email

    public SendMail(String target_mail, String message_header, String message_body) {
        this.target_mail = target_mail;
        this.message_header = message_header;
        this.message_body = message_body;
    }

    public void sendMessage() {
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from_email, from_email_password);
            }
        });

        // used to debug SMTP issues
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from_email));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(target_mail));

            // Set Subject: header field
            message.setSubject(message_header);

            // Now set the actual message
            message.setText(message_body);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
