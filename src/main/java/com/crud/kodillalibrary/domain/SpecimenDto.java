package com.crud.kodillalibrary.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class SpecimenDto {

    private int id;
    private Title title;
    private SpecimenStatus status;
    private Set<RiderSpecimen> rider = new HashSet<>();

    public SpecimenDto(SpecimenStatus status) {
        this.status = status;
    }
}
