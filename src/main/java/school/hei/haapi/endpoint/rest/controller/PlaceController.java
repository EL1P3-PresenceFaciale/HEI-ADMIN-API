package school.hei.haapi.endpoint.rest.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.haapi.endpoint.rest.mapper.CourseMapper;
import school.hei.haapi.endpoint.rest.mapper.PlaceMapper;
import school.hei.haapi.endpoint.rest.model.Course;
import school.hei.haapi.endpoint.rest.model.Place;
import school.hei.haapi.model.exception.NotImplementedException;
import school.hei.haapi.service.CourseService;
import school.hei.haapi.service.PlaceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

public class PlaceController {
    private PlaceService placeService ;

    private PlaceMapper placeMapper ;

    @GetMapping("/places")
    public List<Place> getPlaces(){
        return placeService.getPlaces().stream()
                .map(placeMapper::toRest)
                .collect(Collectors.toUnmodifiableList());
    }

    @PutMapping("/places")
    public school.hei.haapi.endpoint.rest.model.Place createOrUpdatePlace(school.hei.haapi.endpoint.rest.model.Place place){
        return placeMapper.toRest(placeService.createOrUpdatePlaces(placeMapper.toDomain(place))) ;
    }

    @GetMapping("/places/{id}")
    public school.hei.haapi.endpoint.rest.model.Place getPlacesById(String id){
        return placeMapper.toRest(placeService.getPlacesById(id)) ;
    }

}
