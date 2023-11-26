package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import sena.petcom.model.Cliente.Cliente;
import sena.petcom.model.Cliente.ICliente;
import sena.petcom.model.Mascota.IMascota;
import sena.petcom.model.Mascota.Mascota;

@Controller
public class MascotaControlador {

    @Autowired
    private IMascota iMascota;

    @Autowired
    private ICliente iCliente;

    @GetMapping("/moduloMascota")
    public String moduloMascota() {
        return "mascota/moduloMascota";
    }

    @GetMapping("/registrarMascotaV/{idCliente}")
    public String registrarMascotaV(@PathVariable Integer idCliente ,Model m) {
        if (idCliente > 0) {
            Mascota mascota = new Mascota();
            mascota.setEstadoMascota(true);
            mascota.setFK(iCliente.findOne(idCliente));
            m.addAttribute("mascota", mascota);
            return "mascota/registrarMascota";
        }
        return "redirect/listarCliente";
    }

    @PostMapping("/registrarMascota")
    public String registrarMascota(@Validated Mascota mascota, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/registrarMascotaV";
        } else {
            iMascota.save(mascota);
            return "redirect:/moduloMascota";
        }
    }

    @GetMapping("/listarMascota")
    public String listarMascota(Model m) {
        m.addAttribute("mascotas", iMascota.findAll());
        return "mascota/listarMascota";
    }

    @GetMapping("/modificarMascotaV/{idMascota}")
    public String modificarMascotaV(@PathVariable Integer idMascota, Model m) {
        Mascota mascota = null;
        if (idMascota > 0) {
            mascota = iMascota.findOne(idMascota);
            m.addAttribute("mascota", mascota);
            return "mascota/modificarMascota";
        }
        return "redirect:/listarMasco";
    }

    @PostMapping("/modificarMascota")
    public String modificarMascota(@Validated Mascota mascota, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "redirect:/modificarMascotaV/{idMascota}";
        } else {
            iMascota.save(mascota);
            status.setComplete();
            return "redirect:/listarMasco";
        }
    }

}
