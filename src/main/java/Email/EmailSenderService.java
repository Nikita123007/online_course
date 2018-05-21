package Email;

import hibernate.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSenderService {
    final String fromEmail = "online.sourse.bsuir@gmail.com";
    final String password = "ePk6rcfDo4";
    final String host = "smtp.gmail.com";
    final String port = "465";

    Session session;

    public EmailSenderService() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        session = Session.getInstance(props, auth);
    }


    String processTemplate(String template, UserEntity sender, UserEntity recipient){
        String result = template;
        if(sender != null){
            result = result.replace("<sender>", sender.getName());
        }

        result = result.replace("<recipient>", recipient.getName());
        result = result.replace("<recipientRole>", recipient.getRole().getName());

        return result;
    }

    public void sendEmail(EmailEntity emailEntity) {
        List<String> mails = new ArrayList<>();
        for(UserEntity recipient : emailEntity.getRecipients()){
            String body = processTemplate(emailEntity.getBody(), emailEntity.getSender(), recipient);
            sendEmail(recipient.getEmail(), emailEntity.getSubject(), body);
        }
    }

    private void sendEmail(String recipient, String subject, String body) {
        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Online course"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            Transport.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}