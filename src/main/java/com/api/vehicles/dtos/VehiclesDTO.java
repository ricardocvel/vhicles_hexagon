package com.api.vehicles.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class VehiclesDTO {

    @NotBlank
    private String vehicleName;
    
    @NotBlank
    private String vehicleDesc;

    @NotNull
    private Integer vehicleType;

    @NotBlank
    @Size(max = 7)
    private String vehiclePlate;

}
