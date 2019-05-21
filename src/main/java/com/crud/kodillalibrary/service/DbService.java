package com.crud.kodillalibrary.service;


import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.repository.RiderRepository;
import com.crud.kodillalibrary.repository.RiderSpecimenRepository;
import com.crud.kodillalibrary.repository.SpecimenRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class DbService {

    private final RiderRepository riderRepository;

    private final SpecimenRepository specimenRepository;

    private final TitleRepository titleRepository;

    private final RiderSpecimenRepository riderSpecimenRepository;

    public Title saveTitle(final Title title) {
        Specimen specimenSavedWithTitle = new Specimen(SpecimenStatus.AVAILABLE);
        title.getSpecimens().add(specimenSavedWithTitle);
        specimenSavedWithTitle.setTitle(title);
        specimenRepository.save(specimenSavedWithTitle);
        return titleRepository.save(title);
    }

    public Rider saveRider(final Rider rider) {
        String uuid = UUID.randomUUID().toString();
        rider.setUuid(uuid);
        return riderRepository.save(rider);
    }

    public Specimen saveSpecimen(final int titleId){
        Title titleToAssignSpecimen = titleRepository.getOne(titleId);
        Specimen newSpecimen = new Specimen(SpecimenStatus.RENT);
        newSpecimen.setTitle(titleToAssignSpecimen);
        return specimenRepository.save(newSpecimen);
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

    public RiderSpecimen rentBook(final Rider rider, final int titleId){
        Rider foundRider = riderRepository.findFirstByUuid(rider.getUuid());
        Specimen foundSpecimen = specimenRepository.findFirstByStatusAndTitle_Id(SpecimenStatus.AVAILABLE, titleId);

        RiderSpecimen riderSpecimen = new RiderSpecimen(foundRider, foundSpecimen, LocalDate.now());

        foundSpecimen.setStatus(SpecimenStatus.RENT);
        foundRider.getSpecimen().add(riderSpecimen);
        foundSpecimen.getRider().add(riderSpecimen);

        specimenRepository.save(foundSpecimen);
        riderRepository.save(foundRider);
        return riderSpecimenRepository.save(riderSpecimen);

    }

    public RiderSpecimen returnBook(final int riderSpecimenId){
        RiderSpecimen foundRiderSpecimen = riderSpecimenRepository.getOne(riderSpecimenId);
        foundRiderSpecimen.setBookReturnDate(LocalDate.now());

        Specimen foundSpecimen = foundRiderSpecimen.getSpecimen();
        foundSpecimen.setStatus(SpecimenStatus.AVAILABLE);

        specimenRepository.save(foundSpecimen);
        return riderSpecimenRepository.save(foundRiderSpecimen);
    }

}
