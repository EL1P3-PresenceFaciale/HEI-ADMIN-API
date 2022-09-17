package school.hei.haapi.endpoint.rest.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
//import school.hei.haapi.endpoint.rest.mapper.EventMapper;
import school.hei.haapi.endpoint.rest.mapper.EventMapper;
import school.hei.haapi.endpoint.rest.model.Event;
import school.hei.haapi.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

public class EventController {
    private EventService eventService ;
    private EventMapper eventMapper ;

    @GetMapping("/events")
    public List<Event> getAllEvents (@RequestParam int page , @RequestParam int pageSize){
        return eventService.getAllEvents(page , pageSize).stream()
                .map(eventMapper::toRest)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/events/{id_event}")
    public Event getEventById(@PathVariable String id_event){
        return eventMapper.toRest(eventService.getEventById(id_event)) ;
    }

    @PutMapping("/events")
    public List<Event> createOrUpadteEvent(@RequestBody List<Event> events){
        var saved = events.stream().map(eventMapper::toDomain).collect(Collectors.toUnmodifiableList());
        return eventService.createOrUpdateEvent(saved).stream().map(eventMapper::toRest).collect(Collectors.toUnmodifiableList());
    }

    @DeleteMapping("/events/{id_event}")
    public String deleteEventByID(String id_event){
        return eventService.deleteById(id_event) ;
    }


}
