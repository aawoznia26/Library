package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Rider;
import com.crud.kodillalibrary.domain.RiderDto;
import org.springframework.stereotype.Component;

@Component
public class RiderMapper {

    public Rider mapToRider(final RiderDto riderDto) {
         Rider rider = new Rider(
                riderDto.getName(),
                riderDto.getLastName(),
                riderDto.getEnrollmentDate()
        );
        if(riderDto.getUuid() != null) {
            rider.setUuid(riderDto.getUuid().toString());
        }
        return rider;
    }

    public RiderDto mapToRiderDto(final Rider rider) {
        return new RiderDto(
                rider.getName(),
                rider.getLastName(),
                rider.getEnrollmentDate()
        );
    }

}
