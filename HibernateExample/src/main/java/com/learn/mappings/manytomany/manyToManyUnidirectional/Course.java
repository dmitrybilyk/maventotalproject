package com.learn.mappings.manytomany.manyToManyUnidirectional;

import javax.persistence.*;

@Entity(name="com.learn.mappings.manytomany.manyToManyUnidirectional.Course")
@Table(name="COURSES")
public class Course {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="COURSE_ID")
	private long id;

	@Column(name="COURSE_NAME")
	private String courseName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
