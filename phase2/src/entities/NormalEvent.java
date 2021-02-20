package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Constructing an Event object.Stores date name and Speakerid
 * of the Event.
 *
 * @author Yuxuan Gu
 * @version %I%, %G%
 */
public class NormalEvent extends Event implements Serializable{

    private LocalDate date;
    private LocalTime time;
    private int durationInHour;
    private String topic;
    private String organizer_id ;
    private ArrayList<String> speaker_id = new ArrayList<>();
    private ArrayList<String> attendeesIDs = new ArrayList<>();
    private int roomID;
    private String id;
    private int maxAttendee;


    /**
     * Initialize a new event
     * @param topic A String describes the topic that we wish to assign
     * @param organizerID A String describes the organizer id who will organize this event
     * @param roomID the ID of room where the event hold
     * @param year the time for this event
     * @param month the time for this event
     * @param day the time for this event
     * @param hour the time for this event
     * @param minute the time for this event
     */
    public NormalEvent(String topic, String organizerID, int roomID, int year, int month,
                       int day, int hour, int minute, int durationInHour, int maxAttendee){
            this.topic = topic;
            this.organizer_id = organizerID;
            this.roomID = roomID;
            this.date = LocalDate.of(year, month, day);
            this.time = LocalTime.of(hour, minute);
            this.durationInHour = durationInHour;
            this.maxAttendee = maxAttendee;

    }

    /**
     * Getter for the event id
     * @return event id
     */
    @Override
    public String getId(){return this.id;}

    /**
     * Setter for the event id
     * @param i the id that we wish to assign
     */
    @Override
    public void setId(int i) {
            this.id = "n" + i;
    }

    /**
     * Get the date of event
     * @return the date of event
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the time of this event
     * @return time of this event
     */
    @Override
    public LocalTime getTime() {
        return time;
    }

    /**
     * Get the duration of this event
     * @return duration of this event
     */
    @Override
    public int getDurationInHour(){return durationInHour;}

    /**
     * set new duration of this event
     * @param hour new duration
     */
    @Override
    public void setDurationInHour(int hour){this.durationInHour = hour;}

    /**
     * Get the ids of all attendees
     * @return list of ids of all attendees
     */
    @Override
    public ArrayList<String> getAttendeesIDs() {
        return attendeesIDs;
    }

    /**
     * Get the id of speaker
     * @return id of speaker
     */
    @Override
    public ArrayList<String> getSpeaker_id() {
        return speaker_id;
    }

    /**
     * get the organizer's id
     * @return id of organizer
     */
    @Override
    public String getOrganizerID(){return this.organizer_id;}

    /**
     * Get the topic of the event
     * @return topic of event
     */
    @Override
    public String getTopic() {
        return topic;
    }

    /**
     * Get the roomID of the event
     * @return the roomID of event
     */
    @Override
    public int getRoomID() {
        return roomID;
    }

    /**
     * Get the number of attendees
     * @return number of attendees
     */
    @Override
    public int getCurrentEnrollment() {
        return attendeesIDs.size();
    }

    /**
     * Get the maximum number of attendees for this event
     * @return max number of attendees for this event
     */
    @Override
    public int getMaxAttendee(){
        return this.maxAttendee;
    }

    /**
     * Set the maximum number of attendees for this event
     * @param newNum the new max number of attendees for this event
     */
    @Override
    public void setMaxAttendee(int newNum){
        this.maxAttendee = newNum;
    }

    /**
     * Claim the type of the event
     * @return "This is Normal Event"
     */
    @Override
    public String toString() {
        return "This is Normal Event";
    }


}

