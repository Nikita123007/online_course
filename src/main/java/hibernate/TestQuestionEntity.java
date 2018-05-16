package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_question", schema = "online_course", catalog = "")
public class TestQuestionEntity implements AbstractEntity {
    private int idTestQuestion;
    private int test;
    private String question;
    private Collection<TestAnswerEntity> testAnswersByIdTestQuestion;
    private TestEntity testByTest;

    @Id
    @Column(name = "id_test_question")
    public int getIdTestQuestion() {
        return idTestQuestion;
    }

    public void setIdTestQuestion(int idTestQuestion) {
        this.idTestQuestion = idTestQuestion;
    }

    @Basic
    @Column(name = "test")
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    @Basic
    @Column(name = "question")
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

        TestQuestionEntity that = (TestQuestionEntity) o;

        if (idTestQuestion != that.idTestQuestion) return false;
        if (test != that.test) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTestQuestion;
        result = 31 * result + test;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "testQuestionByTestQuestion")
    public Collection<TestAnswerEntity> getTestAnswersByIdTestQuestion() {
        return testAnswersByIdTestQuestion;
    }

    public void setTestAnswersByIdTestQuestion(Collection<TestAnswerEntity> testAnswersByIdTestQuestion) {
        this.testAnswersByIdTestQuestion = testAnswersByIdTestQuestion;
    }

    @ManyToOne
    @JoinColumn(name = "test", referencedColumnName = "id_test", nullable = false, insertable = false, updatable = false)
    public TestEntity getTestByTest() {
        return testByTest;
    }

    public void setTestByTest(TestEntity testByTest) {
        this.testByTest = testByTest;
    }

    @Override
    public boolean checkRights(UserEntity user, ActionType action) {
        if(action == ActionType.Read || action == ActionType.Create){
            return user != null && (getTestByTest().getCourseByCourse().isSubscribed(user.getIdUser()) || user.admin());
        }
        else{
            return user != null && (getTestByTest().getCourseByCourse().isAuthor(user.getIdUser()) || user.admin());
        }
    }
}
