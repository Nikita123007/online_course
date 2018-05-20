package Email;

import dao.DAOFactory;
import hibernate.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class BackgroundSender extends TimerTask {
    public void run() {
        EmailEntity entity = new EmailEntity();

        List<UserEntity> recipients = new ArrayList<UserEntity>();
        recipients.add(DAOFactory.getInstance().getUserDAO().get(20));

        entity.setRecipients(recipients);
        entity.setSubject("Ежедневная рассылка");
        entity.setBody("С Днём роджения! Ура!");

        EmailSenderService service = new EmailSenderService();
        service.sendEmail(entity);
    }
}