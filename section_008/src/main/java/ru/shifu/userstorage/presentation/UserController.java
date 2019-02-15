package ru.shifu.userstorage.presentation;

import ru.shifu.userstorage.logic.Action;
import ru.shifu.userstorage.logic.Validate;
import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.models.PersonalData;
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
 * Servlet works with user's store.
 * Presentation layout.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.6$
 * @since 0.1
 * 18.01.2019
 */
public class UserController extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        Action.Type act = Action.Type.valueOf(action.toUpperCase());

        String id = request.getParameter("id");

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Role role = Role.valueOf(request.getParameter("role"));
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String city = request.getParameter("city");



        User user;
        if (id == null || id.equals("")) {
            user = new User(login, password, role, new PersonalData(name, email, country, city));
        } else {
            user = new User(id, login, password, role, new PersonalData(name, email, country, city));
        }

        String result = this.validate.doAction(act, user);

        request.setAttribute("result", result);
        List<User> users = validate.findAll();
        request.setAttribute("users", users);
        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null) {
            request.getRequestDispatcher("/WEB-INF/views/Enter.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/UserView.jsp").forward(request, response);
        }
    }
}
