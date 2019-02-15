package ru.shifu.userstorage.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.shifu.userstorage.logic.Validate;
import ru.shifu.userstorage.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * Servlet for managing location data.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.6$
 * @since 0.1
 * 18.01.2019
 */
public class LocationController extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        List<String> result = validate.getCities(country);
        String json = mapper.writeValueAsString(result);
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> countries = validate.getCountries();
        String jsonCountries = mapper.writeValueAsString(countries);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        PrintWriter writer = response.getWriter();
        writer.print(jsonCountries);
        writer.flush();
    }
}
