package com.example.tutor.services.converter;

import com.example.tutor.dto.Dto;
import com.example.tutor.models.Model;

public interface Converter<M extends Model,D extends Dto> {
    M convert(D d);
    D convert(M m);
}
