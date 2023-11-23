package sena.petcom.controller;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sena.petcom.model.Usuario.Usuario;

@WebFilter(urlPatterns = {"/*"})
public class SessionControlador implements Filter {
    //Metodo que se encarga de ver cada petición y hacer la correspondiente dirección
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;

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

        //Validación de roles
        Usuario usuario = (Usuario) session.getAttribute("userDetails");
        String urlAccesoDenegado = httpReq.getContextPath() + "/accesoDenegado";
        if (usuario.getFK().getIdRol() == 2 || usuario.getFK().getIdRol() == 3 || usuario.getFK().getIdRol() == 4) {
            //Usuario
            if ("/moduloUsuario".equals(httpReq.getRequestURI()) || "/registrarUsuarioV".equals(httpReq.getRequestURI()) || "/listarUsuario".equals(httpReq.getRequestURI())) {
                httpResp.sendRedirect(urlAccesoDenegado);
                return;
            }
            //Mascota - Cliente
            if (usuario.getFK().getIdRol() != 2) {
                //Mascota
                if ("/moduloMascota".equals(httpReq.getRequestURI()) || "/registrarMascotaV".equals(httpReq.getRequestURI()) || "/listarMascota".equals(httpReq.getRequestURI())) {
                    httpResp.sendRedirect(urlAccesoDenegado);
                    return;
                }

                if ("/moduloCliente".equals(httpReq.getRequestURI()) || "/registrarClienteV".equals(httpReq.getRequestURI()) || "/listarCliente".equals(httpReq.getRequestURI())) {
                    httpResp.sendRedirect(urlAccesoDenegado);
                    return;
                }   
            }
        }
        chain.doFilter(req, resp);
    }
}
