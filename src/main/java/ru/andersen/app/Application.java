package ru.andersen.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.andersen.dto.UserForm;
import ru.andersen.repository.UserRepository;
import ru.andersen.repository.UserRepositoryImpl;
import ru.andersen.service.UserService;
import ru.andersen.service.UserServiceImpl;

public class Application {

    private static final String DB_USERS = "postgres";
    private static final String DB_PASSWORD = "28679854";
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/user";
    private static final String DB_URL = "jdbc:postgresql://192.168.1.107:5432/userdb"; //на виртуалке база userdb НИЖЕ!!

    public static void main(String[] args) {


        HikariConfig config = new HikariConfig();
        config.setUsername(DB_USERS);
        config.setPassword(DB_PASSWORD);
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(DB_URL);
        config.setMaximumPoolSize(20);

        HikariDataSource dataSource = new HikariDataSource(config);

        UserRepository usersRepository = new UserRepositoryImpl(dataSource);
        UserService userService = new UserServiceImpl(usersRepository);


        UserForm form = UserForm.builder()
                .firstName("господи ")
                .lastName("работай")
                .age(1)
                .build();
        userService.createUser(form);

        System.out.println(usersRepository.findAll());
    }
}
