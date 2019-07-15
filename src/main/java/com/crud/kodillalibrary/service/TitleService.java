package com.crud.kodillalibrary.service;


import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.SpecimenStatus;
import com.crud.kodillalibrary.domain.Title;
import com.crud.kodillalibrary.dto.SpecimenDto;
import com.crud.kodillalibrary.repository.SpecimenRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TitleService {

    private final TitleRepository titleRepository;
    private final SpecimenRepository specimenRepository;


    @Transactional
    public Long saveTitle(final Title title) {
        Specimen specimenSavedWithTitle = new Specimen(SpecimenStatus.AVAILABLE);
        title.getSpecimens().add(specimenSavedWithTitle);
        specimenSavedWithTitle.setTitle(title);
        specimenRepository.save(specimenSavedWithTitle);
        return titleRepository.save(title).getId();
    }


    public Specimen markSpecimenAsAvailable (final Long specimenId) {
        Specimen specimenToUpdate = specimenRepository.getOne(specimenId);
        specimenToUpdate.setStatus(SpecimenStatus.AVAILABLE);
        return specimenRepository.save(specimenToUpdate);
    }

    public Specimen markSpecimenAsRent (final Long specimenId) {
        Specimen specimenToUpdate = specimenRepository.getOne(specimenId);
        specimenToUpdate.setStatus(SpecimenStatus.RENT);
        return specimenRepository.save(specimenToUpdate);
    }

    public Long getAvailableSpecimensCount(final Long titleId){
        return specimenRepository.countAllByTitleIdAndStatus(titleId, SpecimenStatus.AVAILABLE);

    }

    @Transactional
    public Long saveSpecimen(final Specimen specimen){
        Specimen newSpecimen = new Specimen(specimen.getTitle(), SpecimenStatus.AVAILABLE);
        return specimenRepository.save(newSpecimen).getId();
    }

    public Specimen mapToSpecimen(SpecimenDto specimenDto) {
        Long id = specimenDto.getTitleId();
        Title title = titleRepository.findOne(id);
        return new Specimen(
                title,
                specimenDto.getStatus()
        );
    }
}
