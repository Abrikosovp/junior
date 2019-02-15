package ru.shifu.userstorage.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authentication filter
 * If the session contains the login parameter, the filter skips on.
 *
 * @author Pavel Abrikosov (abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 30.01.2019
 */
public class AuthFilter implements Filter {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    /**
     * Init filter
     *
     * @param filterConfig filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        LOG.info("Init Filter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/guest") || request.getRequestURI().contains("/signin") || request.getRequestURI().contains("/create") || request.getRequestURI().contains("/json") || request.getRequestURI().contains("/Index.html") || request.getRequestURI().contains("/loc")) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/guest", request.getContextPath()));
                return;
            }
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}