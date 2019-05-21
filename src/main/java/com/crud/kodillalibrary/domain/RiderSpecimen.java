package com.crud.kodillalibrary.domain;


import com.crud.kodillalibrary.mapper.LocalDateAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"rider_id", "specimen_id"}))
public class RiderSpecimen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "specimen_id")
    private Specimen specimen;

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate rentDate;

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate bookReturnDate;

    public RiderSpecimen(Rider rider, Specimen specimen, LocalDate rentDate) {
        this.rider = rider;
        this.specimen = specimen;
        this.rentDate = rentDate;
    }

    public void setBookReturnDate(LocalDate bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }
}
