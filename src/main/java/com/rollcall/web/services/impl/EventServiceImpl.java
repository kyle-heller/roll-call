package com.rollcall.web.services.impl;

import com.rollcall.web.dto.EventDto;
import com.rollcall.web.mapper.EventMapper;
import com.rollcall.web.models.Event;
import com.rollcall.web.models.Group;
import com.rollcall.web.models.UserEntity;
import com.rollcall.web.repository.EventRepository;
import com.rollcall.web.repository.GroupRepository;
import com.rollcall.web.repository.UserRepository;
import com.rollcall.web.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rollcall.web.mapper.EventMapper.mapToEvent;
import static com.rollcall.web.mapper.EventMapper.mapToEventDto;
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    @Override // Creates a new event and associates it with the group specified by groupId, then saves it to the database
    public void createEvent(Long groupId, EventDto eventDto) {
        Group group = groupRepository.findById(groupId).get();
        Event event = mapToEvent(eventDto);
        event.setGroup(group);
        eventRepository.save(event);
    }

    @Override // Retrieves all event entities from the database, maps them to EventDto objects, and returns them as a list
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override // Finds a single event by its ID, maps it to an EventDto, and returns it, or throws an exception if not found
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }


    @Override // Updates an existing event entity with the values from the provided EventDto and saves the changes to the database
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override // Deletes an event identified by eventId from the database
    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }

    public void toggleUserParticipationInEvent(Long userId, Long eventId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        // Check if the user is already participating in the event
        boolean isParticipating = user.getEvents().contains(event);

        if (isParticipating) {
            // If the user is already participating, remove them from the event
            user.getEvents().remove(event);
        } else {
            // If the user is not already participating, add them to the event
            user.getEvents().add(event);
        }
        userRepository.save(user); // Save the user entity back to the database
    }

    @Override
    public List<EventDto> findEventsByZipCode(List<Integer> zips) {
        List<Event> events = eventRepository.findByZipCodeIn(zips);
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

}
