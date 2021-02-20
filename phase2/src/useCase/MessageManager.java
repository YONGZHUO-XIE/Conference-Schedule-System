package useCase;

import entities.Message;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * For constructing an MessageManager object that stores all ChatRooms
 * and manages the functionality of sending message
 *
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class MessageManager implements Serializable{

    //key is userid, value is chatroom
    private Hashtable<String, ChatRoom> allChatRoom = new Hashtable<String, ChatRoom>();

    /**
     * Getter for all pairs of user ids and chatrooms
     * @return the userid-chatroom pairs
     */
    public Hashtable<String, ChatRoom> getAllChatRoom() {
        return allChatRoom;
    }

    /**
     * A method that send the message from an user to another user
     * @param content the content of the message
     * @param sender the sender of the message
     * @param recipient the recipient of the message
     */
    public void sendMessageIndividual(String content, ChatRoom sender, ChatRoom recipient){
        Message mess = new Message(content, sender.getUser().getId(), recipient.getUser().getId(),
                sender.getUser().getName(), recipient.getUser().getName());
        sender.addMessageOutbox(mess);
        recipient.addMessageInbox(mess);
    }

    /**
     * A method that send the message from an user to a group of users.
     * @param content the content of the message
     * @param sender the sender of the message
     * @param recipient a group of the recipients
     */
    public void sendMessageAll(String content, ChatRoom sender, ArrayList<ChatRoom> recipient){
         for (ChatRoom u : recipient){
             sendMessageIndividual(content, sender, u);
         }
    }

    /**
     * A ToString method that describes all the information about the message
     * @param mess the message that we are going to send
     * @return A String describes the information about the message
     */
    public String viewMessage(Message mess){
        return  mess.getDateTime() + "\n" +
                "From:" + mess.getSenderName() + "\n" +
                "To:" + mess.getRecipientName() +"\n" +
                mess.getContent() + "\n";

    }

    /**
     * Create a new chatroom for a given user
     * @param user the user
     */
    public void createChatroom(User user){
        ChatRoom chatRoom = new ChatRoom(user);
        getAllChatRoom().put(user.getId(), chatRoom);
    }

    /**
     * Getter for the inbox of a given user, by the user id
     * @param id the user id
     * @return a list of messages in the inbox of this user
     */
    public ArrayList<Message> getInboxMessage(String id){
        return allChatRoom.get(id).getInbox();
    }

    /**
     * Getter for the outbox of a given user, by the user id
     * @param id the user id
     * @return a list of messages in the outbox of this user
     */
    public ArrayList<Message> getOutboxMessage(String id){
        return allChatRoom.get(id).getOutbox();
    }

    /**
     * Getter for the list of messages in the user's inbox that has been read
     * @param id the user id
     * @return the list of read messages in the inbox of this user
     */
    public ArrayList<Message> getReadMessage(String id){
        ArrayList<Message> mess = new ArrayList<Message>();
        if (!getInboxMessage(id).isEmpty()) {
            for (Message message : getInboxMessage(id)) {
                if (message.getHasRead()) {
                    mess.add(message);
                }
            }
        }
        return mess;
    }

    /**
     * Getter for the list of messages in the user's inbox that are unread
     * @param id the user id
     * @return the list of unread messages in the inbox of this user
     */
    public ArrayList<Message> getUnreadMessage(String id){
        ArrayList<Message> mess = new ArrayList<Message>();
        if (!getInboxMessage(id).isEmpty()) {
            for (Message message : getInboxMessage(id)) {
                if (!message.getHasRead()) {
                    mess.add(message);
                }
            }
        }
        return mess;
    }

    /**
     * Set the status of the given list of messages to be "read"
     * @param mess the list of messages
     */
    public void setHasReadAll(ArrayList<Message> mess) {
        for (Message message : mess) {
            message.setHasRead();
        }
    }

    /**
     * Check whether the given message has been read
     * @param mess the message
     * @return true for read, false for unread
     */
    public boolean hasRead(Message mess){
        return mess.getHasRead();
    }

    /**
     * Set the status of the given message to be unread
     * @param mess the message
     */
    public void setAsUnread(Message mess){
        mess.setAsUnread();
    }

    /**
     * Delete the message from the inbox of the given chatroom
     * @param chatroom the chatroom
     * @param mess the message
     * @return the string indicating successful deletion of the message
     */
    public String deleteInboxMessage(ChatRoom chatroom, Message mess){
        chatroom.deleteInboxMessage(mess);
        return "You have deleted this message.";
    }

    /**
     * Delete the message from the outbox of the given chatroom
     * @param chatroom the chatroom
     * @param mess the message
     * @return the string indicating successful deletion of the message
     */
    public String deleteOutboxMessage(ChatRoom chatroom, Message mess){
        chatroom.deleteOutboxMessage(mess);
        return "You have deleted this message.";
    }

    /**
     * Checks whether the message has been read; if yes, set it to be unread,
     * if no, return a message to indicate the failure of this operation
     * @param mess the message to be set to unread
     * @return a string indicating success/failure of setting the message to be unread
     */
    public String checkAndSetAsUnread(Message mess){
        if (hasRead(mess)) {
            setAsUnread(mess);
            return "You have set this message as unread.";
        }else{
            return "Please try again.";
        }
    }

}
