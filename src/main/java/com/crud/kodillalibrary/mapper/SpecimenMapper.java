package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.SpecimenDto;
import org.springframework.stereotype.Component;


@Component
public class SpecimenMapper {

    public Specimen mapToSpecimen(final SpecimenDto specimenDto) {
        return new Specimen(
                specimenDto.getStatus()
        );
    }

    public SpecimenDto mapToSpecimenDto(final Specimen specimen) {
        return new SpecimenDto(
                specimen.getStatus()
        );
    }

}
