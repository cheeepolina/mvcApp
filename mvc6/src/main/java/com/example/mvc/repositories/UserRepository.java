package com.example.mvc.repositories;
import com.example.mvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);//поиск по емайлу
}
