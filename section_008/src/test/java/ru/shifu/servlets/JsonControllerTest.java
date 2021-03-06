package ru.shifu.servlets;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
/**
 * JsonControllerTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 11.02.2019
 */
public class JsonControllerTest {


    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @Before
    public void createMocks() {
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenSendJsonRequestForBookedHallThenGetMessage() throws IOException, SQLException, ServletException {
        String json = "{\"firstname\":\"name\",\"secondname\":\"Ivanov Ivan Ivanovich\",\"sex\":\"men\",\"description\":\"+7(123)4443322\"}";
        StringReader reader = new StringReader(json);
        StringWriter writer = new StringWriter();
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new JsonController().doGet(request, response);
    }

    @Test
    public void whenSendJsonRequestForBookedHallThenPostMessage() throws IOException, SQLException, ServletException {
        String json = "{\"firstname\":\"name\",\"secondname\":\"Ivanov Ivan Ivanovich\",\"sex\":\"men\",\"description\":\"+7(123)4443322\"}";
        StringReader reader = new StringReader(json);
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        new JsonController().doPost(request, response);
    }
}