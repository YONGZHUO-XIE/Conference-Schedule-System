package Presenter;

/**
 * Present function for AttendeeMenu
 * Contains all print message for inbox
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentInbox {

    /**
     * Contains all the message in inbox
     * @param inbox the inbox that we are going to present
     */
    public void presentFeedback(String inbox){
        System.out.println(inbox);
    }

    /**
     * Present all the basic commands that in inbox
     */
    public void presentInboxCommand(){
        System.out.println("Enter 1 to mark a message as unread.(This will make the message appear under 'new message' next time you view inbox.)\n" +
                "Enter 2 to delete a message.\n" +
                "Enter e to exit.");
    }

    /**
     * Present all the commands that we can do toward inbox
     * @param command different commands for different commands
     */
    public void presentCommandInfo(int command){
        if (command == 1){
            System.out.println("There is a number before each unread message.\n" +
                    "Enter the number with the corresponding message that you want to mark as unread.");
        }
        else if (command == 2){
            System.out.println("There is a number before each message.\n" +
                    "Enter the number with the corresponding message that you want to delete.");
        }
    }

    /**
     * Present invalid information if user enter any invalid command
     */
    public void presentInvalidInfo(){
        System.out.println("Invalid input. Please try again.");
    }
}
