package ru.shifu.userstorage.presentation;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
/**
 * AuthFilterTest.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.5$
 * @since 0.1
 * 02.02.2019
 */
public class AuthFilterTest {

    @Test
    public void whenDoFilterThenGetResult() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        when(req.getRequestURI()).thenReturn("/update");

        new AuthFilter().doFilter(req, resp, filterChain);
        verify(req, times(1)).getSession();
        verify(session, times(1)).getAttribute("login");

        when(session.getAttribute("login")).thenReturn("login444");
        new AuthFilter().doFilter(req, resp, filterChain);
        verify(filterChain, times(1)).doFilter(req, resp);
    }
}