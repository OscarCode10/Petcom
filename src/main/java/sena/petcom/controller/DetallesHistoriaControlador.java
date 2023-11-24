package sena.petcom.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import sena.petcom.model.DetallesHistoria.DetallesHistoria;
import sena.petcom.model.DetallesHistoria.IDetallesHistoria;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;
import sena.petcom.model.HistoriaClinica.HistoriaClinicaPDF;
import sena.petcom.model.HistoriaClinica.IHistoriaClinica;

@Controller
public class DetallesHistoriaControlador {

    @Autowired
    private IDetallesHistoria iDetallesHistoria;

    @Autowired
    private IHistoriaClinica iHistoriaClinica;

    @GetMapping("/registrarDetalleV/{idHistoriaClinica}")
    public String agregarDetalleV(@PathVariable Integer idHistoriaClinica, Model m){
        DetallesHistoria detalle = new DetallesHistoria();
        detalle.setFK(iHistoriaClinica.findOne(idHistoriaClinica));
        m.addAttribute("historia", iHistoriaClinica.findOne(idHistoriaClinica));
        m.addAttribute("detalle", detalle);
        return "detallesHistoria/registrarDetalle";
    }

    @PostMapping("/registrarDetalle")
    public String registrarDetalle(@Validated DetallesHistoria detallesHistoria, BindingResult res, SessionStatus status, Model m){
        if (res.hasErrors()) {
            return "redirect:/registrarDetalleV/{idHistoriaClinica}";
        } else {
            iDetallesHistoria.save(detallesHistoria);
            status.setComplete();
            return "redirect:/listarHistoria";
        }
    }

    @GetMapping("/listarDetalle/{idHistoriaClinica}")
    public String listarDetalle(@PathVariable Integer idHistoriaClinica, Model m){
        List<DetallesHistoria> detallesFiltrados = iDetallesHistoria.findAll().stream()
            .filter(detalle -> detalle.getFK().getIdHistoriaClinica().equals(idHistoriaClinica))
            .collect(Collectors.toList());
        m.addAttribute("detalles", detallesFiltrados);
        return "detallesHistoria/listarDetalle";
    }

    @GetMapping("/generarDetallesPdf/{idDetallesHistoria}")
    public String reporteHistoriaV(@PathVariable Integer idDetallesHistoria, Model m, HttpServletResponse resp) throws DocumentException, IOException {
        if (idDetallesHistoria > 0) {
            DetallesHistoria detallesHistoria = iDetallesHistoria.findOne(idDetallesHistoria);
            resp.setContentType("application/pdf");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String fechaActual = dateFormat.format(new Date());
            String head = "Content-Disposition";
            String value = "attachment; filename=Detalles_Historia_" + detallesHistoria.getFK().getFK().getNombreMascota() + "_" + fechaActual + ".pdf";
            resp.setHeader(head, value);
            HistoriaClinicaPDF historiaClinicaPDF = new HistoriaClinicaPDF(detallesHistoria);
            historiaClinicaPDF.exportDetalles(resp);
        }
        return "redirect:/listarDetalle/{idHistoriaClinica}";
    }
}
