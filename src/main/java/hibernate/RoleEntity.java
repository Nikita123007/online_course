package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", schema = "online_course", catalog = "")
public class RoleEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<UserEntity> users = new HashSet<>();

    public int getId() {
        return id;
    }
    public void setId(int idRole) {
        this.id = idRole;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    public Set<UserEntity> getUsers() {
        return users;
    }
    public void addUser(UserEntity user) {
        if(! this.users.contains(user)){
            this.users.add(user);
            user.setRole(this);
        }
    }

    public boolean checkRights(UserEntity user, ActionType action){
        return action == ActionType.Read;
    }
}
