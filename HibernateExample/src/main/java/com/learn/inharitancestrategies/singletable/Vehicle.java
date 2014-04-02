package com.learn.inharitancestrategies.singletable;

/**
 * Created by dmitry.bilyk on 3/25/14.
 */
import javax.persistence.*;

@Entity
@Table(name="VEHICLE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //Least normalisation strategy
@DiscriminatorColumn(
        name="VEHICLE_TYPE",
        discriminatorType=DiscriminatorType.STRING
)
public class Vehicle
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
