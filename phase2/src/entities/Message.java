package entities;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * For constructing an Message object that represent a message.
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class Message implements Serializable {

    private LocalDateTime dateTime;
    private String content;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private boolean hasRead = false;

    /**
     * Initializing a message
     * @param content the content of the message
     * @param senderId  the Id of the sender of the message
     * @param recipientId the Id of the recipient of the message
     */
    public Message(String content, String senderId, String recipientId, String senderName, String recipientName){
        this.content = content;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Getter of the content of the message
     * @return the content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter of the recipient of the message
     * @return the recipient of the message
     */
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * Getter of the sender of the message
     * @return the sender of the message
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Getter of the dateline of the message
     * @return the dateline of the message
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * getter for the recipient name of the message
     * @return the recipient name
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * getter for the sender name of the message
     * @return the sender name
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * getter for the status of message: read or unread
     * @return status of message
     */
    public boolean getHasRead(){
        return hasRead;
    }

    /**
     * set the status of message to be read
     */
    public void setHasRead(){
        hasRead = true;
    }

    /**
     * set the status of message to be unread
     */
    public void setAsUnread(){
        hasRead = false;
    }
}
