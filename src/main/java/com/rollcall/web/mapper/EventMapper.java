package com.rollcall.web.mapper;

import com.rollcall.web.dto.EventDto;
import com.rollcall.web.models.Event;

public class EventMapper {

    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .zip(eventDto.getZip())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoURL(eventDto.getPhotoURL())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .group(eventDto.getGroup())
                .game(eventDto.getGame())
                .zip(eventDto.getZip())
                .state(eventDto.getState())
                .city(eventDto.getCity())
                .content(eventDto.getContent())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .zip(event.getZip())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoURL(event.getPhotoURL())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .group(event.getGroup())
                .game(event.getGame())
                .zip(event.getZip())
                .state(event.getState())
                .city(event.getCity())
                .content(event.getContent())
                .build();
    }

}
