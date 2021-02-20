package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentUserRequest;
import java.util.Scanner;

/**
 * The UserRequest part of the UI menu.
 *
 * @author Ruicong Zong
 * @version %I%, %G%
 */
public class UserRequestMenu {
    PresentUserRequest presentUserRequest = new PresentUserRequest();

    /**
     * This is a display that demonstrates how UserRequests are received and proceeded.
     * There is no return value.
     * @param system a controllerSystem that initiates all controller class
     */
    public void display( ControllerSystem system, Scanner scanner){
        // this is a sample code to demo how it works, in real system, it should use the attendee.
        while (true){
            presentUserRequest.presentDisplayCommand(1);
            String input = scanner.nextLine();
            if (input.equals("1")) {
                system.messageSystem().listAllUserRequests();
            }else if (input.equals("2")){
                addressUserRequest(system, scanner);
            }else if (input.equals("3")){
                break;
            }else {
                presentUserRequest.presentDisplayCommand(2);
            }
        }
    }

    /**
     * helper function to create a user request
     */
    public void createUserRequest(String userid, ControllerSystem system, Scanner scanner){
        presentUserRequest.presentAskRequest();
        String message = scanner.nextLine();
        system.messageSystem().sendUserRequest(userid,message);
    }

    /**
     * for organizer to addree a user request.
     */
    public void addressUserRequest(ControllerSystem system, Scanner scanner){
        presentUserRequest.presentAddressUserRequest(1);
        String input = scanner.nextLine();
        try {
            int index = Integer.parseInt(input);
            system.messageSystem().addressUserRequest(index);
            presentUserRequest.presentAddressUserRequest(2);
        }catch (IndexOutOfBoundsException e){
            presentUserRequest.presentAddressUserRequest(3);
        }catch (NumberFormatException e){
            presentUserRequest.presentAddressUserRequest(4);
        }
    }



}
