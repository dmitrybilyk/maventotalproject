package com.learn.inharitancestrategies.joinedstrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;
import javax.persistence.Inheritance;  
import javax.persistence.InheritanceType;  
import javax.persistence.Table;  
  
@Entity(name="com.learn.inharitancestrategies.joinedstrategy.Vehicle")
@Table(name="VEHICLE")  
@Inheritance(strategy=InheritanceType.JOINED)//Highly normalized  
public class Vehicle   
{  
 @Id  
 @GeneratedValue  
 @Column(name="VEHICLE_ID")  
 private int vehicleId;  
   
 @Column(name="VEHICLE_NAME")  
 private String vehicleName;  
   
 public int getVehicleId() {  
  return vehicleId;  
 }  
 public void setVehicleId(int vehicleId) {  
  this.vehicleId = vehicleId;  
 }  
 public String getVehicleName() {  
  return vehicleName;  
 }  
 public void setVehicleName(String vehicleName) {  
  this.vehicleName = vehicleName;  
 }  
}  