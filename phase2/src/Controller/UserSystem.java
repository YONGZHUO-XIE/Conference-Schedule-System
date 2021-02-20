package Controller;

import useCase.*;

import java.util.ArrayList;

/**
 * Assemble of some user related methods that are called by UI
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class UserSystem {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private EventManager eventManager;
    private Identification identification = new Identification();

    /**
     * The initializer of the UserSystem.
     * @param attendeeManager the AttendeeManager that works for this system
     * @param organizerManager the OrganizerManager that works for this system
     * @param speakerManager the SpeakerManager that works for this system
     * @param eventManager the EventManager that works for this system
     */
    public UserSystem(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                      SpeakerManager speakerManager, EventManager eventManager){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.eventManager = eventManager;
    }


    /**
     * Shows all events in which the attendee with the id registered
     * @param id the id of the user
     * @return A String describes all events that this user registered
     */
    public String attendeeShowEventsRegistered(String id){
        StringBuilder view = new StringBuilder();
        if (attendeeManager.getEventSignUp(attendeeManager.getUserById(id)).isEmpty()){
            return "There is no registered event.\n";
        }
        for (String i : attendeeManager.getEventSignUp(attendeeManager.getUserById(id))){
            view.append(eventManager.viewEvent(eventManager.getEventById(i)) + "\n\n");
        }
        return view.toString();
    }

    public int attendeeEventsTotal(String id){
        if (attendeeManager.getEventSignUp(attendeeManager.getUserById(id)).isEmpty()){
            return 0;
        } else {
            return attendeeManager.getEventSignUp(attendeeManager.getUserById(id)).size();
        }
    }

    /**
     * Verify the registration of the user.
     * @param attendeeId the id of the attendee
     * @param eventId the id of the event
     * @return Return True if Attendee has registered this event. Return False if not.
     */
    public boolean isRegistered(String attendeeId, String eventId){
        if (attendeeManager.getEventSignUp(attendeeManager.getUserById(attendeeId)).isEmpty()){
            return false;
        }
        for (String i : attendeeManager.getEventSignUp(attendeeManager.getUserById(attendeeId))){
            if (i.equals(eventId)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getFriendsName(ArrayList<String> friendsId){
        ArrayList<String> names = new ArrayList<>();
        for (String id : friendsId){
            if (identification.checkIdentity(id).equals("Attendee")){
                names.add(attendeeManager.getName(attendeeManager.getUserById(id)));
            }
            else if (identification.checkIdentity(id).equals("Speaker")){
                names.add(speakerManager.getName(speakerManager.getUserById(id)));
            }
            else{
                names.add(organizerManager.getName(organizerManager.getUserById(id)));
            }
        }
        return names;
    }

    /**
     * A method that shows this user's friendlist
     * @param userId the id of the user
     * @return return a string that shows this user's friendlist
     */
    public String getUserFriendlist(String userId){
        StringBuilder view = new StringBuilder();
        if (identification.checkIdentity(userId).equals("Attendee")) {
            if (attendeeManager.getFriendList(attendeeManager.getUserById(userId)).isEmpty()) {
                return "You have no friend.\n";
            }
            for (int i = 0; i < getFriendsName(attendeeManager.getUserById(userId).getFriendlist()).size(); i++) {
                String name = getFriendsName(attendeeManager.getUserById(userId).getFriendlist()).get(i);
                String id = attendeeManager.getUserById(userId).getFriendlist().get(i);
                view.append(name + ": " + id + "\n");
            }
        } else if (identification.checkIdentity(userId).equals("Organizer")) {
            if (organizerManager.getFriendList(organizerManager.getUserById(userId)).isEmpty()) {
                return "You have no friend.\n";
            }
            for (int i = 0; i < getFriendsName(organizerManager.getUserById(userId).getFriendlist()).size(); i++) {
                String name = getFriendsName(organizerManager.getUserById(userId).getFriendlist()).get(i);
                String id = organizerManager.getUserById(userId).getFriendlist().get(i);
                view.append(name + ": " + id + "\n");
            }
        } else{
            if (speakerManager.getFriendList(speakerManager.getUserById(userId)).isEmpty()) {
                return "You have no friend.\n";
            }
            for (int i = 0; i < getFriendsName(speakerManager.getUserById(userId).getFriendlist()).size(); i++) {
                String name = getFriendsName(speakerManager.getUserById(userId).getFriendlist()).get(i);
                String id = speakerManager.getUserById(userId).getFriendlist().get(i);
                view.append(name + ": " + id + "\n");
            }
        }
        return view.toString();
    }

    /**
     * Verify whether or not this user in another user's friend list
     * @param userId the id of the user
     * @param friendId the id of another user
     * @return return true if the user with friendId is a friend of in the user with userId
     */
    public boolean isFriend(String userId, String friendId){
        if(identification.checkIdentity(userId).equals("Attendee")){
            if(attendeeManager.getFriendList(attendeeManager.getUserById(userId)).isEmpty()){
                return false;
            }
            for (String s : attendeeManager.getFriendList(attendeeManager.getUserById(userId))){
                if (s.equals(friendId)){
                    return true;
                }
            }
            return false;
        } else if(identification.checkIdentity(userId).equals("Speaker")){
            if(speakerManager.getFriendList(speakerManager.getUserById(userId)).isEmpty()){
                return false;
            }
            for (String s : speakerManager.getFriendList(speakerManager.getUserById(userId))){
                if (s.equals(friendId)){
                    return true;
                }
            }
            return false;
        } else {
            if(organizerManager.getFriendList(organizerManager.getUserById(userId)).isEmpty()){
                return false;
            }
            for (String s : organizerManager.getFriendList(organizerManager.getUserById(userId))){
                if (s.equals(friendId)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * A method shows all events that this organizer holds
     * @param userId the id of the organizer
     * @return return a string that represents all events in which the organizer hold
     */
    public String organizerEventHold(String userId){
        StringBuilder view = new StringBuilder();
        if (organizerManager.getOrganizerEventHold(organizerManager.getUserById(userId)).isEmpty()){
            return "You did not hold any event.\n";
        }
        for(String s : organizerManager.getOrganizerEventHold(organizerManager.getUserById(userId))){
            view.append(eventManager.viewEvent(eventManager.findEvent(s)) + "\n\n");
        }
        return view.toString();
    }

    public int organizerEventsTotal(String id){
        if (organizerManager.getOrganizerEventHold(organizerManager.getUserById(id)).isEmpty()){
            return 0;
        } else {
            return organizerManager.getOrganizerEventHold(organizerManager.getUserById(id)).size();
        }
    }

    /**
     * A method shows all events that this speaker speaks for
     * @param userId the id of the speaker
     * @return return a string that represents all events in which the speaker speaks.
     */
    public String speakerEventSpoke(String userId){
        StringBuilder view = new StringBuilder();
        if (speakerManager.getSpeakerEventSpoke(speakerManager.getUserById(userId)).isEmpty()){
            return "There is no assigned event to you.\n";
        }
        for(String s : speakerManager.getSpeakerEventSpoke(speakerManager.getUserById(userId))){
            view.append(eventManager.viewEvent(eventManager.getEventById(s)) + "\n\n");
        }
        return view.toString();
    }

    public int speakerEventsTotal(String id){
        if (speakerManager.getSpeakerEventSpoke(speakerManager.getUserById(id)).isEmpty()){
            return 0;
        } else {
            return speakerManager.getSpeakerEventSpoke(speakerManager.getUserById(id)).size();
        }
    }

    /**
     * generate a list of string that contains all of id of the events that the speaker has been assigned to
     * @param userId    the user id of the speaker
     * @return          a list of string of event Id
     */
    public ArrayList<String> speakerEvents(String userId){
        return speakerManager.getSpeakerEventSpoke(speakerManager.getUserById(userId));
    }

    /**
     * Get the schedule for the user.
     *
     * @param userId the id of the user who want to get his or her schedule.
     * @return
     */

    public String getSchedule(String userId){
        if (identification.checkIdentity(userId).equals("Attendee")) {
            return attendeeShowEventsRegistered(userId);
        }else if(identification.checkIdentity(userId).equals("Organizer")){
            return organizerEventHold(userId);
        }else{
            return speakerEventSpoke(userId);
        }
    }

    public int getEventTotal(String userId){
        if (identification.checkIdentity(userId).equals("Attendee")) {
            return attendeeEventsTotal(userId);
        }else if(identification.checkIdentity(userId).equals("Organizer")){
            return organizerEventsTotal(userId);
        }else{
            return speakerEventsTotal(userId);
        }
    }

}

