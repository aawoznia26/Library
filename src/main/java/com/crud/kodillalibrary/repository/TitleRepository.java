package com.crud.kodillalibrary.repository;


import com.crud.kodillalibrary.domain.Title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findFirstByAuthorAndPublicationYearAndTitle(String author, Long publicationYear, String title);
}
