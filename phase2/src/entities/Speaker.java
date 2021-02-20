package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * For constructing a Speaker object that represent a speaker user.
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class Speaker extends User implements Serializable {

    private String name;
    private String id;
    private String password;
    private ArrayList<String> friendList;
    private ArrayList<String> eventSpoke;
    private Hashtable<String, String> securityQus;

    /**
     * Initializing a speaker
     * @param name the name of the speaker account
     * @param password the password the speaker account
     */
    public Speaker(String name, String password){
        this.name = name;
        this.password = password;
        this.friendList = new ArrayList<String>();
        this.eventSpoke = new ArrayList<String>();
        this.securityQus = new Hashtable<String, String>();
    }

    /**
     * The getter of the name of the speaker
     * @return A String that describes the name of the speaker
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * The getter of the id of the speaker
     * @return A String that describes the id of the speaker
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * The setter of the id of the speaker
     * @param i the id that we wish to assign to this speaker
     */
    public void setId(int i){
        this.id = "s" + String.valueOf(i);
    }

    /**
     * The setter of the name of the speaker
     * @param newName the name that we wish to assign to this speaker
     */
    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * The getter of all events that this speaker will speak for
     * @return An ArrayList describes all the event that this speaker will speak for
     */
    public ArrayList<String> getEventSpoke() {
        return eventSpoke;
    }

    /**
     * The getter of all friends that this speaker has.
     * @return An ArrayList describes all the friends that this speaker has.
     */
    public ArrayList<String> getFriendList(){
        return friendList;
    }

    /**
     * The getter of the password of this speaker account
     * @return The password of this speaker account
     */
    public String getPassword() {
        return password;
    }

    /**
     * The getter of the security question of this speaker account
     * @return the hashmap that contains both the security question and the answer
     */
    public Hashtable<String, String> getSecurityQus() {return securityQus;}

    /**
     * Setter of the password
     * @param ps the new password
     */
    public void setPassword(String ps) {this.password = ps;}

    /**
     * A method that assign a certain event to this speaker
     * @param eventID the event that we wish to assign
     * @return Return True if the event assigns successfully. Return False if not.
     */
    public boolean addEvent(String eventID){
        return this.eventSpoke.add(eventID);
    }

    /**
     * The setter of the security question of this speaker account
     * @param key the key of the hashtable
     * @param value the value refer by the key
     */
    public void setSecurityQus(String key, String value) { this.securityQus.put(key, value);}

    /**
     * A method that remove a certain event from this speaker
     * @param eventID the event that we wish to remove
     * @return Return True if the event removes successfully. Return False if not.
     */
    public boolean removeEvent(String eventID){
        return this.eventSpoke.remove(eventID);
    }

    /**
     * Getter of friend list of the speaker
     * @return An ArrayList describes all the friends that this speaker has.
     */
    @Override
    public ArrayList<String> getFriendlist() {
        return this.friendList;
    }
}
