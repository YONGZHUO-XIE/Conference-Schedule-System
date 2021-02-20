package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentSpeakerMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for Speaker Menu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class SpeakerMenu {

    PresentSpeakerMenu presentSpeakerMenu = new PresentSpeakerMenu();
    /**
     * Presents menu for Speakers. There are 8 options including commands that are specific to only speakers.
     * @param cs a controller system that initiates all controller class
     * @param userid the id of the organizer who is viewing this menu
     * @param myObj the Scanner object
     */
    public void presentSpeakerMenu(ControllerSystem cs, String userid, Scanner myObj){
        while (true){
            presentSpeakerMenu.presentBasicCommand();
            String in = myObj.nextLine();
            if (in.equals("1")){
                new InboxMenu().printInboxMenu(cs, userid, myObj);
            }
            else if (in.equals("2")){
                new OutboxMenu().printOutboxMenu(cs, userid, myObj);
            }
            else if (in.equals("3")){
                System.out.println(cs.userSystem().getUserFriendlist(userid));
            }
            else if (in.equals("4")){
                presentSpeakerMenu.presentAskInfo(1);
                String content = myObj.nextLine();
                presentSpeakerMenu.presentAskInfo(2);
                String recipientId = myObj.nextLine();
                if (cs.userSystem().isFriend(userid, recipientId)) {
                    cs.messageSystem().sendMessageIndividual(content, userid, recipientId);
                    presentSpeakerMenu.presentSuccessInf(1);
                } else {
                    presentSpeakerMenu.presentFailInfo(1);
                }
            }
            else if (in.equals("5")){
                presentSpeakerMenu.presentAskInfo(1);
                String content = myObj.nextLine();
                ArrayList<String> EventId = new ArrayList<>();
                presentSpeakerMenu.presentAskInfo(3);
                String id = myObj.nextLine();
                if (cs.scheduleSystem().hasEvent(id)) {
                    if (cs.userSystem().speakerEvents(userid).contains(id)) {
                        EventId.add(id);
                    }
                } else{
                    presentSpeakerMenu.presentFailInfo(2);
                    break;
                }
                boolean checker = true;
                while(checker){
                    presentSpeakerMenu.presentAskInfo(4);
                    String keep = myObj.nextLine();
                    if (keep.equals("1")){
                        presentSpeakerMenu.presentAskInfo(5);
                        String id2 = myObj.nextLine();
                        if (cs.scheduleSystem().hasEvent(id2)) {
                            if (cs.userSystem().speakerEvents(userid).contains(id2)) {
                                EventId.add(id2); }
                            else{presentSpeakerMenu.presentFailInfo(3);}
                        }else{
                            presentSpeakerMenu.presentFailInfo(4);
                        }
                    }else{
                        checker = false;
                    }
                }
                ArrayList<String> recipientId = cs.messageSystem().generateRecipients(EventId);
                cs.messageSystem().sendMessageAll(content, userid, recipientId);
                presentSpeakerMenu.presentSuccessInf(2);
            }
            else if (in.equals("6")){
                System.out.println(cs.userSystem().speakerEventSpoke(userid));
            }
            else if (in.equals("7")){
                try {
                    new DownloadPdfMenu().pdfCreated(userid, cs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (in.equals("e")){
                cs.exist();
                break;
            }
            else{
                presentSpeakerMenu.presentFailInfo(5);
            }
        }
    }
}
