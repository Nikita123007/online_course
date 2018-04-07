package hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "diploma", schema = "online_course", catalog = "")
public class DiplomaEntity {
    private int idDiploma;
    private int user;
    private int course;
    private int mark;
    private Timestamp date;
    private UserEntity userByUser;
    private CourseEntity courseByCourse;

    @Id
    @Column(name = "id_diploma")
    public int getIdDiploma() {
        return idDiploma;
    }

    public void setIdDiploma(int idDiploma) {
        this.idDiploma = idDiploma;
    }

    @Basic
    @Column(name = "user")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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
    @Column(name = "mark")
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
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

        DiplomaEntity that = (DiplomaEntity) o;

        if (idDiploma != that.idDiploma) return false;
        if (user != that.user) return false;
        if (course != that.course) return false;
        if (mark != that.mark) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDiploma;
        result = 31 * result + user;
        result = 31 * result + course;
        result = 31 * result + mark;
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
