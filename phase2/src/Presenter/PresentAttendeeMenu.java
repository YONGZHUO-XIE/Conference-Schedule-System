package Presenter;

/**
 * Present function for AttendeeMenu
 * Contains all print message for AttendeeMenu
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentAttendeeMenu {

    /**
     * Presenter function for AttendeeMenu
     * This contains the basic instruction for AttendeeMenu
     */
    public void presentBasicCommand(){
        System.out.println("Enter 1 to check message inbox. \nEnter 2 to check message outbox. \nEnter 3 to" +
                " check available event. \nEnter 4 to sign up for a event. \nEnter 5 to check registered " +
                "event. \nEnter 6 to cancel a registered event. \nEnter 7 to check friend list." +
                " \nEnter 8 to send a message. \nEnter 9 to send a user request."  +
                "\nEnter 10 to download the schedule you have. \nEnter 11 to check your vip status \nEnter e to exit");
    }

    /**
     * Presenter function for AttendeeMenu
     * Ask answer from Attendee base on vary questions
     * @param command different commands for different questions
     */
    public void presentAskInfo(int command){
        if (command == 1){
            System.out.println("Please enter the event id that you wish to sign up");
        }
        else if (command == 2){
            System.out.println("Please enter the event id that you wish to cancel.");
        }
        else if (command == 3){
            System.out.println("Enter the content of the message.");
        }
        else if (command == 4){
            System.out.println("Enter the user id that you are sending message to");
        }
        else if (command == 5){
            System.out.println("You are a VIP member. Do you want to cancel your VIP status?");
            System.out.println("Enter y if you want to. Enter any other key to exit.\n");
        }
        else if (command == 6){
            System.out.println("You are not a vip. Do you want to request for VIP status?");
            System.out.println("Enter the special command if you want to.\nEnter e to exit.");
        }
    }

    /**
     * Presenter function for AttendeeMenu
     * Present success information to Attendee
     * @param command different commands for different success information
     */
    public void presentSuccessInfo(int command){
        if (command == 1){
            System.out.println("Congratulations. You have signed up successfully.\n");
        }
        else if (command == 2){
            System.out.println("This event has been removed from your registered event list.\n");
        }
        else if (command == 3){
            System.out.println("This message has been sent successfully.\n");
        }
        else if (command == 4){
            System.out.println("You have successfully submitted a request.\n");
        }
        else if (command == 5){
            System.out.println("Successfully requested for VIP.\n");
        }
        else if (command == 6){
            System.out.println("You have canceled your VIP status.\n");
        }
    }

    /**
     * Presenter function for AttendeeMenu
     * Present fail information to Attendee
     * @param command different commands for different fail information
     */
    public void presentFailInfo(int command){
        if (command == 1){
            System.out.println("Sorry, you have already signed up for this event.\n");
        }
        else if (command == 2){
            System.out.println("Sorry, the event id that you enter is invalid.\n");
        }
        else if (command == 3){
            System.out.println("Sorry, you haven't sign up this event yet.\n");
        }
        else if (command == 4){
            System.out.println("Invalid recipient id. This id is not in your friendlist.\n");
        }
        else if (command == 5){
            System.out.println("Invalid input. Enter y if you want to cancel vip.\n");
        }
        else if (command == 6){
            System.out.println("Invalid input. Please enter the correct special command.\n");
        }
        else if (command == 7){
            System.out.println("Invalid command input.\n");
        }
    }
}
