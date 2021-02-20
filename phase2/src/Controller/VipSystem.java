package Controller;
import useCase.*;
/**
 * A class that manges VipAttendee in the system.
 *
 * @author ZhaoHan Huang
 * @version %I%, %G%
 */

public class VipSystem {
    private AttendeeManager attendeeManager;

    /**
     * Initialize a VipSystem.
     * @param attendeeManager the attendeeManager passed into this system.
     */
    public VipSystem(AttendeeManager attendeeManager){
        this.attendeeManager = attendeeManager;
    }

    /**
     * Verify a given attendee is it is a Vip.
     *
     * @param id the id of the Attendee passed in.
     */
    public boolean verifyVip_status(String id){
        return attendeeManager.check_Vip(attendeeManager.getUserById(id));

    }

    /**
     * Modify a given attendee to cancel or change to Vip.
     *
     * @param id the id of the Attendee passed in.
     * @param newstatus the new vip status of the attendee.
     */
    public void modifyVip_status(String id, boolean newstatus){

        attendeeManager.change_Vip(attendeeManager.getUserById(id), newstatus);

    }


}
