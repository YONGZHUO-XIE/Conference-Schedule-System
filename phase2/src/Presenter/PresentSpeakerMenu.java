package Presenter;

/**
 * Present function for SpeakerMenu
     * Contains all print message for SpeakerMenu
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentSpeakerMenu {

    /**
     * Present the Basic commands in Speaker Menu
     */
    public void presentBasicCommand(){
        System.out.println("Enter 1 in check message inbox. \nEnter 2 to check message outbox. \nEnter" +
                " 3 to check friend list. \nEnter 4 to send a message to a single user. " +
                "\nEnter 5 to send a message to all attendees who signed up your events." +
                "\nEnter 6 to see events that you have been assigned to." +
                "\nEnter 7 to download the schedule you have." +
                "\nEnter e to exit.");
    }

    /**
     * Ask for User input in order to finish certain commands
     * @param num different numbers for different questions that we are going to ask
     */
    public void presentAskInfo(int num){
        if (num == 1){
            System.out.println("Enter the content of the message.");
        }
        else if (num == 2){
            System.out.println("Enter the user id that you are sending message to");
        }
        else if (num == 3){
            System.out.println("Enter one or multiple event id to send it to all attendees in the event(s). " +
                    "Please enter the first one.");
        }
        else if (num == 4){
            System.out.println("Press 1 to enter another event id. Press any other key to stop entering events.");
        }
        else if (num == 5){
            System.out.println("Please enter the event id.");
        }
    }

    /**
     * Present the success information if the user finish certain instruction
     * @param num different numbers for different Success information
     */
    public void presentSuccessInf(int num){
        if (num == 1){
            System.out.println("The message has been sent.\n");
        }
        else if (num == 2){
            System.out.println("The message has been sent successfully.\n");
        }
    }

    /**
     * Present the fail information if the user fail to finish certain instruction
     * @param num different numbers for different fail information
     */
    public void presentFailInfo(int num){
        if (num == 1){
            System.out.println("Invalid recipient id. This id is not in your friendlist.\n");
        }
        else if (num == 2){
            System.out.println("This event does not exist. Please try again.\n");
        }
        else if (num == 3){
            System.out.println("You are not the speaker of this event.\n");
        }
        else if (num == 4){
            System.out.println("This event does not exist. Please try again.\n");
        }
        else if (num == 5){
            System.out.println("Invalid command input.\n");
        }
    }
}
