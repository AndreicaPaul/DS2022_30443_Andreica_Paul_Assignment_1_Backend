package com.example.first_swing_app.mapper;

import com.example.first_swing_app.dto.CreateDeviceDto;
import com.example.first_swing_app.models.MeteringDevice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeteringDeviceMapper {

    MeteringDeviceMapper INSTANCE = Mappers.getMapper(MeteringDeviceMapper.class);

    MeteringDevice convertToMeteringDevice(CreateDeviceDto createDeviceDto);
}
