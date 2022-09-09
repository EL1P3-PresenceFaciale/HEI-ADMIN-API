package school.hei.haapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.Event;
import school.hei.haapi.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EventService {
    private EventRepository eventRepository ;

    public List<Event> getAllEvents(int page , int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return eventRepository.findAll(pageable).stream().collect(Collectors.toUnmodifiableList()) ;
    }

    public Event getEventById(String id){
        return eventRepository.findById(id).get() ;
    }

    public Event createOrUpdateEvent(Event event){
        Event eventToUpdate = eventRepository.getById(event.getId()) ;
        if(eventRepository.existsById(event.getId())){
        eventToUpdate.setEventDescription(event.getEventDescription());
        eventToUpdate.setEventName(event.getEventName());
        eventToUpdate.setEventResponsible(event.getEventResponsible());
        eventToUpdate.setPlace(event.getPlace());
        eventToUpdate.setStartTime(event.getStartTime());
        eventToUpdate.setEndingTime(event.getEndingTime());
            return eventRepository.save(eventToUpdate) ;
        }
        return eventRepository.save(event) ;
    }
}
