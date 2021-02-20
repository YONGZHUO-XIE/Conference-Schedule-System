package Presenter;


/**
 * Present function for PresentCreateUser
 * Contains all print message for CreateUser
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentMainMenu {

    public void presentBasicInstruction(){
        System.out.println("enter l for login, enter r for register, enter s to reset your password, " +
                "enter e for exit");
    }

    public void presentAskInfo(int num){
        if (num == 1){
            System.out.println("Enter your name");
        }
        else if(num == 2){
            System.out.println("Enter your password");
        }
        else if(num == 3){
            System.out.println("Enter your id");
        }
    }

    public void presentAskRegisterCommand(){
        System.out.println("If you wish to create a Speaker account, enter the special command. ");
        System.out.println("If you wish to create an Organizer account, enter the the special command. ");
        System.out.println("If you wish to create an Attendee account, enter the word 'Attendee'. ");
    }

    public void presentSuccessInfo(int num){
        if (num == 1){
            System.out.println("Congratulations. You have registered successfully.\n");
        }
        else if (num == 2){
            System.out.println("Congratulations. You have login in successfully.\n");
        }
    }

    public void presentIdInfo(String userid){
        System.out.println("Your user id is:" + userid + ". Please remember your id for your future login in.\n");
    }

    public void presentInvalidInfo(int num){
        if (num == 1){
            System.out.println("Invalid input command. Please try again.\n");
        }
        else if(num == 2){
            System.out.println("Invalid id or passwords.");
        }
        else if(num == 3){
            System.out.println("Invalid command input.");
        }
    }


}

