package school.hei.haapi.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.Course;

@Component
public class CourseMapper {
    public Course toRest(school.hei.haapi.model.Course domain) {
        return new Course()
                .id(domain.getId())
                .ref(domain.getRef())
                .name(domain.getName())
                .credits(domain.getCredits())
                .totalHours(domain.getTotalHours());
    }

    public school.hei.haapi.model.Course toDomain(Course rest) {
        return school.hei.haapi.model.Course.builder()
                .id(rest.getId())
                .ref(rest.getRef())
                .name(rest.getName())
                .credits(rest.getCredits())
                .totalHours(rest.getTotalHours())
                .build();
    }
}
