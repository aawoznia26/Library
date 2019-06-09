package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Rent;
import com.crud.kodillalibrary.domain.RentDto;
import org.springframework.stereotype.Component;

@Component
public class RentMapper {

    public RentDto mapToRentDto(final Rent rent) {
        RentDto rentDto = new RentDto(
                rent.getId(),
                rent.getReader(),
                rent.getSpecimen(),
                rent.getRentDate(),
                rent.getBookReturnDate()
        );

        return rentDto;
    }

    public Rent mapToRent(final RentDto rentDto) {
        Rent rent = new Rent(
                rentDto.getReader(),
                rentDto.getSpecimen(),
                rentDto.getRentDate(),
                rentDto.getBookReturnDate()
        );

        return rent;
    }
}
