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

import static org.mockito.Mockito.*;

/**
 * UsersServletTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UsersServletTest {


    private UsersServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private Validate validate;
    private HttpSession session;


    @Before
    public void createMocks() {
        this.validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(this.validate);
        this.session = mock(HttpSession.class);
        this.servlet = new UsersServlet();
        this.request = PowerMockito.mock(HttpServletRequest.class);
        this.response = PowerMockito.mock(HttpServletResponse.class);
        this.dispatcher = PowerMockito.mock(RequestDispatcher.class);
    }

    @Test
    public void whens() throws IOException, ServletException {
        when(this.request.getRequestDispatcher("/WEB-INF/views/UserView.jsp")).thenReturn(dispatcher);
        when(this.session.getAttribute("uid")).thenReturn("1");
        when(this.request.getSession()).thenReturn(this.session);
        this.servlet.doGet(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
    }
}