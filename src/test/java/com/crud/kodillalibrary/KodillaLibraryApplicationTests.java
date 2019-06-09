package com.crud.kodillalibrary;

import com.crud.kodillalibrary.controller.LibraryControler;
import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mapper.ReaderMapper;
import com.crud.kodillalibrary.mapper.RentMapper;
import com.crud.kodillalibrary.mapper.SpecimenMapper;
import com.crud.kodillalibrary.mapper.TitleMapper;
import com.crud.kodillalibrary.repository.ReaderRepository;
import com.crud.kodillalibrary.repository.RentRepository;
import com.crud.kodillalibrary.repository.SpecimenRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class KodillaLibraryApplicationTests {

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    SpecimenRepository specimenRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    ReaderMapper readerMapper;

    @Autowired
    SpecimenMapper specimenMapper;

    @Autowired
    RentMapper rentMapper;

    @Autowired
    TitleMapper titleMapper;

    @Autowired
    LibraryControler libraryControler;

    @Test
    public void testRiderSave() {
        //Given
        ReaderDto readerDto = new ReaderDto("Alina", "Nawrot", LocalDate.of(2019, 5, 10));

        //When
        int id =libraryControler.saveReader(readerDto);

        //Then
        Reader foundReader = readerRepository.getOne(id);
        Assert.assertNotEquals(null, foundReader);

    }

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

    @Test
    public void testSpecimenSave() {
        //Given
        TitleDto titleDto = new TitleDto("TestSpecimenSave", "Mikołaj Makowiecki", 1997);

        //When
        int id = libraryControler.saveTitle(titleDto);

        Specimen newSpecimen = specimenRepository.getOne(libraryControler.saveSpecimen(id));
        Title foundTitle = titleRepository.getOne(id);
        int idFound = foundTitle.getSpecimens().size();

        //Then
        Assert.assertEquals(2, idFound);

    }

    @Test
    public void testMarkSpecimenAsRent() {

        //Given
        TitleDto titleDto = new TitleDto("TestMarkSpecimenAsAvailable", "Joanna Podgórska", 2019);

        //When
        int titleId = libraryControler.saveTitle(titleDto);

        Specimen savedSpecimen = specimenRepository.getOne(libraryControler.saveSpecimen(titleId));
        int specimenId = savedSpecimen.getId();

        libraryControler.markSpecimenAsRent(specimenId);

        //Then
        Assert.assertEquals(SpecimenStatus.RENT, specimenRepository.getOne(specimenId).getStatus());

    }

    @Test
    public void testGetAvailableSpecimensCount() {

        //Given
        TitleDto titleDto = new TitleDto("TestMarkSpecimenAsAvailable", "Joanna Podgórska", 2019);

        //When
        int titleId = libraryControler.saveTitle(titleDto);
        Title savedTitle = titleRepository.getOne(titleId);

        libraryControler.saveSpecimen(titleId);
        libraryControler.saveSpecimen(titleId);
        libraryControler.saveSpecimen(titleId);
        Specimen specimenToMarkAsRent = savedTitle.getSpecimens().stream().findAny().get();
        libraryControler.markSpecimenAsRent(specimenToMarkAsRent.getId());

        int availableSpecimenCounter = libraryControler.getAvailableSpecimensCount(titleId);

        //Then
        Assert.assertEquals(3, availableSpecimenCounter);

    }

    @Test
    public void testRentBook() {

        //Given
        TitleDto titleDto = new TitleDto("TestMarkSpecimenAsAvailable", "Joanna Podgórska", 2019);
        ReaderDto readerDto = new ReaderDto("Franciszka", "Kowalska", LocalDate.of(2019, 6, 9));
        Title savedTitle = titleRepository.getOne(libraryControler.saveTitle(titleDto));
        Reader savedReader = readerRepository.getOne(libraryControler.saveReader(readerDto));

        //When
        Specimen savedSpecimen = savedTitle.getSpecimens().stream().findAny().get();
        int titleId = savedTitle.getId();

        Rent rent = rentMapper.mapToRent(libraryControler.rentBook(readerMapper.mapToReaderDto(savedReader), titleId));

        //Then
        Assert.assertEquals(SpecimenStatus.RENT, savedSpecimen.getStatus());
        Assert.assertEquals(LocalDate.now(), rent.getRentDate());


    }

    @Test
    public void testReturnBook() {

        //Given
        TitleDto titleDto = new TitleDto("TestMarkSpecimenAsAvailable", "Joanna Podgórska", 2019);
        ReaderDto readerDto = new ReaderDto("Franciszka", "Kowalska", LocalDate.of(2019, 6, 9));
        Title savedTitle = titleRepository.getOne(libraryControler.saveTitle(titleDto));
        Reader savedReader = readerRepository.getOne(libraryControler.saveReader(readerDto));

        Specimen savedSpecimen = savedTitle.getSpecimens().stream().findAny().get();
        int titleId = savedTitle.getId();

        int rentId = libraryControler.rentBook(readerMapper.mapToReaderDto(savedReader), titleId).getId();
        Rent rent = rentRepository.getOne(rentId);

        //When
        libraryControler.returnBook(rentId);

        //Then
        Assert.assertEquals(SpecimenStatus.AVAILABLE, savedSpecimen.getStatus());
        Assert.assertEquals(LocalDate.now(), rent.getRentDate());


    }


}
