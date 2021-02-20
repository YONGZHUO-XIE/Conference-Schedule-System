package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentResetPassword;

import java.util.Scanner;

/**
 * Main class for ResetPassword Menu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class ResetPasswordMenu {

    PresentResetPassword presentResetPassword = new PresentResetPassword();

    /**
     * Presents menu for ResetPassword
     * @param cs a controller system that initiates all controller class
     * @param myObj the Scanner object
     */
    public void printResetPasswordMenu(ControllerSystem cs, Scanner myObj){

        presentResetPassword.presentAskId();
        String userid = myObj.nextLine();

        if (cs.loginSystem().checkIdValidity(userid)){
            if (cs.loginSystem().containsSeqQues(userid, "Where is your college located?")){
                presentResetPassword.presentAskAnswer(1);
                String givenAns = myObj.nextLine();
                if (cs.loginSystem().checkSecurityQues(userid, "Where is your college located?", givenAns)){
                    presentResetPassword.presentSuccessInfo();
                    String pw = myObj.nextLine();
                    cs.loginSystem().resetPassword(userid, pw);
                }
                else{
                    presentResetPassword.presentFailInfo();
                }
            }

            else if (cs.loginSystem().containsSeqQues(userid, "What year are you in?")){
                presentResetPassword.presentAskAnswer(2);
                String givenAns = myObj.nextLine();
                if (cs.loginSystem().checkSecurityQues(userid, "What year are you in?", givenAns)){
                    presentResetPassword.presentSuccessInfo();
                    String pw = myObj.nextLine();
                    cs.loginSystem().resetPassword(userid, pw);
                }
                else{
                    presentResetPassword.presentFailInfo();
                }
            }

            else if (cs.loginSystem().containsSeqQues(userid, "What major are you studying?")){
                presentResetPassword.presentAskAnswer(3);
                String givenAns = myObj.nextLine();
                if (cs.loginSystem().checkSecurityQues(userid, "What major are you studying?", givenAns)){
                    presentResetPassword.presentSuccessInfo();
                    String pw = myObj.nextLine();
                    cs.loginSystem().resetPassword(userid, pw);
                }
                else{
                    presentResetPassword.presentFailInfo();
                }
            }
        }

        else{
            presentResetPassword.presentFailLoginInfo();
        }
    }
}
