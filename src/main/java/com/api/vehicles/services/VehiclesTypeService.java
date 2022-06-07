package com.api.vehicles.services;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.api.vehicles.models.VehiclesTypeModel;
import com.api.vehicles.repositories.VehiclesTypeRepository;

@Service
public class VehiclesTypeService {
    
    final VehiclesTypeRepository vehiclesTypeRepository;

    public VehiclesTypeService(VehiclesTypeRepository vehiclesTypeRepository) {
        this.vehiclesTypeRepository = vehiclesTypeRepository;
    }

    public Optional<VehiclesTypeModel> findById(Integer id) {
        return vehiclesTypeRepository.findById(id);
    }

    public Page<VehiclesTypeModel> findAll(Pageable pageable) {
        return vehiclesTypeRepository.findAll(pageable);
    }

    @Transactional
    public Object save(VehiclesTypeModel vehiclesTypeModel) {
        return vehiclesTypeRepository.save(vehiclesTypeModel);
    }

    public boolean existsByvehicleTypeName(String vehicleTypeName) {
        return vehiclesTypeRepository.existsByvehicleTypeName(vehicleTypeName);
    }

    @Transactional
    public void delete(VehiclesTypeModel vehiclesTypeModel) {
        vehiclesTypeRepository.delete(vehiclesTypeModel);
    }

    public Optional<VehiclesTypeModel> findByvehicleTypeName(String vehicleTypeName) {
        return vehiclesTypeRepository.findByvehicleTypeName(vehicleTypeName);
    }

}
