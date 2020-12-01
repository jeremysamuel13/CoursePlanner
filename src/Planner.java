/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #1
 */


/**
 * Planner class
 * Manages all the information of the planner
 */
public class Planner {

    //constant for the maximum courses
    final int MAX_COURSES = 50;

    //Table section titles stored in variable for easy reference
    final String tableSections = String.format("%-5s%-25s%-12s" +
                    "%-6s%-9s" +
                    "%-25s",
            "No.", "Course Name", "Department", "Code", "Section",
            "Instructor") + "\n" +
            "--------------------------------------------------------------" +
            "----------------------"
            + "\n";

    //array that holds the user's courses
    private Course[] courseList;

    //holds the last position for the exists() method when it returns true
    public int finderPos;

    //tracks the amount of courses in courseList. this is used in the size()
    // method
    private int listSize = 0;


    /**
     * Planner constructor
     *
     * Postcondition: This Planner has been initialized to an empty list
     * of Courses.
     */
    public Planner() {
        setCourseList();

    }

    /**
     * courseList setter
     */
    public void setCourseList() {
        courseList = new Course[MAX_COURSES];
    }

    /**
     * courseList getter
     *
     * @return returns courseList
     */
    public Course[] getCourseList() {
        return courseList;
    }

    /**
     * provides the size of courseList
     *
     * @return returns the size of courseList using listSize
     *
     * @custom.Precondition This Planner has been instantiated
     */
    public int size() {
        return listSize;
    }

    /**
     * adds a course to the planner at a certain position
     *
     * @param newCourse the specified course to be added
     * @param position  the position in the planner where the course
     *                  should be added
     * @throws IllegalArgumentException     exception for when the position is
     *                                      not within the array bounds
     *                                      (from 1 to 50)
     * @throws FullPlannerException         exception for when the planner is
     *                                      full
     * @throws CourseAlreadyExistsException exception for when the course
     *                                      already exists in the planner
     *
     * Preconditions: This Course object has been instantiated and 1 ≤
     *                                      position ≤ size() + 1. The number
     *                                      of Course objects in this Planner
     *                                      is less than MAX_COURSES.
     *
     * Postconditions: The new Course is now listed in the correct preference
     *                                      on the list. All Courses that were
     *                                      originally greater than or equal to
     *                                      position are moved back one
     *                                      position.
     */
    public void addCourse(Course newCourse, int position) throws
            IllegalArgumentException, FullPlannerException,
            CourseAlreadyExistsException {

        if (!exists(newCourse)) {
            if (size() < 50) {
                if ((position >= 1 && position <= (size() + 1))) {
                    if (size() > 0 && position != size() + 1 && position !=
                            MAX_COURSES) {
                        System.arraycopy(courseList, position - 1,
                                courseList, position, size() - position + 1);
                    }
                    courseList[position - 1] = newCourse;

                    //tracks list size (referred to in size() method)
                    listSize++;
                } else
                    throw new IllegalArgumentException("Position out of range. "
                            + "Make sure input is between 1 and " +
                            (size() + 1));
            } else
                throw new FullPlannerException("You have reached the maximum " +
                        "amount of courses");
        } else {
            throw new CourseAlreadyExistsException("Course already exists");
        }
    }

    /**
     * adds course to the planner without a specified position. it adds the
     * course to the position right after the last course.
     *
     * @param newCourse the specified course to be added
     * @throws FullPlannerException         exception for when the planner is
     *                                      full
     * @throws CourseAlreadyExistsException exception for when the course
     *                                      already exists in the planner
     *
     * Preconditions: This Course object has been instantiated. The number of
     *                                      Course objects in this Planner is
     *                                      less than MAX_COURSES.
     *
     * Postconditions: The new Course is now listed at the end of the list.
     */
    public void addCourse(Course newCourse) throws FullPlannerException,
            CourseAlreadyExistsException {
        if (!exists(newCourse)) {
            if (size() < MAX_COURSES) {
                addCourse(newCourse, size() + 1);

                //tracks list size (referred to in size() method)
                listSize++;
            } else {
                throw new FullPlannerException("You have reached the maximum " +
                        "amount of courses");
            }
        } else {
            throw new CourseAlreadyExistsException("Course already exists");
        }
    }

    /**
     * removes course at a certain position
     *
     * @param position the position in the planner in which the course
     *                 should be removed
     * @throws IllegalArgumentException exception for when the position is
     *                                  not within the array bounds
     *                                  (from 1 to 50)
     *
     * Preconditions: This Planner has been instantiated and 1 ≤ position ≤
     *                                  size().
     * Postconditions: The Course at the desired position has been removed.
     *                                  All Courses that were originally
     *                                  greater than or equal to position are
     *                                  moved backward one position.
     */
    public void removeCourse(int position) throws IllegalArgumentException {

        if ((position >= 1 && position <= size())) {
            if (size() - position - 1 >= 0)
                System.arraycopy(courseList, position,
                        courseList, position - 1,
                        size() - position + 1);

            courseList[size() - 1] = null;
            listSize--;


        } else
            throw new IllegalArgumentException("Position out of range. " +
                    "Make sure input is between 1 and " + (size() + 1));


    }

    /**
     * returns the course at a specified position in the planner
     *
     * @param position the position of the course that the method should return
     * @return returns the course at the specified position on the planner
     * @throws IllegalArgumentException exception for when the position is
     *                                  not within the bounds (from 1 to 50)
     *
     * Preconditions: The Planner object has been instantiated and 1 ≤
     *                                  position ≤ size().
     */
    public Course getCourse(int position) throws IllegalArgumentException {
        if ((position >= 1 && position <= size())) {
            return courseList[position - 1];
        } else
            throw new IllegalArgumentException(("Position out of range or " +
                    "does not exists. " +
                    "Make sure input is between 1 and " + (size() + 1) + " " +
                    "(inclusive)"));

    }

    /**
     * filters out courses in planner by department
     *
     * @param planner    the courses that should be searched
     * @param department the department that should be filtered
     *
     * Preconditions: This Planner object has been instantiated.
     *
     * Postconditions: Displays a neatly formatted table of each course
     *                                  filtered from the Planner. Keep the
     *                                  preference numbers the same
     */
    public static void filter(Planner planner, String department) {
        String filterString = planner.tableSections;

        for (int l = 1; l < planner.size() + 1; l++) {
            if (planner.getCourse(l).getDepartment().
                    equalsIgnoreCase(department)) {
                filterString += String.format
                        ("%-5d%-25s%-12s%-6d%-9s%-25s", l,
                                planner.getCourse(l).getName(),
                                planner.getCourse(l).getDepartment(),
                                planner.getCourse(l).getCode(),
                                perpendZero(planner.getCourse(l).getSection()),
                                planner.getCourse(l).getInstructor()) + "\n";

            }
        }
        System.out.println(filterString);
    }


    /**
     * checks if a course already exists in the planner
     *
     * @param course the course that we are trying to see exists
     * @return returns true if course exists and false if it does not
     *
     * Precondition: This Planner and Course has both been instantiated.
     */
    public boolean exists(Course course) {
        for (int m = 0; m < size(); m++) {
            if (courseList[m].equals(course)) {
                finderPos = m;
                return true;
            }
        }
        return false;
    }

    /**
     * creates a deep copy of the planner
     *
     * @return the copied planner
     *
     * Precondition: This Planner has been instantiated.
     */
    public Object clone() {
        Planner backup = new Planner();
        for (int n = 1; n < size() + 1; n++) {
            backup.addCourse((Course) getCourse(n).clone(), n);
        }
        return backup;

    }

    /**
     * prints all courses in planner
     *
     * Precondition: This Planner has been instantiated.
     */
    public void printAllCourses() {
        if (size() == 0) {
            System.out.println("No Courses Added");
        } else {
            System.out.println(toString());
        }

    }

    /**
     * creates a formatted tabular string representation of the planner
     *
     * @return returns the formatted tabular string representation of the
     * planner
     */
    public String toString() {
        String plannerString;
        plannerString = tableSections;
        for (int o = 1; o < size() + 1; o++) {
            plannerString += String.format
                    ("%-5d%-25s%-12s%-6d%-9s%-25s", o,
                            getCourse(o).getName(),
                            getCourse(o).getDepartment(),
                            getCourse(o).getCode(),
                            perpendZero(getCourse(o).getSection()),
                            getCourse(o).getInstructor()) + "\n";
        }
        return plannerString;
    }

    /**
     * converts section (byte) into a string and adds a zero before it if it
     * is a single digit section.
     *
     * @param section the course section as a byte
     * @return the section number as a string. if the section had a single
     * digit, there would be a zero added to the section number. If not, the
     * section number is returned as is
     */
    public static String perpendZero(byte section) {
        String sectionToString = "" + section;
        int length = sectionToString.length();

        if (length == 1) {
            sectionToString = "0" + sectionToString;
        }
        return sectionToString;


    }


}