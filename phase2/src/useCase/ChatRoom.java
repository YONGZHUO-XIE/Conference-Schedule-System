package useCase;

import   entities.Message;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * For constructing an ChatRoom object that stores user's inbox and outbox
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class ChatRoom implements Serializable {

    private User user;
    private ArrayList<Message> inbox;
    private ArrayList<Message> outbox;

    /**
     * Initialize a ChatRoom object
     * @param user user of this chatroom
     */
    public ChatRoom(User user){
        this.user = user;
        this.inbox = new ArrayList<>();
        this.outbox = new ArrayList<>();
    }

    /**
     * Add a message to the inbox
     * @param message the message that user received
     */
    public void addMessageInbox(Message message){
        this.inbox.add(message);
    }

    /**
     * Add a message to the outbox
     * @param message the message that user sent
     */
    public void addMessageOutbox(Message message){
        this.outbox.add(message);
    }

    /**
     * Getter of the inbox of this ChatRoom
     * @return the inbox of this ChatRoom
     */
    public ArrayList<Message> getInbox() {
        return inbox;
    }

    /**
     * Getter of the outbox of this ChatRoom
     * @return the outbox of this ChatRoom
     */
    public ArrayList<Message> getOutbox() {
        return outbox;
    }

    /**
     * Getter of the user of this ChatRoom
     * @return the user of this ChatRoom
     */
    public User getUser() {
        return user;
    }

    /**
     * Delete the message from the inbox
     * @param mess the message to be deleted from the inbox
     */
    public void deleteInboxMessage(Message mess){
        inbox.remove(mess);
    }

    /**
     * Delete the message from the outbox
     * @param mess the message to be deleted from the outbox
     */
    public void deleteOutboxMessage(Message mess){
        outbox.remove(mess);
    }

}
