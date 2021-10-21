package com.example.tutor.mapper;

import com.example.tutor.models.Tutor;
import com.example.tutor.services.TutorService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TutorsMapper {

    @Autowired
    private TutorService tutorService;

    public Long map(Tutor entity) {
        return entity.getId();
    }

    public Tutor map(Long id) {
        return tutorService.findById(id);
    }

}
