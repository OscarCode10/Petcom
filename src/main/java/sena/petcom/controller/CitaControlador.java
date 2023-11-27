package sena.petcom.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import sena.petcom.model.GeneradorPDF;
import sena.petcom.model.Agenda.IAgenda;
import sena.petcom.model.Cita.Cita;
import sena.petcom.model.Cita.ICita;
import sena.petcom.model.Cliente.ICliente;
    
@Controller

public class CitaControlador {
    @Autowired
    private ICita iCita;

    @Autowired
    private ICliente iCliente;

    @Autowired
    private IAgenda iAgenda;

    @GetMapping("/moduloCita")
    public String moduloCita() {
        return "cita/moduloCita";
    }

    @GetMapping("/registrarCitaV/{idAgenda}")
    public String registrarCitaV(@PathVariable Integer idAgenda, Model m) {
        if (idAgenda > 0) {
            Cita cita = new Cita();
            cita.setEstadoCita(true);
            cita.setFK(iAgenda.findOne(idAgenda));
            m.addAttribute("cita", cita);
            m.addAttribute("clientes", iCliente.findAll());
            return "cita/registrarCita";
        }
        return "redirect:/listarAgenda";
    }

    @PostMapping("/registrarCita")
    public String registrarCita(@Validated Cita cita, BindingResult result, Model m, SessionStatus status) {

        if (result.hasErrors()) {
            return "redirect:/registrarCitaV";
        } else {
            iCita.save(cita);
            status.setComplete();
            return "redirect:/moduloCita";
        }
    }

    @GetMapping("/listarCita")
    public String listarCita(Model model) {
        model.addAttribute("citas", iCita.findAll());
        return "cita/listarCita";
    }

    @GetMapping("/listarCita/{idAgenda}")
    public String listarCitaId(@PathVariable Integer idAgenda ,Model model) {
        if (idAgenda > 0) {
            List<Cita> citasAgenda = iCita.findAll().stream()
                .filter(cita -> cita.getFK().getIdAgenda().equals(idAgenda))
                .collect(Collectors.toList());
            model.addAttribute("citasAgenda", citasAgenda);
            return "cita/listarCita";    
        }
        return "redirect:/listarAgenda";
    }

    @GetMapping("/modificarCitaV/{idCita}")
    public String modificarCita(@PathVariable Integer idCita, Model model) {
        Cita cita = null;
        if (idCita > 0) {
            cita = iCita.findOne(idCita);
            model.addAttribute("cita", cita);
            return "cita/modificarCita";
        }
        return "redirect:/listCita";
    }

    @PostMapping("/modificarCita")
    public String modificarCita(@Validated Cita cita, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            return "redirect:/modificarCitaV" + cita.getIdCita();
        } else {
            iCita.save(cita);
            status.setComplete();
            return "redirect:/listCita";
        }
    }

    @GetMapping("/generarCitaPdf/{idCita}")
    public String generarCitaPdf(@PathVariable Integer idCita, Model m, HttpServletResponse resp)
            throws DocumentException, IOException {
        if (idCita > 0) {
            Cita cita = iCita.findOne(idCita);
            resp.setContentType("application/pdf");
            LocalDate fechaActual = LocalDate.now();
            String fechaActualString = fechaActual.toString();
            String head = "Content-Disposition";
            String value = "attachment; filename=Cita_" + cita.getFkC().getNombreCliente() + "_" + fechaActualString
                    + ".pdf";
            resp.setHeader(head, value);
            GeneradorPDF generadorPDF = new GeneradorPDF(cita);
            generadorPDF.exportCita(resp);
        }
        return "redirect:/listarCita";
    }
}