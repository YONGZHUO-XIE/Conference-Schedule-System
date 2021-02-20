package Presenter;

/**
 * Present function for OrganizerMenu
 * Contains all print message for OrganizerMenu
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentOrganizerMenu {

    /**
     * Present all the commands we can do in Organizer Menu
     */
    public void presentBasicCommand(){
        System.out.println("Enter 1 in check message inbox. \nEnter 2 to check message outbox. \n" +
                "Enter 3 to create a room. \nEnter 4 to create a new user. \nEnter 5 to schedule a new event. \n" +
                "Enter 6 to send a message to a single user. \nEnter 7 to send a group message. \n" +
                "Enter 8 to view all events you've scheduled. \nEnter 9 to check friend list. " +
                "\nEnter 10 to change the maximum number of attendees for an event. " +
                "\nEnter 11 to process User Request." +
                "\nEnter 12 to download the schedule you have." +
                "\nEnter e to exit");
    }

    /**
     * Ask organizer for inputs to order to achieve above commands
     * @param num different numbers for different commands
     */
    public void presentAskInfo(int num){
        if (num == 1){
            System.out.println("Enter a positive number that indicates the maximum capacity of the room");
        }
        else if (num == 2){
            System.out.println("Enter the content of the message.");
        }
        else if (num == 3){
            System.out.println("Enter the user id that you are sending message to");
        }
        else if (num == 4){
            System.out.println("Enter the id of the event that you are sending message to all of attendees.");
        }
        else if (num == 5){
            System.out.println("enter the id for the event that you want to change maximum number of attendees.");
        }
        else if (num == 6){
            System.out.println("input the maximum number of attendees you want to set for this event.");
        }
        else if (num == 7){
            System.out.println("Enter 1 to send the message to all attendees.\n" +
                    "Enter 2 to send the message to all speakers.\n" +
                    "Enter 3 to send the message to all attendees and speakers.\n" +
                    "Enter 4 to send the message to all attendees who signed up for a specific event.");
        }
    }

    /**
     * Present success information if organizer successfully finish the instruction
     * @param num different number for different success information
     */
    public void presentSuccessInf(int num){
        if (num == 1){
            System.out.println("you have successfully created a room.\n");
        }
        else if (num == 2){
            System.out.println("This message has been sent successfully.\n");
        }
        else if (num == 3){
            System.out.println("Successfully changed the new maximum number of attendees for this event.\n");
        }
    }

    /**
     * Present success information if organizer fail the instruction
     * @param num different number for different fail information
     */
    public void presentFailInfo(int num){
        if (num == 1){
            System.out.println("Invalid input. Please enter a positive integer.\n");
        }
        else if (num == 2){
            System.out.println("Invalid recipient id. This id is not in your friend list.\n");
        }
        else if (num == 3){
            System.out.println("This event does not exist. Please try again.\n");
        }
        else if (num == 4){
            System.out.println("Please enter a new maximum number of attendees that is larger than " +
                    "the current enrollment for this event and the room capacity.\n");
        }
        else if (num == 5){
            System.out.println("This event does not exist, or this event was not scheduled by you. " +
                    "Please try again.\n");
        }
        else if (num == 6){
            System.out.println("Invalid command input.\n");
        }
    }

}
