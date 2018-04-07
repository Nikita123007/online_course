package hibernate;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "comment", schema = "online_course", catalog = "")
public class CommentEntity {
    private int idComment;
    private int author;
    private int course;
    private String text;
    private Integer replyComment;
    private UserEntity userByAuthor;
    private CourseEntity courseByCourse;
    private CommentEntity commentByReplyComment;
    private Collection<CommentEntity> commentsByIdComment;

    @Id
    @Column(name = "id_comment")
    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    @Basic
    @Column(name = "author")
    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
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
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "reply_comment")
    public Integer getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(Integer replyComment) {
        this.replyComment = replyComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (idComment != that.idComment) return false;
        if (author != that.author) return false;
        if (course != that.course) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (replyComment != null ? !replyComment.equals(that.replyComment) : that.replyComment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idComment;
        result = 31 * result + author;
        result = 31 * result + course;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (replyComment != null ? replyComment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public UserEntity getUserByAuthor() {
        return userByAuthor;
    }

    public void setUserByAuthor(UserEntity userByAuthor) {
        this.userByAuthor = userByAuthor;
    }

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id_course", nullable = false, insertable = false, updatable = false)
    public CourseEntity getCourseByCourse() {
        return courseByCourse;
    }

    public void setCourseByCourse(CourseEntity courseByCourse) {
        this.courseByCourse = courseByCourse;
    }

    @ManyToOne
    @JoinColumn(name = "reply_comment", referencedColumnName = "id_comment", insertable = false, updatable = false)
    public CommentEntity getCommentByReplyComment() {
        return commentByReplyComment;
    }

    public void setCommentByReplyComment(CommentEntity commentByReplyComment) {
        this.commentByReplyComment = commentByReplyComment;
    }

    @OneToMany(mappedBy = "commentByReplyComment")
    public Collection<CommentEntity> getCommentsByIdComment() {
        return commentsByIdComment;
    }

    public void setCommentsByIdComment(Collection<CommentEntity> commentsByIdComment) {
        this.commentsByIdComment = commentsByIdComment;
    }
}
