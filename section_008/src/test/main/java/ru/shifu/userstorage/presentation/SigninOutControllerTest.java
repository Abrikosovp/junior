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
/**
 * SigninOutControllerTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class SigninOutControllerTest {

    private SigninOutController servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private HttpSession session;


    @Before
    public void createMocks() {
        session = mock(HttpSession.class);
        this.servlet = new SigninOutController();
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenDoGetThenForwardToView() throws ServletException, IOException {
        when(this.request.getSession()).thenReturn(this.session);
        new SigninOutController().doGet(this.request, this.response);
        verify(this.response, times(1)).sendRedirect(String.format("%s/guest", request.getContextPath()));
        verify(this.session, times(1)).invalidate();
    }

}