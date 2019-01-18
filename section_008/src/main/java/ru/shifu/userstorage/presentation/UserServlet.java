package ru.shifu.userstorage.presentation;

import ru.shifu.userstorage.logic.Action;
import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.persistent.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

/**
 * Servlet works with user's store.
 * Presentation layout.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.01.2019
 */
public class UserServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final ValidateService validate = ValidateService.getInstance().init();
    /**
     * To generate a random id.
     */
    private final Random rn = new Random();

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        Action.Type act = Action.Type.valueOf(action.toUpperCase());

        String id = request.getParameter("id");
        if (id == null || id.equals("")) {
            id = String.valueOf(System.currentTimeMillis() + rn.nextInt());
        }

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        User user = new User(id, name, login, email);


        PrintWriter writer = new PrintWriter(response.getOutputStream());
        String result = this.validate.doAction(act, user);

        writer.append(result);
        writer.flush();
    }
}
