package com.learn.mappings.manytomany.manyToManyUnidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="STUDENTS")
public class Student {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="STUDENT_ID")
	private long id;
	
	@Column(name="STUDENT_NAME")
	private String studentName;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
	@JoinTable(name="STUDENT_COURSE",joinColumns=@JoinColumn(name="STUDENT_ID"),inverseJoinColumns=@JoinColumn(name="COURSE_ID"))
	private Set<Course> courses = new HashSet<Course>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course){
		courses.add(course);
	}
}
