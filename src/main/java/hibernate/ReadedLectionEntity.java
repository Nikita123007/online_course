package hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "readed_lection", schema = "online_course", catalog = "")
@IdClass(ReadedLectionEntityPK.class)
public class ReadedLectionEntity {
    private int user;
    private int lection;
    private Timestamp firstView;
    private Timestamp lastView;
    private UserEntity userByUser;
    private LectionEntity lectionByLection;

    @Id
    @Column(name = "user")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Id
    @Column(name = "lection")
    public int getLection() {
        return lection;
    }

    public void setLection(int lection) {
        this.lection = lection;
    }

    @Basic
    @Column(name = "first_view")
    public Timestamp getFirstView() {
        return firstView;
    }

    public void setFirstView(Timestamp firstView) {
        this.firstView = firstView;
    }

    @Basic
    @Column(name = "last_view")
    public Timestamp getLastView() {
        return lastView;
    }

    public void setLastView(Timestamp lastView) {
        this.lastView = lastView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadedLectionEntity that = (ReadedLectionEntity) o;

        if (user != that.user) return false;
        if (lection != that.lection) return false;
        if (firstView != null ? !firstView.equals(that.firstView) : that.firstView != null) return false;
        if (lastView != null ? !lastView.equals(that.lastView) : that.lastView != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + lection;
        result = 31 * result + (firstView != null ? firstView.hashCode() : 0);
        result = 31 * result + (lastView != null ? lastView.hashCode() : 0);
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
    @JoinColumn(name = "lection", referencedColumnName = "id_lection", nullable = false, insertable = false, updatable = false)
    public LectionEntity getLectionByLection() {
        return lectionByLection;
    }

    public void setLectionByLection(LectionEntity lectionByLection) {
        this.lectionByLection = lectionByLection;
    }
}
