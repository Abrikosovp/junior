package ru.shifu.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;
/**
 * GuestServletTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class GuestServletTest {

    private GuestServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private HttpSession session;

    @Before
    public void createMocks() {
        this.session = mock(HttpSession.class);
        this.servlet = new GuestServlet();
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenDoGetAndSessionHasValue() throws ServletException, IOException {
        when(this.request.getRequestDispatcher("/WEB-INF/views/UserView.jsp")).thenReturn(dispatcher);
        when(this.request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("root");
        this.servlet.doGet(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
        verify(this.request, times(1)).getSession();
    }

    @Test
    public void whenDoGetAndSessionHasNotValue() throws ServletException, IOException {
        when(this.request.getSession()).thenReturn(this.session);
        when(session.getAttribute("login")).thenReturn(null);
        when(this.request.getRequestDispatcher("/WEB-INF/views/Enter.jsp")).thenReturn(dispatcher);
        this.servlet.doGet(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
        verify(this.request, times(1)).getSession();
    }
}