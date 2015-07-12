package me.oque.repo;

import me.oque.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitry Smorzhok on 10.07.15.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
