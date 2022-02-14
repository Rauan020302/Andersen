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
        return usersRepository.findById(userId) == null;
    }

    @Override
    public void deleteById(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public User findById(Long userId) {
        if (usersRepository.findById(userId)!=null) {
            return usersRepository.findById(userId);
        } else {
            throw new IllegalArgumentException("user not found");
        }
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        usersRepository.updateUser(user);
    }

}
