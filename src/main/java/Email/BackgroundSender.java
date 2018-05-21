package Email;

import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;

import java.util.*;

public class BackgroundSender extends TimerTask {

    private String makeBody(UserEntity user){
        StringBuilder result = new StringBuilder("Добрый день, уважаемый автор!");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        Date yesteday = cal.getTime();
        for (CourseEntity course : user.getCreatedCourses()){
            int subscriptionCount = 0;
            for (SubscriptionEntity subscription : course.getSubscriptions()){
                if(yesteday.before(subscription.getDate())){
                    subscriptionCount++;
                }
            }
            result.append("\n");
            result.append("На вас курс '");
            result.append(course.getName());
            result.append("' за последний день ");
            switch (subscriptionCount % 10){
                case 1:
                    result.append("подписался ");
                    result.append(String.valueOf(subscriptionCount));
                    result.append(" человек");
                    break;

                case 2:
                case 3:
                case 4:
                    result.append("подписалось ");
                    result.append(String.valueOf(subscriptionCount));
                    result.append(" человека");
                    break;

                default:
                    result.append("подписалось ");
                    result.append(String.valueOf(subscriptionCount));
                    result.append(" человек");
            }
        }

        return result.toString();
    }

    public void run() {
        for(UserEntity user : DAOFactory.getInstance().getUserDAO().getAll()){
            if(user.getCreatedCourses().isEmpty())
                continue;

            EmailEntity entity = new EmailEntity();
            List<UserEntity> recipients = new ArrayList<UserEntity>();
            recipients.add(user);

            entity.setRecipients(recipients);
            entity.setSubject("Ежедневная рассылка");
            entity.setBody(makeBody(user));

            EmailSenderService service = new EmailSenderService();
            service.sendEmail(entity);
        }
    }
}