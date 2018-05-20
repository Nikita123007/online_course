package hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment", schema = "online_course", catalog = "")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int id;

    @Basic
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "course")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "reply_comment")
    private CommentEntity replyTo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replyTo")
    private Set<CommentEntity> replies = new HashSet<>();

    public int getId() {
        return id;
    }
    public void setId(int idComment) {
        this.id = idComment;
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

        CommentEntity that = (CommentEntity) o;

        if (id != that.id) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    public UserEntity getAuthor() {
        return author;
    }
    public void setAuthor(UserEntity author) {
        if(! author.equals(this.author)){
            this.author = author;
            author.addComment(this);
        }
    }

    public CourseEntity getCourse() {
        return course;
    }
    public void setCourse(CourseEntity course) {
        if(! course.equals(this.course)){
            this.course = course;
            course.addComment(this);
        }
    }

    public CommentEntity getReplyTo() {
        return replyTo;
    }
    public void setReplyTo(CommentEntity replyTo) {
        this.replyTo = replyTo;
    }

    public Set<CommentEntity> getReplies() {
        return replies;
    }
}
