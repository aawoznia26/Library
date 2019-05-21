package com.crud.kodillalibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity
public class Specimen {

    @Id
    @GeneratedValue
    @NotNull
    @Column(unique = true)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TITLE_ID")
    @JsonIgnore
    private Title title;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecimenStatus status;

    @JsonIgnore
    @OneToMany(targetEntity = RiderSpecimen.class, mappedBy = "rider", fetch=FetchType.LAZY)
    private Set<RiderSpecimen> rider = new HashSet<>();

    public Specimen(SpecimenStatus status) {
        this.status = status;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setStatus(SpecimenStatus status) {
        this.status = status;
    }
}
