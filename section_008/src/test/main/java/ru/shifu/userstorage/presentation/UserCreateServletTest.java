package ru.shifu.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.mockito.Mockito.*;
/**
 * UserCreateServletTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class UserCreateServletTest {

    private UserCreateServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @Before
    public void createMocks() {
        this.servlet = new UserCreateServlet();
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenCreateNewUserAndSendPostRequest() throws ServletException, IOException {
        when(this.request.getRequestDispatcher("user")).thenReturn(dispatcher);
        this.servlet.doPost(this.request, this.response);
        verify(this.request, times(1)).getRequestDispatcher("user");
        verify(this.request, never()).getSession();
        verify(this.dispatcher).forward(this.request, this.response);
    }

    @Test
    public void whenCreateNewUserAndSendGetRequest() throws ServletException, IOException {
        when(this.request.getRequestDispatcher("/WEB-INF/views/CreateView.jsp")).thenReturn(this.dispatcher);
        this.servlet.doGet(this.request, this.response);
        verify(this.request, times(1)).getRequestDispatcher("/WEB-INF/views/CreateView.jsp");
        verify(this.request, never()).getSession();
        verify(this.dispatcher).forward(this.request, this.response);
    }
}