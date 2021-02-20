package Presenter;

/**
 * Present function for SecurityQuestion
 * Contains all print message for SecurityQuestion
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentSecurityQuestion {

    /**
     * Present all the commands that available in SecurityQuestion Menu
     */
    public void presentSecurityQuestionCommand(){
        System.out.println("Please notice that we require you to create a Security question in case you need to \n" +
                "reset your password. Choose one of the following question as your security question\n" +
                "Enter 1 to answer Question1: Where is your college located?\n" +
                "Enter 2 to answer Question2: What year are you in?\n" +
                "Enter 3 to answer Question3: What major are you studying?");
    }

    /**
     * Ask for the answer of different security question
     * @param command different commands for different security question
     */
    public void presentSecurityQuestion(int command){
        if (command == 1){
            System.out.println("Enter your answer for Question1.");
        }
        else if (command == 2){
            System.out.println("Enter your answer for Question2.");
        }
        else if (command == 3){
            System.out.println("Enter your answer for Question3.");
        }
    }

    /**
     * Present success information if user enter the correct answer for his security question
     */
    public void presentSuccessInfo(){
        System.out.println("Congratulations. Your security question has set successfully");
    }

    /**
     * Pres the fail information if user fail to answer the security question
     * @param command different commands for different fail information
     */
    public void presentFailInfo(int command){
        if (command == 1) {
            System.out.println("Answer can not be empty");
        }else{
            System.out.println("Invalid input. Please enter 1-3 only.");
        }
    }
}
