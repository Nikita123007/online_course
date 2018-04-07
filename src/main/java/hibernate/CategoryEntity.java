package hibernate;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category", schema = "online_course", catalog = "")
public class CategoryEntity {
    private int idCategory;
    private String name;
    private Collection<CourseEntity> coursesByIdCategory;

    @Id
    @Column(name = "id_category")
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "name")
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

        if (idCategory != that.idCategory) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoryByCategory")
    public Collection<CourseEntity> getCoursesByIdCategory() {
        return coursesByIdCategory;
    }

    public void setCoursesByIdCategory(Collection<CourseEntity> coursesByIdCategory) {
        this.coursesByIdCategory = coursesByIdCategory;
    }
}
