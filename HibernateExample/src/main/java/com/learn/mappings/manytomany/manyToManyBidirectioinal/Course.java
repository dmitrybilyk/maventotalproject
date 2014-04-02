package com.learn.mappings.manytomany.manyToManyBidirectioinal;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="COURSES")
public class Course {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="COURSE_ID")
	private long id;

	@Column(name="COURSE_NAME")
	private String courseName;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
	@JoinTable(name="STUDENT_COURSE",joinColumns=@JoinColumn(name="COURSE_ID"),inverseJoinColumns=@JoinColumn(name="STUDENT_ID"))
	private Set<Student> students = new HashSet<Student>();

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
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
