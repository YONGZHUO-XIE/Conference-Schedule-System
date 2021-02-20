package Gateway;

import useCase.*;
import java.io.*;
    /**
     * Constructing gateway class that read and save Managers
     * in the system.Managers are serializable.
     *
     * Save Manager class in the file.
     * Read Manager from the file.
     *
     * @author Zhao Han Huang, Yongzhuo Xie
     * @version %I%, %G%
     */
public class SaverReader{



    /**
     * Save an Attendee Manager into the file.
     *
     *
     * @param am     the AttendeeManager we want to save in the file.
     */

    public void saveAttendeeManager(AttendeeManager am){
        SavefromFile("AttendeeManager", am); ;
    }

    /**
     * Read an AttendeeManager from the file.Use the algorithm from
     * strategyRead.
     *
     * @return The AttendeeManager Read from the file.
     */
    public AttendeeManager readAttendeeManager() {
        AttendeeManager am = (AttendeeManager) ReadfromFile("AttendeeManager");
        return  am;
    }

    /**
     * Read an UserRequestManager from the file.Use the algorithm from
     * strategyRead.
     *
     * @return The UserRequestManager Read from the file.
     */
    public UserRequestManager readUserRequestManager() {
        UserRequestManager am = (UserRequestManager) ReadfromFile("UserRequestManager");
        return  am;
    }


    /**
     * Save an Organizer Manager into the file.
     *
     *
     * @param om     the OrganizerManager we want to save in the file.
     */
    public void saveOrganizerManager(OrganizerManager om) {
        SavefromFile("OrganizerManager", om);
    }

    /**
     * Read an OrganizerManager object from the file.Use the algorithm from
     *
     *
     *  @return The OrganizerManager Read from the file.
     */
    public OrganizerManager readOrganizerManager() {
        OrganizerManager om =  (OrganizerManager) ReadfromFile("OrganizerManager");
        return om;
    }


    /**
     * Save a SpeakerManager object into the file.
     *
     *
     * @param sm     the SpeakerManager object we want to save in the file.
     */
    public void saveSpeakerManager(SpeakerManager sm){
        SavefromFile("SpeakerManager", sm);
    }

    /**
     * Save a UserRequestManager object into the file.
     *
     *
     * @param sm  the UserRequestManager object we want to save in the file.
     */
    public void saveUserRequestManager(UserRequestManager sm){
        SavefromFile("UserRequestManager", sm);
    }

    /**
     * Read an SpeakerManager object from the file.Use the algorithm from
     * strategyRead.
     *
     *  @return The SpeakerManager Read from the file.
     */
    public SpeakerManager readSpeakerManager() {
        SpeakerManager sm =  (SpeakerManager) ReadfromFile("SpeakerManager");
        return sm;
    }

    /**
     * Save a RoomManager object into the file.
     *
     *
     * @param rm     the RoomManager object we want to save in the file.
     */
    public void saveRoomManager(RoomManager rm) {
        SavefromFile("RoomManager", rm);

    }

    /**
     * Read an RoomManager object from the file.Use the algorithm from
     * strategyRead.
     *
     *  @return The RoomManager Read from the file.
     */

    public RoomManager readRoomManager() {
        RoomManager rm =  (RoomManager) ReadfromFile("RoomManager");
        return rm;
    }

    /**
     * Save an EventManager object into the file.
     *
     *
     * @param em     the EventManager object we want to save in the file.
     */
    public void saveEventManager(EventManager em) {
        SavefromFile("EventManager", em);
    }

    /**
     *  Read an EventManager object from the file.Use the algorithm from
     * strategyRead.
     *
     *  @return The EventManager Read from the file.
     */
    public EventManager readEventManager() {
        EventManager em =  (EventManager) ReadfromFile("EventManager");
        return  em;
    }

    /**
     * Save an MessageManager object into the file.
     *
     *
     * @param mm     the MessageManager object we want to save in the file.
     */
    public void saveMessageManager(MessageManager mm){
        SavefromFile("MessageManager", mm);
    }

    /**
     * Read an MessageManager object from the file.Use the algorithm from
     * strategyRead.
     *
     *  @return The MessageManager Read from the file.
     */
    public MessageManager readMessageManager() {
        MessageManager mm =  (MessageManager)ReadfromFile("MessageManager");
        return mm;
    }

    /**
     * A method for saving Manager.Store an Manager object to the file.
     *
     *
     * @param username the String for the Name of the Manager
     * @param  user the Manager we want to store
     */
    public void SavefromFile(String username, Serializable user){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(username);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * strategyRead can read a Manager in the file from the type provided.
     *
     * @param typename take an Mangaertype
     * @return a Serializable Manager
     */
    public Serializable ReadfromFile(String typename){
        Serializable save = factoryManager(typename);
        try{
            File file = new File(typename);
            file.createNewFile();//create the file if the file does not exist. if exist, this line do nothing
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            save = (Serializable) objectInputStream.readObject(); //method readobject return an object of type obj.
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (EOFException e){
            save = factoryManager(typename);
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return save;
    }


    /**
     *  Implemented factory pattern for Managers while taking
     *  a string type.
     * This is a factory creates Manager Serializable without exposing
     * the creation logic.
     *
     *  @param type a string pass in the type of Manager we want to create.
     *  @return The Serilzable Manager we create.
     */

    public Serializable factoryManager (String type){
        if (type == "AttendeeManager"){return
                new AttendeeManager();}
        else if (type == "SpeakerManager"){return new SpeakerManager();}
        else if (type == "OrganizerManager"){return new OrganizerManager();}
        else if (type == "RoomManager"){return new RoomManager();}
        else if (type == "EventManager"){return new EventManager();}
        else if (type == "MessageManager"){return new MessageManager();}
        else if (type == "UserRequestManager"){return new UserRequestManager();}
        else return null;
    }

}

