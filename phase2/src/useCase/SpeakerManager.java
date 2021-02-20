package useCase;

import entities.Speaker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Constructing an SpeakerManager object that stores a list of speakers.
 * add event for a specific Speaker
 * add friendid to a specific Speaker.
 *
 * @author Zhao Han Huang
 * @version %I%, %G%
 */
public class SpeakerManager implements Serializable{

    private List<Speaker> speakerList = new ArrayList<>();

    /**
     * Getter for all Speaker stored in SpeakerManager.Return a list of Speaker.
     * @return all Speaker stored.
     */
    public List<Speaker> getSpeakerList() {
        return speakerList;
    }

    /**
     * Add friendId to this speaker's friendlist.
     * @param user the Speaker that is passed in for adding.
     * @param friendId the id of the speaker 's friend.
     */
    public void addSpeakerFriendList(Speaker user, String friendId) {
        user.getFriendList().add(friendId);
    }

    /**
     * Find a speaker using the given speaker id
     * @param speakerID the id
     * @return the speaker corresponding to this id
     */
    public Speaker findSpeaker(String speakerID){
        for(Speaker speaker: speakerList){
            if(speaker.getId().equals(speakerID)){
                return speaker;
            }
        }
        return null;
    }

    /**
     * Add an Event to this Speaker.The Event is passed in as an eventid.
     * @param speaker the Speaker we pass in.
     * @param eventid the id of the event.
     */
    public void addEventforSpeaker(Speaker speaker, String eventid) {
        speaker.addEvent(eventid);
    }

    /**
     * Remove an Event to this Speaker. The Event is passed in as an eventid.
     * @param speaker the Speaker we pass in.
     * @param eventID the id of the event.
     */
    public void removeEventForSpeaker(Speaker speaker, String eventID){
        speaker.removeEvent(eventID);
    }

    /**
     * Create a Speaker and add this Speaker to the list of speakers stored in
     * SpeakerManager, return its id.
     * @param name the name of this speaker.
     * @param password the password of this Speaker created.
     */
    public String createAndAddSpeaker(String name, String password) {
        Speaker speaker = new Speaker(name, password);
        speaker.setId(speakerList.size());
        this.speakerList.add(speaker);
        return speaker.getId();
    }

    /**
     * Getter for the id of the given speaker
     * @param speaker the speaker
     * @return this speaker's id
     */
    public String getId(Speaker speaker){
        return speaker.getId();
    }

    /**
     * Getter for the password of the given speaker
     * @param speaker the speaker
     * @return this speaker's password
     */
    public String getPassword(Speaker speaker){
        return speaker.getPassword();
    }

    /**
     * Getter for the name of the given speaker
     * @param speaker the speaker
     * @return this speaker's name
     */
    public String getName(Speaker speaker){ return speaker.getName(); }

    /**
     * Setter for the password of the given speaker
     * @param speaker the speaker
     * @param pw the new password to be set
     */
    public void setPassword(Speaker speaker, String pw) {speaker.setPassword(pw);}

    /**
     * Getter for the security questions of the given speaker
     * @param speaker the speaker
     * @return tthe question-answer pair for the speaker
     */
    public Hashtable<String, String> getSecurityQus(Speaker speaker) {return speaker.getSecurityQus();}

    /**
     * Setter for the security questions of the given speaker
     * @param speaker the speaker
     * @param key the security question
     * @param value the answer to the key
     */
    public void setSecurityQus(Speaker speaker, String key, String value) {speaker.setSecurityQus(key, value);}

    /**
     * Getter for the friend list of the given speaker
     * @param speaker the speaker
     * @return this speaker's friend list
     */
    public ArrayList<String> getFriendList(Speaker speaker){
        return speaker.getFriendList();
    }

    /**
     * Getter for the list of events that the given speaker will speak
     * @param speaker the speaker
     * @return the list of events that this speaker will participate in
     */
    public ArrayList<String> getSpeakerEventSpoke(Speaker speaker){
        return speaker.getEventSpoke();
    }

    /**
     * Get a speaker using the given speaker id
     * @param id the id
     * @return the speaker corresponding to the given id
     */
    public Speaker getUserById(String id){
        for (Speaker speaker: getSpeakerList()){
            if (speaker.getId().equals(id)){
                return speaker;
            }
        }
        return null;
    }
}
