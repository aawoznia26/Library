package com.crud.kodillalibrary.domain;


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
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Reader reader;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "specimen_id")
    private Specimen specimen;

    private LocalDate rentDate;

    private LocalDate bookReturnDate;

    public Rent(Reader reader, Specimen specimen, LocalDate rentDate, LocalDate bookReturnDate) {
        this.reader = reader;
        this.specimen = specimen;
        this.rentDate = rentDate;
        this.bookReturnDate = bookReturnDate;
    }

    public void setBookReturnDate(LocalDate bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }
}
