package in.skr.shivamkumar.livechat;

import java.util.HashMap;

public class LoginCredentials {

    HashMap<String,Integer> allUsersMap;
    HashMap<String,String> allPasswordsMap;

    public HashMap<String, Integer> getAllUsersMap() {
        return allUsersMap;
    }

    public void setAllUsersMap(HashMap<String, Integer> allUsersMap) {
        this.allUsersMap = allUsersMap;
    }

    public HashMap<String, String> getAllPasswordsMap() {
        return allPasswordsMap;
    }

    public void setAllPasswordsMap(HashMap<String, String> allPasswordsMap) {
        this.allPasswordsMap = allPasswordsMap;
    }
}
