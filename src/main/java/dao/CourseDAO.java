package dao;

import hibernate.CourseEntity;

import java.util.Collection;
import java.util.List;

public interface CourseDAO {
    void addCourse(CourseEntity entity);
    void mergeCourse(CourseEntity entity);
    void removeCourse(CourseEntity entity);
    Collection<CourseEntity> getAll();
    CourseEntity getCourse(int id);
    Collection<CourseEntity> getAllByUser(int id);
}
