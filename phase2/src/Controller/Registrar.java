package Controller;

import useCase.AttendeeManager;
import useCase.MessageManager;
import useCase.OrganizerManager;
import useCase.SpeakerManager;

/**
 * Assemble methods that are called when users request to register an account from the UI
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class Registrar {

    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private MessageManager messageManager;

    /**
     * The initializer of the registrar.
     * @param attendeeManager the AttendeeManager that works for this system
     * @param organizerManager the OrganizerManager that works for this system
     * @param speakerManager the SpeakerManager that works for this system
     * @param messageManager the MessageManager that works for this system
     */
    public Registrar(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                     SpeakerManager speakerManager, MessageManager messageManager) {
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.messageManager = messageManager;
    }

    /**
     * Attendee registration and return its id if registration is successful and update related friend list
     * @param name the name of Attendee account
     * @param password the password of Attendee account
     * @return the id of Attendee after the registration
     */
    public String registerAttendee(String name, String password) {
        String attendeeId = attendeeManager.createAndAddAttendee(name, password);
        if (!organizerManager.getAllOrganizer().isEmpty()) {
            for (int i = 0; i < organizerManager.getAllOrganizer().size(); i++) {
                organizerManager.addFriend(organizerManager.getAllOrganizer().get(i), attendeeId);
            }
        }
        messageManager.createChatroom(attendeeManager.getUserById(attendeeId));
        return attendeeId;
    }

    /**
     * Organizer registration and return its id if registration is successful and update related friend list
     * @param name the name of Organizer account
     * @param password the password of Organizer account
     * @return the id of Organizer after the registration
     */
    public String registerOrganizer(String name, String password) {
        String organizerId = organizerManager.createAndAddOrganizer(name, password);
        if (!organizerManager.getAllOrganizer().isEmpty()) {
            for (int i =0; i < organizerManager.getAllOrganizer().size(); i++) {
                if (!organizerManager.getId(organizerManager.getAllOrganizer().get(i)).equals(organizerId)) {
                    organizerManager.addFriend(organizerManager.getAllOrganizer().get(i), organizerId);
                    organizerManager.addFriend(organizerManager.getUserById(organizerId),
                            organizerManager.getId(organizerManager.getAllOrganizer().get(i)));
                }
            }
        }
        if (!attendeeManager.getAttendeeList().isEmpty()) {
            for (int i = 0; i < attendeeManager.getAttendeeList().size(); i++) {
                organizerManager.addFriend(organizerManager.getUserById(organizerId),
                        attendeeManager.getId(attendeeManager.getAttendeeList().get(i)));
            }
        }
        if (!speakerManager.getSpeakerList().isEmpty()) {
            for (int i = 0; i < speakerManager.getSpeakerList().size(); i++){
                organizerManager.addFriend(organizerManager.getUserById(organizerId),
                        speakerManager.getId(speakerManager.getSpeakerList().get(i)));
            }
        }
        messageManager.createChatroom(organizerManager.getUserById(organizerId));
        return organizerId;
    }

    /**
     * Speaker registration and return its id if registration is successful and update related friend list
     * @param name the name of Speaker account
     * @param password the password of Speaker account
     * @return the id of Speaker after the registration
     */
    public String registerSpeaker(String name, String password){
        String speakerId = speakerManager.createAndAddSpeaker(name, password);
        if (!organizerManager.getAllOrganizer().isEmpty()) {
            for (int i = 0; i < organizerManager.getAllOrganizer().size(); i++) {
                organizerManager.addFriend(organizerManager.getAllOrganizer().get(i), speakerId);
            }
        }
        messageManager.createChatroom(speakerManager.getUserById(speakerId));
        return speakerId;
    }

}