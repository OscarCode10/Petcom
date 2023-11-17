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

import sena.petcom.model.Agenda.Agenda;
import sena.petcom.model.Agenda.IAgenda;
import sena.petcom.model.Usuario.IUsuario;
import sena.petcom.model.Usuario.Usuario;
import sena.petcom.model.agendaUsuario.IagendaUsuario;
import sena.petcom.model.agendaUsuario.agendaUsuario;

@Controller
public class AgendaControlador {
    @Autowired
    private IAgenda iAgenda;

    @Autowired IUsuario iUsuario;

    @Autowired IagendaUsuario iagendaUsuario;

    @GetMapping("/moduloAgenda")
    public String modulAgenda(){
        return "agenda/moduloAgenda";
    }

    @GetMapping("/registrarAgendaV")
    public String registrarAgendaV(Model m){
        m.addAttribute("agenda", new Agenda());
        m.addAttribute("usuarios", iUsuario.findAll());
        m.addAttribute("usuario", new Usuario());
        return "agenda/registrarAgenda";
    }

    @PostMapping("/registrarAgenda")
    public String registrarAgenda(@Validated Agenda agenda, @RequestParam String idUsuario, BindingResult res, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/registrarAgendaV";
        } else{
            if (Integer.parseInt(idUsuario) > 0) {
                iAgenda.save(agenda);
                status.setComplete();
                Usuario usu = iUsuario.findOne(Integer.parseInt(idUsuario));
                String tipoCita;

                if (usu.getFK().getIdRol() == 3) {
                    tipoCita = "Médica";
                }else{
                    tipoCita = "Estética";
                }

                agendaUsuario agenUsu = agendaUsuario.builder()
                    .tipoCita(tipoCita)
                    .FK(usu)
                    .FkA(agenda)
                    .build();

                iagendaUsuario.save(agenUsu);
                
                return "redirect:/moduloAgenda";
            }
            else{
                return "redirect:/registrarAgendaV";
            }
        }
    }

    @GetMapping("/listarAgenda")
    public String listarAgenda(Model m){
        m.addAttribute("agenda", iAgenda.findAll());
        return "agenda/listarAgenda";
    }

    @GetMapping("/modificarAgendaV/{idAgenda}")
    public String modificarAgendaV(@PathVariable Integer idAgenda, Model m){
        Agenda agenda = null;
        if(idAgenda > 0){
            agenda = iAgenda.findOne(idAgenda);
            m.addAttribute("agenda", agenda);
            return "agenda/modificarAgenda";
        }
        return "redirect:/listarAgenda";
    }

    @PostMapping("/modificarAgenda")
    public String modificarAgenda(@Validated Agenda agenda, BindingResult res, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/modificarAgendaV/{idAgenda}";
        } else {
            iAgenda.save(agenda);
            status.setComplete();
            return "redirect:/listarAgenda";
        }
    }
}