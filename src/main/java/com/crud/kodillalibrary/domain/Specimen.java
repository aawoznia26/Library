package com.crud.kodillalibrary.domain;

import com.crud.kodillalibrary.dto.SpecimenDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity
public class Specimen {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @Enumerated(EnumType.STRING)
    private SpecimenStatus status;

    @JsonIgnore
    @OneToMany(targetEntity = Rent.class, mappedBy = "reader", fetch=FetchType.LAZY)
    private Set<Rent> reader = new HashSet<>();

    public Specimen(Title title, SpecimenStatus status) {
        this.title = title;
        this.status = status;
    }

    public Specimen(SpecimenStatus status) {
        this.status = status;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setStatus(SpecimenStatus status) {
        this.status = status;
    }

    public static SpecimenDto mapToSpecimenDto(final Specimen specimen) {
        SpecimenDto specimenDto = new SpecimenDto(
                specimen.getTitle().getId(),
                specimen.getStatus()
        );
        specimenDto.setId(specimen.getId());
        specimenDto.setReader(specimen.getReader());

        return specimenDto;
    }
}
