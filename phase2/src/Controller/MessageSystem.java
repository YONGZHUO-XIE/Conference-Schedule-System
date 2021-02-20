package Controller;


import useCase.*;
import java.util.ArrayList;

/**
 * Assemble of some message related methods that are called when users request to send method, view inbox, etc
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class MessageSystem {

    private AttendeeManager attendeeManager;
    private SpeakerManager speakerManager;
    private MessageManager messageManager;
    private EventManager eventManager;
    private UserRequestManager userRequestManager;

    /**
     * The initializer of the MessageSystem
     * @param attendeeManager the AttendeeManager that works for this system
     * @param speakerManager the SpeakerManager that works for this system
     * @param messageManager the MessageManager that works for this system
     * @param eventManager the EventManager that works for this system
     */
    public MessageSystem(AttendeeManager attendeeManager, SpeakerManager speakerManager,
                         MessageManager messageManager, EventManager eventManager, UserRequestManager userRequestManager) {
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.messageManager = messageManager;
        this.eventManager = eventManager;
        this.userRequestManager = userRequestManager;
    }


    /**
     * Send message to a single user
     * if sender or recipient do not have a ChatRoom(i.e.never sent a message/never received a message),
     * create a ChatRoom for them. Add the message to their inbox/outbox as record.
     * @param content the content of the message
     * @param senderId the id of the sender
     * @param recipientId the id of the recipient
     */
    public void sendMessageIndividual(String content, String senderId, String recipientId) {
        ChatRoom senderChatRoom = messageManager.getAllChatRoom().get(senderId);
        ChatRoom recipientChatRoom = messageManager.getAllChatRoom().get(recipientId);
        this.messageManager.sendMessageIndividual(content, senderChatRoom, recipientChatRoom);
    }


    /**
     * send message to all attendees
     * @param content the content of the message
     * @param senderId the sender of the message
     */
    public void sendMessageToAllAttendee(String content, String senderId){
        if (!attendeeManager.getAttendeeList().isEmpty()){
            for (int i = 0; i < attendeeManager.getAttendeeList().size(); i++){
                sendMessageIndividual(content, senderId,
                        attendeeManager.getId(attendeeManager.getAttendeeList().get(i)));
            }
        }
    }

    /**
     * send a message to all speaker
     * @param content the content of the message
     * @param senderId the sender of the message
     */
    public void sendMessageToAllSpeaker(String content, String senderId){
        if (!speakerManager.getSpeakerList().isEmpty()){
            for (int i = 0; i < speakerManager.getSpeakerList().size(); i++){
                sendMessageIndividual(content, senderId,
                        speakerManager.getId(speakerManager.getSpeakerList().get(i)));
            }
        }
    }

    /**
     * send a message to multiple users.
     * @param content the content of the message
     * @param senderId the sender of the message
     * @param recipientId a group of recipients that will receive this message
     */
    //send message to multiple users
    public void sendMessageAll(String content, String senderId, ArrayList<String> recipientId) {
        for (String u : recipientId) {
            sendMessageIndividual(content, senderId, u);
        }
    }

    /**
     * return a list of attendees from specified event(s)
     * @param eventId the id of event(s)
     * @return the returned list contains all attendees who are currently in the sign-up list of the event(s)
     */
    public ArrayList<String> generateRecipients(ArrayList<String> eventId) {
        return eventManager.getAttendeeIds(eventId);
    }

    /**
     * A method to view all the information in the inbox
     * @param id the id of the user who is going to be checked
     * @return return a string that represents all messages in this user's inbox
     */
    public String viewInbox(String id) {
        StringBuilder view = new StringBuilder();
        if (messageManager.getInboxMessage(id).isEmpty()){
            return "Your inbox is empty.\n";
        }
        else{
            int num = 0;
            for (int i = 0; i < messageManager.getReadMessage(id).size(); i++) {
                view.append(num + "\n");
                view.append(messageManager.viewMessage(messageManager.getReadMessage(id).get(i)) + "\n");
                num ++;
            }
            if (!messageManager.getUnreadMessage(id).isEmpty()){
                view.append("-----------New Message-----------\n");}
            for (int i = 0; i < messageManager.getUnreadMessage(id).size(); i++){
                view.append(num + "\n");
                view.append(messageManager.viewMessage(messageManager.getUnreadMessage(id).get(i)) + "\n");
                num ++;
            }
            messageManager.setHasReadAll(messageManager.getUnreadMessage(id));
            return view.toString();}
    }

    /**
     * A method to view all the information in the outbox
     * @param id the id of the user who is going to be checked
     * @return return a string that represents all messages in this user's outbox
     */
    public String viewOutbox(String id) {
        StringBuilder view = new StringBuilder();
        if (messageManager.getOutboxMessage(id).isEmpty()){
            return "Your outbox is empty.\n";
        }
        else{
            int num = 0;
            for (int i = 0; i < messageManager.getOutboxMessage(id).size(); i++) {
                view.append(num + "\n");
                view.append(messageManager.viewMessage(messageManager.getOutboxMessage(id).get(i)) + "\n");
                num ++;
            }
            return view.toString();
        }
    }


    /**
     * check if the user input is valid, if so, mark it as unread
     * @param num       user input
     * @param userid    userId
     * @return  the corresponding string that represents the result
     */
    public String checkNumAndSet(String num, String userid){
        try {
            if (Integer.parseInt(num) >= 0 && Integer.parseInt(num) < messageManager.getInboxMessage(userid).size()) {
                return messageManager.checkAndSetAsUnread(messageManager.getInboxMessage(userid).get(Integer.parseInt(num)));
            } else {
                return "This number does not have a corresponding message. Please try again.\n";
            }
        } catch (NumberFormatException e){
            return "Invalid Input. Please enter a number.\n";
        }
    }


    /**
     * check if the user input is valid, if so, delete the corresponding inbox message
     * @param num       user input
     * @param userid    userId
     * @return  the corresponding string that represents the result
     */
    public String checkNumAndDeleteInbox(String num, String userid){
        try {
            int n = Integer.parseInt(num);
            if (n >= 0 && n < messageManager.getInboxMessage(userid).size()) {
                return messageManager.deleteInboxMessage(messageManager.getAllChatRoom().get(userid),
                        messageManager.getAllChatRoom().get(userid).getInbox().get(n));
            } else {
                return "This number does not have a corresponding message. Please try again.\n";
            }
        } catch (NumberFormatException e){
            return "Invalid Input. Please enter a number.\n";
        }
    }

    /**
     * check if the user input is valid, if so, delete the corresponding outbox message
     * @param num       user input
     * @param userid    userId
     * @return  the corresponding string that represents the result
     */
    public String checkNumAndDeleteOutbox(String num, String userid){
        try {
            if (Integer.parseInt(num) >= 0 && Integer.parseInt(num) < messageManager.getOutboxMessage(userid).size()) {
                return messageManager.deleteOutboxMessage(messageManager.getAllChatRoom().get(userid),
                        messageManager.getAllChatRoom().get(userid).getOutbox().get(Integer.parseInt(num)));
            } else {
                return "This number does not have a corresponding message. Please try again.\n";
            }
        } catch (NumberFormatException e){
            return "Invalid Input. Please enter a number.\n";
        }
    }


    public void sendUserRequest(String senderId, String message){
        userRequestManager.createUserRequest(senderId,message);
    }


    public void addressUserRequest(int index){
        userRequestManager.getUserRequest(index).address();
    }

    public void listAllUserRequests(){
        if (userRequestManager.getAllUserRequests().isEmpty()){
            System.out.println("There is no user request.\n");
        }else{
        System.out.printf("index%20s%30s%30s","Sender Name", "Message", "Status\n");
        int index = 0;
        for(int i = 0; i < userRequestManager.getAllUserRequests().size(); i++) {
            System.out.printf("%5d%20s%30s%30s", index++,
                    attendeeManager.findAttendee(userRequestManager.getSenderId(userRequestManager.getAllUserRequests().get(i))).getName(),
                    userRequestManager.getMessage(userRequestManager.getAllUserRequests().get(i)),
                    userRequestManager.getStatus(userRequestManager.getAllUserRequests().get(i))+"\n");
            }
        System.out.println("\n");
        }
    }

}


