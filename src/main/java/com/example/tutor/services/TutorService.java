package com.example.tutor.services;

import com.example.tutor.exeptions.NotFoundExeption;
import com.example.tutor.models.Tutor;
import com.example.tutor.repositories.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    public Tutor findById(Long id) {
        return tutorRepository.findById(id).orElseThrow(() -> new NotFoundExeption(id, "tutor"));
    }

    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
    public List<Tutor> findAll(){
        return tutorRepository.findAll();
    }

    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }
}
