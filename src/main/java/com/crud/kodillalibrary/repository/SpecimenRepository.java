package com.crud.kodillalibrary.repository;


import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.SpecimenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, Integer> {

    int countAllByTitleIdAndStatus(int titleId, SpecimenStatus status);

    Specimen findFirstByStatusAndTitle_Id(SpecimenStatus status, int titleId);
}

