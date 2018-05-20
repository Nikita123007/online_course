package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lection", schema = "online_course", catalog = "")
public class LectionEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_lection")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "course")
    private CourseEntity course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lection")
    private Set<ReadedLectionEntity> readedLections = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lection")
    private Set<VideoLinkEntity> videoLinks = new HashSet<>();

    public int getId() {
        return id;
    }
    public void setId(int idLection) {
        this.id = idLection;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

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

        if (id != that.id) return false;
        if (course != that.course) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    public CourseEntity getCourse() {
        return course;
    }
    public void setCourse(CourseEntity course) {
        if(! course.equals(this.course)){
            this.course = course;
            course.addLection(this);
        }
    }

    public Set<ReadedLectionEntity> getReadedLections() {
        return readedLections;
    }
    public void addReadedLection(ReadedLectionEntity readedLection) {
        if(! this.readedLections.contains(readedLection)){
            this.readedLections.add(readedLection);
            readedLection.setLection(this);
        }
    }

    public Set<VideoLinkEntity> getVideoLinks() {
        return videoLinks;
    }
    public void addVideoLink(VideoLinkEntity videoLink) {
        if(! this.videoLinks.contains(videoLink)) {
            this.videoLinks.add(videoLink);
            videoLink.setLection(this);
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
}
