package sena.petcom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import sena.petcom.model.Cita.Cita;

@Controller
public class CitaContro {
    
    @GetMapping("/modulCita")
    public String modulCita(){
        return "modulCita";
    }

    @GetMapping("/registrarCitaV")
    public String registrarCitaV(){
        return "registrarCita";
    }

    // @PostMapping("/registrarCita")
    // public String registrarCita(@Validated Cita cita, BindingResult res, SessionStatus status){
    //     if (res.hasErrors()) {
    //         return "redirect:/registerCita";
    //     }
    // }

}
