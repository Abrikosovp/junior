package ru.shifu.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.shifu.userstorage.logic.Action;
import ru.shifu.userstorage.logic.Validate;
import ru.shifu.userstorage.logic.ValidateService;
import ru.shifu.userstorage.logic.ValidateStub;
import ru.shifu.userstorage.models.PersonalData;
import ru.shifu.userstorage.models.Role;
import ru.shifu.userstorage.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * UserControllerTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserControllerTest {

    private Validate validate;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private HttpSession session;


    @Before
    public void createMocks() {
        session = PowerMockito.mock(HttpSession.class);
        this.validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(this.validate);

        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenAdd() throws IOException, ServletException {
        this.validate.fullDelete();
        when(this.request.getParameter("action")).thenReturn("add");
        when(this.request.getParameter("name")).thenReturn("Add Admin");
        when(this.request.getParameter("login")).thenReturn("moot");
        when(this.request.getParameter("password")).thenReturn("moot");
        when(this.request.getParameter("role")).thenReturn("ADMIN");
        when(this.request.getParameter("email")).thenReturn("admin@mail");
        when(this.request.getRequestDispatcher("/WEB-INF/views/UserView.jsp")).thenReturn(dispatcher);

        when(this.session.getAttribute("login")).thenReturn("moot");
        when(this.request.getSession()).thenReturn(this.session);
        new UserController().doPost(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
        assertThat(this.validate.findAll().size(), is(1));
    }

    @Test
    public void whenUpdate() throws IOException, ServletException {
        User first = new User("19", "19", "s", Role.ADMIN, new PersonalData("Pavel", "abriksovp@mail.ru", "Russia", "Moscow"));
        this.validate.doAction(Action.Type.ADD, first);

        when(this.request.getParameter("action")).thenReturn("update");
        when(this.request.getParameter("id")).thenReturn("19");
        when(this.request.getParameter("name")).thenReturn("Updated Admin");
        when(this.request.getParameter("login")).thenReturn("moot");
        when(this.request.getParameter("password")).thenReturn("moot");
        when(this.request.getParameter("role")).thenReturn("ADMIN");
        when(this.request.getParameter("email")).thenReturn("admin@mail");
        when(this.request.getParameter("city")).thenReturn("Moscow");
        when(this.request.getParameter("country")).thenReturn("admin@mail");
        when(this.request.getRequestDispatcher("/WEB-INF/views/UserView.jsp")).thenReturn(this.dispatcher);

        when(this.session.getAttribute("login")).thenReturn("moot");
        when(this.request.getSession()).thenReturn(this.session);

        new UserController().doPost(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
        assertThat(this.validate.findById("19").getName(), is("Updated Admin"));
    }

    @Test
    public void whenDelete() throws IOException, ServletException {
        User first = new User("199", "199", "second", Role.ADMIN, new PersonalData("Pavel", "abriksovp@mail.ru", "Russia", "Moscow"));
        this.validate.doAction(Action.Type.ADD, first);
        assertNotNull(this.validate.findById("199"));

        when(this.request.getParameter("action")).thenReturn("delete");
        when(this.request.getParameter("id")).thenReturn("199");
        when(this.request.getParameter("role")).thenReturn("ADMIN");

        when(this.request.getRequestDispatcher("/WEB-INF/views/UserView.jsp")).thenReturn(this.dispatcher);
        when(this.session.getAttribute("login")).thenReturn("moot");
        when(this.request.getSession()).thenReturn(this.session);

        new UserController().doPost(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
        assertNull(this.validate.findById("199"));
    }


    @Test
    public void whenAddNanSession() throws IOException, ServletException {
        when(this.request.getParameter("action")).thenReturn("add");
        when(this.request.getParameter("id")).thenReturn(null);
        when(this.request.getParameter("id")).thenReturn("");
        when(this.request.getParameter("role")).thenReturn("ADMIN");
        when(this.request.getRequestDispatcher("/WEB-INF/views/Enter.jsp")).thenReturn(this.dispatcher);

        when(this.request.getSession()).thenReturn(this.session);
        when(this.session.getAttribute("login")).thenReturn(null);
        new UserController().doPost(this.request, this.response);
        verify(this.dispatcher).forward(this.request, this.response);
    }
}