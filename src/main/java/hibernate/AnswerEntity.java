package hibernate;

import common.ActionType;

import javax.persistence.*;

@Entity
@Table(name = "test_answer", schema = "online_course", catalog = "")
public class AnswerEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_test_answer")
    private int id;

    @Basic
    @Column(name = "text")
    private String text;

    @Basic
    @Column(name = "is_correct")
    private byte isCorrect;

    @ManyToOne
    @JoinColumn(name = "test_question")
    private QuestionEntity question;


    public int getId() {
        return id;
    }
    public void setId(int idTestAnswer) {
        this.id = idTestAnswer;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public byte getIsCorrect() {
        return isCorrect;
    }
    public void setIsCorrect(byte isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (id != that.id) return false;
        if (isCorrect != that.isCorrect) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (int) isCorrect;
        return result;
    }

    public QuestionEntity getQuestion() {
        return question;
    }
    public void setQuestion(QuestionEntity question) {
        if(! question.equals(this.question)){
            this.question = question;
        }
    }

    @Override
    public boolean checkRights(UserEntity user, ActionType action) {
        if(action == ActionType.Read || action == ActionType.Create){
            return user != null && (getQuestion().getTest().getCourse().isSubscribed(user.getId()) || user.admin());
        }
        else{
            return user != null && (getQuestion().getTest().getCourse().isAuthor(user.getId()) || user.admin());
        }
    }
}
