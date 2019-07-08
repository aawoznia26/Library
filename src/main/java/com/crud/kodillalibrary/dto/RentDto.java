package com.crud.kodillalibrary.dto;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.domain.Rent;
import com.crud.kodillalibrary.domain.Specimen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private Long id;
    private Reader reader;
    private Specimen specimen;
    private LocalDate rentDate;
    private LocalDate bookReturnDate;

    public static Rent mapToRent(final RentDto rentDto) {
        Rent rent = new Rent(
                rentDto.getReader(),
                rentDto.getSpecimen(),
                rentDto.getRentDate(),
                rentDto.getBookReturnDate()
        );

        return rent;
    }
}
