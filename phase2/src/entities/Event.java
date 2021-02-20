package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * An abstract class that represents Event objects.
 * @author Yuxuan Gu
 * @version %I%, %G%
 */
public abstract class Event implements Serializable{
    /**
     * Abstract getter of id of the event
     * @return id of the event
     */
    public abstract String getId();

    /**
     * Abstract setter of id of the event
     * @param i new id
     */
    public abstract void setId(int i);

    /**
     * Abstract getter of date of the event
     * @return date of the event
     */
    public abstract LocalDate getDate();

    /**
     * Abstract getter of time of the event
     * @return time of the event
     */
    public abstract LocalTime getTime();

    /**
     * Abstract getter of organizer's id of the event
     * @return organizer's id of the event
     */
    public abstract String getOrganizerID();

    /**
     * Abstract getter of duration of the event
     * @return duration of the event
     */
    public abstract int getDurationInHour();

    /**
     * Abstract setter of duration of the event
     * @param hour new duration
     */
    public abstract void setDurationInHour(int hour);

    /**
     * Abstract getter of attendees' id of the event
     * @return a list of attendees' id
     */
    public abstract ArrayList<String> getAttendeesIDs();

    /**
     * Abstract getter of speakers' id of the event
     * @return a list of speakers' id
     */
    public abstract ArrayList<String> getSpeaker_id();

    /**
     * Abstract getter of topic of the event
     * @return topic of event
     */
    public abstract String getTopic();

    /**
     * Abstract getter of room id of the event
     * @return room id of the event
     */
    public abstract int getRoomID();

    /**
     * Abstract getter of id of the event
     * @return room id of the event
     */
    public abstract int getCurrentEnrollment();

    /**
     * Abstract getter of maximum attendees of the event
     * @return number of maximum attendees
     */
    public abstract int getMaxAttendee();

    /**
     * Abstract setter of maximum attendees of the event
     * @param newNum number of maximum attendees of the event
     */
    public abstract void setMaxAttendee(int newNum);

    /**
     * Abstract function claiming the type of event
     * @return type of event
     */
    public abstract String toString();

}
