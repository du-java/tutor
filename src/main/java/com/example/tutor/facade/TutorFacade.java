package com.example.tutor.facade;

import com.example.tutor.dto.CreateTutorDto;
import com.example.tutor.dto.TutorDto;
import com.example.tutor.services.TutorService;
import com.example.tutor.services.converter.TutorConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorFacade {

    private final TutorService tutorService;
    private final TutorConverter tutorConverter;

    public TutorDto create(CreateTutorDto tutorDto) {
        return tutorConverter.convert(tutorService.save(tutorConverter.convert(tutorDto)));
    }

    public List<TutorDto> findAll() {
        return tutorService.findAll().stream()
                .map(tutorConverter::convert)
                .collect(Collectors.toList());
    }

    public TutorDto findById(Long id) {
        return tutorConverter.convert(tutorService.findById(id));
    }

    public void deleteById(Long id) {
        tutorService.deleteById(id);
    }

    public TutorDto update(TutorDto tutorDto) {
        return tutorConverter.convert(tutorService.save(tutorConverter.convert(tutorDto)));
    }
}
