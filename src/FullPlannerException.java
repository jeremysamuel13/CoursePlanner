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
 * Exception for when user tries to add a course to a planner that is already
 * full.
 */
public class FullPlannerException extends RuntimeException {
    public FullPlannerException(String message) {
        super(message);
    }


}
