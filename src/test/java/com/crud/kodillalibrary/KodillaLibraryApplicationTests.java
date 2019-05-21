package com.crud.kodillalibrary;

import com.crud.kodillalibrary.controller.LibraryControler;
import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.repository.RiderRepository;
import com.crud.kodillalibrary.repository.RiderSpecimenRepository;
import com.crud.kodillalibrary.repository.SpecimenRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import com.crud.kodillalibrary.service.DbService;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest

public class KodillaLibraryApplicationTests {

    @Autowired
    RiderRepository riderRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    SpecimenRepository specimenRepository;

    @Autowired
    RiderSpecimenRepository riderSpecimenRepository;

    @Autowired
    LibraryControler libraryControler;

    @Test
    public void testRiderSave() {
        //Given
        RiderDto riderDto = new RiderDto("Alina", "Nawrot", LocalDate.of(2019, 5, 10));

        //When
        libraryControler.saveRider(riderDto);

        //Then
        int id = riderDto.getId();
        Rider foundRider = riderRepository.getOne(id);
        Assert.assertNotEquals(null, foundRider);

    }

    @Transactional
    @Test
    public void testTitleSave() {
        //Given
        TitleDto titleDto = new TitleDto("TestTitleSave", "Brajan Brown", 1982);

        //When
        libraryControler.saveTitle(titleDto);

        int titleId = titleDto.getId();

        //Then
        Assert.assertNotEquals(null, titleId);

    }

    @Transactional
    @Test
    public void testSpecimenSave() {
        //Given
        TitleDto titleDto = new TitleDto("TestTitleSave", "Brajan Brown", 1982);

        //When
        Title savedTitle = libraryControler.saveTitle(titleDto);
        int id = savedTitle.getId();


        Specimen newSpecimen = libraryControler.saveSpecimen(id);
        Title foundTitle = titleRepository.getOne(id);
        int idFound = foundTitle.getSpecimens().size();

        //Then
        Assert.assertEquals(2, idFound);

    }


}
