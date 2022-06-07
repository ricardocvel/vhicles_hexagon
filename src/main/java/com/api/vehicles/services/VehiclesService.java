package com.api.vehicles.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.vehicles.dtos.VehiclesDTO;
import com.api.vehicles.models.VehiclesModel;
import com.api.vehicles.models.VehiclesTypeModel;
import com.api.vehicles.repositories.VehiclesRepository;

@Service
public class VehiclesService {
    
    @Autowired 
    VehiclesTypeService vehiclesTypeService;

    final VehiclesRepository vehiclesRepository;

    public VehiclesService(VehiclesRepository vehiclesRepository) {
        this.vehiclesRepository = vehiclesRepository;
    }

    @Transactional
    public Object save(VehiclesModel vehiclesModel) {
        return vehiclesRepository.save(vehiclesModel);
    }

    @Transactional
    public void delete(VehiclesModel vehiclesModel) {
        vehiclesRepository.delete(vehiclesModel);
    }

    public Page<VehiclesModel> findAll(Pageable pageable) {
        return vehiclesRepository.findAll(pageable);
    }

    public Optional<VehiclesModel> findById(Integer vehicleid) {
        return vehiclesRepository.findById(vehicleid);
    }
    
    public Optional<VehiclesModel> findByVehiclePlate(String vehiclePlate) {
        return vehiclesRepository.findByVehiclePlate(vehiclePlate);
    }

	public boolean existsByVehiclePlate(String vehiclePlate) {
		return vehiclesRepository.existsByVehiclePlate(vehiclePlate);
	}

    // public VehiclesTypeModel setType(Integer id) {
    //     VehiclesTypeModel veiVehiclesTypeModel = vehiclesTypeService.findById(id);
    //     return veiVehiclesTypeModel;
    // }

}
