package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sena.petcom.model.Usuario.IUsuario;
import sena.petcom.model.Usuario.Usuario;

@Controller
public class UsuarioControlador {

    @Autowired
    private IUsuario iUsuario;

    @GetMapping(path = { "/", "/index", "/home" })
    public String index(Model m) {
        m.addAttribute("usuario", new Usuario());
        return "index";
    }

    @GetMapping("/accesoDenegado")
    public String accesoDenegado(Model m) {
        m.addAttribute("error", "No cuentas con esos permisos");
        return "usuario/dashUsuario";
    }

    @PostMapping("/login")
    public String login(@RequestParam("correoUsu") String correoUsu, @RequestParam("claveUsu") String claveUsu, HttpServletRequest req, Model m) {
        if (correoUsu != "" && claveUsu != "") {
            Integer idUsuario = iUsuario.login(correoUsu, claveUsu);
            if (idUsuario != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("userDetails", iUsuario.findOne(idUsuario));
                return "usuario/dashUsuario";
            } else {
                m.addAttribute("error", "El usuario no existe");
                m.addAttribute("usuario", new Usuario()); 
                return "index";
            }
        } else {
            m.addAttribute("error", "Los datos no pueden ir vacíos");
            m.addAttribute("usuario", new Usuario()); 
            return "index";
        }
    }

    @GetMapping("/moduloUsuario")
    public String moduloUsuario(Model m) {
        return "usuario/moduloUsuario";
    }

    @GetMapping("/registrarUsuarioV")
    public String registrarUsuarioV(Model m) {
        Usuario usuario = new Usuario();
        usuario.setEstadoUsu(true);
        m.addAttribute("usuario", usuario);
        return "usuario/registrarUsuario";
    }

    @PostMapping("registrarUsuario")
    public String registrarUsuario(@Validated Usuario usuario, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "redirect:/registrarUsuarioV";
        } else {
            iUsuario.save(usuario);
            status.setComplete();
            return "redirect:/moduloUsuario";
        }
    }

    @GetMapping("/listarUsuario")
    public String listarUsuario(Model m) {
        m.addAttribute("usuarios", iUsuario.findAll());
        return "usuario/listarUsuario";
    }

    @GetMapping("/modificarUsuarioV/{idUsuario}")
    public String modificarUsuarioV(@PathVariable Integer idUsuario, Model m) {
        Usuario usuario = null;
        if (idUsuario > 0) {
            usuario = iUsuario.findOne(idUsuario);
            m.addAttribute("usuario", usuario);
            return "usuario/modificarUsuario";
        } else {
            return "redirect:/listarUsuario";
        }
    }

    @PostMapping("/modificarUsuario")
    public String modificarUsuario(@Validated Usuario usuario, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "redirect:/modificarUsuarioV/{idUsuario}";
        } else {
            iUsuario.save(usuario);
            status.setComplete();
            return "redirect:/listarUsuario";
        }
    }

    @GetMapping("/cerrar")
    public String cerrar(Model m, HttpServletRequest req) {
        HttpSession findSession = req.getSession();
        findSession.removeAttribute("userDetails");
        findSession.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/verPerfil")
    public String verPerfil(Model m, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("userDetails");
        if (usuario != null) {
            m.addAttribute("usuario", usuario);
            return "usuario/verPerfil";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/editarPerfil")
    public String mostrarFormularioEditarPerfil(Model model, HttpSession session) {
        // Obtén el usuario actual de la sesión o de la base de datos
        Usuario usuario = (Usuario) session.getAttribute("userDetails");

        // Si no está en la sesión, puedes cargarlo desde la base de datos
        if (usuario == null) {
            // Cargar el usuario según su identificador (por ejemplo, su ID)
            // usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        }

        model.addAttribute("usuario", usuario);

        return "usuario/editarPerfil";
    }

    @PostMapping("/editarPerfil")
    public String editarPerfil(@Validated @ModelAttribute("usuario") Usuario usuario, BindingResult res, Model m,
            SessionStatus status, HttpSession session) {
        if (res.hasErrors()) {
            return "usuario/editarPerfil";
        } else {
            iUsuario.save(usuario);
            status.setComplete();
            session.setAttribute("userDetails", usuario);
            return "redirect:/verPerfil";
        }
    }
}