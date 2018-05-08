package hibernate;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "lection", schema = "online_course", catalog = "")
public class LectionEntity {
    private int idLection;
    private int course;
    private String name;
    private String text;
    private CourseEntity courseByCourse;
    private Collection<ReadedLectionEntity> readedLectionsByIdLection;
    private Collection<VideoLinkEntity> videoLinksByIdLection;

    @Id
    @Column(name = "id_lection")
    public int getIdLection() {
        return idLection;
    }

    public void setIdLection(int idLection) {
        this.idLection = idLection;
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

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectionEntity that = (LectionEntity) o;

        if (idLection != that.idLection) return false;
        if (course != that.course) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLection;
        result = 31 * result + course;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id_course", nullable = false, insertable = false, updatable = false)
    public CourseEntity getCourseByCourse() {
        return courseByCourse;
    }

    public void setCourseByCourse(CourseEntity courseByCourse) {
        this.courseByCourse = courseByCourse;
    }

    @OneToMany(mappedBy = "lectionByLection")
    public Collection<ReadedLectionEntity> getReadedLectionsByIdLection() {
        return readedLectionsByIdLection;
    }

    public void setReadedLectionsByIdLection(Collection<ReadedLectionEntity> readedLectionsByIdLection) {
        this.readedLectionsByIdLection = readedLectionsByIdLection;
    }

    @OneToMany(mappedBy = "lectionByLection")
    public Collection<VideoLinkEntity> getVideoLinksByIdLection() {
        return videoLinksByIdLection;
    }

    public void setVideoLinksByIdLection(Collection<VideoLinkEntity> videoLinksByIdLection) {
        this.videoLinksByIdLection = videoLinksByIdLection;
    }
}
