package com.crud.kodillalibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@NoArgsConstructor
public class RiderDto {

    private int id;
    private String name;
    private String lastName;
    private UUID uuid;
    private LocalDate enrollmentDate;
    private Set<RiderSpecimen> specimen = new HashSet<>();

    public RiderDto(String name, String lastName, LocalDate enrollmentDate) {
        this.name = name;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
    }
}
