package ru.shifu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * JsonServlet
 *
 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 11.02.2019
 */
public class JsonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Index.html").forward(request, response);
    }
}
