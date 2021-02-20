package Presenter;

/**
 * Present function for UserRequest
 * Contains all print message for UserRequest
 *
 * @author Yongzhuo Xie
 * @version %I%, %G%
 */
public class PresentUserRequest {

    /**
     * Present all the commands for we can do in UserRequest
     * @param num different numbers for different UserQuest commands
     */
    public void presentDisplayCommand(int num){
        if (num == 1){
            System.out.println("1.list all user requests\n2.address a user request\n3.exit");
        }
        else if (num == 2){
            System.out.println("Invalid input. Please try again.\n");
        }
    }

    /**
     * Ask for the User input in UserRequest
     */
    public void presentAskRequest(){
        System.out.println("please provide the request:");
    }

    /**
     * Present all the information that is available for the function AddressUserRequest
     * @param num different numbers for different AddressUserQuest commands
     */
    public void presentAddressUserRequest(int num){
        if (num == 1){
            System.out.println("please input the index of user request to address: ");
        }
        else if (num == 2){
            System.out.println("You have successfully addressed this request.\n");
        }
        else if (num == 3){
            System.out.println("This user request does not exist.\n");
        }
        else if (num ==4){
            System.out.println("Invalid input. Please enter a positive integer.\n");
        }
    }
}
