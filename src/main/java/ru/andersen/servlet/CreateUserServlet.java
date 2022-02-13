package ru.andersen.servlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.andersen.dto.UserForm;
import ru.andersen.repository.UserRepository;
import ru.andersen.repository.UserRepositoryImpl;
import ru.andersen.service.UserService;
import ru.andersen.service.UserServiceImpl;

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

//        Properties properties = new Properties();
//        try {
//            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e);
//        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("28679854");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.1.107:5432/userdb");
        hikariConfig.setMaximumPoolSize(20);

        this.dataSource = new HikariDataSource(hikariConfig);

        UserRepository usersRepository = new UserRepositoryImpl(dataSource);
        this.userService = new UserServiceImpl((usersRepository));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        List<UserForm> userFormList = userService.getAll();
        request.setAttribute("userList", userFormList);
        request.getRequestDispatcher("jsp/createUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .age(Integer.valueOf(req.getParameter("age")))
                .build();

        userService.createUser(form);
//        resp.sendRedirect("/create");
    }


    @Override
    public void destroy() {
        dataSource.close();
    }
}
