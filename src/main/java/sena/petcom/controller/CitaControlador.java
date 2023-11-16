package sena.petcom.controller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

import sena.petcom.model.Cita.Cita;
import sena.petcom.model.Cita.ICita;

@Controller

public class CitaControlador {
    @Autowired
    private ICita iCita;

    @GetMapping("/moduloCita")
    public String moduloCita(){
        return "cita/moduloCita";
    }
    
   @GetMapping("/registrarCitaV")
    public String registrarCitaV(Model m){
        m.addAttribute("cita", new Cita());
        return "cita/registrarCita";
    }
    
    @PostMapping("/registrarCita")
    public String registrarCita(@Validated Cita cita, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/registrarCitaV";
        }
        else{
            iCita.save(cita);
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

@Controller
@RequestMapping("/reporteCitas")
public class ReporteCitaController {

    @GetMapping("/pdf")
    @ResponseBody
    public byte[] generarReportePDF() throws IOException {
        // Crea un documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Contenido del informe
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Informe de Citas");
            // Agrega más contenido según tus necesidades
            contentStream.endText();
        }

        // Convierte el documento a un array de bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}

}
