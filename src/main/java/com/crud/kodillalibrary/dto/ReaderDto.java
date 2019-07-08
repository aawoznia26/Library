package com.crud.kodillalibrary.dto;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.domain.Rent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
public class ReaderDto {

    private Long id;
    private String name;
    private String lastName;
    private String uuid;
    private LocalDate enrollmentDate;
    private Set<Rent> specimen = new HashSet<>();

    public ReaderDto(String name, String lastName, LocalDate enrollmentDate) {
        this.name = name;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static Reader mapToReader(final ReaderDto readerDto) {
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
}
