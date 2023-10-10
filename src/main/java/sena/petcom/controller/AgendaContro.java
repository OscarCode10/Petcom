package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import sena.petcom.model.Agenda.IAgenda;

@Controller
public class AgendaContro {
    @Autowired
    private IAgenda iAgenda;

    @GetMapping("/modulAgenda")
    public String modulAgenda(){
        return "modulAgenda";
    }

}
