package hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "readed_lection", schema = "online_course", catalog = "")
@IdClass(ReadedLectionEntityPK.class)
public class ReadedLectionEntity {
    @Id
    @Column(name = "user")
    private int user;

    @Id
    @Column(name = "lection")
    private int lection;

    @Basic
    @Column(name = "first_view")
    private Timestamp firstView;

    @Basic
    @Column(name = "last_view")
    private Timestamp lastView;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private UserEntity userObject;

    @ManyToOne
    @JoinColumn(name = "lection", referencedColumnName = "id_lection", insertable = false, updatable = false)
    private LectionEntity lectionObject;

    public Timestamp getFirstView() {
        return firstView;
    }
    public void setFirstView(Timestamp firstView) {
        this.firstView = firstView;
    }

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

        if (firstView != null ? !firstView.equals(that.firstView) : that.firstView != null) return false;
        if (lastView != null ? !lastView.equals(that.lastView) : that.lastView != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (firstView != null ? firstView.hashCode() : 0);
        result = 31 * result + (lastView != null ? lastView.hashCode() : 0);
        return result;
    }

    public UserEntity getUser() {
        return userObject;
    }
    public void setUser(UserEntity userObject) {
        if(! userObject.equals(this.userObject)){
            this.userObject = userObject;
            userObject.addReadedLection(this);
        }
    }

    public LectionEntity getLection() {
        return lectionObject;
    }
    public void setLection(LectionEntity lectionObject) {
        if(! lectionObject.equals(this.lectionObject)){
            this.lectionObject = lectionObject;
            lectionObject.addReadedLection(this);
        }
    }
}
