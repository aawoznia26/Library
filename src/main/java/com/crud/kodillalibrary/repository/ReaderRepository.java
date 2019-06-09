package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    Reader findFirstByUuid (String uuid);
}
