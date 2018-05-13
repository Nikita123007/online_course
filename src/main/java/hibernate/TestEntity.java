package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test", schema = "online_course", catalog = "")
public class TestEntity implements AbstractEntity {
    private int idTest;
    private int course;
    private String name;
    private Collection<CompletedTestEntity> completedTestsByIdTest;
    private CourseEntity courseByCourse;
    private Collection<TestQuestionEntity> testQuestionsByIdTest;

    @Id
    @Column(name = "id_test")
    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @Basic
    @Column(name = "course")
    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (idTest != that.idTest) return false;
        if (course != that.course) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTest;
        result = 31 * result + course;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "testByTest")
    public Collection<CompletedTestEntity> getCompletedTestsByIdTest() {
        return completedTestsByIdTest;
    }

    public void setCompletedTestsByIdTest(Collection<CompletedTestEntity> completedTestsByIdTest) {
        this.completedTestsByIdTest = completedTestsByIdTest;
    }

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id_course", nullable = false, insertable = false, updatable = false)
    public CourseEntity getCourseByCourse() {
        return courseByCourse;
    }

    public void setCourseByCourse(CourseEntity courseByCourse) {
        this.courseByCourse = courseByCourse;
    }

    @OneToMany(mappedBy = "testByTest")
    public Collection<TestQuestionEntity> getTestQuestionsByIdTest() {
        return testQuestionsByIdTest;
    }

    public void setTestQuestionsByIdTest(Collection<TestQuestionEntity> testQuestionsByIdTest) {
        this.testQuestionsByIdTest = testQuestionsByIdTest;
    }

    public boolean checkRights(UserEntity user, ActionType action){
        if(action == ActionType.Read || action == ActionType.Create){
            return user != null && (getCourseByCourse().isSubscribed(user.getIdUser()) || user.admin());
        }
        else{
            return user != null && (getCourseByCourse().isAuthor(user.getIdUser()) || user.admin());
        }
    }

}
