package hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "completed_test", schema = "online_course", catalog = "")
@IdClass(CompletedTestEntityPK.class)
public class CompletedTestEntity {
    private int user;
    private int test;
    private int correctAnswers;
    private Timestamp date;
    private UserEntity userByUser;
    private TestEntity testByTest;

    @Id
    @Column(name = "user")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Id
    @Column(name = "test")
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    @Basic
    @Column(name = "correct_answers")
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Basic
    @Column(name = "date")
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

        if (user != that.user) return false;
        if (test != that.test) return false;
        if (correctAnswers != that.correctAnswers) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + test;
        result = 31 * result + correctAnswers;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public UserEntity getUserByUser() {
        return userByUser;
    }

    public void setUserByUser(UserEntity userByUser) {
        this.userByUser = userByUser;
    }

    @ManyToOne
    @JoinColumn(name = "test", referencedColumnName = "id_test", nullable = false, insertable = false, updatable = false)
    public TestEntity getTestByTest() {
        return testByTest;
    }

    public void setTestByTest(TestEntity testByTest) {
        this.testByTest = testByTest;
    }
}
