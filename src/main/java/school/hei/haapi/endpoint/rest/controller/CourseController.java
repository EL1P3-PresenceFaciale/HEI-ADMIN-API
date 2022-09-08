package school.hei.haapi.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.haapi.endpoint.rest.mapper.CourseMapper;
import school.hei.haapi.endpoint.rest.model.Course;
import school.hei.haapi.model.exception.NotImplementedException;
import school.hei.haapi.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CourseController {
    private final CourseService service ;
    private final CourseMapper mapper ;

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return service.getCourses().stream()
                .map(mapper::toRest)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable String id){
        return mapper.toRest(service.getById(id)) ;
    }

    @PutMapping("/courses")
    public Course putCourse(Course course){
        return mapper.toRest(service.putCourse(mapper.toDomain(course))) ;
    }
}
