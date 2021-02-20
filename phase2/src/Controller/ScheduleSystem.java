package Controller;

import useCase.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


/**
 * schedule events and rooms by organizers
 *
 * @author Kun Zhang, Shang Wu
 * @version %I%, %G%
 */
public class ScheduleSystem {
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private EventManager eventManager;
    private RoomManager roomManager;
    private AttendeeManager attendeeManager;

    /**
     * Initialize a new ScheduleSystem
     * @param speakerManager A speakerManager which stores and managers the speakers.
     * @param organizerManager A organizerManager which stores and managers the organizers.
     * @param eventManager A eventManger which stores and managers the events.
     * @param roomManager A roomManager which stores and managers the rooms.
     * @param attendeeManager A attendeeManager which stores and managers the attendees.
     */
    public ScheduleSystem(SpeakerManager speakerManager, OrganizerManager organizerManager, EventManager eventManager,
                          RoomManager roomManager, AttendeeManager attendeeManager) {
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.eventManager = eventManager;
        this.roomManager = roomManager;
        this.attendeeManager = attendeeManager;
    }


    /**
     * an organizer with his ID assign an normal event by assign a room select the start time of this event
     *
     * @param organizerID an organizer ID who wants to organize
     * @param topic an topic which will be talked about
     * @param RoomID an room ID the organizer want to assign
     * @param year the int year organizer choose
     * @param month the int month organizer choose
     * @param day the int day organizer choose
     * @param hour the int hour organizer choose
     * @param minute the int minute organizer choose
     * @param duration the duration of an event hold.
     * @param maxAttendee the maximum number of attendees assigned for this event.
     * @return different information which is string by checking the normal event whether can be assigned.
     */
    public String assignEvent(String organizerID, String topic, int RoomID, int year, int month, int day, int hour,
                                     int minute, int duration, int maxAttendee){
        if(roomManager.checkEmpty(LocalDateTime.of(year,month,day,hour,minute),
                roomManager.findRoom(RoomID)).equals("There is no such room.")
                || roomManager.checkEmpty(LocalDateTime.of(year,month,day,hour,minute),
                roomManager.findRoom(RoomID)).equals("There existed an event in this room")){
            return "This room has been assigned";
        }
        else{
            String eventId = eventManager.createAndAddEvent(topic, organizerID, RoomID, year, month, day, hour, minute, duration, maxAttendee).getId();
            roomManager.assignRoom(roomManager.findRoom(RoomID),eventManager.getEventById(eventId), LocalDateTime.of(year, month, day, hour,minute),
                    LocalDateTime.of(year,month,day,hour+duration,minute));
            organizerManager.addEvent(organizerManager.findOrganizer(organizerID), eventId);
            return "The event has been successfully added. The eventId is " + eventId;
        }
    }

    /**
     * an organizer with his ID assign an vip event by assign a room select the start time of this event
     *
     * @param organizerID an organizer ID who wants to organize
     * @param topic an topic which will be talked about
     * @param RoomID an room ID the organizer want to assign
     * @param year the int year organizer choose
     * @param month the int month organizer choose
     * @param day the int day organizer choose
     * @param hour the int hour organizer choose
     * @param minute the int minute organizer choose
     * @param duration the duration of an event hold.
     * @param maxAttendee the maximum number of attendees assigned for this event.
     * @return different information which is string by checking the vip event whether can be assigned.
     */
    public String assignVipEvent(String organizerID, String topic, int RoomID, int year, int month, int day, int hour,
                                 int minute, int duration, int maxAttendee) {
        if(roomManager.checkEmpty(LocalDateTime.of(year,month,day,hour,minute),
                roomManager.findRoom(RoomID)).equals("There is no such room.")
                || roomManager.checkEmpty(LocalDateTime.of(year,month,day,hour,minute),
                roomManager.findRoom(RoomID)).equals("There existed an event in this room")){
            return "This room has been assigned";
        }
        else{
            String eventId = eventManager.createAndAddVipEvent(topic, organizerID, RoomID, year, month, day, hour, minute, duration, maxAttendee).getId();
            roomManager.assignRoom(roomManager.findRoom(RoomID),eventManager.getEventById(eventId), LocalDateTime.of(year, month, day, hour,minute),
                    LocalDateTime.of(year,month,day,hour+duration,minute));
            organizerManager.addEvent(organizerManager.findOrganizer(organizerID), eventId);
            return "The event has been successfully added. The eventId is " + eventId;
        }
    }


    /**
     * a speaker want to assign this event by event ID whether it is normal event or not.
     *
     * @param eventID the String ID of an event
     * @param speakerID the ID of a speaker
     * @return different information which is string by checking the speaker is booked successful or not.
     */
    public String bookingNormalSpeaker(String eventID, String speakerID) {
        if(hasEvent(eventID)){
            if(eventManager.checkSpeakerExisted(LocalDateTime.of(eventManager.findEvent(eventID).getDate(),
                    eventManager.findEvent(eventID).getTime()),speakerID)){
                return "This speaker has been booked in other events";
            }
            else{
                if(eventManager.getSpeakerList(eventManager.findEvent(eventID)).size()==0){
                    eventManager.addSpeakers(eventManager.findEvent(eventID),speakerID);
                    speakerManager.addEventforSpeaker(speakerManager.findSpeaker(speakerID), eventID);
                    return "Successfully booking the Event: " + eventID +"! Now this event is singleSpeakerEvent.";
                }
                else if(eventManager.getSpeakerList(eventManager.findEvent(eventID)).size()>=1){
                    eventManager.addSpeakers(eventManager.findEvent(eventID),speakerID);
                    speakerManager.addEventforSpeaker(speakerManager.findSpeaker(speakerID), eventID);
                    return "Successfully booking the Event: " + eventID +"! Now this event is Multi-SpeakerEvent.";
                }
            }
        }
        return "The event is not existed";
    }

    /**
     * the event (VIP or Normal) is canceled by an organizer who organized it. If the event is not existed by checking
     * the event ID, then return "This event is not existed". If this organizer is not the person who organized the event,
     * then return "This event is not organized by you." Otherwise, return the information that which type of event is
     * successful to bt cancelled.
     *
     * @param eventID the String ID of an event
     * @param organizerID the ID of an organizerID
     * @return different information which is string by checking the event is cancelled or not.
     */
    public String cancelEvent(String eventID, String organizerID){
        if(!eventManager.hasEvent(eventID)){
            return "This event is not existed\n";
        }
        else if(!eventManager.getOrganizerID(eventManager.findEvent(eventID)).equals(organizerID)){
            return "This event is not organized by you.\n";
        }
        else{
            for (String attendeeID : eventManager.getAttendeeList(eventManager.getEventById(eventID))) {
                attendeeManager.removeAttendeeEvent(attendeeManager.findAttendee(attendeeID), eventID);
            }
            roomManager.removeRoom(roomManager.findRoom(eventManager.getRoomID(eventManager.findEvent(eventID))), eventManager.findEvent(eventID),
                    eventManager.findEventStartingTime(eventManager.findEvent(eventID)), eventManager.findEventEndingTime(eventManager.findEvent(eventID)));
            organizerManager.removeEvent(organizerManager.findOrganizer(eventManager.getOrganizerID(eventManager.findEvent(eventID))),eventID);
            if(eventManager.getSpeakerList(eventManager.findEvent(eventID)).size()==0){
                if(eventID.contains("v")) {
                    eventManager.getVipEvents().remove(eventManager.findEvent(eventID));
                    return "Successfully removed the party event of VipEvent\n";
                }
                eventManager.getEvents().remove(eventManager.findEvent(eventID));
                return "Successfully removed the party event of NormalEvent\n";
            }
            else if(eventManager.getSpeakerList(eventManager.findEvent(eventID)).size()==1){
                ArrayList<String> speakerList = eventManager.getSpeakerList(eventManager.findEvent(eventID));
                speakerManager.removeEventForSpeaker(speakerManager.findSpeaker(speakerList.get(0)),eventID);
                if(eventID.contains("v")) {
                    eventManager.getVipEvents().remove(eventManager.findEvent(eventID));
                    return "Successfully removed the single speaker event of VIPEvent\n";
                }
                eventManager.getEvents().remove(eventManager.findEvent(eventID));
                return "Successfully removed the single speaker event of NormalEvent\n";
            }
            else{
                ArrayList<String> speakerList = eventManager.getSpeakerList(eventManager.findEvent(eventID));
                for (String speaker: speakerList){
                    speakerManager.removeEventForSpeaker(speakerManager.findSpeaker(speaker), eventID);
                }
                if(eventID.contains("v")) {
                    eventManager.getVipEvents().remove(eventManager.findEvent(eventID));
                    return "Successfully removed the multi-speaker event of VIPEvent\n";
                }
                eventManager.getEvents().remove(eventManager.findEvent(eventID));
                return "Successfully removed the multi-speaker event of NormalEvent\n";
            }
        }
    }

    /**
     * A method that return the speaker IDs at a specific time
     * @param year the int year organizer choose
     * @param month the int month organizer choose
     * @param day the int day organizer choose
     * @param hour the int hour organizer choose
     * @return A string describes all available speaker ID at a specific time
     */
    public ArrayList<String> availableSpeakerId(int year, int month, int day, int hour){
        ArrayList<String> id = new ArrayList<>();
        for (int i = 0; i < speakerManager.getSpeakerList().size(); i++) {
            if (!eventManager.checkSpeakerExisted(LocalDateTime.of(year, month, day, hour, 00),
                    speakerManager.getSpeakerList().get(i).getId())){
                id.add(speakerManager.getSpeakerList().get(i).getId());
            }
        }
        return id;
    }

    /**
     * A method that return all available speaker at a specific time
     * @param year the int year organizer choose
     * @param month the int month organizer choose
     * @param day the int day organizer choose
     * @param hour the int hour organizer choose
     * @return A string describes all available speakers at a specific time
     */
    public String getAllAvailableSpeakerId(int year, int month, int day, int hour) {
        StringBuilder view = new StringBuilder();
        ArrayList<String> available = availableSpeakerId(year, month, day, hour);
        if (available.isEmpty()) {
            return "There is no available speaker.\n";
        }
        for (String id : available){
            view.append(speakerManager.getUserById(id).getName() + ": " + id);
        }
        return view.toString();
    }

    /**
     * A method that get all valid room IDs at a specific time
     *
     * @param year the int year organizer choose
     * @param month the int month organizer choose
     * @param day the int day organizer choose
     * @param hour the int hour organizer choose
     * @param maxAttendee the maximum number of attendees
     * @return the list of all valid room IDs
     */
    public ArrayList<String> getValidRoomId(int year, int month, int day, int hour, int maxAttendee){
        if (roomManager.getEmptyRoom(LocalDateTime.of(year, month, day, hour, 00)).isEmpty()){
            return null;
        }
        ArrayList<String> roomids = new ArrayList<>();
        for (int i = 0; i < roomManager.getEmptyRoom(LocalDateTime.of(year, month, day, hour, 00)).size(); i++){
            if (roomManager.getEmptyRoom(LocalDateTime.of(year, month, day, hour, 00)).get(i).getRoomCapacity() >= maxAttendee){
                roomids.add(String.valueOf(roomManager.getEmptyRoom(LocalDateTime.of(year, month, day, hour, 00)).get(i).getRoomId()));
            }
        }
        if (roomids.isEmpty()){
            return null;
        }
        return roomids;
    }

    /**
     * A method that return all available room ID
     *
     * @param idList the arraylist of String ID of rooms
     * @return A string describes all available room
     */
    public String printRoomIds(ArrayList<String> idList){
        StringBuilder view = new StringBuilder();
        if (idList == null){
            return "There is no available room.\n";
        }
        for (String id : idList){
            view.append(id + "\n");
        }
        return view.toString();
    }

    /**
     * Check an organizer whether organized this event
     *
     * @param organizerId the String ID of an organizer
     * @param eventid the String ID of an event
     * @return true or false if the event is organized by this organizer or not.
     */
    public boolean checkEventOwner(String organizerId, String eventid){
        return organizerManager.getUserById(organizerId).getEventOrganized().contains(eventid);
    }




    /**
     * Creates a room for a command from an organizer. There's no return value.
     *  @param capacity the int capacity of a room
     */
    public void createRoom(int capacity) {
        roomManager.addRoom(capacity);
    }

    /**
     * Checks whether the given event exists according to the id. Returns true if this event exists and false otherwise.
     * @param id a string representing the id of an event.
     * @return a boolean representing whether this event exists
     */
    public boolean hasEvent(String id) {
        return eventManager.hasEvent(id);
    }

    /**
     * Check the new maximum number of attendees is valid
     *
     * @param newmax the int new maximum number of attendees
     * @param eventid the String ID of an event
     * @return true if the newmax is valid. Otherwise is false.
     */
    public boolean checkValidNewMax(int newmax, String eventid){
        return eventManager.getCurrentEnrollment(eventid) <= newmax &&
                roomManager.findRoom(eventManager.getEventById(eventid).getRoomID()).getRoomCapacity() <= newmax;
    }


    /**
     * Changes the maximum number of attendees for an event.
     * @param eventid the event id
     * @param newMax the new maximum number of attendees
     */
    public void resetEventMaxAttendee(String eventid, int newMax) {
        if (eventManager.findEvent(eventid) != null){
            eventManager.resetMaxAttendee(eventManager.findEvent(eventid),newMax);
        }
    }

    /**
     * Checks whether the input of year is valid, i.e. the input is exactly four digits, and the year must be in the
     * future. The method returns a string indicating input invalidity or success.
     * @param year the year to be validated
     * @return a string which tells validity of the input year
     */
    public String validYear(String year) {
        if (!year.chars().allMatch(Character::isDigit) || year.length() != 4) {
            return "Invalid input. Please enter exactly four digits to represent a year.\n";
        } else if (parseInt(year) < LocalDateTime.now().getYear()) {
            return "Invalid input. Please enter a year in the future.\n";
        } else {
            return "Successfully entered a year for the event.\n";
        }
    }

    /**
     * Checks whether the input of year and month are valid, given the year is a valid year (see above method),
     * i.e. the input must be one of number 1-12, and the month must be in the future.
     * The method returns a string indicating input invalidity or success.
     * @param year the year of the month
     * @param month the month to be validated
     * @return a string which tells validity of the input month
     */
    public String validMonth(String year, String month) {
        try {
            if (!month.chars().allMatch(Character::isDigit) || month.length() > 2 || parseInt(month) > 12) {
                return "Invalid input. Please enter at most two digits to represent a month.\n";
            } else if (parseInt(year) <= LocalDateTime.now().getYear() &&
                    parseInt(month) < LocalDateTime.now().getMonthValue()) {
                return "Invalid input. Please enter a month in the future.\n";
            } else {
                return "Successfully entered a month for the event.\n";
            }
        }
        catch (NumberFormatException e){
            return "Invalid input. Please enter two digits to represent a month.\n";
        }
    }

    /**
     * Checks whether the input of year, month, and day are valid, given the year is a valid year and the month
     * is a valid month (see above methods), i.e. the input must be one of 1-31, and the day must be in the future.
     * The method returns a string indicating input invalidity or success.
     * @param year the year of the given month
     * @param month the month of the given day
     * @param day the day to be validated
     * @return a string which tells validity of the input day
     */
    public String validDay(String year, String month, String day) {
        try {
            if (!day.chars().allMatch(Character::isDigit) || day.length() > 2) {
                return "Invalid input. Please enter at most two digits to represent a day.\n";
            } else if (parseInt(year) <= LocalDateTime.now().getYear() &&
                    parseInt(month) <= LocalDateTime.now().getMonthValue() &&
                    parseInt(day) < LocalDateTime.now().getDayOfMonth()) {
                return "Invalid input. Please enter a day in the future.\n";
            } else {
                ArrayList<Integer> bigmonth = new ArrayList<>(); //month that have 31 days
                bigmonth.add(1);
                bigmonth.add(3);
                bigmonth.add(5);
                bigmonth.add(7);
                bigmonth.add(8);
                bigmonth.add(10);
                bigmonth.add(12);
                ArrayList<Integer> smallmonth = new ArrayList<>(); //month that have 30 days
                smallmonth.add(4);
                smallmonth.add(6);
                smallmonth.add(9);
                smallmonth.add(11);
                if (bigmonth.contains(parseInt(day))) {
                    if (parseInt(day) < 1 || parseInt(day) > 31) {
                        return "Invalid input of day. Please enter a day between 01 and 31.\n";
                    } // 1-31 days for big month
                } else if (smallmonth.contains(parseInt(day))) {
                    if (parseInt(day) < 1 || parseInt(day) > 30) {
                        return "Invalid input of day. Please enter a day between 01 and 30.\n";
                    } // 1-30 days for small month
                } else if (parseInt(month) == 2) { // February is a special case
                    if ((parseInt(year) % 4) == 0) {
                        if (parseInt(day) < 1 || parseInt(day) > 29) {
                            return "Invalid input of day. Please enter a day between 01 and 29.\n";
                        }  // 1-29 days for February in a leap year
                    } else {
                        if (parseInt(day) < 1 || parseInt(day) > 28) {
                            return "Invalid input of day. Please enter a day between 01 and 28.\n";
                        } // 1-28 days for February in a non-leap year
                    }
                }
                return "Successfully entered a day for the event. \n";
            }
        }
        catch (NumberFormatException e){
            return "Invalid input. Please enter two digits to represent a day.\n";
        }
    }

    /**
     * Checks whether the input of year, month, day, and hour are valid, given the year, month, and day are all valid
     * (see above methods), i.e. the input must be between 9-17 inclusively, and the hour must be in the future.
     * The method returns a string indicating input invalidity or success.
     * @param year the year of the given month
     * @param month the month of the given day
     * @param day the day of the given hour
     * @param  hour the hour to be validated
     * @return a string which tells validity of the input day
     */
    public String validHour(String year, String month, String day, String hour) {
        try {
            if (parseInt(hour) < 9 || parseInt(hour) >= 17) {
                return "Invalid hour. Please enter an hour that is between 9 and 16 (both inclusively).\n";
            } else if (parseInt(year) <= LocalDateTime.now().getYear() &&
                    parseInt(month) <= LocalDateTime.now().getMonthValue() &&
                    parseInt(day) <= LocalDateTime.now().getDayOfMonth() &&
                    parseInt(hour) < LocalDateTime.now().getHour()) {
                return "Invalid input. Please enter an hour in the future.\n";
            } else {
                return "Successfully entered the hour for the event.\n";
            }
        }
        catch (NumberFormatException e){
            return "Invalid input. Please enter a number.\n";
        }
    }

    /**
     * A method that check whether this event has a speaker by ID.
     *
     * @param hour the String hour of a given day
     * @param duration the String duration time need to check
     * @return A string describes whether the duration is valid.
     */
    public String checkDuration(String hour, String duration){
        if (Integer.parseInt(hour) + Integer.parseInt(duration) > 17){
            return "This duration will end the event after 17pm. Please enter a valid duration.\n";
        }else{
            return "Successfully entered the duration.\n";
        }
    }

    /**
     * A method that check whether this event has a speaker by ID.
     *
     * @param eventId the String ID of an event
     * @return true if the event has a speaker. Otherwise return false.
     */
    public boolean hasSpeaker(String eventId) {
        return !eventManager.getEventById(eventId).getSpeaker_id().isEmpty();
    }

}
