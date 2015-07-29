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
    void saveUser(UserInfo user, String password);
}
