package com.api.vehicles.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// como o desafio pediu int nao coloquei UUID que é a chave primaria que eu usaria para microserviço
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VEHICLE")
public class VehiclesModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "vehicle_id", nullable = false, unique = true, length = 10)
    private Integer vehicleid;
    
    @Column(name = "vehicle_name", nullable = false, unique = false, length = 100)
    private String vehicleName;

    @Column(name = "vehicle_desc", nullable = true, length = 200)
    private String vehicleDesc;
    
    @Column(name = "vehicle_plate",nullable = true, unique = true, length = 20)
    private String vehiclePlate;

    @JoinColumn(name = "vehicle_type", referencedColumnName = "v_type_id")
    //@Fetch(FetchMode.SELECT)
	@ManyToOne(optional = true)
    private VehiclesTypeModel vehicleType;

}