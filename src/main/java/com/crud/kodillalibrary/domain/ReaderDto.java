package com.crud.kodillalibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
public class ReaderDto {

    private int id;
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
}
