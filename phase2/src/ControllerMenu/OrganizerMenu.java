package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentOrganizerMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for Organizer Menu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class OrganizerMenu {

    PresentOrganizerMenu presentOrganizerMenu = new PresentOrganizerMenu();
    /**
     * Presents menu for organizers. There are 9 options including commands that are specific to only organizers.
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void presentOrganizerMenu(ControllerSystem cs, String userid, Scanner myObj){
        while (true){
            presentOrganizerMenu.presentBasicCommand();
            String in = myObj.nextLine();
            if (in.equals("1")){
                new InboxMenu().printInboxMenu(cs, userid, myObj);
            } else if (in.equals("2")){
                new OutboxMenu().printOutboxMenu(cs, userid, myObj);
            } else if (in.equals("3")){
                presentOrganizerMenu.presentAskInfo(1);
                String capacity = myObj.nextLine();
                try {
                    cs.scheduleSystem().createRoom(Integer.parseInt(capacity));
                    presentOrganizerMenu.presentSuccessInf(1);
                }catch(NumberFormatException e){
                    presentOrganizerMenu.presentFailInfo(1);
                }
            } else if (in.equals("4")){
                new CreateUserMenu().createUser(cs, myObj);
            } else if (in.equals("5")){
                new ScheduleMenu().presenterOrganizerSchedule(cs, userid, myObj);
            } else if (in.equals("6")){
                presentOrganizerMenu.presentAskInfo(2);
                String content = myObj.nextLine();
                presentOrganizerMenu.presentAskInfo(3);
                String recipientId = myObj.nextLine();
                if (cs.userSystem().isFriend(userid, recipientId)) {
                    cs.messageSystem().sendMessageIndividual(content, userid, recipientId);
                    presentOrganizerMenu.presentSuccessInf(2);
                } else {
                    presentOrganizerMenu.presentFailInfo(2);
                }
            } else if (in.equals("7")){
                presentOrganizerMenu.presentAskInfo(2);
                String content = myObj.nextLine();
                presentOrganizerMenu.presentAskInfo(7);
                String choose = myObj.nextLine();
                if (choose.equals("1")){
                    cs.messageSystem().sendMessageToAllAttendee(content, userid);
                    presentOrganizerMenu.presentSuccessInf(2);
                }
                else if (choose.equals("2")){
                    cs.messageSystem().sendMessageToAllSpeaker(content, userid);
                    presentOrganizerMenu.presentSuccessInf(2);
                }
                else if (choose.equals("3")){
                    cs.messageSystem().sendMessageToAllAttendee(content, userid);
                    cs.messageSystem().sendMessageToAllSpeaker(content, userid);
                    presentOrganizerMenu.presentSuccessInf(2);
                }
                else if(choose.equals("4")){
                    presentOrganizerMenu.presentAskInfo(4);
                    String eventid = myObj.nextLine();
                    ArrayList<String> eventId = new ArrayList<>();
                    eventId.add(eventid);
                    if (cs.scheduleSystem().hasEvent(eventid)) {
                        ArrayList<String> allRecipientId = cs.messageSystem().generateRecipients(eventId);
                        cs.messageSystem().sendMessageAll(content, userid, allRecipientId);
                        presentOrganizerMenu.presentSuccessInf(2);
                    } else {
                        presentOrganizerMenu.presentFailInfo(3);
                    }
                }
            } else if (in.equals("8")){
                new OrganizerEventPrompt().printOrganizerEvent(cs, userid, myObj);
            } else if (in.equals("9")){
                System.out.println(cs.userSystem().getUserFriendlist(userid));
            } else if (in.equals("10")) {
                presentOrganizerMenu.presentAskInfo(5);
                String eventid = myObj.nextLine();
                if (cs.scheduleSystem().hasEvent(eventid) && cs.scheduleSystem().checkEventOwner(userid, eventid)) {
                    presentOrganizerMenu.presentAskInfo(6);
                    String newMax = myObj.nextLine();
                    if (cs.scheduleSystem().checkValidNewMax(Integer.parseInt(newMax), eventid)){
                        cs.scheduleSystem().resetEventMaxAttendee(eventid, Integer.parseInt(newMax));
                        presentOrganizerMenu.presentSuccessInf(3);
                    } else {
                        presentOrganizerMenu.presentFailInfo(4);
                    }
                } else {
                    presentOrganizerMenu.presentFailInfo(5);
                }
            } else if(in.equals("11")){
                UserRequestMenu menu = new UserRequestMenu();
                menu.display(cs, myObj);
            }else if (in.equals("12")){
                try {
                    new DownloadPdfMenu().pdfCreated(userid, cs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (in.equals("e")) {
                cs.exist();
                break;
            } else {
                presentOrganizerMenu.presentFailInfo(6);
            }
        }
    }

}
