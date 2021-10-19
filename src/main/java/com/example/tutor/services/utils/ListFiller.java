package com.example.tutor.services.utils;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ListFiller {

    public List<Long> ifNullGetEmptyList(List<Long> list) {
        return list == null ? Collections.emptyList() : list;
    }
}
