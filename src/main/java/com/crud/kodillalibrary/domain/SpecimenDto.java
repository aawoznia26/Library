package com.crud.kodillalibrary.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Set<Rent> rider = new HashSet<>();

    public SpecimenDto(SpecimenStatus status) {
        this.status = status;
    }
}
