/*
Assignment number: 8.2
File name: College.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */
package college;

import linkedList.*;

/**
 * Represents a college, and college management operations.
 * A college has courses, and students. Students take courses and get grades.
 * (See the Course, Student, and CourseTaken classes for more details).
 */
public class College {
	
	private static String nl =  System.getProperty("line.separator");

	private String name; // the name of this college
	private LinkedList<Course> courses;
	private LinkedList<Student> students;
	
	/**
	 * Constructs a new college, with empty student and course lists.
	 */
	public College(String name) {
		this.name = name;
		this.courses = new LinkedList<Course>();
		this.students = new LinkedList<Student>();
	}
	
	/** 
	 * Adds the given course to the course list of this college.
	 * @param cid   course id.
	 * @param title course title.
	 */
	public void addCourse(int cid, String title) {

		ListIterator<Course> l1 = courses.iterator();

		while (l1.hasNext()) {                     // Check if the course already exist.
			Course current = l1.next();
			if (current.getCid() == cid) {        // if does, do noting.
				return;
			}
		}

		Course newCourse = new Course(cid, title);
		courses.add(newCourse);


	}
	
	/**
	 * Prints a list of all the courses.
	 */
	public void coursesList() {
		System.out.println(courses);
	}

	/** 
	 * If the given course is in this college, removes it and returns true.
	 * Otherwise returns false.
	 * @param  cid the course to remove.
	 * @return True if the course was removed, false if there is no such course. 
	 */
	public boolean removeCourse(int cid) {

		Course toRemove = getCourse(cid);

		if (toRemove == null){                             // check if this course exist
			return false;
		}
		this.courses.remove(toRemove);                    // remove the course from the courses list.

		ListIterator <Student> l1 = students.iterator();  // remove the course from each student how took it.
		while (l1.hasNext()) {
			Student current = l1.next();
			if (current.tookCourse(toRemove)) {
				current.removeCourse(toRemove);
			}
		}

		return true;
	}
	
	// Returns the course that has the given id, or null if there is no such course.
	public Course getCourse(int cid) {

		ListIterator<Course> l1 = courses.iterator();
		while (l1.hasNext()) {
			Course c1 = l1.next();
			if (c1.getCid() == cid) {
				return c1;
			}
		}
		return null;
	}
	
	/** 
	 * Adds the given student to the students list of this college.
	 * @param sid   student id
	 * @param name  student name
	 */
	public void addStudent(int sid, String name) {

		ListIterator<Student> l1 = students.iterator();

		while (l1.hasNext()) {                     // Check if the student already exist.
			Student current = l1.next();
			if (current.getSid() == sid) {        // if does, do noting.
				return;
			}
		}
		students.add(new Student(sid,name));
	}
	
	/**
	 * Prints a list of all the students.
	 */
	public void studentsList() {
		System.out.println(students);
	}
	
	/** 
	 * If the given student is in this college, removes it and returns true.
	 * Otherwise returns false.
	 * @param  sid  the student's id.
	 * @return True if the student was removed, false if there is no such student. 
	 */
	public boolean removeStudent(int sid) {

		Student toRemove = getStudent(sid);
		if (toRemove == null){                  // Check if this student exist
			return false;
		}
		this.students.remove(toRemove);         // if does, remove him
		return true;
	}
	
	// Returns the student that has the given id, or null if there is no such student.
	private Student getStudent(int sid) {
		ListIterator<Student> l1 = students.iterator();
		while (l1.hasNext()) {
			Student s1 = l1.next();
			if (s1.getSid() == sid) {
				return s1;
			}
		}
		return null;
	}
	
	/** 
	 * Adds the given course to the course list of the given student.
	 * @param sid   student id
	 * @param cid   course id
	 * @param grade student's grade in the course 
	 */
	public void addCourseTaken(int sid, int cid, int grade) {

		Student s1 = getStudent(sid);
		s1.addCourse(getCourse(cid),grade);
	}
	
	/** 
	 * Prints the student report of the given student.
	 * See the Student class for more details.
	 * @param sid  student id
	 */
	public void studentReport(int sid) {

		if (getStudent(sid) == null) {
			System.out.println("No such a student.");
			return;
		}
		getStudent(sid).studentReport();
	}
	
	/**
	 * Prints a list of all the students who took the course with the given cid.
	 * @param cid  the course
	 */
	public void courseReport(int cid) {

		Course c1 = getCourse(cid);
		LinkedList <Student> tookCourse = studentsWhoTook(c1);
		System.out.println("Course report: " + c1.getTitle());
//		System.out.println("The Students how take " + c1 + " is:");

		if (tookCourse.getFirst() == null) {
			System.out.println("No one taking this course.");
			return;
		}

		ListIterator<Student> l1 = tookCourse.iterator();
		while (l1.hasNext()) {
			Student current = l1.next();
			System.out.println(current);
		}
	}
	
	/** 
	 * Prints the number of students in the given course
	 * @param cid  course id
	 */
	public void printSize(int cid) {
		Course toPrint = getCourse(cid);
		if (toPrint == null) {
			System.out.println("NO such a course.");
			return;
		}
		LinkedList<Student> took = studentsWhoTook(toPrint);
		System.out.println(toPrint + " has " + took.size() + " students");
	}
	
	// Returns a list of all the students who took the given course
	private LinkedList<Student> studentsWhoTook(Course c) {

		LinkedList<Student> tookCourse = new LinkedList<Student>();
		ListIterator<Student> l1 = students.iterator();

		while (l1.hasNext()) {
			Student s1 = l1.next();
			if (s1.tookCourse(c)){
				tookCourse.add(s1);
			}
		}


		return tookCourse;
	}
	
	/** 
	 * Prints the student with the highest grade in the given course.
	 * if there is several best student print them all.
	 * @param cid  course id
	 */
	public void topPerfomerReport(int cid) {

		Course c = getCourse(cid);                         // Check if there is Such a course
		if (c == null) {
			System.out.println("No such a course");
			return;
		}
		LinkedList<Student> tookC = studentsWhoTook(c);
		if (tookC.getFirst() == null) {                                  // Check if there is student in this course
			System.out.println("There is no student in this course.");
			return;
		}

		Student best = tookC.getFirst();
		ListIterator<Student> l1 = tookC.iterator();

		while (l1.hasNext()) {                                          // Check how got the highest grade
			Student current = l1.next();
			if (current.gradeInCourse(c) > best.gradeInCourse(c)) {
				best = current;
			}
		}
		LinkedList<Student> bestStudents = new LinkedList<Student>();
		bestStudents.add(best);

		ListIterator<Student> l2 = tookC.iterator();
		while (l2.hasNext()) {                             // Enter to the list of best student that have the same grade as the best one.
			Student current1 = l2.next();
			if (current1.gradeInCourse(c) == best.gradeInCourse(c)) {
				if (current1.getSid() == best.getSid()) {           // skip the one that already in the list (the one that i compere to).
					continue;
				}
				bestStudents.add(current1);
			}
		}

		System.out.println("The student How gets the highest grade in " + c + " is:");
		ListIterator<Student> l3 = bestStudents.iterator();                 // print all the best student list.
		while (l3.hasNext()) {
			Student current2 = l3.next();
			System.out.println(current2 + ": " + current2.gradeInCourse(c));
		}
	}

	/** 
	 * Returns the college name
	 * @return the college name 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A textual representation of this college, along with its courses and students.
	 */
	public String toString() {
		String str = name + nl;
		str += "courses:" + nl;
		str += courses.toString() + nl;
		str += "students:" + nl;
		str += students.toString() + nl;
		return str;
	}
}
