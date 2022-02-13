package ru.andersen.service;

import ru.andersen.dto.UserForm;
import ru.andersen.model.User;
import ru.andersen.repository.UserRepository;

import java.util.List;

import static ru.andersen.dto.UserForm.from;

public class UserServiceImpl implements UserService{

    private final UserRepository usersRepository;

    public UserServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void createUser(UserForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .age(form.getAge())
                .isDeleted(false)
                .build();

        usersRepository.save(user);
    }

    public Boolean isDeleteUser(Long userId) {
        return usersRepository.findById(userId).isPresent();
    }

    @Override
    public List<UserForm> getAll() {
        return from(usersRepository.findAll());
    }


}
