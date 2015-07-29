package me.oque.service;

import me.oque.entity.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by serega on 27.07.15.
 */
public class MyUserDetailService implements UserDetailsService {
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserInfo> users = entityManager.createQuery("select u from UserInfo u where u.username = :username", UserInfo.class).setParameter("username", username).getResultList();
        if (users.size() == 1) {
            UserInfo userInfo = users.get(0);
            return new User(userInfo.getUsername(), userInfo.getPasswordHash(), Collections.singletonList(() -> "ROLE_USER"));
        } else {
            throw new UsernameNotFoundException("Username: " + username );
        }
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
