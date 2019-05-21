package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.*;
import com.crud.kodillalibrary.mapper.RiderMapper;
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
    private RiderMapper riderMapper;

    @RequestMapping(method = RequestMethod.POST, value = "saveTitle")
    public Title saveTitle(@RequestBody TitleDto titleDto) {
        return dbService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveSpecimen")
    public Specimen saveSpecimen(@RequestParam int titleId) {
        return dbService.saveSpecimen(titleId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveRider")
    public Rider saveRider(@RequestBody RiderDto riderDto) {
        return dbService.saveRider(riderMapper.mapToRider(riderDto));
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
    public RiderSpecimen rentBook(@RequestBody RiderDto riderDto, @RequestParam int titleId) {
        return dbService.rentBook(riderMapper.mapToRider(riderDto),titleId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public RiderSpecimen returnBook(@RequestParam int rentId) {
        return dbService.returnBook(rentId);
    }

}
