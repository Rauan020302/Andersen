package ru.andersen.servlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.andersen.dto.UserForm;
import ru.andersen.dto.UserForm.UserFormBuilder;
import ru.andersen.repository.UserRepository;
import ru.andersen.repository.UserRepositoryImpl;
import ru.andersen.service.UserService;
import ru.andersen.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateUserServlet extends HttpServlet {

    private HikariDataSource dataSource;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {


        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.43.167:5432/andersendb");
        hikariConfig.setMaximumPoolSize(20);
        System.out.println("Database connection inits success.");
        this.dataSource = new HikariDataSource(hikariConfig);

        System.out.println("Database has been connected.");
        UserRepository usersRepository = new UserRepositoryImpl(dataSource);
        this.userService = new UserServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Get method was called in " + this.getClass().getSimpleName());

        List<UserForm> userFormList = userService.getAll();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/createUser.jsp");
        request.setAttribute("userList", userFormList);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Post method was called in " + this.getClass().getSimpleName());

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");

        UserFormBuilder builder = UserForm.builder();
        if (firstName != null) {
            builder.firstName(firstName);
        }
        if (lastName != null) {
            builder.lastName(lastName);
        }
        if (age != null) {
            int ageValue = Integer.parseInt(age);
            builder.age(ageValue);
        }
        UserForm userForm = builder.build();

        userService.createUser(userForm);
    }


    @Override
    public void destroy() {
        System.out.println("Database connection in " + this.getClass().getSimpleName()
                + " has been destroyed success.");
        dataSource.close();
    }
}
