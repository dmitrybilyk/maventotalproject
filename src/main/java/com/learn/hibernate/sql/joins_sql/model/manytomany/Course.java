package com.learn.hibernate.sql.joins_sql.model.manytomany;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="COURSE")
public class Course {

    private long courseId;
    private String courseName;

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    @Id
    @GeneratedValue
    @Column(name="COURSE_ID")
    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Column(name="COURSE_NAME", nullable=false)
    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}