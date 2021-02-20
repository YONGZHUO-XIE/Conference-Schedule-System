package useCase;

/**
 * A class used to generate helper functions and decrease coupling
 *
 * @author Xuezhou
 * @version %I%, %G%
 */

public class Identification {

    /**
     * check type of users
     * @param userId the Id of user
     * @return a string that represents the type of an user
     */
    public String checkIdentity(String userId) {
        if (userId.charAt(0) == 'a') {
            return "Attendee";
        } else if (userId.charAt(0) == 'o') {
            return "Organizer";
        } else
            return "Speaker";
    }

}