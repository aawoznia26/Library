package com.crud.kodillalibrary.domain;


import com.crud.kodillalibrary.dto.RentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames={"rider_id", "specimen_id"}))
public class Rent implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

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

    public static RentDto mapToRentDto(final Rent rent) {
        RentDto rentDto = new RentDto(
                rent.getId(),
                rent.getReader(),
                rent.getSpecimen(),
                rent.getRentDate(),
                rent.getBookReturnDate()
        );

        return rentDto;
    }
}
