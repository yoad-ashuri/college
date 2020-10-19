/** Tests some of the services of the College class. */
package college;
public class HogwartsV2 {
	
	public static void main(String []args) {

		//College.java functions tests by order

		System.out.println("***********constructor test***********");
		// constructor test
		College hogwarts = new College("Hogwarts School of Witchcraft and Wizardry");

		System.out.println();
		System.out.println("***********addCourse test***********");
		// addCourse() test
		hogwarts.addCourse(0,"Discrete Math");
		hogwarts.addCourse(1,"Defence Against The Dark Arts");
		hogwarts.addCourse(120,"Calculus"); // nobody attends this course, obviously ***
		hogwarts.addCourse(2,"Charms");
		hogwarts.addCourse(3,"Dark Arts");
		hogwarts.addCourse(4,"Astronomy");
		hogwarts.addCourse(2,"Charms");  //adds course that already exists, should do nothing ***
		hogwarts.addCourse(54,"Flying");
		hogwarts.addCourse(6,"Herbology");

		System.out.println();
		System.out.println("***********courseList test***********");
		// courseList() test
		System.out.println();
		hogwarts.coursesList();
		System.out.println("should not print charms twice**");

		System.out.println();
		System.out.println("***********removeCourse test***********");
		// removeCourse() test      testing: removing first, last, somewhere in the middle, non-existing.
		hogwarts.removeCourse(0);
		hogwarts.removeCourse(1);
		hogwarts.removeCourse(6);
		hogwarts.removeStudent(86);
		System.out.println();
		hogwarts.coursesList();    // Prints the courses

		System.out.println();
		System.out.println("***********getCourse test***********");
		// getCourse() test      testing: getting existing course, getting non-existing course, getting course we deleted.
		System.out.println();
		System.out.println("Shoud print Charms: " + hogwarts.getCourse(2));
		System.out.println("Shoud print Flying: " + hogwarts.getCourse(6));
		System.out.println("Shoud print null: " + hogwarts.getCourse(7));

		System.out.println();
		System.out.println("***********addStudent test***********");
		// addStudent() test
		hogwarts.addStudent(0,"Draco Malfoy");
		hogwarts.addStudent(1,"Harry Potter");
		hogwarts.addStudent(15,"Avner Halevy");
		hogwarts.addStudent(2,"Hermione Granger");
		hogwarts.addStudent(42,"Yossi Shamai");
		hogwarts.addStudent(3,"Gregory Goyle");
		hogwarts.addStudent(4,"Ron Weasley");
		hogwarts.addStudent(2,"Hermione Granger"); //adds student that already exists, should do nothing ***
		hogwarts.addStudent(55,"Neville longbottom");
		hogwarts.addStudent(6,"Seamus Finnigan");
		hogwarts.addStudent(7,"Vincent Crabble");

		System.out.println();
		System.out.println("***********studentsList test***********");
		// studentsList() test
		System.out.println();
		hogwarts.studentsList();
		System.out.println("should not print Hermione twice**");
		System.out.println("we added Avner, who will not take any course**");

		System.out.println();
		System.out.println("***********removeStudent test***********");
		// removeStudent() test      testing: removing first, last, somewhere in the middle, non-existing.
		hogwarts.removeStudent(3);
		hogwarts.removeStudent(8);
		hogwarts.removeStudent(42);
		hogwarts.removeStudent(0);
		hogwarts.removeStudent(7);
		System.out.println();
		hogwarts.studentsList();    // Prints the students

		//the test for getStudent() is down in part 2..
		System.out.println();
		System.out.println("***********addCourseTaken test***********");
		// addCourseTaken() test
		//testing: existing courses to existing student, non-e courses to e students, e courses to non-e students, non to non.
		hogwarts.addCourseTaken(1,2, 75);  
		hogwarts.addCourseTaken(1,3, 88);
		hogwarts.addCourseTaken(1,4,98);  
		hogwarts.addCourseTaken(1,54,83);
		hogwarts.addCourseTaken(2,2,100);  
		hogwarts.addCourseTaken(2,3, 98);
		hogwarts.addCourseTaken(2,4, 98);
		hogwarts.addCourseTaken(2,54, 100);
		hogwarts.addCourseTaken(4,2, 65);                              
		hogwarts.addCourseTaken(4,54, 60); 
		hogwarts.addCourseTaken(55,2, 85);                              
		hogwarts.addCourseTaken(55,3, 79); 
		hogwarts.addCourseTaken(55,54, 100); 
		hogwarts.addCourseTaken(6,54, 93);                              
		hogwarts.addCourseTaken(6,3, 87);  //all until now existing to existing

		hogwarts.addCourseTaken(0,54, 93);
		hogwarts.addCourseTaken(1,0, 93);
		hogwarts.addCourseTaken(0,0, 93);// non existing

		hogwarts.addCourseTaken(1,3, 54); // changes the grade of Harry in DarkArts***

		System.out.println();
		System.out.println("***********studentReport test***********");
		// studentReport() test      testing:existing, nonexisting students
		System.out.println();	
		hogwarts.studentReport(1);	
		System.out.println();	
		hogwarts.studentReport(2); 
		System.out.println();
		hogwarts.studentReport(4);//all until now existing	
		System.out.println();	
		hogwarts.studentReport(0);// non existing   // should print "no such student***"
		System.out.println();	
		hogwarts.studentReport(15); // should print "no courses taken yet"***

		System.out.println();
		System.out.println("*****new******testing removing course from all students*******new****");
		System.out.println("removing charms**");
		hogwarts.removeCourse(2); // removes charms course***
		System.out.println();	
		hogwarts.studentReport(1);	
		System.out.println();	
		hogwarts.studentReport(2); 
		System.out.println();
		hogwarts.studentReport(4);//all until now existing	
		System.out.println();	
		hogwarts.studentReport(0);// non existing

		System.out.println();
		System.out.println("***********courseReport test***********");
		// courseReport() test      testing:existing, nonexisting courses
		hogwarts.courseReport(2);	//course we deleted
		System.out.println();	
		hogwarts.courseReport(3); 
		System.out.println();
		hogwarts.courseReport(4);	
		System.out.println();	
		hogwarts.courseReport(54);	//all until now existing
		System.out.println();
		hogwarts.courseReport(0);	// non existing  //should print "no such course"***
		System.out.println();	
		hogwarts.courseReport(120);	//should print "no student took this course"***
		System.out.println();	
	
		System.out.println();
		System.out.println("***********printSize test***********");
		// printSize() test      testing:existing, nonexisting courses
		System.out.println();	
		hogwarts.printSize(2);
		hogwarts.printSize(3);
		hogwarts.printSize(4);
		hogwarts.printSize(54);	//all until now existing
		hogwarts.printSize(0);	// non existing


		//the test for studentsWhoTook() is down in part 2..


		System.out.println();
		System.out.println("***********topPerfomerReport test***********");
		// topPerfomerReport() test      testing:existing, nonexisting courses
		System.out.println();	
		hogwarts.topPerfomerReport(2);
		hogwarts.topPerfomerReport(3);
		hogwarts.topPerfomerReport(4);
		hogwarts.topPerfomerReport(54);	//all until now existing
		hogwarts.topPerfomerReport(0);	// non existing


		// // For this part change getStudent() and studentsWhoTook() in College.java to public

		// System.out.println();
		// System.out.println("************ Second Part *************");
		// System.out.println();	
		// System.out.println("************getStudent test*************");	
		// hogwarts.getStudent(6).addCourse(hogwarts.getCourse(2),85);
		// hogwarts.getStudent(6).studentReport();
		// System.out.println();
		// hogwarts.courseReport(4);
		// System.out.println();

		// System.out.println("Grade : " + hogwarts.getStudent(6).gradeInCourse(hogwarts.getCourse(2)));

		// System.out.println("Took Course : " + hogwarts.getStudent(6).tookCourse(hogwarts.getCourse(2)));
		// System.out.println("Took Course : " + hogwarts.getStudent(4).tookCourse(hogwarts.getCourse(3)));

		// System.out.println();
		// System.out.println("************studentsWhoTook test*************");	
		// System.out.println(hogwarts.studentsWhoTook(hogwarts.getCourse(54)));//existing course
		// System.out.println(hogwarts.studentsWhoTook(hogwarts.getCourse(6)));//non existing course
	}
}