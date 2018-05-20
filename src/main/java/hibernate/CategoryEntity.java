package hibernate;

import common.ActionType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", schema = "online_course", catalog = "")
public class CategoryEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<CourseEntity> courses = new HashSet<>();

    public int getId() {
        return id;
    }
    public void setId(int idCategory) {
        this.id = idCategory;
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

        CategoryEntity that = (CategoryEntity) o;

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

    public Set<CourseEntity> getCourses() {
        return courses;
    }
    public void addCourse(CourseEntity course) {
        if(! this.courses.contains(course)){
            this.courses.add(course);
            course.setCategory(this);
        }
    }

    public boolean checkRights(UserEntity user, ActionType action){
        return action == ActionType.Read;
    }
}
