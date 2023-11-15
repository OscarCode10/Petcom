package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sena.petcom.model.HistoriaClinica.IHistoriaClinica;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;

@Controller
public class HistoriaClinicaContro {
    
    @Autowired
    private IHistoriaClinica iHistoriaClinica;

    

     @GetMapping("/modulHisto")
    public String modulHisto(){
        return "modulHisto";
    }

    @GetMapping("/regisHistoV")
    public String registHistoV(Model m){
        m.addAttribute("histo", new HistoriaClinica());
        return "registrarHisto";
    }

    @PostMapping("/regisHisto")
    public String regisHisto(@Validated HistoriaClinica histo, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/regisHistoV";
        } else {
            iHistoriaClinica.save(histo);
            return "redirect:/modulHisto";
        }
    }



    @GetMapping("/listHisto")
    public String verHisto(Model m){
        m.addAttribute("historias", iHistoriaClinica.findAll());
        return "listHisto";
    }
    
}
