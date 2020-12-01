/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #1
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * PlannerManager class
 * Allows user to interact and manage their planner using a menu
 */
public class PlannerManager {

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {

        //scanner for user input
        Scanner scan = new Scanner(System.in);

        //planner that is to be interacted with
        Planner planner = new Planner();

        //backup planner
        Planner backupPlanner = new Planner();

        //tracks if the program is active or not. program is closed if the
        // user inputs "q"
        boolean active = true;

        String input;

        //Keeps track if backup has been created
        boolean backupCreated = false;

        //while loops allows for user to give input to planner until the user
        // inputs "q"
        while (active) {
            menu();

            System.out.println("Enter a selection: ");
            input = scan.nextLine();

            //switch statement to execute certain code depending on the input
            // . also converts all the inputs into lower case, so it doesn't
            // matter if the input is in upper case or lower case.
            switch (input.toLowerCase()) {

                //adds course
                case "a":

                    Course c = new Course();

                    //asks for name, department, code, section, instructor
                    // and position in the array
                    //Catches thrown exceptions for things such as invalid
                    // values.

                    System.out.println("Enter Course Name: ");
                    c.setName(scan.nextLine());

                    System.out.println("Enter Department Name: ");
                    try {
                        c.setDepartment(scan.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;

                    }

                    System.out.println("Enter Course Code: ");
                    try {
                        c.setCode(scan.nextInt());

                        //extra scan allows to prevent the following scan
                        //from being skipped
                        scan.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }


                    System.out.println("Enter Section Code: ");
                    try {
                        c.setSection(scan.nextByte());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    } catch (InputMismatchException e) {
                        System.out.println("Input should be between 1 and 127");
                        continue;
                    }

                    //extra scan allows to prevent the following scan
                    //from being skipped
                    scan.nextLine();

                    System.out.println("Enter Instructor: ");
                    c.setInstructor(scan.nextLine());


                    System.out.println("Enter Position: ");
                    int position;
                    try {
                        position = scan.nextInt();

                        //extra scan allows to prevent the following scan
                        //from being skipped
                        scan.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    //adds course to planner as long as the course doesn't
                    // already exist in the planner.
                    if (!planner.exists(c)) {
                        try {

                            if (position != 0)
                                planner.addCourse(c, position);
                            else
                                planner.addCourse(c);

                            System.out.println(c.getDepartment().toUpperCase() +
                                    " " + c.getCode() + "." +
                                    Planner.perpendZero(c.getSection()) +
                                    " successfully added to planner");
                        } catch (IllegalArgumentException | FullPlannerException
                                | CourseAlreadyExistsException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    } else {
                        System.out.println("Course Already Exists");
                    }

                    break;
                //Gets course and its details and converts it
                // into a string.
                //Catches exceptions thrown for invalid position input.
                case "g":

                    String getCourseString = planner.tableSections;
                    System.out.println("Enter the course position");
                    int getPos = scan.nextInt();

                    //extra scan allows to prevent the following scan
                    //from being skipped
                    scan.nextLine();

                    try {
                        getCourseString = getCourseString + String.format
                                ("%-5d%-25s%-12s%-6d%-9s%-25s", getPos,
                                        planner.getCourse(getPos).getName(),
                                        planner.getCourse(getPos).
                                                getDepartment(),
                                        planner.getCourse(getPos).getCode(),
                                        Planner.perpendZero(planner.getCourse
                                                (getPos).getSection()),
                                        planner.getCourse(getPos).
                                                getInstructor()) + "\n";


                        System.out.println(getCourseString);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    break;
                //removes course at specified position. catches exception if
                // course does not exists at the inputted position.
                case "r":

                    System.out.println("Enter position of course to remove");

                    try {
                        int removedPos = scan.nextInt();

                        //extra scan allows to prevent the following scan
                        //from being skipped
                        scan.nextLine();

                        System.out.println(planner.getCourse(removedPos).
                                getDepartment().toUpperCase() + " " +
                                planner.getCourse(removedPos).getCode() + "." +
                                Planner.perpendZero(planner.getCourse
                                        (removedPos).getSection()) +
                                " successfully removed from planner");

                        planner.removeCourse(removedPos);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    break;
                //Prints all courses
                case "p":
                    planner.printAllCourses();
                    break;
                //Prints a table of a filtered course list based on an inputted
                // department

                case "f":

                    System.out.println("Enter department to filter");

                    try {

                        Planner.filter(planner, scan.nextLine());


                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    break;
                //Checks if a course exists in the planner based on the
                // inputted information
                case "l":

                    Course courseFinder = new Course();

                    System.out.println("Enter Course Name: ");
                    courseFinder.setName(scan.nextLine());

                    System.out.println("Enter Department Name: ");
                    try {
                        courseFinder.setDepartment(scan.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;

                    }

                    System.out.println("Enter Course Code: ");
                    try {
                        courseFinder.setCode(scan.nextInt());

                        //extra scan allows to prevent the following scan
                        //from being skipped
                        scan.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }


                    System.out.println("Enter Section Code: ");
                    try {
                        courseFinder.setSection(scan.nextByte());

                        //extra scan allows to prevent the following scan
                        //from being skipped
                        scan.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.println("Enter Instructor: ");
                    courseFinder.setInstructor(scan.nextLine());

                    if (planner.exists(courseFinder)) {
                        System.out.println("Course exists in planner at " +
                                "position: " + (planner.finderPos + 1));
                    } else {
                        System.out.println("Course does not exist in planner");
                    }


                    break;
                //Prints the amount of courses in the planner
                case "s":

                    System.out.println("The planner has " + planner.size() +
                            " courses.");
                    break;
                //backs up planner
                case "b":
                    backupPlanner = (Planner) planner.clone();
                    backupCreated = true;
                    System.out.println("A backup of the planner has " +
                            "been created");
                    break;
                //prints all the courses in the backup. if no backup is
                // available, it informs the user and continues with the loop
                case "pb":

                    if (backupCreated)
                        backupPlanner.printAllCourses();
                    else
                        System.out.println("Backup not created");

                    break;
                //reverts to backup. if no backup is
                // available, it informs the user and continues with the loop
                case "rb":

                    if (backupCreated) {
                        planner = (Planner) backupPlanner.clone();
                        System.out.println("Successfully reverted to backup");
                    } else
                        System.out.println("Backup not created");

                    break;
                //quits the loop
                case "q":
                    System.out.println("Program terminating successfully...");
                    active = false;
                    break;
                //informs the user if the inputted selection is invalid and
                // then continues with the loop.
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    /**
     * Prints menu
     */
    public static void menu() {
        System.out.println("\n(A) Add Course\n(G) Get Course\n(R) Remove " +
                "Course\n(P)" +
                " Print Courses in Planner\n(F) Filter by Department Code\n" +
                "(L)" +
                " Look For Course\n(S) Size\n(B) Backup\n(PB) Print Courses " +
                "in Backup\n(RB) Revert to Backup\n(Q) Quit\n");
    }


}


