package ru.andersen.service;

import ru.andersen.dto.UserForm;

import java.util.List;

public interface UserService {
    void createUser(UserForm form);
    Boolean isDeleteUser(Long userId);
//    List<UserForm> getAll();
}
