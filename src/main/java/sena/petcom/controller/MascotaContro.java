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

import sena.petcom.model.Mascota.IMascota;
import sena.petcom.model.Mascota.Mascota;

@Controller
public class MascotaContro {

    @Autowired
    private IMascota iMascota;

    @GetMapping("/modulMasco")
    public String modulMasco(){
        return "modulMascota";
    }

    @GetMapping("/regisMascoV")
    public String regisMascoV(Model m){
        m.addAttribute("masco", new Mascota());
        return "registrarMasco";
    }

    @PostMapping("/regisMasco")
    public String regisMasco(@Validated Mascota masco, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/regisMascoV";
        }
        else{
            iMascota.save(masco);
            return "redirect:/modulMasco";
        }
        
    }
    
    @GetMapping("/listMasco")
    public String verMasco(Model m){
        m.addAttribute("mascotas",iMascota.findAll());
        return "listMasco";
    }

    @GetMapping("/modificarM/{idMascota}")
    public String modificarM(@PathVariable Integer idMascota,Model m){
        Mascota mascota=null;
        if (idMascota>0) {
            mascota=iMascota.findOne(idMascota);
            m.addAttribute("mascota", mascota);
            return "modificarMascota";
        }
        return "redirect:/listMasco";
    }


    
    @PostMapping("/modificarMasco")
    public String modi(@Validated Mascota mascota, BindingResult res, Model m, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/modificarM/{idMascota}";
        }else{
            iMascota.save(mascota);
            status.setComplete();
            return "redirect:/listMasco";
        }
    }

}
