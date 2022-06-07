package com.api.vehicles.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class VehiclesTypeDTO {
    
    @NotBlank
    private String vehicleTypeName; 

    @NotNull
    private String vehicleTypeDesc;

}
