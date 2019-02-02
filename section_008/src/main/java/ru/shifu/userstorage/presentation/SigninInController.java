package ru.shifu.userstorage.presentation;

import ru.shifu.userstorage.logic.Validate;
import ru.shifu.userstorage.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Controller for sign in.
 * If the session contains the login parameter, the filter skips on.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 30.01.2019
 */
public class SigninInController extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        long id = validate.isRegistered(login, password);

        if (id != -1) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("uid", id);
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        } else {
            request.setAttribute("error", "Sign in invalid");
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
    }
}
