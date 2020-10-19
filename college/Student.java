/*
Assignment number: 8.3
File name: Student.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */
package college;

import linkedList.*;

/** 
 * Represents a student.
 */
public class Student {
	
	private int sid;                             
	private String name;                         
	private LinkedList<CourseTaken> courseList;
	
	/** 
	 * Constructs a new student with the given sid and name, and an empty course list.
	 * @param sid  the student's sid
	 * @param name the student's name
	 */
	public Student(int sid, String name) {
		this.sid = sid;
		this.name = name;
		courseList = new LinkedList<CourseTaken>();
	}
	
	/** 
	 * Returns the sid of this student.
	 * @return the sid of this student.
	 */
	public int getSid() {
		return sid;
	}
	
	/** 
	 * Returns the name of this student.
	 * @return the name of this student.
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Adds the given course and grade to the course list of this student.
	 * @param c     the course to add
	 * @param grade the grade in the added course 
	 */
	public void addCourse(Course c, int grade) {

		if (tookCourse(c)){                         // check if he already took this course
			changeGrade(c,grade);   				// if does, change only the grade.
			return;
		}

		courseList.add(new CourseTaken(c,grade));
	}

	/**
	 * Change the grade of this student in exist course.
	 * @param c the course that the need to be change
	 * @param grade the grade to change.
	 */
	public void changeGrade (Course c, int grade) {

		ListIterator<CourseTaken> l1 = courseList.iterator();

		while (l1.hasNext()) {
			CourseTaken c1 = l1.next();
			if (c1.getCourse().getCid() == c.getCid()) {
				c1.setGrade(grade);
			}
		}
	}

	/**
	 * Remove the given course from this student course list.
	 * if the course does not exist in the list - do noting
	 * @param c the course to remove
	 */
	public void removeCourse (Course c) {

		ListIterator<CourseTaken> l1 = courseList.iterator();
		while (l1.hasNext()) {
			CourseTaken current = l1.next();
			if (current.getCourse().getCid() == c.getCid()) {      //check if the current course if the one to remove
				courseList.remove(current);							//if does, remove it.
			}
		}
	}
	
	/** 
	 * Returns the grade that this student got in the given course, 
	 *  or -1 if the course was not taken by this student.
	 * @param c - the returned grade will be the grade in this course.
	 * @return the grade that this student got in the given course
	 */
	public int gradeInCourse(Course c) {

		if (tookCourse(c)) {
			ListIterator<CourseTaken> l1 = courseList.iterator();

			while (l1.hasNext()) {
				CourseTaken c1 = l1.next();
				if (c1.getCourse().getCid() == c.getCid()) {     //check if the current course if the one that the user want the grade of
					return c1.getGrade();						  // if does, return the grate
				}
			}
		}
		return -1;
	}
	
	/** 
	 * Returns true if this student took the given course, false otherwise.
	 * @param c  the course we want to know whether or not the student took.
	 * @return true if this student took the given course, false otherwise.
	 */
	public boolean tookCourse(Course c) {

		ListIterator<CourseTaken> l1 = this.courseList.iterator();
		while (l1.hasNext()) {
			CourseTaken c1 = l1.next();
			if (c1.getCourse().getCid() == c.getCid()) {    // Check if the current course if the one that the user ask about
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Prints this student, all the courses that s/he took, and the grade point average.
	 */
	public void studentReport() {

		ListIterator<CourseTaken> l1 = courseList.iterator();
		double sum = 0;
		int count = 0;

		System.out.println(this);                   // print the details of the student
		while (l1.hasNext()) {                      // print each course of him
			CourseTaken c = l1.next();
			System.out.println(c);
			sum += c.getGrade();					// sum up the grades
			count++;								// count the courses number
		}
		if (count == 0) {
			System.out.println("The student didn't took any courses.");  // handel if he didn't took any courses.
			return;
		}
		System.out.println("Average grade: " + sum/count);    // print his average
	}
	
	/**
	 * Textual representation of this student.
	 */
	public String toString() {
		return "Student " + sid + ": " + name;
	}

	public static void main (String [] args) {

		Course c2 = new Course(1,"bla lba");
		Course c1 = new Course(2,"bla lba");
		Course c3 = new Course(4,"bla lba");
		Student s1 = new Student(2,"Yoad");
		s1.addCourse(c2,100);
		s1.addCourse(c1,95);
		s1.addCourse(c3,82);
		s1.studentReport();
		s1.addCourse(c2,90);
		s1.studentReport();
	}
}