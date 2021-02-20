package useCase;

import entities.UserRequest;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Constructing an UserRequestManager object that stores a list of UserRequest.
 * @author Ruicong Zong
 * @version %I%, %G%
 */

public class UserRequestManager implements Serializable {

    private ArrayList<UserRequest> userRequestList = new ArrayList<>();

    /**
     * Create a user request.
     * @param senderId the request sender's id.
     * @param message the message that is being requested.
     */
    public void createUserRequest(String senderId, String message){
        userRequestList.add(new UserRequest(senderId, message));
    }

    /**
     * Get a list of all user requests.
     * @return the list of all user requests.
     */
    public ArrayList<UserRequest> getAllUserRequests(){
        return userRequestList;
    }

    /**
     * Get a user request in the list of all requests at the given index.
     * @param index the index.
     * @return the request at the given index.
     */
    public UserRequest getUserRequest(int index){
        return userRequestList.get(index);
    }

    /**
     * Getter for the message of the given user request.
     * @param userRequest the user request.
     * @return the message of this request.
     */
    public String getMessage(UserRequest userRequest){
        return userRequest.getMessage();
    }

    /**
     * Getter for the status of the given user request (pending or addressed).
     * @param userRequest the user request.
     * @return the status of this request.
     */
    public String getStatus(UserRequest userRequest){
        return userRequest.getStatus();
    }

    /**
     * Getter for the sender id of the given user request.
     * @param userRequest the user request.
     * @return the send id of this request.
     */
    public String getSenderId(UserRequest userRequest){
        return userRequest.getSenderId();
    }
 }
