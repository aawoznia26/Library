package com.crud.kodillalibrary.service;


import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.domain.Rent;
import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.SpecimenStatus;
import com.crud.kodillalibrary.repository.ReaderRepository;
import com.crud.kodillalibrary.repository.RentRepository;
import com.crud.kodillalibrary.repository.SpecimenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class RentService {

    private final RentRepository rentRepository;

    private final ReaderRepository readerRepository;

    private final SpecimenRepository specimenRepository;


    @Transactional
    public Rent rentBook(final Reader reader, final Long titleId){
        Reader foundReader = readerRepository.findFirstByUuid(reader.getUuid());
        Specimen foundSpecimen = specimenRepository.findFirstByStatusAndTitle_Id(SpecimenStatus.AVAILABLE, titleId);

        Rent rent = new Rent(foundReader, foundSpecimen, LocalDate.now(), null);

        foundSpecimen.setStatus(SpecimenStatus.RENT);
        foundReader.getSpecimen().add(rent);
        foundSpecimen.getReader().add(rent);

        specimenRepository.save(foundSpecimen);
        readerRepository.save(foundReader);
        return rentRepository.save(rent);

    }

    @Transactional
    public Rent returnBook(final Long rentId){
        Rent foundRent = rentRepository.getOne(rentId);
        foundRent.setBookReturnDate(LocalDate.now());

        Specimen foundSpecimen = foundRent.getSpecimen();
        foundSpecimen.setStatus(SpecimenStatus.AVAILABLE);

        specimenRepository.save(foundSpecimen);
        return rentRepository.save(foundRent);
    }

}
