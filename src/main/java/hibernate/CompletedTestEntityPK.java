package hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CompletedTestEntityPK implements Serializable {
    private int user;
    private int test;

    @Column(name = "user")
    @Id
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Column(name = "test")
    @Id
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompletedTestEntityPK that = (CompletedTestEntityPK) o;

        if (user != that.user) return false;
        if (test != that.test) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user;
        result = 31 * result + test;
        return result;
    }
}
