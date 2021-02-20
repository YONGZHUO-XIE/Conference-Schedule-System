package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * For constructing an Attendee object that represent an attendee user.
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class Attendee extends User implements Serializable {

    private String name;
    private String id;
    private String password;
    private ArrayList<String> friendList;
    private ArrayList<String> eventSignUp;
    private boolean vipstatus;
    private Hashtable<String, String> securityQus;

    /**
     *Initialize a new Attendee
     * @param name         name of this attendee account
     * @param password     password of this attendee account
     */
    public Attendee(String name, String password){
        this.name = name;
        this.password = password;
        this.friendList = new ArrayList<String>();
        this.eventSignUp = new ArrayList<String>();
        this.securityQus = new Hashtable<String, String>();
    }

    /**
     *Getter of name
     * @return  name of this attendee account
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter of Id
     * @return Id of this attendee account
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Setter of Id
     * @param i     the new Id
     */
    public void setId(int i){
        this.id = "a" + String.valueOf(i);
    }

    /**
     * Setter of name
     * @param newName   the new name
     */
    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Getter of the friend list of this account
     * @return  the friend list of this account
     */
    public ArrayList<String> getFriendList(){
        return friendList;
    }

    /**
     * Getter of all events that this attendee has signup up
     * @return  a list of string that contains all Id of events that his attendee has signup up
     */
    public ArrayList<String> getEventSignUp() {
        return eventSignUp;
    }

    /**
     * Getter of the password of this attendee account
     * @return the password of this attendee account
     */
    public String getPassword() {
        return password;
    }

    /**
     * The getter of the security question of this Organizer account
     * @return the hashmap that contains both the security question and the answer
     */
    public Hashtable<String, String> getSecurityQus() {return securityQus;}

    public void setSecurityQus(String key, String value) { this.securityQus.put(key, value);}

    public void setPassword(String ps) {this.password = ps;}

    /**
     * Getter of all friends of this account
     * @return a list of strings that contains all Id of this attendee's friend
     */
    @Override
    public ArrayList<String> getFriendlist() {
        return this.friendList;
    }

    /**
     * add a new event to the attendee's sign up list, given the event id
     * @param eventId the event id
     */
    public void addEvent(String eventId){
        this.eventSignUp.add(eventId);
    }

    /**
     * change the status of this attendee (whether the attendee is a vip or not)
     * @param status the new status
     */
    public void change_status(boolean status){
        vipstatus = status;
    }

    /**
     * getter for the current vip status
     * @return the vip status (whether the attendee is a vip)
     */
    public boolean get_vipstatus(){
        return vipstatus;
    }
}