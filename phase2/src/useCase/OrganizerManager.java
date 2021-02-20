package useCase;

import entities.Organizer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Login the user with the ID and password entered by the user.
 *
 * @author Minhui
 * @version %I%, %G%
 */
public class OrganizerManager implements Serializable{

    // Store a list of all Organizers.
    private ArrayList<Organizer> allOrganizer = new ArrayList<Organizer>();

    /**
     * Add organizers to the organizer list
     * @param organizer the Organizer object
     */
    public void addOrganizer(Organizer organizer){
        this.allOrganizer.add(organizer);
    }

    /**
     * Add an Event object to a specific organizer's event list
     * @param organizer the Organizer object
     * @param eventID the event Id
     */
    public void addEvent(Organizer organizer, String eventID){
        organizer.addEvent(eventID);
    }

    /**
     * Remove an event from the given organizer's event list
     * @param organizer the organizer
     * @param eventID the id of the event to be removed
     */
    public void removeEvent(Organizer organizer, String eventID) {organizer.removeEvent(eventID);}

    /**
     * Getter of all organizers registered
     * @return a list of Organizers that contains all organizers
     */
    public ArrayList<Organizer> getAllOrganizer() {
        return allOrganizer;
    }

    /**
     * Find an organizer given an id
     * @param organizerID the organizer id
     * @return the organizer corresponding id
     */
    public Organizer findOrganizer(String organizerID){
        for(Organizer organizer: allOrganizer){
            if(organizer.getId().equals(organizerID)){
                return organizer;
            }
        }
        return null;
    }

    /**
     * Create a new Organizer object and add it to allOrganizer
     * @param name the name of the organizer account
     * @param password the password of the organizer account
     * @return the new Organizer object
     */
    public String createAndAddOrganizer(String name, String password){
        Organizer organizer = new Organizer(name, password);
        organizer.setId(allOrganizer.size());
        addOrganizer(organizer);
        return organizer.getId();
    }

    /**
     * Add another user to the organizer's friend list
     * @param organizer the organizer who is adding friend
     * @param userid the id of a user who is being added as a friend
     */
    public void addFriend(Organizer organizer, String userid){
        organizer.getFriendList().add(userid);
    }

    /**
     * Getter for the organizer's id
     * @param organizer the organizer
     * @return this organizer's id
     */
    public String getId(Organizer organizer){
        return organizer.getId();
    }

    /**
     * Getter for the organizer's password
     * @param organizer the organizer
     * @return this organizer's password
     */
    public String getPassword(Organizer organizer){
        return organizer.getPassword();
    }

    /**
     * Getter for the organizer's name
     * @param organizer the organizer
     * @return this organizer's name
     */
    public String getName(Organizer organizer){ return organizer.getName(); }

    /**
     * Setter for the organizer's password
     * @param organizer the organizer
     * @param pw the new password to be set
     */
    public void setPassword(Organizer organizer, String pw) {organizer.setPassword(pw);}

    /**
     * Getter of the security question of this organizer
     * @param organizer the organizer
     * @return the question-answer pair
     */
    public Hashtable<String, String> getSecurityQus(Organizer organizer) {
        return organizer.getSecurityQus();
    }

    /**
     * Getter of the security question of this organizer
     * @param organizer the organizer
     * @param key the security question
     * @param value the answer to the key
     */
    public void setSecurityQus(Organizer organizer, String key, String value) {organizer.setSecurityQus(key, value);}

    /**
     * Getter of the friend list of this organizer
     * @param organizer the organizer
     * @return the list of friends of this organizer
     */
    public ArrayList<String> getFriendList(Organizer organizer){
        return organizer.getFriendList();
    }

    /**
     * Getter for all the events organized by the given organizer
     * @param organizer the organizer
     * @return the list of organized events
     */
    public ArrayList<String> getOrganizerEventHold(Organizer organizer){
        return organizer.getEventOrganized();
    }

    /**
     * Get the organizer that has the given id
     * @param id the id
     * @return the organizer corresponding to the given id
     */
    public Organizer getUserById(String id){
        for (Organizer organizer: getAllOrganizer()){
            if (organizer.getId().equals(id)){
                return organizer;
            }
        }
        return null;
    }
}
