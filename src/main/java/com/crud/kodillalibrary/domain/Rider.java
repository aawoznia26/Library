package com.crud.kodillalibrary.domain;


import com.crud.kodillalibrary.mapper.LocalDateAttributeConverter;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@NoArgsConstructor
@Entity
public class Rider {

    @Id
    @GeneratedValue
    @NotNull
    @Column(unique = true)
    private int id;

    @Column
    private String name;

    @Column
    private String lastName;

    @GeneratedValue
    @Column
    private String uuid;

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate enrollmentDate;


    @OneToMany(targetEntity = RiderSpecimen.class, mappedBy = "specimen", fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<RiderSpecimen> specimen = new HashSet<>();

    public Rider(String name, String lastName, LocalDate enrollmentDate) {
        this.name = name;
        this.lastName = lastName;
        this.enrollmentDate = enrollmentDate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
