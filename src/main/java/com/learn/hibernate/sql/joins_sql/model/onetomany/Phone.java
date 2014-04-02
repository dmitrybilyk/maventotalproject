package com.learn.hibernate.sql.joins_sql.model.onetomany;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 25.07.12
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHONE")
public class Phone {

    private long phoneId;
    private String phoneType;
    private String phoneNumber;

    public Phone() {
    }

    public Phone(String phoneType, String phoneNumber) {
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue
    @Column(name = "PHONE_ID")
    public long getPhoneId() {
        return this.phoneId;
    }

    public void setPhoneId(long phoneId) {
        this.phoneId = phoneId;
    }

    @Column(name = "PHONE_TYPE", nullable = false, length=10)
    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Column(name = "PHONE_NUMBER", nullable = false, length=15)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
