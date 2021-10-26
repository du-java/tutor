package com.example.tutor.facade;

import com.example.tutor.dto.TutorDto;
import com.example.tutor.mapper.TutorMapper;
import com.example.tutor.services.TutorService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorFacade {

    private final TutorService tutorService;
    private final TutorMapper tutorMapper;

    public TutorDto create(TutorDto tutorDto) {
        return tutorMapper.map(tutorService.save(tutorMapper.map(tutorDto)));
    }

    public List<TutorDto> findAll() {
        return tutorService.findAll().stream()
                .map(tutorMapper::map)
                .collect(Collectors.toList());
    }

    public TutorDto findById(Long id) {
        return tutorMapper.map(tutorService.findById(id));
    }

    public void deleteById(Long id) {
        tutorService.deleteById(id);
    }

    public TutorDto update(TutorDto tutorDto) {
        return tutorMapper.map(tutorService.save(tutorMapper.map(tutorDto)));
    }
}
