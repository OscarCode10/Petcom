package sena.petcom.controller;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"})
public class SessionControlador implements Filter {
    //Metodo que se encarga de ver cada petición y hacer la correspondiente dirección
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp= (HttpServletResponse) resp;

        // Excluir ciertos tipos de solicitudes (recursos estáticos) del filtro
        if (httpReq.getRequestURI().startsWith("/static/") || httpReq.getRequestURI().startsWith("/css/") || httpReq.getRequestURI().startsWith("/img/") || httpReq.getRequestURI().startsWith("/js/")) {
            chain.doFilter(req, resp);
            return;
        }

        // Excluir ciertas URL del filtro (por ejemplo, la página de inicio y el proceso de inicio de sesión)
        if ("/index".equals(httpReq.getRequestURI()) || "/login".equals(httpReq.getRequestURI())) {
            chain.doFilter(req, resp);
            return;
        }

        HttpSession session = httpReq.getSession(false);

        // Redirigir a la página de inicio de sesión si no hay una sesión o si no hay detalles de usuario en la sesión
        if (session == null || session.getAttribute("userDetails") == null) {
            httpResp.sendRedirect(httpReq.getContextPath() + "/index");
            return;
        }

        // Usuario usuario = (Usuario) session.getAttribute("userDetails");
        chain.doFilter(req, resp);
    }
}
