package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * For constructing a Room object that represent a room.
 *
 * @author Xuezhou
 * @version %I%, %G%
 */
public class Room implements Serializable {

    private int roomId = 0;
    private Hashtable<LocalDateTime, String> schedule = new Hashtable<>();
    private Hashtable<String, LocalDateTime> roomUsingEndingTime = new Hashtable<>();
    private int capacity;

    /**
     * Initializing a room with given capacity
     * @param capacity the number indicating the room capacity
     */
    public Room(int capacity){
        this.capacity = capacity;
    }


    /**
     * Get the hold time of the event
     * @return A hashtable that describes the name of the event and the time of the event
     */
    public Hashtable<LocalDateTime, String> getSchedule() {
        return schedule;
    }

    /**
     * getter for the end time of using this room
     * @return the end time for this room to be used
     */
    public Hashtable<String, LocalDateTime> getRoomUsingEndingTime(){return roomUsingEndingTime;}

    /**
     * Assign a event into this room in a specific time
     * @param eventID the event that we wish to assign
     * @param startTime the time that we wish to assign for a specific event
     */
    public void addEvent(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        schedule.put(startTime, eventID);
        roomUsingEndingTime.put(eventID,endTime);
    }

    /**
     * Getter of the room id
     * @return the room id
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Setter of the room id
     * @param id set the room id
     */
    public void setRoomId(int id){
        roomId = id;
    }

    /**
     * Getter for room capacity
     * @return int
     */
    public int getRoomCapacity(){
        return this.capacity;
    }

    /**
     * Remove a specific a event from this room
     * @param eventID the event that we wish to remove
     * @param startTime the time of the event
     */
    public void removeEvent(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        schedule.remove(startTime, eventID);
        roomUsingEndingTime.remove(eventID,endTime);
    }
}
