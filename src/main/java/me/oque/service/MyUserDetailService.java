package me.oque.service;

import me.oque.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by serega on 27.07.15.
 */
public class MyUserDetailService implements UserDetailsService {
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = entityManager.createQuery("select u from UserInfo u where u.username = :username", UserInfo.class).setParameter("username", username).getSingleResult();
        return null;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
