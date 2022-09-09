package school.hei.haapi.endpoint.rest.mapper;


import org.springframework.stereotype.Component;
import school.hei.haapi.endpoint.rest.model.Event;

@Component
public class EventMapper {
    private PlaceMapper placeMapper;

    public Event toRest(school.hei.haapi.model.Event domain){
        return new Event()
                .idEvent(domain.getId())
                .eventDescription(domain.getEventDescription())
                .eventName(domain.getEventName())
                .endingTime(domain.getEndingTime())
                .startTime(domain.getStartTime())
                .place(placeMapper.toRest(domain.getPlace()));
    }

    public school.hei.haapi.model.Event toDomain(Event rest){
        return school.hei.haapi.model.Event.builder()
                .id(rest.getIdEvent())
                .endingTime(rest.getEndingTime())
                .eventDescription(rest.getEventDescription())
                .eventName(rest.getEventName())
                .place(placeMapper.toDomain(rest.getPlace()))
                .startTime(rest.getStartTime())
                .build() ;
    }
}
