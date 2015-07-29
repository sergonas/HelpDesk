package me.oque.service;

import me.oque.entity.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;

/**
 * User service with OWASP recommended implementation for save and auth
 *
 * Created by Dmitry Smorzhok on 19.07.15.
 */
@Service("userService")
public class UserServiceImpl extends SelectionServiceImpl implements UserService {

    @Inject
    private PasswordEncoder encoder;

    @Override
    public void saveUser(UserInfo user, String password) {
        if (user != null && StringUtils.hasText(user.getPasswordHash())) {
            String passwordHash = encoder.encode(password);
            user.setPasswordHash(passwordHash);
            selectionDao.save(user);
        }
    }
}
