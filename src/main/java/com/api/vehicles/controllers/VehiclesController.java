package com.api.vehicles.controllers;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.vehicles.dtos.VehiclesDTO;
import com.api.vehicles.models.VehiclesModel;
import com.api.vehicles.models.VehiclesTypeModel;
import com.api.vehicles.services.VehiclesService;
import com.api.vehicles.services.VehiclesTypeService;

import java.util.Optional;

import lombok.var;

@RestController
@CrossOrigin(origins = "*", methods={RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST} )
@RequestMapping("/vehicles")
public class VehiclesController {
    
    @Autowired
    VehiclesTypeService vehiclesTypeService;

    final VehiclesService vehiclesService;

    public VehiclesController(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @GetMapping
    public ResponseEntity<Page<VehiclesModel>> getAllVehicles(
            @PageableDefault(page = 0, size = 10 ,sort = "vehicleid", direction = Sort.Direction.ASC) 
            Pageable pageable
        ){
        return ResponseEntity.status(HttpStatus.OK).body(vehiclesService.findAll(pageable));
    }

    //busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getVehicle(@PathVariable(value = "id") Integer id){
        Optional<VehiclesModel> vehicle = vehiclesService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
    }

    //busca por placa
    @GetMapping("/plate/{id}")
    public ResponseEntity<Object> getVehicle(@PathVariable(value = "id") String vehiclePlate){
        Optional<VehiclesModel> vehicle = vehiclesService.findByVehiclePlate(vehiclePlate);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid VehiclesDTO vehiclesDTO){
        Optional<VehiclesModel> vehicle = vehiclesService.findById(id);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado.");
        }
        var vehiclesModel = new VehiclesModel();
        Optional<VehiclesTypeModel> vehicleTypeModel = vehiclesTypeService.findById(vehiclesDTO.getVehicleType());
        if(!vehicleTypeModel.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipo de veiculo não encontrado!");
        }
        BeanUtils.copyProperties(vehiclesDTO, vehiclesModel);
        vehiclesModel.setVehicleType(vehicleTypeModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(vehiclesService.save(vehiclesModel));
    }
    
    
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid VehiclesDTO vehiclesDTO){
        
        //exemplo de  tratamento de erros que eu colocaria a mais
        // if(true){ 
        //     throw new NotBlanckPatos("Deu erro");
        // }

        if(vehiclesService.existsByVehiclePlate(vehiclesDTO.getVehiclePlate())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A placa do veiculo informado já esta em uso!");
        }
        var vehiclesModel = new VehiclesModel();
        Optional<VehiclesTypeModel> vehicleTypeModel = vehiclesTypeService.findById(vehiclesDTO.getVehicleType());
        if(!vehicleTypeModel.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipo de veiculo não encontrado!");
        }
        BeanUtils.copyProperties(vehiclesDTO, vehiclesModel);
        vehiclesModel.setVehicleType(vehicleTypeModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiclesService.save(vehiclesModel));
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Integer vehicleid){
        Optional<VehiclesModel> vehicle = vehiclesService.findById(vehicleid);
        if (!vehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado.");
        }
        vehiclesService.delete(vehicle.get());
        return ResponseEntity.status(HttpStatus.OK).body("Veiculo deletado com sucesso.");
    }

}

