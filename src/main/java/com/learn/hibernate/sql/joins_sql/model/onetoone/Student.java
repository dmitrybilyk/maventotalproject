package com.learn.hibernate.sql.joins_sql.model.onetoone;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 25.07.12
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Student implements Serializable{
    @Id
    @GeneratedValue
    private long studentId;
    private String studentName;
    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    public Student() {
    }

    public Student(String studentName, Address studentAddress) {
        this.studentName = studentName;
        this.address = studentAddress;
    }

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Address getStudentAddress() {
        return this.address;
    }

    public void setStudentAddress(Address studentAddress) {
        this.address = studentAddress;
    }
}
