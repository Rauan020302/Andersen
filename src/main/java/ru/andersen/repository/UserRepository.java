package ru.andersen.repository;

import ru.andersen.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    void updateUser(User user);

    void deleteById(Long userId);

    List<User> findAll();

    User findById(Long userId);
}
