package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * An abstract class that represents User objects.
 * @author Xuezhou
 * @version %I%, %G%
 */
public abstract class User implements Serializable {

    /**
     * Initialize an User object
     */
    public User(){
    }

    /**
     * Abstract getter of name of the user
     * @return  getter of name of the user
     */
    public abstract String getName();

    /**
     * Abstract getter of the Id of the user
     * @return  getter of the Id of the user
     */
    public abstract String getId();

    /**
     * Abstract getter of the password of this account
     * @return  getter of the password of this account
     */
    public abstract String getPassword();

    /**
     * Abstract getter of the security question of this account
     * @return the hashmap that contains both security question and its answer
     */
    public abstract Hashtable<String, String> getSecurityQus();

    /**
     * Abstract setter of the security question of this account
     */
    public abstract void setSecurityQus(String key, String value);

    /**
     * Abstract setter of the name of this account
     * @param name  the new name
     */
    public abstract void setName(String name);

    /**
     * Abstract setter of the password of the account
     * @param pw the new password
     */
    public abstract void setPassword(String pw);

    /**
     * Abstract getter of the friend list of this account
     * @return  getter of the friend list of this account
     */
    public abstract ArrayList<String> getFriendlist();
}
