package hibernate;

import common.ActionType;
import dao.DAOFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test", schema = "online_course", catalog = "")
public class TestEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_test")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private Set<CompletedTestEntity> completedTests = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "course")
    private CourseEntity course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    private Set<QuestionEntity> questions = new HashSet<>();


    public int getId() {
        return id;
    }
    public void setId(int idTest) {
        this.id = idTest;
    }
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

        if (id != that.id) return false;
        if (course != that.course) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    public Set<CompletedTestEntity> getCompletedTests() {
        return completedTests;
    }
    public void addCompletedTests(CompletedTestEntity completedTest){
        if(! this.completedTests.contains(completedTest)){
            completedTests.add(completedTest);
            completedTest.setTest(this);
        }
    }

    public CourseEntity getCourse() {
        return course;
    }
    public void setCourse(CourseEntity course) {
        if(! course.equals(this.course)){
            this.course = course;
            course.addTest(this);
        }
    }

    public Set<QuestionEntity> getQuestions() {
        return questions;
    }
    public void addQuestion(QuestionEntity question){
        if(! this.questions.contains(question)){
            questions.add(question);
            question.setTest(this);
        }
    }

    public boolean checkRights(UserEntity user, ActionType action){
        if(action == ActionType.Read || action == ActionType.Create){
            return user != null && (getCourse().isSubscribed(user.getId()) || user.admin());
        }
        else{
            return user != null && (getCourse().isAuthor(user.getId()) || user.admin());
        }
    }

    public boolean isTestCompleted(int userId){
        return DAOFactory.getInstance().getCompletedTestDAO().getCompletedTest(userId, getId()) != null;
    }
}
