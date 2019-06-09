package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

   Rent findFirstByReaderIdAndSpecimenId (int readerId, int specimenId);
}
