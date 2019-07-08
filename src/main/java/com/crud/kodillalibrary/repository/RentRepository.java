package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

   Rent findFirstByReaderIdAndSpecimenId (Long readerId, Long specimenId);
}
