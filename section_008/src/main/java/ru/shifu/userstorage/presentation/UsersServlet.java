package ru.shifu.userstorage.presentation;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet to display all users.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.2$
 * @since 0.1
 * 21.01.2019
 */
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(String.format("%s/index.jsp", request.getContextPath()));
    }
}
