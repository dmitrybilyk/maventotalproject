package com.learn.hibernate.sql.joins_sql.model.onetoone.casewithownkeys;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 9:41
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;

@Entity
@Table(name="EMPLOYEEDETAIL")
public class EmployeeDetail {

    @Id
    @GeneratedValue
//    @Column(name="employee_id", unique=true, nullable=false)
//    @GeneratedValue(generator="gen")
//    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="employee"))
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @OneToOne
    @JoinColumn(name = "employee_employee_id")
    private Employee employee;

    public EmployeeDetail() {

    }

    public EmployeeDetail(String street, String city, String state, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long employeeId) {
        this.id = employeeId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
