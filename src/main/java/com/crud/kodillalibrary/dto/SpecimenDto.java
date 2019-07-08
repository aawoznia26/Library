package com.crud.kodillalibrary.dto;


import com.crud.kodillalibrary.domain.Rent;
import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.SpecimenStatus;
import com.crud.kodillalibrary.domain.Title;
import com.crud.kodillalibrary.repository.TitleRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Component
public class SpecimenDto {

    @Autowired
    private TitleRepository titleRepository;

    private Long id;
    private Long titleId;
    private SpecimenStatus status;
    private Set<Rent> reader = new HashSet<>();

    public SpecimenDto(Long titleId, SpecimenStatus status) {
        this.titleId = titleId;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReader(Set<Rent> reader) {
        this.reader = reader;
    }

    public Specimen mapToSpecimen() {
        Long id = this.getTitleId();
        Title title = titleRepository.findOne(id);
        return new Specimen(
                title,
                this.getStatus()
        );
    }

}
