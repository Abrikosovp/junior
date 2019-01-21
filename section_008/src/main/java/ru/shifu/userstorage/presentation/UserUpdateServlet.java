package ru.shifu.userstorage.presentation;

import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.persistent.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet to update existed user.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 21.01.2019
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final ValidateService validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");

        User result = this.validate.findById(id);
        PrintWriter writer = response.getWriter();
        writer.append(String.format(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>User update</title>\n"
                        + "</head>\n"
                        + "<body><table>\n"
                        + "<form method=\"POST\" action=\"edit\">\n"
                        + "<input type=\"hidden\" name=\"action\" value=\"update\">\n"
                        + "<input type=\"hidden\" name=\"id\" value=\"%s\">\n"
                        + "<tr><td>ID</td><td>%s</td></tr>\n"
                        + "<tr><td>Name</td><td><input type=\"text\" name=\"name\" value=\"%s\"></td></tr>\n"
                        + "<tr><td>Login</td><td><input type=\"text\" name=\"login\" value=\"%s\"></td></tr>\n"
                        + "<tr><td>Email</td><td><input type=\"text\" name=\"email\" value=\"%s\"></td></tr>\n"
                        + "<tr align=\"right\"><td colspan=\"2\"><input type=\"submit\" value=\"UPDATE\"></td></tr>\n"
                        + "</form></table></body></html>",
                id, id, result.getName(), result.getLogin(), result.getEmail()
        ));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new UserServlet().doPost(request, response);
        new UsersServlet().doGet(request, response);
    }
}
