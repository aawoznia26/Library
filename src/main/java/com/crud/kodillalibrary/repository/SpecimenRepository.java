package com.crud.kodillalibrary.repository;


import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.SpecimenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Long> {

    Long countAllByTitleIdAndStatus(Long titleId, SpecimenStatus status);

    Specimen findFirstByStatusAndTitle_Id(SpecimenStatus status, Long titleId);
}

