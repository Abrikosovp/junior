package ru.shifu.userstorage.presentation;

import ru.shifu.userstorage.logic.Action;
import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet works with user's store.
 * Presentation layout.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.3$
 * @since 0.1
 * 18.01.2019
 */
public class UserServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final ValidateService validate = ValidateService.getInstance().init();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        List<User> users = this.validate.findAll();
        if (users.size() == 0) {
            writer.append("Storage is empty.");
        } else {
            users.forEach(user -> writer.append(user.toString()));
        }
        writer.flush();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        Action.Type act = Action.Type.valueOf(action.toUpperCase());

        String id = request.getParameter("id");

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");

        User user;
        if (id == null || id.equals("")) {
            user = new User(name, login, email);
        } else {
            user = new User(id, name, login, email);
        }

        String result = this.validate.doAction(act, user);

        request.setAttribute("result", result);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
