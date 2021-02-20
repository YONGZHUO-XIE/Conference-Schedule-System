package entities;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class VipEvent extends Event implements Serializable{

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
     * Initialize a vip event, which is a subclass for normal event
     * @param topic topic
     * @param organizerID organizer id
     * @param roomID room id
     * @param year year of the vip event
     * @param month month of the vip event
     * @param day day of the vip event
     * @param hour hour of the vip event
     * @param minute minute of the vip event
     * @param durationInHour duration of the vip event (in hours)
     * @param maxAttendee maximum number of attendees of the vip event
     */
    public VipEvent(String topic, String organizerID, int roomID, int year, int month,
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
     * Getter for the vip event id
     * @return vip event id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Setter for the vip event id
     * @param i new id
     */
    public void setId(int i) {
        this.id = "v" + i;
    }

    /**
     * Getter for the date of the vip event
     * @return vip event date
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter for the time of the vip event
     * @return localdate time of the event
     */
    @Override
    public LocalTime getTime() {
        return time;
    }

    /**
     * Getter for the organizer id of the vip event
     * @return organizer id of the vip event
     */
    @Override
    public String getOrganizerID() {
        return organizer_id;
    }

    /**
     * Getter for the duration of the vip event
     * @return duration of the vip event (in hours)
     */
    @Override
    public int getDurationInHour() {
        return durationInHour;
    }

    /**
     * Setter for the duration of the vip event
     * @param hour new duration
     */
    @Override
    public void setDurationInHour(int hour) {
         durationInHour = hour;
    }

    /**
     * Getter for all the attendee ids of the vip event
     * @return list of attendee ids
     */
    @Override
    public ArrayList<String> getAttendeesIDs() {
        return attendeesIDs;
    }

    /**
     * Getter for the speaker id of the vip event
     * @return the speaker id
     */
    @Override
    public ArrayList<String> getSpeaker_id() {
        return speaker_id;
    }

    /**
     * Getter for the topic of the vip event
     * @return the topic of the vip event
     */
    @Override
    public String getTopic() {
        return topic;
    }

    /**
     * Getter for the room id of the vip event
     * @return the room id
     */
    @Override
    public int getRoomID() {
        return roomID;
    }

    /**
     * Getter for the current enrollment of the vip event
     * @return the current enrollment (number of attendees)
     */
    @Override
    public int getCurrentEnrollment() {
        return attendeesIDs.size();
    }

    /**
     * Getter for the maximum number of attendees of the vip event
     * @return maximum number of attendees
     */
    @Override
    public int getMaxAttendee() {
        return maxAttendee;
    }

    /**
     * Setter for the maximum number of attendees of the vip event
     * @param newNum new number of maximum attendees
     */
    @Override
    public void setMaxAttendee(int newNum) {
        maxAttendee = newNum;
    }

    /**
     * Claim the type of the event
     * @return "This is a VipEvent"
     */
    @Override
    public String toString() {
        return "This is a VipEvent";
    }
}
