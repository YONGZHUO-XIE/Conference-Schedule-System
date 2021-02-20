package Controller;

import Gateway.SaverReader;
import useCase.*;

/**
 * Organized all initialization of controller class
 * @author Yongzhou Xie
 * @version %I%, %G%
 */
public class ControllerSystem {

    AttendeeManager attendeeManager;
    OrganizerManager organizerManager;
    SpeakerManager speakerManager;
    MessageManager messageManager;
    RoomManager roomManager;
    EventManager eventManager;
    UserRequestManager userRequestManager;
    private MessageSystem messageSystem;
    private ScheduleSystem scheduleSystem;
    private SignUpSystem signUpSystem;
    private Registrar registrar;
    private LoginSystem loginSystem;
    private  UserSystem userSystem;
    private SaverReader saverReader = new SaverReader();
    private VipSystem vipSystem;

    /**
     * The initializer of the ControllerSystem
     */
    public ControllerSystem(){
        attendeeManager = saverReader.readAttendeeManager();
        organizerManager = saverReader.readOrganizerManager();
        speakerManager = saverReader.readSpeakerManager();
        messageManager = saverReader.readMessageManager();
        roomManager =  saverReader.readRoomManager();
        eventManager =  saverReader.readEventManager();
        userRequestManager = saverReader.readUserRequestManager();
        messageSystem = new MessageSystem(attendeeManager, speakerManager, messageManager, eventManager, userRequestManager);
        scheduleSystem = new ScheduleSystem(speakerManager, organizerManager, eventManager, roomManager, attendeeManager);
        registrar = new Registrar(attendeeManager, organizerManager, speakerManager, messageManager);
        signUpSystem = new SignUpSystem(attendeeManager, eventManager, speakerManager, organizerManager);
        loginSystem = new LoginSystem(attendeeManager, organizerManager, speakerManager);
        userSystem = new UserSystem(attendeeManager, organizerManager, speakerManager, eventManager);
        vipSystem = new VipSystem(attendeeManager);
    }

    /**
     * Construct a LoginSystem that works for UI
     * @return A loginSystem
     */
    public LoginSystem loginSystem(){return loginSystem; }

    /**
     * Construct a MessageSystem that works for UI
     * @return A MessageSystem
     */
    public MessageSystem messageSystem(){
        return messageSystem;
    }

    /**
     * Construct a ScheduleSystem that works for UI
     * @return A ScheduleSystem
     */
    public ScheduleSystem scheduleSystem(){
        return scheduleSystem;
    }

    /**
     * Construct a SignUpSystem that works for UI
     * @return A SignupSystem
     */
    public SignUpSystem signUpSystem(){
        return signUpSystem;
    }

    /**
     * Construct a Registrar that works for UI
     * @return A registrar
     */
    public Registrar registrar(){return registrar; }

    /**
     * Construct a UserSystem that works for UI
     * @return A UserSystem
     */
    public UserSystem userSystem() {return userSystem;}

    /**
     * Construct a VipSystem that works for UI
     * @return A VipSystem
     */
    public VipSystem vipSystem(){return vipSystem; }

    /**
     * Save all managers before actual exist the program
     */
    public void exist(){
        saverReader.saveAttendeeManager(attendeeManager);
        saverReader.saveEventManager(eventManager);
        saverReader.saveMessageManager(messageManager);
        saverReader.saveOrganizerManager(organizerManager);
        saverReader.saveRoomManager(roomManager);
        saverReader.saveSpeakerManager(speakerManager);
        saverReader.saveUserRequestManager(userRequestManager);
    }
}
