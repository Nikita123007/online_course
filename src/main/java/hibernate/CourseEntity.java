package hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "course", schema = "online_course", catalog = "")
public class CourseEntity {
    private int idCourse;
    private int author;
    private String name;
    private BigDecimal price;
    private String level;
    private Integer duration;
    private int category;
    private Collection<CommentEntity> commentsByIdCourse;
    private UserEntity userByAuthor;
    private CategoryEntity categoryByCategory;
    private Collection<DiplomaEntity> diplomasByIdCourse;
    private Collection<LectionEntity> lectionsByIdCourse;
    private Collection<SubscriptionEntity> subscriptionsByIdCourse;
    private Collection<TestEntity> testsByIdCourse;

    @Id
    @Column(name = "id_course")
    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "category")
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (idCourse != that.idCourse) return false;
        if (author != that.author) return false;
        if (category != that.category) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCourse;
        result = 31 * result + author;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + category;
        return result;
    }

    @OneToMany(mappedBy = "courseByCourse")
    public Collection<CommentEntity> getCommentsByIdCourse() {
        return commentsByIdCourse;
    }

    public void setCommentsByIdCourse(Collection<CommentEntity> commentsByIdCourse) {
        this.commentsByIdCourse = commentsByIdCourse;
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
    @JoinColumn(name = "category", referencedColumnName = "id_category", nullable = false, insertable = false, updatable = false)
    public CategoryEntity getCategoryByCategory() {
        return categoryByCategory;
    }

    public void setCategoryByCategory(CategoryEntity categoryByCategory) {
        this.categoryByCategory = categoryByCategory;
    }

    @OneToMany(mappedBy = "courseByCourse")
    public Collection<DiplomaEntity> getDiplomasByIdCourse() {
        return diplomasByIdCourse;
    }

    public void setDiplomasByIdCourse(Collection<DiplomaEntity> diplomasByIdCourse) {
        this.diplomasByIdCourse = diplomasByIdCourse;
    }

    @OneToMany(mappedBy = "courseByCourse")
    public Collection<LectionEntity> getLectionsByIdCourse() {
        return lectionsByIdCourse;
    }

    public void setLectionsByIdCourse(Collection<LectionEntity> lectionsByIdCourse) {
        this.lectionsByIdCourse = lectionsByIdCourse;
    }

    @OneToMany(mappedBy = "courseByCourse")
    public Collection<SubscriptionEntity> getSubscriptionsByIdCourse() {
        return subscriptionsByIdCourse;
    }

    public void setSubscriptionsByIdCourse(Collection<SubscriptionEntity> subscriptionsByIdCourse) {
        this.subscriptionsByIdCourse = subscriptionsByIdCourse;
    }

    @OneToMany(mappedBy = "courseByCourse")
    public Collection<TestEntity> getTestsByIdCourse() {
        return testsByIdCourse;
    }

    public void setTestsByIdCourse(Collection<TestEntity> testsByIdCourse) {
        this.testsByIdCourse = testsByIdCourse;
    }
}
