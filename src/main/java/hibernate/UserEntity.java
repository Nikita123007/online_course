package hibernate;

import common.ActionType;
import constants.Roles;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "online_course", catalog = "")
public class UserEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password_hash")
    private String passwordHash;

    @Basic
    @Column(name = "auth_token")
    private String authToken;

    @ManyToOne
    @JoinColumn(name = "role")
    private RoleEntity role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<CommentEntity> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<CompletedTestEntity> completedTests = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<CourseEntity> createdCourses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<DiplomaEntity> diplomas = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ReadedLectionEntity> readedLections = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<SubscriptionEntity> subscriptions = new HashSet<>();

    public int getId() {
        return id;
    }
    public void setId(int idUser) {
        this.id = idUser;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        return result;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }
    public void addComment(CommentEntity comment) {
        if(! this.comments.contains(comment)){
            this.comments.add(comment);
            comment.setAuthor(this);
        }
    }

    public Set<CompletedTestEntity> getCompletedTests() {
        return completedTests;
    }
    public void addCompletedTest(CompletedTestEntity completedTest) {
        if(! this.completedTests.contains(completedTest)){
            this.completedTests.add(completedTest);
            completedTest.setUser(this);
        }
    }

    public Set<CourseEntity> getCreatedCourses() {
        return createdCourses;
    }
    public void addCreatedCourse(CourseEntity createdCourse) {
        if(! this.createdCourses.contains(createdCourse)){
            this.createdCourses.add(createdCourse);
            createdCourse.setAuthor(this);
        }
    }

    public Set<DiplomaEntity> getDiplomas() {
        return diplomas;
    }
    public void addDiploma(DiplomaEntity diploma) {
        if(! this.diplomas.contains(diploma)){
            this.diplomas.add(diploma);
            diploma.setUser(this);
        }
    }

    public Set<ReadedLectionEntity> getReadedLections() {
        return readedLections;
    }
    public void addReadedLection(ReadedLectionEntity readedLection) {
        if(! this.readedLections.contains(readedLection)){
            this.readedLections.add(readedLection);
            readedLection.setUser(this);
        }
    }

    public Set<SubscriptionEntity> getSubscriptions() {
        return subscriptions;
    }
    public void addSubscription(SubscriptionEntity subscription) {
        if(! this.subscriptions.contains(subscription)){
            this.subscriptions.add(subscription);
            subscription.setUser(this);
        }
    }

    public RoleEntity getRole() {
        return role;
    }
    public void setRole(RoleEntity role) {
        if(! role.equals(this.role)){
            this.role = role;
            role.addUser(this);
        }
    }

    public boolean admin(){
        return getRole().getId() == Roles.Role.Admin;
    }

    public boolean checkRights(UserEntity user, ActionType action){
        return action == ActionType.Read && user != null;
    }
}
