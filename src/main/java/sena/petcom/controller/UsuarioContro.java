package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sena.petcom.model.Usuario.IUsuario;
import sena.petcom.model.Usuario.Usuario;

@Controller
public class UsuarioContro {

    @Autowired
    private IUsuario user;

    @GetMapping(path = {"/", "/index", "/home"})
    public String nose(Model m){
        m.addAttribute("usuario", new Usuario());
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("correoUsu") String correoUsu, @RequestParam("claveUsu") String claveUsu, HttpServletRequest req, Model m){
        if (correoUsu!=null && claveUsu!=null) {
            Integer idUsuario = user.login(correoUsu, claveUsu);
            if (idUsuario != null) {
                HttpSession session = req.getSession();
                session.setAttribute("userDetails", user.findOne(idUsuario));
                return "admin";
            }else{  
                return "redirect:/index";
            }
        }else{
            return "redirect:/index";
        }
    }

    @GetMapping("/usu")
    public String mo(Model m){
        return "modulUser";
    }

    @GetMapping("/registrarUsuV")
    public String re(Model m){
        m.addAttribute("usuario", new Usuario());
        return "registrarUsu";
    }

    @PostMapping("registrarUsu")
    public String addU(@Validated Usuario usuario, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "redirect:/usu";
        }else{
            user.save(usuario);
            status.setComplete();
            return "redirect:/usu";
        }
    }

    @GetMapping("/listUsu")
    public String listar(Model m) {
        m.addAttribute("usuarios", user.findAll());
        return "/listUsu";
    }

    @GetMapping("/modificarUsuV/{idUsuario}")
    public String verModi(@PathVariable Integer idUsuario, Model m){
        Usuario usuario=null;
        if(idUsuario>0){
            usuario=user.findOne(idUsuario);
            m.addAttribute("usuario",usuario);
            return "modificarUsuario";
        }else{
            return "redirect:/listUsu";
        }
    }

    @PostMapping("/modificarUsu")
    public String modi(@Validated Usuario usuario, BindingResult res, Model m, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/modificarUsuV/{idUsuario}";
        }else{
            user.save(usuario);
            status.setComplete();
            return "redirect:/listUsu";
        }
    }

    @GetMapping("/cerrar")
    public String cerrar(Model m, HttpServletRequest req){
        req.getSession().invalidate();
        return "redirect:/index";
    }
    
}
