package com.api.vehicles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.vehicles.models.VehiclesTypeModel;

@Repository
public interface VehiclesTypeRepository extends JpaRepository<VehiclesTypeModel, Integer> {

    boolean existsByvehicleTypeName(String vehicleTypeName);

    Optional<VehiclesTypeModel> findByvehicleTypeName(String vehicleTypeName);
    
}
