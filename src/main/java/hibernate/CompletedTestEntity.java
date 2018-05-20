package hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "completed_test", schema = "online_course", catalog = "")
@IdClass(CompletedTestEntityPK.class)
public class CompletedTestEntity {
    @Id
    @Column(name = "user")
    private int user;

    @Id
    @Column(name = "test")
    private int test;

    @Basic
    @Column(name = "correct_answers")
    private int correctAnswers;

    @Basic
    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity userObject;

    @Id
    @ManyToOne
    @JoinColumn(name = "test", referencedColumnName = "id_test", insertable = false, updatable = false)
    private TestEntity testObject;

    public int getCorrectAnswers() {
        return correctAnswers;
    }
    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompletedTestEntity that = (CompletedTestEntity) o;

        if (correctAnswers != that.correctAnswers) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = correctAnswers;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public UserEntity getUser() {
        return userObject;
    }
    public void setUser(UserEntity userObject) {
        if(! userObject.equals(this.userObject)){
            this.user = userObject.getId();
            this.userObject = userObject;
            userObject.addCompletedTest(this);
        }
    }

    public TestEntity getTest() {
        return testObject;
    }
    public void setTest(TestEntity testObject) {
        if(! testObject.equals(this.testObject)){
            this.test = testObject.getId();
            this.testObject = testObject;
            testObject.addCompletedTests(this);
        }
    }
}
