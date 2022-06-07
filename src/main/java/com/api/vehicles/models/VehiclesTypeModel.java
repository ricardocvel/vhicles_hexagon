package com.api.vehicles.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// como o desafio pediu int nao coloquei UUID que é a chave primaria que eu usaria para microserviço

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VEHICLE_TYPE")
public class VehiclesTypeModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "v_type_id", nullable = false, length = 10)
    private Integer VeicleTypeid;

    @Column(name = "vehicle_type_name", nullable = false, unique = false, length = 100)
    private String vehicleTypeName;

    @Column(name = "vehicle_type_desc", nullable = true, length = 200)
    private String vehicleTypeDesc;

}
