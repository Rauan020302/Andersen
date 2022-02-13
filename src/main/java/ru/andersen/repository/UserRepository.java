package ru.andersen.repository;

import ru.andersen.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    void updateUser(User user);

    void deleteById(User user);

    Optional<User> findById(Long userId);

    List<User> findAll();
}
