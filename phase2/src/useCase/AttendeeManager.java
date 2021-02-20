package useCase;
import entities.Attendee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Constructing AttendeeManager object that store a list o attendees,
 * add, remove users to this user's friend list.
 * add, remove event from this user's event list.
 *
 * @author Ruicong Zong
 * @version %I%, %G%
 */

public class AttendeeManager implements Serializable{
    private List<Attendee> attendeeList = new ArrayList<>();

    /**
     * Add attendee to this system's attendee list.
     * @param newAttendee the attendee that is added.
     */
    public void addAttendee(Attendee newAttendee){
        attendeeList.add(newAttendee);
    }

    /**
     * Find if this an attendee is in the attendee list
     * @param attendeeID the attendee id that is passed in
     */
    public Attendee findAttendee(String attendeeID){
        for(Attendee attendee: attendeeList){
            if(attendee.getId().equals(attendeeID)){
                return attendee;
            }
        }
        return null;
    }

    /**
     * Add user to this attendee's friend list
     * @param user this attendee
     * @param friendId the friend id that is passed in to add in this attendee's friend list
     */
    public void addAttendeeFriendList(Attendee user, String friendId){
        user.getFriendList().add(friendId);
    }

    /**
     * Add event to this attendee's event list
     * @param user this attendee
     * @param eventId  the eventid passed in to add in this attendee's event list
     */
    public void addAttendeeEvent(Attendee user, String eventId){
        user.addEvent(eventId);
    }

    /**
     * Remove event from this attendee's event list
     * @param user this attendee
     * @param eventId the eventid passed in to remove in this attendee's event list
     */
    public void removeAttendeeEvent(Attendee user, String eventId){
        user.getEventSignUp().remove(eventId);
    }

    /**
     * Return the list of all attendees.
     * @return a list of all attendees.
     */
    public List<Attendee> getAttendeeList() {
        return attendeeList;
    }

    /**
     * create an attendee and add it to the list of all attendees, return its id.
     * @param name the name of the new attendee.
     * @param password the password set for this attendee.
     * @return the id of the new attendee.
     */
    public String createAndAddAttendee(String name, String password) {
        Attendee attendee = new Attendee(name, password);
        attendee.setId(attendeeList.size());
        addAttendee(attendee);
        return attendee.getId();
    }

    /**
     * Return the friend list of the attendee.
     * @param attendee the attendee.
     * @return this attendee's friend list.
     */
    public ArrayList<String> getFriendList(Attendee attendee){
        return attendee.getFriendList();
    }

    /**
     * Getter for the id of an attendee.
     * @param attendee the attendee.
     * @return the id of this attendee.
     */
    public String getId(Attendee attendee){
        return attendee.getId();
    }

    /**
     * Getter for the password of this attendee's account.
     * @param attendee the attendee.
     * @return this attendee's password.
     */
    public String getPassword(Attendee attendee){
        return attendee.getPassword();
    }

    /**
     * Setter for the attendee's password.
     * @param attendee the attendee.
     * @param pw the password that is to be setted.
     */
    public void setPassword(Attendee attendee, String pw) {attendee.setPassword(pw);}

    /**
     * Getter for the security question of this attendee.
     * @param attendee the attendee.
     * @return a pair of security question and its answer.
     */
    public Hashtable<String, String> getSecurityQus(Attendee attendee) {
        return attendee.getSecurityQus();
    }

    /**
     * Setter for the security question of this attendee.
     * @param attendee the attendee.
     * @param key the security question.
     * @param value the answer to the key.
     */
    public void setSecurityQus(Attendee attendee, String key, String value) {attendee.setSecurityQus(key, value);}

    /**
     * Getter for the name.
     * @param attendee the attendee.
     * @return this attendee's name.
     */
    public String getName(Attendee attendee){
        return attendee.getName();
    }

    /**
     * Change the status of this attendee (vip or not).
     * @param attendee the attendee.
     * @param status the new status to be changed for this attendee.
     */
    public void change_Vip(Attendee attendee, boolean status) {
        attendee.change_status(status);
    }

    /**
     * Check the vip status of the given attendee.
     * @param attendee the attendee.
     * @return the status of this attendee (vip or not).
     */
    public boolean check_Vip(Attendee attendee) {
        return attendee.get_vipstatus();
    }

    /**
     * Get the attendee object given its user id.
     * @param id the id of the attendee.
     * @return the attendee corresponding to this id.
     */
    public Attendee getUserById(String id){
        for (Attendee attendee: getAttendeeList()){
            if (attendee.getId().equals(id)){
                return attendee;
            }
        }
        return null;
    }

    /**
     * Get a list of event ids that has been signed up by this attendee.
     * @param attendee the attendee.
     * @return the event ids.
     */
    public ArrayList<String> getEventSignUp(Attendee attendee){
        return attendee.getEventSignUp();
    }
}

