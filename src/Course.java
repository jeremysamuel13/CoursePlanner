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
 * Course class
 * Manage all the information of a course
 */
public class Course {

    private String name;
    private String department;
    private String instructor;
    private int code;
    private byte section;


    /**
     * Constructor for Course object
     */
    public Course() {
    }


    /**
     * Sets name of course
     *
     * @param name course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets course department
     *
     * @param department course department
     * @throws IllegalArgumentException when a given input is invalid
     *                                  (negative value, invalid course code,
     *                                  invalid department code, etc.)
     */
    public void setDepartment(String department)
            throws IllegalArgumentException {
        String deptNumCheck =
                department.replaceAll("[*a-zA-Z]", "");

        try {
            if (deptNumCheck.length() > 0) {
                throw new IllegalArgumentException("Invalid department input " +
                        "(should not have numbers and should be 3 letters)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Only a 3 lettered department " +
                    "could is valid.");
        }

        if (department.length() != 3)
            throw new
                    IllegalArgumentException("Invalid department input (should"
                    + " be 3 letters)");
        else
            this.department = department;
    }

    /**
     * Sets course instructor
     *
     * @param instructor course instructor
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Sets course code
     *
     * @param code course code
     * @throws IllegalArgumentException when a given input is invalid
     *                                  (negative value, invalid course code,
     *                                  invalid department code, etc.)
     */
    public void setCode(int code) throws IllegalArgumentException {
        String stringCode = Integer.toString(code);

        if (code < 0)
            throw new
                    IllegalArgumentException("Negative numbers are not valid");
        if (stringCode.length() != 3)
            throw new
                    IllegalArgumentException("Invalid course code (should be 3"
                    + " digits)");
        else
            this.code = code;
    }

    /**
     * Sets course section
     *
     * @param section course section
     * @throws IllegalArgumentException when a given input is invalid
     *                                  (negative value, invalid course code,
     *                                  invalid department code, etc.)
     */
    public void setSection(byte section) throws IllegalArgumentException {
        if (section < 1)
            throw new
                    IllegalArgumentException("Negative numbers are not valid");
        else
            this.section = section;
    }


    /**
     * Gets course name
     *
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets course department
     *
     * @return course department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Gets course instructor
     *
     * @return course instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Gets course code
     *
     * @return course code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets course section
     *
     * @return course section
     */
    public byte getSection() {
        return section;
    }

    /**
     * clones a course. edits made in clone will not affect the source course.
     *
     * @return returns a cloned version of a course
     */
    public Object clone() {

        Course newCourse = new Course();
        newCourse.setName(getName());
        newCourse.setInstructor(getInstructor());
        newCourse.setSection((getSection()));
        newCourse.setCode(getCode());
        newCourse.setDepartment(getDepartment());

        return newCourse;
    }


    /**
     * compares two courses and returns a boolean that indicates if the
     * courses are equivalent or not
     *
     * note: converts all the strings to lower case in order to detect
     * equivalence despite the letter casing.
     *
     * @param obj Course that is being compared
     * @return returns true if courses are equal and false if not
     */
    public boolean equals(Object obj) {
        if (obj instanceof Course) {

            Course targetCourse = (Course) obj;

            return (this.name.equalsIgnoreCase(targetCourse.name) &&
                    this.department.equalsIgnoreCase(targetCourse.department) &&
                    this.instructor.equalsIgnoreCase(targetCourse.instructor) &&
                    this.code == targetCourse.code &&
                    this.section == targetCourse.section);
        }
        return false;
    }

}
