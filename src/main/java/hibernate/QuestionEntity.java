package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test_question", schema = "online_course", catalog = "")
public class QuestionEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_test_question")
    private int id;

    @Basic
    @Column(name = "question")
    private String question;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private Set<AnswerEntity> answers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "test")
    private TestEntity test;


    public int getId() {
        return id;
    }
    public void setId(int idTestQuestion) {
        this.id = idTestQuestion;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (id != that.id) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

    public Set<AnswerEntity> getAnswers() {
        return answers;
    }
    public void addAnswer(AnswerEntity answer){
        if(! this.answers.contains(answer)){
            this.answers.add(answer);
            answer.setQuestion(this);
        }
    }

    public TestEntity getTest() {
        return test;
    }
    public void setTest(TestEntity test) {
        if(! test.equals(this.test)){
            this.test = test;
            test.addQuestion(this);
        }
    }

    @Override
    public boolean checkRights(UserEntity user, ActionType action) {
        if(action == ActionType.Read || action == ActionType.Create){
            return user != null && (getTest().getCourse().isSubscribed(user.getId()) || user.admin());
        }
        else{
            return user != null && (getTest().getCourse().isAuthor(user.getId()) || user.admin());
        }
    }
}
