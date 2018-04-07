package dao;

import hibernate.CourseEntity;

import java.util.Collection;
import java.util.List;

public interface CourseDAO {
    void addCourse(CourseEntity entity);
    void mergeCourse(CourseEntity entity);
    Collection<CourseEntity> getAll();
    CourseEntity getCourse(int id);
}
