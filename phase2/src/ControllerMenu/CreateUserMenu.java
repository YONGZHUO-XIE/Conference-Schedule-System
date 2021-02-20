package ControllerMenu;

import Controller.ControllerSystem;
import Presenter.PresentCreateUser;

import java.util.Scanner;

/**
 * Main class for CreateUserMenu
 * @author Yongzhou Xie
 * @version %I%, %G%
 */

public class CreateUserMenu {

    PresentCreateUser presentCreateUser = new PresentCreateUser();
    SecurityQuestionMenu securityQuestionMenu = new SecurityQuestionMenu();

    /**
     * resents menu for CreateUser. There are 3 options in total.
     * @param cs a controller system that initiates all controller class
     * @param myObj the Scanner object
     */
    public void createUser(ControllerSystem cs, Scanner myObj){
        presentCreateUser.presentCreateCommand();
        String type = myObj.nextLine();
        if (type.equals("1")){
            presentCreateUser.presentUserName(1);
            String name = myObj.nextLine();
            presentCreateUser.presentUserPasswordCommand(1);
            String password = myObj.nextLine();
            String userid = cs.registrar().registerSpeaker(name, password);
            securityQuestionMenu.printSecurityQuestionMenu(cs, userid, myObj);
            presentCreateUser.presentUserIdCommand(1, userid );}
        else if (type.equals("2")){
            presentCreateUser.presentUserName(2);
            String name = myObj.nextLine();
            presentCreateUser.presentUserPasswordCommand(2);
            String password = myObj.nextLine();
            String userid = cs.registrar().registerAttendee(name, password);
            securityQuestionMenu.printSecurityQuestionMenu(cs, userid, myObj);
            presentCreateUser.presentUserIdCommand(2, userid );}
        else if (type.equals("3")){
            presentCreateUser.presentUserName(3);
            String name = myObj.nextLine();
            presentCreateUser.presentUserPasswordCommand(3);
            String password = myObj.nextLine();
            String userid = cs.registrar().registerOrganizer(name, password);
            securityQuestionMenu.printSecurityQuestionMenu(cs, userid, myObj);
            presentCreateUser.presentUserIdCommand(3, userid );}
        else{
            presentCreateUser.presentInvalidInformation();
        }
    }
}
