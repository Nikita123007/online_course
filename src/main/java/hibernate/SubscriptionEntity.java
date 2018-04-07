package hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subscription", schema = "online_course", catalog = "")
@IdClass(SubscriptionEntityPK.class)
public class SubscriptionEntity {
    private int user;
    private int course;
    private Timestamp date;
    private UserEntity userByUser;
    private CourseEntity courseByCourse;

    @Id
    @Column(name = "user")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Id
    @Column(name = "course")
    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
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

        SubscriptionEntity that = (SubscriptionEntity) o;

        if (user != that.user) return false;
        if (course != that.course) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + course;
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
    @JoinColumn(name = "course", referencedColumnName = "id_course", nullable = false, insertable = false, updatable = false)
    public CourseEntity getCourseByCourse() {
        return courseByCourse;
    }

    public void setCourseByCourse(CourseEntity courseByCourse) {
        this.courseByCourse = courseByCourse;
    }
}
