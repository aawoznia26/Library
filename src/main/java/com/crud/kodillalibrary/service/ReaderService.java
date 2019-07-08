package com.crud.kodillalibrary.service;


import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Transactional
    public Long saveReader(final Reader reader) {
        String uuid = UUID.randomUUID().toString();
        reader.setUuid(uuid);
        return readerRepository.save(reader).getId();
    }



}
