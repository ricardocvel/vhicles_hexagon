package com.api.vehicles.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.vehicles.dtos.VehiclesTypeDTO;
import com.api.vehicles.models.VehiclesTypeModel;
import com.api.vehicles.services.VehiclesTypeService;

import lombok.var;


@RestController
@CrossOrigin(origins = "*", methods={RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST} )
@RequestMapping("vehiclesType")
public class VehicleTypeController {

    final VehiclesTypeService vehiclesTypeService;

    public VehicleTypeController(VehiclesTypeService vehiclesTypeService) {
        this.vehiclesTypeService = vehiclesTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<VehiclesTypeModel>> getAllVehiclesType(
        @PageableDefault(page = 0, size = 10 ,sort = "vehicleTypeName", direction = Sort.Direction.ASC) 
        Pageable pageable
    ){
        return ResponseEntity.status(HttpStatus.OK).body(vehiclesTypeService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid VehiclesTypeDTO vehiclesTypeDTO){
       
        if(vehiclesTypeService.existsByvehicleTypeName(vehiclesTypeDTO.getVehicleTypeName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: o Typo informado já esta em uso!");
        }
        
        var vehiclesTypeModel = new VehiclesTypeModel();
        BeanUtils.copyProperties(vehiclesTypeDTO, vehiclesTypeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiclesTypeService.save(vehiclesTypeModel));
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getVehicle(@PathVariable(value = "id") Integer id){
        Optional<VehiclesTypeModel> vehicle = vehiclesTypeService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de veiculo não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getVehicle(@PathVariable(value = "name") String vehicleTypeName){
        Optional<VehiclesTypeModel> vehicleType = vehiclesTypeService.findByvehicleTypeName(vehicleTypeName);
        if (!vehicleType.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de veiculo não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicleType.get());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid VehiclesTypeDTO vehiclesTypeDTO){
        Optional<VehiclesTypeModel> vehicle = vehiclesTypeService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de veiculo não encontrado.");
        }
        var vehiclesTypeModel = new VehiclesTypeModel();
        BeanUtils.copyProperties(vehiclesTypeDTO, vehiclesTypeModel);
        vehiclesTypeModel.setVeicleTypeid(vehicle.get().getVeicleTypeid());
        return ResponseEntity.status(HttpStatus.OK).body(vehiclesTypeService.save(vehiclesTypeModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Integer id){
        Optional<VehiclesTypeModel> vehicleType = vehiclesTypeService.findById(id);
        if (!vehicleType.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo Não encontrado.");
        }
        vehiclesTypeService.delete(vehicleType.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de veiculo deletado com sucesso.");
    }
    
}
