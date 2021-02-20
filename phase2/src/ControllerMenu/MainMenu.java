package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentMainMenu;

import java.util.Scanner;

/**
 * Organized the main UI class
 * @author Yongzhou Xie, Shang Wu
 * @version %I%, %G%
 */


public class MainMenu {

    ControllerSystem cs = new ControllerSystem();
    Scanner myObj = new Scanner(System.in); //Create a Scanner object
    AttendeeMenu attendeeMenu = new AttendeeMenu();
    OrganizerMenu organizerMenu = new OrganizerMenu();
    SpeakerMenu speakerMenu = new SpeakerMenu();
    PresentMainMenu presentMainMenu = new PresentMainMenu();

    /**
     *  A method respond to run the UserInterface.
     *  Specific the program action after receiving the user input.
     *  Given feedback to the user after each action.
     */
    public void run() {
        while (true) {
            presentMainMenu.presentBasicInstruction();
            String in = myObj.nextLine(); //method nextLine reads a string value from the user
            if (in.equals("r")) {
                String userid = null;
                presentMainMenu.presentAskInfo(1);
                String username = myObj.nextLine();
                presentMainMenu.presentAskInfo(2);
                String pw = myObj.nextLine();
                presentMainMenu.presentAskRegisterCommand();
                String command = myObj.nextLine();
                if (command.equals("csc")) {
                    userid = cs.registrar().registerOrganizer(username, pw);
                    new SecurityQuestionMenu().printSecurityQuestionMenu(cs, userid, myObj);
                    presentMainMenu.presentSuccessInfo(1);
                    presentMainMenu.presentIdInfo(userid);
                } else if (command.equals("207")) {
                    userid = cs.registrar().registerSpeaker(username, pw);
                    new SecurityQuestionMenu().printSecurityQuestionMenu(cs, userid, myObj);
                    presentMainMenu.presentSuccessInfo(1);
                    presentMainMenu.presentIdInfo(userid);
                } else if (command.equals("Attendee")) {
                    userid = cs.registrar().registerAttendee(username, pw);
                    new SecurityQuestionMenu().printSecurityQuestionMenu(cs, userid, myObj);
                    presentMainMenu.presentSuccessInfo(1);
                    presentMainMenu.presentIdInfo(userid);
                }
                else {
                    presentMainMenu.presentInvalidInfo(1);
                }
            } //Registration will be done after implement the check Registration

            else if (in.equals("l")) {
                presentMainMenu.presentAskInfo(3);
                String id = myObj.nextLine();
                presentMainMenu.presentAskInfo(2);
                String pw = myObj.nextLine();
                if (cs.loginSystem().logIn(id, pw)) {
                    presentMainMenu.presentSuccessInfo(2);
                    if (id.charAt(0) == 'a') {
                        attendeeMenu.presentAttendeeMenu(cs, id, myObj);
                    } else if (id.charAt(0) == 'o') {
                        organizerMenu.presentOrganizerMenu(cs, id, myObj);
                    } else if (id.charAt(0) == 's') {
                        speakerMenu.presentSpeakerMenu(cs, id, myObj);
                    }
                } else {
                    presentMainMenu.presentInvalidInfo(2);
                }
            } else if (in.equals("e")) {
                cs.exist();
                break;
            }
            else if (in.equals("s")){
                new ResetPasswordMenu().printResetPasswordMenu(cs, myObj);
            }
            else {
                presentMainMenu.presentInvalidInfo(3);
            }
        }
    }
}


