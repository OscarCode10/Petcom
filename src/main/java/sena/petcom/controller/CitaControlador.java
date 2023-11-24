package sena.petcom.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import sena.petcom.model.GeneradorPDF;
import sena.petcom.model.Cita.Cita;
import sena.petcom.model.Cita.ICita;
import sena.petcom.model.DetallesHistoria.DetallesHistoria;

@Controller

public class CitaControlador {
    @Autowired
    private ICita iCita;

    @GetMapping("/moduloCita")
    public String moduloCita() {
        return "cita/moduloCita";
    }

    @GetMapping("/registrarCitaV")
    public String registrarCitaV(Model m) {
        Cita cita = new Cita();
        cita.setEstadoCita(true);
        m.addAttribute("cita", cita);
        return "cita/registrarCita";
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
    public String generarCitaPdf(@PathVariable Integer idCita, Model m, HttpServletResponse resp) throws DocumentException, IOException {
        if (idCita > 0) {
            Cita cita = iCita.findOne(idCita);
            resp.setContentType("application/pdf");
            LocalDate fechaActual = LocalDate.now();
            String fechaActualString = fechaActual.toString();
            String head = "Content-Disposition";
            String value = "attachment; filename=Cita_" + cita.getFkC().getNombreCliente() + "_" + fechaActualString + ".pdf";
            resp.setHeader(head, value);
            GeneradorPDF generadorPDF = new GeneradorPDF(cita);
            generadorPDF.exportCita(resp);
        }
        return "redirect:/listarCita";
    }

}