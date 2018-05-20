package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "diploma", schema = "online_course", catalog = "")
public class DiplomaEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_diploma")
    private int id;

    @Basic
    @Column(name = "mark")
    private int mark;

    @Basic
    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "course")
    private CourseEntity course;

    public int getId() {
        return id;
    }
    public void setId(int idDiploma) {
        this.id = idDiploma;
    }

    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
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

        DiplomaEntity that = (DiplomaEntity) o;

        if (id != that.id) return false;
        if (mark != that.mark) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mark;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        if(! user.equals(this.user)){
            this.user = user;
            user.addDiploma(this);
        }
    }

    public CourseEntity getCourse() {
        return course;
    }
    public void setCourse(CourseEntity course) {
        if(! course.equals(this.course)){
            this.course = course;
            course.addDiploma(this);
        }
    }

    public boolean checkRights(UserEntity user, ActionType action){
        return action == ActionType.Read && user != null;
    }
}
