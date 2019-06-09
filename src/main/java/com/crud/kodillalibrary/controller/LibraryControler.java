package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mapper.ReaderMapper;
import com.crud.kodillalibrary.mapper.RentMapper;
import com.crud.kodillalibrary.mapper.SpecimenMapper;
import com.crud.kodillalibrary.mapper.TitleMapper;
import com.crud.kodillalibrary.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class LibraryControler {

    @Autowired
    private DbService dbService;

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private SpecimenMapper specimenMapper;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private RentMapper rentMapper;

    @RequestMapping(method = RequestMethod.POST, value = "saveTitle")
    public int saveTitle(@RequestBody TitleDto titleDto) {
        return dbService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveSpecimen")
    public int saveSpecimen(@RequestParam int titleId){
        return dbService.saveSpecimen(titleId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveReader")
    public int saveReader(@RequestBody ReaderDto readerDto) {
        return dbService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "markSpecimenAsAvailable")
    public SpecimenDto markSpecimenAsAvailable(@RequestParam int specimenId) {
        return specimenMapper.mapToSpecimenDto(dbService.markSpecimenAsAvailable(specimenId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "markSpecimenAsRent")
    public SpecimenDto markSpecimenAsRent(@RequestParam int specimenId) {
        return specimenMapper.mapToSpecimenDto(dbService.markSpecimenAsRent(specimenId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableSpecimensCount")
    public int getAvailableSpecimensCount(@RequestParam int titleId) {
        return dbService.getAvailableSpecimensCount(titleId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentBook")
    public RentDto rentBook(@RequestBody ReaderDto readerDto, @RequestParam int titleId) {
        return rentMapper.mapToRentDto(dbService.rentBook(readerMapper.mapToReader(readerDto),titleId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public RentDto returnBook(@RequestParam int rentId) {
        return rentMapper.mapToRentDto(dbService.returnBook(rentId));
    }

}
