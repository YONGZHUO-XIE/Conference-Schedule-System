package Presenter;

/**
 * Present function for ResetPassword
 * Contains all print message for ResetPassword
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentResetPassword {

    /**
     * Ask user to enter user id
     */
    public void presentAskId(){
        System.out.println("Please enter your id");
    }

    /**
     * Present different security question in order to reset password
     * @param num
     */
    public void presentAskAnswer(int num){
        if (num == 1){
            System.out.println("Where your college located?");
        }
        else if (num == 2){
            System.out.println("What year are you in?");
        }
        else if (num == 3){
            System.out.println("What major are you studying?");
        }
    }

    /**
     * Present the success information if the user successfully reset his password
     */
    public void presentSuccessInfo(){
        System.out.println("The answer of the security question is correct, you can enter your new " +
                "password now");
    }

    /**
     * Present the fail information if user enter the wrong answer for his security question
     */
    public void presentFailInfo(){
        System.out.println("Invalid answer");
    }

    /**
     * Present the fail login information if the user haven't register into system yet.
     */
    public void presentFailLoginInfo(){
        System.out.println("You haven't register");
    }
}
