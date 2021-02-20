package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * For constructing an Organizer object that represent an organizer user.
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class Organizer extends User implements Serializable {

    private String name;
    private String id;
    private String password;
    private ArrayList<String> friendList;
    private ArrayList<String> eventOrganized;
    private Hashtable<String, String> securityQus;

    /**
     * Initialize a new organizer
     *
     * @param name      the name of this organizer account
     * @param password  the password of this organizer account
     */

    public Organizer(String name, String password){
        this.name = name;
        this.password = password;
        this.friendList = new ArrayList<String>();
        this.eventOrganized = new ArrayList<String>();
        this.securityQus = new Hashtable<String, String>();
    }

    /**
     * Getter of the name of this organizer account
     * @return  the name of this organizer account
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter of the Id of this organizer account
     * @return  the Id of this organizer account
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Setter of the Id of this organizer account
     * @param i   an int that represents the Id
     */
    public void setId(int i){
        this.id = "o" + String.valueOf(i);
    }

    /**
     * Setter of the name of this organizer account
     * @param newName   the new name of this account
     */
    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Getter of all events that his organizer has organized
     * @return   a list of strings that contains all Id of events that this organizer has organized
     */
    public ArrayList<String> getEventOrganized() {
        return eventOrganized;
    }

    /**
     * Getter of all friend of this organizer account
     * @return   a list of strings that contains Id of friends of this organizer account
     */
    public ArrayList<String> getFriendList(){
        return friendList;
    }

    /**
     * Getter of the password of this organizer account
     * @return   the password of this organizer account
     */
    public String getPassword() {
        return password;
    }

    /**
     * The getter of the security question of this Organizer account
     * @return the hashmap that contains both the security question and the answer
     */
    public Hashtable<String, String> getSecurityQus() {return securityQus;}

    /**
     * Setter of the password
     * @param ps the new password
     */
    @Override
    public void setPassword(String ps) {this.password = ps;}

    /**
     * The setter of the security question of this Organizer account
     * @param key the key of the hashtable
     * @param value the value refer by the key
     */
    public void setSecurityQus(String key, String value) { this.securityQus.put(key, value);}

    /**
     * Add the Id of an event to this organizer's event list
     * @param eventID  the Id of this event
     */
    public void addEvent(String eventID){
        eventOrganized.add(eventID);
    }

    /**
     * remove an organized event from the list of organized event ids
     * @param eventID the event id that wanted to be removed
     */
    public void removeEvent(String eventID){ eventOrganized.remove(eventID);}

    /**
     * Getter of all friend of this organizer account
     * @return   a list of strings that contains all Id of this organizer's friend
     */
    @Override
    public ArrayList<String> getFriendlist() {
        return this.friendList;
    }
}
