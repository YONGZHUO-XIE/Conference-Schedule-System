package Controller;

import entities.Attendee;
import org.w3c.dom.Attr;
import useCase.*;

/**
 * Login the user with the ID and password entered by the user.
 *
 * @author Minhui
 * @version %I%, %G%
 */
// start
public class LoginSystem {
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private Identification identification;

    /**
     *
     *
     * @param attendeeManager   attendeeManager from use case AttendeeManager
     * @param organizerManager  organizerManager from use case OrganizerManager
     * @param speakerManager    speakerManager from use case SpeakerManager
     */
    public LoginSystem(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.identification = new Identification();
    }

    //return true if this id exist

    /**
     * Check whether the user of given id is valid and registered
     *
     * @param id id entering by the user who want to login
     * @return true or false -- if the user is registered return ture; otherwise, return false
     */
    public boolean checkIdValidity(String id){
        if (identification.checkIdentity(id).equals("Attendee")){
            for (int i =0; i < attendeeManager.getAttendeeList().size(); i++){
                if (attendeeManager.getId(attendeeManager.getAttendeeList().get(i)).equals(id)){
                    return true;
                }
            }
        }
        if (identification.checkIdentity(id).equals("Organizer")){
            for (int i = 0; i < organizerManager.getAllOrganizer().size(); i++){
                if (organizerManager.getId(organizerManager.getAllOrganizer().get(i)).equals(id)){
                    return true;
                }
            }
        }
        if (identification.checkIdentity(id).equals("Speaker")){
            for (int i = 0; i < speakerManager.getSpeakerList().size(); i++){
                if (speakerManager.getId(speakerManager.getSpeakerList().get(i)).equals(id)){
                    return true;
                }
            }
        }
        return false;
    }

    // return true if password is correct

    /**
     * Check whether the given password and given id matches
     *
     * @param id id entering by the user who want to login
     * @param password password entering by the user who want to login
     * @return true or false -- if the entering password is correct, return ture; otherwise, return false
     */
    public Boolean checkPassword(String id, String password){
        if (identification.checkIdentity(id).equals("Organizer")){
            for (int i = 0; i < organizerManager.getAllOrganizer().size(); i++) {
                if (organizerManager.getId(organizerManager.getAllOrganizer().get(i)).equals(id)
                        && organizerManager.getPassword(organizerManager.getAllOrganizer().get(i)).equals(password)) {
                    return true; }
            }
        }
        if (identification.checkIdentity(id).equals("Attendee")) {
            for (int i = 0; i < attendeeManager.getAttendeeList().size(); i++) {
                if (attendeeManager.getId(attendeeManager.getAttendeeList().get(i)).equals(id)
                        && attendeeManager.getPassword(attendeeManager.getAttendeeList().get(i)).equals(password)) {
                    return true; }
            }
        }
        if (identification.checkIdentity(id).equals("Speaker")){
            for (int i = 0; i < speakerManager.getSpeakerList().size(); i++){
                if (speakerManager.getId(speakerManager.getSpeakerList().get(i)).equals(id)
                        && speakerManager.getPassword(speakerManager.getSpeakerList().get(i)).equals(password)) {
                    return true;}
            }
        }
        return false;
    }

    //login method
    /**
     * This method examine whether the login action by the user is valid and legit.
     *
     * @param id id entering by the user who want to login
     * @param password password entering by the user who want to login
     * @return true if the user log in successfully
     */
    public boolean logIn(String id, String password){
        return checkIdValidity(id) && checkPassword(id,password);
    }

    public void setSecurityQuestion(String id, String key, String value){
        for (int i = 0; i < organizerManager.getAllOrganizer().size(); i++){
            if (organizerManager.getId(organizerManager.getAllOrganizer().get(i)).equals(id)){
                organizerManager.getAllOrganizer().get(i).setSecurityQus(key, value);
            }
        }

        for (int i = 0; i < speakerManager.getSpeakerList().size(); i++){
            if (speakerManager.getId(speakerManager.getSpeakerList().get(i)).equals(id)){
                speakerManager.getSpeakerList().get(i).setSecurityQus(key, value);
            }
        }

        for (int i = 0; i < attendeeManager.getAttendeeList().size(); i++){
            if (attendeeManager.getId(attendeeManager.getAttendeeList().get(i)).equals(id)){
                attendeeManager.getAttendeeList().get(i).setSecurityQus(key, value);
            }
        }
    }

    /**
     * Check if the answer ofr security question is correct
     * @param id    id of the user
     * @param key   security question
     * @param value answer to the security question
     * @return  true if the answer is correct
     */
    public boolean checkSecurityQues(String id, String key, String value){
        if (identification.checkIdentity(id).equals("Attendee")){
            if (attendeeManager.getUserById(id).getSecurityQus().containsKey(key)){
                if (attendeeManager.getUserById(id).getSecurityQus().get(key).equals(value)){
                    return true;
                }
            }
        }else if (identification.checkIdentity(id).equals("Organizer")){
            if (organizerManager.getUserById(id).getSecurityQus().containsKey(key)){
                if (organizerManager.getUserById(id).getSecurityQus().get(key).equals(value)){
                    return true;
                }
            }
        }else{
            if (speakerManager.getUserById(id).getSecurityQus().containsKey(key)){
                if (speakerManager.getUserById(id).getSecurityQus().get(key).equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * reset a password
     * @param id    id of the user
     * @param pw    new password
     */
    public void resetPassword(String id, String pw){
        for (int i = 0; i < speakerManager.getSpeakerList().size(); i++){
            if (speakerManager.getId(speakerManager.getSpeakerList().get(i)).equals(id))
                speakerManager.setPassword(speakerManager.getSpeakerList().get(i), pw);
        }

        for (int i = 0; i < organizerManager.getAllOrganizer().size(); i++){
            if (organizerManager.getId(organizerManager.getAllOrganizer().get(i)).equals(id))
                organizerManager.setPassword(organizerManager.getAllOrganizer().get(i), pw);
        }

        for (int i = 0; i < attendeeManager.getAttendeeList().size(); i++){
            if (attendeeManager.getId(attendeeManager.getAttendeeList().get(i)).equals(id))
                attendeeManager.setPassword(attendeeManager.getAttendeeList().get(i), pw);
        }
    }

    /**
     * check if the user has answered a question
     * @param userId    the user id
     * @param key       the security question that is being checked
     * @return  true if the user has answered this security question
     */
    public boolean containsSeqQues(String userId, String key){
        if (identification.checkIdentity(userId).equals("Attendee")){
            return attendeeManager.getUserById(userId).getSecurityQus().containsKey(key);
        }else if (identification.checkIdentity(userId).equals("Organizer")){
            return organizerManager.getUserById(userId).getSecurityQus().containsKey(key);
        }else{
            return speakerManager.getUserById(userId).getSecurityQus().containsKey(key);
        }
    }
}
