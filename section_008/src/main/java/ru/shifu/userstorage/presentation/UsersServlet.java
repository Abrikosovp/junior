package ru.shifu.userstorage.presentation;


import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to display all users.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.4$
 * @since 0.1
 * 21.01.2019
 */
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = ValidateService.getInstance().findAll();
        request.setAttribute("size", users.size());
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/UserView.jsp").forward(request, response);
    }
}
