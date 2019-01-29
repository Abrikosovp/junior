package ru.shifu.userstorage.presentation;

import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Servlet to update existed user.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 21.01.2019
 */
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        User user = ValidateService.getInstance().findById(id);
        request.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        request.getRequestDispatcher("/WEB-INF/views/UpdateView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        new UserController().doPost(request, response);
    }
}
