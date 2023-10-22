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

    @GetMapping("/registrarHistoriaClinicaV")
    public String registrarHistoriaClinicaV(Model m ){
        m.addAttribute("histo", new HistoriaClinicaContro());
        return "registrarHistoriaClinica";
    }

    @GetMapping("/registrarHistoriaClinica")
    public String regisHistoria(@Validated HistoriaClinica histo, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/registrarHistoriaClinicaV";
        }
        else{
            iHistoriaClinica.save(histo);
            return "redirect:/modulHisto";
        }
    }

    @GetMapping("/listoHisto")
    public String verHisto(Model m){
        m.addAttribute("historias",iHistoriaClinica.findAll());
        return "listHisto";
    }

    @GetMapping("/modificarM/{idHistoriaClinica}")
    public String modificarHisto(@PathVariable Integer idHistoriaClinica, Model m) {
        HistoriaClinica historiaClinica=null;
        if (idHistoriaClinica>0) {
            historiaClinica=iHistoriaClinica.findOne(idHistoriaClinica);
            m.addAttribute("historiaClinica", historiaClinica);
            return "modificarHistoriaClinica";
        }
        return "redirect:/listHisto";   
    }

     @PostMapping("/modificarHisto")
    public String modificarHisto(@Validated HistoriaClinica historiaClinica, BindingResult res, Model m, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/modificarM/{idMascota}";
        }else{
            iHistoriaClinica.save(historiaClinica);
            status.setComplete();
            return "redirect:/listMasco";
        }
    }

    
    
}
