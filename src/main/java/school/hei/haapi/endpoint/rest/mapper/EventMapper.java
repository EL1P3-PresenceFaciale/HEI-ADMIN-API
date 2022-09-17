package school.hei.haapi.endpoint.rest.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.Event;
import school.hei.haapi.model.Place;
import school.hei.haapi.model.User;
import school.hei.haapi.service.PlaceService;
import school.hei.haapi.service.UserService;

@Component
@AllArgsConstructor
public class EventMapper {
    private UserService userService;
    private PlaceService placeService;

    public Event toRest(school.hei.haapi.model.Event domain) {
        return new Event()
                .idEvent(domain.getIdEvent())
                .eventDescription(domain.getEventDescription())
                .responsible(domain.getEventResponsible().getId())
                .eventName(domain.getEventName())
                .place(domain.getPlace().getId())
                .startTime(domain.getStartTime())
                .endingTime(domain.getEndingTime());
    }

    public school.hei.haapi.model.Event toDomain(Event rest) {
        User responsible = userService.getById(rest.getResponsible());
        Place place = placeService.getPlacesById(rest.getPlace());
        return school.hei.haapi.model.Event.builder()
                .idEvent(rest.getIdEvent())
                .eventDescription(rest.getEventDescription())
                .eventResponsible(responsible)
                .eventName(rest.getEventName())
                .place(place)
                .startTime(rest.getStartTime())
                .endingTime(rest.getEndingTime())
                .build();
    }
}
