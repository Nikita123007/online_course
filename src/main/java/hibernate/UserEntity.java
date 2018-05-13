package hibernate;

import common.ActionType;
import constants.Roles;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "online_course", catalog = "")
public class UserEntity implements AbstractEntity {
    private int idUser;
    private String name;
    private String email;
    private String passwordHash;
    private String authToken;
    private int role;
    private RoleEntity roleByRole;
    private Collection<CommentEntity> commentsByIdUser;
    private Collection<CompletedTestEntity> completedTestsByIdUser;
    private Collection<CourseEntity> coursesByIdUser;
    private Collection<DiplomaEntity> diplomasByIdUser;
    private Collection<ReadedLectionEntity> readedLectionsByIdUser;
    private Collection<SubscriptionEntity> subscriptionsByIdUser;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password_hash")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "auth_token")
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Basic
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByAuthor")
    public Collection<CommentEntity> getCommentsByIdUser() {
        return commentsByIdUser;
    }

    public void setCommentsByIdUser(Collection<CommentEntity> commentsByIdUser) {
        this.commentsByIdUser = commentsByIdUser;
    }

    @OneToMany(mappedBy = "userByUser")
    public Collection<CompletedTestEntity> getCompletedTestsByIdUser() {
        return completedTestsByIdUser;
    }

    public void setCompletedTestsByIdUser(Collection<CompletedTestEntity> completedTestsByIdUser) {
        this.completedTestsByIdUser = completedTestsByIdUser;
    }

    @OneToMany(mappedBy = "userByAuthor")
    public Collection<CourseEntity> getCoursesByIdUser() {
        return coursesByIdUser;
    }

    public void setCoursesByIdUser(Collection<CourseEntity> coursesByIdUser) {
        this.coursesByIdUser = coursesByIdUser;
    }

    @OneToMany(mappedBy = "userByUser")
    public Collection<DiplomaEntity> getDiplomasByIdUser() {
        return diplomasByIdUser;
    }

    public void setDiplomasByIdUser(Collection<DiplomaEntity> diplomasByIdUser) {
        this.diplomasByIdUser = diplomasByIdUser;
    }

    @OneToMany(mappedBy = "userByUser")
    public Collection<ReadedLectionEntity> getReadedLectionsByIdUser() {
        return readedLectionsByIdUser;
    }

    public void setReadedLectionsByIdUser(Collection<ReadedLectionEntity> readedLectionsByIdUser) {
        this.readedLectionsByIdUser = readedLectionsByIdUser;
    }

    @OneToMany(mappedBy = "userByUser")
    public Collection<SubscriptionEntity> getSubscriptionsByIdUser() {
        return subscriptionsByIdUser;
    }

    public void setSubscriptionsByIdUser(Collection<SubscriptionEntity> subscriptionsByIdUser) {
        this.subscriptionsByIdUser = subscriptionsByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id_role", nullable = false, insertable = false, updatable = false)
    public RoleEntity getRoleByRole() {
        return roleByRole;
    }

    public void setRoleByRole(RoleEntity roleByRole) {
        this.roleByRole = roleByRole;
    }

    public boolean admin(){
        return getRole() == Roles.Role.Admin;
    }

    public boolean checkRights(UserEntity user, ActionType action){
        return action == ActionType.Read && user != null;
    }
}
