package sena.petcom.model.HistoriaClinica;

import java.awt.Color;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.FontFactoryImp;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import sena.petcom.model.DetallesHistoria.DetallesHistoria;

public class HistoriaClinicaPDF {
    
    private HistoriaClinica historiaClinica;

    private DetallesHistoria detallesHistoria;

    public HistoriaClinicaPDF(DetallesHistoria detallesHistoria) {
        this.detallesHistoria = detallesHistoria;
    }

    public HistoriaClinicaPDF(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    // private void headReport(PdfPTable PdfPTable){
    //     PdfPCell cell = new PdfPCell();

    //     cell.setBackgroundColor(Color.GRAY);
    //     cell.setPadding(16);

    //     Font font = new FontFactoryImp().getFont(FontFactory.HELVETICA);
    //     cell.setPhrase(new Phrase("Historia Clinica de: ", font));
    //     PdfPTable.addCell(cell);
    //     cell.setPhrase(new Phrase("Tiene un peso de: ", font));
    //     PdfPTable.addCell(cell);
    //     cell.setPhrase(new Phrase("Tiene un tamaño de: ", font));
    //     PdfPTable.addCell(cell);
    //     cell.setPhrase(new Phrase("Tiene de enfermedades: ", font));
    //     PdfPTable.addCell(cell);
    //     cell.setPhrase(new Phrase("Tiene de antecendetes: ", font));
    //     PdfPTable.addCell(cell);
    //     cell.setPhrase(new Phrase("Y su diagnóstico es: ", font));
    //     PdfPTable.addCell(cell);
    // }

    // private void bodyReport(PdfPTable pdfPTable){
    //     pdfPTable.addCell(historiaClinica.getFK().getNombreMascota() + " del Cliente " + historiaClinica.getFK().getFK().getNombreCliente());
    //     pdfPTable.addCell(String.valueOf(historiaClinica.getPeso()));
    //     pdfPTable.addCell(String.valueOf(historiaClinica.getTamano()));
    //     pdfPTable.addCell(historiaClinica.getEnfermedades());
    //     pdfPTable.addCell(historiaClinica.getAntecedentes());
    //     pdfPTable.addCell(historiaClinica.getDiagnosticoHistoria());
    // }

    // public void export(HttpServletResponse resp) throws DocumentException, IOException{
    //     Document document = new Document(PageSize.A4);
    //     PdfWriter.getInstance(document, resp.getOutputStream());
    //     document.open();
    //     Font font = new FontFactoryImp().getFont(FontFactory.HELVETICA_BOLD);
    //     font.setColor(Color.WHITE);
    //     font.setSize(18);

    //     Paragraph paragraph = new Paragraph("Historia Clinica", font);
    //     paragraph.setAlignment(paragraph.ALIGN_CENTER);
    //     document.add(paragraph);
    //     PdfPTable pdfPTable = new PdfPTable(6);
    //     pdfPTable.setWidthPercentage(100);
    //     pdfPTable.setSpacingBefore(15);
    //     pdfPTable.setWidths(new float[] {6f, 5f, 5f, 4f, 6f, 7f});
    //     pdfPTable.setWidthPercentage(110);

    //     headReport(pdfPTable);
    //     bodyReport(pdfPTable);

    //     document.add(pdfPTable);
    //     document.close();
    // }

    public void exportHistoria(HttpServletResponse resp) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, resp.getOutputStream());
        document.open();

        Font font = new FontFactoryImp().getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph paragraph = new Paragraph("Historia Clinica", font);
        paragraph.setAlignment(paragraph.ALIGN_CENTER);
        document.add(paragraph);

        document.add(new Phrase("\n")); // Add a line break

        // Generate the report content
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Historia Clínica de: " + historiaClinica.getFK().getNombreMascota());
        reportContent.append("\n");
        reportContent.append("Cliente: " + historiaClinica.getFK().getFK().getNombreCliente());
        reportContent.append("\n");
        reportContent.append("Peso: " + historiaClinica.getPeso() + " kg");
        reportContent.append("\n");
        reportContent.append("Tamaño: " + historiaClinica.getTamano() + " cm");
        reportContent.append("\n");
        reportContent.append("Enfermedades: " + historiaClinica.getEnfermedades());
        reportContent.append("\n");
        reportContent.append("Antecedentes: " + historiaClinica.getAntecedentes());
        reportContent.append("\n");
        reportContent.append("Diagnóstico: " + historiaClinica.getDiagnosticoHistoria());

        document.add(new Paragraph(reportContent.toString()));

        document.close();
    }

    public void exportDetalles(HttpServletResponse resp) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, resp.getOutputStream());
        document.open();

        Font font = new FontFactoryImp().getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph paragraph = new Paragraph("Historia Clinica", font);
        paragraph.setAlignment(paragraph.ALIGN_CENTER);
        document.add(paragraph);

        document.add(new Phrase("\n")); // Add a line break

        // Generate the report content
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Detalles de la Historia Clínica de: " + detallesHistoria.getFK().getFK().getNombreMascota());
        reportContent.append("\n");
        reportContent.append("Cliente: " + detallesHistoria.getFK().getFK().getFK().getNombreCliente());
        reportContent.append("\n");
        reportContent.append("Fecha de Detalles: " + detallesHistoria.getFkC().getFechaCita());
        reportContent.append("\n");
        reportContent.append("Peso: " + detallesHistoria.getPesoDetalles() + " kg");
        reportContent.append("\n");
        reportContent.append("Tamaño: " + detallesHistoria.getTamanoDetalles() + " cm");
        reportContent.append("\n");
        reportContent.append("Diagnóstico: " + detallesHistoria.getDiagnosticoDetalles());

        document.add(new Paragraph(reportContent.toString()));

        document.close();
    }
}