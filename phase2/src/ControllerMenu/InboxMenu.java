package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentInbox;

import java.util.Scanner;
/**
 * Main class for Inbox Menu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class InboxMenu {


    PresentInbox presentInbox = new PresentInbox();

    /**
     * Presents menu for Inbox There are 3 options in total.
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void printInboxMenu(ControllerSystem cs, String userid, Scanner myObj) {
        String inbox = cs.messageSystem().viewInbox(userid);
        presentInbox.presentFeedback(inbox);
        while (true) {
            presentInbox.presentInboxCommand();
            String type = myObj.nextLine();
            if (type.equals("1")) {
                presentInbox.presentCommandInfo(1);
                String num = myObj.nextLine();
                String feedback = cs.messageSystem().checkNumAndSet(num, userid);
                presentInbox.presentFeedback(feedback);
            }
            else if (type.equals("2")){
                presentInbox.presentCommandInfo(2);
                String num = myObj.nextLine();
                String feedback = cs.messageSystem().checkNumAndDeleteInbox(num, userid);
                presentInbox.presentFeedback(feedback);
            }
            else if (type.equals("e")){
                break;
            }else{
                presentInbox.presentInvalidInfo();
            }
        }
    }
}
