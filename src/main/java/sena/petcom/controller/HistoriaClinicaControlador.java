package sena.petcom.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import sena.petcom.model.HistoriaClinica.IHistoriaClinica;
import sena.petcom.model.GeneradorPDF;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;

@Controller
public class HistoriaClinicaControlador {

    @Autowired
    private IHistoriaClinica iHistoriaClinica;

    @GetMapping("/moduloHistoria")
    public String moduloHistoria() {
        return "historiaClinica/moduloHistoria";
    }

    @GetMapping("/registrarHistoriaV")
    public String registrarHistoriaV(Model m) {
        m.addAttribute("historia", new HistoriaClinica());
        return "historiaClinica/registrarHistoria";
    }

    @PostMapping("/registrarHistoria")
    public String registrarHistoria(@Validated HistoriaClinica historia, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/registrarHistoriaV";
        } else {
            iHistoriaClinica.save(historia);
            return "redirect:/moduloHistoria";
        }
    }

    @GetMapping("/listarHistoria")
    public String listarHistoria(Model m) {
        m.addAttribute("historias", iHistoriaClinica.findAll());
        return "historiaClinica/listarHistoria";
    }

    @GetMapping("/reporteHistoriaV/{idHistoriaClinica}")
    public String reporteHistoria(@PathVariable Integer idHistoriaClinica, Model m) {
        m.addAttribute("historiaClinica", iHistoriaClinica.findOne(idHistoriaClinica));
        return "historiaClinica/reporteHistoria";
    }

    @GetMapping("/generarHistoriaPdf/{idHistoriaClinica}")
    public String reporteHistoriaV(@PathVariable Integer idHistoriaClinica, Model m, HttpServletResponse resp) throws DocumentException, IOException {
        if (idHistoriaClinica > 0) {
            HistoriaClinica historiaClinica = iHistoriaClinica.findOne(idHistoriaClinica);
            resp.setContentType("application/pdf");
            LocalDate fechaActual = LocalDate.now();
            String fechaActualString = fechaActual.toString();
            String head = "Content-Disposition";
            String value = "attachment; filename=Histora_Clinica_" + historiaClinica.getFK().getNombreMascota() + "_" + fechaActualString + ".pdf";
            resp.setHeader(head, value);
            GeneradorPDF generadorPDF = new GeneradorPDF(historiaClinica);
            generadorPDF.exportHistoria(resp);
            return "redirect:/reporteHistoria/{idHistoriaClinica}";
        }
        return "redirect:/listarHistoria";
    }
}