package Email;


import hibernate.UserEntity;

import java.util.List;

public class EmailEntity{
    private String subject;
    private String body;
    private List<UserEntity> recipients;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<UserEntity> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<UserEntity> recipients) {
        this.recipients = recipients;
    }
}
