package sena.petcom.controller;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.List;

import sena.petcom.model.HistoriaClinica.IHistoriaClinica;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;

@Controller
public class HistoriaClinicaControlador {
    
    @Autowired
    private IHistoriaClinica iHistoriaClinica;

    @GetMapping("/moduloHistoria")
    public String moduloHistoria(){
        return "historiaClinica/moduloHistoria";
    }

    @GetMapping("/registrarHistoriaV")
    public String registrarHistoriaV(Model m){
        m.addAttribute("historia", new HistoriaClinica());
        return "historiaClinica/registrarHistoria";
    }

    @PostMapping("/registrarHistoria")
    public String registrarHistoria(@Validated HistoriaClinica historia, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/registrarHistoriaV";
        } else {
            iHistoriaClinica.save(historia);
            return "redirect:/moduloHistoria";
        }
    }

    @GetMapping("/listarHistoria")
    public String listarHisto(Model m){
        m.addAttribute("historias", iHistoriaClinica.findAll());
        return "historiaClinica/listarHistoria";
    }
    
    @GetMapping("/reporteHistoria")
    public String reporteHistoria(){
        return "historiaClinica/reporteHistoria";
    }
    
    @GetMapping("/reporteHistoriaV/{idHistoriaClinica}")
    public String reporteHistoriaV(@PathVariable Integer idHistoriaClinica,Model m){
        HistoriaClinica historia=null;
        if (idHistoriaClinica>0) {
            historia=iHistoriaClinica.findOne(idHistoriaClinica);
            m.addAttribute("historia", historia );
            return "HistoriaClinica/reporteHistoria";
        }
        return "redirect:/listarHistoria";
    }

    

    
}
