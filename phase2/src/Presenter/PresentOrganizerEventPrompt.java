package Presenter;

/**
 * Present function for OrganizerEventPrompt
 * Contains all print message for OrganizerEventPrompt
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentOrganizerEventPrompt {

    /**
     * Contains Basic instruction for OrganizerEventPrompt
     */
    public void presentOrganizerEventCommand(){
        System.out.println("Enter 1 if you wish to cancel a event. Enter any other key to exit.");
    }

    /**
     * Ask for the event id from user
     */
    public void presentOrganizerEventIdCommand(){
        System.out.println("Please enter the ID of the event you want to cancel.");
    }

    /**
     * Present the feedback information to user
     * @param feedback the string that is going to be present
     */
    public void presentOrganizerEventFeedback(String feedback){
        System.out.println(feedback);;
    }
}
