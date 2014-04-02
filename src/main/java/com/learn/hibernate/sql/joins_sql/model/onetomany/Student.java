package com.learn.hibernate.sql.joins_sql.model.onetomany;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 25.07.12
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {

    private long studentId;
    private String studentName;
    private Set<Phone> studentPhoneNumbers = new HashSet<Phone>(0);

    public Student() {
    }

    public Student(String studentName, Set<Phone> studentPhoneNumbers) {
        this.studentName = studentName;
        this.studentPhoneNumbers = studentPhoneNumbers;
    }

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Column(name = "STUDENT_NAME", nullable = false, length = 100)
    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_PHONE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "PHONE_ID") })
    public Set<Phone> getStudentPhoneNumbers() {
        return this.studentPhoneNumbers;
    }

    public void setStudentPhoneNumbers(Set<Phone> studentPhoneNumbers) {
        this.studentPhoneNumbers = studentPhoneNumbers;
    }

}
