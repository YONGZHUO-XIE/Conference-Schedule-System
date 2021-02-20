package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentOutbox;
import java.util.Scanner;

/**
 * Main class for Outbox Menu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class OutboxMenu {

    PresentOutbox presentOutbox = new PresentOutbox();

    /**
     * Presents menu for Outbox There are 2 options in total.
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void printOutboxMenu(ControllerSystem cs, String userid, Scanner myObj) {
        System.out.println(cs.messageSystem().viewOutbox(userid));
        while (true){
            presentOutbox.presentOutboxCommand();
            String type = myObj.nextLine();
            if (type.equals("1")){
                presentOutbox.presentOutboxCommandInfo();
                String num = myObj.nextLine();
                String feedback = cs.messageSystem().checkNumAndDeleteOutbox(num, userid);
                presentOutbox.presentOutbox(feedback);
            }
            else if (type.equals("e")){
                break;
            }else{
               presentOutbox.presentOutboxInvalidInfo();
            }
        }
    }
}
