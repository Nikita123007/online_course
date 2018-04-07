package hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SubscriptionEntityPK implements Serializable {
    private int user;
    private int course;

    @Column(name = "user")
    @Id
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Column(name = "course")
    @Id
    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionEntityPK that = (SubscriptionEntityPK) o;

        if (user != that.user) return false;
        if (course != that.course) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + course;
        return result;
    }
}
