package sena.petcom.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import sena.petcom.model.Cita.Cita;
import sena.petcom.model.Cita.ICita;

@Controller

public class CitaContro {
    
    private ICita iCita;

 
    public CitaContro(ICita iCita) {
        this.iCita = iCita;
    }

    @GetMapping("/modulCita")
    public String modulCita(){
        return "modulCita";
    }
    
   @GetMapping("/registrarCitaV")
    public String registrarCitaV(Model model){
    model.addAttribute("cita", new Cita());
    return "registrarCita";
}
  

    @GetMapping("/modificarCitaV")
    public String modificarCita(){
        return "modificarCita";
    }

    @GetMapping("/listCitaV")
    public String listCita(){
        return"listCita";
    }

    @PostMapping("/registrarCita")
    public String regisMasco(@Validated Cita cita, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/registrarCitaV";
        }
        else{
            iCita.save(cita);
            return "redirect:/listCita";
        }
        
    }

    @GetMapping("/listCita")
    public String listCitas(Model model) {
        List<Cita> citas = iCita.findAll();  // Obtener la lista de citas desde tu servicio o repositorio
        model.addAttribute("citas", citas);  // Pasar la lista de citas a la vista
        return "listCita";  // Renderizar la vista
    }


    @GetMapping("/modificarCitaV/{idCita}")
public String modificarCita(@PathVariable Integer idCita, Model model) {
    Cita cita = null;
    if (idCita > 0) {
        cita = iCita.findOne(idCita);
        model.addAttribute("cita", cita);
        return "modificarCita";
    }
    return "redirect:/listCita";
}

    
    @PostMapping("/modificarCita")
    public String modificarCita(@Validated Cita cita, BindingResult result, Model model, SessionStatus status) {
    if (result.hasErrors()) {
        return "redirect:/modificarCitaV" + cita.getIdCita();
    } else {
        // Llama al servicio para actualizar la cita.
        iCita.save(cita);
        status.setComplete();
        return "redirect:/listCita";
    }
}


}