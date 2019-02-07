package ru.shifu.servlets;
import org.junit.Test;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class EchoServletTest {

    @Test
    public void whenDoGetAndSessionHasNotValue() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletOutputStream outputStream = mock(ServletOutputStream.class);
        when(resp.getOutputStream()).thenReturn(outputStream);
        new EchoServlet().doGet(req, resp);
    }

}