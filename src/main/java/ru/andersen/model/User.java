package ru.andersen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.andersen.dto.UserForm;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private boolean isDeleted;

    public static User from(UserForm userForm) {
        return User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .age(userForm.getAge())
                .build();
    }

    public static List<User> from(List<UserForm> userForm) {
        return userForm.stream().map(User::from).collect(Collectors.toList());
    }
}
