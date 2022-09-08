package school.hei.haapi.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.Course;
import school.hei.haapi.model.Place;
import school.hei.haapi.repository.CourseRepository;
import school.hei.haapi.repository.PlaceRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class PlaceService {
    private PlaceRepository placeRepository ;

    public List<Place> getPlaces(){
        return placeRepository.findAll() ;
    }

    public Place createOrUpdatePlaces(Place place){
        Place placeToPut = placeRepository.findById(place.getId()).get() ;
        placeToPut.setPlaceName(place.getPlaceName());
        placeToPut.setCity(place.getCity());
        return placeRepository.save(placeToPut) ;
    }

    public Place getPlacesById(String id){
        return placeRepository.findById(id).get() ;
    }

}
