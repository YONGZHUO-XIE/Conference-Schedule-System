package useCase;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import entities.Event;
import entities.NormalEvent;
import entities.VipEvent;

/**
 * Actions event manager can do
 *
 * @author Yuxuan Gu, Kun Zhang
 * @version %I%, %G%
 */
public class EventManager implements Serializable{

    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Event> vipEvents = new ArrayList();
    /**
     * get a list of event
     *
     * @return all events
     */
    public ArrayList<Event> getEvents() {return events;}

    /**
     * get a list of vip event
     * @return vip events
     */
    public ArrayList<Event> getVipEvents() {return vipEvents;}

    /**
     * Get organizer's id for this event
     * @param event events which is needed
     * @return id of specific organizer's id
     */
    public String getOrganizerID(Event event){
        return event.getOrganizerID();
    }

    /**
     * Add attendees
     * @param event an event which need to be used
     * @param attendee an ID of attendee which is string
     */
    public void addAttendees(Event event, String attendee){
        event.getAttendeesIDs().add(attendee);
    }

    /**
     * Remove attendees
     * @param event an event which need to be used
     * @param attendee an ID of attendee which is string
     */
    public void removeAttendees(Event event, String attendee){
        event.getAttendeesIDs().remove(attendee);
    }

    /**
     * get attendees
     * @param event an event which need to be used
     * @return list of all attendees
     */
    public ArrayList<String> getAttendeeList(Event event){
        return event.getAttendeesIDs();
    }

    /**
     * Find an event according to the specific event id
     * @param eventID an ID of eventID which is string
     * @return the specific event or null
     */
    public Event findEvent(String eventID){
        for(Event event: this.events){
            if(event.getId().equals(eventID)){
                return event;
            }
        }
        for(Event event: this.vipEvents){
            if(event.getId().equals(eventID)){
                return event;
            }
        }
        return null;
    }

    /**
     * Get the start time
     * @param event an event which is  an Event
     * @return the start time of the specific event
     */
    public LocalDateTime findEventStartingTime(Event event){
        return LocalDateTime.of(event.getDate(), event.getTime());
    }

    /**
     * Get the end time of an event
     * @param event an event which is  an Event
     * @return the end time of the specific event
     */
    public LocalDateTime findEventEndingTime(Event event){
        return LocalDateTime.of(event.getDate(),
                LocalTime.of(event.getTime().getHour() + event.getDurationInHour(),event.getTime().getMinute()));
    }

    /**
     * Check whether the speaker is added
     * @param event an event which is  an Event
     * @param speaker a speaker which is aimed to add
     */
    public void addSpeakers(Event event, String speaker){
        if(event.getSpeaker_id().contains(speaker)){
            return;
        }
        event.getSpeaker_id().add(speaker);
    }

    /**
     * Get a list of speaker of an event
     * @param event an event which is  an Event
     * @return a list of speakers of a specific event
     */
    public ArrayList<String> getSpeakerList(Event event) {return event.getSpeaker_id();}

    /**
     * Check whether the speaker existed
     * @param StartTime the start time of an event
     * @param speakerID tge id of specific speaker
     * @return whether the speaker existed
     */
    public boolean checkSpeakerExisted(LocalDateTime StartTime, String speakerID){
        for(Event event: events){
            if ((LocalDateTime.of(event.getDate(),event.getTime()).isEqual(StartTime) ||
                    (StartTime.isAfter(LocalDateTime.of(event.getDate(),event.getTime()))
                            && StartTime.isBefore(findEventEndingTime(event))))
                    && event.getSpeaker_id().contains(speakerID))
                return true;
        }
        for(Event event: vipEvents){
            if ((LocalDateTime.of(event.getDate(),event.getTime()).isEqual(StartTime) ||
                    (StartTime.isAfter(LocalDateTime.of(event.getDate(),event.getTime()))
                            && StartTime.isBefore(findEventEndingTime(event))))
                    && event.getSpeaker_id().contains(speakerID))
                return true;
        }
        return false;
    }

    /**
     * Create and add event
     * @param topic the string topic which speaker choose
     * @param organizerID the ID of a organizer which is string
     * @param roomID a room which is existed
     * @param year the int year
     * @param month the int month
     * @param day the int day
     * @param hour the int hour
     * @param minute the int minute
     * @return create event
     */
    public Event createAndAddEvent(String topic, String organizerID, int roomID,
                                  int year, int month, int day, int hour, int minute, int durationTime, int maxAttendee){
        Event event = new NormalEvent(topic, organizerID, roomID,
                year, month, day, hour, minute, durationTime, maxAttendee);
        event.setId(events.size() + vipEvents.size());
        this.events.add(event);
        return event;
    }

    /**
     * Create and add Vipevent
     * @param topic the string topic which speaker choose
     * @param organizerID the ID of a organizer which is string
     * @param roomID a room which is existed
     * @param year the int year
     * @param month the int month
     * @param day the int day
     * @param hour the int hour
     * @param minute the int minute
     * @return the event created.
     */
    public Event createAndAddVipEvent(String topic, String organizerID, int roomID,
                                   int year, int month, int day, int hour, int minute, int durationTime, int maxAttendee){
        Event vipevent = new VipEvent(topic, organizerID, roomID,
                year, month, day, hour, minute, durationTime, maxAttendee);
        vipevent.setId(vipEvents.size() + events.size());
        this.vipEvents.add(vipevent);
        return vipevent;
    }



    /**
     * Checks whether the given event exists according to the id. Returns true if this event exists and false otherwise.
     * @param id a string representing the id of an event.
     * @return a boolean representing whether this event exists
     */
    public boolean hasEvent(String id) {
        return findEvent(id) != null;
    }

    /**
     * Check the availability of an event
     * @param event an event which need to be used
     * @return whether the event is available
     */
    public boolean checkEventAvailable(Event event) {
        return event.getCurrentEnrollment() < event.getMaxAttendee();
    }

    /**
     * Check whether the event has been passed
     * @param event an event which need to be used
     * @return whether the event has been passed
     */
    public boolean isNotPassed(Event event){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        if (date.isBefore(event.getDate())){
            return true;
        }
        if (date.isEqual(event.getDate())){
            if (time.isBefore(event.getTime())){
                return true;
            }
        }
        return false;
    }

    /**
     * Return a list of normal event in the system.
     *
     * @return the normal event stored the in EventManager
     */
    public ArrayList<Event> availableEvents() {
        ArrayList<Event> available = new ArrayList<>();
        for (Event e : getEvents()){
            if (checkEventAvailable(e) && isNotPassed(e)){
                available.add(e);
            }
        }

        return available;
    }

    /**
     * Return a list of VipEvent in the system.
     *
     * @return the VipEvent stored the in EventManager
     */
    public  ArrayList<Event> availableVipEvents() {
        ArrayList<Event> available = new ArrayList<>();
        for (Event e : getVipEvents()){
            if (checkEventAvailable(e) && isNotPassed(e)){
                available.add(e);
            }
        }

        return available;
    }

    /**
     * Get all information of this event
     * @param event an event which want to print
     * @return all information of the event
     */
    public String viewEvent(Event event){
        return "Date:" + event.getDate() + "\n" +
                "Time:" + event.getTime() + "\n" +
                "Topic:" + event.getTopic() + "\n" +
                "Room Number:" + event.getRoomID() + "\n" +
                "Speaker:" + event.getSpeaker_id() + "\n" +
                "Event Id:" + event.getId() + "\n" +
                "Event Type:" + event.toString();
    }

    /**
     * reset a new maximum number of attendees for an event
     * @param event the event to be changed
     * @param newMax the new maximum number of attendees
     */
    public void resetMaxAttendee(Event event, int newMax){
        event.setMaxAttendee(newMax);
    }

    /**
     * Get id of an event
     * @param event an event which is to used
     * @return id of specific event
     */
    public String getId(Event event){
        return event.getId();
    }

    /**
     * Get id of room of specific event
     * @param event an event which is to used
     * @return id of an specific room
     */
    public int getRoomID(Event event) {return event.getRoomID();}

    /**
     * Get events which are enrolled
     * @param eventid the id of event
     * @return number of enrolled events
     */
    public int getCurrentEnrollment(String eventid){
        return findEvent(eventid).getCurrentEnrollment();
    }

    /**
     * get event through event id
     * @param id id of an event which is need to be found
     * @return event which we are going to find according to the event id
     */
    public Event getEventById(String id){
        if (id.contains("n")) {
            for (Event e : getEvents()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
        }else{
            for (Event e : getVipEvents()){
                if (e.getId().equals(id)){
                    return e;
                    }
                }
            }
        return null;
    }


    /**
     * get a list of events through event ids
     * @param ids ids of some event
     * @return a list of events with specific ids
     */
    public ArrayList<Event> getListEventByID(ArrayList<String> ids){
        ArrayList<Event> events = new ArrayList<>();
        for (String id : ids){
            events.add(getEventById(id));
        }
        return events;
    }



    /**
     * Get all attendee's Id who signed up given events(for both normal and vip)
     *
     * @param eventId specific room id
     * @return all attendee's Id who signed up given events
     */
    //return all attendee's Id who signed up given events
    public ArrayList<String> getAttendeeIds(ArrayList<String> eventId){
        ArrayList<String > attendeeId = new ArrayList<>();
        ArrayList<Event> events = getListEventByID(eventId);
        for (Event event : events){
            for (String id : event.getAttendeesIDs()) {
                if (!attendeeId.contains(id)){
                    attendeeId.add(id);
                }
            }
        }
        return attendeeId;
    }


}