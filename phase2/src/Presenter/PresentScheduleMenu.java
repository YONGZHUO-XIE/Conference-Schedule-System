package Presenter;

/**
 * Present function for ScheduleMenu
 * Contains all print message for ScheduleMenu
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentScheduleMenu {

    /**
     * Ask user for user input in order to finish the schedule
     * @param num different number for different instructions
     */
    public void presentAskInfo(int num){
        if (num == 1){
            System.out.println("enter the 4-digit year for the new event");
        }
        else if (num == 2){
            System.out.println("What type of event do you want to schedule?\n" +
                    "Enter v to schedule a VIP event. Enter any other key to schedule a non-vip event.");
        }
        else if (num == 3){
            System.out.println("enter the month for the new event.");
        }
        else if (num == 4){
            System.out.println("enter the day for the new event.");
        }
        else if (num == 5){
            System.out.println("Enter the hour for the new event. \nFor example, enter 15 " +
                    "for an event that will happen from 15 O'clock to 16 O'clock in a day. \n" +
                    "The working hour is from 9 to 17 each day.");
        }
        else if (num == 6){
            System.out.println("Enter the duration of the event.(e.g. enter 1 to represent 1 hour)");
        }
        else if (num == 7){
            System.out.println("enter the topic of the new event(maximum: 70 characters)");
        }
        else if (num == 8){
            System.out.println("what's the maximum number of attendees for this event?");
        }
        else if (num == 9){
            System.out.println("choose one of the room ids from the above that you want to assign this event to.");
        }
        else if (num == 10){
            System.out.println("choose one of the speaker ids from the above that you want to assign this event to.");
        }
        else if (num == 11){
            System.out.println("Enter 1 to book another speaker. Enter any other key to finish booking.");
        }
    }

    /**
     * Present the success information if the user finish certain instruction
     * @param num different number for different success information
     */
    public void presentSuccessInf(int num){
        if (num == 1){
            System.out.println("You're scheduling a VIP event.\n");
        }
        else if (num == 2){
            System.out.println("You're scheduling a normal event.\n");
        }
        else if (num == 3){
            System.out.println("Successfully entered a topic for the event.\n");
        }
        else if (num == 4){
            System.out.println("Successfully entered the maximum number of attendees.\n");
        }
        else if (num == 5){
            System.out.println("Please see the following list of available rooms. They have sufficient capacity " +
                    "for the event at the given time.");
        }
        else if (num == 6){
            System.out.println("Please see the following list of available speakers at the given time");
        }
        else if (num == 7){
            System.out.println("Successfully entered an available speaker id for the event.\n");
        }
        else if (num == 8){
            System.out.println("Congratulations. You have successfully scheduled the event. \n");
        }
    }

    /**
     * Present the fail information if the user incorrectly enter some input that is not available
     * @param num different number for different fail information
     */
    public void presentFailInf(int num){
        if (num == 1){
            System.out.println("Invalid input. Please enter a positive integer.\n");
        }
        else if (num == 2){
            System.out.println("Too much characters. Please try again.");
        }
        else if (num == 3){
            System.out.println("Invalid input. This room does not exist or it is unavailable for you.");
        }
        else if (num == 4){
            System.out.println("Invalid speaker id. Please try again.\n");
        }
        else if (num == 5){
            System.out.println("There is no available speaker to be assigned to this event. " +
                    "Therefore we'll cancel the event you just scheduled.");
        }
    }
}
