package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.Rent;
import com.crud.kodillalibrary.domain.Specimen;
import com.crud.kodillalibrary.dto.ReaderDto;
import com.crud.kodillalibrary.dto.RentDto;
import com.crud.kodillalibrary.dto.SpecimenDto;
import com.crud.kodillalibrary.dto.TitleDto;
import com.crud.kodillalibrary.service.ReaderService;
import com.crud.kodillalibrary.service.RentService;
import com.crud.kodillalibrary.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class LibraryControler {

    @Autowired
    private RentService rentService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private ReaderService readerService;

    @RequestMapping(method = RequestMethod.POST, value = "saveTitle")
    public Long saveTitle(@RequestBody TitleDto titleDto) {
        return titleService.saveTitle(TitleDto.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveSpecimen")
    public Long saveSpecimen(@RequestBody SpecimenDto specimenDto){
        return titleService.saveSpecimen(titleService.mapToSpecimen(specimenDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveReader")
    public Long saveReader(@RequestBody ReaderDto readerDto) {
        return readerService.saveReader(ReaderDto.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "markSpecimenAsAvailable")
    public SpecimenDto markSpecimenAsAvailable(@RequestParam Long specimenId) {
        return Specimen.mapToSpecimenDto(titleService.markSpecimenAsAvailable(specimenId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "markSpecimenAsRent")
    public SpecimenDto markSpecimenAsRent(@RequestParam Long specimenId) {
        return Specimen.mapToSpecimenDto(titleService.markSpecimenAsRent(specimenId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableSpecimensCount")
    public Long getAvailableSpecimensCount(@RequestParam Long titleId) {
        return titleService.getAvailableSpecimensCount(titleId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rentBook/{titleId}")
    public RentDto rentBook(@RequestBody ReaderDto readerDto, @PathVariable Long titleId) {
        return Rent.mapToRentDto(rentService.rentBook(ReaderDto.mapToReader(readerDto),titleId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public RentDto returnBook(@RequestParam Long rentId) {
        return Rent.mapToRentDto(rentService.returnBook(rentId));
    }

}
