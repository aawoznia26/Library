package com.crud.kodillalibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity
public class Title {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    private String title;

    private String author;

    private int publicationYear;

    @OneToMany(targetEntity = Specimen.class, mappedBy = "title", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Specimen> specimens = new HashSet<>();

    public Title(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
