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

    public List<Event> createOrUpdateEvent(List<Event> events){
        return eventRepository.saveAll(events) ;
    }

    public String deleteById(String eventId) {
        eventRepository.deleteById(eventId);
        return "Deleted successfull";
    }
}
