package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentSecurityQuestion;

import java.util.Scanner;

/**
 * Main class for SecurityQuestionMenu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class SecurityQuestionMenu {

    PresentSecurityQuestion presentSecurityQuestion = new PresentSecurityQuestion();

    /**
     * Presents menu for SecurityQuestion. There are 3 options in total.
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void printSecurityQuestionMenu(ControllerSystem cs, String userid, Scanner myObj){
        while(true) {
            presentSecurityQuestion.presentSecurityQuestionCommand();
            String num = myObj.nextLine();
            if (num.equals("1")) {
                presentSecurityQuestion.presentSecurityQuestion(1);
                String answer = myObj.nextLine();
                if (!answer.isEmpty()) {
                    cs.loginSystem().setSecurityQuestion(userid, "Where is your college located?", answer);
                    presentSecurityQuestion.presentSuccessInfo();
                    break;
                } else {
                    presentSecurityQuestion.presentFailInfo(1);
                }
            } else if (num.equals("2")) {
                presentSecurityQuestion.presentSecurityQuestion(2);
                String answer = myObj.nextLine();
                if (!answer.isEmpty()) {
                    cs.loginSystem().setSecurityQuestion(userid, "What year are you in?", answer);
                    presentSecurityQuestion.presentSuccessInfo();
                    break;
                } else {
                    presentSecurityQuestion.presentFailInfo(1);
                }
            } else if (num.equals("3")) {
                presentSecurityQuestion.presentSecurityQuestion(3);
                String answer = myObj.nextLine();
                if (!answer.isEmpty()) {
                    cs.loginSystem().setSecurityQuestion(userid, "What major are you studying?", answer);
                    presentSecurityQuestion.presentSuccessInfo();
                    break;
                } else {
                    presentSecurityQuestion.presentFailInfo(1);
                }
            } else {
                presentSecurityQuestion.presentFailInfo(2);
            }
        }
    }
}
