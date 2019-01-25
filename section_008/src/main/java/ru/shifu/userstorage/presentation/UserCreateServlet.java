package ru.shifu.userstorage.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet to create new users.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.4$
 * @since 0.1
 * 21.01.2019
 */
public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/CreateView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("user").forward(request, response);
    }
}
