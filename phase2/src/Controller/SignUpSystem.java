package Controller;

import useCase.AttendeeManager;
import useCase.EventManager;
import useCase.OrganizerManager;
import useCase.SpeakerManager;
import java.util.ArrayList;

/**
 * Assemble methods that are called when users request to sign up
 *
 * @author Ruicong Zong, Xuezhou
 * @version %I%, %G%
 */
public class SignUpSystem {
    private AttendeeManager attendeeManager;
    private EventManager eventManager;
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;

    /**
     * The initializer of a SignupSystem.
     * @param attendeeManager the AttendeeManager that works for this system
     * @param eventManager the EventManager that works for this system
     * @param speakerManager the SpeakerManager that works for this system
     * @param organizerManager the OrganizerManager that works for this system
     */
    public SignUpSystem(AttendeeManager attendeeManager, EventManager eventManager, SpeakerManager speakerManager,
                        OrganizerManager organizerManager){
        this.attendeeManager = attendeeManager;
        this.eventManager = eventManager;
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
    }


    /**
     * By taken an attendee id and an event id, sign up the event for this attendee
     * @param attendeeId the id of the attendee
     * @param eventId the id of the event
     */
    public void signUp(String attendeeId, String eventId){
        ArrayList<String> speakerList = eventManager.getSpeakerList(eventManager.findEvent(eventId));
        attendeeManager.addAttendeeEvent(attendeeManager.findAttendee(attendeeId), eventManager.getId(eventManager.findEvent(eventId)));
        eventManager.addAttendees(eventManager.findEvent(eventId), attendeeId);
        for(String i : eventManager.findEvent(eventId).getAttendeesIDs()){
            if(!i.equals(attendeeId)) {
                if(!attendeeManager.getFriendList(attendeeManager.findAttendee(attendeeId)).contains(i)){
                attendeeManager.addAttendeeFriendList(attendeeManager.findAttendee(attendeeId), i);}
                if(!attendeeManager.getFriendList(attendeeManager.findAttendee(i)).contains(attendeeId)){
                attendeeManager.addAttendeeFriendList(attendeeManager.findAttendee(i), attendeeId);}
            }
        }
        if(speakerList.size()==1) {
            if (!speakerManager.findSpeaker(speakerList.get(0)).getFriendList().contains(attendeeId)) {
                speakerManager.addSpeakerFriendList(speakerManager.findSpeaker(speakerList.get(0)), attendeeId);
            }
            if (!attendeeManager.findAttendee(attendeeId).getFriendList().contains(speakerList.get(0))) {
                attendeeManager.addAttendeeFriendList(attendeeManager.findAttendee(attendeeId), speakerList.get(0));
            }
        }
        else if(speakerList.size()>=1){
            for(String speaker: speakerList){
                if (!speakerManager.findSpeaker(speaker).getFriendList().contains(attendeeId)) {
                    speakerManager.addSpeakerFriendList(speakerManager.findSpeaker(speaker), attendeeId);
                }
                if (!attendeeManager.findAttendee(attendeeId).getFriendList().contains(speaker)) {
                    attendeeManager.addAttendeeFriendList(attendeeManager.findAttendee(attendeeId),speaker);
                }
            }
        }
    }

    /**
     * By taken an attendee id and an event id, cancel the registered event for this attendee
     * @param attendeeId the id of the attendee
     * @param eventId the id of the event
     */
    public void cancel(String attendeeId, String eventId){
        attendeeManager.removeAttendeeEvent(attendeeManager.getUserById(attendeeId), eventId);
        eventManager.removeAttendees(eventManager.getEventById(eventId), attendeeId);
    }


    /**
     * check if the event by the given id is in the event list(valid) and is available
     * @param eventId  the id of the event
     * @return  true if the event is in valid and available
     */
    //check if the event by the given id is valid(in the event list and is available)
    public boolean isValid(String eventId, String userId){
        if (eventManager.getEvents().isEmpty() && eventManager.getVipEvents().isEmpty()){
            return false;
        }
        for (int i = 0; i < eventManager.getEvents().size(); i++){
            if (eventManager.getId(eventManager.getEvents().get(i)).equals(eventId)
                    && eventManager.checkEventAvailable(eventManager.getEvents().get(i))
                    && eventManager.isNotPassed(eventManager.getEvents().get(i))) {
                return true;
            }
        }
        if (attendeeManager.getUserById(userId).get_vipstatus()) {
            for (int i = 0; i < eventManager.getVipEvents().size(); i++) {
                if (eventManager.getId(eventManager.getVipEvents().get(i)).equals(eventId)
                        && eventManager.checkEventAvailable(eventManager.getVipEvents().get(i))
                        && eventManager.isNotPassed(eventManager.getVipEvents().get(i))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * generate a string that enable attendees to view available events
     * @param userid the id of the given attandee
     * @return  a string that shows all available events
     */
    //return a string that contains all available events that an attendee can sign up
    public String viewAvailableEvent(String userid){
        StringBuilder view = new StringBuilder();
        if (eventManager.availableEvents().isEmpty()){
            if (!attendeeManager.getUserById(userid).get_vipstatus()) {
                return "There is no available event.\n";
            }else{
                if (eventManager.availableVipEvents().isEmpty()){
                    return "There is no available event.\n";
                }
            }
        }
        for (int i = 0; i < eventManager.availableEvents().size(); i++){
            view.append(eventManager.viewEvent(eventManager.availableEvents().get(i)) + "\n\n");
        }
        if (attendeeManager.getUserById(userid).get_vipstatus()){
            for (int i = 0; i < eventManager.availableVipEvents().size(); i++){
                view.append(eventManager.viewEvent(eventManager.availableVipEvents().get(i)) + "\n\n");
            }
        }

        return view.toString();
    }





    /**
     * check if the attendee is trying to sign up for a duplicated NormalEvent or VipeEvent(he has already
     * signed up for this event)
     * @param attendeeId    the Id of the attendee
     * @param eventId       the event Id that the attendee is trying to sign up
     * @return              true if he has already signed up for this event
     */
    public boolean checkDoubleSignUp(String attendeeId, String eventId) {

        String type = eventManager.findEvent(eventId).toString();
        if (type.equals("This is Normal Event")) {
            for (int i = 0; i < eventManager.getEvents().size(); i++) {
                if (eventManager.getId(eventManager.getEvents().get(i)).equals(eventId)) {
                    if (eventManager.getEvents().get(i).getAttendeesIDs().contains(attendeeId)) {
                        return true;
                    }
                }
            }
        } else if (type.equals("This is a VipEvent")) {
            for (int i = 0; i < eventManager.getVipEvents().size(); i++) {
                if (eventManager.getId(eventManager.getVipEvents().get(i)).equals(eventId)) {
                    if (eventManager.getVipEvents().get(i).getAttendeesIDs().contains(attendeeId)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
