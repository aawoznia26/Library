package com.crud.kodillalibrary.repository;


import com.crud.kodillalibrary.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    Title findFirstByAuthorAndPublicationYearAndTitle(String author, int publicationYear, String title);
}
