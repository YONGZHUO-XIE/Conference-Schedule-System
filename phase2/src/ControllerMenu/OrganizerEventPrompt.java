package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentOrganizerEventPrompt;

import java.util.Scanner;

/**
 * Main class for OrganizerEventPrompt
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class OrganizerEventPrompt {

    PresentOrganizerEventPrompt presentOrganizerEventPrompt = new PresentOrganizerEventPrompt();

    /**
     * Presents menu for OrganizerEventPrompt
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void printOrganizerEvent(ControllerSystem cs, String userid, Scanner myObj){
        String feedback = cs.userSystem().organizerEventHold(userid);
        presentOrganizerEventPrompt.presentOrganizerEventFeedback(feedback);
        while(true) {
            presentOrganizerEventPrompt.presentOrganizerEventCommand();
            String option = myObj.nextLine();
            if (option.equals("1")){
                presentOrganizerEventPrompt.presentOrganizerEventIdCommand();
                String eventId = myObj.nextLine();
                String fe = cs.scheduleSystem().cancelEvent(eventId, userid);
                presentOrganizerEventPrompt.presentOrganizerEventFeedback(fe);
            }else{
                break;
            }
        }
    }


}
