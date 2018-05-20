package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "subscription", schema = "online_course", catalog = "")
@IdClass(SubscriptionEntityPK.class)
public class SubscriptionEntity implements AbstractEntity {

    @Id
    @Column(name = "user")
    private int user;

    @Id
    @Column(name = "course")
    private int course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionEntity that = (SubscriptionEntity) o;
        return user == that.user &&
                course == that.course &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, course, date);
    }

    @Basic

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity userObject;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id_course", insertable = false, updatable = false)
    private CourseEntity courseObject;

    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public UserEntity getUser() {
        return userObject;
    }
    public void setUser(UserEntity userObject) {
        if(! userObject.equals(this.userObject)){
            this.user = userObject.getId();
            this.userObject = userObject;
            userObject.addSubscription(this);
        }
    }

    public CourseEntity getCourse() {
        return courseObject;
    }
    public void setCourse(CourseEntity courseObject) {
        if(! courseObject.equals(this.courseObject)){
            this.course = courseObject.getId();
            this.courseObject = courseObject;
            courseObject.addSubscription(this);
        }
    }

    public boolean checkRights(UserEntity user, ActionType action){
        return false;
    }
}
