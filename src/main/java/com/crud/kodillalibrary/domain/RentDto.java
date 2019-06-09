package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private int id;
    private Reader reader;
    private Specimen specimen;
    private LocalDate rentDate;
    private LocalDate bookReturnDate;
}