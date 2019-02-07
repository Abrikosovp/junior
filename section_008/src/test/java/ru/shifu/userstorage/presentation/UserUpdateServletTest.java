package ru.shifu.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * UserUpdateServletTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class UserUpdateServletTest {
    private UserUpdateServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;


    @Before
    public void createMocks() {
        this.servlet = new UserUpdateServlet();
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenUpdateUserAndSendToPostUserController() throws ServletException, IOException {
        when(this.request.getRequestDispatcher("user")).thenReturn(this.dispatcher);
        this.servlet.doPost(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
    }

    @Test
    public void whenUpdateUserAndSendToGetUserController() throws ServletException, IOException {
        when(this.request.getRequestDispatcher("/WEB-INF/views/UpdateView.jsp")).thenReturn(this.dispatcher);
        when(this.request.getParameter("id")).thenReturn("1");
        this.servlet.doGet(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
    }
}