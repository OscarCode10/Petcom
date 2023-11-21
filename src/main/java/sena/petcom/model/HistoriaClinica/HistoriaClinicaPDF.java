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

public class HistoriaClinicaPDF {
    
    private HistoriaClinica historiaClinica;

    public HistoriaClinicaPDF(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    private void headReport(PdfPTable PdfPTable){
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(16);

        Font font = new FontFactoryImp().getFont(FontFactory.HELVETICA);
        cell.setPhrase(new Phrase("Historia Clinica de ", font));
        PdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Tiene un peso de: ", font));
        PdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Tiene un tamaño de: ", font));
        PdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Tiene de enfermedades: ", font));
        PdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Tiene de antecendetes: ", font));
        PdfPTable.addCell(cell);
        cell.setPhrase(new Phrase("Y su diagnóstico es: ", font));
        PdfPTable.addCell(cell);
    }

    private void bodyReport(PdfPTable pdfPTable){
        pdfPTable.addCell(historiaClinica.getFK().getNombreMascota() + " del Cliente " + historiaClinica.getFK().getFK().getNombreCliente());
        pdfPTable.addCell(String.valueOf(historiaClinica.getPeso()));
        pdfPTable.addCell(String.valueOf(historiaClinica.getTamano()));
        pdfPTable.addCell(historiaClinica.getEnfermedades());
        pdfPTable.addCell(historiaClinica.getAntecedentes());
        pdfPTable.addCell(historiaClinica.getDiagnosticoHistoria());
    }

    public void export(HttpServletResponse resp) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, resp.getOutputStream());
        document.open();
        Font font = new FontFactoryImp().getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph paragraph = new Paragraph("Historia Clinica", font);
        paragraph.setAlignment(paragraph.ALIGN_CENTER);
        document.add(paragraph);
        PdfPTable pdfPTable = new PdfPTable(6);
        pdfPTable.setWidthPercentage(100);
        pdfPTable.setSpacingBefore(15);
        pdfPTable.setWidths(new float[] {6f, 2f, 2f, 4f, 8f, 10f});
        pdfPTable.setWidthPercentage(110);

        headReport(pdfPTable);
        bodyReport(pdfPTable);

        document.add(pdfPTable);
        document.close();
    }
}
