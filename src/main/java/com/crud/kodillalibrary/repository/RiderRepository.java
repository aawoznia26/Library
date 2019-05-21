package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {

    Rider findFirstByUuid (String uuid);
}
