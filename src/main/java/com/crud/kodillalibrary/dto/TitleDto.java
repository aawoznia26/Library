package com.crud.kodillalibrary.dto;

import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.domain.Title;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TitleDto {

    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private List<Specimen> specimens = new ArrayList<>();

    public TitleDto(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public static Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationYear()
        );
    }
}


