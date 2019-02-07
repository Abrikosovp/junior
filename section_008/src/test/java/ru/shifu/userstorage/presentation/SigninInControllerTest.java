package ru.shifu.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.shifu.userstorage.logic.Validate;
import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.logic.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
/**
 * SigninInControllerTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class SigninInControllerTest {

    private Validate validate;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private final static String PATH = "/WEB-INF/views/LoginView.jsp";

    @Before
    public void createMocks() {
        this.validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(this.validate);

        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
        when(this.request.getRequestDispatcher(this.PATH)).thenReturn(this.dispatcher);
    }


    @Test
    public void whenDoGetThenForwardToView() throws ServletException, IOException {
        new SigninInController().doGet(this.request, this.response);
        verify(this.request, times(1)).getRequestDispatcher(this.PATH);
        verify(this.request, never()).getSession();
        verify(this.dispatcher).forward(this.request, this.response);
    }

    @Test
    public void whenDoPostWithWrongLoginAndPassThenRedirect() throws ServletException, IOException {
        when(this.request.getParameter("login")).thenReturn("not existed");
        when(this.request.getParameter("password")).thenReturn("not existed");
        new SigninInController().doPost(this.request, this.response);
        verify(this.request, times(1)).getRequestDispatcher(this.PATH);
        verify(this.dispatcher).forward(this.request, this.response);
        assertNull(this.request.getSession(false));
    }

    @Test
    public void whenDoPostWithRightLoginAndPassThenRedirect() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        when(this.request.getParameter("login")).thenReturn("root");
        when(this.request.getParameter("password")).thenReturn("root");
        when(this.request.getSession()).thenReturn(session);
        new SigninInController().doPost(this.request, this.response);
        verify(this.response).sendRedirect(String.format("%s/", this.request.getContextPath()));
    }

}