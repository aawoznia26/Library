package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.domain.ReaderDto;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
         Reader reader = new Reader(
                readerDto.getName(),
                readerDto.getLastName(),
                readerDto.getEnrollmentDate()
        );
        if(readerDto.getUuid() != null) {
            reader.setUuid(readerDto.getUuid());
        }
        return reader;
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        ReaderDto readerDto = new ReaderDto(
                reader.getName(),
                reader.getLastName(),
                reader.getEnrollmentDate()
        );
        if(reader.getUuid() != null) {
            readerDto.setUuid(reader.getUuid());
        }

        return readerDto;
    }

}
