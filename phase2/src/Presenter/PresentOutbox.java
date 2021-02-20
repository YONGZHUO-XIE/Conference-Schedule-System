package Presenter;

/**
 * Present function for Outbox
 * Contains all print message for Outbox
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentOutbox {

    /**
     * Present all the messages that contain in the outbox
     * @param outbox the outbox that we are going to present
     */
    public void presentOutbox(String outbox){
        System.out.println(outbox);;
    }

    /**
     * Present all the commands that we can do toward this outbox
     */
    public void presentOutboxCommand(){
        System.out.println("Enter 1 to delete a message.(Note that this won't delete the message in recipient's inbox)\n" +
                "Enter e to exit");
    }

    /**
     * Present the command information for certain command
     */
    public void presentOutboxCommandInfo(){
        System.out.println("There is a number before each message.\n" +
                "Enter the number with the corresponding message that you want to delete.");
    }

    /**
     * Present the invalid information if the user enter the invalid command
     */
    public void presentOutboxInvalidInfo(){
        System.out.println("Invalid Input. Please try again.\n");
    }
}
