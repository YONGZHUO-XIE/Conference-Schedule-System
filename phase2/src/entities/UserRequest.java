package entities;

import java.io.Serializable;
/**
 * An abstract class that represents UserRequest object.
 * @author Ruicong Zong
 * @version %I%, %G%
 */

public class UserRequest  implements Serializable {
    private String senderId;
    private String message;
    private String status = "pending"; // either pending or addressed

    /**
     * Initialize a UserRequest Object.
     * @param senderId the sender id of the request.
     * @param message the message requested to be sent.
     */
    public UserRequest(String senderId, String message) {
        this.senderId = senderId;
        this.message = message;
    }

    /**
     * change the status of the user request to be "addressed".
     */
    public void address(){
        status = "addressed";
    }

    /**
     * getter for the request sender id.
     * @return the sender id.
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * getter for the request message context.
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter for the status of the request.
     * @return the status (addressed or not).
     */
    public String getStatus() {
        return status;
    }
}
