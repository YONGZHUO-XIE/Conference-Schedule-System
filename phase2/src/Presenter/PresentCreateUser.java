package Presenter;

/**
 * Present function for PresentCreateUser
 * Contains all print message for CreateUser
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentCreateUser{

    /**
     * Present the basic command instruction for CreateUserMenu
     */
    public void presentCreateCommand(){
        System.out.println("Enter 1 to create a speaker; " +
                "\nenter 2 to create an attendee; " +
                "\nenter 3 to create an organizer.");
    }

    /**
     * Ask user to enter the name of the account
     * different commands for different user name information
     * @param command
     */
    public void presentUserName(int command){
        if (command == 1){
            System.out.println("Enter the user name of the speaker that you want to create."); }
        else if (command == 2){
            System.out.println("Enter the user name of the attendee that you want to create.");
        }
        else if (command == 3){
            System.out.println("Enter the user name of the organizer that you want to create.");
        }
    }

    /**
     * Ask user to enter the password of the account
     * @param command different commands for different password information
     */
    public void presentUserPasswordCommand(int command){
        if (command == 1){
            System.out.println("Enter the password for this speaker account.");
        }
        else if (command == 2){
            System.out.println("Enter the password for this attendee account.");
        }
        else if (command == 3){
            System.out.println("Enter the password for this organizer account.");
        }
    }

    /**
     * Present a success information after creating an account
     * @param command different commands for different success information
     * @param userid the user id that is going to be present in the screen
     */
    public void presentUserIdCommand(int command, String userid){
        if (command == 1){
            System.out.println("You have successfully created a speaker account. Its ID is: "
                    + userid + "\n");
        }
        else if (command == 2){
            System.out.println("You have successfully created an attendee account. Its ID is: "
                    + userid + "\n");
        }
        else if (command == 3){
            System.out.println("You have successfully created an organizer account. Its ID is: "
                    + userid + "\n");
        }
    }

    /**
     * The fail information if user enter any invalid input
     */
    public void presentInvalidInformation(){
        System.out.println("Invalid Input. Please try again.");
    }


}
