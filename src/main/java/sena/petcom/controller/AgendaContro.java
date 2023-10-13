package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import sena.petcom.model.Agenda.Agenda;
import sena.petcom.model.Agenda.IAgenda;

@Controller
public class AgendaContro {
    @Autowired
    private IAgenda iAgenda;

    @GetMapping("/modulAgenda")
    public String modulAgenda(){
        return "modulAgenda";
    }

    @GetMapping("/registrarAgendaV")
    public String registrarAgendaV(Model m){
        m.addAttribute("agenda", new Agenda());
        return "registrarAgenda";
    }

    @PostMapping("/registrarAgenda")
    public String registrarAgenda(@Validated Agenda agenda, BindingResult res, SessionStatus status){
        System.out.println(res.getAllErrors());
        if (res.hasErrors()) {
            return "redirect:/registrarAgendaV";
        } else{
            iAgenda.save(agenda);
            status.setComplete();
            return "redirect:/modulAgenda";
        }
    }

    @GetMapping("/listAgenda")
    public String listAgenda(Model m){
        m.addAttribute("agenda", iAgenda.findAll());
        return "listAgenda";
    }

    @GetMapping("/modificarAgendaV/{idAgenda}")
    public String modificarAgendaV(@PathVariable Integer idAgenda, Model m){
        if(idAgenda>0){
            m.addAttribute("agenda", iAgenda.findOne(idAgenda));
            return "modificarAgenda";
        }
        return "redirect:/listAgenda";
    }

    @PostMapping("/modificarAgenda")
    public String modificarAgenda(@Validated Agenda agenda, BindingResult res, SessionStatus status){
        if (res.hasErrors()) {
            return "modificarAgendaV/{idAgenda}";
        } else {
            iAgenda.save(agenda);
            status.setComplete();
            return "redirect:/modulAgenda";
        }
    }
}
