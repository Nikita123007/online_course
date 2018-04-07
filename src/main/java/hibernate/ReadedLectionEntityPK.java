package hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ReadedLectionEntityPK implements Serializable {
    private int user;
    private int lection;

    @Column(name = "user")
    @Id
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Column(name = "lection")
    @Id
    public int getLection() {
        return lection;
    }

    public void setLection(int lection) {
        this.lection = lection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadedLectionEntityPK that = (ReadedLectionEntityPK) o;

        if (user != that.user) return false;
        if (lection != that.lection) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + lection;
        return result;
    }
}
