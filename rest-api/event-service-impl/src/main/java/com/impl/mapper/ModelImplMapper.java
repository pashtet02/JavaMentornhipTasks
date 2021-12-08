package com.impl.mapper;

import com.api.model.Event;
import com.example.dto.EventDto;
import com.impl.model.EventImpl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelImplMapper {
    ModelImplMapper INSTANCE = Mappers.getMapper(ModelImplMapper.class);

    EventDto eventToEventDto(EventImpl source);
    EventImpl eventDtoToEventImpl(EventDto destination);
    Event eventImplToEvent(EventImpl source);
    Event eventDtoToEvent(EventDto destination);
}
