package ru.shifu.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.mockito.Mockito.*;

/**
 * LocationControllerTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 15.02.2019
 */
public class LocationControllerTest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void createMocks() {
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
    }

    @Test
    public void whenSendJsonRequestForBookedHallThenGetMessage() throws IOException, ServletException {
        String json = "{\"id\":\"12\",\"login\":\"login\",\"password\":\"password\",\"role\":\"admin\", \"role\": {\"name\":\"admin\",\"email\":\"admin\",\"country\":\"Russia\",\"city\":\"Moscow\"}}";
        StringReader reader = new StringReader(json);
        StringWriter writer = new StringWriter();
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new LocationController().doGet(request, response);
    }

    @Test
    public void whenSendJsonRequestForBookedHallThenPostMessage() throws IOException, ServletException {
        String json = "{\"id\":\"12\",\"login\":\"login\",\"password\":\"password\",\"role\":\"admin\", \"role\": {\"name\":\"admin\",\"email\":\"admin\",\"country\":\"Russia\",\"city\":\"Moscow\"}}";
        StringReader reader = new StringReader(json);
        StringWriter writer = new StringWriter();
        when(request.getReader()).thenReturn(new BufferedReader(reader));
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        new LocationController().doPost(request, response);
    }
}