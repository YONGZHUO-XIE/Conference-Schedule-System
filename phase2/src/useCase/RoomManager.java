package useCase;

import entities.Room;
import entities.Event;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * For constructing a RoomManager object that modifies the properties of Room and stores all Rooms
 *
 * @author Shang Wu
 * @version %I%, %G%
 */
public class RoomManager implements Serializable{
    private ArrayList<Room> allRooms = new ArrayList<Room>();

    /**
     * Creates a new room and add it to the list of all rooms. The room id is unique depending on the total number
     * of rooms. This method has no parameters and no returns.
     */
    public void addRoom(int capacity) {
        Room newRoom = new Room(capacity);
        this.allRooms.add(newRoom);
        newRoom.setRoomId(allRooms.size());
    }

    /**
     * Checks whether a room is available at a specific time. Returns "There is no such room." if the room does
     * not exist; returns "No event assigned at this time" if there is no scheduled events at the given time;
     * returns the event id if there is an event scheduled at the given time.
     * @param datetime the time slot to check availability
     * @param room the room to check availability
     * @return string describing the status of this room at the given time, or an event id
     */
    public String checkEmpty(LocalDateTime datetime, Room room){
        Hashtable<LocalDateTime, String> StartTime = room.getSchedule();
        Hashtable<String, LocalDateTime> EndTime = room.getRoomUsingEndingTime();
        if (!allRooms.contains(room)){
            return "There is no such room.";  // check if the room exists
        } else {
            for (LocalDateTime times : StartTime.keySet()){
                if(times.isEqual(datetime) || (datetime.isAfter(times) &&
                        datetime.isBefore(EndTime.get(StartTime.get(times))))){
                    return "There existed an event in this room" ;  // check if the datetime is in schedule
                }
            }
            return "No event assigned at this time.";
        }
    }

    /** Returns a list of empty rooms at a specific time.
     * @param datetime the time to check all available rooms
     * @return a list of rooms that is available at the given time
     */
    public ArrayList<Room> getEmptyRoom(LocalDateTime datetime) {
        ArrayList<Room> emptyRooms = new ArrayList<>();
        for (Room i: allRooms) {
            if (!checkEmpty(datetime, i).equals("There is no such room.") &&
                    !checkEmpty(datetime, i).equals("There existed an event in this room")
                    && checkEmpty(datetime, i).equals("No event assigned at this time.") ) {
                emptyRooms.add(i);
            }
        }
        return emptyRooms;
    }

    /**
     * Assigns an event to a room at a given datetime. There is no return value.
     * @param room the room that gets assigned an event
     * @param event the event that gets added to this room
     * @param datetime the datetime that this event happens in this room
     */
    public void assignRoom(Room room, Event event, LocalDateTime datetime, LocalDateTime endTime) {
        room.addEvent(event.getId(), datetime, endTime);
    }

    /**
     * Remove a an event from the given room, with the start time and end time of the event
     * @param room the room
     * @param event the event to be removed
     * @param startTime start time of the event
     * @param endTime end time of the event
     */
    public void removeRoom(Room room, Event event, LocalDateTime startTime, LocalDateTime endTime){
        room.removeEvent(event.getId(), startTime, endTime);
    }

    /**
     * Finds a room according to its room id. Returns a Room object that was found.
     * @param RoomID the id of the room that is looking for
     * @return a room object that was found according to the given room id
     */
    public Room findRoom(int RoomID){
        for(Room room:allRooms){
            if(room.getRoomId()==RoomID){
                return room;
            }
        }
        return null;
    }
}
