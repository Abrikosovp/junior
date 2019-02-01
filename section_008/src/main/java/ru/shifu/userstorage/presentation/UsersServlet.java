package ru.shifu.userstorage.presentation;


import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to display all users.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 21.01.2019
 */
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        List<User> users = ValidateService.getInstance().findAll();

        Role role = null;
        for (User user: users) {
            if (Long.parseLong(user.getId()) == Long.parseLong(session.getAttribute("uid").toString())) {
                role = user.getRole();
            }
        }
        session.setAttribute("role", role);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/UserView.jsp").forward(request, response);

    }
}
