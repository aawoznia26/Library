package com.crud.kodillalibrary.domain;


import com.crud.kodillalibrary.dto.ReaderDto;
import com.crud.kodillalibrary.mapper.LocalDateAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Reader {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    private String lastName;

    @GeneratedValue
    private String uuid;

    @Convert(converter = LocalDateAttributeConverter.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate enrollmentDate;


    @OneToMany(targetEntity = Rent.class, mappedBy = "specimen", fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<Rent> specimen = new HashSet<>();

    public Reader(String name, String lastName, LocalDate enrollmentDate) {
        this.name = name;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static ReaderDto mapToReaderDto(final Reader reader) {
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
