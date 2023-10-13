package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import sena.petcom.model.HisoriaClinica.IHistoriaClinica;

@Controller
public class HistoriaClinicaContro {
    @Autowired
    private IHistoriaClinica iHistoriaClinica;

     @GetMapping("/modulHisto")
    public String modulHisto(){
        return "modulHisto";
    }
}
