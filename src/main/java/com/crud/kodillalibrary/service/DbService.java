package com.crud.kodillalibrary.service;


import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.repository.ReaderRepository;
import com.crud.kodillalibrary.repository.RentRepository;
import com.crud.kodillalibrary.repository.SpecimenRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DbService {

    private final ReaderRepository readerRepository;

    private final SpecimenRepository specimenRepository;

    private final TitleRepository titleRepository;

    private final RentRepository rentRepository;

    public int saveTitle(final Title title) {
        Specimen specimenSavedWithTitle = new Specimen(SpecimenStatus.AVAILABLE);
        title.getSpecimens().add(specimenSavedWithTitle);
        specimenSavedWithTitle.setTitle(title);
        specimenRepository.save(specimenSavedWithTitle);
        return titleRepository.save(title).getId();
    }

    public int saveReader(final Reader reader) {
        String uuid = UUID.randomUUID().toString();
        reader.setUuid(uuid);
        return readerRepository.save(reader).getId();
    }

    public int saveSpecimen(final int titleId){
        Title titleToAssignSpecimen = titleRepository.getOne(titleId);
        Specimen newSpecimen = new Specimen(SpecimenStatus.AVAILABLE);
        newSpecimen.setTitle(titleToAssignSpecimen);
        titleToAssignSpecimen.getSpecimens().add(newSpecimen);
        return specimenRepository.save(newSpecimen).getId();
    }

    public Specimen markSpecimenAsAvailable (final int specimenId) {
        Specimen specimenToUpdate = specimenRepository.getOne(specimenId);
        specimenToUpdate.setStatus(SpecimenStatus.AVAILABLE);
        return specimenRepository.save(specimenToUpdate);
    }

    public Specimen markSpecimenAsRent (final int specimenId) {
        Specimen specimenToUpdate = specimenRepository.getOne(specimenId);
        specimenToUpdate.setStatus(SpecimenStatus.RENT);
        return specimenRepository.save(specimenToUpdate);
    }

    public int getAvailableSpecimensCount(final int titleId){
        return specimenRepository.countAllByTitleIdAndStatus(titleId, SpecimenStatus.AVAILABLE);

    }

    public Rent rentBook(final Reader reader, final int titleId){
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

    public Rent returnBook(final int rentId){
        Rent foundRent = rentRepository.getOne(rentId);
        foundRent.setBookReturnDate(LocalDate.now());

        Specimen foundSpecimen = foundRent.getSpecimen();
        foundSpecimen.setStatus(SpecimenStatus.AVAILABLE);

        specimenRepository.save(foundSpecimen);
        return rentRepository.save(foundRent);
    }

}
