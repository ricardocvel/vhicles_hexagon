package com.api.vehicles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.vehicles.models.VehiclesModel;

@Repository
public interface VehiclesRepository extends JpaRepository<VehiclesModel, Integer > {

    boolean existsByVehiclePlate (String vehiclePlate);

    Optional<VehiclesModel> findByVehiclePlate(String vehiclePlate);
    
}
