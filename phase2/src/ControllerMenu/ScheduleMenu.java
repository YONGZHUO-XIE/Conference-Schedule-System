package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentScheduleMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * The Schedule part of the UI menu of Organizer
 *
 * @author Shang Wu
 * @version %I%, %G%
 */
public class ScheduleMenu {

    PresentScheduleMenu presentScheduleUIMenu = new PresentScheduleMenu();

    /**
     * This is a helper function for presentOrganizerMenu. It asks organizers for inputs, checks whether the inputs are
     * valid, and finally schedule an event for the organizer. There is no return value.
     * @param cs a controller system that initiates all controller class
     * @param userid the if of the organizer who is scheduling events
     */
    public void presenterOrganizerSchedule(ControllerSystem cs, String userid, Scanner myObj) {
        while (true) {
            //type
            presentScheduleUIMenu.presentAskInfo(2);
            String type = myObj.nextLine();
            if (type.equals("v")){
                presentScheduleUIMenu.presentSuccessInf(1);
            }else{
                presentScheduleUIMenu.presentSuccessInf(2);
            }
            // year
            presentScheduleUIMenu.presentAskInfo(1);
            String year = myObj.nextLine();
            System.out.println(cs.scheduleSystem().validYear(year));
            if (cs.scheduleSystem().validYear(year).contains("Invalid")) {
                break;
            }
            // month
            presentScheduleUIMenu.presentAskInfo(3);
            String month = myObj.nextLine();
            System.out.println(cs.scheduleSystem().validMonth(year, month));
            if (cs.scheduleSystem().validMonth(year, month).contains("Invalid")) {
                break;
            }
            // day
            presentScheduleUIMenu.presentAskInfo(4);
            String day = myObj.nextLine();
            System.out.println(cs.scheduleSystem().validDay(year, month, day));
            if (cs.scheduleSystem().validDay(year, month, day).contains("Invalid")) {
                break;
            }
            // hour
            presentScheduleUIMenu.presentAskInfo(5);
            String hour = myObj.nextLine();
            System.out.println(cs.scheduleSystem().validHour(year, month, day, hour));
            if (cs.scheduleSystem().validHour(year, month, day, hour).contains("Invalid")) {
                break;
            }
            //duration
            presentScheduleUIMenu.presentAskInfo(6);
            String duration = myObj.nextLine();
            try {
                System.out.println(cs.scheduleSystem().checkDuration(hour, duration));
                if (cs.scheduleSystem().checkDuration(hour, duration).contains("valid")) {
                    break;
                }
            }catch (NumberFormatException e){
                presentScheduleUIMenu.presentFailInf(1);
                break;
            }
            // topic
            presentScheduleUIMenu.presentAskInfo(7);
            String topic = myObj.nextLine();
            if (topic.length() > 70){
                presentScheduleUIMenu.presentFailInf(2);
                break;
            }
            presentScheduleUIMenu.presentSuccessInf(3);
            //maximum number of attendees
            presentScheduleUIMenu.presentAskInfo(8);
            String maxAttendee = myObj.nextLine();
            presentScheduleUIMenu.presentSuccessInf(4);
            //room
            presentScheduleUIMenu.presentSuccessInf(5);
            ArrayList<String> roomids = cs.scheduleSystem().getValidRoomId(parseInt(year), parseInt(month),
                    parseInt(day), parseInt(hour), Integer.parseInt(maxAttendee));
            String idString = cs.scheduleSystem().printRoomIds(roomids);
            System.out.println(idString);
            if (idString.equals("There is no available room.\n")) {
                break;
            }
            presentScheduleUIMenu.presentAskInfo(9);
            String roomid = myObj.nextLine();
            if (!roomids.contains(roomid)){
                presentScheduleUIMenu.presentFailInf(3);
            }
            String assignResult;
            if (type.equals("v")) {
                assignResult = cs.scheduleSystem().assignVipEvent(userid, topic, Integer.parseInt(roomid), Integer.parseInt(year),
                        Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), 00,
                        Integer.parseInt(duration), Integer.parseInt(maxAttendee));
            }else{
                assignResult = cs.scheduleSystem().assignEvent(userid, topic, Integer.parseInt(roomid), Integer.parseInt(year),
                        Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), 00,
                        Integer.parseInt(duration), Integer.parseInt(maxAttendee));
            }
            System.out.println(assignResult);
            String eventId = assignResult.substring(54);
            if (assignResult.contains("successfully")){
                //speaker
                while (true) {
                    presentScheduleUIMenu.presentSuccessInf(6);
                    String speakerids = cs.scheduleSystem().getAllAvailableSpeakerId(parseInt(year), parseInt(month),
                            parseInt(day), parseInt(hour));
                    System.out.println(speakerids);
                    if (speakerids.equals("There is no available speaker.\n")) {
                        break;
                    }
                    presentScheduleUIMenu.presentAskInfo(10);
                    String speakerid = myObj.nextLine();
                    if (!cs.scheduleSystem().availableSpeakerId(Integer.parseInt(year), Integer.parseInt(month),
                            Integer.parseInt(day), Integer.parseInt(hour)).contains(speakerid)) {
                        presentScheduleUIMenu.presentFailInf(4);
                        break;
                    } else {
                        presentScheduleUIMenu.presentSuccessInf(7);
                    }
                    System.out.println(cs.scheduleSystem().bookingNormalSpeaker(eventId, speakerid));
                    presentScheduleUIMenu.presentAskInfo(11);
                    String booking = myObj.nextLine();
                    if (!booking.equals("1")){
                        break;
                    }
                }
                if (!cs.scheduleSystem().hasSpeaker(eventId)){
                    presentScheduleUIMenu.presentFailInf(5);
                    System.out.println(cs.scheduleSystem().cancelEvent(eventId, userid));
                }else{
                    presentScheduleUIMenu.presentSuccessInf(8);
                }
                }
            break;
            }
        }
    }


