package ru.shifu.userstorage.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Guest Servlet
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 30.01.2019
 */
public class GuestServlet extends HttpServlet {

    /**
     * Get Request processing.
     *
     * @param req  request from client.
     * @param resp response for client.
     * @throws ServletException Servlet Exception
     * @throws IOException      IO exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (nonNull(req.getSession().getAttribute("login"))) {
            req.getRequestDispatcher("/WEB-INF/views/UserView.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/Enter.jsp").forward(req, resp);
        }
    }
}