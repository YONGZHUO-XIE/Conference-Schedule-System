package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentAttendeeMenu;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class for Attendee Menu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class AttendeeMenu {

    PresentAttendeeMenu presentAttendeeMenu = new PresentAttendeeMenu();

    /**
     * resents menu for attendees. There are 12 options including commands that are specific to only attendees.
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void presentAttendeeMenu(ControllerSystem cs, String userid, Scanner myObj){
        while (true){
            presentAttendeeMenu.presentBasicCommand();
            String in = myObj.nextLine();
            if (in.equals("1")){
                new InboxMenu().printInboxMenu(cs, userid, myObj);
            }
            else if (in.equals("2")){
                new OutboxMenu().printOutboxMenu(cs, userid, myObj);
            }
            else if (in.equals("3")){
                System.out.println(cs.signUpSystem().viewAvailableEvent(userid));
            }
            else if (in.equals("4")){
                presentAttendeeMenu.presentAskInfo(1);
                String eventId = myObj.nextLine();
                if (cs.signUpSystem().isValid(eventId, userid)){
                    if (cs.signUpSystem().checkDoubleSignUp(userid, eventId)){
                        presentAttendeeMenu.presentFailInfo(1);
                    }
                    else{
                        cs.signUpSystem().signUp(userid, eventId);
                        presentAttendeeMenu.presentSuccessInfo(1);
                    }
                }
                else{
                    presentAttendeeMenu.presentFailInfo(2);
                }
            }
            else if (in.equals("5")){
                System.out.println(cs.userSystem().attendeeShowEventsRegistered(userid));
            }
            else if (in.equals("6")){
                presentAttendeeMenu.presentAskInfo(2);
                String eventId = myObj.nextLine();
                if (cs.userSystem().isRegistered(userid, eventId)){
                    cs.signUpSystem().cancel(userid, eventId);
                    presentAttendeeMenu.presentSuccessInfo(2);
                }
                else{presentAttendeeMenu.presentFailInfo(3);}
            }
            else if (in.equals("7")){
                System.out.println(cs.userSystem().getUserFriendlist(userid));
            }
            else if (in.equals("8")){
                presentAttendeeMenu.presentAskInfo(3);
                String content = myObj.nextLine();
                presentAttendeeMenu.presentAskInfo(4);
                String recipientId = myObj.nextLine();
                if (cs.userSystem().isFriend(userid, recipientId)) {
                    cs.messageSystem().sendMessageIndividual(content, userid, recipientId);
                    presentAttendeeMenu.presentSuccessInfo(3);
                } else {
                    presentAttendeeMenu.presentFailInfo(4);
                }
            }
            else if (in.equals("9")){
                UserRequestMenu menu = new UserRequestMenu();
                menu.createUserRequest(userid,cs,myObj);
                presentAttendeeMenu.presentSuccessInfo(4);
            }
            else if (in.equals("10")){
                try {
                    new DownloadPdfMenu().pdfCreated(userid, cs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(in.equals("11")){
                if(cs.vipSystem().verifyVip_status(userid)) {
                    presentAttendeeMenu.presentAskInfo(5);
                    String request = myObj.nextLine();
                    if (request.equals("y")) {
                        cs.vipSystem().modifyVip_status(userid,false);
                        presentAttendeeMenu.presentSuccessInfo(6);
                    } else {
                        presentAttendeeMenu.presentFailInfo(5);
                    }
                }

                else{
                    presentAttendeeMenu.presentAskInfo(6);
                    String add_vip = myObj.nextLine();
                    if (add_vip.equals("csc207")) {
                        cs.vipSystem().modifyVip_status(userid,true);
                        presentAttendeeMenu.presentSuccessInfo(5);
                    } else if(add_vip.equals("e")){
                    }else {
                        presentAttendeeMenu.presentFailInfo(6);
                    }
                }
            }

            else if (in.equals("e")){
                cs.exist();
                break;
            }
            else{
                presentAttendeeMenu.presentFailInfo(7);
            }
        }
    }
}
