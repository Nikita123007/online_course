package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "course", schema = "online_course", catalog = "")
public class CourseEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_course")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "price")
    private BigDecimal price;

    @Basic
    @Column(name = "level")
    private String level;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "duration")
    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CommentEntity> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<DiplomaEntity> diplomas = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<LectionEntity> lections = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL, mappedBy = "course")
    private Set<SubscriptionEntity> subscriptions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<TestEntity> tests = new HashSet<>();

    public int getId() {
        return id;
    }
    public void setId(int idCourse) {
        this.id = idCourse;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
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
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }
    public void addComment(CommentEntity comment) {
        if(! comments.contains(comment)){
            this.comments.add(comment);
            comment.setCourse(this);
        }
    }

    public UserEntity getAuthor() {
        return author;
    }
    public void setAuthor(UserEntity author) {
        if(! author.equals(this.author)){
            this.author = author;
            author.addCreatedCourse(this);
        }
    }

    public CategoryEntity getCategory() {
        return category;
    }
    public void setCategory(CategoryEntity category) {
        if(! category.equals(this.category)){
            this.category = category;
            category.addCourse(this);
        }
    }

    public Set<DiplomaEntity> getDiplomas() {
        return diplomas;
    }
    public void addDiploma(DiplomaEntity diploma) {
        if(! this.diplomas.contains(diploma)){
            this.diplomas.add(diploma);
            diploma.setCourse(this);
        }
    }

    public Set<LectionEntity> getLections() {
        return lections;
    }
    public void addLection(LectionEntity lection) {
        if(! this.lections.contains(lection)){
            this.lections.add(lection);
            lection.setCourse(this);
        }
    }

    public Set<SubscriptionEntity> getSubscriptions() {
        return subscriptions;
    }
    public void addSubscription(SubscriptionEntity subscription) {
        if(! this.subscriptions.contains(subscription)){
            this.subscriptions.add(subscription);
            subscription.setCourse(this);
        }
    }

    public Set<TestEntity> getTests() {
        return tests;
    }
    public void addTest(TestEntity test) {
        if(! this.tests.contains(test)){
            this.tests.add(test);
            test.setCourse(this);
        }
    }

    public boolean isSubscribed(int userId){
        for(SubscriptionEntity subscription : subscriptions){
            if(subscription.getUser().getId() == userId)
                return true;
        }
        return false;
    }

    public boolean isAuthor(int userId){
        return getAuthor().getId() == userId;
    }

    public boolean checkRights(UserEntity user, ActionType action){
        if(action == ActionType.Read){
            return user != null && (isSubscribed(user.getId()) || user.admin());
        }
        else{
            return user != null && (isAuthor(user.getId()) || user.admin());
        }
    }
}
