package ru.shifu.userstorage.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet to create new users.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.2$
 * @since 0.1
 * 21.01.2019
 */
public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.append(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\">\n"
                        + "<title>User creation</title>\n"
                        + "</head>\n"
                        + "<body><table>\n"
                        + "<form method=\"POST\" action=\"create\">\n"
                        + "<input type=\"hidden\" name=\"action\" value=\"add\">\n"
                        + "<tr><td>ID</td><td><input type=\"text\" name=\"id\"></td></tr>\n"
                        + "<tr><td>Name</td><td><input type=\"text\" name=\"name\"></td></tr>\n"
                        + "<tr><td>Login</td><td><input type=\"text\" name=\"login\"></td></tr>\n"
                        + "<tr><td>Email</td><td><input type=\"text\" name=\"email\"></td></tr>\n"
                        + "<tr align=\"right\"><td colspan=\"2\"><input type=\"submit\" value=\"CREATE\"></td></tr>\n"
                        + "</form></table></body></html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        new UserServlet().doPost(request, response);
    }
}
