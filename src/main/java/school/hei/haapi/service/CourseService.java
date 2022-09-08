package school.hei.haapi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.Course;
import school.hei.haapi.repository.CourseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
//    @Autowired
//    private CourseRepository courseRepository ;
    private final CourseRepository courseRepository;

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public Course getById(String id){
        Course course = courseRepository.findById(id).get() ;
        return course ;
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course) ;
    }

    public Course putCourse(Course course){
        Course courseToBeUpdate = courseRepository.findById(course.getId()).get() ;
        courseToBeUpdate.setName(course.getName());
        courseToBeUpdate.setRef(course.getRef());
        courseToBeUpdate.setCredits(course.getCredits());
        courseToBeUpdate.setTotalHours(course.getTotalHours());
        return courseRepository.save(courseToBeUpdate) ;
    }
}

