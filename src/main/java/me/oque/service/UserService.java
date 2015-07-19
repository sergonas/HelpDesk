package me.oque.service;

import me.oque.entity.UserInfo;

/**
 * Created by Dmitry Smorzhok on 19.07.15.
 */
public interface UserService extends SelectionService {

    /**
     * Saves new user to database
     * @param user - user object
     */
    void saveUser(UserInfo user);

    /**
     * Tries to authenticate user with given credentials
     * @param username username to authenticate
     * @param password password for given username
     * @return true if authentication succeeded, false otherwise
     */
    boolean authenticateUser(String username, String password);

}
