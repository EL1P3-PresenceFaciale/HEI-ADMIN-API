package school.hei.haapi.endpoint.rest.mapper;

import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.Course;
import school.hei.haapi.endpoint.rest.model.Place;

@Component
public class PlaceMapper {
    public Place toRest(school.hei.haapi.model.Place domain) {
        return new Place()
                .id(domain.getId())
                .city(domain.getCity())
                .placeName(domain.getPlaceName());
    }

    public school.hei.haapi.model.Place toDomain(Place rest){
        return school.hei.haapi.model.Place.builder()
                .id(rest.getId())
                .placeName(rest.getPlaceName())
                .city(rest.getCity())
                .build() ;
    }
}
