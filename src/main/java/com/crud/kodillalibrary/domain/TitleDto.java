package com.crud.kodillalibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TitleDto {

    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private List<Specimen> specimens = new ArrayList<>();

    public TitleDto(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}


