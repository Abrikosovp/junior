package ru.shifu.servlets;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void whenDoGetThenForwardToView() throws ServletException, IOException {
        when(this.request.getRequestDispatcher("Index.html")).thenReturn(this.dispatcher);
        new JsonServlet().doPost(request, response);
        verify(this.dispatcher).forward(this.request, this.response);
    }
}