package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.RiderSpecimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface RiderSpecimenRepository extends JpaRepository<RiderSpecimen, Integer> {
}
